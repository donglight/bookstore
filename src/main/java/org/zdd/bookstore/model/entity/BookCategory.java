package org.zdd.bookstore.model.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "book_category")
public class BookCategory {
    @Id
    @Column(name = "cate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cateId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_parent")
    private Boolean isParent;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    /**
     * @return cate_id
     */
    public Integer getCateId() {
        return cateId;
    }

    /**
     * @param cateId
     */
    public void setCateId(Integer cateId) {
        this.cateId = cateId;
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
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sort_order
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return is_parent
     */
    public Boolean getIsParent() {
        return isParent;
    }

    /**
     * @param isParent
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
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