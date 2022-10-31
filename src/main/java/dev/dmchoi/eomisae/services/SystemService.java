package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.entities.member.UserEntity;
import dev.dmchoi.eomisae.interfaces.IResult;
import dev.dmchoi.eomisae.entities.system.ActivityLogEntity;
import dev.dmchoi.eomisae.entities.system.BannedIpEntity;
import dev.dmchoi.eomisae.entities.system.ExceptionLogEntity;
import dev.dmchoi.eomisae.mappers.ISystemMapper;
import dev.dmchoi.eomisae.mappers.IUserMapper;
import dev.dmchoi.eomisae.models.PagingModel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service(value = "dev.dmchoi.eomisae.services.SystemService")
public class SystemService {
    public static final int BAD_ACTIVITY_LOOK_BACK_SECONDS = 600;
    public static final int BAD_ACTIVITY_LIMIT = 10;
    public static final int BAN_MINUTES = 10;

    private final ISystemMapper systemMapper;
    private final IUserMapper userMapper;

    @Autowired
    public SystemService(ISystemMapper systemMapper, IUserMapper userMapper) {
        this.systemMapper = systemMapper;
        this.userMapper = userMapper;
    }


    //  checkActivityAndBan 메서드에서는 전달받은 request가 가지고 있는 Ip 주소를 활용해서
//  그 Ip 주소가 지난 몇 분 혹은 몇 시간 동안 몇번의 FAILURE나 ILLEGAL을 발생시켰는지 확인하고
//  그 값을 넘어섰다면 banned_ips에다 insert를 하는 작업을 할 것.
    public void checkActivityAndBan(HttpServletRequest request) {
        int badActivityCount = this.systemMapper.selectBadActivityCountByIp(request.getRemoteAddr(), BAD_ACTIVITY_LOOK_BACK_SECONDS);
        if (badActivityCount >= BAD_ACTIVITY_LIMIT) {
            int bannedTimes = this.systemMapper.selectBannedIpCountByIpAll(request.getRemoteAddr());
//      여태 차단된 이력이 없는 ip라면 0이 나오겠고, 악질적인 ip였다면 그 이상이 나오겠다.
            int banMinutes = (int) (BAN_MINUTES * Math.pow(bannedTimes, bannedTimes));
            if (banMinutes < BAN_MINUTES) {
                banMinutes = Integer.MAX_VALUE; // 오버플로우 발생 방지
//                초범 - 10 * (1 ^ 1) = 10 -> 10분차단
//                2범 - 10 * (2 ^ 2) = 40 -> 40분차단
//                3범 - 10 * (3 ^ 3) = 270 -> 270분차단
//                4범 - 10 * (4 ^ 4) = 2560 -> 2560분차단
            }
            Date currentDate = new Date();
            BannedIpEntity bannedIpEntity = BannedIpEntity.build()
                    .setCreatedAt(currentDate)
                    .setExpiresAt(DateUtils.addMinutes(currentDate, banMinutes))
                    .setExpired(false)
                    .setIp(request.getRemoteAddr());
            this.systemMapper.insertBannedIp(bannedIpEntity);
            System.out.println(banMinutes);
        }
    }

    public boolean isIpBanned(HttpServletRequest request) {
        return this.systemMapper.selectBannedIpCountByIp(request.getRemoteAddr()) > 0;
    } // 현재 유효한 것만 확인. 0을 초과하면 차단이 되었다는 것

    @Transactional
    public void putActivityLog(String email, HttpServletRequest request, IResult<? extends Enum<?>> iResult) {
        this.putActivityLog(email, request, iResult, true);
    }

    @Transactional
    public void putActivityLog(int index, HttpServletRequest request, IResult<? extends Enum<?>> iResult) {
        this.putActivityLog(index, request, iResult, true);
    }

    @Transactional
    public void putActivityLog(String email, HttpServletRequest request, IResult<? extends Enum<?>> iResult, boolean checkPast) {
        Date currentDate = new Date();
        UserEntity user = this.userMapper.selectUserIndexByEmail(email);
        if (user == null) {
            return;
        }
        ActivityLogEntity activityLogEntity = ActivityLogEntity.build()
                .setUserIndex(user.getIndex())
                .setCreatedAt(currentDate)
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"))
                .setRequestUri(request.getRequestURI())
                .setResult(iResult.getResult().name());
        this.systemMapper.insertActivityLog(activityLogEntity);
        if (checkPast) {
            this.checkActivityAndBan(request);
        }
    }

    @Transactional
    public void putActivityLog(int index, HttpServletRequest request, IResult<? extends Enum<?>> iResult, boolean checkPast) {
        Date currentDate = new Date();
        ActivityLogEntity activityLogEntity = ActivityLogEntity.build()
                .setUserIndex(index)
                .setCreatedAt(currentDate)
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"))
                .setRequestUri(request.getRequestURI())
                .setResult(iResult.getResult().name());
        this.systemMapper.insertActivityLog(activityLogEntity);
        if (checkPast) {
            this.checkActivityAndBan(request);
        }
    }

    public int activityLogTotalCountByUserIndex(int index) {
        return this.systemMapper.selectActivityCountTotalByUserIndex(index);
    }

    public ActivityLogEntity[] getActivityLog(int index, PagingModel pagingModel) {
        return this.systemMapper.selectActivityLog(index,
                pagingModel.rowCountPerPage,
                (pagingModel.requestPage - 1) * pagingModel.rowCountPerPage);
    }


    //  자바가 가진 예외는 StackTrace를 가지는 문자열 값이 없기 때문에 가상의 화면에 찍는다,
//  가상으로 하나 더 만들어 거기다 찍어두고 찍은 내용을 스트링라이터로 가져온 다음 String으로 가져오는게
//  방법이었으나, DateUtils를 쓰기 위해 추가했던 의존성 안에 ExceptionUtils 라는 것이 있다.
    public void putExceptionLog(Exception ex) {
        String message = ex.getMessage();
        String stacktrace = ExceptionUtils.getStackTrace(ex);
        if (message == null) {
            message = "";
        }
        Date currentDate = new Date();
        ExceptionLogEntity exceptionLogEntity = ExceptionLogEntity.build()
                .setCreatedAt(currentDate)
                .setMessage(message)
                .setStacktrace(stacktrace);
        this.systemMapper.insertExceptionLog(exceptionLogEntity);
    }
}
