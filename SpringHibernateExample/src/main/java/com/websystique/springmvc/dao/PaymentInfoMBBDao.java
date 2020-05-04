package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.APGPaymentInfoMbb;

public interface PaymentInfoMBBDao {

	public void saveMBBInfo(APGPaymentInfoMbb paymentInfoMbb);

	public APGPaymentInfoMbb getMbbPaymentInfoByPaymentId(Long paymentId);

	public String getMbbPaymentInfoByCustomerId(Long customerid);

	public void saveUserPinStatus(Long cid, String status);

//	public APGPaymentInfoMbb getPaymentIdByCCDataKeymbb(Long ccDataKey);

	public void deletePaymentInfoMBB(Long paymentID);

	public List<Long> getTransactionListForCard(String ccNumber);

	public String getPinStatusByCid(Long cid);

}
