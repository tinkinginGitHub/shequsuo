package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpComment;
import cn.anyoufang.mapper.SpCommentMapper;
import cn.anyoufang.service.CommentService;
import cn.anyoufang.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daiping
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private SpCommentMapper commentMapper;



    @Override
    public boolean saveComments(String comment, String url,String phone) {
        SpComment comment1 = new SpComment();
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
}
