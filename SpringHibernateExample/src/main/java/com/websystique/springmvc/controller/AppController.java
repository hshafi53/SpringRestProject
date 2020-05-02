package com.websystique.springmvc.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.dao.UserDaoImpl;
import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.Login;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.EmployeeService;
import com.websystique.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EmployeeService service;

	@Autowired
	UserService userService;
	@Autowired
	UserDaoImpl userDao;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		System.out.println("List of employess  are" + employees);
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String listEmployeesbyID(ModelMap model, @PathVariable int id) {

		Employee employees = service.findById(id);
		System.out.println("Details of employee by ID  are" + employees);
		model.addAttribute("employeesbyid", employees);
		return "EmployeeByID";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@RequestParam("username") String username,
			@RequestParam("password") String password) throws SQLException {

		ModelAndView mav = new ModelAndView("welcome");

		boolean isValid = userService.findAllEmployees(username, password);
		System.out.println("Is user valid ?= " + isValid);

		if (isValid = false) {

			// mav = new ModelAndView("welcome");
			mav.addObject("firstname", "wrong credentials");

		}

		if (isValid = true) {

			// mav = new ModelAndView("welcome");
			mav.addObject("firstname", username);

		}

		return mav;

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		return mav;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,

			@ModelAttribute("user") User user) throws SQLException {
		ModelAndView mav = new ModelAndView("welcome");
		if (user != null) {
			userService.register(user);
			mav.addObject("firstname", user.getUsername());
		} else {
			mav.addObject("message", "invalid or missing data");
		}
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editEmployee(@PathVariable int id, ModelMap model) {
		Employee employee = service.findById(id);
		// model.addAttribute("employee", employee);

		model.addAttribute("edit", employee);
		return "registration";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	/*
	 * public ModelAndView updateEmployee(@Valid Employee employee, BindingResult
	 * result, ModelMap model,
	 * 
	 * @PathVariable int id) {
	 */
	public String updateEmployee(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("employee") Employee employee, ModelMap model) {
		ModelAndView mav;

		System.out.println("inside update method" + employee.getId());

		service.updateEmployee(employee);

		return "viewemp";
	}

	// This method will delete an employee by it's SSN value.

	@RequestMapping(value = { "/deleteemployee/{id}" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable int id) {
		service.deleteEmployeeBySsn(id);
		return "redirect:/list";
	}

	// This method will provide the medium to add a new employee.

	/*
	 * @RequestMapping(value = { "/new" }, method = RequestMethod.POST) public
	 * String newEmployee(ModelMap model) { Employee employee = new Employee();
	 * service.saveEmployee(employee); model.addAttribute("employee", employee);
	 * model.addAttribute("edit", false); return "registration"; }
	 */
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	/*
	 * @RequestMapping(value = { "/new" }, method = RequestMethod.POST) public
	 * String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap
	 * model) {
	 * 
	 * if (result.hasErrors()) { return "registration"; }
	 * 
	 * 
	 * Preferred way to achieve uniqueness of field [ssn] should be implementing
	 * custom @Unique annotation and applying it on field [ssn] of Model class
	 * [Employee].
	 * 
	 * Below mentioned peace of code [if block] is to demonstrate that you can fill
	 * custom errors outside the validation framework as well while still using
	 * internationalized messages.
	 * 
	 * 
	 * if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
	 * FieldError ssnError =new
	 * FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new
	 * String[]{employee.getSsn()}, Locale.getDefault()));
	 * result.addError(ssnError); return "registration"; }
	 * 
	 * service.saveEmployee(employee);
	 * 
	 * model.addAttribute("success", "Employee " + employee.getName() +
	 * " registered successfully"); return "success"; }
	 */

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	/*
	 * @RequestMapping(value = { "/edit-{ssn}-employee" }, method =
	 * RequestMethod.GET) public String editEmployee(@PathVariable String ssn,
	 * ModelMap model) { Employee employee = service.findEmployeeBySsn(ssn);
	 * model.addAttribute("employee", employee); model.addAttribute("edit", true);
	 * return "registration"; }
	 */

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	/*
	 * @RequestMapping(value = { "/edit-{ssn}-employee" }, method =
	 * RequestMethod.POST) public String updateEmployee(@Valid Employee employee,
	 * BindingResult result, ModelMap model, @PathVariable String ssn) {
	 * 
	 * if (result.hasErrors()) { return "registration"; }
	 * 
	 * if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
	 * FieldError ssnError =new
	 * FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new
	 * String[]{employee.getSsn()}, Locale.getDefault()));
	 * result.addError(ssnError); return "registration"; }
	 * 
	 * service.updateEmployee(employee);
	 * 
	 * model.addAttribute("success", "Employee " + employee.getName() +
	 * " updated successfully"); return "success"; }
	 */

	/*
	 * This method will delete an employee by it's SSN value.
	 * 
	 * @RequestMapping(value = { "/delete-{ssn}-employee" }, method =
	 * RequestMethod.GET) public String deleteEmployee(@PathVariable String ssn) {
	 * service.deleteEmployeeBySsn(ssn); return "redirect:/list"; }
	 */

	/*
	 * @RequestMapping(value = "/new", method = RequestMethod.POST) public String
	 * saveEmployee(@Valid @RequestBody Employee employee, BindingResult result,
	 * ModelMap model) { System.out.println("into new method"); if
	 * (result.hasErrors()) { return "registration"; }
	 * 
	 * service.saveEmployee(employee);
	 * 
	 * model.addAttribute("employee", "Employee " + employee.getName() +
	 * " registered successfully"); return "registration"; }
	 */

}
