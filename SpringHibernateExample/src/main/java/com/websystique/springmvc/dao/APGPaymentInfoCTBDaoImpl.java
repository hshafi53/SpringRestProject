package com.websystique.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.APGPaymentInfoCTB;

@Repository
public class APGPaymentInfoCTBDaoImpl implements PaymentInfoCTBDao {

	// @Autowired
	private APGPaymentInfoCTBRepository apgPaymentInfoCTBRepository;

	// @Autowired
	@Qualifier("apgEntityManager1")
	private EntityManager apgEntityManager;

	@Override
	public void saveCTBInfo(com.websystique.springmvc.model.APGPaymentInfoCTB paymentInfoCtb) {
		apgPaymentInfoCTBRepository.save(paymentInfoCtb);

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

	@Override
	public APGPaymentInfoCTB getCTBPaymentInfoByPaymentId(Long paymentID) {

		return apgPaymentInfoCTBRepository.findByPaymentId(paymentID);
	}

}
