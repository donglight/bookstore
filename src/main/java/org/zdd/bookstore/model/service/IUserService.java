package org.zdd.bookstore.model.service;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.model.entity.User;

import java.util.List;

public interface IUserService {

    BSResult login(String username, String password);

    BSResult checkUserExistByUsername(String username);

    BSResult saveUser(User user);

    BSResult activeUser(String activeCode);

    User addUser(User user);

    BSResult updateUser(User user);

    void delUser(int userId);

    List<User> findBusinesses(int roleId);

    List<User> findAllUsers();

    User findById(int userId);

    BSResult compareAndChange(int userId, String oldPassword, String newPassword);
}
