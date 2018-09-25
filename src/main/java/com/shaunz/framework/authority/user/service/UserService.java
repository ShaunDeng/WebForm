package com.shaunz.framework.authority.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaunz.framework.authority.user.dao.UserMapper;
import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.web.base.BaseService;

@Service
public class UserService extends BaseService{
	
	@Autowired
	UserMapper userMapper;
	
	public User selectByPrimaryKey(String id){
		return userMapper.selectByPrimaryKey(id);
	}
	
	public User findUserByNm(String usrNm){
		return userMapper.selectByUserNm(usrNm);
	}
	
	public User findUserByEmail(String email){
		return userMapper.selectByEmail(email);
	}
	
	public User findUser(String identifier){
		User user = findUserByNm(identifier);
		if(user== null || IStringUtil.isBlank(user.getId())){
			user = findUserByEmail(identifier);
		}
		return user;
	}
	
	public List<User> queryAll(){
		return userMapper.queryAll();
	}
    
	public List<User> queryLst(User user){
		return userMapper.queryLst(user);
	}
	
	public boolean updateUserByPrimaryKeySelective(User user){
		return userMapper.updateByPrimaryKeySelective(user) == 1;
	}
	
	public boolean addNewUser(User user){
		return userMapper.insertSelective(user) == 1;
	}
	
	public boolean deleteUser(User user){
		return userMapper.deleteByPrimaryKey(IStringUtil.isBlank(user.getId())?"":user.getId()) == 1;
	}
	
	public boolean closeUser(User user){
		user.setCloseFlg("Y");
		return updateUserByPrimaryKeySelective(user);
	}
	
	@Transactional
	public boolean roleGrant(String[] roleIds,String userId){
		boolean flag = false;
		List<Map<String, Object>> oldUsrRoleMap = userMapper.getUsrRoleMapBy(userId);
		List<Map<String, Object>> newUsrRoleMap = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < roleIds.length; i++) {
			Map<String, Object> usrRoleMap = null;
			for (int j = 0; j < oldUsrRoleMap.size(); j++) {
				usrRoleMap = oldUsrRoleMap.get(j);
				if(roleIds[i].equals(usrRoleMap.get("role_id").toString())){
					newUsrRoleMap.add(usrRoleMap);
					break;
				}
				usrRoleMap = null;
			}
			if(usrRoleMap == null){
				usrRoleMap = new HashMap<String, Object>();
				usrRoleMap.put("user_id", userId);
				usrRoleMap.put("role_id", roleIds[i]);
				newUsrRoleMap.add(usrRoleMap);
			}
		}
		int deletedRows = 0;
		if(!IArrayListUtil.isBlankList(oldUsrRoleMap)){
			deletedRows = userMapper.deleteUsrRoleMapBy(userId);
		}
		int insertedRows = 0;
		for (int i = 0; i < newUsrRoleMap.size(); i++) {
			insertedRows += userMapper.insertUsrRoleMapSelective(newUsrRoleMap.get(i));
		}
		if((deletedRows == (oldUsrRoleMap==null?0:oldUsrRoleMap.size()))
				&&(insertedRows == newUsrRoleMap.size()))
			flag = true;
		return flag;
	}
}
