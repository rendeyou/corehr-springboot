package com.bjsxt.corehr.service.impl;


import com.bjsxt.corehr.mapper.TbContentCategoryMapper;
import com.bjsxt.corehr.pojo.TbContentCategory;
import com.bjsxt.corehr.pojo.TbContentCategoryExample;
import com.bjsxt.corehr.pojo.dto.TbContentCategoryExt;
import com.bjsxt.corehr.service.ContentCategoryService;
import com.bjsxt.corehr.utils.ListConvertUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className: ContentCategoryServiceImpl
 * @description: 内容类别实现
 * @author: RenDeYou
 * @date: 2021/4/21 17:29
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    /**
     * 内容类别的树查询
     */
    @Override
    public List<TbContentCategoryExt> selectContentCategoryTree() {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        List<TbContentCategory> tbContentCategories = this.tbContentCategoryMapper.selectByExample(example);
        //集合转换
        List<TbContentCategoryExt> tbContentCategoryExts = ListConvertUtil.listConvert(tbContentCategories, TbContentCategoryExt.class);
        //根节点rootIds组装一级树
        List<TbContentCategoryExt> rootTree = this.assembleRootTree(tbContentCategoryExts);
        return rootTree;
    }

    /**
     * 根节点rootIds组装一级树
     */
    private List<TbContentCategoryExt> assembleRootTree(List<TbContentCategoryExt> tbContentCategoryExts) {
        if (CollectionUtils.isEmpty(tbContentCategoryExts)) {
            return Collections.emptyList();
        }
        //查找根节点rootIds
        List<TbContentCategoryExt> rootList = Lists.newArrayList();
        HashSet<Object> rootIds = Sets.newHashSet();
        HashSet<Object> parentIds = Sets.newHashSet();
        HashSet<Object> ids = Sets.newHashSet();
        Map<Long, Long> idMap = Maps.newHashMap();
        tbContentCategoryExts.stream().forEach(e -> idMap.put(e.getId(), e.getParentId()));
        idMap.forEach((k, v) -> {
            ids.add(k);
            parentIds.add(v);
        });
        parentIds.forEach(e -> {
            if (!ids.contains(e)) {
                rootIds.add(e);
            }
        });
        //根据父节点分组parentIds
        Map<Long, List<TbContentCategoryExt>> rootMap = tbContentCategoryExts.stream().collect(Collectors.groupingBy(TbContentCategoryExt::getParentId));
        //根节点rootIds组装一级树
        rootIds.forEach(e -> {
            List<TbContentCategoryExt> rootTree = rootMap.get(e);
            //递归组装下级树
            this.assembleChildrenTree(rootTree, rootMap);
            rootList.addAll(rootTree);
        });
        return rootList;
    }

    /**
     * 递归组装下级树
     */
    private void assembleChildrenTree(List<TbContentCategoryExt> rootTree, Map<Long, List<TbContentCategoryExt>> rootMap) {
        if (CollectionUtils.isEmpty(rootTree)) {
            return;
        }
        for (TbContentCategoryExt tbContentCategoryExt : rootTree) {
            List<TbContentCategoryExt> childrenTree = rootMap.get(tbContentCategoryExt.getId());
            tbContentCategoryExt.setChildren(childrenTree);
            //递归组装下级树
            this.assembleChildrenTree(childrenTree, rootMap);
        }
    }
}
