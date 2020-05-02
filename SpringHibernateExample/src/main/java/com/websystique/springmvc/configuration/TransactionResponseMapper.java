package com.websystique.springmvc.configuration;

import java.sql.Timestamp;

public class TransactionResponseMapper {

	private TransactionResponseMapper() {

	}

	public static com.response.dto.CreateTransactionResponseDto transactionResponseMapper(
			com.response.dto.ValidationResponseDto validationResponse, Long transrefId, Timestamp transtime,String token) {

		String message = "Transaction and payment proccessed";

		com.response.dto.CreateTransactionResponseBody createTransaction = new com.response.dto.CreateTransactionResponseBody();
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = new com.response.dto.CreateTransactionResponseDto();

		// Setting the CreateTransactionBody
		createTransaction.setResult(true);
		createTransaction.setErrorcode("200");
		createTransaction.setTransactionid(transrefId);
		createTransaction.setResultUIMessage(message);
		createTransaction.setToken(token);
		createTransaction.setTimeStamp(transtime);

		createTransactionResponse.setBody(createTransaction);
		return createTransactionResponse;

	}
	
	public static com.response.dto.CreateTransactionResponseDto transactionNEFTResponseMapper(
			com.response.dto.ValidationResponseDto validationResponse,Timestamp transtime,Long transrefid) {

		String message = "NEFT proccessed";

		com.response.dto.CreateTransactionResponseBody createTransaction = new com.response.dto.CreateTransactionResponseBody();
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = new com.response.dto.CreateTransactionResponseDto();

		// Setting the CreateTransactionBody
		createTransaction.setResult(true);
		createTransaction.setErrorcode("200");
		createTransaction.setTransactionid(transrefid);
		createTransaction.setResultUIMessage(message);
		System.out.println("time under responsemapper is"+transtime);
		createTransaction.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		createTransactionResponse.setBody(createTransaction);
		return createTransactionResponse;

	}

	public static com.response.dto.CreateTransactionResponseDto createTransactionErrorResponseMapper(
			com.response.dto.ValidationResponseDto validationResponse) {

		Integer statusCode = validationResponse.getCode();
		String message = validationResponse.getMessage();

		com.response.dto.CreateTransactionResponseBody createTransaction = new com.response.dto.CreateTransactionResponseBody();
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = new com.response.dto.CreateTransactionResponseDto();

		// Setting the CreateTransactionBody
		createTransaction.setResult(false);
		createTransaction.setErrorcode(statusCode.toString());
		createTransaction.setResultUIMessage(message);
		createTransactionResponse.setBody(createTransaction);

		return createTransactionResponse;

	}
	public static com.response.dto.CreateTransactionResponseDto createInvalidErrorResponseMapper(
			com.response.dto.ValidationResponseDto validationResponse) {

		Integer statusCode = 4500;
		String message = "Transaction canceeled due to invalid PIN";

		com.response.dto.CreateTransactionResponseBody createTransaction = new com.response.dto.CreateTransactionResponseBody();
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = new com.response.dto.CreateTransactionResponseDto();

		// Setting the CreateTransactionBody
		createTransaction.setResult(false);
		createTransaction.setErrorcode(statusCode.toString());
		createTransaction.setResultUIMessage(message);
		//createTransactionResponse.setBody(createTransaction);

		return createTransactionResponse;

	}

	/**
	 * This method is called to create the response for create transaction API in
	 * case when validation is failed we are creating signature without
	 * transactionrefid as we dont create a transaction and save in DB if there is
	 * validation error.
	 * 
	 * @param validationResponse
	 * @return
	 */
	/*
	 * public static CreateTransactionResponseDto
	 * createTransactionErrorResponseMapper( ValidationResponseDto
	 * validationResponse) {
	 * 
	 * Integer statusCode = validationResponse.getCode(); String message =
	 * validationResponse.getMessage(); String patrnerPassword =
	 * validationResponse.getPartnerSecret();
	 * 
	 * SignInfoResponse signInfoResponse = new SignInfoResponse();
	 * CreateTransactionResponseBody createTransaction = new
	 * CreateTransactionResponseBody(); CreateTransactionResponseDto
	 * createTransactionResponse = new CreateTransactionResponseDto(); String
	 * timeStamp = APGUtils.getTimestampForResponse(); String sig =
	 * APGUtils.getTimestampForSignature(timeStamp) + statusCode+false +
	 * patrnerPassword; String hashedSignature =
	 * SignatureUtil.generateSignature(sig);
	 * 
	 * 
	 * // Setting the signInfo signInfoResponse.setSig(hashedSignature);
	 * signInfoResponse.setTimestamp(timeStamp);
	 * signInfoResponse.setResultdescription(message);
	 * signInfoResponse.setResultuimessage(message);
	 * signInfoResponse.setResultUILanguage("Eng");
	 * 
	 * // Setting the CreateTransactionBody createTransaction.setResult(false);
	 * createTransaction.setErrorcode(statusCode.toString());
	 * 
	 * createTransactionResponse.setSignInfo(signInfoResponse);
	 * 
	 * createTransactionResponse.setBody(createTransaction);
	 * 
	 * return createTransactionResponse;
	 * 
	 * }
	 * 
	 * public static SignInfoResponse
	 * getSignatureResponseForSuccess(ValidationResponseDto validationResponse,
	 * GetTransactionDetailsRequestDto request, List<TransactionDetail>
	 * transactionDetails) {
	 * 
	 * Integer statusCode = validationResponse.getCode(); String message =
	 * validationResponse.getMessage(); String partnerPassword =
	 * validationResponse.getPartnerSecret();
	 * 
	 * SignInfoResponse signInfoResponse = new SignInfoResponse();
	 * 
	 * String sig = APGUtils.getTimestampForSignature() + statusCode +
	 * request.getBody().getPageNumber() + request.getBody().getPageSize() + 1 +
	 * transactionDetails + partnerPassword; String hashedSignature =
	 * SignatureUtil.generateSignature(sig); String timeStamp =
	 * APGUtils.getTimestampForResponse();
	 * 
	 * signInfoResponse.setSig(hashedSignature);
	 * signInfoResponse.setTimestamp(timeStamp);
	 * signInfoResponse.setResultdescription(message);
	 * signInfoResponse.setResultuimessage(message);
	 * signInfoResponse.setResultUILanguage("Eng");
	 * 
	 * return signInfoResponse;
	 * 
	 * }
	 * 
	 *//**
		 * 
		 * @param validationResponse
		 * @param getTransactionDetailsRequestDto
		 * @return GetTransactionDetailsResponseDto
		 *//*
			 * public static GetTransactionDetailsResponseDto mapTransactionDetailsResponse(
			 * ValidationResponseDto validationResponse, GetTransactionDetailsRequestDto
			 * getTransactionDetailsRequestDto) {
			 * 
			 * Integer statusCode = validationResponse.getCode(); String message =
			 * validationResponse.getMessage(); String partnerPassword =
			 * validationResponse.getPartnerSecret();
			 * 
			 * SignInfoResponse signInfoResponse = new SignInfoResponse();
			 * GetTransactionDetailsResponseBody body = new
			 * GetTransactionDetailsResponseBody(); GetTransactionDetailsResponseDto dto =
			 * new GetTransactionDetailsResponseDto();
			 * 
			 * String sig = APGUtils.getTimestampForSignature() + statusCode +
			 * getTransactionDetailsRequestDto.getBody().getPageNumber() +
			 * getTransactionDetailsRequestDto.getBody().getPageSize() + partnerPassword;
			 * String hashedSignature = SignatureUtil.generateSignature(sig); String
			 * timeStamp = APGUtils.getTimestampForResponse();
			 * 
			 * signInfoResponse.setSig(hashedSignature);
			 * signInfoResponse.setTimestamp(timeStamp);
			 * signInfoResponse.setResultdescription(message);
			 * signInfoResponse.setResultuimessage(message);
			 * signInfoResponse.setResultUILanguage("Eng");
			 * 
			 * body.setErrorCode(statusCode.toString());
			 * dto.setSignInfoResponse(signInfoResponse);
			 * 
			 * dto.setBody(body);
			 * 
			 * return dto;
			 * 
			 * } }
			 */
}
