package com.websystique.springmvc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.request.dto.ItemDesc;
import com.response.dto.CreateTransactionResponseBody;
import com.response.dto.CreateTransactionResponseDto;
import com.websystique.springmvc.configuration.GenerateTxnDtls;
import com.websystique.springmvc.dao.APGPaymentInfoMBBDaoImpl;
import com.websystique.springmvc.dao.CustomerDaoImpl;
import com.websystique.springmvc.model.APGPaymentInfoMbb;
import com.websystique.springmvc.model.Transaction;

@Service

public class CreateTransactionService implements ICreateTransactionService {

	@Autowired
	private com.websystique.springmvc.dao.PaymentInfoPBBDao paymentInfoPBBDao;

	@Autowired
	private com.websystique.springmvc.dao.PaymentInfoCTBDao paymentInfoCTBDao;

	@Autowired
	private com.websystique.springmvc.dao.PaymentInfoMBBDao paymentInfoMBBDao;

	@Autowired
	private com.websystique.springmvc.dao.TransactionDao transactionDao;

	@Autowired
	private CustomerDaoImpl custdaoimpl;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private APGPaymentInfoMBBDaoImpl apgmbbdao;

	private static String queueurl = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final SecureRandom secureRandom = new SecureRandom(); // threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); // threadsafe

	Map<String, Object> cotsMap = new HashMap<String, Object>();
	private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your
													// requirement.

	@Transactional
	public List<String> getCards(Long paymentId) {

		List<String> cardList = new ArrayList<>();

		if (paymentId != null) {

			APGPaymentInfoMbb apgPaymentInfoMbb = paymentInfoMBBDao.getMbbPaymentInfoByPaymentId(paymentId);
			String ccdisplay = apgPaymentInfoMbb.getCcDisplay();
			cardList.add(ccdisplay);
			System.out.println("MBB Bank CC card request processed" + cardList);

		}

		return cardList;

	}

	@Transactional
	public com.response.dto.CreateTransactionResponseDto createTransaction(
			com.request.dto.CreateTransactionRequestDto createTransaction) {

		com.response.dto.ValidationResponseDto validationResponse = null;
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = null;

		validationResponse = validationService.validateCreateTransaction(createTransaction);

		try {

			// status code 01 is validation success
			if (validationResponse.getCode() == 01) {

				try {
					if (createTransaction != null) {
						System.out.println("request is==========" + createTransaction);
						// Saving the Transaction

						Long transactionRefId = getRandomNumber();
						Long transactionitemid = getRandomNumber();
						Long balance = custdaoimpl.getBalanceByCustomerID(
								createTransaction.getCreateTransactionBody().getCustomerDetail().getCustomerId());

						System.out.println("balance from customer table" + balance);
						// if payment by card

						if (createTransaction.getCreateTransactionBody().getPaymentType().equals("Card")) {

							System.out.println("Payment through card service satarted-----------");
							String ccdisplay = paymentInfoMBBDao.getMbbPaymentInfoByCustomerId(
									createTransaction.getCreateTransactionBody().getCustomerDetail().getCustomerId());
							System.out.println("Card is-----------" + ccdisplay);
							Long custid = createTransaction.getCreateTransactionBody().getCustomerDetail()
									.getCustomerId();

							String api_host = "http://localhost:8080/SpringHibernateExample/";
							String commoncont = "getCardDetails/";
							String url = api_host + commoncont + ccdisplay + custid;
							System.out.println("URL =========================" + url);
							HttpEntity<String> entity = new HttpEntity<>(ccdisplay);
							RestTemplate rest = new RestTemplate();
							ResponseEntity<String> validationtransResponse = rest.exchange(url, HttpMethod.GET, entity,
									String.class);
							List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
							// Add the Jackson Message converter
							MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

							// Note: here we are making this converter to process any kind of response,
							// not only application/*json, which is the default behaviour
							converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
							messageConverters.add(converter);
							rest.setMessageConverters(messageConverters);

							TimeUnit.SECONDS.sleep(30);
							// This method is used to consume transactioncancel API for Invalid PIN
							String getfinalstatus = checkDBPinvalidation(false, custid);

							String validation = "Pin invalid and transaction cancelled";
							TimeUnit.SECONDS.sleep(30);
							System.out.println(
									"response from checkDBPinvalidation=====================" + getfinalstatus);
							if (getfinalstatus.equalsIgnoreCase(validation)) {
								System.out.println("inside pin validation failure condition===============");
								validationResponse = validationService.validateUserPin(getfinalstatus);
								System.out
										.println("validationResponse inside validation failure condition==============="
												+ validationResponse.getMessage());

								return createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
										.createPINInvalidErrorResponseMapper(validationResponse);
							} else
								System.out.println(
										"pin successfully validated from ValidateCCPin API in transaction process");

						}

						com.websystique.springmvc.model.Transaction transaction = com.websystique.springmvc.configuration.TransactionMapper
								.createTransactionMapper(createTransaction, balance, transactionRefId,
										transactionitemid, validationResponse);
						if (transaction != null) {
							transactionDao.saveTransaction(transaction);

							System.out.println("transaction saved success");
							// ******************This code is to generate invoice file
							// start*********************
							List<ItemDesc> itemlst = new ArrayList<ItemDesc>();
							itemlst = createTransaction.getCreateTransactionBody().getItemDesc();
							String itemname = null;
							int qty = 0;
							Long price = 0L;
							for (ItemDesc invLine : itemlst) {
								itemname = invLine.getName();
								qty = invLine.getQty();
								price = invLine.getUnitprice();
							}
							cotsMap.put("custid",
									createTransaction.getCreateTransactionBody().getCustomerDetail().getCustomerId());
							cotsMap.put("custname",
									createTransaction.getCreateTransactionBody().getCustomerDetail().getCustomerName());
							cotsMap.put("itemname", itemname);
							cotsMap.put("quantity", qty);
							cotsMap.put("price", price);

							cotsMap.put("transref", transactionRefId);
							cotsMap.put("transtype", createTransaction.getCreateTransactionBody().getTransType());
							cotsMap.put("paymenttype", createTransaction.getCreateTransactionBody().getPaymentType());

							// generate Txn and write to file
							GenerateTxnDtls gtxnd = new GenerateTxnDtls();
							gtxnd.processProcessToCots(cotsMap);
							// ******************This code is to generate invoice file
							// end*********************

							// ****************** To Give Call to Transaction payment API for New Payment
							String url = "http://localhost:8080/SpringHibernateExample/" + "transactionpayment";
							CreateTransactionResponseDto response = new CreateTransactionResponseDto();
							CreateTransactionResponseBody body = new CreateTransactionResponseBody();
							response.setBody(body);
							ObjectMapper mapperObj = new ObjectMapper();
							String jsonStr;
							try {
								jsonStr = mapperObj.writeValueAsString(transaction);
								System.out.println("jsonStr----------" + jsonStr);

								HttpHeaders headers = new HttpHeaders();
								headers.setContentType(MediaType.APPLICATION_JSON);
								// headers.set(CommonConstants.USER_AGENT_KEY,
								// CommonConstants.USER_AGENT_VALUE);
								HttpEntity<String> entity = new HttpEntity<>(jsonStr, headers);
								RestTemplate rest = new RestTemplate();
								ResponseEntity<CreateTransactionResponseDto> validationtransResponse = rest
										.exchange(url, HttpMethod.POST, entity, CreateTransactionResponseDto.class);

								String custname = createTransaction.getCreateTransactionBody().getCustomerDetail()
										.getCustomerName();
								// *********This is test method to pass name and ref ID to Queue using
								// ActiveMQ*********
								writeToQueue(custname, transactionRefId);

								// Save final balance
								savefinalbal(transaction.getBalance(), createTransaction.getCreateTransactionBody()
										.getCustomerDetail().getCustomerId(), transaction.getTransactionID());

							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}

						}

						else
							System.out.println("transaction not success");

						String token = generateNewToken();
						System.out.println("----------------------token----------------" + token);
						// Creating the transaction response
						createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
								.transactionResponseMapper(validationResponse, transactionRefId,
										transaction.getCreateDateUTC(), token);

					} else {
						// Use ValidationEnums for error code and message
						validationResponse = new com.response.dto.ValidationResponseDto(
								com.websystique.springmvc.configuration.ValidationEnums.TransactionValidationCodes.INVALID_CUSTOMER_DETAIL
										.getResponseCode(),
								"");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// Status Code is not 01 then check for other status codes sent
				createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
						.createTransactionErrorResponseMapper(validationResponse);
			}
		} catch (Exception ex) {

			validationResponse = new com.response.dto.ValidationResponseDto(
					com.websystique.springmvc.configuration.ValidationEnums.TransactionValidationCodes.TECHNICAL_ERROR_HAS_OCCURED
							.getResponseCode(),
					"Technical error occured");
			createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
					.createTransactionErrorResponseMapper(validationResponse);
			return createTransactionResponse;
		}
		return createTransactionResponse;
	}

	@Transactional
	public com.response.dto.CreateTransactionResponseDto createTransactionPayment(Transaction transaction) {
		System.out.println("inside payment service method");
		com.response.dto.ValidationResponseDto validationResponse = null;
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = null;

		try {

			try {
				if (transaction != null) {
					System.out.println("transaction from transaction API" + transaction);
					// Saving the Transaction

					Long paymentRefId = getRandomNumber();
					com.websystique.springmvc.model.TransactionPayment transactionpayment = com.websystique.springmvc.configuration.TransactionMapper
							.createTransactionPaymentMapper(transaction, paymentRefId);

					if (transaction != null) {
						transactionDao.saveTransactionPayment(transactionpayment);
						System.out.println("transaction Payment saved success");

					}

					else
						System.out.println("transaction payment not success");
					System.out.println("current date and time" + transaction.getCreateDateUTC());
					String token = generateNewToken();
					// Creating the transaction response
					createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
							.transactionResponseMapper(validationResponse, paymentRefId, transaction.getCreateDateUTC(),
									token);

				} else {
					// Use ValidationEnums for error code and message
					validationResponse = new com.response.dto.ValidationResponseDto(
							com.websystique.springmvc.configuration.ValidationEnums.TransactionValidationCodes.INVALID_CUSTOMER_DETAIL
									.getResponseCode(),
							"");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {

			validationResponse = new com.response.dto.ValidationResponseDto(
					com.websystique.springmvc.configuration.ValidationEnums.TransactionValidationCodes.TECHNICAL_ERROR_HAS_OCCURED
							.getResponseCode(),
					"Technical error occured");
			createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
					.createTransactionErrorResponseMapper(validationResponse);
			return createTransactionResponse;
		}
		return createTransactionResponse;
	}

	public static String getTime() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);

	}

	public static Long getRandomNumber() {
		Random rand = new Random();
		String id = String.format("%04d", rand.nextInt(10000));
		// Generate random integers in range 0 to 999
		Long rand_int1 = Long.parseLong(id);
		System.out.println("refid random" + rand_int1);
		return rand_int1;
	}

	@Transactional
	public Long validateCCPin(Long pin, Long cid) throws JsonProcessingException {
		// boolean invalidpinchk = false;

		System.out.println(" PIN from user in validateCCPin method" + pin);

		Long pinfromDB = apgmbbdao.getCustomerByPIN(pin, cid);
		if (pin.equals(pinfromDB)) {
			System.out.println("PIN successfully validated from DB==========" + pinfromDB);

			String jsonStrpinvalid = "Pin Validated";
			paymentInfoMBBDao.saveUserPinStatus(cid, jsonStrpinvalid);

		} else if (!pin.equals(pinfromDB)) {

			System.out.println("Pin invalid-----------------------" + pinfromDB);

			String jsonStrpinInvalid = "Pin invalid and transaction cancelled";

			paymentInfoMBBDao.saveUserPinStatus(cid, jsonStrpinInvalid);

		}
		return pinfromDB;
	}

	public List<String> getTransactionDetails(Long transactionRefId) {

		List<String> transaction = transactionDao.getTransactionByTransactionRefId(transactionRefId);

		if (transaction != null) {

			System.out.println("Transaction details are" + transaction);
		}

		return transaction;
	}

	public void savefinalbal(Long finalbal, Long custid, Long transactionid) {
		System.out.println("final balance and id from service method is-----------" + finalbal + "---" + custid);
		custdaoimpl.updateBal(finalbal, custid, transactionid);
	}

	public void writeToQueue(String custname, Long transrefid) throws JMSException {
		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(queueurl);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Creating a non transactional session to send/receive JMS message.
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination represents here our queue 'JCG_QUEUE' on the JMS server.
		// The queue will be created automatically on the server.
		Destination destination = session.createQueue(subject);

		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello World!!!'
		TextMessage message = session.createTextMessage(custname);

		ObjectMessage omo = session.createObjectMessage();
		omo.setObject(new Long(transrefid));

		// Here we are sending our message!
		producer.send(message);
		producer.send(omo);

		System.out.println("Sending customer name via queue '" + message.getText() + "'");

		// Getting the queue 'JCG_QUEUE'
		Destination destinationmsg = session.createQueue(subject);

		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destinationmsg);

		// Here we receive the message.
		Message messagere = consumer.receive();

		ObjectMessage omi = (ObjectMessage) consumer.receive();
		System.out.println("Receieved transaction ref ID from sender" + (Long) omi.getObject());

		// We will be using TestMessage in our example. MessageProducer sent us a
		// TextMessage
		// so we must cast to it to get access to its .getText() method.
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) messagere;
			System.out.println("Received customer name from sender '" + textMessage.getText() + "'");
		}
		connection.close();
	}

	public static String generateNewToken() {
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

	public String checkDBPinvalidation(boolean transcancel, Long cid) {
		String output = "";
		String out = "";

		try {

			String api_host = "http://localhost:8080/SpringHibernateExample/";
			String commoncont = "transactioncancel/";
			String url = api_host + commoncont + cid;
			System.out.println("URL to hit gettransactioncancel" + url);
			URL urlc = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlc.openConnection();
			conn.setRequestMethod("GET"); // conn.setRequestProperty("Accept",
			// "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			output = br.readLine();

			System.out.println("Output from transaction cancel API==============================" + output);
			transcancel = true;
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return output;
	}

	@Transactional
	public String getPinStatusByCid(Long cid) {
		String status = paymentInfoMBBDao.getPinStatusByCid(cid);
		return status;
	}
}
