package org.zdd.bookstore.model.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "store")
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    @Column(name = "store_manager_id")
    private Integer storeManagerId;

    @Column(name = "store_phone_num")
    private String storePhoneNum;

    @Column(name = "store_telephone")
    private String storeTelephone;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_position")
    private String storePosition;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Transient
    private String storeManagerName;

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    /**
     * @return store_id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * @param storeId
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * @return store_manager_id
     */
    public Integer getStoreManagerId() {
        return storeManagerId;
    }

    /**
     * @param storeManagerId
     */
    public void setStoreManagerId(Integer storeManagerId) {
        this.storeManagerId = storeManagerId;
    }

    /**
     * @return store_phone_num
     */
    public String getStorePhoneNum() {
        return storePhoneNum;
    }

    /**
     * @param storePhoneNum
     */
    public void setStorePhoneNum(String storePhoneNum) {
        this.storePhoneNum = storePhoneNum == null ? null : storePhoneNum.trim();
    }

    /**
     * @return store_telephone
     */
    public String getStoreTelephone() {
        return storeTelephone;
    }

    /**
     * @param storeTelephone
     */
    public void setStoreTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone == null ? null : storeTelephone.trim();
    }

    /**
     * @return store_name
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    /**
     * @return store_position
     */
    public String getStorePosition() {
        return storePosition;
    }

    /**
     * @param storePosition
     */
    public void setStorePosition(String storePosition) {
        this.storePosition = storePosition == null ? null : storePosition.trim();
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