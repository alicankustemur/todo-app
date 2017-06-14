import React, {Component} from 'react';
import {Col, Grid} from 'react-bootstrap';
import axios from 'axios';
import toast from 'pre-toast/lib/Toast';
import EmployeeList from "./EmployeeList";

import "../style.css";
import AddEmployee from "./AddEmployee";

const serviceUrl = "http://localhost:8080/todo-app/employee";

export default class Employee extends Component {

    constructor() {
        super();
        this.state = {
            employees: [],
            employee: {
                id: "",
                name: "",
                surname: "",
                salary: ""
            }
        }
    }

    render() {

        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddEmployee serviceUrl={serviceUrl} employee={this.state.employee}
                                 addOrUpdate={this.__addOrUpdate} onClear={this._clear} />
                </Col>
                <Col lg={6}>
                    <EmployeeList employees={this.state.employees} serviceUrl={serviceUrl} onDelete={this.__delete}
                                  onUpdate={this.__update} />
                </Col>
            </Grid>
        );
    }


    _clear(){
        this.setState({
            employee: {
                id: "",
                name: "",
                surname: "",
                salary: ""
            }
        });
    }

    __list() {

        axios.get(serviceUrl + '/list')
            .then(response => {
                this.setState({
                    employees: response.data
                });
            });
    }

    __delete = (id) => {
        axios.delete(serviceUrl + '/delete/' + id)
            .then(() => {
                this.__list();
                toast.success("Deleted selected employee");
            });
    }


    __update = (employee) => {

        this.setState({
            id: employee.id,
            name: employee.name,
            surname: employee.surname,
            salary: employee.salary
        });

    }


    __addOrUpdate = (employee) => {

        if (employee.name && employee.surname && employee.salary) {

            if (!employee.id) {
                axios.post(serviceUrl + "/add", {
                    name: employee.name,
                    surname: employee.surname,
                    salary: employee.salary
                }).then(() => {
                    this.__list();
                    toast.success("Added new employee.");
                    this._clear();
                });
            } else {
                axios.put(serviceUrl + "/update/" + this.state.id, {
                    name: this.state.name,
                    surname: this.state.surname,
                    salary: this.state.salary
                }).then(() => {
                    this.__list();
                    toast.success("Updated new employee.");
                    this._clear();
                });
            }

        } else if (!this.state.name) {
            toast.info("Please enter a name");
        } else if (!this.state.surname) {
            toast.info("Please enter a surname");
        } else if (!this.state.salary) {
            toast.info("Please enter a salary");
        }

    }


    componentDidMount() {
        this.__list();
    }

}


