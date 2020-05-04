package com.websystique.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.APGPaymentInfoPBB;

@Repository
public class APGPaymentInfoPBBDaoImpl implements PaymentInfoPBBDao {

	// @Autowired
	private APGPaymentInfoPBBRepository apgPaymentInfoPBBRepository;

	// @Autowired
	@Qualifier("apgEntityManager1")
	private EntityManager apgEntityManager;

	@Override
	public void savePBBInfo(com.websystique.springmvc.model.APGPaymentInfoPBB paymentInfoPBB) {
		apgPaymentInfoPBBRepository.save(paymentInfoPBB);
	}

	@Override
	public APGPaymentInfoPBB getPBBPaymentInfoByPaymentId(Long paymentId) {
		return apgPaymentInfoPBBRepository.getPBBPaymentInfoByPaymentId(paymentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getTransactionListForCard(String txtCardNumber) {
		return apgEntityManager.createNativeQuery(
				"SELECT TransactionID FROM APG_PaymentInfo_PBB,APG_TransactionPayment WHERE CCDisplay like ?0"
						+ " AND createdDate > DATE_SUB(CURDATE(), INTERVAL 1 DAY) "
						+ "AND APG_PaymentInfo_PBB.PaymentID = APG_TransactionPayment.PaymentID AND errorcode = 1")
				.setParameter(0, txtCardNumber).getResultList();
	}

}
