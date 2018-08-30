package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbVoucher;
import cn.anyoufang.entity.TbVoucherExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbVoucherMapper {
    int countByExample(TbVoucherExample example);

    int deleteByExample(TbVoucherExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbVoucher record);

    int insertSelective(TbVoucher record);

    List<TbVoucher> selectByExample(TbVoucherExample example);

    TbVoucher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbVoucher record, @Param("example") TbVoucherExample example);

    int updateByExample(@Param("record") TbVoucher record, @Param("example") TbVoucherExample example);

    int updateByPrimaryKeySelective(TbVoucher record);

    int updateByPrimaryKey(TbVoucher record);
}