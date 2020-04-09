package com.chj.service;

import com.chj.mapper.UserMapper;
import com.chj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：chj
 * @date ：Created in 2020/4/6 17:21
 * @params :
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectByName(String username){
        return userMapper.selectByName(username);
    }
}
