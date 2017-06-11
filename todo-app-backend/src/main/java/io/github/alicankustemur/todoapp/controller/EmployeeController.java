package io.github.alicankustemur.todoapp.controller;

import java.util.Date;
import java.util.List;

import io.github.alicankustemur.todoapp.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import io.github.alicankustemur.todoapp.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping("/addTest")
    public void add(@RequestBody Employee employee) {

        System.out.println(employee.getName());

    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("employee");
        return modelAndView;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> employees() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {

        Employee employee = service.get(id);
        employee.setRecordIsDeleted(true);
        employee.setRecordCreateTime(new Date());

        service.saveOrUpdate(employee);

        return "redirect:/employee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEmployee(@RequestParam("name") String name,
                              @RequestParam("surname") String surname,
                              @RequestParam("salary") Float salary) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setSalary(salary);
        employee.setRecordIsDeleted(false);
        employee.setRecordCreateTime(new Date());

        service.saveOrUpdate(employee);

        return "redirect:/employee";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEmployee(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("salary") Float salary) {

        Employee employee = service.get(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setSalary(salary);
        employee.setRecordUpdateTime(new Date());

        service.saveOrUpdate(employee);

        return "redirect:/employee";
    }


}
