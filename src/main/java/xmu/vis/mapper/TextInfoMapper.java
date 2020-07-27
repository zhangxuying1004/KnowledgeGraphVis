package xmu.vis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.vis.domain.TextInfo;

import java.util.List;

@Mapper
@Component
public interface TextInfoMapper {

    public Integer insertText(TextInfo textInfo);

    public TextInfo getTextInfoById(Integer id);

    public Integer updateTextInfo(TextInfo textInfo);

    public Integer updateTextStatus(Integer id, Integer status);

    public List<TextInfo> getAllText();

    public Integer test(Integer status);
}
