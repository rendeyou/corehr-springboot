package com.bjsxt.corehr.service;


import com.bjsxt.corehr.pojo.dto.TbContentCategoryExt;

import java.util.List;

/**
 * @className: ContentCategoryService
 * @description: 内容类别接口
 * @author: RenDeYou
 * @date: 2021/4/21 17:29
 */
public interface ContentCategoryService {

    List<TbContentCategoryExt> selectContentCategoryTree();

}
