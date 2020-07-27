package xmu.vis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface TestMapper {

    public void deleteTableColumn(String tableName,String column);

    public void addTableColumn(String tableName,String column);

    public void createTable(String tableName);

    public Integer updateInfo(String tableName, Integer id, Map<String,Object> map);

    public Integer insertInfo(String tableName, Map<String,Object> map);

}
