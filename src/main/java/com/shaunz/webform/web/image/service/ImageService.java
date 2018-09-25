package com.shaunz.webform.web.image.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.image.dao.ImageMapper;
import com.shaunz.webform.web.image.entity.Image;

@Service
public class ImageService extends BaseService{
	@Autowired
	ImageMapper imageMapper;
	
	public List<Image> queryList(){
		return imageMapper.queryList();
	}

	public Image selectByPrimaryKey(String id) {
		return imageMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Image image) {
		return imageMapper.insertSelective(image) == 1;
	}

	public boolean updateByPrimaryKeySelective(Image image) {
		return imageMapper.updateByPrimaryKeySelective(image) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return imageMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean close(Image image){
		image.setCloseFlg("Y");
		return updateByPrimaryKeySelective(image);
	}
}
