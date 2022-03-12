package com.bjsxt.corehr.controller;

import com.bjsxt.corehr.pojo.dto.TbContentCategoryExt;
import com.bjsxt.corehr.service.ContentCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: ContentCategoryController
 * @description: 内容类别控制器
 * @author: RenDeYou
 * @date: 2021/4/21 17:30
 */
//@NotInSwagger //排除类：自定义注解，不显示在Swagger中
@Api(value = "ContentCategoryController", description = "内容类别控制器")
@RestController
@RequestMapping("/service/contentCategory")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * Postman测试
     * Body——>x-www-form-urlencoded
     * http://localhost:9100/service/contentCategory/selectContentCategoryTree
     */
    /**
     * @param
     * @return java.util.List<com.bjsxt.corehr.pojo.dto.bzshop.TbContentCategoryExt>
     * @description: 内容类别的树查询
     * @author: RenDeyou
     * @date: 2021/5/22 17:47
     */
    @ApiOperation(value = "selectContentCategoryTree", notes = "内容类别的树查询")
    @PostMapping("/selectContentCategoryTree")
    public List<TbContentCategoryExt> selectContentCategoryTree() {
        return this.contentCategoryService.selectContentCategoryTree();
    }
}
