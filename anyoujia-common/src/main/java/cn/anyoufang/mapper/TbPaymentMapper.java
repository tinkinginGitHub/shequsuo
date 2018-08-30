package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbPayment;
import cn.anyoufang.entity.TbPaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPaymentMapper {
    int countByExample(TbPaymentExample example);

    int deleteByExample(TbPaymentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbPayment record);

    int insertSelective(TbPayment record);

    List<TbPayment> selectByExample(TbPaymentExample example);

    TbPayment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbPayment record, @Param("example") TbPaymentExample example);

    int updateByExample(@Param("record") TbPayment record, @Param("example") TbPaymentExample example);

    int updateByPrimaryKeySelective(TbPayment record);

    int updateByPrimaryKey(TbPayment record);
}