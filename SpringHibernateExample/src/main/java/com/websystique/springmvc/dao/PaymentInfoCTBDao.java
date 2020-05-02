package com.websystique.springmvc.dao;
import com.websystique.springmvc.model.*;
import java.util.List;


public interface PaymentInfoCTBDao {

	public void saveCTBInfo(APGPaymentInfoCTB paymentInfoCtb);

	public List<Long> getTransactionListForCard(String ccNumber);

	public APGPaymentInfoCTB getCTBPaymentInfoByPaymentId(Long paymentID);

}
