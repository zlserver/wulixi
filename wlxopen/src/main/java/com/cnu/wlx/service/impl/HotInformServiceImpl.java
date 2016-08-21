package com.cnu.wlx.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.HotInform;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.HotInformDao;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.HotInformService;

@Service("hotInformService")
public class HotInformServiceImpl implements HotInformService {

	private HotInformDao hotInformDao;
	@Override
	public void save(HotInform inform) {
		// TODO Auto-generated method stub
		hotInformDao.save(inform);
	}

	@Override
	public void update(HotInform inform) {
		// TODO Auto-generated method stub
		hotInformDao.update(inform);
	}

	@Override
	public QueryResult<HotInform> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return hotInformDao.getScrollData(firstindex, maxresult, wherejpql, queryParams,orderby);
	}

	@Override
	public HotInform getUniqueHot() {
		// TODO Auto-generated method stub
		LinkedHashMap< String, String> orderby = new LinkedHashMap<>();
		
		orderby.put("createTime", "desc");
		
		List<HotInform> list= hotInformDao.getAllData("o.state= ?", new Object[]{StateEnum.YES}, orderby);
		if( list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	public HotInformDao getHotInformDao() {
		return hotInformDao;
	}
	@Autowired
	public void setHotInformDao(HotInformDao hotInformDao) {
		this.hotInformDao = hotInformDao;
	}

	@Override
	public void delete(Serializable... id) {
		// TODO Auto-generated method stub
		hotInformDao.delete(id);
	}

	@Override
	public HotInform find(String id) {
		// TODO Auto-generated method stub
		return hotInformDao.find(id);
	}

}
