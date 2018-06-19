package com.mmall.vo;

import com.mmall.pojo.OrderItem;
import com.mmall.util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jiangxl
 * @description
 * @date 2018-06-19 10:23
 **/
public class OrderProductVo {

    List<OrderItemVo> orderItemVoList;
    private BigDecimal productTotalPrice;
    private String imageHost;

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
