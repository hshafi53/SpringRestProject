package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
@Table(name = "APG_TransactionItem")
public class TransactionItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransactionItemID")
	private Long transactionItemID;

	@Column(name = "Name")
	private String name;

	@Column(name = "Quantity")
	private Integer quantity;

	@Column(name = "UnitPrice")
	private Long unitPrice;

	
	private Long transactionid;

	public Long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}

	/**
	 * @return the transactionItemID
	 */
	public Long isTransactionItemID() {
		return transactionItemID;
	}

	/**
	 * @param transactionItemID the transactionItemID to set
	 */
	public void setTransactionItemID(Long transactionItemID) {
		this.transactionItemID = transactionItemID;
	}

	/**
	 * @return the partnerItemRef
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the qty
	 */

	/**
	 * @return the unitPrice
	 */
	public Long getUnitPrice() {
		return unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the sortOrder
	 */

	

	@Override
	public String toString() {
		return "TransactionItem [transactionItemID=" + transactionItemID + ",  name=" + name + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", ]";
	}

}
