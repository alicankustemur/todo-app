package io.github.alicankustemur.todoapp.controller;

import java.util.Date;
import java.util.List;

import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.domain.Employee;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService service;
	
	@Autowired
	private EmployeeService employeeService;



	@PostMapping("/test")
	@ResponseBody
	public String test(@RequestParam("name") String name){

		System.out.println(name);

		return name;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("department");
		System.out.println("Ali Can");
		System.out.println("Ali Can");
		System.out.println("Ali Can");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> departments() {
		return service.getAll();
	}
	
	@RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
	public String deleteDepartment(@PathVariable("id") Long id){
		
		Department department = service.get(id);
		department.setRecordIsDeleted(true);
		department.setRecordCreateTime(new Date());
		
		service.saveOrUpdate(department);
		
		return "redirect:/department";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST )
	public String addDepartment(
							  @RequestParam("name") String name,
							  @RequestParam("description") String description,
							  @RequestParam("employee") Long employeeId){
		Employee employee = employeeService.get(employeeId);
		
		if(!service.isItAvailableDepartmentWithThisEmployee(employee)){
			Department department = new Department();
			department.setName(name);
			department.setDescription(description);
			department.setRecordIsDeleted(false);
			department.setEmployee(employee);
			department.setRecordCreateTime(new Date());
			
			service.saveOrUpdate(department);
		}else{
			return "redirect:/department?error=availableDepartment";
		}
		return "redirect:/department";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST )
	public String updateDepartment(
							  @RequestParam("id") Long id,
							  @RequestParam("name") String name,
							  @RequestParam("description") String description,
							  @RequestParam("employee") Long employeeId){
		
		
		Employee employee = employeeService.get(employeeId);
		if(!service.isItAvailableDepartmentWithThisEmployee(employee)){
			Department department = service.get(id);
			department.setName(name);
			department.setDescription(description);
			department.setEmployee(employee);
			department.setRecordUpdateTime(new Date());
			
			service.saveOrUpdate(department);
		}else{
			return "redirect:/department?error=availableDepartment";
		}
		return "redirect:/department";
	}
	
}
