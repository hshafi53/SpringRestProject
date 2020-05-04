package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Employee;

public interface EmployeeDao {

	Employee findById(int id);

	void saveEmployee(Employee employee);

	void deleteEmployeeBySsn(int id);

	List<Employee> findAllEmployees();

	Employee findEmployeeBySsn(Integer id);

	public Employee updateEmployee(Employee employee);

}
