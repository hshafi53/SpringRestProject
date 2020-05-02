package com.websystique.springmvc.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.model.Transaction;

@RestController
/* @RequestMapping(value = "/transaction") */
public class TransactionController {

	@Autowired
	com.websystique.springmvc.service.ICreateTransactionService createTransaction;

	@RequestMapping(value = "/tocreatetransaction", method = RequestMethod.GET)
	public ModelAndView createtrans(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("tocreatetransaction");
		mav.addObject("tocreatetransaction", new Transaction());
		return mav;
	}

	@RequestMapping(value = "/createtransaction", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public com.response.dto.CreateTransactionResponseDto createTransaction(
			@RequestBody com.request.dto.CreateTransactionRequestDto requestDto) throws URISyntaxException, Exception {
		System.out.println("starting" + requestDto);

		return createTransaction.createTransaction(requestDto);

	}

	@RequestMapping(value = "/transactionpayment", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public com.response.dto.CreateTransactionResponseDto transactionPayment(@RequestBody Transaction transaction) {
		System.out.println("transactionpayment API-------------");
		return createTransaction.createTransactionPayment(transaction);

	}

	/*
	 * @RequestMapping(value = CommonConstants.SUBMIT_TRANSACTION, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public SubmitTransactionRequestDto
	 * submitTransactionProcess(@RequestBody CreateTransactionRequestDto requestDto)
	 * {
	 * 
	 * return createTransaction.submitTransactionForRequest(requestDto);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.CHECK_TRANSACTION, method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public PaymentProviderExistDto checkTransactionForProvider(
	 * 
	 * @RequestParam(name = "providerKey", required = true) String providerKey) {
	 * 
	 * return createTransaction.checkTransactionProviderKey(providerKey);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.CHECK_CHANNEL, method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public ChannelExistDto checkChannelForChannelKey(
	 * 
	 * @RequestParam(name = "channelKey", required = true) String channelKey) {
	 * 
	 * return createTransaction.checkChannelForChannelKey(channelKey);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.CHECK_PARTNER, method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public PartnerExistDto checkPartnerForParnterKey(
	 * 
	 * @RequestParam(name = "partnerKey", required = true) String partnerKey) {
	 * 
	 * return createTransaction.checkPartnerForPartnerKey(partnerKey);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.DELETE_TRANASACTION, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public DeleteTransactionResponseDto deleteTransactionProcess(
	 * 
	 * @RequestBody DeleteTransactionSubmitDto deleteTransactionSubmit) {
	 * 
	 * return createTransaction
	 * .deleteTransactionForFailureSignatureVerification(deleteTransactionSubmit.
	 * getTransactionRefId());
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.PROCESS_RECURRING_TRANSACTION, method
	 * = RequestMethod.POST)
	 * 
	 * @ResponseBody public RecurringRequestListDto processRecurringTransaction(
	 * 
	 * @RequestBody RecurringScedularRequestDto recurringScedularRequestDto) {
	 * 
	 * return
	 * createTransaction.processRecurringTransaction(recurringScedularRequestDto);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.GET_RECURRING_SCHEDULED_TRANSACTIONS,
	 * method = RequestMethod.GET)
	 * 
	 * @ResponseBody public RecurringScedularRequestDto
	 * getRecurringScheduledTransactions() {
	 * 
	 * return createTransaction.getRecurringScheduledTransactions();
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.CANCEL_PAYMENT, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public BankPaymentPageResponseDto
	 * cancelTransaction(@RequestBody CancelRequestDto cancelRequestDto) {
	 * 
	 * return createTransaction.cancelTransaction(cancelRequestDto);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.EWALLET_CANCEL_PAYMENT, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public BankPaymentPageResponseDto
	 * ewalletcancelTransaction(@RequestBody EwalletCancelRequestDto
	 * cancelRequestDto) {
	 * 
	 * return createTransaction.ewalletcancelTransaction(cancelRequestDto);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.CANCEL_RECURRING_PAYMENT, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public BankPaymentPageResponseDto
	 * cancelRecurringTransaction(@RequestBody CancelRequestDto cancelRequestDto) {
	 * 
	 * return createTransaction.cancelRecurringTransaction(cancelRequestDto);
	 * 
	 * 
	 * }
	 * 
	 * @RequestMapping(value = CommonConstants.RESULT_FOR_BLACKLIST, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public BankPaymentPageResponseDto getResultForBlackList(
	 * 
	 * @RequestBody CardBlackListRequestDto cardBlackListRequestDto) {
	 * 
	 * return createTransaction.getResultForBlackList(cardBlackListRequestDto);
	 * 
	 * }
	 */
}
