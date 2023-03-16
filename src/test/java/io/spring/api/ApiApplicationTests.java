package io.spring.api;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.spring.api.models.Employee;
import io.spring.api.services.IEmployeeService;

@SpringBootTest
class ApiApplicationTests {

	private IEmployeeService iEmployeeService;

	@Autowired
	public ApiApplicationTests(IEmployeeService iEmployeeService) {
		this.iEmployeeService = iEmployeeService;
	}

	@Test
	void getAllEmployee(){
		// arrange			
		List<Employee> employees = new ArrayList<Employee>();					
		//act
		employees = iEmployeeService.Get();		                         		
		// assert		
		Assertions.assertThat(employees).isNotNull();
	}

	@Test
	void getByIdEmployee(){
		// arrange				
		Employee employee = new Employee();
		// act
		employee = iEmployeeService.Get(26);
		// assert
		Assertions.assertThat(employee).isNotNull();
	}

	@Test
	void insertEmployee(){
		// arrange				
		Employee employee = new Employee();		
		employee.setFullname("Farhan Test");
		employee.setEmail("farhantest@gmail.com");		
		//act
		Boolean result = iEmployeeService.Save(employee);
		// assert
		Assertions.assertThat(result).isEqualTo(true);
	}

	@Test
	void updateEmployee(){
		// arrange				
		Employee employee = iEmployeeService.Get(26);
		employee.setFullname("Abdul Test");
		employee.setEmail("abdultest@gmail.com");        
		//act
		Boolean result = iEmployeeService.Save(employee);
		// assert
		Assertions.assertThat(result).isEqualTo(true);
	}

	@Test
	void deleteEmployee(){
		// arrange				
		Employee employee = iEmployeeService.Get(26);
		//act
		Boolean result = iEmployeeService.Delete(employee.getId());
		// assert
		Assertions.assertThat(result).isEqualTo(true);
	}

}
