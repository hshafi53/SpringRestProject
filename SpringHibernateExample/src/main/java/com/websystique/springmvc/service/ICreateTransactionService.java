package com.websystique.springmvc.service;

import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.request.dto.CreateTransactionRequestDto;
import com.response.dto.CreateTransactionResponseDto;
import com.websystique.springmvc.model.APGPaymentInfoMbb;
import com.websystique.springmvc.model.Transaction;

/*import com.apg.v2.api.common.dto.request.CancelRecurringTransactionRequestDto;
import com.apg.v2.api.common.dto.request.CancelRequestDto;
import com.apg.v2.api.common.dto.request.CardBlackListRequestDto;
import com.apg.v2.api.common.dto.request.CreateTransactionRequestDto;
import com.apg.v2.api.common.dto.request.EwalletCancelRequestDto;
import com.apg.v2.api.common.dto.request.GetTransactionPaymentDetailsRequestDto;
import com.apg.v2.api.common.dto.request.GetTransactionResultRequestDto;
import com.apg.v2.api.common.dto.request.RecurringRequestListDto;
import com.apg.v2.api.common.dto.request.RecurringScedularRequestDto;
import com.apg.v2.api.common.dto.request.SubmitTransactionRequestDto;
import com.apg.v2.api.common.dto.response.BankPaymentPageResponseDto;
import com.apg.v2.api.common.dto.response.CancelRecurringInternalResponseDto;
import com.apg.v2.api.common.dto.response.CreateTransactionResponseDto;
import com.apg.v2.api.common.dto.response.DeleteTransactionResponseDto;
//import com.apg.v2.api.common.dto.response.EwalletCancelResponsedto;
import com.apg.v2.api.common.dto.response.GetTransactionPaymentDetailsResponseDto;
import com.apg.v2.api.common.dto.response.GetTransactionResultResponseDto;
import com.apg.v2.api.response.dto.ChannelExistDto;
import com.apg.v2.api.response.dto.PartnerExistDto;
import com.apg.v2.api.response.dto.PaymentProviderExistDto;*/

public interface ICreateTransactionService {
	
	public CreateTransactionResponseDto
	  createTransaction(CreateTransactionRequestDto createTransactionRequest) throws URISyntaxException, Exception;
	
	public CreateTransactionResponseDto
	createTransactionPayment(Transaction createTransactionRequest);
	public List<String> getCards(Long paymentId);
	
	public Long validateCCPin(Long pin) throws JsonProcessingException;

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
