import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddEmployee from "./AddEmployee";

import "../style.css";
import EmployeeList from "./EmployeeList";

export default class Employee extends Component {

    render() {
        console.log("calling employee!");
        return (
            <Grid className="grid">
                <Col lg="6">
                    <AddEmployee/>
                </Col>
                <Col lg="6">
                    <EmployeeList/>
                </Col>
            </Grid>
        );
    }
}


