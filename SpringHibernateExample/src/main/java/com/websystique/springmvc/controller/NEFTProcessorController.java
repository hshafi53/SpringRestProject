package com.websystique.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.service.NEFTService;

@RestController

public class NEFTProcessorController {
	@Autowired
	private NEFTService neftservice;

	@RequestMapping(value = "/neftpaymentprocess", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public com.response.dto.CreateTransactionResponseDto createTransaction(
			@RequestBody com.request.dto.NewNEFTRequestDto requestDto) {
		System.out.println("starting NEFT Payment Initiation service" + requestDto);

		return neftservice.createNEFTPayment(requestDto);

	}

}
