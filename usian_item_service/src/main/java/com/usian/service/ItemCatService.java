package com.usian.service;

import com.usian.pojo.TbItemCat;
import com.usian.utils.CatNode;
import com.usian.utils.CatResult;

import java.util.List;

public interface ItemCatService {
    List<TbItemCat> selectItemCategoryByParentId(Long id);

    CatResult selectItemCategoryAll();
}
