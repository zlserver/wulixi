package com.cnu.wlx.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnu.wlx.bean.News;
import com.cnu.wlx.dao.NewsDao;
import com.cnu.wlx.dao.base.DaoSupport;

@Repository("newsDao")
public class NewsDaoImpl extends DaoSupport<News>implements NewsDao {


}
