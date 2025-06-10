package com.vkakarla.ems.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkakarla.ems.dto.EmployeeDto;
import com.vkakarla.ems.entities.Employee;
import com.vkakarla.ems.mapper.EmployeeMapper;
import com.vkakarla.ems.repo.EmployeeRepository;
import com.vkakarla.ems.service.EmployeeService;

@Service
public class EmployeeImpl implements EmployeeService {
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	

	@Override
	public void createEmployee(EmployeeDto dto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(dto);
		employeeRepository.save(employee);

	}

	@Override
	public EmployeeDto getEmployeeById(long id) {
		
		Employee employee = employeeRepository.findById(id).get();
		EmployeeDto dto = EmployeeMapper.mapToEmployeeDto(employee);
		
		return dto;
	}

	@Override
	public void updateEmployee(long id, EmployeeDto dto) {

		Employee employee = employeeRepository.findById(id).get();
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		
	    employeeRepository.save(employee);

	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> empList = null;
		empList = employeeRepository.findAll();
		List<EmployeeDto> dtos = EmployeeMapper.mapToEmployeeDtoList(empList);
		return dtos;
	}

}
