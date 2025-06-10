package com.vkakarla.ems.service;

import java.util.List;

import com.vkakarla.ems.dto.EmployeeDto;

public interface EmployeeService {
	
	public void createEmployee(EmployeeDto dto);

	public EmployeeDto getEmployeeById(long id);

	public void updateEmployee(long id, EmployeeDto employee);

	public void deleteEmployee(long id);
 
	public List<EmployeeDto> getAllEmployees();
}
