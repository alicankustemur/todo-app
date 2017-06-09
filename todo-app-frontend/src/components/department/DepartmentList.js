import React, {Component} from 'react';
import {Panel, Table} from 'react-bootstrap';

import "../style.css";

export default class DepartmentList extends Component {

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
                    <tbody></tbody>
                    </thead>
                </Table>
            </Panel>
        );
    }
}


