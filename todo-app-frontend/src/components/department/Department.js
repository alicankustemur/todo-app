import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddDepartment from "./AddDepartment";

import "../style.css";
import DepartmentList from "./DepartmentList";

const serviceUrl = "http://localhost:8080/todo-app/department" ;

export default class Department extends Component {

    render() {
        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddDepartment serviceUrl={serviceUrl}/>
                </Col>
                <Col lg={6}>
                    <DepartmentList serviceUrl={serviceUrl}/>
                </Col>
            </Grid>
        );
    }
}


