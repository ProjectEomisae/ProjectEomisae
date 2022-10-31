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
//  어떠한 ip가 지난 lookBackSeconds 동안 저질렀던 나쁜 행위의 갯수를 가져오는 것
//  lookBackSeconds :지난 몇초 내지 몇시간 안에 갯수가 ~개를 초과하면 차단시키겠다,
//  차단한계가 3이고 (lookBackSeconds = 600)이면 : 지난 10분 내에 3번의 실패나 불법을 일으키면 차단을 시킨다.
    int selectBannedIpCountByIp(
            @Param(value = "ip") String ip);
//  현재 유효한 것만. expiredAt을 지나지 않았고 expired가 false인 것을 골라올 것
//  이 메서드에 의해 반환된 결과값이 0이라면 그 유저는 차단 당하지 않은 것. 초과하면 차단되었다는 뜻이겠다.
    int selectBannedIpCountByIpAll(
            @Param(value = "ip") String ip);
//  만료 여부를 떠나 지난 이력을 전부 조회.
    ActivityLogEntity[] selectActivityLog(
            @Param(value = "userIndex") int userIndex,
            @Param(value = "count") int count,
            @Param(value = "offset") int offset);


}
