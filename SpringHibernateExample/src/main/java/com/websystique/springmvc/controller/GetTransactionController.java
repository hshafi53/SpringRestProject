package com.websystique.springmvc.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.websystique.springmvc.model.APGPaymentInfoMbb;
import com.websystique.springmvc.model.Transaction;
import com.websystique.springmvc.service.ICreateTransactionService;

@Controller
public class GetTransactionController {

	@RequestMapping(value = "/gettransaction", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("gettransaction");
		mav.addObject("gettransaction", new Transaction());
		return mav;
	}

	@Autowired
	private ICreateTransactionService createTransactionService;

	@RequestMapping(value = "/gettransactionDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView getCards(@RequestParam("transactionRefId") Long transactionRefId) {
		ModelAndView mav = new ModelAndView("getrans");
		List getrans = new ArrayList();
		System.out.println(transactionRefId);
		if (transactionRefId != null) {
			getrans = createTransactionService.getTransactionDetails(transactionRefId);
			mav.addObject("getrans", getrans);
		} else {
			mav.addObject("getrans", "Transaction not exisits or invalid ID");
		}
		return mav;

	}

	@RequestMapping(value = "/getCardDetails/{ccvalue}", method = RequestMethod.GET)
	public ModelAndView getCardDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String spliturl = "http://localhost:8080/SpringHibernateExample/getCardDetails/Mxx12345671";
		System.out.println("Splited URL" + spliturl.length());

		String getstrcard = spliturl.substring(60, 70);
		String getstrcustid = spliturl.substring(70, spliturl.length());
		System.out.println("Splited substring card" + getstrcard);
		System.out.println("Splited substring cust id" + getstrcustid);
		ModelAndView mav = new ModelAndView("getCardDetails");
		mav.addObject("getCardDetails", new APGPaymentInfoMbb());

		mav.addObject("getstrcustid", getstrcustid);
		return mav;
	}

	@RequestMapping(value = "/validatepinforCC", method = RequestMethod.POST)
	public ModelAndView validateCards(@RequestParam("pin") Long cardpin, @RequestParam("customerId") Long cid)
			throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("validateCCpin");

		System.out.println("USer pin from page" + cardpin);
		if (cardpin != null) {
			Long getpin = createTransactionService.validateCCPin(cardpin, cid);
			System.out.println("Pin from DB in validatepinforCC controller==========" + getpin);
			if (getpin.equals(cardpin)) {
				mav.addObject("validateCCpin", "Pin Validated");
			}

			if (!getpin.equals(cardpin)) {
				mav.addObject("validateCCpin", "invalid pin Please try to initiate payment again");
			}
		} else {
			System.out.println("Pin not entered by user");
		}
		return mav;

	}

	@RequestMapping(value = "/transactioncancel/{id}", method = RequestMethod.GET)

	public ResponseEntity<String> canceltrans(@PathVariable Long id) throws URISyntaxException, Exception {
		System.out.println(
				"Customer ID from validateCCpin API in transactioncancel API controller======================-" + id);
		String finalstatus = createTransactionService.getPinStatusByCid(id);
		System.out.println("List from transaction cancel API====================" + finalstatus);
		return new ResponseEntity<String>(finalstatus, HttpStatus.OK);
	}

}
