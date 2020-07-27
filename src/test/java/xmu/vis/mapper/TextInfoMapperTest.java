package xmu.vis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.vis.domain.TextInfo;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextInfoMapperTest {

    @Autowired
    private TextInfoMapper textInfoMapper;

    @Test
    public void insertText() {
        TextInfo textInfo = new TextInfo();
        textInfo.setContent("123");
        textInfoMapper.insertText(textInfo);
        System.out.println(textInfo);
    }

    @Test
    public void getTextInfoById() {
        System.out.println(textInfoMapper.getTextInfoById(2));
    }

    @Test
    public void updateTextInfo() {
        TextInfo textInfo = new TextInfo();
        textInfo.setId(1);
        textInfo.setExtractNode1("222");
        textInfo.setExtractRelation("22");
        textInfo.setExtractNode2(("11"));
        textInfoMapper.updateTextInfo(textInfo);
    }

    @Test
    public void updateTextStatus() {
        textInfoMapper.updateTextStatus(1,1);
    }

    @Test
    public void getAllText() {
        System.out.println(textInfoMapper.getAllText());
    }

    @Test
    public void test() {
        System.out.println(textInfoMapper.test(0));
    }
}