package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.NEFTDetails;
import com.websystique.springmvc.model.Transaction;
import com.websystique.springmvc.model.TransactionPayment;
import com.websystique.springmvc.model.TransactionUser;

//import org.springframework.data.domain.Page;

/*import com.apg.v2.api.common.dto.request.GetTransactionDetailsRequestDto;
import com.apg.v2.api.common.dto.response.RecurringResponseDto;
import com.apg.v2.api.transaction.model.APGAdditionalCharges;
import com.apg.v2.api.transaction.model.APGRecurring;
import com.apg.v2.api.transaction.model.APGTransactionEppInfo;
import com.apg.v2.api.transaction.model.EntryPageModel;
import com.apg.v2.api.transaction.model.GetListCreditCardDetails;
import com.apg.v2.api.transaction.model.MiscData;
import com.apg.v2.api.transaction.model.RecurringCCDataKeyInfo;
import com.apg.v2.api.transaction.model.Transaction;
import com.apg.v2.api.transaction.model.TransactionItem;
import com.apg.v2.api.transaction.model.TransactionLog;
import com.apg.v2.api.transaction.model.TransactionPayment;
import com.apg.v2.api.transaction.model.TransactionUser;*/

public interface TransactionDao {
	public void saveTransaction(Transaction createTransaction);

	public Long getNEFTDetailsBySenderName(String sendername);

	public TransactionUser getSenderDetailsBySenderName(String sendername);// getBeneDetailsByBeneName

	public TransactionUser getBeneDetailsByBeneName(String receivername);// getBeneDetailsByBeneName

	public void saveTransactionPayment(TransactionPayment transpay);

	public void saveNEFT(NEFTDetails transpay);

	// saveNEFT

	public Long getTransactionByTransactionReferenceId(Long transactionId);
	/*
	 * public Transaction saveTransaction(Transaction transactionDetails);
	 * 
	 * public Transaction checkPartnerRefNo(String partnerRefNo);
	 * 
	 * public String saveTransactionLog(TransactionLog transactionLogDetails);
	 * 
	 * public EntryPageModel getTransactionDataforEntryPage(String transactionId);
	 * 
	 * public void saveMiscData(MiscData miscData);
	 * 
	 * public Long getTransactionId(String transactionRefId);
	 * 
	 * public Long getTransactionUserId(Long transactionId);
	 */

	/*
	 * public Transaction getTransactionByTransactionId(Long transactionRefId);
	 */ public List<String> getTransactionByTransactionRefId(Long transactionRefId);

	// public Transaction getTransactionByTransactionReferenceId(Long
	// transactionRefId);

	/*
	 * public Transaction getTransactionByTransactionId(Long transactionId);
	 * 
	 * public void saveTransactionEppInfo(APGTransactionEppInfo
	 * apgTransactionEppInfo);
	 * 
	 * public boolean checkEppInfoExists(Long transactionID);
	 * 
	 * public void deleteTransaction(Long transactionID);
	 * 
	 * public void deleteTransactionEppInfo(Long transactionID);
	 * 
	 * public List<Transaction> getTransactionListById(List<Long>
	 * transactionIdList);
	 * 
	 * public void saveTransactionRecurring(APGRecurring apgRecurring);
	 * 
	 * public void saveTransactionRecurring(List<APGRecurring> apgRecurring);
	 * 
	 * public void saveRecurringCCDataKeyInfo(RecurringCCDataKeyInfo
	 * recurringCCDataKeyInfo);
	 * 
	 * public APGRecurring getRecurringById(Long recurringID);
	 * 
	 * public List<Transaction>
	 * getTransactionDetails(GetTransactionDetailsRequestDto request);
	 * 
	 * public Transaction getTransactionByTransactionRefIdAndPartnerKey(String
	 * transactionId, String partnerkey);
	 * 
	 * public List<MiscData> getExtraDataList(Long transactionID);
	 * 
	 * public List<GetListCreditCardDetails>
	 * getListRecurringCardDetailsforSSoSessionId(String ssoId);
	 * 
	 * public List<TransactionItem> getTransactionItemsById(Long transactionID);
	 * 
	 * public List<APGAdditionalCharges> getAdditionalChargesById(Long
	 * transactionID);
	 * 
	 * public APGRecurring getRecurringByTransactionID(Long transactionID);
	 * 
	 * public List<APGRecurring> getRecurringByCCDataKey(String ccDataKey);
	 * 
	 * public List<Transaction> getTransactionByChannelKey(String channelKey);
	 * 
	 * public List<Transaction> getTransactionByPartnerKey(String partnerKey);
	 * 
	 * public List<TransactionUser> getRecurringByUser(String user);
	 * 
	 * public void saveTransactionItems(List<TransactionItem> items);
	 * 
	 * public List<APGRecurring> getRecurringByCCDataKey(List<String> ccDataKey);
	 * 
	 * public List<APGRecurring> getRecurringByTransactionRefID(String
	 * transactionID);
	 * 
	 * public Page<Transaction> getTransactionDetails(String referenceId, String
	 * referenceType, String partnerKey, Integer pageNumber, Integer pageSize);
	 * 
	 * public List<APGRecurring> getRecurringScheduledTransactions(String startDate,
	 * String endDate, Integer retryCount);
	 * 
	 * public List<Transaction> getTransactionDetails(String referenceType, String
	 * id, String partnerKey) throws Exception;
	 * 
	 * public List<RecurringResponseDto> getRecurringDetailsByPartnerUserKey(String
	 * user) throws ParseException;
	 * 
	 * public List<Transaction> getPendingTransaction(Integer paymentPending);
	 * 
	 * public List<APGRecurring> getRecurringByRecurringList(List<Long>
	 * recurringList);
	 */

}
