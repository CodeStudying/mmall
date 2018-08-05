package com.man.mmall.service;

import com.github.pagehelper.PageInfo;
import com.man.mmall.common.ServerResponse;
import com.man.mmall.pojo.Shipping;

public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse del(Integer id, Integer shippingId);

    ServerResponse update(Integer id, Shipping shipping);

    ServerResponse<Shipping> select(Integer id, Integer shippingId);

    ServerResponse<PageInfo> list(Integer id, int pageNum, int pageSize);
}
