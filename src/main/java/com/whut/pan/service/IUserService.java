package com.whut.pan.service;

import com.whut.pan.domain.User;

import java.util.List;
import java.util.Map;


/**
 * @author Sandeepin
 */
public interface IUserService {
    int add(User user);

    int update(User user);

    int deleteByIds(String[] ids);

    int deleteByUsernames(String[] userNames);

    User queryUserByUsername(String userName);

    List<User> queryUserList(Map<String, Object> params);
}
