package xmu.vis.mapper;

import net.sf.json.JSONObject;
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
public class AttributeMapperTest {

    @Autowired
    private AttributeMapper attributeMapper;

    @Test
    public void getAttributeById() {
        Map<String,Object> map = attributeMapper.getAttributeById("player_attribute",1);
        map.remove("id");
        System.out.println(JSONObject.fromObject(map));
    }

    @Test
    public void addAttribute() {
        attributeMapper.addAttribute("player_attribute","test");
    }

    @Test
    public void deleteAttribute() {
        attributeMapper.deleteAttribute("player_attribute","test");
    }

    @Test
    public void updateAttribute() {
        Map<String,Object> map = new HashMap<>();
        map.put("test","123");
        System.out.println(attributeMapper.updateAttribute("player_attribute",1,map));
    }

    @Test
    public void createTable() {
        attributeMapper.createTable("contest_attribute");
    }

    @Test
    public void existTable() {
        System.out.println(attributeMapper.existTable("tmp1"));
    }

    @Test
    public void getAllAttributeByName() {
        System.out.println(attributeMapper.getAllAttributeByName("tmp"));
    }

    @Test
    public void updateColumn() {
        attributeMapper.updateColumn("tmp","球员编号","test4");
    }

    @Test
    public void deleteTable() {
        attributeMapper.deleteTable("雅岚");
    }
}