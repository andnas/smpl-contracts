package com.example.contracts.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the companies database table.
 * 
 */
@Entity
@Table(name="companies")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String shortName;
	private DictOrgForm dictOrgForm;
	private List<Contract> contracts1;
	private List<Contract> contracts2;

	public Company() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COMPANY_ID")
	public int getId() {
		return this.id;
	}

	public void setId(int companyId) {
		this.id = companyId;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="SHORT_NAME")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	//bi-directional many-to-one association to DictOrgForm
	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="ORGFORM_ID")
	public DictOrgForm getDictOrgForm() {
		return this.dictOrgForm;
	}

	public void setDictOrgForm(DictOrgForm dictOrgForm) {
		this.dictOrgForm = dictOrgForm;
	}


	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="company1", fetch=FetchType.LAZY)
	public List<Contract> getContracts1() {
		return this.contracts1;
	}

	public void setContracts1(List<Contract> contracts1) {
		this.contracts1 = contracts1;
	}

	public Contract addContracts1(Contract contracts1) {
		getContracts1().add(contracts1);
		contracts1.setCompany1(this);

		return contracts1;
	}

	public Contract removeContracts1(Contract contracts1) {
		getContracts1().remove(contracts1);
		contracts1.setCompany1(null);

		return contracts1;
	}


	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="company2", fetch=FetchType.LAZY)
	public List<Contract> getContracts2() {
		return this.contracts2;
	}

	public void setContracts2(List<Contract> contracts2) {
		this.contracts2 = contracts2;
	}

	public Contract addContracts2(Contract contracts2) {
		getContracts2().add(contracts2);
		contracts2.setCompany2(this);

		return contracts2;
	}

	public Contract removeContracts2(Contract contracts2) {
		getContracts2().remove(contracts2);
		contracts2.setCompany2(null);

		return contracts2;
	}

}