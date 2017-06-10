import React, {Component} from 'react';
import {Panel, FormGroup, Col, FormControl, Button, ControlLabel} from 'react-bootstrap';

import "../style.css";

export default class AddDepartment extends Component {

    render() {

        return (
            <Panel bsStyle="primary" header="Add Department" className="panel">
                <div className="form-horizontal">
                <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Name
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Name" name="name" required/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Description
                        </Col>
                        <Col sm={10}>
                            <FormControl componentClass="textarea" rows="4" placeholder="Description" name="description"
                                         required/>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Employee
                        </Col>
                        <Col sm={10}>
                            <select className="form-control employeesDropDown" name="employee" required>
                            </select>
                        </Col>
                    </FormGroup>
                    <FormControl type="hidden" name="id"/>
                    <FormGroup>
                        <Col smOffset={2} lg={2}>
                            <Button bsStyle="primary" type="submit" className="addDepartmentButton"
                                    onComplete={() => this.addDepartmentOnComplete()}>
                                Add
                            </Button>
                        </Col>

                        <Col lg={2}>
                            <Button bsStyle="warning" type="button" className="cleDepartmentButton"
                                    onClick={() => this.addDepartmentOnComplete()}>
                                Clear
                            </Button>
                        </Col>
                    </FormGroup>
                </div>
            </Panel>
        );
    }
}


