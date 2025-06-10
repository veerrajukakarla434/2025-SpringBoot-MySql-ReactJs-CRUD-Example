package com.vkakarla.ems.mapper;

import java.util.ArrayList;
import java.util.List;

import com.vkakarla.ems.dto.EmployeeDto;
import com.vkakarla.ems.entities.Employee;

public class EmployeeMapper {
	public static EmployeeDto mapToEmployeeDto(Employee employee) {

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		
		return employeeDto;
	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {

		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		
		return employee;
	}

	public static List<EmployeeDto> mapToEmployeeDtoList(List<Employee> employees) {

		List<EmployeeDto> list = new ArrayList<>();
		for (Employee emp : employees) {
			EmployeeDto dto = mapToEmployeeDto(emp);
			list.add(dto);
		}
		return list;
	}

	public static List<Employee> mapToEmployeeList(List<EmployeeDto> employeeDtos) {

		List<Employee> list = new ArrayList<>();
		for (EmployeeDto dto : employeeDtos) {
			Employee emp = mapToEmployee(dto);
			list.add(emp);
		}
		return list;
	}
}
