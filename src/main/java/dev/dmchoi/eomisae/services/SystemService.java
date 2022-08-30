package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.interfaces.IResult;
import dev.dmchoi.eomisae.entities.system.ActivityLogEntity;
import dev.dmchoi.eomisae.entities.system.BannedIpEntity;
import dev.dmchoi.eomisae.entities.system.ExceptionLogEntity;
import dev.dmchoi.eomisae.mappers.ISystemMapper;
import dev.dmchoi.eomisae.models.PagingModel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service(value = "dev.dmchoi.eomisae.services.SystemService")
public class SystemService {
    public static final int BAD_ACTIVITY_LOOK_BACK_SECONDS = 600;
    public static final int BAD_ACTIVITY_LIMIT = 5;
    public static final int BAN_MINUTES = 10;

    private final ISystemMapper systemMapper;

    @Autowired
    public SystemService(ISystemMapper systemMapper) {
        this.systemMapper = systemMapper;
    }

    public void checkActivityAndBan(HttpServletRequest request) {
        int badActivityCount = this.systemMapper.selectBadActivityCountByIp(request.getRemoteAddr(), BAD_ACTIVITY_LOOK_BACK_SECONDS);
        if (badActivityCount >= BAD_ACTIVITY_LIMIT) {
            int bannedTimes = this.systemMapper.selectBannedIpCountByIpAll(request.getRemoteAddr()) + 1;
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
        }
    }

    public boolean isIpBanned(HttpServletRequest request) {
        return this.systemMapper.selectBannedIpCountByIp(request.getRemoteAddr()) > 0;
    }

    public void putActivityLog(int index, HttpServletRequest request, IResult<? extends Enum<?>> iResult) {
        this.putActivityLog(index, request, iResult, true);
    }
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
