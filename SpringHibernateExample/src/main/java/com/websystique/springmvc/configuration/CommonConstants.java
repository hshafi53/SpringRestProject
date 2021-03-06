/**
 * 
 */
package com.websystique.springmvc.configuration;

/**
 * @author USer
 *
 */
public interface CommonConstants {

	public static final String OTP_PAGE_DETAILS = "/epayment/otp";
	public static final String ENTRY_PAGE_DETAILS = "/epayment/entry";
	public static final String ENTRY_PAGE_SAVED_CARD_HISTROY_DETAILS = "/getSavedCardHistory";
	public static final String ENTRY_PAGE_TRANSACTION_HISTORY_PER_LOGIN_USER_DETAILS = "/getTransactionHistoryPerLoginUser";
	public static final String ENTRY_PAGE_WALLET_HISTROY_DETAILS = "/getewallethistory";
	public static final String ENTRY_PAGE_EWALLET_BALANCE = "/getInitBalance";
	public static final String ENTRY_PAGE_EWALLET_TRANSACTION_TIMER = "/otptransactiontimer";
	public static final String ENTRY_PAGE_EWALLET_SSO_DETAILS = "/getEwalletSSODetails";
	public static final String ENTRY_PAGE_PARAM_CC = "ccDataKey";
	public static final String ENTRY_PAGE_PARAM_TbticektID = "tbticketid";
	public static final String QUICK_ENTRY_PAGE = "/quickPay";
	public static final String VALIDATION_PAGE_DETAILS = "/epayment/validation/validationentry";
	public static final String VALIDATION_PAGE_DETAILS_EXPIRY = "/epayment/validation/validationexpiryentry";
	public static final String UPDATE_CARD_PAGE_DETAILS = "/epayment/validation/updatecardentry";
	public static final String UPDATE_CARD_PAGE_DETAILS_EXPIRY = "/epayment/validation/updateexpirycardentry";
	public static final String CHANGE_RECURRING_CARD_PAGE_DETAILS = "/epayment/recurringEntry";
	public static final String VALIDATION_PAGE_INIT_PAYMENT = "/epayment/validation/initPayment";
	public static final String VALIDATION_PAGE_UPDATE_CARD = "/epayment/validation/initUpdateCard";
	public static final String ENTRY_PAGE_PARAM_WALLET_ID = "walletrefid";
	public static final String ENTRY_PAGE_PARAM_TRANSACTION_ID = "refid";
	public static final String INIT_PAYMENT = "/initpayment";
	public static final String RECURRING_INIT_PAYMENT = "/epayment/validation/recurringinitpayment";
	public static final String VALIDATE_CREATE_TRANSACTION = "/validate/createtransaction";
	public static final String VALIDATE_GET_PROVIDER_KEY_LIST = "/validate/getproviderkeylist";
	public static final String VALIDATE_CARD_DETAILS = "/validate/validatecarddetails";
	public static final String VALIDATE_RECURRING_DETAILS = "/validate/validaterecurring";
	public static final String VERIFY_PAYMENT_DETAILS_MBB = "/verifyPaymentDetails/MBB";
	public static final String VERIFY_PAYMENT_DETAILS_CTB = "/verifyPaymentDetails/CTB";
	public static final String VERIFY_PAYMENT_DETAILS_CTB_RECURRING = "/verifyPaymentDetails/CTBRecurring";
	public static final String VERIFY_PAYMENT_DETAILS_FPX = "/verifyPaymentDetails/FPX";
	public static final String VERIFY_PAYMENT_DETAILS_PBB = "/verifyPaymentDetails/PBB";
	public static final String VERIFY_PAYMENT_DETAILS_EWALLET = "/verifyPaymentDetails/eWallet";
	public static final String SUBMIT_TRANSACTION = "/submitTransaction";
	public static final String DELETE_TRANASACTION = "/deleteTransaction";
	public static final String CANCEL_PAYMENT = "/cancelpayment";
	public static final String EWALLET_CANCEL_PAYMENT = "/ewalletcancelpayment";
	public static final String CANCEL_ADD_PAYMENT = "/canceladdpayment";
	public static final String CANCEL_UPDATE_PAYMENT = "/cancelupdatepayment";
	public static final String PROCESS_RECURRING_TRANSACTION = "/processrecurring";
	public static final String TRANSACTION = "/transaction";
	public static final String CREATE_TRANSACTION = "/createTransaction";
	public static final String MBB_TRANSACTION_CASA = "/MayBankCasa";
	public static final String FPX_TRANSACTION = "/fpxTransaction";
	public static final String GET_RECURRING_SCHEDULED_TRANSACTIONS = "/getRecurringTransactions";
	public static final String GET_TRANSACTION_RESULT = "/GetTransactionResult";
	public static final String GET_TRANSACTION_DETAILS = "/GetTransactionDetails";
	public static final String GET_CREDITCARD_LIST_VALIDATE = "/validate/GetCreditCardListValidate";
	public static final String ADD_NEW_CREDIT_CARD_VALIDATE = "/validate/AddNewCreditCard";
	public static final String UPDATE_CREDIT_CARD_VALIDATE = "/validate/UpdateCreditCardExpiryDate";
	public static final String REMOVE_CREDIT_CARD_VALIDATE = "/validate/RemoveCreditCard";
	public static final String CHECK_TRANSACTION = "/checkTransactionForProviderKey";
	public static final String CHECK_CHANNEL = "/checkTransactionForChannelKey";
	public static final String SECRET_FACTORY = "PBEWithMD5AndDES";
	public static final String MANAGE_CARD_SUCCESS_RESULT = "S";
	public static final String MANAGE_CARD_SUCCESS_RESULT_CODE = "01";
	public static final String MANAGE_CARD_FAILURE_RESULT = "F";
	public static final String MANAGE_CARD_SUCCESS_MESSAGE = "Success. Input data matches.";
	public static final String ADD_NEW_CARD_SUCCESS_MESSAGE = "Success. Add new card request successfully.";
	public static final String UPDATE_CARD_SUCCESS_MESSAGE = "Success. Update card request.";
	public static final String REMOVE_CARD_SUCCESS_MESSAGE = "Success. Removed the card from favorite list.";
	public static final String COMMON_RESPONSE_LANGUAGE = "Eng";
	public static final String TRANSACTION_TIMER = "/transactiontimer";
	public static final String GET_LIST_CREDIT_CARD_CONTROLLER = "/apg/v2/api/transaction/getlistofpayeecreditcard";
	public static final String ADD_NEW_CREDIT_CARD_CONTROLLER = "/apg/v2/api/transaction/addnewcreditcard";
	public static final String UPDATE_CREDIT_CARD_CONTROLLER = "/apg/v2/api/transaction/updatecreditcardexpirydate";
	public static final String REMOVE_CREDIT_CARD_CONTROLLER = "/apg/v2/api/transaction/removecreditcard";
	public static final String QUICK_PAY_TRANSACTION = "/quickPay/createTransaction";
	public static final String QUICK_PAY_CSC_TRANSACTION = "/quickPayCsc/createTransaction";
	public static final String KEY_CHECK = "/keyCheck";
	public static final String DYNAMIC_PAY = "/dynamicPay";
	public final String USER_AGENT_KEY = "User-Agent";
	public final String USER_AGENT_VALUE = "apg";
	public static final String ADD_FAV_CARD_IND = "AddCard";
	public static final String RECURRING_CHANGE_CARD = "RecurringChange";
	public static final String UPDATE_FAV_CARD_IND = "UpdateCard";
	public static final String TOKEN_ID_PARAM = "tokenId";
	public static final String DYNAMIC_PAY_TRANSACTION = "/dynamicPay/createTransaction";
	public static final String CAMPAIGN = "/campaign";
	public static final String CC_EXPIRY_REMINDER = "/remindccexpiry";
	public static final String FAV_EXPIRY_REMINDER = "/remindfavexpiry";
	public static final String CAMPAIGN_TRANSACTION = "/campaign/createTransaction";
	public static final String GET_QUICK_PAY_CONFIG = "/quickPay/getConfig";
	public static final String GET_QUICK_PAY_CSC_CONFIG = "/quickPayCsc/getConfig";
	public static final String GET_DYNAMIC_PAY_CONFIG = "/dynamicPay/getConfig";
	public static final String GET_SCHEDULAR_SETTINGS = "/getSchedularSettings/{type}";
	public static final String GET_SUBSCRIPTION_DETAILS = "/getSubscriptionDetails";
	public static final String UPDATE_CARD = "/updateCard";
	public static final String UPDATE_CARD_KEY = "key";
	public static final String GET_TRANSACTION_REF_ID_FOR_CC_DATA_KEY = "/getTransRefId";
	public static final String GET_TRANSACTION_PAYMENT_DETAILS = "/GetTransactionPaymentDetails";
	public static final String CASA_DB_CHECK = "/casaCheck";
	public static final String PAYMENT_REF_ID = "paymentRefId";
	public static final String VERIFY_PAYMENT_DETAILS_MBB_CASA = "/verifyPaymentDetails/MBBCASA";
	public static final String REFERENCE_ID = "refId";
	public static final String RESULT = "/result";
	public static final String ERROR_PAGE = "/errorpage";
	public static final String GET_RESULT_PROCESSED_TRANSACTION = "/getResult";
	public static final String CHECK_PARTNER = "/checkTransactionForPartnerKey";
	public static final String CANCEL_RECURRING_TRANSACTION = "/api/apg/CancelRecurringTransaction";
	public static final String CANCEL_RECURRING = "/cancelRecurring";
	public static final String GET_CARDS = "/getcards/{paymentId}";
	public static final String GET_CAMPAIGN_PAY_CONFIG = "/getCampaignPayConfig";
	public static final String RESULT_FOR_BLACKLIST = "/getResultForBlacklist";
	public static final String BLACKLIST_PAYMENT = "/getResult";
	public static final String TRACK_DROP_OFF = "/trackDropOff";

	public static final String INTERNAL_CONFIRMATION_PAGE_DETAILS = "/confirmation/status";

	public static final String GET_RECURRING_ACCESS_TOKEN = "/getRecurringAccessToken";
	public static final String GET_CC_DETAILS = "/getCCdetails/{token}";
	public static final String GET_CC_DETAILS_EMAIL = "/getCCdetails/email/{token}";
	public static final String GET_CC_DETAILS_BY_USER = "/getCCdetails/{user}";
	public static final String GET_CC_DETAILS_BY_TXN = "/getCCdetailsByTxn/{id}";
	public static final String GET_TXN_BY_CC_DATA_KEY = "/getTxnByCcDataKey/{ccDataKey}/{expirytype}";
	public static final String CHECK_TRANS_STATUS = "/checkRecurringStatus";
	public static final String INIT_CHANGE = "/initChange";
	public static final String INIT_CHANGE_EMAIL = "/initEmailChange/{token}/{datakey}";
	public static final String CHANGE_RECURRING_DETAILS = "/changeRecurringDetail";
	public static final String CHANGE_RECURRING_CREDIT_CARD = "/changeRecurringCreditCard";
	public static final String ERROR_IN_RECURRING = "Error in generating recurring access token";
	public static final String CANCEL_RECURRING_PAYMENT = "/cancelrecurringpayment";
	public static final String CANCEL_UPDATE_RECURRING_PAYMENT = "/cancelupdaterecurringpayment";
	public static final String GET_PARTNER_SECRET = "/getpartnersecret";
	public static final String EWALLET_COMPLETECONSUME_DETAILS = "/completeconsumebalance";
	public static final String EWALLET_CANCELBACK_TIMEOUT = "/cancelbacktimeout";

	public static final String EWALLET_CONSUMEBAL_TRANSACTION_ID = "/wrefid";
	public static final String EWALLET_PAYMENT_DETAILS = "/ewalletpaymentdetails";
	public static final String EWALLET_PAYMENT_SUCCESS = "/bankResultEwallet";
	public static final String RESEND_OTP_BY_PUID = "/resendotpbypuid";
	public static final String RESEND_OTP_VIA_EMAIL = "/resendotpviaemail";
	public static final String RESEND_OTP_VIA_EMAIL_COUNTER = "/resendotpviaemailcounter";
	public static final String VIA = "via";
	public static final String RESEND_PARAM = "resend";
	public static final String GET_WALLET_INFORMATION = "/getwalletinformation";
	public static final String GET_GLOBAL_SETTING = "/getglobalsetting";
	public static final String REVERSE_CONSUME_BALANCE = "/reverseconsumebalance";
	public static final String UPDATE_GLOBAL_SETTING = "/updateglobalsetting";
	public static final String TIMEZONE_ASIA_SINGAPORE = "Asia/Singapore";
	public static final String TIMEZONE_ASIA_KUALALUMPUR = "Asia/Kuala_Lumpur";
}
