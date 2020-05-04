package com.websystique.springmvc.service;

import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.request.dto.CreateTransactionRequestDto;
import com.response.dto.CreateTransactionResponseDto;
import com.websystique.springmvc.model.Transaction;

public interface ICreateTransactionService {

	// public void saveUserPINStatus(String pinstatus);

	public CreateTransactionResponseDto createTransaction(CreateTransactionRequestDto createTransactionRequest)
			throws URISyntaxException, Exception;

	public CreateTransactionResponseDto createTransactionPayment(Transaction createTransactionRequest);

	public List<String> getCards(Long paymentId);

	public String getPinStatusByCid(Long id);

	public Long validateCCPin(Long pin, Long cid) throws JsonProcessingException;

	/*
	 * public CreateTransactionResponseDto
	 * createTransaction(CreateTransactionRequestDto createTransactionRequest);
	 * 
	 * public SubmitTransactionRequestDto
	 * submitTransactionForRequest(CreateTransactionRequestDto requestDto);
	 * 
	 * public DeleteTransactionResponseDto
	 * deleteTransactionForFailureSignatureVerification(String transactionRefId);
	 * 
	 * public RecurringRequestListDto
	 * processRecurringTransaction(RecurringScedularRequestDto
	 * recurringScedularRequestDto);
	 * 
	 * public RecurringScedularRequestDto getRecurringScheduledTransactions();
	 * 
	 * public GetTransactionResultResponseDto getTransactionResult(
	 * GetTransactionResultRequestDto getTransactionResultRequestDto);
	 * 
	 * public BankPaymentPageResponseDto cancelTransaction(CancelRequestDto
	 * cancelRequestDto);
	 * 
	 * public BankPaymentPageResponseDto
	 * ewalletcancelTransaction(EwalletCancelRequestDto cancelRequestDto);
	 * 
	 * public PaymentProviderExistDto checkTransactionProviderKey(String
	 * providerKey);
	 * 
	 * public GetTransactionPaymentDetailsResponseDto getTransactionPaymentDetails(
	 * GetTransactionPaymentDetailsRequestDto
	 * getTransactionPaymentDetailsRequestDto);
	 * 
	 * public BankPaymentPageResponseDto getResult(String refId);
	 * 
	 * public ChannelExistDto checkChannelForChannelKey(String channelKey);
	 * 
	 * public PartnerExistDto checkPartnerForPartnerKey(String partnerKey);
	 * 
	 * public CancelRecurringInternalResponseDto cancelRecurring(
	 * CancelRecurringTransactionRequestDto cancelRecurringTransactionRequestDto);
	 */

	public List<String> getTransactionDetails(Long transactionRefId);

	/*
	 * public BankPaymentPageResponseDto
	 * getResultForBlackList(CardBlackListRequestDto cardBlackListRequestDto);
	 * 
	 * public BankPaymentPageResponseDto cancelRecurringTransaction(CancelRequestDto
	 * cancelRequestDto);
	 */

}
