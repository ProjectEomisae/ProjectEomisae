package dev.dmchoi.eomisae.mappers;

import dev.dmchoi.eomisae.entities.system.ActivityLogEntity;
import dev.dmchoi.eomisae.entities.system.BannedIpEntity;
import dev.dmchoi.eomisae.entities.system.ExceptionLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ISystemMapper {

    int selectActivityCountTotalByUserIndex(
            @Param(value = "userIndex") int userIndex);
    int insertActivityLog(ActivityLogEntity activityLogEntity);
    int insertBannedIp(BannedIpEntity bannedIpEntity);
    int insertExceptionLog(ExceptionLogEntity exceptionLogEntity);
    int selectBadActivityCountByIp(
            @Param(value = "ip") String ip,
            @Param(value = "lookBackSeconds") int lookBackSeconds);
    int selectBannedIpCountByIp(
            @Param(value = "ip") String ip);
    int selectBannedIpCountByIpAll(
            @Param(value = "ip") String ip);
    ActivityLogEntity[] selectActivityLog(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);


}
