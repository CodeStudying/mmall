package com.man.mmall.service;

import com.man.mmall.common.ServerResponse;
import com.man.mmall.vo.CartVo;

public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> list (Integer userId);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, int checked);

    ServerResponse<CartVo> deleteProduct(Integer id, String productIds);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
