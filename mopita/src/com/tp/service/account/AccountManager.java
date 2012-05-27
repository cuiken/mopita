package com.tp.service.account;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tp.dao.account.GroupDao;
import com.tp.dao.account.UserDao;
import com.tp.entity.account.Group;
import com.tp.entity.account.User;
import com.tp.service.ServiceException;

@Component
@Transactional(readOnly = true)
public class AccountManager {

	private static Logger logger = LoggerFactory.getLogger(AccountManager.class);
	private UserDao userDao;
	private GroupDao groupDao;
	private ShiroDbRealm shiroRealm;

	//-- User Manager --//
	public User getUser(Long id) {
		return userDao.get(id);
	}

	@Transactional(readOnly = false)
	public void saveUser(User entity) {
		userDao.save(entity);
		shiroRealm.clearCachedAuthorizationInfo(entity.getLoginName());
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", SecurityUtils.getSubject().getPrincipal());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.delete(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	public List<User> getAllUser() {
		return userDao.getAll();
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findUniqueBy("loginName", loginName);
	}

	@Transactional(readOnly = true)
	public boolean isLoginNameUnique(String newLoginName, String oldLoginName) {
		return userDao.isPropertyUnique("loginName", newLoginName, oldLoginName);
	}

	//-- Group Manager --//
	public Group getGroup(Long id) {
		return groupDao.get(id);
	}

	public List<Group> getAllGroup() {
		return groupDao.getAll();
	}

	@Transactional(readOnly = false)
	public void saveGroup(Group entity) {
		groupDao.save(entity);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteGroup(Long id) {
		groupDao.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Autowired(required = false)
	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}
}
