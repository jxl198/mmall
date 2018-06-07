package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * @author jiangxl
 * @description
 * @date 2018-05-31 16:00
 **/
public interface ICategoryService {
    public ServerResponse addCategory(String categoryName, Integer parentId);
    public ServerResponse<String> updateCategoryName(Integer categoryId,String categoryName);
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    public ServerResponse<List<Integer>> selectCateogoryAndChildrenById(Integer categoryId);
}
