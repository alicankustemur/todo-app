import React, {Component} from 'react';
import {Panel, FormGroup, Col, FormControl, Button, ControlLabel} from 'react-bootstrap';
import axios from "axios";

import "../style.css";

export default class AddDepartment extends Component {

    constructor(props) {
        super(props);

        this.state = {
            name: "",
            description: "",
            employee: "",
            employees : []
        };
    }

    componentDidMount() {
        var _this = this;

        axios.get( "http://localhost:8080/todo-app/employee/employees")
            .then(response => {
                _this.setState({
                    employees: response.data
                });
            });
    }

    render() {

        return (
            <Panel bsStyle="primary" header="Add Department" className="panel">
                <div className="form-horizontal">
                <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Name
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Name" name="name" value={this.state.name} required onChange={this.__handleChange}/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Description
                        </Col>
                        <Col sm={10}>
                            <FormControl componentClass="textarea" rows="4" placeholder="Description" name="description" value={this.state.description}
                                         required onChange={this.__handleChange}/>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Employee
                        </Col>
                        <Col sm={10}>
                            <select className="form-control employeesDropDown" name="employee" value={this.state.employee} required onChange={this.__handleChange}>
                                {
                                    this.state.employees.map((employee, index) =>
                                        <option key={index} value={employee.id}>{employee.name}</option>
                                    )
                                }
                            </select>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col smOffset={2} lg={2}>
                            <Button bsStyle="primary" type="submit" className="addDepartmentButton">
                                Add
                            </Button>
                        </Col>

                        <Col lg={2}>
                            <Button bsStyle="warning" type="button" className="cleDepartmentButton"
                                    onClick={() => this.__clear()}>
                                Clear
                            </Button>
                        </Col>
                    </FormGroup>
                </div>
            </Panel>
        );
    }

    __handleChange = (e) => {
        let state = {};
        state[e.target.name] = e.target.value;
        this.setState(state);
    };

    __clear = (e) => {
        this.setState({
            name : "",
            description : "",
            employee : ""
        });
    }

}


