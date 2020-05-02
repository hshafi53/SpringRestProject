package com.websystique.springmvc.configuration;

public class ValidationEnums {

	private ValidationEnums() {

	}

	public static enum TransactionValidationCodes {

		SUCCESS(1), REQUESTBODY_OR_SIGNATURE_EMPTY(102), INVALID_PARTNER(112), SIGNATURE_MISMATCH(108), SYSTEM_DOWNTIME(
				116), REQUEST_TIMEOUT(101), INVALID_CHANNEL(218), MISSING_MANDATORY_FIELD(109), INVALID_PARTNER_ACCESS(
						112), INVALID_RESPONSEURL(207), INVALID_PARTNERREFNO(231), INVALID_TITLE(
								232), INVALID_CURRENCYEXP(234), INVALID_ITEMTOTAL(
										235), INVALID_TOTALAMOUNT(236), TECHNICAL_ERROR_HAS_OCCURED(
												106), DUPLICATE_PARTNERREF_NO(233), INVALID_TRANSACTION_TYPE(
														230), INVALID_TOTAL_ITEM_VALUE(219), INVALID_PARTNERUSERKEY(
																125), INVALID_ITEMNAME(220), INVALID_ITEM_QUANTITY(
																		221), INVALID_ITEMUNIT_PRICE(
																				222), INVALID_CUSTOMER_DETAIL(
																						239), INVALID_CUSTDETAIL_PROFILE_KEY(
																								223), INVALID_CUSTDETAIL_PROFILE_VALUE(
																										224), INVALID_EXTRADATA_KEY(
																												225), INVALID_EXTRADATA_VALUE(
																														226), INVALID_ITEM_TOTAL_NOT_TALLY(
																																227), INVALID_TAX_AMOUNT(
																																		212), INVALID_SHIPPING_AMOUNT(
																																				211), EXTRA_DATA_INFO_EMPTY(
																																						240), INVALID_CURRENCY_CODE(
																																								229), INVALID_TOTAL(
																																										300), ADDITIONAL_CHARGES_EMPTY(
																																												301), INVALID_ADDITIONALCHARGES_KEY(
																																														302), INVALID_ADDITIONALCHARGES_VALUE(
																																																303), INVALID_MONTHTERM(
																																																		205), PAYMENT_PROVIDER_DOWNTIME(
																																																				129), EPPINFO_RECURRING_COEXIST(
																																																						242), INVALID_AGENTID(
																																																								237), CC_EXPIRED(
																																																										119), EMPTY_CCINFO(
																																																												120), CC_NOTFOUND_IN_BINLIST(
																																																														123), INVALID_NOTIFICATION_URL(
																																																																208), INVALID_CURRENCY_EXP(
																																																																		209), INVALID_CURRENCYCODE(
																																																																				210), INVALID_SHIPPINGAMOUNT(
																																																																						211), INVALID_TAXAMOUNT(
																																																																								212), INVALID_CHARGEAMOUNT(
																																																																										213), INVALID_INITIALAMOUNT(
																																																																												214), INVALID_FREQUENCY(
																																																																														215), INVALID_CYCLEPERIOD(
																																																																																216), INVALID_RECURRINGINFO(
																																																																																		217), EXCEEDED_DAILY_MAX_VALUE(
																																																																																				121), EXCEEDED_LIMIT_OF_TRANSACTIONS(
																																																																																						122), INVALID_PAYMENT_TYPE(
																																																																																								244), CARDTYPE_NOT_SUPPORTED(
																																																																																										245), CASA_NOT_SUPPORTED_FOR_EMOTO(
																																																																																												241), CCINFO_INVALID_NAME(
																																																																																														246), CCINFO_INVALID_NUMBER(
																																																																																																254), CCINFO_INVALID_CC_TYPE(
																																																																																																		248), CCINFO_INVALID_CVV(
																																																																																																				249), CCINFO_INVALID_EXPIRY_MONTH(
																																																																																																						250), CCINFO_INVALID_EXPIRY_YEAR(
																																																																																																								251), INVALID_CHARGE_AMOUNT(
																																																																																																										252), INVALID_CHANNEL_PROVIDER(
																																																																																																												253), CARD_BLACKLISTED(
																																																																																																														124), RECURRING_EMOTO_NOT_VALID(
																																																																																																																		105),INVALID_TBT_TICKET_ID(
																																																																																																																		312), INVALID_TRANSACTIONID(
																																																																																																																		105), PENDING_PAYMENT(
																																																																																																																				201), PENDING_RECURRING(
																																																																																																																						202), ONLY_SUCCESS_RECURRING(
																																																																																																																								203), ONLY_RECURRING(
																																																																																																																										204), CARD_BLAKLISTED_INVESTIGATION(
																																																																																																																												130), EMAIL_BLACKLISTED(
																																																																																																																														131), PARTNERUSERKEY_BLACKLISTED(
																																																																																																																																132), LOGINUSERID_BLACKLISTED(
																																																																																																																																		133), IP_BLACKLISTED(
																																																																																																																																				134), INVALID_REFERNECE_TYPE(
																																																																																																																																						255), INVALID_REFERNECE_ID(
																																																																																																																																								256), INVALID_PAGESIZE(
																																																																																																																																										257), INVALID_PAGENO(
																																																																																																																																												258);

		private int responseCode;

		TransactionValidationCodes(int responseCode) {
			this.responseCode = responseCode;
		}

		public int getResponseCode() {
			return responseCode;
		}

	}

}