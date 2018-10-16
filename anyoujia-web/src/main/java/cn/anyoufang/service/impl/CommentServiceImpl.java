package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpComment;
import cn.anyoufang.mapper.SpCommentMapper;
import cn.anyoufang.service.CommentService;
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
        int num = commentMapper.insertSelective(comment1);
        if(num == 1) {
            return true;
        }
        return false;
    }
}
