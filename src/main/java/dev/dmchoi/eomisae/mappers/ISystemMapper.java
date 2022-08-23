package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.system.SystemActivityLogEntity;
import dev.dmchoi.eomisae.entities.system.SystemBannedIpEntity;
import dev.dmchoi.eomisae.entities.system.SystemExceptionLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ISystemMapper {
    int insertActivityLog(SystemActivityLogEntity systemActivityLogEntity);
    int insertBannedIp(SystemBannedIpEntity systemBannedIpEntity);
    int insertExceptionLog(SystemExceptionLogEntity systemExceptionLogEntity);
    int selectBadActivityCountByIp(
            @Param(value = "ip") String ip,
            @Param(value = "lookBackSeconds") int lookBackSeconds);
    int selectBannedIpCountByIp(
            @Param(value = "ip") String ip);
    int selectBannedIpCountByIpAll(
            @Param(value = "ip") String ip);


}
