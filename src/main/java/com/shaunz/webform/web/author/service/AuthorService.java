package com.shaunz.webform.web.author.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.author.dao.AuthorMapper;
import com.shaunz.webform.web.author.entity.Author;

@Service
public class AuthorService extends BaseService{
	@Autowired
	AuthorMapper authorMapper;
	
	public List<Author> queryList(){
		return authorMapper.queryList();
	}

	public Author selectByPrimaryKey(String id) {
		return authorMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Author author) {
		return authorMapper.insertSelective(author) == 1;
	}

	public boolean updateByPrimaryKeySelective(Author author) {
		return authorMapper.updateByPrimaryKeySelective(author) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return authorMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closeAuthor(Author author){
		author.setCloseFlg("Y");
		return updateByPrimaryKeySelective(author);
	}
}
