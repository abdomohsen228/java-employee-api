package com.luv2code.springboot.thymeleafdemo.employeeReview.service;


import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    public void employeeService_CreateEmployee_ReturnsEmployee(){
        Employee employee = Employee.builder()
                .firstName("pikachu")
                .lastName("fsried")
                .email("electric@gmail").build();

        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.save(employee);

        Assertions.assertThat(savedEmployee).isNotNull();

    }
    @Test
    public void employeeService_GetAllEmployee_ReturnsResponse() {
        // Mocking a list of employees
        Employee employee1 = Employee.builder().firstName("Pikachu").lastName("Fried").email("pikachu@gmail.com").build();
        Employee employee2 = Employee.builder().firstName("Charmander").lastName("Fury").email("charmander@gmail.com").build();

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        // Stubbing the repository's findAll() method to return the list of employees
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Act: Call the service's findAll() method
        List<Employee> result = employeeService.findAll();

        // Assert: Ensure the result is not null and contains expected data
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result.get(0).getFirstName()).isEqualTo("Pikachu");
        Assertions.assertThat(result.get(1).getFirstName()).isEqualTo("Charmander");

        // Verify the repository call
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void employeeService_FindById_ReturnEmployee() {
        int employeeId = 1;
        Employee employee = Employee.builder().id(1)
                .firstName("pikachu").lastName("electric").email("charmander@gmail.com").build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));


        Employee employeeReturn = employeeService.findById(employeeId);

        Assertions.assertThat(employeeReturn).isNotNull();
    }

    @Test
    public void PokemonService_DeletePokemonById_ReturnVoid() {
        int employeeId = 1;
        Employee employee = Employee.builder().id(1)
                .firstName("pikachu").lastName("electric").email("charmander@gmail.com").build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        assertAll(() -> employeeService.deleteById(employeeId));
    }


}
