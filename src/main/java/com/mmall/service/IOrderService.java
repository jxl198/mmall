package com.mmall.service;

        import com.github.pagehelper.PageInfo;
        import com.mmall.common.ServerResponse;
        import com.mmall.vo.OrderVo;

        import java.util.Map;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-11 15:44
 **/
public interface IOrderService {
    public ServerResponse pay(Long orderNo, Integer userId, String path);
    public ServerResponse alipayCallback(Map<String,String> params);
    public ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
    public ServerResponse createOrder(Integer userId, Integer shippingId);
    public ServerResponse<String> cancelOrder(Integer userId, Long orderNo);
    public ServerResponse getOrderCartProduct(Integer userId);
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);
    ServerResponse<PageInfo> manageList(int pageNum,int pageSize);
    ServerResponse<OrderVo> manageDetail(Long orderNo);
    ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize);
    ServerResponse<String> manageSendGoods(Long orderNo);
}
