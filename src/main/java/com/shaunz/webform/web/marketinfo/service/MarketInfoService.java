package com.shaunz.webform.web.marketinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.marketinfo.dao.MarketInfoMapper;
import com.shaunz.webform.web.marketinfo.entity.MarketInfo;

@Service
public class MarketInfoService extends BaseService{
	@Autowired
	MarketInfoMapper marketInfoMapper;
	
	public List<MarketInfo> quaryAll(){
		return marketInfoMapper.quaryAll();
	}
	
	public List<MarketInfo> queryList(){
		return marketInfoMapper.queryList();
	}

	public MarketInfo selectByPrimaryKey(String id) {
		return marketInfoMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(MarketInfo marketInfo) {
		return marketInfoMapper.insertSelective(marketInfo) == 1;
	}

	public boolean updateByPrimaryKeySelective(MarketInfo marketInfo) {
		return marketInfoMapper.updateByPrimaryKeySelective(marketInfo) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return marketInfoMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean close(MarketInfo marketInfo){
		marketInfo.setCloseFlg("Y");
		return updateByPrimaryKeySelective(marketInfo);
	}
}
