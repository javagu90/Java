package com.javagu.pos.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, ID extends Serializable>
{
	T insert(T entity); //save
	T update(T entity);
	void delete(T entity);
	T findById(ID id);
	List<T> findAll(); //select * from
	void flush();
	
}
