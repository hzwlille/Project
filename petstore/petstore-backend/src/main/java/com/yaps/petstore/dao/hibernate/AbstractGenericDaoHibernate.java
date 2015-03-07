package com.yaps.petstore.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.yaps.petstore.dao.GenericDao;
import com.yaps.petstore.model.Category;


public class AbstractGenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK>  {

	protected Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
    @SuppressWarnings("unchecked")
	public AbstractGenericDaoHibernate() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public Class<T> getType() {
        return entityClass;
    }	
 
    
	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id) {
		Object obj = getSessionFactory().getCurrentSession().get(getType(),id);
        if (obj == null) {
            throw new ObjectRetrievalFailureException(Category.class, id);
        }
        
        return (T) obj; 
	}

	@Override
	public void saveOrUpdate(T obj) {
		 getSessionFactory().getCurrentSession().saveOrUpdate(obj);
	     getSessionFactory().getCurrentSession().flush();
		 
	}

	@Override
	public void delete(PK id) {
		getSessionFactory().getCurrentSession().delete(get(id));
        getSessionFactory().getCurrentSession().flush();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> lst = getSessionFactory().getCurrentSession().createQuery("from " + getType().getName()).list();
		return lst;
	}
	
	
}
