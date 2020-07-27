package xmu.vis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.vis.domain.NodeRelationInfoHistory;

@Mapper
@Component
public interface NodeRelationInfoHistoryMapper {

    public Integer insertHistory(NodeRelationInfoHistory nodeRelationInfoHistory);


}
