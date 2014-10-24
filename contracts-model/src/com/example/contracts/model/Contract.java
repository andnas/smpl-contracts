package com.example.contracts.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the contracts database table.
 * 
 */
@Entity
@Table(name="contracts")
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	private int contractId;
	private Date contractDate;
	private String contractNumber;
	private String contractorNumber;
	private String customerNumber;
	private byte mainNumberFlag;
	private Company company1;
	private Company company2;

	public Contract() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CONTRACT_ID")
	public int getContractId() {
		return this.contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="CONTRACT_DATE")
	public Date getContractDate() {
		return this.contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}


	@Column(name="CONTRACT_NUMBER")
	public String getContractNumber() {
		return this.contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}


	@Column(name="CONTRACTOR_NUMBER")
	public String getContractorNumber() {
		return this.contractorNumber;
	}

	public void setContractorNumber(String contractorNumber) {
		this.contractorNumber = contractorNumber;
	}


	@Column(name="CUSTOMER_NUMBER")
	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	@Column(name="MAIN_NUMBER_FLAG")
	public byte getMainNumberFlag() {
		return this.mainNumberFlag;
	}

	public void setMainNumberFlag(byte mainNumberFlag) {
		this.mainNumberFlag = mainNumberFlag;
	}


	//bi-directional many-to-one association to Company
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACTOR_ID")
	public Company getCompany1() {
		return this.company1;
	}

	public void setCompany1(Company company1) {
		this.company1 = company1;
	}


	//bi-directional many-to-one association to Company
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	public Company getCompany2() {
		return this.company2;
	}

	public void setCompany2(Company company2) {
		this.company2 = company2;
	}

}