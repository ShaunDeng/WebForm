package com.shaunz.framework.authority.authority.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.authority.authority.dao.AuthorityMapper;
import com.shaunz.framework.web.base.BaseService;

@Service
public class AuthorityService extends BaseService{
	@Autowired
	AuthorityMapper authorityMapper;
	
	public List<String> findFunctionPermissionByUsrId(String usrId) {
		return authorityMapper.findFunctionPermissionByUsrId(usrId);
	}
}
