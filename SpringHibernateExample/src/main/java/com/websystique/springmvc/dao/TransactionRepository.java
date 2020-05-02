package com.websystique.springmvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Transaction;
import com.websystique.springmvc.model.TransactionPayment;

/*import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apg.v2.api.transaction.model.Transaction;
import com.apg.v2.api.transaction.model.TransactionPayment;
import com.apg.v2.api.transaction.model.TransactionUser;*/

@Repository("transactionRepository")
public interface TransactionRepository
		extends  JpaRepository<TransactionPayment, Long>  {

	@Query("Select t from Transaction t where t.transactionRefId = :transactionId")
	public Transaction getTransactionByTransactionRefId(@Param("transactionId") String transactionId);

	
	
	/*
	 * @Query("Select t from Transaction t where t.partnerRefno = :partnerRefNo")
	 * public Transaction isPartnerRefNoExist(@Param("partnerRefNo") String
	 * partnerRefNo);
	 * 
	 * @Query("Select transactionID from Transaction t where t.transactionRefId = :transactionRefId"
	 * ) public Long getTransactionId(@Param("transactionRefId") String
	 * transactionRefId);
	 * 
	 * @Query("Select transactionUserID from TransactionUser t where t.transactionID.transactionID = :transactionId"
	 * ) public Long getTransactionUserId(@Param("transactionId") Long
	 * transactionId);
	 */

	/*
	 * @Query("Select t from Transaction t where t.transactionRefId = 1001") public
	 * com.websystique.springmvc.model.Transaction
	 * getTransactionByTransactionRefId(@Param("transactionRefId") Long
	 * transactionRefId);
	 */

	@Query("Select t from Transaction t where t.transactionRefId = :transactionId")
	public Transaction getTransactionByTransactionRefId(@Param("transactionId") Long transactionId);

	@Query("Select t from Transaction t where t.transactionRefId = :transactionRefId")
	public com.websystique.springmvc.model.Transaction getTransactionByTransactionId(@Param("transactionRefId") Long transactionRefId);

	/*
	 * @Query("Select t from Transaction t where t.transactionRefId = :transactionId AND t.partnerKey = :partnerkey"
	 * ) public Transaction
	 * getTransactionByTransactionRefIdAndPartnerKey(@Param("transactionId") String
	 * transactionId,
	 * 
	 * @Param("partnerkey") String partnerkey);
	 * 
	 * @Query("Select t from Transaction t where  t.transType=:transType and t.status=:status and t.recurringID.status=:status and  t.transactionRefId = :transactionId AND t.partnerKey = :partnerkey"
	 * ) public Transaction
	 * getActiveRecurringTransactionByTransactionRefIdAndPartnerKey(
	 * 
	 * @Param("transactionId") String transactionId, @Param("partnerkey") String
	 * partnerkey,
	 * 
	 * @Param("transType") String transType, @Param("status") Integer status);
	 * 
	 * @Query("Select t from Transaction t where t.partnerRefno = :id and t.partnerKey = :partnerKey"
	 * ) public List<Transaction>
	 * getTransactionByPartnerRefNOAndPartnerKey(@Param("id") String id,
	 * 
	 * @Param("partnerKey") String partnerKey);
	 * 
	 * @Query("Select t from Transaction t where t.transType=:transType and t.status=:status and t.recurringID.status=:status and t.partnerRefno = :id and t.partnerKey = :partnerKey"
	 * ) public List<Transaction>
	 * getActiveRecurringTransactionByPartnerRefNOAndPartnerKey(@Param("id") String
	 * id,
	 * 
	 * @Param("partnerKey") String partnerKey, @Param("transType") String transType,
	 * 
	 * @Param("status") Integer status);
	 * 
	 * @Query("Select t from Transaction t where t.user.partnerUserKey  = :id and t.partnerKey = :partnerKey"
	 * ) public List<Transaction>
	 * getTransactionByPartnerUserKeyAndPartnerKey(@Param("id") String id,
	 * 
	 * @Param("partnerKey") String partnerKey);
	 * 
	 * @Query("Select t from Transaction t where t.transType=:transType and t.status=:status and t.recurringID.status=:status and  t.user.partnerUserKey  = :id and t.partnerKey = :partnerKey"
	 * ) public List<Transaction>
	 * getActiveRecurringTransactionByPartnerUserKeyAndPartnerKey(@Param("id")
	 * String id,
	 * 
	 * @Param("partnerKey") String partnerKey, @Param("transType") String transType,
	 * 
	 * @Param("status") Integer status);
	 * 
	 * @Query("Select p from TransactionPayment p where p.paymentRefID = :id")
	 * public TransactionPayment
	 * getTransactionByPaymentRefIDAndPartnerKey(@Param("id") String id);
	 * 
	 * @Query("Select p from Transaction p where p.status = :pendingStatus") public
	 * List<Transaction> getPendingTransaction(@Param("pendingStatus") Integer
	 * pendingStatus);
	 * 
	 * @Query("Select p from Transaction p where p.channelKey = :channelKey") public
	 * List<Transaction> getTransactionByChannelKey(@Param("channelKey") String
	 * channelKey);
	 * 
	 * @Query("Select p from Transaction p where p.partnerKey = :partnerKey") public
	 * List<Transaction> getTransactionByPartnerKey(@Param("partnerKey") String
	 * partnerKey);
	 * 
	 * @Query("Select t from Transaction t where t.transactionRefId = :transactionId AND t.partnerKey = :partnerkey"
	 * ) public Page<Transaction>
	 * getDetailsByTransactionRefIDAndPartnerKey(@Param("transactionId") String
	 * transactionId,
	 * 
	 * @Param("partnerkey") String partnerkey, Pageable pageRequest);
	 * 
	 * @Query("Select t from Transaction t where t.partnerRefno = :id and t.partnerKey = :partnerKey"
	 * ) public Page<Transaction> getDetailsByPartnerRefNoAndPartnerKey(@Param("id")
	 * String id,
	 * 
	 * @Param("partnerKey") String partnerKey, Pageable pageRequest);
	 * 
	 * @Query("Select t from Transaction t where t.user.partnerUserKey  = :id and t.partnerKey = :partnerKey"
	 * ) public Page<Transaction>
	 * getTransactionByPartnerUserKeyAndPartnerKey(@Param("id") String id,
	 * 
	 * @Param("partnerKey") String partnerKey, Pageable pageRequest);
	 * 
	 * @Query("Select t from Transaction t where t.transactionID  = :id and t.partnerKey = :partnerKey"
	 * ) public Page<Transaction>
	 * getTransactionByPaymentRefIDAndPartnerKey(@Param("id") Long id,
	 * 
	 * @Param("partnerKey") String partnerKey, Pageable pageRequest);
	 * 
	 * @Query("SELECT a FROM TransactionPayment a WHERE a.favCardDataKey = :ccDataKey"
	 * ) public TransactionPayment
	 * getTransactionPaymentByCCDataKey(@Param("ccDataKey")Long ccDataKey);
	 * 
	 * @Query("Select transactionID from Transaction t where t.transactionRefId = :transactionRefId"
	 * ) public Long getTransactionIdByTransactionRefId(@Param("transactionRefId")
	 * String transactionRefId);
	 * 
	 * @Query("Select t from TransactionUser t where t.partnerUserKey = :user")
	 * public List<TransactionUser> getRecurringByUser(@Param("user") String user);
	 */

}
