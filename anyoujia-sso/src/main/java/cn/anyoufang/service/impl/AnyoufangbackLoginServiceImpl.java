package cn.anyoufang.service.impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.mapper.TbRenterMapper;
import cn.anyoufang.mapper.TbTerraceMapper;
import cn.anyoufang.service.AnyoufangBackLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
public class AnyoufangbackLoginServiceImpl implements AnyoufangBackLoginService {

    @Autowired
    private TbTerraceMapper terraceMapper;

    @Override
    public boolean login(String username, String pwd) {


        //根据用户名查询
        TbTerraceExample example = new TbTerraceExample();
        TbTerraceExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(username);
        List<TbTerrace> terraces = terraceMapper.selectByExample(example);
        if (terraces == null || terraces.size() == 0) {
            //没有信息，返回false
            return false;
        }
        TbTerrace terrace = terraces.get(0);

        //校验进行md5加密之后的密码
        if (!DigestUtils.md5DigestAsHex(pwd.getBytes())
                .equals(terrace.getPwd())) {
            //返回登录失败
            return false;
        }
        return true;
    }
}
