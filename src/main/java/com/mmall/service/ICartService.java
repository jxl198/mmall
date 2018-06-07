package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-05 17:52
 **/
public interface ICartService    {
    public ServerResponse<CartVo> add(Integer userId, Integer count, Integer productId);
    public ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);
    public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);
    public ServerResponse<CartVo> list(Integer userId);
    public ServerResponse<CartVo> selectOrUnselect(Integer userId,Integer checked,Integer productId);
    public ServerResponse<Integer> getCartProductCount(Integer userId);
}
