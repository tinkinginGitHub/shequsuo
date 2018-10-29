package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMemberComment;
import cn.anyoufang.entity.SpMemberCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberCommentMapper {
    int countByExample(SpMemberCommentExample example);

    int deleteByExample(SpMemberCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpMemberComment record);

    int insertSelective(SpMemberComment record);

    List<SpMemberComment> selectByExample(SpMemberCommentExample example);

    SpMemberComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpMemberComment record, @Param("example") SpMemberCommentExample example);

    int updateByExample(@Param("record") SpMemberComment record, @Param("example") SpMemberCommentExample example);

    int updateByPrimaryKeySelective(SpMemberComment record);

    int updateByPrimaryKey(SpMemberComment record);
}