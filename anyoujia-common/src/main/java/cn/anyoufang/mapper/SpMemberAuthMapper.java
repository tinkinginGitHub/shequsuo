package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMemberAuth;
import cn.anyoufang.entity.SpMemberAuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberAuthMapper {
    int countByExample(SpMemberAuthExample example);

    int deleteByExample(SpMemberAuthExample example);

    int insert(SpMemberAuth record);

    int insertSelective(SpMemberAuth record);

    List<SpMemberAuth> selectByExample(SpMemberAuthExample example);

    int updateByExampleSelective(@Param("record") SpMemberAuth record, @Param("example") SpMemberAuthExample example);

    int updateByExample(@Param("record") SpMemberAuth record, @Param("example") SpMemberAuthExample example);
}