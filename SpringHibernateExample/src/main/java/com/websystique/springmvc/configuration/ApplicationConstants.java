/**
 * 
 */
package com.websystique.springmvc.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author USer
 *
 */
public class ApplicationConstants {

	public static final String GST_PERCENT_KEY = "gst_percent";

	public static final String DEFAULT_GST_PERCENT = "6%";

	public static final String EPP_CARD_VALIDATION_ERROR = "We currently do not accept %s card for Easy payments for %d months. Please choose another tenure or try again with another Credit Card type.";

	public static final String SINGLE_CARD_VALIDATION_ERROR = "We currently do not accept %s card for this payment. Please use another card";

	public static final String TRANSACTION_EPP = "e";

	public static final String INSTALLMENT_PLAN_CODE = "InstallmentPlanCode";

	public static final String TERM_MONTH = "TermMonth";

	public static final String EPP = ".epp";

	public static final String CARD_VALIDATION_FAILED = "CARD VALIDATION FAILED";

	public static final String CARD_BLACKLISTED = "CARD_BLACKLISTED";

	public static final String BINLIST_VALIDATION_FAILED = "This card is not supported. Please use another credit card.";

	public static final String BLACKLIST_VALIDATION_FAILED = "This card has been blacklisted and cannot be used. Please use another credit card.";

	public static final String PAYMENT_STATUS_SUCCESSFUL = "Successful";

	public static final String PAYMENT_STATUS_UNSUCCESSFUL = "Unsuccessful";

	public static final String PAYMENT_STATUS_INVESTIGATION = "Investigation";

	public static final String PAYMENT_STATUS_BLACKLISTED = "Blacklisted";

	public static final String MERCHANT_PASS = "Merchantpswd";

	public static final String PAYMENT_TYPE_MOTO = "moto";

	public static final String MERCHANT_ID = "MerchantID";

	public static final String MERCHANT_URL = "Merchanturl";

	public static final String EMOTO = ".emoto";

	public static final String EMOTO_EPP = ".emotoepp";

	public static final String MERCHANT_EMOTO_URL = "MerchantEmotourl";

	public static final String TRANSACTION_RECURRING = "r";

	public static final String TRANSACTION_SINGLE = "s";

	public static final Integer CREATE_SUBMIT_TRANSACTION_SUCCESSFUL_CODE = 200;

	public static final String CREATE_SUBMIT_TRANSACTION_SUCCESSFUL_MESSAGE = "VALIDATION SUCCESS";

	public static final String MBB = "mbb";

	public static final String CTB = "ctb";

	public static final String PBB = "pbb";

	public static final String CURRENCY_MYR = "myr";

	public static final String CURRENCY_SGD = "sgd";

	public static final Long MYR_CARD_VALIDATION_AMOUNT = 5L;

	public static final Long SDG_CARD_VALIDATION_AMOUNT = 1L;

	public static final String CARD_VALIDATION_MESSAGE = "Authorize %s for the subscription payment of %s %s. We will do a %s %s charge and reversal to verify your card.";

	public static final String INITIAL_PAYMENT_MESSSAGE = "Authorize %s for the subscription payment of %s %s towards the initial payment. Your subscription will be charged at %s %s.";

	public static final String CHARGE_AMOUNT_MESSAGE = "Authorize %s for subscription payment of %s %s";

	public static final String SUBMIT_INFO = "This is for test purpose";

	public static final String CREDITCARD = "cc";

	public static final String RECURRING_CYCLE_PERIOD_DAY = "Day";

	public static final String RECURRING_CYCLE_PERIOD_MONTH = "Month";

	public static final String MBB_CASA_ACTION = "Login";

	public static final String DIRECT = ".direct";

	public static final Object DOLLAR_SYMBOL = "$";

	public static final Object AMOUNT_BIT = "1";

	public static final Object REFERENCE_BIT = "1";

	public static final Object ACCOUNT_BIT = "1";

	public static final String SALT = "Maybank2u simple encryption";

	public static final String SUBSCRIBE_SERVICE = "OT";

	public static final String FUTURE_DOWNTIME_MESSAGE = "Maintenance announcement.<br><b>%s</b> will be undergoing a scheduled maintenance <b>from %s to %s.</b> Between this window you will not be able to make any payments.<br><br>Thanks,<br> Astro Team. ";

	public static final String CURRENT_DOWNTIME_MESSAGE = "Credit & Debit card payment services currently unavailable.<br>Our system is currently in maintenance until 08:21PM, 13 Jun 2017. Please try again later.<br>Please try again later after the maintenance window or you can choose another payment method below.";

	public static final String MBB_DIRECT = "mbb.direct";

	public static final String PAYMENT_STATUS_PENDING = "Pending";

	public static final Long MAYBANK_PENDING_CODE = 1005L;

	public static final Long MAYBANK_PENDING_CODE2 = 4003L;

	public static final Long MAYBANK_SUCCESS_CODE = 0L;

	public static final Object SELLER_ID = "SellerID";

	public static final Object CHECKSTATUS_URL = "CheckStatusURL";

	public static final Object EXCHANGE_ID = "ExchangeID";

	public static final String ACQUIRED_ID = "AcquiredId";

	public static final String SECRET_FACTORY = "PBEWithMD5AndDES";

	public static final String PAYMENT_ID = "?paymentId=";

	public static final String REDIRECT = "redirect:";

	public static final Object MERCHANT_USER_ID = "MerchantUserID";

	public static final String AR_TYPE = "AR";

	public static final String AR_MESSAGE_TOKEN = "01";

	public static final String SELLER_BANK_CODE = "01";

	public static final String TRANSACTION_CURRENCY = "MYR";

	public static final String PRODUCT_DESCRIPTION_FPX = "test";

	public static final String FPX_VERSION = "6.0";

	public static final String SIGNATURE_ALG_FPX = "SHA1withRSA";

	public static final String BE_TYPE = "BE";

	public static final String BE_MESSAGE_TOKEN = "01";

	public static final String PVT_KEY_FILE_NAME = "D:\\E\\APG\\APG\\FPX_Demo\\FPX_SMI\\EX00003788.key";

	public static final String PVT_KEY_FILE_NAME_BANK = "C:\\FPXKey";

	public static final String FPX_BANKLIST = "fpx_bankList";

	public static final String PBB_REFUND_URL = "https://uattds2.pbebank.com/PGW/Pay/Refund";

	public static final String PBB_CHECKSTATUS_URL = "https://uattds2.pbebank.com/PGW/Pay/Check";

	public static final String SECRET_CODE = "PBBSECRET";

	public static final String PBB_ENCRYPTION_ALGO = "SHA1";

	public static final String PBB_BANK = "PBB";

	public static final int PBB_LENGTH = 20;

	public static final String PBB_INVOICE_CONSTANT = "PBBBANKREQUEST";

	public static final String FPX_APPROVED_DEBITCODE = "00";

	public static final String[] FPX_PENDING_CODE = { "16", "17", "18", "19", "20", "21", "22", "23", "39", "40",
			"98" };

	public static final List<String> FPX_PENDING_DEBITCODE = new ArrayList<>(Arrays.asList(FPX_PENDING_CODE));

	public static final String PAYMENT_STATUS_CANCELLED = "cancelled";

	public static final String PAYMENT_STATUS_EXPIRED = "stopped";

	public static final String PBB_APPROVED_CODE = "00";

	public static final Long FPX_APPROVED_DEBITCODE_DB = 0L;

	public static final int PBB_TERMMONTH_LENGTH = 2;

	public static final String FPX_DIRECT = "fpx.direct";

	public static final String Ewallet = "apg.wallet";
	
	public static final String MBB_RESPONSE_TYPE = "xml";

	public static final String MBB_TRANS_TYPE_REF = "7";

	public static final String MBB_TRANS_TYPE_REV = "6";

	public static final Object CITI_BANK_APPROVED_RESPONSE_CODE = "1";

	public static final String START_DATE = "startdate";

	public static final String END_DATE = "enddate";

	public static final String ORIGIN_PLATFORM = "origin_platform";

	public static final String COST_CENTRE = "costcentre";

	public static final String PRICE = "price";

	public static final String USER_ID = "userid";

	public static final String CURRENCY = "currency";

	public static final String DURATION = "duration";

	public static final String PAYMENT_TYPE = "paymenttype";

	public static final String ASSET_TITLE = "assettitle";

	public static final String CHARGE_CODE = "chargecode";

	public static final String PORTAL_USER_ID = "portaluserid";

	public static final String PAYMENT_METHOD = "paymentmethod";

	public static final String ASSET_ID = "assetid";

	public static final String GST_PRICE = "gst_price";

	public static final String TOTAL_PRICE = "total_price";

	public static final String MBB_TRANS_TYPE_QUERY = "1";

	public static final String AE_TYPE = "AE";

	public static final String AE_MESSAGE_TOKEN = "01";

	public static final Object CITI_BANK_PENDING_RESPONSE_CODE = "40";

	public static final Object MBB_CASA_SUCCESS = "00";

	public static final Long MBB_SINGLE_SUCCESS = 0L;

	public static final Integer PAYMENT_PENDING = 2;

	public static final Integer RETRY_COUNT = 3;

	public static final long HOURS_MILLISECONDS = 86400000;

	public static final Long MAYBANK_PENDING_CODE3 = 3009L;

	public static final String[] PBB_PENDING_CODE = { "06", "19", "96", "99" };

	public static final List<String> PBB_PENDING = new ArrayList<>(Arrays.asList(PBB_PENDING_CODE));

	public static final String EMAIL_BLACKLISTED = "EMAIL BLACKLISTED/UNDER INVESTIGATION";

	public static final String PARTNERUSERKEY_BLACKLISTED = "PARTNER USERKEY BLACKLISTED/UNDER INVESTIGATION";

	public static final String LOGINUSERID_BLACKLISTED = "LOGIN USERID BLACKLISTED/UNDER INVESTIGATION";

	public static final String IP_BLACKLISTED = "IP BLACKLISTED/UNDER INVESTIGATION";

	public static final String DISCOUNT_INFO_NOT_FOUND = "Discount error";

	public static final String PAYMENT_UNSUCCESSFUL_MESSAGE_DESC = "Payment+unsuccessful";
	public static final String PAYMENT_SUCCESSFUL_MESSAGE_DESC = "Payment+is+successful";
	public static final String PAYMENT_PENDING_MESSAGE_DESC = "Payment+is+Pending";
	public static final String RESULT_PAGE_TIME_FORMAT = "dd-MMM-yyyy hh:mm:ss a";
	public static final String RESULT_PAGETIME_ZONE = "Asia/Kuala_Lumpur";

	public static final String REFERENCE_TYPE1 = "1";

	public static final String REFERENCE_TYPE2 = "2";

	public static final String REFERENCE_TYPE3 = "3";

	public static final String REFERENCE_TYPE4 = "4";

	public static final String TRANSACTION_TYPE_VERIFIED = "v";

	public static final String TRANSACTION_TYPE_3D = "3d";

	public static final String FIND_CHANNEL_BY_KEY_AND_CURRENCY = "SELECT channel.* FROM APG_Channel channel inner join"
			+ " APG_ChannelCurrency channelCurr on channel.ChannelID = channelCurr.ChannelID "
			+ " inner join APG_Partner partner on channel.PartnerID = partner.PartnerID"
			+ "	inner join APG_Currency curr  on channelCurr.CurrencyID=curr.CurrencyId where"
			+ " channel.ChannelKey = ?1 AND curr.ThreeLetterISOCode = ?2  AND partner.PartnerKey= ?3 AND channel.Status=1";

	/**
	 * 
	 */
	private ApplicationConstants() {
		super();
	}

}
