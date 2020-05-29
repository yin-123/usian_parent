package com.usian.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.mapper.TbItemCatMapper;
import com.usian.mapper.TbItemMapper;
import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemCatExample;
import com.usian.pojo.TbItemExample;
import com.usian.utils.CatNode;
import com.usian.utils.CatResult;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<TbItemCat> selectItemCategoryByParentId(Long id) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andParentIdEqualTo(id);
		return tbItemCatMapper.selectByExample(tbItemCatExample);
	}

	@Override
	public CatResult selectItemCategoryAll() {
		CatResult catResult = new CatResult();
		//查询商品分类
		catResult.setData(getCatList(0L));
		return catResult;
	}

	private List getCatList(Long parentId){
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
		List resultList = new ArrayList();
		int count = 0;
		for(TbItemCat tbItemCat:list){
			//判断是否是父节点
			if(tbItemCat.getIsParent()){
				CatNode catNode = new CatNode();
				catNode.setName(tbItemCat.getName());
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count++;
				//只取商品分类中的 18 条数据
				if (count == 18){
					break;
				}
			}else{
				resultList.add(tbItemCat.getName());
			}
		}
		return resultList;
	}
}