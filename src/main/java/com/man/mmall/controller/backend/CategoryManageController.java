package com.man.mmall.controller.backend;

import com.man.mmall.common.Const;
import com.man.mmall.common.ResponseCode;
import com.man.mmall.common.ServerResponse;
import com.man.mmall.pojo.User;
import com.man.mmall.service.ICategoryService;
import com.man.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manage/category/")
public class CategoryManageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("add_category.do")
    public ServerResponse addCategory(String categoryName,
                                      @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
//        }
//        //校验一下是否是管理员
//        if (userService.checkAdminRole(user).isSuccess()) {
//            //是管理员
//            //增加我们处理分类的逻辑
//            return categoryService.addCategory(categoryName, parentId);
//
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }

        return categoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping("set_category_name.do")
    public ServerResponse setCategoryName(Integer categoryId, String categoryName) {
        return categoryService.updateCategoryName(categoryId, categoryName);
    }

    @RequestMapping("get_category.do")
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        return categoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    public ServerResponse getCategoryAndDeepChildrenCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        return categoryService.selectCategoryAndChildrenById(categoryId);
    }
}
