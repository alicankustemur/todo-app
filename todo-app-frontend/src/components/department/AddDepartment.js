import React, {Component} from 'react';
import {Panel, FormGroup, Col, FormControl, Button, ControlLabel} from 'react-bootstrap';

import "../style.css";

export default class AddDepartment extends Component {

    constructor(props) {
        super(props);

        this.state = {
            department: this.props.department,
            employees: this.props.employees
        };

    }

    render() {

        let operationText = this.state.department.id ? "Update" : "Add";
        let bsStyle = this.state.department.id ? "success" : "primary";

        return (
            <Panel bsStyle={bsStyle} header={operationText + " Department"} className="panel">
                <div className="form-horizontal">
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Name
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Name" name="name" value={this.state.department.name}
                                         onChange={this.__handleChange} onKeyPress={this.__onEnterClick}/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Description
                        </Col>
                        <Col sm={10}>
                            <FormControl componentClass="textarea" rows="4" placeholder="Description" name="description"
                                         value={this.state.department.description}
                                         onChange={this.__handleChange} onKeyPress={this.__onEnterClick}/>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Employee
                        </Col>
                        <Col sm={10}>
                            <select className="form-control employeesDropDown" name="employee"
                                    value={this.state.department.employee} onChange={this.__handleChange}>
                                {this._employeeOptions()}
                            </select>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col lg={2 } xs={2} xsPush={3}>
                            <Button bsStyle={bsStyle} type="submit"
                                    onClick={this.props.addOrUpdate.bind(this, this.state.department)}>
                                {operationText}
                            </Button>
                        </Col>
                        <Col lg={2} xs={2} xsPush={4}>
                            <Button bsStyle="warning" type="button"
                                    onClick={this.props.onClear.bind(this)}>
                                Clear
                            </Button>
                        </Col>
                    </FormGroup>
                </div>
            </Panel>
        );
    }

    __onEnterClick = (event) => {
        if (event.key === "Enter") {
            this.props.addOrUpdate(this.state.department);
        }
    };

    __handleChange = (e) => {

        let state = {
            department: this.state.department
        };

        state.department[e.target.name] = e.target.value;

        this.setState(state);

    };

    _employeeOptions = () => {
        let employees = this.state.employees;
        let rows = [];
        for (let i = 0; i < employees.length; i++) {
            let employee = employees[i];
            rows.push(<option key={i} value={employee.id}>{employee.name}</option>);
        }

        return rows;
    };

    componentWillReceiveProps = (nextProps) => {
        this.setState({department: nextProps.department, employees: nextProps.employees});
    };


}


