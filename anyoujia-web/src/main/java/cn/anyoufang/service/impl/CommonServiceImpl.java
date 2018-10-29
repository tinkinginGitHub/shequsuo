package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpActionRecords;
import cn.anyoufang.entity.SpMemberComment;
import cn.anyoufang.mapper.SpActionRecordsMapper;
import cn.anyoufang.mapper.SpMemberCommentMapper;
import cn.anyoufang.service.CommonService;
import cn.anyoufang.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daiping
 */

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private SpMemberCommentMapper commentMapper;

    @Autowired
    private SpActionRecordsMapper actionRecordsMapper;



    @Override
    public boolean saveComments(String comment, String url,String phone) {
        SpMemberComment comment1 = new SpMemberComment();
        comment1.setComment(comment);
        comment1.setPicurl(url);
        comment1.setPhone(phone);
        comment1.setCreatetime(DateUtil.generateTenTime());
        int num = commentMapper.insertSelective(comment1);
        if(num == 1) {
            return true;
        }
        return false;
    }

    /**
     * 检查安全密码
     * @param dbpassword
     * @param answeredpassword
     * @return
     */
    @Override
    public boolean checkSecurityPassword(String dbpassword , String answeredpassword) {
     if(dbpassword != null&& dbpassword.equals(answeredpassword)) {
         return true;
     }
        return false;
    }

    @Override
    public boolean saveActionRecords(String locksn, int memberid, String action, int when) {
        SpActionRecords record = new SpActionRecords();
        record.setAction(action);
        record.setLocksn(locksn);
        record.setMemberid(memberid);
        record.setActiontime(when);
        actionRecordsMapper.insertSelective(record);
        return true;
    }
}
