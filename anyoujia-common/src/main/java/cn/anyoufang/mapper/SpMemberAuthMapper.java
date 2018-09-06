package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMemberAuth;
import cn.anyoufang.entity.SpMemberAuthExample;
import cn.anyoufang.entity.SpMemberAuthKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberAuthMapper {
    int countByExample(SpMemberAuthExample example);

    int deleteByExample(SpMemberAuthExample example);

    int deleteByPrimaryKey(SpMemberAuthKey key);

    int insert(SpMemberAuth record);

    int insertSelective(SpMemberAuth record);

    List<SpMemberAuth> selectByExample(SpMemberAuthExample example);

    SpMemberAuth selectByPrimaryKey(SpMemberAuthKey key);

    int updateByExampleSelective(@Param("record") SpMemberAuth record, @Param("example") SpMemberAuthExample example);

    int updateByExample(@Param("record") SpMemberAuth record, @Param("example") SpMemberAuthExample example);

    int updateByPrimaryKeySelective(SpMemberAuth record);

    int updateByPrimaryKey(SpMemberAuth record);
}