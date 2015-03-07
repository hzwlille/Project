package com.yaps.petstore.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {
	
    public T get(PK id);
    public void saveOrUpdate(T object);
    public void delete(PK Id);
    public List<T> findAll();

}
