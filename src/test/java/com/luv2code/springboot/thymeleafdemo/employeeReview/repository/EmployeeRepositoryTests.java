package com.luv2code.springboot.thymeleafdemo.employeeReview.repository;


import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void employeeRepository_SaveAll_ReturnSavedEmployee(){
        //Arrange
        Employee employee = Employee.builder()
                .firstName("yahia")
                .lastName("hamda")
                .email("yahia@tezo").build();

        //Act
        Employee savedEmployee= employeeRepository.save(employee);

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void employeeRepository_GetAll_ReturnMoreThenOneEmployee(){

        Employee employee1 = Employee.builder()
                .firstName("yahia")
                .lastName("hamda")
                .email("yahia@tezo").build();
        Employee employee2 = Employee.builder()
                .firstName("ameno")
                .lastName("nsar")
                .email("amino@tezo").build();

        Employee savedEmployee1= employeeRepository.save(employee1);
        Employee savedEmployee2= employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees.size()).isEqualTo(2);
    }
    @Test
    public void employeeRepository_FindById_ReturnEmployee() {
        Employee employee = Employee.builder()
                .firstName("ameno")
                .lastName("nsar")
                .email("amino@tezo").build();

        employeeRepository.save(employee);

        Employee employees = employeeRepository.findById(employee.getId()).get();

        Assertions.assertThat(employees).isNotNull();
    }
    @Test
    public void employeeRepository_FindByEmail_ReturnEmployeeNotNull()
    {
        Employee employee = Employee.builder()
                .firstName("ameno")
                .lastName("nsar")
                .email("amino@tezo").build();

        employeeRepository.save(employee);

        Employee employees = employeeRepository.findByEmail(employee.getEmail());

        Assertions.assertThat(employees).isNotNull();
    }
    @Test
    public void employeeRepository_UpdateEmployee_ReturnEmployeeNotNull() {
        Employee employee = Employee.builder()
                .firstName("ameno")
                .lastName("nsar")
                .email("amino@tezo").build();

        employeeRepository.save(employee);

        Employee employeeSave = employeeRepository.findById(employee.getId()).get();
        employeeSave.setFirstName("Nasr");
        employeeSave.setLastName("PPOP");
        employeeSave.setEmail("a@tezo");

        Employee employeeUpdate = employeeRepository.save(employeeSave);

        Assertions.assertThat(employeeUpdate.getFirstName()).isNotNull();
        Assertions.assertThat(employeeUpdate.getLastName()).isNotNull();
        Assertions.assertThat(employeeUpdate.getEmail()).isNotNull();
    }
    @Test

    public void PokemonRepository_PokemonDelete_ReturnPokemonIsEmpty() {
        Employee employee = Employee.builder()
                .firstName("ameno")
                .lastName("nsar")
                .email("amino@tezo").build();

        employeeRepository.save(employee);

        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeReturn = employeeRepository.findById(employee.getId());

        Assertions.assertThat(employeeReturn).isEmpty();
    }

}
