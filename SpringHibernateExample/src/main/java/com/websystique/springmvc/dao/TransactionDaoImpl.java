package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;*/
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.response.dto.CreateTransactionResponseDto;
import com.response.dto.ValidationResponseDto;
import com.websystique.springmvc.model.NEFTDetails;
import com.websystique.springmvc.model.Transaction;
import com.websystique.springmvc.model.TransactionPayment;
import com.websystique.springmvc.model.TransactionUser;

/*import com.apg.v2.api.common.dto.request.GetTransactionDetailsRequestDto;
import com.apg.v2.api.common.dto.response.RecurringResponseDto;
import com.apg.v2.api.constants.ApplicationConstants;
import com.apg.v2.api.enums.RecurringEnum;
import com.apg.v2.api.transaction.dao.TransactionDao;
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
import com.apg.v2.api.transaction.model.TransactionUser;
import com.apg.v2.api.transaction.repository.AdditionalChargesRepository;
import com.apg.v2.api.transaction.repository.MiscDataRepository;
import com.apg.v2.api.transaction.repository.RecurringCCDataKeyInfoRepository;
import com.apg.v2.api.transaction.repository.TransactionEppInfoRepository;
import com.apg.v2.api.transaction.repository.TransactionItemsRepository;
import com.apg.v2.api.transaction.repository.TransactionLogRepository;
import com.apg.v2.api.transaction.repository.TransactionRecurringRepository;
import com.apg.v2.api.transaction.repository.TransactionRepository;*/

@Repository
public class TransactionDaoImpl extends TransactionAbstractDao<Integer, Transaction> implements TransactionDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public TransactionDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	// @Transactional
	public void saveTransaction(Transaction transactiondetails)

	{
		try {
			/* transactionRepository.save(transactiondetails); */
			System.out.println("createTransaction under daoimpl" + transactiondetails);
			persist(transactiondetails);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return transactionRepository.save(transactiondetails);
	}

	@Override
	// @Transactional
	public void saveTransactionPayment(TransactionPayment transactiondetails)

	{
		persistpayment(transactiondetails);
		// return transactionRepository.save(transactiondetails);
	}

	public void saveNEFT(NEFTDetails transactiondetails)

	{
		persistNEFTpayment(transactiondetails);
		// return transactionRepository.save(transactiondetails);
	}

	/*
	 * @Autowired
	 * 
	 * @Qualifier("apgEntityManager1") private EntityManager em;
	 * 
	 */
	/*
	 * @Autowired private TransactionRepository transactionRepository;
	 */
	/*
	 * @Autowired private TransactionLogRepository transactionLogRepository;
	 * 
	 * @Autowired private MiscDataRepository miscDataRepository;
	 * 
	 * @Autowired private TransactionEppInfoRepository transactionEppInfoRepository;
	 * 
	 * @Autowired private TransactionRecurringRepository
	 * transactionRecurringRepository;
	 * 
	 * @Autowired private RecurringCCDataKeyInfoRepository
	 * recurringCCDataKeyInfoRepository;
	 * 
	 * @Autowired private TransactionItemsRepository transactionItemsRepository;
	 * 
	 * @Autowired private AdditionalChargesRepository additionalChargesRepository;
	 */
	/*
	 * @Override public Transaction saveTransaction(Transaction transactiondetails)
	 * { return transactionRepository.save(transactiondetails); }
	 * 
	 * @Override public Transaction checkPartnerRefNo(String partnerRefNo) {
	 * 
	 * return transactionRepository.isPartnerRefNoExist(partnerRefNo); }
	 * 
	 * @Override public String saveTransactionLog(TransactionLog
	 * transactionLogDetails) {
	 * 
	 * transactionLogRepository.save(transactionLogDetails); return null; }
	 * 
	 * @Override public List<APGRecurring> getRecurringByRecurringList(List<Long>
	 * recurringList) { return
	 * transactionRecurringRepository.getRecurringByRecurringId(recurringList); }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public EntryPageModel getTransactionDataforEntryPage(String
	 * transactionId) {
	 * 
	 * List<Object[]> results = em
	 * .createNativeQuery("select title, currencycode,currencyexp," +
	 * "shippingamount, taxamount," +
	 * "itemtotalamount, totalamount, TransactionID from APG_Transaction where transactionrefid=?1 "
	 * ) .setParameter(1, transactionId).getResultList(); EntryPageModel epm = null;
	 * for (Object[] record : results) { String title = ((String) record[0]); String
	 * currencyCode = (String) record[1]; String currencyExp =
	 * String.valueOf(record[2]); String shippingAmount = String.valueOf(record[3]);
	 * String taxAmount = String.valueOf(record[4]); String itemTotalAmount =
	 * String.valueOf(record[5]); String totalAmount = String.valueOf(record[6]);
	 * Long transId = Long.parseLong(String.valueOf(record[7])); epm = new
	 * EntryPageModel(title, currencyCode, currencyExp, shippingAmount, taxAmount,
	 * itemTotalAmount, totalAmount, transId);
	 * 
	 * } return epm; }
	 * 
	 * @Override public void saveMiscData(MiscData miscData) {
	 * miscDataRepository.save(miscData); }
	 * 
	 * @Override public Long getTransactionId(String transactionRefId) { return
	 * transactionRepository.getTransactionId(transactionRefId); }
	 * 
	 * @Override public Long getTransactionUserId(Long transactionId) { return
	 * transactionRepository.getTransactionUserId(transactionId); }
	 * 
	 * 
	 * 
	 * @Override public Transaction getTransactionByTransactionId(Long
	 * transactionId) {
	 * 
	 * return transactionRepository.findOne(transactionId); }
	 * 
	 * @Override public void saveTransactionEppInfo(APGTransactionEppInfo
	 * apgTransactionEppInfo) {
	 * transactionEppInfoRepository.save(apgTransactionEppInfo); }
	 * 
	 * @Override public boolean checkEppInfoExists(Long transactionID) { int size =
	 * em.
	 * createNativeQuery("SELECT EppInfoID FROM APG_Transaction_EPPInfo WHERE TransactionID  = ?0"
	 * ) .setParameter(0, transactionID).getResultList().size();
	 * 
	 * return size > 0; }
	 * 
	 * @Override
	 * 
	 * public void deleteTransaction(Long transactionID) {
	 * transactionRepository.delete(transactionID); }
	 * 
	 * @Override public void deleteTransactionEppInfo(Long transactionID) {
	 * transactionEppInfoRepository.deleteTransactionEpp(transactionID);
	 * 
	 * }
	 * 
	 * @Override public List<Transaction> getTransactionListById(List<Long>
	 * transactionIdList) { return (List<Transaction>)
	 * transactionRepository.findAll(transactionIdList);
	 * 
	 * }
	 * 
	 * @Override public void saveTransactionRecurring(APGRecurring apgRecurring) {
	 * transactionRecurringRepository.save(apgRecurring); }
	 * 
	 * @Override public void saveTransactionRecurring(List<APGRecurring>
	 * apgRecurring) { transactionRecurringRepository.save(apgRecurring); }
	 * 
	 * @Override public void saveRecurringCCDataKeyInfo(RecurringCCDataKeyInfo
	 * recurringCCDataKeyInfo) {
	 * recurringCCDataKeyInfoRepository.save(recurringCCDataKeyInfo);
	 * 
	 * }
	 * 
	 * @Override public APGRecurring getRecurringById(Long recurringID) { return
	 * transactionRecurringRepository.findOne(recurringID); }
	 * 
	 * @Override public List<APGRecurring> getRecurringScheduledTransactions(String
	 * startDate, String endDate, Integer retryCount) { return
	 * transactionRecurringRepository.getRecurringScheduledTransactions(startDate,
	 * endDate, retryCount); }
	 * 
	 * @Override public Transaction
	 * getTransactionByTransactionRefIdAndPartnerKey(String transactionId, String
	 * partnerkey) { return
	 * transactionRepository.getTransactionByTransactionRefIdAndPartnerKey(
	 * transactionId, partnerkey); }
	 * 
	 * public List<Transaction>
	 * getTransactionDetails(GetTransactionDetailsRequestDto request) { String id =
	 * request.getBody().getReferenceId(); String partnerKey =
	 * request.getBody().getPartnerKey(); List<Transaction> transactionList = new
	 * ArrayList<>(); if
	 * (request.getBody().getReferenceType().equals(String.valueOf(
	 * ApplicationConstants.REFERENCE_TYPE1))) { Transaction transaction =
	 * transactionRepository.getTransactionByTransactionRefIdAndPartnerKey(id,
	 * partnerKey); transactionList.add(transaction); return transactionList; } else
	 * if (request.getBody().getReferenceType().equals(String.valueOf(
	 * ApplicationConstants.REFERENCE_TYPE2))) { return
	 * transactionRepository.getTransactionByPartnerRefNOAndPartnerKey(id,
	 * partnerKey); } else if
	 * (request.getBody().getReferenceType().equals(String.valueOf(
	 * ApplicationConstants.REFERENCE_TYPE3))) { return
	 * transactionRepository.getTransactionByPartnerUserKeyAndPartnerKey(id,
	 * partnerKey); } else if
	 * (request.getBody().getReferenceType().equals(String.valueOf(
	 * ApplicationConstants.REFERENCE_TYPE4))) { TransactionPayment payment =
	 * transactionRepository.getTransactionByPaymentRefIDAndPartnerKey(id);
	 * List<Transaction> list = new ArrayList<>(); Transaction t =
	 * payment.getTransaction(); list.add(t); return list; } return null; }
	 * 
	 * @Override public List<Transaction> getTransactionDetails(String
	 * referenceType, String id, String partnerKey) throws Exception {
	 * List<Transaction> transactionList = new ArrayList<>(); if
	 * (referenceType.equals(String.valueOf(ApplicationConstants.REFERENCE_TYPE1)))
	 * { Transaction transaction = transactionRepository
	 * .getActiveRecurringTransactionByTransactionRefIdAndPartnerKey(id, partnerKey,
	 * "r", 1); if (transaction == null) { throw new
	 * Exception(String.valueOf(RecurringEnum.INVALID_TRANSACTION_ID.getResponseCode
	 * ())); } transactionList.add(transaction); return transactionList; } else if
	 * (referenceType.equals(String.valueOf(ApplicationConstants.REFERENCE_TYPE2)))
	 * { transactionList = transactionRepository.
	 * getActiveRecurringTransactionByPartnerRefNOAndPartnerKey(id, partnerKey, "r",
	 * 1); if (transactionList == null || transactionList.isEmpty()) { throw new
	 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER_REFNO.getResponseCode(
	 * ))); } return transactionList; } else if
	 * (referenceType.equals(String.valueOf(ApplicationConstants.REFERENCE_TYPE3)))
	 * { transactionList = transactionRepository.
	 * getActiveRecurringTransactionByPartnerUserKeyAndPartnerKey(id, partnerKey,
	 * "r", 1); if (transactionList == null || transactionList.isEmpty()) { throw
	 * new Exception(String.valueOf(RecurringEnum.INVALID_PARTNER_USER_KEY.
	 * getResponseCode())); } return transactionList; } else { throw new
	 * Exception(String.valueOf(RecurringEnum.INVALID_REFERENCE_TYPE.getResponseCode
	 * ())); } }
	 * 
	 * @Override public Page<Transaction> getTransactionDetails(String referenceId,
	 * String referenceType, String partnerKey, Integer pageNumber, Integer
	 * pageSize) {
	 * 
	 * // Transactionid(transactionrefid) if
	 * (referenceType.equals(String.valueOf(ApplicationConstants.REFERENCE_TYPE1)))
	 * {
	 * 
	 * Page<Transaction> transactionList =
	 * transactionRepository.getDetailsByTransactionRefIDAndPartnerKey( referenceId,
	 * partnerKey, new PageRequest(pageNumber, pageSize));
	 * 
	 * return transactionList; }
	 * 
	 * // partnerrefno else if
	 * (referenceType.equals(String.valueOf(ApplicationConstants.REFERENCE_TYPE2)))
	 * {
	 * 
	 * Page<Transaction> transactionList =
	 * transactionRepository.getDetailsByPartnerRefNoAndPartnerKey(referenceId,
	 * partnerKey, new PageRequest(pageNumber, pageSize));
	 * 
	 * return transactionList; }
	 * 
	 * // partner user key else if
	 * (referenceType.endsWith(String.valueOf(ApplicationConstants.REFERENCE_TYPE3))
	 * ) {
	 * 
	 * Page<Transaction> transactionList =
	 * transactionRepository.getTransactionByPartnerUserKeyAndPartnerKey(
	 * referenceId, partnerKey, new PageRequest(pageNumber, pageSize));
	 * 
	 * return transactionList; }
	 * 
	 * // payment ref id else if
	 * (referenceType.equals(String.valueOf(ApplicationConstants.REFERENCE_TYPE4)))
	 * {
	 * 
	 * TransactionPayment payment =
	 * transactionRepository.getTransactionByPaymentRefIDAndPartnerKey(referenceId);
	 * 
	 * Page<Transaction> transactionList =
	 * transactionRepository.getTransactionByPaymentRefIDAndPartnerKey(
	 * payment.getTransaction().getTransactionID(), partnerKey, new
	 * PageRequest(pageNumber, pageSize));
	 * 
	 * return transactionList; } return null; }
	 * 
	 * @Override public List<MiscData> getExtraDataList(Long transactionID) { return
	 * miscDataRepository.getExtraDataList(transactionID); }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<GetListCreditCardDetails>
	 * getListRecurringCardDetailsforSSoSessionId(String ssoId) {
	 * 
	 * List<Object[]> results =
	 * em.createNativeQuery("SELECT  recur.CCDataKey, recurcc.CCDisplay, " +
	 * "transaction.partnerKey FROM " + "APG_Recurring recur inner join " +
	 * "APG_Transaction transaction on recur.TransactionID = transaction.TransactionID inner join "
	 * +
	 * "RecurringCCDataKeyInfo recurcc on recurcc.RecurringID = recur.RecurringID inner join "
	 * +
	 * "APG_TransactionUser transactionuser on transaction.TransactionID = transactionuser.TransactionID where "
	 * + "transactionuser.LoginUserID =?1").setParameter(1, ssoId).getResultList();
	 * GetListCreditCardDetails cardDetails = null; List<GetListCreditCardDetails>
	 * cardDetailsList = new ArrayList<>(); for (Object[] record : results) {
	 * 
	 * String ccDataKey = String.valueOf(record[0]); String ssDisplay =
	 * String.valueOf(record[1]); String partnerKey = String.valueOf(record[2]);
	 * cardDetails = new GetListCreditCardDetails(ccDataKey, ssDisplay, partnerKey);
	 * cardDetailsList.add(cardDetails); } return cardDetailsList; }
	 * 
	 * @Override public List<TransactionItem> getTransactionItemsById(Long
	 * transactionID) { return
	 * transactionItemsRepository.getTransactionItemsById(transactionID); }
	 * 
	 * @Override public List<APGAdditionalCharges> getAdditionalChargesById(Long
	 * transactionID) { return
	 * additionalChargesRepository.getAdditionalChargesById(transactionID);
	 * 
	 * }
	 * 
	 * @Override public APGRecurring getRecurringByTransactionID(Long transactionID)
	 * { return
	 * transactionRecurringRepository.getRecurringByTransactionID(transactionID); }
	 * 
	 * @Override public List<APGRecurring> getRecurringByTransactionRefID(String
	 * transactionID) { return
	 * transactionRecurringRepository.getRecurringByTransactionRefID(transactionID);
	 * }
	 * 
	 * @Override public List<APGRecurring> getRecurringByCCDataKey(String ccDataKey)
	 * { return transactionRecurringRepository.getRecurringByCCDataKey(ccDataKey); }
	 * 
	 * @Override public List<APGRecurring> getRecurringByCCDataKey(List<String>
	 * ccDataKey) { return
	 * transactionRecurringRepository.getRecurringByCCDataKey(ccDataKey); }
	 * 
	 * @Override public List<Transaction> getTransactionByChannelKey(String
	 * channelKey) { return
	 * transactionRepository.getTransactionByChannelKey(channelKey);
	 * 
	 * }
	 * 
	 * @Override public List<Transaction> getTransactionByPartnerKey(String
	 * partnerKey) { return
	 * transactionRepository.getTransactionByPartnerKey(partnerKey); }
	 * 
	 * @Override public List<TransactionUser> getRecurringByUser(String user) {
	 * return transactionRepository.getRecurringByUser(user); }
	 * 
	 * @Override public void saveTransactionItems(List<TransactionItem> items) {
	 * transactionItemsRepository.save(items); }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<RecurringResponseDto>
	 * getRecurringDetailsByPartnerUserKey(String user) throws ParseException {
	 * 
	 * List<Object[]> results = em.createNativeQuery(
	 * "Select recur.CycleDuration, recur.CyclePeriod, recur.CCDataKey, recur.ChargeAmount, recur.CurrencyExp, recur.NextChargeDateUTC, trans.title, recur.CurrencyCode, trans.createdateUTC, recur.RecurringID from APG_Transaction trans inner join  APG_TransactionUser user on trans.TransactionID = user.TransactionID inner  join APG_Recurring recur on user.TransactionID = recur.TransactionID  where trans.Status = 1 and recur.Status = 1 and user.PartnerUserKey = ?1"
	 * )
	 * 
	 * .setParameter(1, user).getResultList(); List<RecurringResponseDto> dtoList =
	 * new ArrayList<>(); for (Object[] record : results) { RecurringResponseDto dto
	 * = new RecurringResponseDto(); dto.setBillingCycle((String.valueOf(record[0]))
	 * + " " + (String.valueOf(record[1])));
	 * dto.setCcDataKey(String.valueOf(record[2]));
	 * dto.setFormatNextRecurringDate((String.valueOf(record[5])));
	 * dto.setTotalSubscriptionPrice(
	 * 
	 * Long.parseLong(String.valueOf(record[3])));
	 * dto.setTitle(String.valueOf(record[6]));
	 * 
	 * dto.setCurrencyExp(Integer.parseInt(String.valueOf(record[4])));
	 * 
	 * dto.setCurrencyCode(String.valueOf(record[7]));
	 * dto.setCreateDateUTC(((Timestamp)record[8]));
	 * dto.setRecurringID(Long.parseLong(String.valueOf(record[9])));
	 * dtoList.add(dto);
	 * 
	 * } return dtoList; }
	 * 
	 * @Override public List<Transaction> getPendingTransaction(Integer
	 * pendingStatus) { List<Transaction>
	 * transactionList=transactionRepository.getPendingTransaction(pendingStatus);
	 * 
	 * return transactionList; }
	 */

	/*
	 * @Transactional
	 * 
	 * @Query("Select t from Transaction t where t.transactionRefId = :transactionId"
	 * )
	 */

	/*
	 * @Override public Transaction getTransactionByTransactionId(Long
	 * transactionId) { return getByKey(transactionId); }
	 */
	@Transactional
	public List<String> getTransactionByTransactionRefId(Long transactionId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Transaction where transactionrefid= :transactionId");

		query.setLong("transactionId", transactionId);
		Transaction p = (Transaction) query.uniqueResult();

		List transobj = new ArrayList();
		transobj.add(p.getTransType());
		transobj.add(p.getPaymentType());
		transobj.add(p.getTotalAmount());
		transobj.add(p.getStatus());

		return transobj;
	}

	@Transactional
	public Long getTransactionByTransactionReferenceId(Long transactionId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransactionPayment where transactionid= :transactionId");

		query.setLong("transactionId", transactionId);
		TransactionPayment p = (TransactionPayment) query.uniqueResult();
		Long pmntid = p.getPaymentID();

		return pmntid;
	}

	@Transactional
	public Long getNEFTDetailsBySenderName(String sendername) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from NEFTDetails where sender_Name= :sendername");

		query.setString("sendername", sendername);
		NEFTDetails p = (NEFTDetails) query.setMaxResults(1).uniqueResult();
		Long pmntid = p.getTransactionID();

		return pmntid;
	}

	// getSenderDetailsBySenderName
	@Transactional
	public TransactionUser getSenderDetailsBySenderName(String sendername) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransactionUser where customername= :sendername");

		query.setString("sendername", sendername);
		TransactionUser p = (TransactionUser) query.uniqueResult();

		return p;
	}

	@Transactional
	public TransactionUser getBeneDetailsByBeneName(String receivername) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransactionUser where customername= :receivername");

		query.setString("receivername", receivername);
		TransactionUser p = (TransactionUser) query.setMaxResults(1).uniqueResult();

		return p;
	}

}
