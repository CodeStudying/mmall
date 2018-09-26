package com.man.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.man.mmall.common.ServerResponse;
import com.man.mmall.service.IOrderService;
import com.man.mmall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/manage/order/")
public class OrderManageController {


    @Autowired
    private IOrderService iOrderService;

    @GetMapping("list.do")
    public ServerResponse<PageInfo> orderList(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        User user = (User) redisTemplate.opsForValue().get(loginToken);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iOrderService.manageList(pageNum, pageSize);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iOrderService.manageList(pageNum, pageSize);
    }

    @RequestMapping("detail.do")
    public ServerResponse<OrderVo> orderDetail(Long orderNo) {
        return iOrderService.manageDetail(orderNo);
    }


    @RequestMapping("search.do")
    public ServerResponse<PageInfo> orderSearch(Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iOrderService.manageSearch(orderNo, pageNum, pageSize);
    }


    @RequestMapping("send_goods.do")
    public ServerResponse<String> orderSendGoods(Long orderNo) {
        return iOrderService.manageSendGoods(orderNo);
    }


}
