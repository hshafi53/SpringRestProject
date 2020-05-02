package com.websystique.springmvc.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.TransactionUser;

@Repository
public class CustomerDaoImpl {

	private final SessionFactory sessionFactory;

	@Autowired
	public CustomerDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long getBalanceByCustomerID(Long transactionId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransactionUser where transactionUserID= :transactionId");

		query.setLong("transactionId", transactionId);
		TransactionUser p = (TransactionUser) query.uniqueResult();
		Long pmntid = p.getAmount();

		return pmntid;
	}

	public Long getBalanceByCustomerName(String Cname) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransactionUser where customername= :Cname");

		query.setString("Cname", Cname);
		TransactionUser p = (TransactionUser) query.uniqueResult();
		Long pmntid = p.getAmount();

		return pmntid;
	}

	public void updateBal(Long finalbal, Long custid, Long transactionid) {
		System.out.println("inside update balance method");
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("update TransactionUser set amount = :finalbal" + " " + " where customer_id = :custid");
		query.setParameter("finalbal", finalbal);
		query.setParameter("custid", custid);
		int result = query.executeUpdate();
		System.out.println("result after balance updated" + result);
		if (result == 1) {
			System.out.println("Balance updated");
		} else {
			System.out.println("Balance not updated");

		}
		
	}

	public void updateDebitNEFTBal(Long debitBal, String sendername) {
		System.out.println("inside updateDebitNEFTBal method---------");
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(
				"update TransactionUser set amount = :debitBal" + " " + " where customername = :sendername");
		query.setParameter("debitBal", debitBal);
		query.setParameter("sendername", sendername);
		int result = query.executeUpdate();
		System.out.println("result after sender balance updated NEFT" + result);
		if (result == 1) {
			System.out.println("NEFT Debit Balance updated");
		} else {
			System.out.println("NEFT Debit Balance not updated");

		}

	}

	public void updatecreditNEFTBal(Long creditBal, String benename) {
		System.out.println("inside updatecreditNEFTBal method");
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("update TransactionUser set amount = :creditBal" + " " + " where customername = :benename");
		query.setParameter("creditBal", creditBal);
		query.setParameter("benename", benename);
		int result = query.executeUpdate();
		System.out.println("result after beneficiary balance updated" + result);
		if (result == 1) {
			System.out.println("NEFT credit Balance updated");
		} else {
			System.out.println("NEFT credit Balance not updated");

		}

	}

}
