
package com.websystique.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.request.dto.NewNEFTRequestDto;
import com.response.dto.ValidationResponseDto;
import com.websystique.springmvc.configuration.ValidationEnums.TransactionValidationCodes;
import com.websystique.springmvc.model.TransactionUser;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	private ApplicationContext appContext;

	@Override
	public com.response.dto.ValidationResponseDto validateCreateTransaction(
			com.request.dto.CreateTransactionRequestDto createTransactionRequestDto) {

		System.out.println("inside validationservice");
		try {

			if (createTransactionRequestDto.getCreateTransactionBody() == null) {

				return new com.response.dto.ValidationResponseDto(
						com.websystique.springmvc.configuration.ValidationEnums.TransactionValidationCodes.REQUESTBODY_OR_SIGNATURE_EMPTY
								.getResponseCode(),
						"No request found");
			}
			if

			(createTransactionRequestDto.getCreateTransactionBody().getCurrencyCode() == "") {
				return new ValidationResponseDto(TransactionValidationCodes.INVALID_CURRENCYEXP.getResponseCode(),
						"Invalid currency");
			}

			if ((createTransactionRequestDto.getCreateTransactionBody().getTransType() == "")) {
				return new ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTION_TYPE.getResponseCode(),
						"payment type invalid or not found");
			}
			if ((createTransactionRequestDto.getCreateTransactionBody().getPaymentType() == "")) {
				return new ValidationResponseDto(TransactionValidationCodes.INVALID_PAYMENT_TYPE.getResponseCode(),
						"payment type invalid or not found");
			}
			//

			System.out.println("all checks validated");
			return new ValidationResponseDto(TransactionValidationCodes.SUCCESS.getResponseCode(),
					"Transaction request successful");

		} catch (Exception exception) {

			return new ValidationResponseDto(TransactionValidationCodes.TECHNICAL_ERROR_HAS_OCCURED.getResponseCode(),
					"Technical error occured");

		}
	}

	// validateNewNEFTRequest
	public com.response.dto.ValidationResponseDto validateNewNEFTRequest(NewNEFTRequestDto neftreqdto,
			TransactionUser valsend, TransactionUser valbene) {
		
		System.out.println("sender name from DB--------------------------"+valsend.getCustomername());
		System.out.println("sender name from user--------------------------"+neftreqdto.getCreateTransactionBody().getBeneficiary_Name());

		try {
			if ((neftreqdto.getCreateTransactionBody().getSender_Name() == "")
					|| (!valsend.getCustomername().equals(neftreqdto.getCreateTransactionBody().getSender_Name()))) {
				return new ValidationResponseDto(101, "Sender Name not found or invalid Sender");
			}
			if ((neftreqdto.getCreateTransactionBody().getSender_Accountno() == 0)
					|| neftreqdto.getCreateTransactionBody().getSender_Accountno() <= 8) {
				return new ValidationResponseDto(102, "Sender Account Number empty or length not matched");
			}
			if (!valsend.getAccount_no().equals(neftreqdto.getCreateTransactionBody().getSender_Accountno())) {
				return new ValidationResponseDto(112, "Sender Account Number invalid");
			}
			if (!valsend.getAccount_type().equals(neftreqdto.getCreateTransactionBody().getSender_Accounttype()) || (neftreqdto.getCreateTransactionBody().getSender_Accounttype() == "")) {
				return new ValidationResponseDto(103, "Sender Account Type not found or invalid");
			}
			if (!valsend.getIfsc().equals(neftreqdto.getCreateTransactionBody().getSender_IFSC())||(neftreqdto.getCreateTransactionBody().getSender_IFSC() == "")) {
				return new ValidationResponseDto(104, "Sender IFSC not found or invalid");
			}
			if (!valbene.getCustomername().equals(neftreqdto.getCreateTransactionBody().getBeneficiary_Name())||(neftreqdto.getCreateTransactionBody().getBeneficiary_Name() == "")) {
				return new ValidationResponseDto(105, "Beneficiary Name not found or invalid");
			}
			if ((neftreqdto.getCreateTransactionBody().getBeneficiary_Accountno() == 0)||!valbene.getAccount_no().equals(neftreqdto.getCreateTransactionBody().getBeneficiary_Accountno())) {
				return new ValidationResponseDto(106, "Beneficiary Account Number invalid or empty");
			}
			if ((neftreqdto.getCreateTransactionBody().getBeneficiary_Accounttype() == "")) {
				return new ValidationResponseDto(107, "Beneficiary Account Type not found");
			}
			if ((neftreqdto.getCreateTransactionBody().getBeneficiary_IFSC() == "")) {
				return new ValidationResponseDto(108, "Beneficiary IFSC not found or invalid");
			}
			if ((neftreqdto.getCreateTransactionBody().getAMOUNT() == 0)) {
				return new ValidationResponseDto(109, "Amount invalid");
			}

			System.out.println("all checks validated in NEFT Request");
			return new ValidationResponseDto(TransactionValidationCodes.SUCCESS.getResponseCode(),
					"NEFT request successful");

		} catch (Exception exception) {

			return new ValidationResponseDto(TransactionValidationCodes.TECHNICAL_ERROR_HAS_OCCURED.getResponseCode(),
					"Technical error occured");

		}
	}

	/*
	 * private boolean validateProvider1(CreateTransactionRequestDto
	 * createTransactionRequestDto, APGChannel channel) {
	 * 
	 * boolean validateFlag = false; boolean isEppFlag = false; boolean eppFlag =
	 * false;
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getTransType()
	 * .equalsIgnoreCase(ApplicationConstants.TRANSACTION_EPP)) { eppFlag = true; }
	 * 
	 * String defaultProviderKey = channel.getDefaultProviderKey();
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymentType().
	 * equals("") && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EPP; }
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymentType()
	 * .equals(ApplicationConstants.PAYMENT_TYPE_MOTO) && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else if
	 * (createTransactionRequestDto.getCreateTransactionBody().getPaymentType()
	 * .equals(ApplicationConstants.PAYMENT_TYPE_MOTO)) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO; }
	 * 
	 * List<APGChannelProvider> channelProviderList =
	 * channel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) {
	 * 
	 * String termMonth = ""; String planCode = "";
	 * 
	 * if (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus()) {
	 * 
	 * if (eppFlag &&
	 * createTransactionRequestDto.getCreateTransactionBody().getEppinfo() != null)
	 * {
	 * 
	 * List<APGChannelProviderMetaData> metaDataList =
	 * channelProvider.getChannelProvidermetaData();
	 * 
	 * for (APGChannelProviderMetaData metaData : metaDataList) {
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); } if
	 * (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.
	 * INSTALLMENT_PLAN_CODE)) { planCode = metaData.getDataValue(); }
	 * 
	 * if (!termMonth.isEmpty() && !planCode.isEmpty() &&
	 * metaData.getChannelProvider().getChannelProviderId() == channelProvider
	 * .getChannelProviderId() &&
	 * (termMonth.equals(createTransactionRequestDto.getCreateTransactionBody()
	 * .getEppinfo().getMonthterm().toString()))) {
	 * 
	 * isEppFlag = true; } } } else { eppFlag = false; }
	 * 
	 * List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList(); for
	 * (APGChannelProviderCurrency currency : channelProviderCurrencyList) {
	 * 
	 * if ((currency.isStatus()) && (currency.getMinPerTrxValue() == 0 &&
	 * currency.getMaxPerTrxValue() == 0 &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode()))) {
	 * 
	 * validateFlag = true; } else
	 * 
	 * if ((currency.isStatus()) && (currency.getMinPerTrxValue() <=
	 * createTransactionRequestDto .getCreateTransactionBody().getTotalAmount() &&
	 * createTransactionRequestDto.getCreateTransactionBody() .getTotalAmount() <=
	 * currency.getMaxPerTrxValue()) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode())) {
	 * 
	 * validateFlag = true; }
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * if (eppFlag) {
	 * 
	 * return validateFlag && isEppFlag;
	 * 
	 * } else {
	 * 
	 * return validateFlag;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Override public boolean checkIP(String ipAddress, BlacklistRule blacklisted)
	 * {
	 * 
	 * String[] ipAddArray = ipAddress.split(",");
	 * 
	 * for (int i = 0; i < ipAddArray.length; i++) {
	 * 
	 * boolean flag = blackListRuleDao.checkIpForBlackListRule(ipAddArray[i],
	 * blacklisted.ordinal());
	 * 
	 * if (flag) {
	 * 
	 * return true; } }
	 * 
	 * return false; }
	 * 
	 * private boolean checkPartnerUserKeyForBlackListRule(String partneruserkey,
	 * Long partnerId, BlacklistRule blacklisted) {
	 * 
	 * return blackListRuleDao.checkPartnerUserKeyForBlackListRule(partneruserkey,
	 * partnerId, blacklisted.ordinal()); }
	 * 
	 * @Override public boolean checkEmailForBlackListRule(String emailId,
	 * BlacklistRule blacklisted) {
	 * 
	 * return blackListRuleDao.checkEmail(emailId, blacklisted.ordinal());
	 * 
	 * }
	 * 
	 * @Override public boolean checkSSOBlacklist(ValidateSSOResponseBody
	 * validateSSOResponseBody, BlacklistRule blacklisted) {
	 * 
	 * if (validateSSOResponseBody.getEmail() != null) {
	 * 
	 * boolean ssoEmailFlag =
	 * blackListRuleDao.checkSSO(validateSSOResponseBody.getEmail(),
	 * blacklisted.ordinal());
	 * 
	 * if (ssoEmailFlag) { return ssoEmailFlag; } }
	 * 
	 * if (validateSSOResponseBody.getUserName() != null) {
	 * 
	 * boolean ssoUserNameFlag =
	 * blackListRuleDao.checkSSO(validateSSOResponseBody.getUserName(),
	 * blacklisted.ordinal());
	 * 
	 * if (ssoUserNameFlag) { return ssoUserNameFlag; } }
	 * SSOGetSomeUserInfoResponseDto ssoGetSomeUserInfoResponseDto =
	 * checkSSOValidity( validateSSOResponseBody.getPortalUserId());
	 * 
	 * if (ssoGetSomeUserInfoResponseDto != null) {
	 * 
	 * boolean ssoAccountNoFlag = blackListRuleDao.checkSSO(
	 * ssoGetSomeUserInfoResponseDto.getSsoGetSomeUserInfoResponseBody().
	 * getAccountNo(), blacklisted.ordinal());
	 * 
	 * if (ssoAccountNoFlag) { return ssoAccountNoFlag; } }
	 * 
	 * return false; }
	 * 
	 * private boolean validateEppForNoMonthTerm(CreateTransactionRequestDto
	 * createTransactionRequestDto, APGChannel apgChannel) {
	 * 
	 * boolean flag = false, currencyFlag = false;
	 * 
	 * String defaultProviderKey = apgChannel.getDefaultProviderKey();
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymenttype()
	 * .equalsIgnoreCase(ApplicationConstants.PAYMENT_TYPE_MOTO)) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else { defaultProviderKey += ApplicationConstants.EPP; }
	 * List<APGChannelProvider> channelProviderList =
	 * apgChannel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) { if
	 * (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus()) {
	 * 
	 * List<APGChannelProviderMetaData> channelProviderMetaDataList =
	 * channelProvider .getChannelProvidermetaData();
	 * 
	 * if (channelProviderMetaDataList != null &&
	 * !channelProviderMetaDataList.isEmpty()) {
	 * 
	 * flag = true; List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList();
	 * 
	 * for (APGChannelProviderCurrency currency : channelProviderCurrencyList) {
	 * 
	 * if (currency.isStatus() && currency.getMinPerTrxValue() == 0 &&
	 * currency.getMaxPerTrxValue() == 0 &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode())) {
	 * currencyFlag = true; break; } else if ((currency.isStatus()) &&
	 * (currency.getMinPerTrxValue() <= createTransactionRequestDto
	 * .getCreateTransactionBody().getTotalAmount() &&
	 * createTransactionRequestDto.getCreateTransactionBody() .getTotalAmount() <=
	 * currency.getMaxPerTrxValue()) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode())) {
	 * 
	 * currencyFlag = true; break; } } } }
	 * 
	 * } }
	 * 
	 * return flag && currencyFlag; }
	 * 
	 * private boolean validateEmotoForDirect(CreateTransactionRequestDto
	 * createTransactionRequestDto, APGChannel channel) {
	 * 
	 * boolean validateEmotoForDirectFlag = false; boolean eppFlag = false;
	 * 
	 * if (ApplicationConstants.TRANSACTION_EPP
	 * .equalsIgnoreCase(createTransactionRequestDto.getCreateTransactionBody().
	 * getTransType())) { eppFlag = true; }
	 * 
	 * String defaultProviderKey = channel.getDefaultProviderKey();
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymentType().
	 * equals("") && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EPP; }
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymentType()
	 * .equals(ApplicationConstants.PAYMENT_TYPE_MOTO) && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else if
	 * (createTransactionRequestDto.getCreateTransactionBody().getPaymentType()
	 * .equals(ApplicationConstants.PAYMENT_TYPE_MOTO)) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO; }
	 * 
	 * List<APGChannelProvider> channelProviderList =
	 * channel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) { if
	 * (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus()) { if
	 * (channelProvider.getCardType().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCcInfo().getCcType(
	 * )) && channelProvider.getProvider().getProviderType()
	 * .equalsIgnoreCase(ApplicationConstants.CREDITCARD)) {
	 * validateEmotoForDirectFlag = true; break; } } }
	 * 
	 * } return validateEmotoForDirectFlag; }
	 * 
	 * private boolean validateProvider(CreateTransactionRequestDto
	 * createTransactionRequestDto, APGChannel channel) {
	 * 
	 * boolean validateFlag = false; boolean isEppFlag = false; boolean eppFlag =
	 * false;
	 * 
	 * if (ApplicationConstants.TRANSACTION_EPP
	 * .equalsIgnoreCase(createTransactionRequestDto.getCreateTransactionBody().
	 * getTransType())) { eppFlag = true; }
	 * 
	 * String defaultProviderKey = channel.getDefaultProviderKey();
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymentType().
	 * equals("") && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EPP; }
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymentType()
	 * .equals(ApplicationConstants.PAYMENT_TYPE_MOTO) && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else if
	 * (createTransactionRequestDto.getCreateTransactionBody().getPaymentType()
	 * .equals(ApplicationConstants.PAYMENT_TYPE_MOTO)) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO; }
	 * 
	 * List<APGChannelProvider> channelProviderList =
	 * channel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) {
	 * 
	 * String termMonth = ""; String planCode = "";
	 * 
	 * if (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus() &&
	 * channelProvider.getCardType().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCcInfo().getCcType(
	 * ))) {
	 * 
	 * if (eppFlag) {
	 * 
	 * List<APGChannelProviderMetaData> metaDataList =
	 * channelProvider.getChannelProvidermetaData();
	 * 
	 * for (APGChannelProviderMetaData metaData : metaDataList) {
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); } if
	 * (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.
	 * INSTALLMENT_PLAN_CODE)) { planCode = metaData.getDataValue(); }
	 * 
	 * if (!termMonth.isEmpty() && !planCode.isEmpty() &&
	 * metaData.getChannelProvider().getChannelProviderId() == channelProvider
	 * .getChannelProviderId() &&
	 * (termMonth.equals(createTransactionRequestDto.getCreateTransactionBody()
	 * .getEppinfo().getMonthterm().toString()))) {
	 * 
	 * isEppFlag = true; } } }
	 * 
	 * List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList(); for
	 * (APGChannelProviderCurrency currency : channelProviderCurrencyList) {
	 * 
	 * if ((currency.isStatus()) && (currency.getMinPerTrxValue() == 0 &&
	 * currency.getMaxPerTrxValue() == 0 &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode()))) {
	 * 
	 * validateFlag = true; } else
	 * 
	 * if ((currency.isStatus()) && (currency.getMinPerTrxValue() <=
	 * createTransactionRequestDto .getCreateTransactionBody().getTotalAmount() &&
	 * createTransactionRequestDto.getCreateTransactionBody() .getTotalAmount() <=
	 * currency.getMaxPerTrxValue()) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode())) {
	 * 
	 * validateFlag = true; }
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * if (eppFlag) {
	 * 
	 * return validateFlag && isEppFlag;
	 * 
	 * } else {
	 * 
	 * return validateFlag;
	 * 
	 * }
	 * 
	 * }
	 * 
	 *//**
		 * Method to validate EPP Object
		 * 
		 * @param createTransactionRequestDto
		 * @param apgChannel
		 * @return boolean flag
		 */

	/*
	 * private EppInfoDto validateEppInfo(CreateTransactionRequestDto
	 * createTransactionRequestDto, APGChannel apgChannel) {
	 * 
	 * EppInfoDto eppInfoDto = null;
	 * 
	 * Long channelProviderId = null;
	 * 
	 * boolean currencyFlag = false;
	 * 
	 * String defaultProviderKey = apgChannel.getDefaultProviderKey();
	 * 
	 * if (createTransactionRequestDto.getCreateTransactionBody().getPaymenttype()
	 * .equalsIgnoreCase(ApplicationConstants.PAYMENT_TYPE_MOTO)) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else { defaultProviderKey += ApplicationConstants.EPP; }
	 * List<APGChannelProvider> channelProviderList =
	 * apgChannel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) {
	 * 
	 * boolean flag = false; String installmentPlanCode = null; String termMonth =
	 * null;
	 * 
	 * if (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus()) {
	 * 
	 * List<APGChannelProviderMetaData> channelProviderMetaDataList =
	 * channelProvider .getChannelProvidermetaData();
	 * 
	 * if (channelProviderMetaDataList != null &&
	 * !channelProviderMetaDataList.isEmpty()) {
	 * 
	 * for (APGChannelProviderMetaData metaData : channelProviderMetaDataList) {
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.
	 * INSTALLMENT_PLAN_CODE)) { installmentPlanCode = metaData.getDataValue(); }
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); }
	 * 
	 * if (installmentPlanCode != null && termMonth != null &&
	 * termMonth.equalsIgnoreCase(createTransactionRequestDto.
	 * getCreateTransactionBody() .getEppinfo().getMonthterm().toString())) {
	 * channelProviderId = metaData.getChannelProvider().getChannelProviderId();
	 * eppInfoDto = new EppInfoDto();
	 * eppInfoDto.setInstallmentPlanCode(installmentPlanCode);
	 * eppInfoDto.setMonthTerm(termMonth);
	 * eppInfoDto.setChannelProviderId(channelProviderId); flag = true; break; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * if (flag) {
	 * 
	 * List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList();
	 * 
	 * for (APGChannelProviderCurrency currency : channelProviderCurrencyList) {
	 * 
	 * if (currency.isStatus() && currency.getMinPerTrxValue() == 0 &&
	 * currency.getMaxPerTrxValue() == 0 &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode())) {
	 * currencyFlag = true; break; } else if ((currency.isStatus()) &&
	 * (currency.getMinPerTrxValue() <= createTransactionRequestDto
	 * .getCreateTransactionBody().getTotalAmount() &&
	 * createTransactionRequestDto.getCreateTransactionBody() .getTotalAmount() <=
	 * currency.getMaxPerTrxValue()) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode().equalsIgnoreCase(
	 * createTransactionRequestDto.getCreateTransactionBody().getCurrencycode())) {
	 * 
	 * currencyFlag = true; break; } }
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * } if (currencyFlag) {
	 * 
	 * return eppInfoDto; } else return null;
	 * 
	 * }
	 * 
	 * @Override public ChannelPaymentProviderDetailsResponseDto
	 * getProviderKeyList(String transactionId) {
	 * 
	 * ChannelPaymentProviderDetailsResponseDto
	 * channelPaymentProviderDetailsResponseDto = null;
	 * 
	 * Transaction transaction =
	 * transactionDao.getTransactionByTransactionRefId(transactionId);
	 * 
	 * channelPaymentProviderDetailsResponseDto =
	 * validationDao.getProviderKeyList(transaction.getChannelKey(),
	 * transaction.getPartnerKey());
	 * 
	 * return channelPaymentProviderDetailsResponseDto; }
	 * 
	 * @Override public ChannelPaymentProviderDetailsResponseDto
	 * getProviderKeyListFor3d(String transactionId) {
	 * 
	 * ChannelPaymentProviderDetailsResponseDto
	 * channelPaymentProviderDetailsResponseDto = null;
	 * 
	 * Transaction transaction =
	 * transactionDao.getTransactionByTransactionRefId(transactionId);
	 * 
	 * channelPaymentProviderDetailsResponseDto =
	 * validationDao.getProviderKeyListFor3d(transaction.getChannelKey(),
	 * transaction.getPartnerKey());
	 * 
	 * return channelPaymentProviderDetailsResponseDto; }
	 * 
	 * @Override public BankSuperResposeDto validateCard(InitPaymentDto
	 * initPaymentDto) { BankHeaderRequestDto bankHeader; try { BankSuperResposeDto
	 * bankSuperResponse = new BankSuperResposeDto(); HttpServletRequest request =
	 * ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
	 * .getRequest(); Integer code = null; String message = ""; Transaction
	 * transactionDetail = transactionDao
	 * .getTransactionByTransactionRefId(initPaymentDto.getTxtTransId());
	 * 
	 * APGPartner partner =
	 * partnerDao.findPartnerByPartnerKey(transactionDetail.getPartnerKey());
	 * 
	 * String cardRange = initPaymentDto.getTxtCardNumber().replaceAll("\\s",
	 * "").substring(0, 6);
	 * 
	 * // Check if partner has configured to check card in binList if
	 * (partner.getSupportBinList()) {
	 * 
	 * boolean binListFlag =
	 * validationDao.validateCardAgainstBinList(transactionDetail.getPartnerKey(),
	 * cardRange);
	 * 
	 * if (!binListFlag) {
	 * 
	 * code = 404; message = ApplicationConstants.BINLIST_VALIDATION_FAILED;
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setTransactionStatus("");
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse;
	 * 
	 * } }
	 * 
	 * if (transactionDetail.getTransactionDiscountInfo() != null) {
	 * 
	 * if (!initPaymentDto.getCardType()
	 * .equalsIgnoreCase(transactionDetail.getTransactionDiscountInfo().getCcType())
	 * ) {
	 * 
	 * transactionDetail.setStatus(259);
	 * transactionDao.saveTransaction(transactionDetail);
	 * 
	 * code = 203; message = ApplicationConstants.DISCOUNT_INFO_NOT_FOUND;
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus("259");
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * if (transactionDetail.getTransactionDiscountInfo().getBankName() != null) {
	 * boolean binListFlag =
	 * validationDao.validateCardAgainstBinList(transactionDetail.getPartnerKey(),
	 * cardRange, transactionDetail.getTransactionDiscountInfo().getBankName());
	 * 
	 * if (!binListFlag) {
	 * 
	 * transactionDetail.setStatus(259);
	 * transactionDao.saveTransaction(transactionDetail); code = 203; message =
	 * ApplicationConstants.DISCOUNT_INFO_NOT_FOUND; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus("259");
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; } }
	 * 
	 * }
	 * 
	 * boolean validCardFlag =
	 * validationDao.validateCardType(initPaymentDto.getCardType(),
	 * transactionDetail.getPartnerKey());
	 * 
	 * if (!validCardFlag) {
	 * 
	 * code = 404; message = ApplicationConstants.CARD_VALIDATION_FAILED; bankHeader
	 * = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * boolean blacklistCardFlag =
	 * blackListRuleDao.checkCardForBlackListRule(initPaymentDto.getTxtCardNumber())
	 * ;
	 * 
	 * if (blacklistCardFlag) {
	 * 
	 * APGBlacklistPayee apgBlacklistPayee =
	 * blackListRuleDao.getRuleByCard(initPaymentDto.getTxtCardNumber());
	 * 
	 * if (apgBlacklistPayee.getStatus() == BlacklistRule.BLACKLISTED.ordinal()) {
	 * 
	 * transactionDetail.setStatus(130);
	 * 
	 * } else if (apgBlacklistPayee.getStatus() ==
	 * BlacklistRule.INVESTIGATION.ordinal()) { transactionDetail.setStatus(135); }
	 * 
	 * transactionDao.saveTransaction(transactionDetail); code = 203; message =
	 * ApplicationConstants.CARD_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus(String.valueOf(transactionDetail.getStatus())
	 * ); bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * if (transactionDetail.getUser() != null) {
	 * 
	 * boolean emailFlag =
	 * blackListRuleDao.checkEmailForBlackListRule(transactionDetail.getUser().
	 * getEmail());
	 * 
	 * if (emailFlag) {
	 * 
	 * transactionDetail.setStatus(136);
	 * transactionDao.saveTransaction(transactionDetail); code = 203; message =
	 * ApplicationConstants.EMAIL_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus(String.valueOf(transactionDetail.getStatus())
	 * ); bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * boolean partnerUserKeyFlag =
	 * blackListRuleDao.checkPartnerUserKeyForBlackListRule(
	 * transactionDetail.getUser().getPartnerUserKey(), partner.getPartnerId());
	 * 
	 * if (partnerUserKeyFlag) {
	 * 
	 * transactionDetail.setStatus(137);
	 * transactionDao.saveTransaction(transactionDetail); code = 203; message =
	 * ApplicationConstants.PARTNERUSERKEY_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus(String.valueOf(transactionDetail.getStatus())
	 * ); bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * if (transactionDetail.getUser().getLoginUserID() != null) {
	 * SSOGetSomeUserInfoResponseDto ssoGetSomeUserInfoResponseDto =
	 * checkSSOValidity( transactionDetail.getUser().getLoginUserID());
	 * 
	 * boolean ssoEmailFlag = blackListRuleDao.checkSSO(
	 * ssoGetSomeUserInfoResponseDto.getSsoGetSomeUserInfoResponseBody().getEmail(),
	 * BlacklistRule.INVESTIGATION.ordinal());
	 * 
	 * boolean ssoAccountIdFlag = blackListRuleDao.checkSSO(
	 * ssoGetSomeUserInfoResponseDto.getSsoGetSomeUserInfoResponseBody().
	 * getAccountNo(), BlacklistRule.INVESTIGATION.ordinal());
	 * 
	 * boolean ssoAstroIdFlag = blackListRuleDao.checkSSO(
	 * ssoGetSomeUserInfoResponseDto.getSsoGetSomeUserInfoResponseBody().getAstroId(
	 * ), BlacklistRule.INVESTIGATION.ordinal());
	 * 
	 * if (ssoEmailFlag || ssoAccountIdFlag || ssoAstroIdFlag) {
	 * 
	 * transactionDetail.setStatus(138);
	 * transactionDao.saveTransaction(transactionDetail); code = 203; message =
	 * ApplicationConstants.LOGINUSERID_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus(String.valueOf(transactionDetail.getStatus())
	 * ); bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; } }
	 * }
	 * 
	 * if (request.getAttribute("error").equals(1)) {
	 * transactionDetail.setStatus(139);
	 * transactionDao.saveTransaction(transactionDetail); code = 203; message =
	 * ApplicationConstants.IP_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankHeader.setTransactionStatus(String.valueOf(transactionDetail.getStatus())
	 * ); bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * List<APGBlackListCards> blackListCardList =
	 * blackListCardDao.getBlackListCards();
	 * 
	 * for (APGBlackListCards card : blackListCardList) {
	 * 
	 * card.setCcInfo(APGUtils.getMaskedCard(card.getCcInfo().trim())); }
	 * 
	 * for (APGBlackListCards card : blackListCardList) {
	 * 
	 * if (card.getCcInfo().equals(initPaymentDto.getTxtCardNumber())) { code = 404;
	 * message = ApplicationConstants.BLACKLIST_VALIDATION_FAILED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setTransactionStatus("404");
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; } }
	 * 
	 * APGChannel channel =
	 * validationDao.getChannelInfo(transactionDetail.getChannelKey(),
	 * partner.getPartnerId());
	 * 
	 * if (transactionDetail.getIsepp()) {
	 * 
	 * boolean flag =
	 * transactionDao.checkEppInfoExists(transactionDetail.getTransactionID());
	 * 
	 * if (!flag) {
	 * 
	 * APGTransactionEppInfo eppObj = new APGTransactionEppInfo();
	 * eppObj.setMonthTerms(initPaymentDto.getMonthTerm());
	 * eppObj.setPlanCode(initPaymentDto.getPlanCode());
	 * eppObj.setTransactionID(transactionDetail);
	 * eppObj.setChannelProviderId(initPaymentDto.getChannelProviderId());
	 * transactionDao.saveTransactionEppInfo(eppObj);
	 * 
	 * } } boolean validateMonthTermFlag = validateProvider(initPaymentDto,
	 * transactionDetail, channel, transactionDetail.getIsepp());
	 * 
	 * if (!validateMonthTermFlag) { code = 404;
	 * 
	 * if (transactionDetail.getIsepp()) {
	 * 
	 * message = String.format(ApplicationConstants.EPP_CARD_VALIDATION_ERROR,
	 * initPaymentDto.getCardName(), initPaymentDto.getMonthTerm());
	 * 
	 * } else {
	 * 
	 * message = String.format(ApplicationConstants.SINGLE_CARD_VALIDATION_ERROR,
	 * initPaymentDto.getCardName()); }
	 * 
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setTransactionStatus("404");
	 * bankSuperResponse.setBankHeader(bankHeader);
	 * 
	 * return bankSuperResponse; }
	 * 
	 * Integer maxNoOfTransactionsPerCard = partner.getTransPerCardLimit();
	 * 
	 * Long maxTransactionValue = partner.getTransPerCardValue();
	 * 
	 * List<Long> transactionIdList = null;
	 * 
	 * if (channel.getChannelKey().equalsIgnoreCase(ApplicationConstants.MBB)) {
	 * 
	 * transactionIdList =
	 * paymentInfoMBBDao.getTransactionListForCard(initPaymentDto.getTxtCardNumber()
	 * );
	 * 
	 * } else if
	 * (channel.getChannelKey().equalsIgnoreCase(ApplicationConstants.CTB)) {
	 * 
	 * transactionIdList =
	 * paymentInfoCTBDao.getTransactionListForCard(initPaymentDto.getTxtCardNumber()
	 * );
	 * 
	 * } else if
	 * (channel.getChannelKey().equalsIgnoreCase(ApplicationConstants.PBB_BANK)) {
	 * 
	 * transactionIdList =
	 * paymentInfoPBBDao.getTransactionListForCard(initPaymentDto.getTxtCardNumber()
	 * );
	 * 
	 * }
	 * 
	 * if (transactionIdList != null && !transactionIdList.isEmpty()) {
	 * 
	 * if ((transactionIdList.size() + 1) > maxNoOfTransactionsPerCard) {
	 * 
	 * String errorResponse = ErrorUtil
	 * .getProperty(TransactionValidationCodes.EXCEEDED_LIMIT_OF_TRANSACTIONS.
	 * getResponseCode());
	 * 
	 * errorResponse = String.format(errorResponse, maxNoOfTransactionsPerCard);
	 * 
	 * code = 404;
	 * 
	 * message = errorResponse;
	 * 
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setTransactionStatus("404");
	 * bankSuperResponse.setBankHeader(bankHeader);
	 * 
	 * return bankSuperResponse;
	 * 
	 * }
	 * 
	 * List<Transaction> transactionList =
	 * transactionDao.getTransactionListById(transactionIdList);
	 * 
	 * Long totalAmount = 0L;
	 * 
	 * for (Transaction transaction : transactionList) { totalAmount +=
	 * transaction.getTotalAmount(); }
	 * 
	 * if ((totalAmount + transactionDetail.getTotalAmount()) > maxTransactionValue)
	 * {
	 * 
	 * code = 404;
	 * 
	 * message = ErrorUtil
	 * .getProperty(TransactionValidationCodes.EXCEEDED_DAILY_MAX_VALUE.
	 * getResponseCode());
	 * 
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setTransactionStatus("404");
	 * bankSuperResponse.setBankHeader(bankHeader);
	 * 
	 * return bankSuperResponse;
	 * 
	 * }
	 * 
	 * } if (transactionDetail.getTransType().equalsIgnoreCase(ApplicationConstants.
	 * TRANSACTION_RECURRING)) {
	 * 
	 * APGRecurring apgRecurring = transactionDetail.getRecurringID();
	 * 
	 * apgRecurring.setCcDataKey(initPaymentDto.getCcDataKey());
	 * 
	 * transactionDao.saveTransactionRecurring(apgRecurring);
	 * 
	 * RecurringCCDataKeyInfo recurringCCDataKeyInfo = new RecurringCCDataKeyInfo();
	 * recurringCCDataKeyInfo.setApgRecurring(apgRecurring);
	 * recurringCCDataKeyInfo.setCcDisplay(initPaymentDto.getTxtCardNumber());
	 * recurringCCDataKeyInfo.setCcName(initPaymentDto.getTxtNameOnCard());
	 * recurringCCDataKeyInfo.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * recurringCCDataKeyInfo.setExpiryMonth(initPaymentDto.getDdlMonth());
	 * recurringCCDataKeyInfo.setCvv(initPaymentDto.getTxtCcv());
	 * recurringCCDataKeyInfo.setExpiryYear(initPaymentDto.getDdlYear());
	 * recurringCCDataKeyInfo.setCardType(initPaymentDto.getCardType());
	 * recurringCCDataKeyInfo.setLastModifiedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * 
	 * transactionDao.saveRecurringCCDataKeyInfo(recurringCCDataKeyInfo); }
	 * transactionDetail.setAdditionalChargesList(transactionDetail.
	 * getAdditionalChargesList());
	 * transactionDetail.setItemList(transactionDetail.getItemList());
	 * submitMessageService.sendOnUserBeginToSubmitMessage(transactionDetail);
	 * 
	 * String defaultProviderStr = channel.getDefaultProviderKey();
	 * PaymentProviderService paymentService = (PaymentProviderService) appContext
	 * .getBean(defaultProviderStr.toUpperCase() + "PaymentProviderServiceImpl");
	 * return paymentService.getDetails(channel, initPaymentDto, partner); } catch
	 * (Exception e) { GenericUtil.logError("TransactionPayment", "Error", "" + e,
	 * logger); return null; }
	 * 
	 * }
	 * 
	 * @Override public BankSuperResposeDto
	 * validateRecurringCard(ChangeRecurringPaymentDto initPaymentDto,
	 * HttpServletRequest request) {
	 * 
	 * BankHeaderRequestDto bankHeader; BankSuperResposeDto bankSuperResponse = new
	 * BankSuperResposeDto();
	 * 
	 * Integer code = null; String message = ""; Transaction transactionDetail =
	 * null;
	 * 
	 * transactionDetail =
	 * transactionDao.getTransactionByTransactionRefId(initPaymentDto.getTxtTransId(
	 * ));
	 * 
	 * APGPartner partner =
	 * partnerDao.findPartnerByPartnerKey(transactionDetail.getPartnerKey());
	 * 
	 * boolean validCardFlag =
	 * validationDao.validateCardType(initPaymentDto.getCardType(),
	 * transactionDetail.getPartnerKey());
	 * 
	 * if (!validCardFlag) {
	 * 
	 * code = 404; message = ApplicationConstants.CARD_VALIDATION_FAILED; bankHeader
	 * = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * boolean blacklistCardFlag =
	 * blackListRuleDao.checkCardForBlackListRule(initPaymentDto.getTxtCardNumber())
	 * ;
	 * 
	 * if (blacklistCardFlag) {
	 * 
	 * TransactionPayment transactionPayment = new TransactionPayment(); String
	 * currencyExp = transactionDetail.getCurrencyExp().toString(); String
	 * totalAmount = String.valueOf(transactionDetail.getTotalAmount()); totalAmount
	 * = APGUtils.changeToPrecision(totalAmount, currencyExp);
	 * transactionPayment.setAmount(totalAmount);
	 * transactionPayment.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * transactionPayment.setLastModifiedUTC(new
	 * Timestamp(System.currentTimeMillis())); transactionPayment.setMID(null);
	 * transactionPayment.setTransaction(transactionDetail);
	 * transactionPayment.setPaymentRefID(null);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setTransType(transactionDetail.getTransType());
	 * transactionPayment.setPaymentProviderKey(null);
	 * transactionPayment.setBank("");
	 * transactionPayment.setStatus(TransactionPaymentStatus.FAILURE.ordinal());
	 * transactionPayment.setRetryCount(0);
	 * transactionPayment.setCcValidation(false);
	 * transactionPayment.setCardReplacement(false);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setErrorCode("203");
	 * transactionPayment.setErrorDesc("EMAIL BLACKLISTED/UNDER INVESTIGATION"); if
	 * ((initPaymentDto != null) && initPaymentDto.getFavCardDataKey() != null) {
	 * transactionPayment.setFavCardDataKey(initPaymentDto.getFavCardDataKey()); }
	 * 
	 * transactionPaymentDao.saveTransactionPaymentInfo(transactionPayment); code =
	 * 203; message = ApplicationConstants.CARD_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * if (transactionDetail.getUser() != null) {
	 * 
	 * boolean emailFlag =
	 * blackListRuleDao.checkEmailForBlackListRule(transactionDetail.getUser().
	 * getEmail());
	 * 
	 * if (emailFlag) { TransactionPayment transactionPayment = new
	 * TransactionPayment(); String currencyExp =
	 * transactionDetail.getCurrencyExp().toString(); String totalAmount =
	 * String.valueOf(transactionDetail.getTotalAmount()); totalAmount =
	 * APGUtils.changeToPrecision(totalAmount, currencyExp);
	 * transactionPayment.setAmount(totalAmount);
	 * transactionPayment.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * transactionPayment.setLastModifiedUTC(new
	 * Timestamp(System.currentTimeMillis())); transactionPayment.setMID(null);
	 * transactionPayment.setTransaction(transactionDetail);
	 * transactionPayment.setPaymentRefID(null);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setTransType(transactionDetail.getTransType());
	 * transactionPayment.setPaymentProviderKey(null);
	 * transactionPayment.setBank("");
	 * transactionPayment.setStatus(TransactionPaymentStatus.FAILURE.ordinal());
	 * transactionPayment.setRetryCount(0);
	 * transactionPayment.setCcValidation(false);
	 * transactionPayment.setCardReplacement(false);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setErrorCode("203");
	 * transactionPayment.setErrorDesc("EMAIL BLACKLISTED/UNDER INVESTIGATION"); if
	 * ((initPaymentDto != null) && initPaymentDto.getFavCardDataKey() != null) {
	 * transactionPayment.setFavCardDataKey(initPaymentDto.getFavCardDataKey()); }
	 * 
	 * transactionPaymentDao.saveTransactionPaymentInfo(transactionPayment); code =
	 * 203; message = ApplicationConstants.EMAIL_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * boolean partnerUserKeyFlag =
	 * blackListRuleDao.checkPartnerUserKeyForBlackListRule(
	 * transactionDetail.getUser().getPartnerUserKey(), partner.getPartnerId());
	 * 
	 * if (partnerUserKeyFlag) { TransactionPayment transactionPayment = new
	 * TransactionPayment(); String currencyExp =
	 * transactionDetail.getCurrencyExp().toString(); String totalAmount =
	 * String.valueOf(transactionDetail.getTotalAmount()); totalAmount =
	 * APGUtils.changeToPrecision(totalAmount, currencyExp);
	 * transactionPayment.setAmount(totalAmount);
	 * transactionPayment.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * transactionPayment.setLastModifiedUTC(new
	 * Timestamp(System.currentTimeMillis())); transactionPayment.setMID(null);
	 * transactionPayment.setTransaction(transactionDetail);
	 * transactionPayment.setPaymentRefID(null);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setTransType(transactionDetail.getTransType());
	 * transactionPayment.setPaymentProviderKey(null);
	 * transactionPayment.setBank("");
	 * transactionPayment.setStatus(TransactionPaymentStatus.FAILURE.ordinal());
	 * transactionPayment.setRetryCount(0);
	 * transactionPayment.setCcValidation(false);
	 * transactionPayment.setCardReplacement(false);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setErrorCode("203"); transactionPayment.
	 * setErrorDesc("PARTNER USERKEY BLACKLISTED/UNDER INVESTIGATION"); if
	 * ((initPaymentDto != null) && initPaymentDto.getFavCardDataKey() != null) {
	 * transactionPayment.setFavCardDataKey(initPaymentDto.getFavCardDataKey()); }
	 * 
	 * transactionPaymentDao.saveTransactionPaymentInfo(transactionPayment); code =
	 * 203; message = ApplicationConstants.PARTNERUSERKEY_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * boolean ssoEmailFlag =
	 * blackListRuleDao.checkSSO(transactionDetail.getUser().getEmail(),
	 * BlacklistRule.INVESTIGATION.ordinal());
	 * 
	 * if (ssoEmailFlag) { TransactionPayment transactionPayment = new
	 * TransactionPayment(); String currencyExp =
	 * transactionDetail.getCurrencyExp().toString(); String totalAmount =
	 * String.valueOf(transactionDetail.getTotalAmount()); totalAmount =
	 * APGUtils.changeToPrecision(totalAmount, currencyExp);
	 * transactionPayment.setAmount(totalAmount);
	 * transactionPayment.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * transactionPayment.setLastModifiedUTC(new
	 * Timestamp(System.currentTimeMillis())); transactionPayment.setMID(null);
	 * transactionPayment.setTransaction(transactionDetail);
	 * transactionPayment.setPaymentRefID(null);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setTransType(transactionDetail.getTransType());
	 * transactionPayment.setPaymentProviderKey(null);
	 * transactionPayment.setBank("");
	 * transactionPayment.setStatus(TransactionPaymentStatus.FAILURE.ordinal());
	 * transactionPayment.setRetryCount(0);
	 * transactionPayment.setCcValidation(false);
	 * transactionPayment.setCardReplacement(false);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setErrorCode("203"); transactionPayment.
	 * setErrorDesc("SSO LOGIN USERID BLACKLISTED/UNDER INVESTIGATION"); if
	 * ((initPaymentDto != null) && initPaymentDto.getFavCardDataKey() != null) {
	 * transactionPayment.setFavCardDataKey(initPaymentDto.getFavCardDataKey()); }
	 * 
	 * transactionPaymentDao.saveTransactionPaymentInfo(transactionPayment); code =
	 * 203; message = ApplicationConstants.LOGINUSERID_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; } }
	 * 
	 * if (request.getAttribute("error").equals(1)) { TransactionPayment
	 * transactionPayment = new TransactionPayment(); String currencyExp =
	 * transactionDetail.getCurrencyExp().toString(); String totalAmount =
	 * String.valueOf(transactionDetail.getTotalAmount()); totalAmount =
	 * APGUtils.changeToPrecision(totalAmount, currencyExp);
	 * transactionPayment.setAmount(totalAmount);
	 * transactionPayment.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * transactionPayment.setLastModifiedUTC(new
	 * Timestamp(System.currentTimeMillis())); transactionPayment.setMID(null);
	 * transactionPayment.setTransaction(transactionDetail);
	 * transactionPayment.setPaymentRefID(null);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setTransType(transactionDetail.getTransType());
	 * transactionPayment.setPaymentProviderKey(null);
	 * transactionPayment.setBank("");
	 * transactionPayment.setStatus(TransactionPaymentStatus.FAILURE.ordinal());
	 * transactionPayment.setRetryCount(0);
	 * transactionPayment.setCcValidation(false);
	 * transactionPayment.setCardReplacement(false);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setErrorCode("203");
	 * transactionPayment.setErrorDesc("IP BLACKLISTED/UNDER INVESTIGATION"); if
	 * ((initPaymentDto != null) && initPaymentDto.getFavCardDataKey() != null) {
	 * transactionPayment.setFavCardDataKey(initPaymentDto.getFavCardDataKey()); }
	 * 
	 * transactionPaymentDao.saveTransactionPaymentInfo(transactionPayment); code =
	 * 203; message = ApplicationConstants.IP_BLACKLISTED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankHeader.setRedirectURL(initPaymentDto.getTxtTransId());
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; }
	 * 
	 * List<APGBlackListCards> blackListCardList =
	 * blackListCardDao.getBlackListCards();
	 * 
	 * for (APGBlackListCards card : blackListCardList) {
	 * 
	 * card.setCcInfo(APGUtils.getMaskedCard(card.getCcInfo().trim())); }
	 * 
	 * for (APGBlackListCards card : blackListCardList) {
	 * 
	 * if (card.getCcInfo().equals(initPaymentDto.getTxtCardNumber())) { code = 404;
	 * message = ApplicationConstants.BLACKLIST_VALIDATION_FAILED; bankHeader =
	 * MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse; } }
	 * 
	 * // Check if partner has configured to check card in binList if
	 * (partner.getSupportBinList()) {
	 * 
	 * String cardRange = initPaymentDto.getTxtCardNumber().replaceAll("\\s",
	 * "").substring(0, 6);
	 * 
	 * boolean binListFlag =
	 * validationDao.validateCardAgainstBinList(transactionDetail.getPartnerKey(),
	 * cardRange);
	 * 
	 * if (!binListFlag) {
	 * 
	 * code = 404; message = ApplicationConstants.BINLIST_VALIDATION_FAILED;
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * bankSuperResponse.setBankHeader(bankHeader); return bankSuperResponse;
	 * 
	 * } }
	 * 
	 * APGChannel channel =
	 * validationDao.getChannelInfo(transactionDetail.getChannelKey(),
	 * partner.getPartnerId());
	 * 
	 * if (transactionDetail.getIsepp()) {
	 * 
	 * boolean flag =
	 * transactionDao.checkEppInfoExists(transactionDetail.getTransactionID());
	 * 
	 * if (!flag) {
	 * 
	 * APGTransactionEppInfo eppObj = new APGTransactionEppInfo();
	 * eppObj.setMonthTerms(initPaymentDto.getMonthTerm());
	 * eppObj.setPlanCode(initPaymentDto.getPlanCode());
	 * eppObj.setTransactionID(transactionDetail);
	 * eppObj.setChannelProviderId(initPaymentDto.getChannelProviderId());
	 * transactionDao.saveTransactionEppInfo(eppObj);
	 * 
	 * } } boolean validateMonthTermFlag = validateRecurringProvider(initPaymentDto,
	 * transactionDetail, channel, transactionDetail.getIsepp());
	 * 
	 * if (!validateMonthTermFlag) { code = 404;
	 * 
	 * if (transactionDetail.getIsepp()) {
	 * 
	 * message = String.format(ApplicationConstants.EPP_CARD_VALIDATION_ERROR,
	 * initPaymentDto.getCardName(), initPaymentDto.getMonthTerm());
	 * 
	 * } else {
	 * 
	 * message = String.format(ApplicationConstants.SINGLE_CARD_VALIDATION_ERROR,
	 * initPaymentDto.getCardName()); }
	 * 
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * 
	 * bankSuperResponse.setBankHeader(bankHeader);
	 * 
	 * return bankSuperResponse; }
	 * 
	 * Integer maxNoOfTransactionsPerCard = partner.getTransPerCardLimit();
	 * 
	 * Long maxTransactionValue = partner.getTransPerCardValue();
	 * 
	 * List<Long> transactionIdList = null;
	 * 
	 * if (channel.getChannelKey().equalsIgnoreCase(ApplicationConstants.MBB)) {
	 * 
	 * transactionIdList =
	 * paymentInfoMBBDao.getTransactionListForCard(initPaymentDto.getTxtCardNumber()
	 * );
	 * 
	 * } else if
	 * (channel.getChannelKey().equalsIgnoreCase(ApplicationConstants.CTB)) {
	 * 
	 * transactionIdList =
	 * paymentInfoCTBDao.getTransactionListForCard(initPaymentDto.getTxtCardNumber()
	 * );
	 * 
	 * } else if
	 * (channel.getChannelKey().equalsIgnoreCase(ApplicationConstants.PBB_BANK)) {
	 * 
	 * transactionIdList =
	 * paymentInfoPBBDao.getTransactionListForCard(initPaymentDto.getTxtCardNumber()
	 * );
	 * 
	 * }
	 * 
	 * if (transactionIdList != null && !transactionIdList.isEmpty()) {
	 * 
	 * if ((transactionIdList.size() + 1) > maxNoOfTransactionsPerCard) {
	 * 
	 * String errorResponse = ErrorUtil
	 * .getProperty(TransactionValidationCodes.EXCEEDED_LIMIT_OF_TRANSACTIONS.
	 * getResponseCode());
	 * 
	 * errorResponse = String.format(errorResponse, maxNoOfTransactionsPerCard);
	 * 
	 * code = 404;
	 * 
	 * message = errorResponse;
	 * 
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * 
	 * bankSuperResponse.setBankHeader(bankHeader);
	 * 
	 * return bankSuperResponse;
	 * 
	 * }
	 * 
	 * List<Transaction> transactionList =
	 * transactionDao.getTransactionListById(transactionIdList);
	 * 
	 * Long totalAmount = 0L;
	 * 
	 * for (Transaction transaction : transactionList) { totalAmount +=
	 * transaction.getTotalAmount(); }
	 * 
	 * if ((totalAmount + transactionDetail.getTotalAmount()) > maxTransactionValue)
	 * {
	 * 
	 * code = 404;
	 * 
	 * message =
	 * ErrorUtil.getProperty(TransactionValidationCodes.EXCEEDED_DAILY_MAX_VALUE.
	 * getResponseCode());
	 * 
	 * bankHeader = MBBRequestMapper.mbbHeaderRequestMapper(code, message);
	 * 
	 * bankSuperResponse.setBankHeader(bankHeader);
	 * 
	 * return bankSuperResponse;
	 * 
	 * }
	 * 
	 * } if (transactionDetail.getTransType().equalsIgnoreCase(ApplicationConstants.
	 * TRANSACTION_RECURRING)) {
	 * 
	 * APGRecurring apgRecurring = transactionDetail.getRecurringID();
	 * 
	 * 
	 * apgRecurring.setCcDataKey(initPaymentDto.getCcDataKey());
	 * 
	 * transactionDao.saveTransactionRecurring(apgRecurring);
	 * 
	 * 
	 * RecurringCCDataKeyInfo recurringCCDataKeyInfo =
	 * apgRecurring.getRecurringCCDataKeyInfo();
	 * 
	 * recurringCCDataKeyInfo.setCcDisplay(initPaymentDto.getTxtCardNumber());
	 * recurringCCDataKeyInfo.setCcName(initPaymentDto.getTxtNameOnCard());
	 * recurringCCDataKeyInfo.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * recurringCCDataKeyInfo.setExpiryMonth(initPaymentDto.getDdlMonth());
	 * recurringCCDataKeyInfo.setCvv(initPaymentDto.getTxtCcv());
	 * recurringCCDataKeyInfo.setExpiryYear(initPaymentDto.getDdlYear());
	 * recurringCCDataKeyInfo.setCardType(initPaymentDto.getCardType());
	 * recurringCCDataKeyInfo.setLastModifiedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * 
	 * transactionDao.saveRecurringCCDataKeyInfo(recurringCCDataKeyInfo); }
	 * transactionDetail.setAdditionalChargesList(transactionDetail.
	 * getAdditionalChargesList());
	 * transactionDetail.setItemList(transactionDetail.getItemList());
	 * submitMessageService.sendOnUserBeginToSubmitMessage(transactionDetail);
	 * 
	 * String defaultProviderStr = channel.getDefaultProviderKey();
	 * PaymentProviderService paymentService = (PaymentProviderService) appContext
	 * .getBean(defaultProviderStr.toUpperCase() + "PaymentProviderServiceImpl");
	 * return paymentService.getDetails(channel, initPaymentDto, partner);
	 * 
	 * }
	 * 
	 * private boolean validateRecurringProvider(ChangeRecurringPaymentDto
	 * initPaymentDto, Transaction transaction, APGChannel channel, Boolean eppFlag)
	 * {
	 * 
	 * boolean validateFlag = false; boolean isEppFlag = false;
	 * 
	 * String defaultProviderKey = channel.getDefaultProviderKey();
	 * 
	 * if (transaction.getPaymentType().equals("") && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EPP; }
	 * 
	 * if
	 * (transaction.getPaymentType().equals(ApplicationConstants.PAYMENT_TYPE_MOTO)
	 * && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else if
	 * (transaction.getPaymentType().equals(ApplicationConstants.PAYMENT_TYPE_MOTO))
	 * {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO; }
	 * 
	 * List<APGChannelProvider> channelProviderList =
	 * channel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) {
	 * 
	 * String termMonth = ""; String planCode = "";
	 * 
	 * if (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus() &&
	 * channelProvider.getCardType().equalsIgnoreCase(initPaymentDto.getCardType()))
	 * {
	 * 
	 * if (eppFlag) {
	 * 
	 * List<APGChannelProviderMetaData> metaDataList =
	 * channelProvider.getChannelProvidermetaData();
	 * 
	 * for (APGChannelProviderMetaData metaData : metaDataList) {
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); } if
	 * (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.
	 * INSTALLMENT_PLAN_CODE)) { planCode = metaData.getDataValue(); }
	 * 
	 * if (!termMonth.isEmpty() && !planCode.isEmpty() &&
	 * metaData.getChannelProvider().getChannelProviderId() == channelProvider
	 * .getChannelProviderId() &&
	 * (termMonth.equals(initPaymentDto.getMonthTerm().toString())) &&
	 * (planCode.equals(initPaymentDto.getPlanCode()))) {
	 * 
	 * isEppFlag = true; } } }
	 * 
	 * List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList(); for
	 * (APGChannelProviderCurrency currency : channelProviderCurrencyList) { if
	 * ((currency.isStatus()) && (currency.getMinPerTrxValue() == 0 &&
	 * currency.getMaxPerTrxValue() == 0)) {
	 * 
	 * validateFlag = true; } else if ((currency.isStatus()) &&
	 * (currency.getMinPerTrxValue() <= transaction.getTotalAmount() &&
	 * transaction.getTotalAmount() <= currency.getMaxPerTrxValue())) {
	 * 
	 * validateFlag = true; }
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * if (eppFlag) {
	 * 
	 * return validateFlag && isEppFlag;
	 * 
	 * } else {
	 * 
	 * return validateFlag;
	 * 
	 * } }
	 * 
	 * private TransactionPayment getTransactionPayment(InitPaymentDto
	 * initPaymentDto, HttpServletRequest request, Transaction transactionDetail) {
	 * TransactionPayment transactionPayment = new TransactionPayment(); String
	 * currencyExp = transactionDetail.getCurrencyExp().toString(); String
	 * totalAmount = String.valueOf(transactionDetail.getTotalAmount()); totalAmount
	 * = APGUtils.changeToPrecision(totalAmount, currencyExp);
	 * transactionPayment.setAmount(totalAmount);
	 * transactionPayment.setCreatedDateUTC(new
	 * Timestamp(System.currentTimeMillis()));
	 * transactionPayment.setLastModifiedUTC(new
	 * Timestamp(System.currentTimeMillis())); transactionPayment.setMID("");
	 * transactionPayment.setTransaction(transactionDetail);
	 * transactionPayment.setPaymentRefID("");
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setTransType(transactionDetail.getTransType());
	 * transactionPayment.setPaymentProviderKey(""); transactionPayment.setBank("");
	 * transactionPayment.setStatus(TransactionPaymentStatus.FAILURE.ordinal());
	 * transactionPayment.setRetryCount(0);
	 * transactionPayment.setCcValidation(false);
	 * transactionPayment.setCardReplacement(false);
	 * transactionPayment.setPaymentType(transactionDetail.getPaymentType());
	 * transactionPayment.setErrorCode("203");
	 * transactionPayment.setErrorDesc("ERROR OCCURRED");
	 * 
	 * String ip = GenericUtil.getClientIpAddr(request);
	 * 
	 * String arr[] = null;
	 * 
	 * if (ip != null && !ip.isEmpty()) {
	 * 
	 * arr = ip.split(","); }
	 * 
	 * transactionPayment.setIpAddress(arr[0]);
	 * transactionPayment.setUserAgent(request.getHeader("user-agent")); if
	 * ((initPaymentDto != null) && initPaymentDto.getFavCardDataKey() != null) {
	 * transactionPayment.setFavCardDataKey(initPaymentDto.getFavCardDataKey()); }
	 * return transactionPayment; }
	 * 
	 * public static String getTimestampForResponse() { Date date =
	 * Calendar.getInstance().getTime(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	 * sdf.setTimeZone(TimeZone.getTimeZone("UTC")); return sdf.format(date);
	 * 
	 * }
	 * 
	 * public static String generateSignature(String signatureString) {
	 * 
	 * MessageDigest digest = null; try { digest =
	 * MessageDigest.getInstance("SHA-256"); } catch (NoSuchAlgorithmException e) {
	 * e.printStackTrace(); } byte[] hash =
	 * digest.digest(signatureString.getBytes(StandardCharsets.UTF_8)); return new
	 * String(Base64.encodeBase64(hash)); }
	 * 
	 * @Override public SSOGetSomeUserInfoResponseDto checkSSOValidity(String
	 * portalUserId) {
	 * 
	 * SSOGetSomeUserInfoRequestDto ssoGetSomeUserInfoRequestDto = new
	 * SSOGetSomeUserInfoRequestDto(); SSOGetSomeUserInfoRequestBody
	 * ssoGetSomeUserInfoRequestBody = new SSOGetSomeUserInfoRequestBody();
	 * com.apg.v2.api.request.dto.SignInfo signInfo = new SignInfo();
	 * 
	 * ssoGetSomeUserInfoRequestBody.setPortalUserId(portalUserId);
	 * ssoGetSomeUserInfoRequestBody.setLanguage(propertyUtils.getSSOAPILANGUAGE());
	 * ssoGetSomeUserInfoRequestBody.setPartnerKey(propertyUtils.getSSOAPIPARTNER())
	 * ; ssoGetSomeUserInfoRequestDto.setSsoGetSomeUserInfoRequestBody(
	 * ssoGetSomeUserInfoRequestBody);
	 * 
	 * String ts = getTimestampForResponse();
	 * 
	 * String ts1 = ts.replace("-", ""); ts1 = ts1.replace("T", ""); ts1 =
	 * ts1.replace("Z", ""); ts1 = ts1.replace(":", "");
	 * 
	 * String sigStr = ts1 + ssoGetSomeUserInfoRequestBody.getSign() +
	 * propertyUtils.getSSOAPIPARTNERPWD();
	 * 
	 * String hashedSignaure = generateSignature(sigStr);
	 * signInfo.setSig(hashedSignaure); signInfo.setTimeStamp(ts);
	 * 
	 * ssoGetSomeUserInfoRequestDto.setSignInfo(signInfo);
	 * 
	 * String url = propertyUtils.getSSOAPIURL();
	 * 
	 * ObjectMapper mapperObj = new ObjectMapper(); String jsonStr; try { jsonStr =
	 * mapperObj.writeValueAsString(ssoGetSomeUserInfoRequestDto); } catch
	 * (JsonProcessingException e) { return null; } HttpHeaders headers = new
	 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
	 * headers.set("User-Agent", "ABC"); HttpEntity<String> entity = new
	 * HttpEntity<>(jsonStr, headers); RestTemplate restTemplate = new
	 * RestTemplate(); ResponseEntity<SSOGetSomeUserInfoResponseDto> response =
	 * restTemplate.exchange(url, HttpMethod.POST, entity,
	 * SSOGetSomeUserInfoResponseDto.class);
	 * 
	 * return response.getBody();
	 * 
	 * }
	 * 
	 * private boolean validateProvider(InitPaymentDto initPaymentDto, Transaction
	 * transaction, APGChannel channel, Boolean eppFlag) {
	 * 
	 * boolean validateFlag = false; boolean isEppFlag = false;
	 * 
	 * String defaultProviderKey = channel.getDefaultProviderKey();
	 * 
	 * if (transaction.getPaymentType().equals("") && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EPP; }
	 * 
	 * if
	 * (transaction.getPaymentType().equals(ApplicationConstants.PAYMENT_TYPE_MOTO)
	 * && eppFlag) {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO_EPP;
	 * 
	 * } else if
	 * (transaction.getPaymentType().equals(ApplicationConstants.PAYMENT_TYPE_MOTO))
	 * {
	 * 
	 * defaultProviderKey += ApplicationConstants.EMOTO; }
	 * 
	 * List<APGChannelProvider> channelProviderList =
	 * channel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) {
	 * 
	 * String termMonth = ""; String planCode = "";
	 * 
	 * if (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus() &&
	 * channelProvider.getCardType().equalsIgnoreCase(initPaymentDto.getCardType()))
	 * {
	 * 
	 * if (eppFlag) {
	 * 
	 * List<APGChannelProviderMetaData> metaDataList =
	 * channelProvider.getChannelProvidermetaData();
	 * 
	 * for (APGChannelProviderMetaData metaData : metaDataList) {
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); } if
	 * (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.
	 * INSTALLMENT_PLAN_CODE)) { planCode = metaData.getDataValue(); }
	 * 
	 * if (!termMonth.isEmpty() && !planCode.isEmpty() &&
	 * metaData.getChannelProvider().getChannelProviderId() == channelProvider
	 * .getChannelProviderId() &&
	 * (termMonth.equals(initPaymentDto.getMonthTerm().toString())) &&
	 * (planCode.equals(initPaymentDto.getPlanCode()))) {
	 * 
	 * isEppFlag = true; } } }
	 * 
	 * List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList(); for
	 * (APGChannelProviderCurrency currency : channelProviderCurrencyList) { if
	 * ((currency.isStatus()) && (currency.getMinPerTrxValue() == 0 &&
	 * currency.getMaxPerTrxValue() == 0) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode()
	 * .equals(transaction.getCurrencyCode())) {
	 * 
	 * validateFlag = true; } else if ((currency.isStatus()) &&
	 * (currency.getMinPerTrxValue() <= transaction.getTotalAmount() &&
	 * transaction.getTotalAmount() <= currency.getMaxPerTrxValue()) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode()
	 * .equals(transaction.getCurrencyCode())) {
	 * 
	 * validateFlag = true; }
	 * 
	 * }
	 * 
	 * } } }
	 * 
	 * if (eppFlag) {
	 * 
	 * return validateFlag && isEppFlag;
	 * 
	 * } else {
	 * 
	 * return validateFlag;
	 * 
	 * } }
	 * 
	 * @Override public List<EppInfoUiDto> getEppInfo(Transaction transaction) {
	 * 
	 * List<EppInfoUiDto> eppInfoList = new ArrayList<>();
	 * 
	 * APGPartner apgPartner =
	 * partnerDao.findPartnerByPartnerKey(transaction.getPartnerKey());
	 * 
	 * APGChannel apgChannel = channelDao.findByPartnerID(apgPartner.getPartnerId(),
	 * transaction.getChannelKey());
	 * 
	 * String defaultProviderKey = apgChannel.getDefaultProviderKey();
	 * 
	 * if (transaction.getPaymentType().equalsIgnoreCase(ApplicationConstants.
	 * PAYMENT_TYPE_MOTO)) { defaultProviderKey += ApplicationConstants.EMOTO_EPP; }
	 * else { defaultProviderKey += ApplicationConstants.EPP; } String
	 * installmentPlanCode = null; String termMonth = null;
	 * 
	 * List<APGChannelProvider> channelProviderList =
	 * apgChannel.getChannelProviderList();
	 * 
	 * if (channelProviderList != null && !channelProviderList.isEmpty()) {
	 * 
	 * for (APGChannelProvider channelProvider : channelProviderList) {
	 * 
	 * String cardType = channelProvider.getCardType();
	 * 
	 * if (channelProvider.getProvider().getProviderKey().equalsIgnoreCase(
	 * defaultProviderKey) && channelProvider.getStatus()) {
	 * 
	 * List<APGChannelProviderCurrency> channelProviderCurrencyList =
	 * channelProvider .getChannelProviderCurrencyList();
	 * 
	 * for (APGChannelProviderCurrency currency : channelProviderCurrencyList) { if
	 * (currency.getMinPerTrxValue() == 0 && currency.getMaxPerTrxValue() == 0 &&
	 * currency.getApgCurrency().getThreeLetterIsoCode()
	 * .equalsIgnoreCase(transaction.getCurrencyCode())) {
	 * 
	 * List<APGChannelProviderMetaData> channelProviderMetaDataList =
	 * channelProvider .getChannelProvidermetaData();
	 * 
	 * if (channelProviderMetaDataList != null &&
	 * !channelProviderMetaDataList.isEmpty()) {
	 * 
	 * for (APGChannelProviderMetaData metaData : channelProviderMetaDataList) {
	 * 
	 * if (metaData.getDataKey()
	 * .equalsIgnoreCase(ApplicationConstants.INSTALLMENT_PLAN_CODE)) {
	 * installmentPlanCode = metaData.getDataValue(); }
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); }
	 * 
	 * String parentKey = String
	 * .valueOf(metaData.getChannelProvider().getChannelProviderId()); if
	 * (installmentPlanCode != null && termMonth != null) { EppInfoUiDto
	 * eppInfoUiDto = new EppInfoUiDto();
	 * 
	 * eppInfoUiDto .setEppInfoKey(installmentPlanCode + "_" + termMonth + "_" +
	 * parentKey); eppInfoUiDto.setEppInfoValue(Integer.parseInt(termMonth));
	 * installmentPlanCode = null; termMonth = null;
	 * 
	 * int index = eppInfoList.indexOf(eppInfoUiDto);
	 * 
	 * if (index >= 0) {
	 * 
	 * EppInfoUiDto dto = eppInfoList.get(index); if
	 * (!dto.getCardTypes().contains(cardType)) {
	 * 
	 * dto.getCardTypes().add(cardType); } } else {
	 * 
	 * Set<String> cardTypes = new HashSet<>();
	 * 
	 * cardTypes.add(cardType); eppInfoUiDto.setCardTypes(cardTypes);
	 * eppInfoList.add(eppInfoUiDto); } } } }
	 * 
	 * } else if ((currency.isStatus()) && (currency.getMinPerTrxValue() <=
	 * transaction.getTotalAmount() && transaction.getTotalAmount() <=
	 * currency.getMaxPerTrxValue()) &&
	 * currency.getApgCurrency().getThreeLetterIsoCode()
	 * .equalsIgnoreCase(transaction.getCurrencyCode())) {
	 * 
	 * List<APGChannelProviderMetaData> channelProviderMetaDataList =
	 * channelProvider .getChannelProvidermetaData();
	 * 
	 * if (channelProviderMetaDataList != null &&
	 * !channelProviderMetaDataList.isEmpty()) {
	 * 
	 * for (APGChannelProviderMetaData metaData : channelProviderMetaDataList) {
	 * 
	 * if (metaData.getDataKey()
	 * .equalsIgnoreCase(ApplicationConstants.INSTALLMENT_PLAN_CODE)) {
	 * installmentPlanCode = metaData.getDataValue(); }
	 * 
	 * if (metaData.getDataKey().equalsIgnoreCase(ApplicationConstants.TERM_MONTH))
	 * { termMonth = metaData.getDataValue(); }
	 * 
	 * String parentKey = String
	 * .valueOf(metaData.getChannelProvider().getChannelProviderId()); if
	 * (installmentPlanCode != null && termMonth != null) { EppInfoUiDto
	 * eppInfoUiDto = new EppInfoUiDto();
	 * 
	 * eppInfoUiDto .setEppInfoKey(installmentPlanCode + "_" + termMonth + "_" +
	 * parentKey); eppInfoUiDto.setEppInfoValue(Integer.parseInt(termMonth));
	 * installmentPlanCode = null; termMonth = null;
	 * 
	 * int index = eppInfoList.indexOf(eppInfoUiDto);
	 * 
	 * if (index >= 0) {
	 * 
	 * EppInfoUiDto dto = eppInfoList.get(index); if
	 * (!dto.getCardTypes().contains(cardType)) {
	 * 
	 * dto.getCardTypes().add(cardType); } } else {
	 * 
	 * Set<String> cardTypes = new HashSet<>();
	 * 
	 * cardTypes.add(cardType); eppInfoUiDto.setCardTypes(cardTypes);
	 * eppInfoList.add(eppInfoUiDto); } } } } } } } } }
	 * 
	 * Set<EppInfoUiDto> setWithUniqueValues = new HashSet<>(eppInfoList);
	 * 
	 * eppInfoList = new ArrayList<>(setWithUniqueValues);
	 * Collections.sort(eppInfoList);
	 * 
	 * return eppInfoList; }
	 * 
	 *//**
		* 
		*//*
			 * @Override public ValidationResponseDto
			 * validateTransactionRequest(GetTransactionDetailsRequestDto request) {
			 * EppInfoDto eppInfoDto = null;
			 * 
			 * try { if (request.getBody() == null || request.getSignInfo() == null) {
			 * 
			 * return new ValidationResponseDto(
			 * TransactionValidationCodes.REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode(),
			 * ErrorUtil.getProperty(
			 * TransactionValidationCodes.REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode()),
			 * null, null, 0, eppInfoDto); }
			 * 
			 * if (request.getBody().getPartnerKey() == null ||
			 * request.getBody().getPartnerKey().isEmpty()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, eppInfoDto);
			 * 
			 * }
			 * 
			 * if (request.getBody().getReferenceType() == null ||
			 * request.getBody().getReferenceType().isEmpty()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_REFERNECE_TYPE.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_REFERNECE_TYPE.
			 * getResponseCode()), null, null, 0, eppInfoDto); }
			 * 
			 * boolean flag = false; if (request.getBody().getReferenceType().equals("1") ||
			 * request.getBody().getReferenceType().equals("2") ||
			 * request.getBody().getReferenceType().equals("3") ||
			 * request.getBody().getReferenceType().equals("4")) { flag = true; }
			 * 
			 * if (!flag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_REFERNECE_TYPE.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_REFERNECE_TYPE.
			 * getResponseCode()), null, null, 0, eppInfoDto); }
			 * 
			 * if (request.getBody().getReferenceId() == null ||
			 * request.getBody().getReferenceId().isEmpty()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_REFERNECE_ID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_REFERNECE_ID.
			 * getResponseCode()), null, null, 0, eppInfoDto); }
			 * 
			 * if (request.getBody().getPageSize() == null ||
			 * request.getBody().getPageSize() < 0) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_PAGESIZE.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_PAGESIZE.
			 * getResponseCode()), null, null, 0, eppInfoDto); }
			 * 
			 * if (request.getBody().getPageNumber() == null ||
			 * request.getBody().getPageNumber() < 0) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_PAGENO.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_PAGENO.
			 * getResponseCode()), null, null, 0, eppInfoDto); }
			 * 
			 * APGPartner apgPartner =
			 * partnerDao.findPartnerByPartnerKey(request.getBody().getPartnerKey());
			 * 
			 * if (apgPartner == null) {
			 * 
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, eppInfoDto);
			 * 
			 * }
			 * 
			 * boolean accessFlag =
			 * validationDao.checkPartnerAccessRights(apgPartner.getPartnerId(),
			 * AccessFunctionConstants.GET_TRANSACTION_DETAILS);
			 * 
			 * if (!accessFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean signatureFlag = SignatureUtil.verifySignature(request,
			 * AESEncryptionDecryptionUrl.decode(apgPartner.getPvsSanketik()));
			 * 
			 * if (!signatureFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode()), null, null, 0, eppInfoDto); } return new
			 * ValidationResponseDto(TransactionValidationCodes.SUCCESS.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SUCCESS.getResponseCode()),
			 * null, AESEncryptionDecryptionUrl.decode(apgPartner.getPvsSanketik()),
			 * apgPartner.getPartnerUserKeyType(), eppInfoDto); }
			 * 
			 * catch (Exception exception) {
			 * 
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.TECHNICAL_ERROR_HAS_OCCURED.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.TECHNICAL_ERROR_HAS_OCCURED.
			 * getResponseCode()), null, null, 0, null); } }
			 * 
			 * @Override public ValidationResponseDto validateGetTransactionResult(
			 * GetTransactionResultRequestDto getTransactionResultRequestDto) {
			 * 
			 * if (getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getPartnerkey() == null ||
			 * getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getPartnerkey().isEmpty()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getTransactionId() == null ||
			 * getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getTransactionId().isEmpty()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getMaxPaymentCount() == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if ((getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getMaxPaymentCount() < 1 ||
			 * getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getMaxPaymentCount() > 100)) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * APGPartner partner = partnerDao.findPartnerByPartnerKey(
			 * getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getPartnerkey());
			 * 
			 * if (partner == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * Transaction transaction =
			 * transactionDao.getTransactionByTransactionRefIdAndPartnerKey(
			 * getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getTransactionId(),
			 * getTransactionResultRequestDto.getGetTransactionResultRequestBody().
			 * getPartnerkey());
			 * 
			 * if (transaction == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * String bufferTs = propertyUtils.getBufferTime();
			 * 
			 * OffsetDateTime odt =
			 * OffsetDateTime.parse(getTransactionResultRequestDto.getSignInfo().
			 * getTimeStamp()); Calendar cal =
			 * Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * cal.setTimeInMillis(odt.toInstant().toEpochMilli());
			 * 
			 * Long timeDiff = new Timestamp(System.currentTimeMillis()).getTime() -
			 * cal.getTimeInMillis();
			 * 
			 * if (timeDiff > Long.parseLong(bufferTs) || timeDiff < 0) {
			 * 
			 * return new ValidationResponseDto(TransactionValidationCodes.REQUEST_TIMEOUT.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.REQUEST_TIMEOUT.
			 * getResponseCode()), null, null, 0, null);
			 * 
			 * }
			 * 
			 * if (getTransactionResultRequestDto.getGetTransactionResultRequestBody() ==
			 * null || getTransactionResultRequestDto.getSignInfo() == null) {
			 * 
			 * return new ValidationResponseDto(
			 * TransactionValidationCodes.REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.
			 * REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean signatureFlag =
			 * SignatureUtil.verifySignature(getTransactionResultRequestDto,
			 * AESEncryptionDecryptionUrl.decode(partner.getPvsSanketik()));
			 * 
			 * if (!signatureFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean accessFlag =
			 * validationDao.checkPartnerAccessRights(partner.getPartnerId(),
			 * AccessFunctionConstants.GET_TRANSACTION_RESULT);
			 * 
			 * if (!accessFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.SUCCESS.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SUCCESS.getResponseCode()),
			 * null, AESEncryptionDecryptionUrl.decode(partner.getPvsSanketik()),
			 * partner.getPartnerUserKeyType(), null); }
			 * 
			 * @Override public ValidationResponseDto validateGetTransactionPaymentDetails(
			 * GetTransactionPaymentDetailsRequestDto
			 * getTransactionPaymentDetailsRequestDto) {
			 * 
			 * if (getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody() == null ||
			 * getTransactionPaymentDetailsRequestDto.getSignInfo() == null) {
			 * 
			 * return new ValidationResponseDto(
			 * TransactionValidationCodes.REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.
			 * REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode()), null, null, 0, null); }
			 * 
			 * if (getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getPartnerKey() == null ||
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getPartnerKey() .isEmpty()) {
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody() .getTransactionId() == null ||
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody() .getTransactionId().isEmpty()) {
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getPageSize() == null ||
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody() .getPageSize() < 0) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getPageNumber() == null ||
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody() .getPageNumber() < 0) { return
			 * new ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * APGPartner partner = partnerDao.findPartnerByPartnerKey(
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getPartnerKey());
			 * 
			 * if (partner == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean signatureFlag =
			 * SignatureUtil.verifySignature(getTransactionPaymentDetailsRequestDto,
			 * AESEncryptionDecryptionUrl.decode(partner.getPvsSanketik()));
			 * 
			 * if (!signatureFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean accessFlag =
			 * validationDao.checkPartnerAccessRights(partner.getPartnerId(),
			 * AccessFunctionConstants.GET_TRANSACTION_PAYMENT_DETAILS);
			 * 
			 * if (!accessFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * Transaction transaction =
			 * transactionDao.getTransactionByTransactionRefIdAndPartnerKey(
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getTransactionId(),
			 * getTransactionPaymentDetailsRequestDto.
			 * getGetTransactionPaymentDetailsRequestBody().getPartnerKey());
			 * 
			 * if (transaction == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * String bufferTs = propertyUtils.getBufferTime();
			 * 
			 * OffsetDateTime odt =
			 * OffsetDateTime.parse(getTransactionPaymentDetailsRequestDto.getSignInfo().
			 * getTimeStamp()); Calendar cal =
			 * Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * cal.setTimeInMillis(odt.toInstant().toEpochMilli());
			 * 
			 * Long timeDiff = new Timestamp(System.currentTimeMillis()).getTime() -
			 * cal.getTimeInMillis();
			 * 
			 * if (timeDiff > Long.parseLong(bufferTs) || timeDiff < 0) {
			 * 
			 * return new ValidationResponseDto(TransactionValidationCodes.REQUEST_TIMEOUT.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.REQUEST_TIMEOUT.
			 * getResponseCode()), null, null, 0, null);
			 * 
			 * }
			 * 
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.SUCCESS.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SUCCESS.getResponseCode()),
			 * null, AESEncryptionDecryptionUrl.decode(partner.getPvsSanketik()),
			 * partner.getPartnerUserKeyType(), null); }
			 * 
			 * @Override public ValidationResponseDto validateCancelRecurring(
			 * CancelRecurringTransactionRequestDto cancelRecurringTransactionRequestDto) {
			 * 
			 * if (cancelRecurringTransactionRequestDto.
			 * getCancelRecurringTransactionRequestBody() == null ||
			 * cancelRecurringTransactionRequestDto.getSignInfo() == null) {
			 * 
			 * return new ValidationResponseDto(
			 * TransactionValidationCodes.REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.
			 * REQUESTBODY_OR_SIGNATURE_EMPTY.getResponseCode()), null, null, 0, null); }
			 * 
			 * if (cancelRecurringTransactionRequestDto.
			 * getCancelRecurringTransactionRequestBody().getPartnerKey() == null ||
			 * cancelRecurringTransactionRequestDto.getCancelRecurringTransactionRequestBody
			 * ().getPartnerKey() .isEmpty()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (cancelRecurringTransactionRequestDto.
			 * getCancelRecurringTransactionRequestBody().getTransactionId() == null ||
			 * cancelRecurringTransactionRequestDto.getCancelRecurringTransactionRequestBody
			 * ().getTransactionId() .isEmpty()) {
			 * 
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * APGPartner partner = partnerDao.findPartnerByPartnerKey(
			 * cancelRecurringTransactionRequestDto.getCancelRecurringTransactionRequestBody
			 * ().getPartnerKey());
			 * 
			 * if (partner == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.MISSING_MANDATORY_FIELD.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean signatureFlag =
			 * SignatureUtil.verifySignature(cancelRecurringTransactionRequestDto,
			 * AESEncryptionDecryptionUrl.decode(partner.getPvsSanketik()));
			 * 
			 * if (!signatureFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SIGNATURE_MISMATCH.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * boolean accessFlag =
			 * validationDao.checkPartnerAccessRights(partner.getPartnerId(),
			 * AccessFunctionConstants.CANCEL_RECURRING);
			 * 
			 * if (!accessFlag) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_PARTNER_ACCESS.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * String bufferTs = propertyUtils.getBufferTime();
			 * 
			 * OffsetDateTime odt =
			 * OffsetDateTime.parse(cancelRecurringTransactionRequestDto.getSignInfo().
			 * getTimeStamp()); Calendar cal =
			 * Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * cal.setTimeInMillis(odt.toInstant().toEpochMilli());
			 * 
			 * Long timeDiff = new Timestamp(System.currentTimeMillis()).getTime() -
			 * cal.getTimeInMillis();
			 * 
			 * if (timeDiff > Long.parseLong(bufferTs) || timeDiff < 0) {
			 * 
			 * return new ValidationResponseDto(TransactionValidationCodes.REQUEST_TIMEOUT.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.REQUEST_TIMEOUT.
			 * getResponseCode()), null, null, 0, null);
			 * 
			 * }
			 * 
			 * Transaction transaction = transactionDao.getTransactionByTransactionRefId(
			 * cancelRecurringTransactionRequestDto.getCancelRecurringTransactionRequestBody
			 * ().getTransactionId());
			 * 
			 * if (transaction == null) { return new
			 * ValidationResponseDto(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.INVALID_TRANSACTIONID.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (!transaction.getTransType().equalsIgnoreCase(ApplicationConstants.
			 * TRANSACTION_RECURRING)) { return new
			 * ValidationResponseDto(TransactionValidationCodes.ONLY_RECURRING.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.ONLY_RECURRING.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (transaction.getTransType().equalsIgnoreCase(ApplicationConstants.
			 * TRANSACTION_RECURRING) && transaction.getStatus() !=
			 * TransactionEnums.TransactionStatus.SUCCESS.ordinal()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.ONLY_SUCCESS_RECURRING.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.ONLY_SUCCESS_RECURRING.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * if (transaction.getStatus() ==
			 * TransactionEnums.TransactionStatus.PENDING.ordinal()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.PENDING_PAYMENT.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.PENDING_PAYMENT.
			 * getResponseCode()), null, null, 0, null); }
			 * 
			 * List<TransactionPayment> transactionPaymentList = transaction.getPaymentID();
			 * 
			 * if (transactionPaymentList != null && !transactionPaymentList.isEmpty()) {
			 * 
			 * for (TransactionPayment transactionPayment : transactionPaymentList) {
			 * 
			 * if (transactionPayment.getStatus() ==
			 * TransactionPaymentStatus.PENDING.ordinal()) { return new
			 * ValidationResponseDto(TransactionValidationCodes.PENDING_RECURRING.
			 * getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.PENDING_RECURRING.
			 * getResponseCode()), null, null, 0, null); } } }
			 * 
			 * return new
			 * ValidationResponseDto(TransactionValidationCodes.SUCCESS.getResponseCode(),
			 * ErrorUtil.getProperty(TransactionValidationCodes.SUCCESS.getResponseCode()),
			 * null, AESEncryptionDecryptionUrl.decode(partner.getPvsSanketik()),
			 * partner.getPartnerUserKeyType(), null); }
			 * 
			 * @Override public String validateAccesstoken(GetRecurringAccessTokenRequestDto
			 * requestDto) throws Exception {
			 * 
			 * List<APGDownTimePeriod> apgDownTimePeriodList =
			 * downTimePeriodDao.getDowntime();
			 * 
			 * if (apgDownTimePeriodList != null && !apgDownTimePeriodList.isEmpty()) {
			 * 
			 * APGDownTimePeriod apgDownTimePeriod = apgDownTimePeriodList.get(0);
			 * 
			 * String fromTime = apgDownTimePeriod.getFromTimeUTC();
			 * 
			 * String[] arrfromTime = fromTime.split(":");
			 * 
			 * String toTime = apgDownTimePeriod.getToTimeUTC();
			 * 
			 * String[] arrtoTime = toTime.split(":");
			 * 
			 * Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * c1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrtoTime[0]));
			 * c1.set(Calendar.MINUTE, Integer.parseInt(arrtoTime[1]));
			 * c1.set(Calendar.SECOND, Integer.parseInt(arrtoTime[2]));
			 * 
			 * Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * c2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrfromTime[0]));
			 * c2.set(Calendar.MINUTE, Integer.parseInt(arrfromTime[1]));
			 * c2.set(Calendar.SECOND, Integer.parseInt(arrfromTime[2]));
			 * 
			 * Calendar current = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * 
			 * if ((c2.getTimeInMillis() <= current.getTimeInMillis()) &&
			 * (current.getTimeInMillis() <= c1.getTimeInMillis())) { throw new
			 * Exception(String.valueOf(RecurringEnum.SYSTEM_DOWNTIME.getResponseCode())); }
			 * } GetRecurringAccessBody recurringAccessBody =
			 * requestDto.getGetRecurringAccessBody(); GetRecurringAPIResponseBody
			 * recurringAccessResponse = new GetRecurringAPIResponseBody();
			 * 
			 * if (requestDto.getGetRecurringAccessBody() == null || requestDto.getSigInfo()
			 * == null) { throw new
			 * Exception(String.valueOf(RecurringEnum.REQUESTBODY_OR_SIGNATURE_EMPTY.
			 * getResponseCode())); } String bufferTs = propertyUtils.getBufferTime();
			 * 
			 * OffsetDateTime odt =
			 * OffsetDateTime.parse(requestDto.getSigInfo().getTimeStamp()); Calendar cal =
			 * Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * cal.setTimeInMillis(odt.toInstant().toEpochMilli());
			 * 
			 * Long timeDiff = new Timestamp(System.currentTimeMillis()).getTime() -
			 * cal.getTimeInMillis();
			 * 
			 * 
			 * if (timeDiff > Long.parseLong(bufferTs) || timeDiff < 0) {
			 * 
			 * throw new Exception(String.valueOf(RecurringEnum.REQUEST_TIMEOUT.
			 * getResponseCode()));
			 * 
			 * }
			 * 
			 * if (recurringAccessBody.getReferenceType().equals("1") &&
			 * (recurringAccessBody.getReferenceId() == null ||
			 * recurringAccessBody.getReferenceId().equals(""))) { throw new
			 * Exception(String.valueOf(RecurringEnum.PLEASEINSERT_TRANSACTION_ID.
			 * getResponseCode())); } else if
			 * (recurringAccessBody.getReferenceType().equals("2") &&
			 * (recurringAccessBody.getReferenceId() == null ||
			 * recurringAccessBody.getReferenceId().equals(""))) { throw new
			 * Exception(String.valueOf(RecurringEnum.PLEASE_INSERT_PARTNER_REFNO.
			 * getResponseCode())); } else if
			 * (recurringAccessBody.getReferenceType().equals("3") &&
			 * (recurringAccessBody.getReferenceId() == null ||
			 * recurringAccessBody.getReferenceId().equals(""))) { throw new
			 * Exception(String.valueOf(RecurringEnum.PLEASE_INSERT_PARTNER_USERKEY.
			 * getResponseCode())); } String encryptedSaanketik =
			 * authenticateSignature(requestDto); String backURL =
			 * requestDto.getGetRecurringAccessBody().getBackurl(); if (backURL != null &&
			 * !GenericUtil.validateURL(backURL)) { throw new
			 * Exception(String.valueOf(RecurringEnum.BACKURL_NOTFOUND.getResponseCode()));
			 * } if (encryptedSaanketik == null) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_SIGNATURE.getResponseCode()));
			 * } return encryptedSaanketik; }
			 * 
			 * public String authenticateSignature(GetRecurringAccessTokenRequestDto
			 * requestDto) throws Exception { String encryptedSanketik = null; String
			 * requestSignature = requestDto.getSigInfo().getSig(); String
			 * generatedSignature = null; String partnerKey =
			 * requestDto.getGetRecurringAccessBody().getPartnerKey(); if (partnerKey ==
			 * null || partnerKey.equals("")) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER.getResponseCode())); }
			 * APGPartner partner = partnerDao.findPartnerByPartnerKey(partnerKey); if
			 * (partner == null) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER.getResponseCode())); }
			 * else { boolean accessFlag =
			 * validationDao.checkPartnerAccessRights(partner.getPartnerId(),
			 * AccessFunctionConstants.GET_RECURRING_ACCESS_TOKEN);
			 * 
			 * if (!accessFlag) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER_ACCESS.getResponseCode
			 * ())); }
			 * 
			 * encryptedSanketik = partner.getPvsSanketik(); String partnerSanketik =
			 * AESEncryptionDecryptionUrl.decode(encryptedSanketik);
			 * 
			 * requestDto.generateSign(partnerSanketik); generatedSignature =
			 * requestDto.getSigInfo().getSig(); }
			 * 
			 * if (requestSignature == null || generatedSignature == null ||
			 * !(requestSignature.equals(generatedSignature))) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_SIGNATURE.getResponseCode()));
			 * }
			 * 
			 * return encryptedSanketik; }
			 * 
			 * @Override public String
			 * authenticateSignature(CancelRecurringTransactionRequestDto requestDto) throws
			 * Exception { String encryptedSanketik = null; String requestSignature =
			 * requestDto.getSignInfo().getSig(); String generatedSignature = null; String
			 * partnerKey =
			 * requestDto.getCancelRecurringTransactionRequestBody().getPartnerKey(); if
			 * (partnerKey == null || partnerKey.equals("")) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER.getResponseCode())); }
			 * APGPartner partner = partnerDao.findPartnerByPartnerKey(partnerKey); if
			 * (partner == null) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER.getResponseCode())); }
			 * else { boolean accessFlag =
			 * validationDao.checkPartnerAccessRights(partner.getPartnerId(),
			 * AccessFunctionConstants.CHANGE_RECURRING_CARD);
			 * 
			 * if (!accessFlag) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_PARTNER_ACCESS.getResponseCode
			 * ())); }
			 * 
			 * encryptedSanketik = partner.getPvsSanketik(); String partnerSanketik =
			 * AESEncryptionDecryptionUrl.decode(encryptedSanketik);
			 * 
			 * requestDto.generateSign(partnerSanketik); generatedSignature =
			 * requestDto.getSignInfo().getSig(); }
			 * 
			 * if (requestSignature == null || generatedSignature == null ||
			 * !(requestSignature.equals(generatedSignature))) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_SIGNATURE.getResponseCode()));
			 * }
			 * 
			 * return encryptedSanketik; }
			 * 
			 * @Override public void validateTransaction(List<Transaction> transactionList)
			 * throws Exception { if (transactionList == null || transactionList.isEmpty())
			 * { throw new Exception(String.valueOf(RecurringEnum.NOACTIVE_RECURRING_FOUND.
			 * getResponseCode())); }
			 * 
			 * Transaction transaction = transactionList.get(0); if
			 * (!transaction.getTransType().equalsIgnoreCase(ApplicationConstants.
			 * TRANSACTION_RECURRING)) { throw new
			 * Exception(String.valueOf(RecurringEnum.ONLY_SUCCESS_RECURRING.
			 * getResponseCode())); }
			 * 
			 * if (transaction.getTransType().equalsIgnoreCase(ApplicationConstants.
			 * TRANSACTION_RECURRING) && transaction.getStatus() !=
			 * TransactionEnums.TransactionStatus.SUCCESS.ordinal()) { }
			 * 
			 * }
			 * 
			 * @Override public String
			 * validateAccesstokenrecurring(CancelRecurringTransactionRequestDto requestDto)
			 * throws Exception { List<APGDownTimePeriod> apgDownTimePeriodList =
			 * downTimePeriodDao.getDowntime();
			 * 
			 * if(apgDownTimePeriodList!=null&&!apgDownTimePeriodList.isEmpty()) {
			 * 
			 * APGDownTimePeriod apgDownTimePeriod = apgDownTimePeriodList.get(0);
			 * 
			 * String fromTime = apgDownTimePeriod.getFromTimeUTC();
			 * 
			 * String[] arrfromTime = fromTime.split(":");
			 * 
			 * String toTime = apgDownTimePeriod.getToTimeUTC();
			 * 
			 * String[] arrtoTime = toTime.split(":");
			 * 
			 * Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * c1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrtoTime[0]));
			 * c1.set(Calendar.MINUTE, Integer.parseInt(arrtoTime[1]));
			 * c1.set(Calendar.SECOND, Integer.parseInt(arrtoTime[2]));
			 * 
			 * Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * c2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrfromTime[0]));
			 * c2.set(Calendar.MINUTE, Integer.parseInt(arrfromTime[1]));
			 * c2.set(Calendar.SECOND, Integer.parseInt(arrfromTime[2]));
			 * 
			 * Calendar current = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			 * 
			 * if ((c2.getTimeInMillis() <= current.getTimeInMillis()) &&
			 * (current.getTimeInMillis() <= c1.getTimeInMillis())) { throw new
			 * Exception(String.valueOf(RecurringEnum.SYSTEM_DOWNTIME.getResponseCode())); }
			 * } CancelRecurringTransactionRequestBody recurringAccessBody =
			 * requestDto.getCancelRecurringTransactionRequestBody();
			 * GetRecurringAPIResponseBody recurringResponse = new
			 * GetRecurringAPIResponseBody();
			 * 
			 * if(requestDto.getCancelRecurringTransactionRequestBody()==null||requestDto.
			 * getSignInfo()==null) { throw new
			 * Exception(String.valueOf(RecurringEnum.REQUESTBODY_OR_SIGNATURE_EMPTY.
			 * getResponseCode())); } String bufferTs = propertyUtils.getBufferTime();
			 * 
			 * OffsetDateTime odt =
			 * OffsetDateTime.parse(requestDto.getSignInfo().getTimeStamp()); Calendar cal =
			 * Calendar
			 * .getInstance(TimeZone.getTimeZone("UTC"));cal.setTimeInMillis(odt.toInstant()
			 * .toEpochMilli());
			 * 
			 * Long timeDiff = new Timestamp(System.currentTimeMillis()).getTime() -
			 * cal.getTimeInMillis();
			 * 
			 * String encryptedSaanketik = authenticateSignature(requestDto);
			 * 
			 * if(encryptedSaanketik==null) { throw new
			 * Exception(String.valueOf(RecurringEnum.INVALID_SIGNATURE.getResponseCode()));
			 * }return encryptedSaanketik; }
			 */

}