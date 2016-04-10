package com.cnu.wlx.dao.base;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.utils.GenericsUtils;


/**
* @author 周亮 
* @version 创建时间：2015年11月16日 下午10:39:54
* 抽象类说明 操作数据库的类，包含的功能有：
* 1.获取T类的的记录总数
* 2.保存实体
* 3.更新实体
* 4.根据id属性查找实体
* 5.根据id属性删除实体
* 6.根据实体属性来查询实体
* 7.根据条件分页查询实体
 * @param <T> 数据库中表对应的类
 */
@SuppressWarnings("unchecked")
@Transactional
public abstract class DaoSupport<T>   implements Dao<T>{
	//利用反射技术得到T对象实例
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	//@PersistenceContext protected EntityManager em;
	protected HibernateTemplate  ht;

	public HibernateTemplate getHt() {
		return ht;
	}
	@Resource(name="hibernateTemplate")
	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	public void delete(Serializable...  entityids) {
		for(Serializable id : entityids)	
		{
			T clazz = ht.get(this.entityClass, id );
			if(clazz != null)
			 ht.delete(ht.get(this.entityClass, id));
		}
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId==null) 
			throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		return  ht.get(this.entityClass, entityId);
	}

	public T find(final String wherejpql,final Object value) {
		// TODO Auto-generated method stub
		String entityname = getEntityName(DaoSupport.this.entityClass);
		List<T> list =ht.find("select o from "+ entityname+ " o  where "+ wherejpql, value);
		
		if(list.size()==1)
			return list.get(0);
		return null;
	}
	
	public void save(T entity) {
			try {
				ht.persist(entity);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}catch(Exception e){
				throw new RuntimeException("账号一存在!");
			}
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount() {
		
		 return (Long) ht.execute(new HibernateCallback<Object>() {
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				return (Long) session.createQuery("select count("+ getCountField(DaoSupport.this.entityClass) +") from "+ getEntityName(DaoSupport.this.entityClass)+ " o").uniqueResult();
				
			}
		});
	}
	
	public void update(T entity) {
		ht.merge(entity);
	}

	/**
	 * 分页查询并根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstindex,maxresult,null,null,orderby);
	}
	
	/**
	 * 根据条件分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getScrollData(firstindex,maxresult,wherejpql,queryParams,null);
	}
	/**
	 * 分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) {
		return getScrollData(firstindex,maxresult,null,null,null);
	}


	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(final int firstindex,final  int maxresult
			,final  String wherejpql,final  Object[] queryParams,final LinkedHashMap<String,String> orderby) {
		
		
		QueryResult<T> qr = ht.execute(new HibernateCallback<QueryResult<T>>() {
			public QueryResult<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				QueryResult<T> q = new QueryResult<T>();
				
				String entityname = getEntityName(DaoSupport.this.entityClass);
				Query query = session.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
				//Query query = session.createQuery("select o from info o inner join o.buyerChat chat where chat.chatid=? "+ buildOrderby(orderby));
				setQueryParams(query, queryParams);
				
				
				if(firstindex!=-1 && maxresult!=-1) 
				query.setFirstResult(firstindex).setMaxResults(maxresult);
				List list = query.list();
				q.setResultlist(list);
				query = session.createQuery("select count("+ getCountField(DaoSupport.this.entityClass)+ ") from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql));
				setQueryParams(query, queryParams);
				q.setTotalrecord((Long)query.uniqueResult());
				
				return q;
			}
		});
		
		return qr;
	}
	/**
	 * 根据条件查询，结果根据条件排序
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<T> getAllData(final  String wherejpql,final  Object[] queryParams,final LinkedHashMap<String,String> orderby) {
		
		List<T> qr = ht.execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String entityname = getEntityName(DaoSupport.this.entityClass);
				Query query = session.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
				setQueryParams(query, queryParams);
				List<T> list = query.list();
				return list;
			}
		});
		return qr;
	}
	/**
	 * 根据条件查询所有数据
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<T> getAllData(String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return getAllData(wherejpql, queryParams, null);
	}
	
	/**
	 * 查询所有数据
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<T> getAllData() {
		// TODO Auto-generated method stub
		return getAllData(null, null, null);
	}
	/**
	 * 设置查询参数
	 * @param query 查询对象
	 * @param params 参数值
	 */
	protected static void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i, queryParams[i]);
			}
		}
	}
	/**
	 * 构建排序语句
	 * @param orderby 排序属性与asc/desc, Key为属性,Value为asc/desc
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	/**
	 * 获取实体的名称
	 * @param <E>泛型方法声明
	 * @param clazz 实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz){
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}
	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx o语句BUG而增加,hibernate对此jpql解析后的sql为select count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * @param <E>泛型方法声明
	 * @param clazz
	 * @return
	 */
	protected static <E> String getCountField(Class<E> clazz){
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors){
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){					
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return out;
	}
}
