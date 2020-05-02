package com.websystique.springmvc.service;

import com.request.dto.NewNEFTRequestDto;
import com.websystique.springmvc.model.TransactionUser;

public interface ValidationService {

	public com.response.dto.ValidationResponseDto validateCreateTransaction(
			com.request.dto.CreateTransactionRequestDto createTransactionRequestDto);
	
	public com.response.dto.ValidationResponseDto validateNewNEFTRequest(NewNEFTRequestDto neftreqdto,TransactionUser valsender,TransactionUser valbene) ;


}
