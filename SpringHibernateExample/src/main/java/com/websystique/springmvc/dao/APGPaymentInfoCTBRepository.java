package com.websystique.springmvc.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface APGPaymentInfoCTBRepository
		extends CrudRepository<com.websystique.springmvc.model.APGPaymentInfoCTB, Long>,
		JpaSpecificationExecutor<com.websystique.springmvc.model.APGPaymentInfoCTB> {

	@Query("SELECT p from APGPaymentInfoCTB p where p.paymentID=:paymentId")
	com.websystique.springmvc.model.APGPaymentInfoCTB findByPaymentId(@Param("paymentId") Long paymentid);
}
