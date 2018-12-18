package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpMemberMapper {

    int countByExample(SpMemberExample example);

    int deleteByExample(SpMemberExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(SpMember record);

    int insertSelective(SpMember record);

    List<SpMember> selectByExample(SpMemberExample example);

    SpMember selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") SpMember record, @Param("example") SpMemberExample example);

    int updateByExample(@Param("record") SpMember record, @Param("example") SpMemberExample example);

    int updateByPrimaryKeySelective(SpMember record);

    int updateByPrimaryKey(SpMember record);

    /**
     * 自定义查询
     * @param locksn
     * @return
     */
    Map<String,String> selectBySn(String locksn);

    /**
     * 自定义关联查询
     * @param uid
     * @return
     */
    Map<String,Object> selectByIdJoinFind(Integer uid);

}