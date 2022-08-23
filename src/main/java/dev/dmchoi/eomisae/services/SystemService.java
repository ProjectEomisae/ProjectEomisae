package dev.dmchoi.eomisae.services;

import dev.dmchoi.eomisae.mappers.ISystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

    public boolean isIpBanned(HttpServletRequest request) {
        return this.systemMapper.selectBannedIpCountByIp(request.getRemoteAddr()) > 0;
    }
}
