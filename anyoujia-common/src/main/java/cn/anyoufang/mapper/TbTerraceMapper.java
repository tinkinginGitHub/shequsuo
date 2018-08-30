package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbTerrace;
import cn.anyoufang.entity.TbTerraceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTerraceMapper {
    int countByExample(TbTerraceExample example);

    int deleteByExample(TbTerraceExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbTerrace record);

    int insertSelective(TbTerrace record);

    List<TbTerrace> selectByExample(TbTerraceExample example);

    TbTerrace selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbTerrace record, @Param("example") TbTerraceExample example);

    int updateByExample(@Param("record") TbTerrace record, @Param("example") TbTerraceExample example);

    int updateByPrimaryKeySelective(TbTerrace record);

    int updateByPrimaryKey(TbTerrace record);
}