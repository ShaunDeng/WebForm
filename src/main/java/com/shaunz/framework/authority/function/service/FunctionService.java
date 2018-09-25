package com.shaunz.framework.authority.function.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.authority.function.dao.FunctionMapper;
import com.shaunz.framework.authority.function.entity.Function;
import com.shaunz.framework.common.treemenu.BootStrapTreeViewNode;
import com.shaunz.framework.common.treemenu.TreeMenu;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.web.base.BaseService;

@Service
public class FunctionService extends BaseService{
	@Autowired
	private FunctionMapper functionMapper;
	
	public List<Function> queryAllFunctions(){
		return functionMapper.queryAll();
	}
	
	public List<Function> queryAllAuthorizedFunctionByUsrId(String usrId){
		return functionMapper.queryAllAuthorizedFunctionByUsrId(usrId);
	}
	
	public TreeMenu generateMngmtTree(List<Function> functions){
		List<BootStrapTreeViewNode> treeNodes = TreeMenu.changeFunctionLstToTreeNodes(functions);
		TreeMenu treeMenu = new TreeMenu(treeNodes);
		if(treeMenu.isValidTree()){
			treeMenu.setShowBorder(false);
			treeMenu.setBackColor("#F5F5F5");
			return treeMenu;
		}
		return null;
	}
	
	public Function findFunctionbyId(String id){
		return functionMapper.selectByPrimaryKey(id);
	}
	
	public Map<String,Object> findObjectDetailBy(String functionId,String objId){
		Function function = findFunctionbyId(functionId);
		String tableNm = function.getTableNm();
		Map<String,Object> result = null;
		if(IStringUtil.notBlank(tableNm)){
			result= functionMapper.findObjBy(tableNm, objId);
			result.remove("close_flg");
			result.remove("close_flg".toUpperCase());
		}
		return result;
	}
	
	public List<Map<String, Object>> queryObjLstby(String functionId){
		Function function = findFunctionbyId(functionId);
		String tableNm = function.getTableNm();
		List<Map<String, Object>> result = null;
		if(IStringUtil.notBlank(tableNm)){
			result = functionMapper.queryObjLstBy(tableNm);
		}
		return result;
	}

}
