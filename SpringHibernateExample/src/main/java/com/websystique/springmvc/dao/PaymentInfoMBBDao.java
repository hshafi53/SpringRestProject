package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.*;
import java.util.List;


public interface PaymentInfoMBBDao {

	public void saveMBBInfo(APGPaymentInfoMbb paymentInfoMbb);

	public APGPaymentInfoMbb getMbbPaymentInfoByPaymentId(Long paymentId);
	public String getMbbPaymentInfoByCustomerId(Long customerid);

	
	
//	public APGPaymentInfoMbb getPaymentIdByCCDataKeymbb(Long ccDataKey);

	public void deletePaymentInfoMBB(Long paymentID);

	public List<Long> getTransactionListForCard(String ccNumber);

	

}
