import React, {Component} from 'react';
import {Panel,  FormGroup, FormControl, Col, Button, ControlLabel} from 'react-bootstrap';
import axios from "axios";

import "../style.css";

export default class AddMeeting extends Component {

    constructor(props) {
        super(props);

        this.state = {
            name: "",
            description: "",
            department: "",
            departments : []
        };
    }

    componentDidMount() {
        var _this = this;

        axios.get( "http://localhost:8080/todo-app/department/departments")
            .then(response => {
                _this.setState({
                    departments: response.data
                });
            });
    }

    render() {

        return (
            <Panel bsStyle="primary" header="Add Meeting" className="panel">
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
                            Department
                        </Col>
                        <Col sm={10}>
                            <select className="form-control departmentsDropDown" name="department" value={this.state.department} required onChange={this.__handleChange}>
                                {
                                    this.state.departments.map((department, index) =>
                                        <option key={index} value={department.id}>{department.name}</option>
                                    )
                                }
                            </select>
                        </Col>
                    </FormGroup>
                    <FormControl type="hidden" name="id"/>
                    <FormGroup>
                        <Col smOffset={2} lg={2}>
                            <Button bsStyle="primary" type="submit" className="addMeetingButton">
                                Add
                            </Button>
                        </Col>

                        <Col lg={2}>
                            <Button bsStyle="warning" type="button" className="clearMeetingButton" onClick = {() => this.__clear()}>
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
            department : ""
        });
    }
}


