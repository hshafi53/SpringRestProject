package com.websystique.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.websystique.springmvc.model.NEFTDetails;
import com.websystique.springmvc.model.Transaction;
import com.websystique.springmvc.model.TransactionPayment;
import com.websystique.springmvc.model.TransactionUser;

public abstract class TransactionAbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public TransactionAbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public Transaction getByKey(Long transactionId) {
		return (Transaction) getSession().get(persistentClass, transactionId);
	}

	public TransactionUser getCustByKey(Long transactionId) {
		return (TransactionUser) getSession().get(persistentClass, transactionId);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void persistpayment(TransactionPayment transactiondetails) {
		getSession().persist(transactiondetails);
	}

	public void persistNEFTpayment(NEFTDetails transactiondetails) {
		getSession().persist(transactiondetails);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	public Transaction updateTransaction(Transaction Transaction) {
		// TODO Auto-generated method stub
		return null;
	}

}
