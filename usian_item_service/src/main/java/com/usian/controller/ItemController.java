package com.usian.controller;

import com.usian.pojo.TbItem;
import com.usian.service.ItemService;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/service/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(Long itemId) {
        return itemService.selectItemInfo(itemId);
    }

    /**
     * 商品列表查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(Integer page, Integer rows){
        return this.itemService.selectTbItemAllByPage(page,rows);
    }

    /**
     * 商品的添加
     */
    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem,String desc,
                                String itemParams){
        return this.itemService.insertTbItem(tbItem,desc,itemParams);
    }

    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(Long itemId){
        return itemService.deleteItemById(itemId);
    }

    @RequestMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(@RequestParam Long itemId){
        return itemService.preUpdateItem(itemId);
    }

    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem){
        return this.itemService.updateTbItem(tbItem);
    }
}