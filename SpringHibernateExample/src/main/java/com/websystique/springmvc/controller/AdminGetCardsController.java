package com.websystique.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminGetCardsController {

	@Autowired
	private com.websystique.springmvc.service.ICreateTransactionService createTransactionService;

	@RequestMapping(value = com.websystique.springmvc.configuration.CommonConstants.GET_CARDS, method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCards(@PathVariable Long paymentId) {
		System.out.println("paymentId" + paymentId);
		return createTransactionService.getCards(paymentId);
	}

}
