import React, {Component} from 'react';
import {Panel, ControlLabel, FormControl, FormGroup, Col, Button} from 'react-bootstrap';
import axios from 'axios';

import "../style.css";

export default class AddEmployee extends Component {


    constructor(props) {
        super(props);

        this.state = {
            name: "",
            surname: "",
            salary: ""
        };
    }

    render() {
        return (
            <Panel bsStyle="primary" header="Add Employee" className="panel">
                <div className="form-horizontal">
                <FormGroup>
                    <Col componentClass={ControlLabel} sm={2}>
                        Name
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" placeholder="Name" name="name" value={this.state.name} onChange={this.__handleChange} />
                    </Col>
                </FormGroup>

                <FormGroup>
                    <Col componentClass={ControlLabel} sm={2}>
                        Surname
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" placeholder="Surname" name="surname" value={this.state.surname} onChange={this.__handleChange} />
                    </Col>
                </FormGroup>

                <FormGroup>
                    <Col componentClass={ControlLabel} sm={2}>
                        Salary
                    </Col>
                    <Col sm={10}>
                        <FormControl type="input" placeholder="Salary" name="salary" value={this.state.salary} onChange={this.__handleChange}/>
                    </Col>
                </FormGroup>

                <FormGroup>
                    <Col smOffset={2} lg={2}>
                        <Button bsStyle="primary" type="submit" className="addEmployeeButton"
                                onClick={() => this.__add()}>
                            Add
                        </Button>
                    </Col>

                    <Col lg={2}>
                        <Button bsStyle="warning" type="button" className="clearAddEmployeeButton"
                                onClick = {() => this.__clear()}
                                >
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

    __add(){

        axios.post(this.props.serviceUrl + "/add",{
            name : this.state.name,
            surname : this.state.surname,
            salary : this.state.salary

        });
    }


    __clear = (e) => {
        this.setState({
            name : "",
            surname : "",
            salary : ""
        });
    }


}


