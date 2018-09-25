package com.shaunz.framework.authority.role.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaunz.framework.authority.function.entity.Function;
import com.shaunz.framework.authority.role.dao.RoleMapper;
import com.shaunz.framework.authority.role.entity.Role;
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.web.base.BaseService;

@Service
public class RoleService extends BaseService{
	
	@Autowired
	RoleMapper roleMapper;
	
	public List<Role> getRolesByUsrId(String usrId) {
		return roleMapper.selectByUserId(usrId);
	}
	
	public List<Role> queryList(Role role){
		return roleMapper.queryList(role);
	}
	
	public Role selectByPrimaryKey(String id){
		return roleMapper.selectByPrimaryKey(id);
	}
	
	public boolean insertSelective(Role record){
		return roleMapper.insertSelective(record) == 1;
	}
	
	public boolean updateByPrimaryKeySelective(Role record){
		return roleMapper.updateByPrimaryKeySelective(record) == 1;
	}
	
	public boolean deleteByPrimaryKey(String id){
		return roleMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closeRole(Role role){
		role.setCloseFlg("Y");
		return updateByPrimaryKeySelective(role);
	}
	
	public List<Map<String, Object>> getAuthorityBy(String roleId){
		return roleMapper.getAuthorityBy(roleId);
	}
	
	@Transactional
	public boolean authorityGrant(String roleId,List<Function> authorizedFunctions){
		boolean flag = false;
		List<Map<String, Object>> mapFromPage = generateRoleFunctionAuthorityMapBaseOn(roleId,authorizedFunctions);
		List<Map<String, Object>> oldRoleFunctionAuthorityMap = roleMapper.getAuthorityBy(roleId);
		List<Map<String, Object>> newRoleFunctionAuthorityMap = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < mapFromPage.size(); i++) {
			Map<String, Object> roleFunctionAuthorityMap = null;
			for (int j = 0; j < oldRoleFunctionAuthorityMap.size(); j++) {
				roleFunctionAuthorityMap = oldRoleFunctionAuthorityMap.get(j);
				if(((String)mapFromPage.get(i).get("function_id")).equals((String)roleFunctionAuthorityMap.get("function_id"))
						&&((String)mapFromPage.get(i).get("authority_id")).equals((String)roleFunctionAuthorityMap.get("authority_id"))){
					newRoleFunctionAuthorityMap.add(roleFunctionAuthorityMap);
					break;
				}
				roleFunctionAuthorityMap = null;
			}
			if(roleFunctionAuthorityMap == null){
				newRoleFunctionAuthorityMap.add(mapFromPage.get(i));
			}
		}
		
		int deletedRows = 0;
		if(!IArrayListUtil.isBlankList(oldRoleFunctionAuthorityMap)){
			deletedRows = roleMapper.deleteRoleFunctionAuthorityMapBy(roleId);
		}
		int insertedRows = 0;
		for (int i = 0; i < newRoleFunctionAuthorityMap.size(); i++) {
			insertedRows += roleMapper.insertRoleFunctionAuthorityMapSelective(newRoleFunctionAuthorityMap.get(i));
		}
		if((deletedRows == (oldRoleFunctionAuthorityMap==null?0:oldRoleFunctionAuthorityMap.size()))
				&&(insertedRows == newRoleFunctionAuthorityMap.size()))
			flag = true;
		return flag;
	}
	
	private List<Map<String, Object>> generateRoleFunctionAuthorityMapBaseOn(String roleId,List<Function> authorizedFunctions){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Function function = null;
		List<String> authories = null;
		for (int i = 0; i < authorizedFunctions.size(); i++) {
			function = authorizedFunctions.get(i);
			authories = function.getGrantedAuthority();
			for (int j = 0; j < authories.size(); j++) {
				Map<String, Object> record = new HashMap<String, Object>();
				record.put("role_id", roleId);
				record.put("function_id", function.getId());
				record.put("authority_id", authories.get(j));
				result.add(record);
			}
		}
		return result;
	}
}
