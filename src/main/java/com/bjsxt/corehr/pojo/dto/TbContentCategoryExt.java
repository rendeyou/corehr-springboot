package com.bjsxt.corehr.pojo.dto;

import com.bjsxt.corehr.pojo.TbContentCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: TbContentCategoryExt
 * @description: 内容类别的树查询实体类
 * @author: RenDeYou
 * @date: 2021/4/15 21:16
 */
@ApiModel(value = "TbContentCategoryExt", description = "内容类别的树查询实体类")
@Data
@NoArgsConstructor
public class TbContentCategoryExt extends TbContentCategory {
    private List<TbContentCategoryExt> children;
}
