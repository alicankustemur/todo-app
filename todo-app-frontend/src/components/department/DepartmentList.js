import React, {Component} from 'react';
import {Panel, Table,Button} from 'react-bootstrap';

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
                <td>{department.employee.name}</td>
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

}


