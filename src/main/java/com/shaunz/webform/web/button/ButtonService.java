package com.shaunz.webform.web.button;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.button.dao.ButtonMapper;
import com.shaunz.webform.web.button.entity.Button;

@Service
public class ButtonService extends BaseService{
	@Autowired
	ButtonMapper buttonMapper;
	
	public List<Button> queryList(){
		return buttonMapper.queryList();
	}

	public Button selectByPrimaryKey(String id) {
		return buttonMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Button button) {
		return buttonMapper.insertSelective(button) == 1;
	}

	public boolean updateByPrimaryKeySelective(Button button) {
		return buttonMapper.updateByPrimaryKeySelective(button) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return buttonMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean close(Button button){
		button.setCloseFlg("Y");
		return updateByPrimaryKeySelective(button);
	}

}
