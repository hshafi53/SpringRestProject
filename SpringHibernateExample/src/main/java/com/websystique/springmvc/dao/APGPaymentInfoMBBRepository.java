package com.websystique.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.websystique.springmvc.model.APGPaymentInfoMbb;

public interface APGPaymentInfoMBBRepository
		extends JpaRepository<com.websystique.springmvc.model.APGPaymentInfoMbb, Long> {

	/*
	 * public static String
	 * sqlstr="select * from APG_paymentInfo_MBB where p.paymentID=:paymentId";
	 * 
	 * @Query(sqlstr)
	 * 
	 * APGPaymentInfoMbb findByPaymentId(@Param("paymentId") Long paymentid);
	 */

	@Query("SELECT p from APGPaymentInfoMbb p where p.paymentId=:paymentId")
	APGPaymentInfoMbb findByPaymentId(@Param("paymentId") Long paymentid);

	@Modifying
	@Query("DELETE from APGPaymentInfoMbb where paymentID=:paymentID")
	void deleteByPaymentId(@Param("paymentID") Long paymentID);

}
