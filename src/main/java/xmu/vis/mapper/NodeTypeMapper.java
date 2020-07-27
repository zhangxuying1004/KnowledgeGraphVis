package xmu.vis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.vis.domain.NodeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface NodeTypeMapper {

    public NodeType getNodeTypeByNodeTypeId(Integer nodeTypeId);

    public NodeType getNodeTypeByName(String name);

    public String getTableNameByNodeTypeId(Integer nodeTypeId);

    public Integer insertNodeType(NodeType nodeType);

    public List<NodeType> getAllNodeType();

    public NodeType getRootNode();

    public Integer deleteNodeType(String name);
}
