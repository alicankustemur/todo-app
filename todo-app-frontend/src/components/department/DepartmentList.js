import React, {Component} from 'react';
import {Panel, Table, Button, OverlayTrigger, Popover} from 'react-bootstrap';

import "../style.css";

export default class DepartmentList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            departments: this.props.departments
        };

    }


    render() {


        return (
            <Panel header="Department List" className="panel">
                <Table className="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Employee</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this._rows()}
                    </tbody>
                </Table>
            </Panel>
        );
    }

    _rows = () => {

        let departments = this.props.departments;
        let rows = [];

        for (let i = 0; i < departments.length; i++) {
            let department = departments[i];
            rows.push(<tr key={i}>
                <td>{department.name}</td>
                <td>{department.description}</td>
                <td>
                    <OverlayTrigger trigger={['hover', 'focus']} placement="right"
                                    overlay={this._employeeInformationPopover(department.employee)}>
                        <Button>{department.employee.name}</Button>
                    </OverlayTrigger>
                </td>
                <td>
                    <Button bsStyle="danger" onClick={ this.props.onDelete.bind(this, department.id) }>Delete</Button>
                </td>
                <td>
                    <Button bsStyle="success" onClick={ this.props.onUpdate.bind(this, department) }>Update</Button>
                </td>
            </tr>);
        }

        return rows;
    };


    _employeeInformationPopover(employee) {
        return <Popover id="popover-trigger-hover-focus" title="Employee Informations">
            <strong>Name : </strong> {employee.name} <br/>
            <strong>Surname : </strong> {employee.surname} <br/>
            <strong>Identity : </strong> {employee.identity} <br/>
            <strong>Salary : </strong> {employee.salary} <br/>
        </Popover>;

    }

}


