package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.APGPaymentInfoPBB;

public interface PaymentInfoPBBDao {

	void savePBBInfo(APGPaymentInfoPBB paymentInfoPBB);

	APGPaymentInfoPBB getPBBPaymentInfoByPaymentId(Long paymentID);

	List<Long> getTransactionListForCard(String txtCardNumber);

}
