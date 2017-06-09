import React, {Component} from 'react';
import {Panel,Table} from 'react-bootstrap';

import "../style.css";

export default class EmployeeList extends Component {

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
                    <tbody></tbody>
                    </thead>
                </Table>
            </Panel>
        );
    }
}


