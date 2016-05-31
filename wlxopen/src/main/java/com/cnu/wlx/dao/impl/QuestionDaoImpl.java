package com.cnu.wlx.dao.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.QuestionDao;
import com.cnu.wlx.dao.base.DaoSupport;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 下午2:45:34
* 类说明
*/
@Repository("questionDao")
public class QuestionDaoImpl extends DaoSupport<Question>implements QuestionDao {

}
