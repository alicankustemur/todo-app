import React, {Component} from 'react';
import {Panel, Form, ControlLabel, FormControl, FormGroup, Col, Button} from 'react-bootstrap';

import "../style.css";

export default class AddEmployee extends Component {

    render() {

        return (
            <Panel bsStyle="primary" header="Add Employee" className="panel">
                <Form horizontal name="addEmployeeForm" method="POST" action="employee/add">

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Name
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Name" name="name"/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Surname
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Surname" name="surname"/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Salary
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Salary" name="salary"/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col smOffset={2} lg={2}>
                            <Button bsStyle="primary" type="submit" className="addEmployeeButton"
                                    onComplete={() => this.handleOnComplete()}>
                                Add
                            </Button>
                        </Col>

                        <Col lg={2}>
                            <Button bsStyle="warning" type="button" className="clearAddEmployeeButton"
                                    onClick={() => this.handleOnComplete()}>
                                Clear
                            </Button>
                        </Col>
                    </FormGroup>

                </Form>
            </Panel>
        );
    }
}


