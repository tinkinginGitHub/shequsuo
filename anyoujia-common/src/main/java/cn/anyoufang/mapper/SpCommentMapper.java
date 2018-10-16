package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpComment;
import cn.anyoufang.entity.SpCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpCommentMapper {
    int countByExample(SpCommentExample example);

    int deleteByExample(SpCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpComment record);

    int insertSelective(SpComment record);

    List<SpComment> selectByExample(SpCommentExample example);

    SpComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpComment record, @Param("example") SpCommentExample example);

    int updateByExample(@Param("record") SpComment record, @Param("example") SpCommentExample example);

    int updateByPrimaryKeySelective(SpComment record);

    int updateByPrimaryKey(SpComment record);
}