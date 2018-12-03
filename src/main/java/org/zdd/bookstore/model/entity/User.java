package org.zdd.bookstore.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    /**
     * 邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "location")
    private String location;

    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 身份
     */
    @Column(name = "identity")
    private String identity;

    @Column(name = "active")
    private String active;

    /**
     * 用户激活码
     */
    @Column(name = "code")
    private String code;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "created")
    private Date created;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取邮政编码
     *
     * @return zip_code - 邮政编码
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置邮政编码
     *
     * @param zipCode 邮政编码
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * @return detail_address
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * @param detailAddress
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * 获取身份
     *
     * @return identity - 身份
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置身份
     *
     * @param identity 身份
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * @return active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active
     */
    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    /**
     * 获取用户激活码
     *
     * @return code - 用户激活码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置用户激活码
     *
     * @param code 用户激活码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
}