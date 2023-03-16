package io.spring.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.api.models.Employee;
import io.spring.api.responses.ResponseHandler;
import io.spring.api.services.IEmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeController {

    private IEmployeeService iEmployeeService;

    public EmployeeController(IEmployeeService iEmployeeService) {
        this.iEmployeeService = iEmployeeService;
    }

    // GET ALL
    // localhost:8088/api/region
    @GetMapping("employee")
    public ResponseEntity<Object> get(){
        List<Employee> employees = iEmployeeService.Get();                          
        return ResponseHandler.generateResponse("data ditemukan", HttpStatus.OK, employees);
    }

    // GET BY ID
    @GetMapping("employee/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id){
        Employee employee = iEmployeeService.Get(id);                
        return ResponseHandler.generateResponse("data ditemukan", HttpStatus.OK, employee);        
    }

    // insert
    // @RequestBody -> untuk input data dari api/klien
    // PostMapping -> tanda insert
    @PostMapping("employee")
    public ResponseEntity<Object> post(@RequestBody Employee employee){        
        Employee newEmployee = new Employee();        
        newEmployee.setId(employee.getId());
        newEmployee.setFullname(employee.getFullname());
        newEmployee.setEmail(employee.getEmail());

        // output setelah berhasil insert adalah boolean        
        Boolean result = iEmployeeService.Save(newEmployee);
        if(result){
            return ResponseHandler.generateResponse("data berhasil tersimpan", HttpStatus.OK);            
        }else{            
            return ResponseHandler.generateResponse("data gagal tersimpan", HttpStatus.BAD_REQUEST);   
        }
    }

    // update
    // @PathVariable -> ngambil inputan variable dari url
    // PutMapping -> tanda update
    @PutMapping("employee/{id}")
    public ResponseEntity<Object> put(@RequestBody Employee employee, @PathVariable(required = true) Integer id){
        Employee employeeById = iEmployeeService.Get(id);
        employeeById.setFullname(employee.getFullname());
        employeeById.setEmail(employee.getEmail());
                
        Boolean result = iEmployeeService.Save(employeeById);
        if(result){
            return ResponseHandler.generateResponse("data berhasil diupdate", HttpStatus.OK);
        }else{
            return ResponseHandler.generateResponse("data gagal diupdate", HttpStatus.BAD_REQUEST);
        }
    }

    // delete
    @DeleteMapping("employee/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id){        
        Boolean result = iEmployeeService.Delete(id);
        if(result){
            return ResponseHandler.generateResponse("data berhasi terhapus", HttpStatus.OK);
        }else{
            return ResponseHandler.generateResponse("data gagal terhapus", HttpStatus.BAD_REQUEST);
        } 
    }

}
