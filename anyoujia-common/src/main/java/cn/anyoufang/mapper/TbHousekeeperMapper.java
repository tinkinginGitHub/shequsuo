package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbHousekeeper;
import cn.anyoufang.entity.TbHousekeeperExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbHousekeeperMapper {
    int countByExample(TbHousekeeperExample example);

    int deleteByExample(TbHousekeeperExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbHousekeeper record);

    int insertSelective(TbHousekeeper record);

    List<TbHousekeeper> selectByExample(TbHousekeeperExample example);

    TbHousekeeper selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbHousekeeper record, @Param("example") TbHousekeeperExample example);

    int updateByExample(@Param("record") TbHousekeeper record, @Param("example") TbHousekeeperExample example);

    int updateByPrimaryKeySelective(TbHousekeeper record);

    int updateByPrimaryKey(TbHousekeeper record);
}