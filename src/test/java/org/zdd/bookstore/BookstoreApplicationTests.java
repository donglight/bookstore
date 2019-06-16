/*package org.zdd.bookstore;

import org.zdd.bookstore.model.dao.UserMapper;
import org.zdd.bookstore.model.dao.custom.CustomMapper;
import org.zdd.bookstore.model.entity.Privilege;
import org.zdd.bookstore.model.entity.Role;
import org.zdd.bookstore.model.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Value("${return_url}")
    String a;
    @Test
    public void contextLoads() {
        System.out.println("---------------------");
        System.out.println(customMapper);
        System.out.println(a);
        List<Role> rolesByUserId = customMapper.findRolesByUserId(1);
        List<Privilege> privilegeByRoleId = customMapper.findPrivilegesByRoleId(1);
        System.out.println();
        //List<OrderCustom> ordersByUserId = customMapper.findOrdersById(1);
    }

}*/
