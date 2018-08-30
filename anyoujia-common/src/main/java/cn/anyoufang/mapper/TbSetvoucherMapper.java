package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbSetvoucher;
import cn.anyoufang.entity.TbSetvoucherExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSetvoucherMapper {
    int countByExample(TbSetvoucherExample example);

    int deleteByExample(TbSetvoucherExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbSetvoucher record);

    int insertSelective(TbSetvoucher record);

    List<TbSetvoucher> selectByExample(TbSetvoucherExample example);

    TbSetvoucher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbSetvoucher record, @Param("example") TbSetvoucherExample example);

    int updateByExample(@Param("record") TbSetvoucher record, @Param("example") TbSetvoucherExample example);

    int updateByPrimaryKeySelective(TbSetvoucher record);

    int updateByPrimaryKey(TbSetvoucher record);
}