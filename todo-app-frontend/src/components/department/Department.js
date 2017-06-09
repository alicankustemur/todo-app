import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddDepartment from "./AddDepartment";

import "../style.css";
import DepartmentList from "./DepartmentList";

export default class Department extends Component {

    render() {
        console.log("calling department!");
        return (
            <Grid className="grid">
                <Col lg="6">
                    <AddDepartment/>
                </Col>
                <Col lg="6">
                    <DepartmentList/>
                </Col>
            </Grid>
        );
    }
}


