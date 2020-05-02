package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.EmployeeDao;
import com.websystique.springmvc.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public Employee findById(int id) {
		return dao.findById(id);
	}

	public void saveEmployee(Employee employee) {
		dao.saveEmployee(employee);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with proper
	 * values within transaction. It will be updated in db once transaction ends.
	 */
	/*public void updateEmployee(Employee employee) {
		System.out.println("inside updateEmployee---------");
		Employee entity = dao.findById(employee.getId());
		System.out.println("entity value---------"+entity);
		if (entity != null) {
			entity.setName(employee.getName());
			entity.setAmount(employee.getAmount());
			System.out.println("Name"+employee.getName());
		}
	}*/
	public Employee updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        return dao.updateEmployee(employee);
    }

	public void deleteEmployeeBySsn(int id) {
		dao.deleteEmployeeBySsn(id);
	}

	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	public Employee findEmployeeBySsn(Integer id) {
		return dao.findEmployeeBySsn(id);
	}

	public boolean isEmployeeSsnUnique(Integer id) {
		Employee employee = findEmployeeBySsn(id);
		return (employee == null || ((id != null) && (employee.getId() == id)));
	}

}
