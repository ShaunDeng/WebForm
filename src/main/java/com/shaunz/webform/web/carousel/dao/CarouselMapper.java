package com.shaunz.webform.web.carousel.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.webform.web.carousel.entity.Carousel;

@Repository
public interface CarouselMapper {
    int deleteByPrimaryKey(String id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
    
    List<Carousel> quaryAll();
    
    List<Carousel> queryList();
}