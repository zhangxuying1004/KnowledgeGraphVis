package xmu.vis.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class VisServiceTest {
    @Autowired
    private VisService visService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNodeNameByFatherNodeAndRelation() {
        String nodeName = "太平洋舰队司令部";
        List<String> childrenName = visService.getNodeNameByFatherNodeAndRelation(nodeName, "下属");
        System.out.println(childrenName);
    }
}