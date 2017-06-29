import React, {Component} from 'react';
import axios from 'axios';
import {Col, Grid} from 'react-bootstrap';
import Toast from "pre-toast/lib/Toast";
import PropTypes from "prop-types";

import EmployeeList from "./EmployeeList";
import AddEmployee from "./AddEmployee";

import "../style.css";


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
                salary: "",
                identity: ""
            }
        }
    }

    render() {

        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddEmployee serviceUrl={serviceUrl} employee={this.state.employee}
                                 addOrUpdate={this._addOrUpdate} onClear={this._clear}/>
                </Col>
                <Col lg={6}>
                    <EmployeeList employees={this.state.employees} onDelete={this._delete}
                                  onUpdate={this._updateInputValues}/>
                </Col>
            </Grid>
        );
    }


    _clear() {
        this.setState({
            employee: {
                id: "",
                name: "",
                surname: "",
                salary: "",
                identity: ""
            }
        });
    };

    _updateInputValues = (employee) => {

        this.setState({
            employee: {
                id: employee.id,
                name: employee.name,
                surname: employee.surname,
                salary: employee.salary,
                identity: employee.identity
            }
        });

    };


    _addOrUpdate = (employee) => {

        if (employee.name && employee.surname && employee.salary && employee.identity) {

            if (!employee.id) {
                this._add(employee);
            } else {
                this._update(employee);
            }

        } else if (!this.state.name) {
            Toast.info("Please enter a name");
        } else if (!this.state.surname) {
            Toast.info("Please enter a surname");
        } else if (!this.state.salary) {
            Toast.info("Please enter a salary");
        } else if (!this.state.identity) {
            Toast.info("Please enter a identity");
        }

    };

    _add(employee) {
        axios.post(serviceUrl + "/add", {
            name: employee.name,
            surname: employee.surname,
            salary: employee.salary,
            identity: employee.identity
        }).then(() => {
            this._list();
            Toast.success("Added new employee.");
            this._clear();
        }).catch(error => {
            this._availableEmployeeError(error);
        });
    };

    _update(employee) {
        axios.put(serviceUrl + "/update/" + employee.id, {
            id: employee.id,
            name: employee.name,
            surname: employee.surname,
            salary: employee.salary,
            identity: employee.identity
        }).then(() => {
            this._list();
            Toast.success("Updated new employee.");
            this._clear();
        }).catch(error => {
            this._availableEmployeeError(error);
        });
    };

    _list() {

        axios.get(serviceUrl + '/list')
            .then(response => {
                this.setState({
                    employees: response.data
                });
            });
    };

    _delete = (id) => {
        axios.delete(serviceUrl + '/delete/' + id)
            .then(() => {
                this._list();
                Toast.success("Deleted selected employee");
            });
    };

    _availableEmployeeError = (error) => {
        if (error.response.data === "employeeAvailableError") {
            Toast.warning("There is available a employee in same identiy.");
        }
    };


    componentDidMount() {
        this._list();
    };

}

Employee.propTypes = {
    employees: PropTypes.array,
    employee: PropTypes.object
};

