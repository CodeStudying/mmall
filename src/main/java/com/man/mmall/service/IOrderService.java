package com.man.mmall.service;

import com.man.mmall.common.ServerResponse;

import java.util.Map;

public interface IOrderService  {
    ServerResponse createOrder(Integer id, Integer shippingId);

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> parms);

    ServerResponse queryOrderPayStatus(Integer id, Long orderNo);
}
