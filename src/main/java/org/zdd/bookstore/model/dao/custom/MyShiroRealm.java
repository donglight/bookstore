package org.zdd.bookstore.model.dao.custom;

import org.zdd.bookstore.config.UserResourceProperties;
import org.zdd.bookstore.model.dao.UserMapper;
import org.zdd.bookstore.model.entity.Privilege;
import org.zdd.bookstore.model.entity.Role;
import org.zdd.bookstore.model.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserResourceProperties userResourceProperties;

    @Autowired
    private CustomMapper customMapper;


    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //权限配置-->MyShiroRealm.doGetAuthorizationInfo()
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();


        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Role> roles = customMapper.findRolesByUserId(user.getUserId());

        for (Role role : roles) {
            authorizationInfo.addRole(role.getCode());
            List<Privilege> privileges = customMapper.findPrivilegesByRoleId(role.getRoleId());
            for (Privilege privilege : privileges) {
                authorizationInfo.addStringPermission(privilege.getCode());
            }
        }

        return authorizationInfo;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        String username = (String) authenticationToken.getPrincipal();

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username).andEqualTo("active", userResourceProperties.getActive());
        List<User> users = userMapper.selectByExample(example);

        if (users != null && users.size() != 0) {

            User user = users.get(0);
            AuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    this.getName()
            );
            return simpleAuthenticationInfo;
        }

        return null;
    }
}
