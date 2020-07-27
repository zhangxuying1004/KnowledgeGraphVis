package xmu.vis.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.vis.domain.NodeRelationInfo;

import java.util.List;

@Mapper
@Component
public interface NodeRelationInfoMapper {

    public NodeRelationInfo getNodeRelationByNode1(Integer node1);

    public List<Integer> getNode1ByNode2(Integer node2);

    public NodeRelationInfo getNodeRelationByNode12(Integer node1, Integer node2);

    public Integer updateRelation(Integer id,Integer node2);
}
