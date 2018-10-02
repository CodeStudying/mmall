package com.man.mmall.service;

import com.github.pagehelper.PageInfo;
import com.man.mmall.common.ServerResponse;
import com.man.mmall.vo.OrderVo;

import java.util.Map;

public interface IOrderService  {
    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> parms);

    ServerResponse queryOrderPayStatus(Integer id, Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse cancel(Integer userId, Long orderNo);

    ServerResponse getOrderList(Integer userId, int pageNum, int pageSize);

    ServerResponse getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSendGoods(Long orderNo);

    void closeOrder(int hour);
}
