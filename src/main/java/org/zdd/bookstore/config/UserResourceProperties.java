package org.zdd.bookstore.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "user.default.info")
@PropertySource(value = "classpath:resource.properties", encoding = "utf-8")
/**
 * 配置用户的默认属性
 */
public class UserResourceProperties {

    private String active;

    private String isNotActive;

    private String location;

    private String detailAddress;

    private String ordinaryCustomer;

    private String businessCustomer;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(String isNotActive) {
        this.isNotActive = isNotActive;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getOrdinaryCustomer() {
        return ordinaryCustomer;
    }

    public void setOrdinaryCustomer(String ordinaryCustomer) {
        this.ordinaryCustomer = ordinaryCustomer;
    }

    public String getBusinessCustomer() {
        return businessCustomer;
    }

    public void setBusinessCustomer(String businessCustomer) {
        this.businessCustomer = businessCustomer;
    }
}
