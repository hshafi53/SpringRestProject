package com.websystique.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.TransactionUser;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<TransactionUser, Long> {

	@Query("Select amount from TransactionUser t where t.transactionUserID = :transactionUserID")
	public Long getBalanceByCustomerID(@Param("transactionUserID") Long transactionRefId);

}
