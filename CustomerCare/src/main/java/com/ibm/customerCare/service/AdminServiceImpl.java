package com.ibm.customerCare.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.customerCare.exceptions.DepartmentNotFoundException;
import com.ibm.customerCare.exceptions.DuplicateCustomerException;
import com.ibm.customerCare.exceptions.OperatorNotFoundException;
import com.ibm.customerCare.model.Department;
import com.ibm.customerCare.model.Operator;
import com.ibm.customerCare.repo.DepartmentDao;
import com.ibm.customerCare.repo.OperatorDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	DepartmentDao deptDao;
	
	@Autowired
	OperatorDao operatorDao;
	
	
	
	@Override
	public String addDepartment(Department d) {
		boolean exists = deptDao.existsById(d.getDepartmentId());
		if(exists) {
			throw new DuplicateCustomerException("Department already have Id="+d.getDepartmentId());
		}

		deptDao.save(d);
		return "Department Created";
	}

	@Override
	public String removeDepartment(int id) {
		boolean exists = deptDao.existsById(id);
		if(!exists) throw new DepartmentNotFoundException("Department not found with Id=" + id);

		deptDao.deleteById(id);

		return "Department Deleted ";
	}

	@Override
	public String modifyDepartment(Department d) {
		boolean exists = deptDao.existsById(d.getDepartmentId());
		if(!exists) throw new DepartmentNotFoundException("Department not found with Id=" + d.getDepartmentId());

		deptDao.save(d);
		return "Department Updated";
	}

	@Override
	public Department findDepartmentById(int id) {
		Department department = deptDao.findById(id).get();
		return department;
	}

	@Override
	public String addOpertaor(Operator o) {
		boolean exists = operatorDao.existsById(o.getOperatorId());
		if(exists) throw new DuplicateCustomerException("Operator already exists of Id="+o.getOperatorId());

		operatorDao.save(o);
		return "Operator Created";
	}

	@Override
	public String removeOperator(int id) {
		boolean exists = operatorDao.existsById(id);
		if(!exists) throw new OperatorNotFoundException("Operator Not Found with Id="+id);

		operatorDao.deleteById(id);

		return "Operator Deleted ";
	}

	@Override
	public String modifyOperator(Operator o) {
		boolean exists = operatorDao.existsById(o.getOperatorId());
		if(!exists) throw new OperatorNotFoundException("Operator Not Found with Id="+o.getOperatorId());

		operatorDao.save(o);
		return "Operator Updated";
	}

	@Override
	public Operator findOperatorById(int id) {
		boolean exists = operatorDao.existsById(id);
		if(!exists) throw new OperatorNotFoundException("Operator Not Found with Id="+id);

		Operator operator = operatorDao.findById(id).get();	
		return operator;
	}

	@Override
	public List<Operator> findAllOperators() {
		List<Operator> operators = operatorDao.findAll();
		return operators;
	}
	
}