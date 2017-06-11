import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddEmployee from "./AddEmployee";

import "../style.css";
import EmployeeList from "./EmployeeList";

const serviceUrl = "http://localhost:8080/todo-app/employee/" ;

export default class Employee extends Component {

    render() {
        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddEmployee serviceUrl={serviceUrl} />
                </Col>
                <Col lg={6}>
                    <EmployeeList serviceUrl={serviceUrl} />
                </Col>
            </Grid>
        );
    }
}


