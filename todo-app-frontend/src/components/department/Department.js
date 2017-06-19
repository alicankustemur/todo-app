import React, {Component} from "react";
import {Col, Grid} from "react-bootstrap";
import axios from "axios";
import toast from "pre-toast/lib/Toast";
import PropTypes from "prop-types";

import "../style.css";

import AddDepartment from "./AddDepartment";
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
                                   addOrUpdate={this._addOrUpdate} onClear={this._clear}/>
                </Col>
                <Col lg={6}>
                    <DepartmentList departments={this.state.departments} onDelete={this._delete}
                                    onUpdate={this._updateInputValues}/>
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

    _updateInputValues = (department) => {

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
            if (id.toString() === employee.id.toString()) {
                return employee;
            }
        }

    };

    _addOrUpdate = (department) => {

        if (department.name && department.description && department.employee) {

            department.employee = this._getEmployeeById(department.employee);

            if (!department.id) {
                this._add(department);
            } else {
                this._update(department);
            }

        } else if (!this.state.department.name) {
            toast.info("Please enter a name");
        } else if (!this.state.department.description) {
            toast.info("Please enter a description");
        } else if (!this.state.department.employee) {
            toast.info("Please choose a employee");
        }

    }

    _add(department) {
        axios.post(serviceUrl + "/add", {
            name: department.name,
            description: department.description,
            employee: department.employee
        }).then(() => {
            this._list();
            toast.success("Added new department.");
            this._clear();
        });
    }

    _update(department) {
        axios.put(serviceUrl + "/update/" + department.id, {
            id: department.id,
            name: department.name,
            description: department.description,
            employee: department.employee
        }).then(() => {
            this._list();
            toast.success("Updated new department.");
            this._clear();
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

    _delete = (id) => {
        axios.delete(serviceUrl + '/delete/' + id)
            .then(() => {
                this._list();
                toast.success("Deleted selected department.");
            });
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

Department.propTypes = {
    departments: PropTypes.array,
    department: PropTypes.object,
    employees: PropTypes.array
}


