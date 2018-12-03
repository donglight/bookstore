package org.zdd.bookstore.model.dao.custom;

import org.zdd.bookstore.model.entity.Privilege;
import org.zdd.bookstore.model.entity.Role;
import org.zdd.bookstore.model.entity.User;
import org.zdd.bookstore.model.entity.custom.OrderCustom;

import java.util.List;

/**
 * 自定义mapper
 */

public interface CustomMapper {

    List<OrderCustom> findOrdersByUserId(int userId);

    List<OrderCustom> findOrdersByStoreId(int storeId);

    List<Role> findRolesByUserId(int userId);

    List<Privilege> findPrivilegesByRoleId(int roleId);

    List<User> findBusinesses(int roleId);
}
