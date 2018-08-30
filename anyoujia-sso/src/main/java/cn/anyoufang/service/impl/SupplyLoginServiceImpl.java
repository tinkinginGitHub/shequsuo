package cn.anyoufang.service.impl;


import cn.anyoufang.entity.TbRenter;
import cn.anyoufang.entity.TbRenterExample;
import cn.anyoufang.entity.TbSupplier;
import cn.anyoufang.entity.TbSupplierExample;
import cn.anyoufang.mapper.TbRenterMapper;
import cn.anyoufang.mapper.TbSupplierMapper;
import cn.anyoufang.service.SupplyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 供应商端登录处理
 */
@Service
public class SupplyLoginServiceImpl implements SupplyLoginService {

    @Autowired
    private TbSupplierMapper supplierMapper;

    @Override
    public String login(String username, String password) {
         //根据用户名查询
        TbSupplierExample example = new TbSupplierExample();
        TbSupplierExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(username);
        List<TbSupplier> rents = supplierMapper.selectByExample(example);
        if (rents == null || rents.size() == 0) {
            //没有信息，返回false
            return "";
        }

        TbSupplier supplier = rents.get(0);

        //
        if (!password.equals(supplier.getPwd())) {
            //返回登录失败
            return "";
        }
        return supplier.getId();

    }

    @Override
    public boolean logout() {

        return false;
    }
}
