package com.websystique.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.request.dto.NewNEFTRequestDto;
import com.websystique.springmvc.dao.CustomerDaoImpl;
import com.websystique.springmvc.dao.TransactionDao;
import com.websystique.springmvc.model.TransactionUser;

@Service
public class NEFTService {
	@Autowired
	private CustomerDaoImpl custdaoimpl;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private TransactionDao transactionDao;

	@Transactional
	public com.response.dto.CreateTransactionResponseDto createNEFTPayment(NewNEFTRequestDto createTransaction) {
		com.response.dto.ValidationResponseDto validationResponse = null;
		com.response.dto.CreateTransactionResponseDto createTransactionResponse = null;
		System.out.println("inside neft service method" + createTransaction);
		String chksendername = createTransaction.getCreateTransactionBody().getSender_Name();
		String chkreceiverName = createTransaction.getCreateTransactionBody().getBeneficiary_Name();

		TransactionUser transsenderuser = transactionDao.getSenderDetailsBySenderName(chksendername);
		TransactionUser transbeneuser = transactionDao.getBeneDetailsByBeneName(chkreceiverName);

		validationResponse = validationService.validateNewNEFTRequest(createTransaction, transsenderuser,
				transbeneuser);

		try {

			// status code 01 is validation success need to check for unique
			// partnerref number
			if (validationResponse.getCode() == 01) {

				try {
					if (createTransaction != null) {
						System.out.println("createTransaction" + createTransaction);
						// Saving the Transaction

						Long Senderbalance = custdaoimpl.getBalanceByCustomerName(
								createTransaction.getCreateTransactionBody().getSender_Name());

						System.out.println("sender current balance from customer table" + Senderbalance);
						Long Receiverbalance = custdaoimpl.getBalanceByCustomerName(
								createTransaction.getCreateTransactionBody().getBeneficiary_Name());

						System.out.println("reciever current balance from customer table" + Receiverbalance);

						Long creditBal = createTransaction.getCreateTransactionBody().getAMOUNT() + Receiverbalance;

						Long debitBal = Senderbalance - createTransaction.getCreateTransactionBody().getAMOUNT();

						String sendername = createTransaction.getCreateTransactionBody().getSender_Name();
						String benename = createTransaction.getCreateTransactionBody().getBeneficiary_Name();

						custdaoimpl.updateDebitNEFTBal(debitBal, sendername);
						custdaoimpl.updatecreditNEFTBal(creditBal, benename);

						com.websystique.springmvc.model.NEFTDetails neftdetails = com.websystique.springmvc.configuration.TransactionMapper
								.createNEFTTransactionMapper(createTransaction);

						if (neftdetails != null) {
							transactionDao.saveNEFT(neftdetails);

							System.out.println("NEFT Data  saved success");

						}

						else
							System.out.println("NEFT not success");
						Long transrefid = transactionDao.getNEFTDetailsBySenderName(sendername);
						System.out.println("transaction id from NEFT Table" + transrefid);

						// Creating the transaction response
						createTransactionResponse = com.websystique.springmvc.configuration.TransactionResponseMapper
								.transactionNEFTResponseMapper(validationResponse, neftdetails.getTransferdate(),
										transrefid);

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

}
