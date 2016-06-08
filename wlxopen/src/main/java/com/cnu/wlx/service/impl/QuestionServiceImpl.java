package com.cnu.wlx.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.QuestionDao;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.QuestionService;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 下午2:50:37
* 类说明
*/
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	private QuestionDao questionDao;
	@Override
	public Question find(String id) {
		// TODO Auto-generated method stub
		return questionDao.find(id);
	}

	@Override
	public void delete(String... ids) {
		// TODO Auto-generated method stub
		questionDao.delete(ids);
	}

	@Override
	public void update(Question question) {
		// TODO Auto-generated method stub
		questionDao.update(question);
	}

	@Override
	public QueryResult<Question> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		
		return questionDao.getScrollData(firstindex, maxresult, wherejpql, queryParams,orderby);
	}

	@Override
	public QueryResult<Question> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return questionDao.getScrollData(firstindex, maxresult, wherejpql, queryParams);
	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}
	@Resource
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public void save(Question question) {
		// TODO Auto-generated method stub
		questionDao.save(question);
	}

	@Override
	public List<Question> getHomeData(int firstindex, int maxresult) {
		// TODO Auto-generated method stub
		//结果集根据栏目的顺序升序,时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("createTime", "desc");
		//可见
		String wherejpql=" o.visible= ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(StateEnum.YES);
		
		QueryResult<Question>  qr = questionDao.getScrollData(firstindex, maxresult,wherejpql,params.toArray());
		if( qr.getTotalrecord()>0)
			return qr.getResultlist();
		return null;
	}

}
