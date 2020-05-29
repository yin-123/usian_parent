package com.usian.feign;

import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utils.AdNode;
import com.usian.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "usian-content-service")
public interface ContentServiceFeign {

    @PostMapping("/service/contentCategory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("parentId") 
                                                            			Long parentId);

    @PostMapping("/service/contentCategory/insertContentCategory")
    Integer insertContentCategory(TbContentCategory tbContentCategory);

    @PostMapping("/service/contentCategory/deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/contentCategory/updateContentCategory")
    Integer updateContentCategory(TbContentCategory tbContentCategory);

    @RequestMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam("page") Integer page,
                                              @RequestParam("rows") Integer rows, @RequestParam("categoryId") Long categoryId);

    @PostMapping("/service/content/insertTbContent")
    Integer insertTbContent(TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam("id") Long id);

    @RequestMapping("/service/content/selectFrontendContentByAD")
    List<AdNode> selectFrontendContentByAD();
}