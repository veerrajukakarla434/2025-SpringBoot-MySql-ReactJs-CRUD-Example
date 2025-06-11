package com.vkakarla.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vkakarla.ems.dto.EmployeeDto;
import com.vkakarla.ems.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
 
	@Autowired
	EmployeeService employeeService;
	

	@Operation(summary = "Save Employee", description = "Save Employee into DataBase")
	@PostMapping(value = "/")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDto dto) {

		employeeService.createEmployee(dto);
		return new ResponseEntity<Object>("Successfully Saved", HttpStatus.CREATED);
	}

	@Operation(summary = "Get Employees", description = "Get Employee From DataBases")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getEmployee(@PathVariable Long id) {

		EmployeeDto employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Object>(employee, HttpStatus.OK);
	}

	@Operation(summary = "Get All Employees", description = "Get All Employees from the DataBase")
	@GetMapping(value = "/")
	public ResponseEntity<Object> getAllEmployees() {
		return new ResponseEntity<Object>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@Operation(summary = "Update Employees", description = "Update Employee into DataBases")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateEmployee( @RequestBody EmployeeDto employee) {
     
		employeeService.updateEmployee(employee);
		return new ResponseEntity<Object>("Successfully Updated", HttpStatus.OK);
	}
	
	@Operation(summary = "Delete Employees", description = "Delete Employee From DataBases")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);
		return new ResponseEntity<Object>("Successfully Deleted", HttpStatus.NO_CONTENT);
	}
}
