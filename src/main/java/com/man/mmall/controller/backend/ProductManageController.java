package com.man.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.man.mmall.common.Const;
import com.man.mmall.common.ResponseCode;
import com.man.mmall.common.ServerResponse;
import com.man.mmall.pojo.Product;
import com.man.mmall.pojo.User;
import com.man.mmall.service.IFileService;
import com.man.mmall.service.IProductService;
import com.man.mmall.service.IUserService;
import com.man.mmall.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/manage/product/")
public class ProductManageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IFileService iFileService;


    @RequestMapping("save.do")
    public ServerResponse productSave(Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @RequestMapping("set_sale_status.do")
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (userService.checkAdminRole(user).isSuccess()) {
//            return productService.setSaleStatus(productId, status);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return productService.setSaleStatus(productId, status);
    }

    @RequestMapping("detail.do")
    public ServerResponse getDetail(Integer productId) {
        return productService.manageProductDetail(productId);
    }

    @RequestMapping("list.do")
    public ServerResponse getList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return productService.getProductList(pageNum, pageSize);
    }

    @RequestMapping("search.do")
    public ServerResponse productSearch(String productName, Integer productId,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return productService.searchProduct(productName, productId, pageNum, pageSize);
    }

    @RequestMapping("upload.do")
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }
}
