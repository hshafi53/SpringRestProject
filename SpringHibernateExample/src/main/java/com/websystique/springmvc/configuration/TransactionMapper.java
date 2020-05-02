package com.websystique.springmvc.configuration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;

import com.request.dto.NewNEFTRequestDto;
import com.websystique.springmvc.dao.CustomerDaoImpl;
import com.websystique.springmvc.model.NEFTDetails;
import com.websystique.springmvc.model.Transaction;
import com.websystique.springmvc.model.TransactionPayment;
import com.websystique.springmvc.service.CreateTransactionService;

public class TransactionMapper {

	@Autowired
	private static CustomerDaoImpl custdaoimpl;

	private static final String DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss";

	private static final String DATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	static CreateTransactionService createser = new CreateTransactionService();

	private TransactionMapper() {

	}

	public static com.websystique.springmvc.model.Transaction createTransactionMapper(
			com.request.dto.CreateTransactionRequestDto createTransactionRequest, Long balance, Long transactionRefId,
			Long transactionitemid, com.response.dto.ValidationResponseDto validationResponse) {

		com.websystique.springmvc.model.Transaction transaction = new com.websystique.springmvc.model.Transaction();

		transaction.setTransType(createTransactionRequest.getCreateTransactionBody().getTransType());

		transaction.setPaymentType(createTransactionRequest.getCreateTransactionBody().getPaymentType());
		transaction.setCurrencyCode(createTransactionRequest.getCreateTransactionBody().getCurrencyCode());
		// transaction.setItemTotal(createTransactionRequest.getCreateTransactionBody().getItemTotal());
		// ITem Data Info
		List<com.websystique.springmvc.model.TransactionItem> transactionItemList = new ArrayList<>();
		List<com.request.dto.ItemDesc> itemList = createTransactionRequest.getCreateTransactionBody().getItemDesc();
		Long TotalAmount = 0L;

		transaction.setCreateDateUTC(new Timestamp(System.currentTimeMillis()));

		if (itemList != null) {

			for (com.request.dto.ItemDesc item : itemList) {
				System.out.println("item from itemList" + itemList);

				com.websystique.springmvc.model.TransactionItem transactionItem = new com.websystique.springmvc.model.TransactionItem();
				transactionItem.setTransactionItemID(transactionitemid);
				transactionItem.setName(item.getName());
				transactionItem.setQuantity(item.getQty());
				transactionItem.setUnitPrice(item.getUnitprice());
				transactionItem.setTransactionid(transaction.getTransactionID());
				transactionItemList.add(transactionItem);
				int qty = item.getQty();
				Long unitpr = item.getUnitprice();
				TotalAmount = qty * unitpr;
			}
		}
		System.out.println("TotalAmount is" + TotalAmount);
		transaction.setTotalAmount(TotalAmount);

		transaction.setTransactionRefId(transactionRefId);
		transaction.setStatus(0);

		// Customer data info
		com.request.dto.CustomerDetail customer = createTransactionRequest.getCreateTransactionBody()
				.getCustomerDetail();
		Long finalbal = 0L;

		if (customer != null) {

			com.websystique.springmvc.model.TransactionUser transactionUser = new com.websystique.springmvc.model.TransactionUser();

			transactionUser.setCustomername(customer.getCustomerName());
			transactionUser.setTransactionUserID(customer.getCustomerId());

			transactionUser.setTransactionID(transaction.getTransactionID());

			finalbal = balance - TotalAmount;

			transactionUser.setAmount(finalbal);

		}
		transaction.setBalance(finalbal);
		return transaction;

	}

	// createNEFTTransactionMapper
	public static NEFTDetails createNEFTTransactionMapper(NewNEFTRequestDto neftrequest) {
		NEFTDetails neftdetails = new NEFTDetails();
		neftdetails.setSender_Name(neftrequest.getCreateTransactionBody().getSender_Name());
		neftdetails.setSender_Accountno(neftrequest.getCreateTransactionBody().getSender_Accountno());
		neftdetails.setSender_Accounttype(neftrequest.getCreateTransactionBody().getSender_Accounttype());
		neftdetails.setSender_IFSC(neftrequest.getCreateTransactionBody().getSender_IFSC());
		neftdetails.setBeneficiary_Name(neftrequest.getCreateTransactionBody().getBeneficiary_Name());
		neftdetails.setBeneficiary_Accountno(neftrequest.getCreateTransactionBody().getBeneficiary_Accountno());
		neftdetails.setBeneficiary_Accounttype(neftrequest.getCreateTransactionBody().getBeneficiary_Accounttype());
		neftdetails.setBeneficiary_IFSC(neftrequest.getCreateTransactionBody().getBeneficiary_IFSC());
		neftdetails.setAMOUNT(neftrequest.getCreateTransactionBody().getAMOUNT());

		return neftdetails;

	}

	public static TransactionPayment createTransactionPaymentMapper(Transaction createTransactionRequest,
			Long paymentRefId) {

		com.websystique.springmvc.model.TransactionPayment transaction = new com.websystique.springmvc.model.TransactionPayment();

		transaction.setTransType(createTransactionRequest.getTransType());

		transaction.setPaymentType(createTransactionRequest.getPaymentType());
		transaction.setAmount(createTransactionRequest.getTotalAmount().toString());
		transaction.setPaymentRefID(paymentRefId);
		transaction.setTransactionid(createTransactionRequest.getTransactionID());
		transaction.setStatus(0);

		transaction.setBank("mbb");
		/*
		 * transaction.setPaymentID(transaction.getPaymentID());
		 * transaction.setStatus(status);
		 */
		return transaction;

	}

	private static Date getDateWithoutTime(Date date) {

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();

	}

}