package org.zdd.bookstore.model.service.impl;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.utils.BSResultUtil;
import org.zdd.bookstore.config.UserResourceProperties;
import org.zdd.bookstore.model.dao.StoreMapper;
import org.zdd.bookstore.model.dao.UserMapper;
import org.zdd.bookstore.model.dao.UserRoleMapper;
import org.zdd.bookstore.model.dao.custom.CustomMapper;
import org.zdd.bookstore.model.entity.Store;
import org.zdd.bookstore.model.entity.User;
import org.zdd.bookstore.model.entity.UserRole;
import org.zdd.bookstore.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务
 */
@Service
@EnableConfigurationProperties(UserResourceProperties.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private UserResourceProperties userResourceProperties;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Value("${super.role-id}")
    private String superRole;

    @Value("${ordinary.role-id}")
    private String ordinaryRole;

    @Value("${business.role-id}")
    private String businessRole;

    @Override
    public BSResult login(String username, String password) {
        //根据用户名查询用户信息
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username).andEqualTo("active","1");
        List<User> users = userMapper.selectByExample(userExample);

        if(users == null || users.size() == 0){
            return BSResultUtil.build(400, "用户名或密码错误");
        }

        //若不为空，取用户信息
        User user = users.get(0);

        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return BSResultUtil.build(400, "用户名或密码错误");
        }

        return BSResultUtil.success(user);
    }

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    @Override
    public BSResult checkUserExistByUsername(String username) {

        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        List<User> users = userMapper.selectByExample(userExample);

        if(users == null || users.size() == 0){
            return BSResultUtil.build(200, "用户名可以使用", true);
        }else{
            return BSResultUtil.build(400, "用户名已被注册", false);
        }
    }

    /**
     * 注册用户,向数据库插入一条记录
     * @param user
     * @return
     */
    @Override
    @Transactional
    public BSResult saveUser(User user) {

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCode(UUID.randomUUID().toString());
        //刚刚注册的用户处于未激活状态
        user.setActive(userResourceProperties.getIsNotActive());
        user.setLocation(userResourceProperties.getLocation());
        user.setDetailAddress(userResourceProperties.getDetailAddress());
        user.setCreated(new Date());
        user.setUpdated(new Date());

        userMapper.insert(user);
        return BSResultUtil.success(user);
    }

    /**
     * 激活用户
     * @param activeCode
     * @return
     */
    @Override
    @Transactional
    public BSResult activeUser(String activeCode) {

        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("code",activeCode);//code是激活码
        List<User> users = userMapper.selectByExample(userExample);
        if(users != null && users.size() != 0){
            User user = users.get(0);
            //设置激活状态为1激活
            user.setActive(userResourceProperties.getActive());
            //一次性激活码
            user.setCode(null);
            userMapper.updateByPrimaryKey(user);

            //分配角色
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setCreated(new Date());
            userRole.setUpdated(new Date());
            if(userResourceProperties.getOrdinaryCustomer().equals(user.getIdentity())){
                //普通用户
                userRole.setRoleId(Integer.parseInt(ordinaryRole));

                userRoleMapper.insert(userRole);
            }else if(userResourceProperties.getBusinessCustomer().equals(user.getIdentity())){
                //企业用户或商家
                userRole.setRoleId(Integer.parseInt(businessRole));
                userRoleMapper.insert(userRole);
                //激活后默认开通一个店铺
                Store store = new Store();
                store.setStoreManagerId(user.getUserId());
                store.setStoreManagerName(user.getUsername());
                store.setStoreName("新店铺");
                store.setStorePosition(user.getLocation());
                store.setCreated(new Date());
                store.setUpdated(new Date());
                storeMapper.insert(store);
            }
            return BSResultUtil.success(user.getUsername());
        }

        return BSResultUtil.build(400, "不好意思，激活码已经失效", "");
    }

    @Override
    public User addUser(User user) {
        user.setActive("1");
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public BSResult updateUser(User user) {
        if(StringUtils.isEmpty(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        userMapper.updateByPrimaryKeySelective(user);
        return BSResultUtil.build(200, "保存成功");
    }

    @Override
    public void delUser(int userId) {
        Example exampleOfUserRole = new Example(UserRole.class);
        exampleOfUserRole.createCriteria().andEqualTo("userId", userId);
        userRoleMapper.deleteByExample(exampleOfUserRole);
        userMapper.deleteByPrimaryKey(userId);

    }

    @Override
    public List<User> findBusinesses(int roleId) {
        return customMapper.findBusinesses(roleId);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public BSResult compareAndChange(int userId,String oldPassword, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        String password = user.getPassword();
        if(password.equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))){
            user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            userMapper.updateByPrimaryKeySelective(user);
            return BSResultUtil.build(200, "修改密码成功");
        }else{
            return BSResultUtil.build(400, "旧密码不正确");
        }
    }

}
