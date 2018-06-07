package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-07 9:54
 **/
public interface IShippingService {
    public ServerResponse add(Integer userId, Shipping shipping);
    public ServerResponse del(Integer userId,Integer shippingId);
    public ServerResponse update(Integer userId, Shipping shipping);
    public ServerResponse<Shipping> list(Integer userId,Integer shippingId);
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
