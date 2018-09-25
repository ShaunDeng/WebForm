package com.shaunz.framework.shiro.realm;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shaunz.framework.authority.authority.service.AuthorityService;
import com.shaunz.framework.authority.role.entity.Role;
import com.shaunz.framework.authority.role.service.RoleService;
import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.authority.user.service.UserService;
import com.shaunz.framework.common.source.service.SourceService;
import com.shaunz.framework.common.utils.IStringUtil;

@Component
public class ShaunzRealm extends AuthorizingRealm{
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	SourceService sourceTableGenerator;
	
    public ShaunzRealm(CacheManager cacheManager) {
        super(cacheManager);
    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo autoInfo = null;
		String inputUsrNm = principals.getPrimaryPrincipal().toString();
		User user = userService.findUser(inputUsrNm);
		if(IStringUtil.notBlank(user.getId())){
			autoInfo = generateAuthorizationInfo(autoInfo,user);
		}
		return autoInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if(token == null || token.getPrincipal() == null)
			return null;
		String inputUsrNm = token.getPrincipal().toString();
		User user = userService.findUser(inputUsrNm);
		if(user == null){
			throw new UnknownAccountException();
		}
		if("lock".equals(user.getLockUp())){
			throw new LockedAccountException();
		}
		if("Y".equals(user.getCloseFlg())){
			throw new DisabledAccountException();
		}
		if(isAccountExpired(user)){
			throw new ExpiredCredentialsException();
		}
		if(isExcessiveAttemptsTime(user)){
			throw new ExcessiveAttemptsException();
		}
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(
				user.getLoginName(),
                user.getPassword(),
                getName() 
        );
		return authInfo;
	}
	
	private SimpleAuthorizationInfo generateAuthorizationInfo(SimpleAuthorizationInfo autoInfo,User user){
		autoInfo = new SimpleAuthorizationInfo();
		autoInfo.setRoles(findRolesByUsrId(user.getId()));
		autoInfo.setStringPermissions(findPermissionByUsrId(user.getId()));
		return autoInfo;
	}
	
	private Set<String> findRolesByUsrId(String usrId){
		Set<String> roleNmSet = new HashSet<String>();
		List<Role> roles = roleService.getRolesByUsrId(usrId);
		if(notEmptyLst(roles)){
			for (int i = 0; i < roles.size(); i++) {
				roleNmSet.add(roles.get(i).getName());
			}
		}
		return roleNmSet;
	}
	
	private Set<String> findPermissionByUsrId(String usrId){
		Set<String> permissionSet = new HashSet<String>();
		List<String> functionPermissionLst = 
				authorityService.findFunctionPermissionByUsrId(usrId);
		if(notEmptyLst(functionPermissionLst)){
			for(int i = 0; i < functionPermissionLst.size(); i++){
				permissionSet.add(functionPermissionLst.get(i));
			}
		}
		return permissionSet;
	}
	
	private boolean notEmptyLst(List<?> list){
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}
	
	private boolean isAccountExpired(User user){
		Date currentDate = new Date();
		if(user.getStartTime() == null && user.getEndTime() == null)
			return false;
		if(user.getStartTime() == null)
			user.setStartTime(currentDate);
		if(user.getEndTime() == null)
			user.setEndTime(currentDate);
		return !((currentDate.after(user.getStartTime()) || currentDate.equals(user.getStartTime())) 
				&& (currentDate.before(user.getEndTime()) ||  currentDate.equals(user.getStartTime())));
	}
	
	private boolean isExcessiveAttemptsTime(User user){
		if(IStringUtil.isBlank(user.getAttemptSignTimes())){
			return false;
		}
		String maxSignAttemptsTimeString = sourceTableGenerator.getSourceValueBy("System", "MaxSignAttemptsTime");
		if(IStringUtil.isBlank(maxSignAttemptsTimeString)){
			return false;
		}
		int maxSignAttemptsTime = Integer.valueOf(maxSignAttemptsTimeString);
		int attemptsTime = Integer.valueOf(user.getAttemptSignTimes());
		return attemptsTime >= maxSignAttemptsTime;
	}

}
