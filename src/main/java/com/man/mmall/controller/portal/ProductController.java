package com.man.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.man.mmall.common.ServerResponse;
import com.man.mmall.service.IProductService;
import com.man.mmall.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/detail/{productId}")
    public ServerResponse<ProductDetailVo> detail(@PathVariable("productId") Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @GetMapping(value = "/list/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse<PageInfo> list(@PathVariable(value = "keyword")String keyword,
                                         @PathVariable(value = "categoryId")Integer categoryId,
                                         @PathVariable(value = "pageNum") Integer pageNum,
                                         @PathVariable(value = "pageSize") Integer pageSize,
                                         @PathVariable(value = "orderBy") String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        if(StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }
}
