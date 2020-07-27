package xmu.vis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.vis.domain.NodeInfo;

@Mapper
@Component
public interface NodeInfoMapper {

    public NodeInfo getNodeInfoByName(String name);

    public Integer updateAttribute(NodeInfo nodeInfo);

    public NodeInfo getNodeInfoById(Integer id);

    public String getNodeNameById(Integer id);

}
