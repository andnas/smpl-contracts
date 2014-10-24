package com.example.contracts.services;

import java.util.List;

import com.example.contracts.model.Company;
import com.example.contracts.model.DictOrgForm;

public interface CompanyService {

    public Company getCompany(int id);

    public int createCompany(Company company);

    public void updateCompany(Company company);

    public void deleteCompany(int id);

    public List<Company> getCompanyList(Integer orgFormId);

    public List<DictOrgForm> findAllOrgForms();

    public void generateCompanies();

}
