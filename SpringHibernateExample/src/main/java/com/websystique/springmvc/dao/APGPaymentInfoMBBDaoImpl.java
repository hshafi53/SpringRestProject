package com.websystique.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.APGPaymentInfoMbb;

@Repository
public class APGPaymentInfoMBBDaoImpl implements PaymentInfoMBBDao {

	// @Autowired
	private APGPaymentInfoMBBRepository paymentInfoMBBRepository;

	private final SessionFactory sessionFactory;

	@Autowired
	public APGPaymentInfoMBBDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// @Autowired
	@Qualifier("apgEntityManager1")
	private EntityManager apgEntityManager;

	@Override
	public void saveMBBInfo(com.websystique.springmvc.model.APGPaymentInfoMbb paymentInfoMbb) {

		paymentInfoMBBRepository.save(paymentInfoMbb);
	}

	// @Transactional
	public Long getCustomerByPIN(Long pin, Long cid) {
		Long DBPin = 0L;
		try {
			System.out.println("Pin from user in side DAO================" + pin);
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from APGPaymentInfoMbb where customerId= :cid");
			query.setLong("cid", cid);
			APGPaymentInfoMbb p = (APGPaymentInfoMbb) query.uniqueResult();

			DBPin = p.getPin();
			System.out.println("DB Pin from Database in DAO Method--------------" + DBPin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DBPin;
	}

	public String getMbbPaymentInfoByCustomerId(Long customerid) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from APGPaymentInfoMbb where customerId= :customerid");
		query.setLong("customerid", customerid);
		APGPaymentInfoMbb p = (APGPaymentInfoMbb) query.uniqueResult();
		String ccdisp = p.getCcDisplay();

		return ccdisp;
	}

	@Override
	public APGPaymentInfoMbb getMbbPaymentInfoByPaymentId(Long paymentId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from APGPaymentInfoMbb where paymentID= :paymentId");
		query.setLong("paymentId", paymentId);
		APGPaymentInfoMbb p = (APGPaymentInfoMbb) query.uniqueResult();

		return p;
	}

	public void saveUserPinStatus(Long cid, String status) {
		System.out.println("inside saveUserPinStatus method");
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("update APGPaymentInfoMbb set UserPin = :status" + " " + " where customer_id = :cid");
		query.setParameter("status", status);
		query.setParameter("cid", cid);
		int result = query.executeUpdate();
		System.out.println("result after PIN status updated" + result);
		if (result == 1) {
			System.out.println("PIN status updated");
		} else {
			System.out.println("PIN status not updated");

		}

	}

	public String getPinStatusByCid(Long cid) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from APGPaymentInfoMbb where customer_id= :cid");
		query.setParameter("cid", cid);
		APGPaymentInfoMbb p = (APGPaymentInfoMbb) query.uniqueResult();
		String status = p.getUserpin();
		return status;

	}

	@Override
	public void deletePaymentInfoMBB(Long paymentID) {
		paymentInfoMBBRepository.deleteByPaymentId(paymentID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getTransactionListForCard(String ccNumber) {

		return apgEntityManager.createNativeQuery(
				"SELECT TransactionID FROM APG_paymentInfo_MBB,APG_TransactionPayment WHERE CCDisplay like ?0"
						+ " AND createdDate > DATE_SUB(CURDATE(), INTERVAL 1 DAY) "
						+ "AND APG_paymentInfo_MBB.PaymentID = APG_TransactionPayment.PaymentID AND errorcode = 1")
				.setParameter(0, ccNumber).getResultList();
	}

}
