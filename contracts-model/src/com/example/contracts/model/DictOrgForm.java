package com.example.contracts.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dict_orgforms database table.
 * 
 */
@Entity
@Table(name="dict_orgforms")
@NamedQuery(name="DictOrgForm.findAll", query="SELECT d FROM DictOrgForm d")
public class DictOrgForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String fullName;
	private String name;
	private List<Company> companies;

	public DictOrgForm() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORGFORM_ID")
	public int getId() {
		return this.id;
	}

	public void setId(int orgformId) {
		this.id = orgformId;
	}


	@Column(name="FULL_NAME")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="dictOrgForm")
	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Company addCompany(Company company) {
		getCompanies().add(company);
		company.setDictOrgForm(this);

		return company;
	}

	public Company removeCompany(Company company) {
		getCompanies().remove(company);
		company.setDictOrgForm(null);

		return company;
	}

}