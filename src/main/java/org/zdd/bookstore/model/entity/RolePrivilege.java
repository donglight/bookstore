package org.zdd.bookstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "role_privilege")
public class RolePrivilege {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "privilege_id")
    private Integer privilegeId;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return privilege_id
     */
    public Integer getPrivilegeId() {
        return privilegeId;
    }

    /**
     * @param privilegeId
     */
    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}