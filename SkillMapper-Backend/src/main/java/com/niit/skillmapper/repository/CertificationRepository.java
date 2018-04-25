package com.niit.skillmapper.repository;

import java.util.List;

import com.niit.skillmapper.model.Certification;

public interface CertificationRepository {

	boolean addCertification(Certification certification);
	boolean deleteCertification(int certificationId);
	boolean updateCertification(Certification certification);
	List<Certification> getAllCertification();
	List<Certification> getCertificationByEmployeeId(int employeeId);
	Certification getCertificationById(int certificationId);
	List<Certification> getCertificationByName(String certificationName);
}
