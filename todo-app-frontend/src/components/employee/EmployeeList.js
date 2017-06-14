import React, {Component} from 'react';
import {Panel, Table,Button} from 'react-bootstrap';

export default class EmployeeList extends Component {

    constructor(props){
        super(props);
    }

    render() {

        return (
            <Panel header="Employee List" className="panel">
                <Table className="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Salary</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.__renderTable()}
                    </tbody>

                </Table>
                <Button onClick={this.__buttonClick}></Button>
            </Panel>
        );
    }

    __renderTable = () => {
        let employees = this.props.employees;
        let rows = [];
        for (let i = 0; i < employees.length; i++) {
            let employee = employees[i];
            rows.push(<tr key={i}>
                <td>{employee.name}</td>
                <td>{employee.surname}</td>
                <td>{employee.salary}</td>
                <td>
                    <Button bsStyle="danger" onClick={ this.props.onDelete.bind(this, employee.id) }>Delete</Button>
                </td>
                <td>
                    <Button bsStyle="success" onClick={ this.props.onUpdate.bind(this, employee) }>Update</Button>
                </td>
            </tr>);
        }

        return rows;
    };

    __buttonClick = () => {
        let name = "alican";

        if(this.props.alican)
            this.props.alican(name);
    };






}


