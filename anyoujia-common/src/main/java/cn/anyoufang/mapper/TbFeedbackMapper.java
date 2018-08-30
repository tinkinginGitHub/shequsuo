package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbFeedback;
import cn.anyoufang.entity.TbFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFeedbackMapper {
    int countByExample(TbFeedbackExample example);

    int deleteByExample(TbFeedbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbFeedback record);

    int insertSelective(TbFeedback record);

    List<TbFeedback> selectByExample(TbFeedbackExample example);

    TbFeedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbFeedback record, @Param("example") TbFeedbackExample example);

    int updateByExample(@Param("record") TbFeedback record, @Param("example") TbFeedbackExample example);

    int updateByPrimaryKeySelective(TbFeedback record);

    int updateByPrimaryKey(TbFeedback record);
}