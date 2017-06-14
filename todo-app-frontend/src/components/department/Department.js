import React, {Component} from 'react';
import {Col, Grid} from 'react-bootstrap';
import AddDepartment from "./AddDepartment";
import axios from "axios";
import toast from "pre-toast/lib/Toast";

import "../style.css";
import DepartmentList from "./DepartmentList";

const serviceUrl = "http://localhost:8080/todo-app/department";

export default class Department extends Component {

    constructor(props) {
        super(props);

        this.state = {
            departments: [],
            department: {
                name: "",
                description: "",
                employee: ""
            },
            employees: []
        }
    }

    render() {
        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddDepartment department={this.state.department}
                                   employees={this.state.employees}
                                   addOrUpdate={this.__addOrUpdate} onClear={this._clear}/>
                </Col>
                <Col lg={6}>
                    <DepartmentList departments={this.state.departments} onDelete={this.__delete}
                                    onUpdate={this.__update}/>
                </Col>
            </Grid>
        );
    }

    _clear() {
        this.setState({
            department: {
                id: "",
                name: "",
                description: "",
                employee: ""
            }
        });
    }

    _list() {

        axios.get(serviceUrl + '/list')
            .then(response => {
                this.setState({
                    departments: response.data
                });
            });
    }

    __delete = (id) => {
        axios.delete(serviceUrl + '/delete/' + id)
            .then(() => {
                this._list();
                toast.success("Deleted selected department.");
            });
    }

    __update = (department) => {

        this.setState({
            department: {
                id: department.id,
                name: department.name,
                description: department.description,
                employee: department.employee.id
            }
        });

    }

    _getEmployeeById(id) {
        let employees = this.state.employees;
        for (let i = 0; i < employees.length; i++) {
            let employee = employees[i];

            if (id === employee.id.toString()) {
                return employee;
            }
        }

    };

    __addOrUpdate = (department) => {

        if (department.name && department.description && department.employee) {

            department.employee = this._getEmployeeById(department.employee);

            if (!department.id) {
                axios.post(serviceUrl + "/add", {
                    name: department.name,
                    description: department.description,
                    employee: department.employee
                }).then(() => {
                    this._list();
                    toast.success("Added new department.");
                    this._clear();
                });
            } else {
                axios.put(serviceUrl + "/update/" + department.id, {
                    name: department.name,
                    description: department.description,
                    employee: department.employee
                }).then(() => {
                    this._list();
                    toast.success("Updated new department.");
                    this._clear();
                });
            }

        } else if (!this.state.department.name) {
            toast.info("Please enter a name");
        } else if (!this.state.department.description) {
            toast.info("Please enter a description");
        } else if (!this.state.department.employee) {
            toast.info("Please choose a employee");
        }

    }

    _employeeList() {
        axios.get("http://localhost:8080/todo-app/employee/list")
            .then(response => {
                this.setState({
                    employees: response.data
                });
            });
    }

    componentDidMount() {
        this._list();
        this._employeeList();
    }
}


