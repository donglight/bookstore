package org.zdd.bookstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "global_parameter")
public class GlobalParameter {
    @Id
    @Column(name = "para_id")
    private Integer paraId;

    @Column(name = "web_name")
    private String webName;

    /**
     * 注册条款
     */
    @Column(name = "reg_clause")
    private String regClause;

    /**
     * 公告
     */
    @Column(name = "notice")
    private String notice;

    @Column(name = "address")
    private String address;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "copyright")
    private String copyright;

    @Column(name = "weblogo")
    private String weblogo;

    @Column(name = "afford_method")
    private String affordMethod;

    @Column(name = "shops_stream")
    private String shopsStream;

    /**
     * 送货方式
     */
    @Column(name = "post_method")
    private String postMethod;

    @Column(name = "post_price")
    private BigDecimal postPrice;

    /**
     * 运输说明
     */
    @Column(name = "post_desc")
    private String postDesc;

    @Column(name = "work_time")
    private String workTime;

    @Column(name = "after_service")
    private String afterService;

    @Column(name = "law")
    private String law;

    /**
     * 常见问题
     */
    @Column(name = "commques")
    private String commques;

    /**
     * 交易条款
     */
    @Column(name = "deal_rule")
    private String dealRule;

    /**
     * @return para_id
     */
    public Integer getParaId() {
        return paraId;
    }

    /**
     * @param paraId
     */
    public void setParaId(Integer paraId) {
        this.paraId = paraId;
    }

    /**
     * @return web_name
     */
    public String getWebName() {
        return webName;
    }

    /**
     * @param webName
     */
    public void setWebName(String webName) {
        this.webName = webName == null ? null : webName.trim();
    }

    /**
     * 获取注册条款
     *
     * @return reg_clause - 注册条款
     */
    public String getRegClause() {
        return regClause;
    }

    /**
     * 设置注册条款
     *
     * @param regClause 注册条款
     */
    public void setRegClause(String regClause) {
        this.regClause = regClause == null ? null : regClause.trim();
    }

    /**
     * 获取公告
     *
     * @return notice - 公告
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 设置公告
     *
     * @param notice 公告
     */
    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * @param copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    /**
     * @return weblogo
     */
    public String getWeblogo() {
        return weblogo;
    }

    /**
     * @param weblogo
     */
    public void setWeblogo(String weblogo) {
        this.weblogo = weblogo == null ? null : weblogo.trim();
    }

    /**
     * @return afford_method
     */
    public String getAffordMethod() {
        return affordMethod;
    }

    /**
     * @param affordMethod
     */
    public void setAffordMethod(String affordMethod) {
        this.affordMethod = affordMethod == null ? null : affordMethod.trim();
    }

    /**
     * @return shops_stream
     */
    public String getShopsStream() {
        return shopsStream;
    }

    /**
     * @param shopsStream
     */
    public void setShopsStream(String shopsStream) {
        this.shopsStream = shopsStream == null ? null : shopsStream.trim();
    }

    /**
     * 获取送货方式
     *
     * @return post_method - 送货方式
     */
    public String getPostMethod() {
        return postMethod;
    }

    /**
     * 设置送货方式
     *
     * @param postMethod 送货方式
     */
    public void setPostMethod(String postMethod) {
        this.postMethod = postMethod == null ? null : postMethod.trim();
    }

    /**
     * @return post_price
     */
    public BigDecimal getPostPrice() {
        return postPrice;
    }

    /**
     * @param postPrice
     */
    public void setPostPrice(BigDecimal postPrice) {
        this.postPrice = postPrice;
    }

    /**
     * 获取运输说明
     *
     * @return post_desc - 运输说明
     */
    public String getPostDesc() {
        return postDesc;
    }

    /**
     * 设置运输说明
     *
     * @param postDesc 运输说明
     */
    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc == null ? null : postDesc.trim();
    }

    /**
     * @return work_time
     */
    public String getWorkTime() {
        return workTime;
    }

    /**
     * @param workTime
     */
    public void setWorkTime(String workTime) {
        this.workTime = workTime == null ? null : workTime.trim();
    }

    /**
     * @return after_service
     */
    public String getAfterService() {
        return afterService;
    }

    /**
     * @param afterService
     */
    public void setAfterService(String afterService) {
        this.afterService = afterService == null ? null : afterService.trim();
    }

    /**
     * @return law
     */
    public String getLaw() {
        return law;
    }

    /**
     * @param law
     */
    public void setLaw(String law) {
        this.law = law == null ? null : law.trim();
    }

    /**
     * 获取常见问题
     *
     * @return commques - 常见问题
     */
    public String getCommques() {
        return commques;
    }

    /**
     * 设置常见问题
     *
     * @param commques 常见问题
     */
    public void setCommques(String commques) {
        this.commques = commques == null ? null : commques.trim();
    }

    /**
     * 获取交易条款
     *
     * @return deal_rule - 交易条款
     */
    public String getDealRule() {
        return dealRule;
    }

    /**
     * 设置交易条款
     *
     * @param dealRule 交易条款
     */
    public void setDealRule(String dealRule) {
        this.dealRule = dealRule == null ? null : dealRule.trim();
    }
}