package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Employee;

public interface EmployeeService {

	Employee findById(int id);

	void saveEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	void deleteEmployeeBySsn(int id);

	List<Employee> findAllEmployees();

	Employee findEmployeeBySsn(Integer id);

	boolean isEmployeeSsnUnique(Integer id);

}
