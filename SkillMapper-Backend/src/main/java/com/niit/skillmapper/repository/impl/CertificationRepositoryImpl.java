package com.niit.skillmapper.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.skillmapper.model.Certification;
import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.repository.CertificationRepository;

@Repository
@Transactional
public class CertificationRepositoryImpl implements CertificationRepository {

	private static final Logger log = LoggerFactory.getLogger(CertificationRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CertificationRepository  certificationRepository;
	
	@Override
	public boolean addCertification(Certification certification) {
		try{
			sessionFactory.getCurrentSession().save(certification);	
			log.debug("certification added successfully");
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteCertification(int certificationId) {
		try{
			sessionFactory.getCurrentSession().delete(getCertificationById(certificationId));	
			log.debug("certification deleted successfully");
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean updateCertification(Certification certification) {
		try{
			sessionFactory.getCurrentSession().update(certification);	
			log.debug("certification updated successfully");	
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}	}

	@Override
	public List<Certification> getAllCertification() {
		try{
			log.debug("Certification data is going to fetch");
			List<Certification> certificationList=sessionFactory.getCurrentSession().createQuery("from Certification").list();
			return certificationList;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}

	}

	@Override
	public List<Certification> getCertificationByEmployeeId(int employeeId) {
		try{
			log.debug("Employee certification  is going to fetch by employeeid");
			List<Certification> certificationList=(List<Certification>)sessionFactory.getCurrentSession().createQuery("from Certification where employeeId=:id").setParameter("id",employeeId).list();
			if(certificationList!=null)
			return certificationList;
			else
				return null;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}}

	@Override
	public Certification getCertificationById(int certificationId) {
		try{
			log.debug("certification data is going to fetch by certificationId");
			Certification certification=(Certification)sessionFactory.getCurrentSession().createQuery("from Certification where certificationId=:id").setParameter("id",certificationId).uniqueResult();
			if(certification!=null)
			return certification;
			else
				return null;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}
	
	}

	@Override
	public List<Certification> getCertificationByName(String certificationName) {
		try{
			log.debug("certification data is going to fetch by certificationName");
			List<Certification> certificationList=(List<Certification>)sessionFactory.getCurrentSession().createQuery("from Certification where certificationName=:Name").setParameter("Name",certificationName).list();
			if(certificationList!=null)
			return certificationList;
			else
				return null;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}
	}

	
}
