package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.APGPaymentInfoCTB;

public interface PaymentInfoCTBDao {

	public void saveCTBInfo(APGPaymentInfoCTB paymentInfoCtb);

	public List<Long> getTransactionListForCard(String ccNumber);

	public APGPaymentInfoCTB getCTBPaymentInfoByPaymentId(Long paymentID);

}
