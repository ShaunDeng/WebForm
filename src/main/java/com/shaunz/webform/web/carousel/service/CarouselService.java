package com.shaunz.webform.web.carousel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.carousel.dao.CarouselMapper;
import com.shaunz.webform.web.carousel.entity.Carousel;

@Service
public class CarouselService extends BaseService{

	@Autowired
	CarouselMapper carouselMapper;
	
	public List<Carousel> quaryAll(){
		return carouselMapper.quaryAll();
	}
	
	public List<Carousel> queryList(){
		return carouselMapper.queryList();
	}

	public Carousel selectByPrimaryKey(String id) {
		return carouselMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Carousel carousel) {
		return carouselMapper.insertSelective(carousel) == 1;
	}

	public boolean updateByPrimaryKeySelective(Carousel carousel) {
		return carouselMapper.updateByPrimaryKeySelective(carousel) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return carouselMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean close(Carousel carousel){
		carousel.setCloseFlg("Y");
		return updateByPrimaryKeySelective(carousel);
	}


}
