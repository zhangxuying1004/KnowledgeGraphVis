package xmu.vis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMapperTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void deleteTableColumn() {
        testMapper.deleteTableColumn("tmp","test3");
    }

    @Test
    public void addTableColumn() {
        testMapper.addTableColumn("tmp","球员编号");
    }

    @Test
    public void createTable() {
        testMapper.createTable("tmp1");
    }

    @Test
    public void updateInfo() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("test1",1);
//        map.put("test2",4);
//        map.put("test3",4);
//        map.put("球员编号",4);
        testMapper.updateInfo("tmp",1, map);
    }

    @Test
    public void insertInfo() {
        Map<String,Object> map = new HashMap<>();
        map.put("test1",1);
        map.put("test2",4);
        map.put("test3",4);
        map.put("球员编号",4);
        testMapper.insertInfo("tmp",map);
    }
}