package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.interfaces.IResult;
import dev.dmchoi.eomisae.entities.system.SystemActivityLogEntity;
import dev.dmchoi.eomisae.entities.system.SystemBannedIpEntity;
import dev.dmchoi.eomisae.entities.system.SystemExceptionLogEntity;
import dev.dmchoi.eomisae.mappers.ISystemMapper;
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
                banMinutes = Integer.MAX_VALUE;
            }
            Date currentDate = new Date();
            SystemBannedIpEntity systemBannedIpEntity = SystemBannedIpEntity.build()
                    .setCreatedAt(currentDate)
                    .setExpiresAt(DateUtils.addMinutes(currentDate, banMinutes))
                    .setExpired(false)
                    .setIp(request.getRemoteAddr());
            this.systemMapper.insertBannedIp(systemBannedIpEntity);
        }
    }

    public boolean isIpBanned(HttpServletRequest request) {
        return this.systemMapper.selectBannedIpCountByIp(request.getRemoteAddr()) > 0;
    }

    public void putActivityLog(HttpServletRequest request, IResult<? extends Enum<?>> iResult, boolean checkPast) {
        Date currentDate = new Date();
        SystemActivityLogEntity systemActivityLogEntity = SystemActivityLogEntity.build()
                .setCreatedAt(currentDate)
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"))
                .setRequestUri(request.getRequestURI())
                .setResult(iResult.getResult().name());
        this.systemMapper.insertActivityLog(systemActivityLogEntity);
        if (checkPast) {
            this.checkActivityAndBan(request);
        }
    }

    public void putExceptionLog(Exception ex) {
        String message = ex.getMessage();
        String stacktrace = ExceptionUtils.getStackTrace(ex);
        if (message == null) {
            message = "";
        }
        Date currentDate = new Date();
        SystemExceptionLogEntity systemExceptionLogEntity = SystemExceptionLogEntity.build()
                .setCreatedAt(currentDate)
                .setMessage(message)
                .setStacktrace(stacktrace);
        this.systemMapper.insertExceptionLog(systemExceptionLogEntity);
    }
}
