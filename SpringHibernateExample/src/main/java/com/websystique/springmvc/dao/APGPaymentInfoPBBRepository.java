package com.websystique.springmvc.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.websystique.springmvc.model.APGPaymentInfoPBB;

public interface APGPaymentInfoPBBRepository
		extends CrudRepository<APGPaymentInfoPBB, Long>, JpaSpecificationExecutor<APGPaymentInfoPBB> {

	@Query("SELECT p from APGPaymentInfoPBB p where p.paymentID=:paymentId")
	APGPaymentInfoPBB getPBBPaymentInfoByPaymentId(@Param("paymentId") Long paymentId);

}
