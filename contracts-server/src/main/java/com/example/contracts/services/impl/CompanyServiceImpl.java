package com.example.contracts.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.contracts.model.Company;
import com.example.contracts.model.DictOrgForm;
import com.example.contracts.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @PersistenceContext
    private EntityManager entityManager; // = EntityManagerUtil.getEntityManager();

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
	//this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Company getCompany(int id) {
	return entityManager.find(Company.class, id);
    }

    // @Override
    // public int createCompany(Company company) {
    // try {
    // entityManager.getTransaction().begin();
    // entityManager.persist(company);
    // entityManager.getTransaction().commit();
    // return company.getId();
    // } catch (Throwable e) {
    // e.printStackTrace();
    // entityManager.getTransaction().rollback();
    // return 0;
    // }
    // }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int createCompany(Company company) {
	entityManager.persist(company);
	return company.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCompany(Company company) {
	// TODO Auto-generated method stub

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCompany(int id) {
	Company company = entityManager.find(Company.class, id);
	entityManager.remove(company);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Company> getCompanyList(Integer orgFormId) {
	if (orgFormId == null) {
	    @SuppressWarnings("unchecked")
	    List<Company> companies = entityManager.createNamedQuery("Company.findAll").getResultList();
	    return companies;
	} else {
	    Query query = entityManager.createQuery("select c from Company c where c.orgForm.id = :orgFormId");
	    query.setParameter("orgFormId", orgFormId);
	    @SuppressWarnings("unchecked")
	    List<Company> companies = query.getResultList();
	    return companies;
	}

    }

    // @Override
    // public Collection<DictOrgForm> findAllOrgForms() {
    // try {
    // entityManager.getTransaction().begin();
    // @SuppressWarnings("unchecked")
    // List<DictOrgForm> orgForms =
    // entityManager.createNamedQuery("DictOrgForm.findAll").getResultList();
    // entityManager.getTransaction().commit();
    // return orgForms;
    // } catch (Throwable e) {
    // e.printStackTrace();
    // entityManager.getTransaction().rollback();
    // return Collections.emptyList();
    // }
    // }
    //

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<DictOrgForm> findAllOrgForms() {
	@SuppressWarnings("unchecked")
	List<DictOrgForm> orgForms = entityManager.createNamedQuery("DictOrgForm.findAll").getResultList();
	return orgForms;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateCompanies() {
	Long count = (Long) entityManager.createQuery("select count(c) from Company c").getSingleResult();
	if (count == 0) {
	    List<DictOrgForm> orgForms = findAllOrgForms();
	    int i = 0;
	    int orgFormsCount = orgForms.size();
	    for (int j = i; j < 20; j++) {
		Company company = new Company();
		company.setDictOrgForm(orgForms.get((int) Math.round(Math.random() * (orgFormsCount - 1))));
		company.setName("Полное наименование компании " + j);
		company.setShortName("Компания " + j);
		createCompany(company);
	    }
	}
    }

}
