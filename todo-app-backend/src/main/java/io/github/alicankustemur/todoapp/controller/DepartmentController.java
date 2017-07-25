package io.github.alicankustemur.todoapp.controller;

import io.github.alicankustemur.todoapp.controller.base.AbstractBaseController;
import io.github.alicankustemur.todoapp.controller.base.BaseController;
import io.github.alicankustemur.todoapp.domain.Department;
import io.github.alicankustemur.todoapp.service.DepartmentService;
import io.github.alicankustemur.todoapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController extends AbstractBaseController<Department,DepartmentService> {


    @GetMapping("/test")
    @ResponseBody
    public List<Department> test(){

        List<Department> list = new ArrayList<Department>();
        list.add(new Department());

        return list;
    }


}
