package org.zdd.bookstore.model.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "privilege")
public class Privilege {
    @Id
    @Column(name = "priv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer privId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "url")
    private String url;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "is_parent")
    private Integer isParent;


    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    /**
     * @return priv_id
     */
    public Integer getPrivId() {
        return privId;
    }

    /**
     * @param privId
     */
    public void setPrivId(Integer privId) {
        this.privId = privId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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