import React, {Component} from 'react';
import {Panel,Table} from 'react-bootstrap';

import "../style.css";

export default class MeetingList extends Component {

    render() {

        return (
            <Panel header="Meeting List" className="panel">
                <Table className="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Departments</th>
                    </tr>
                    <tbody></tbody>
                    </thead>
                </Table>
            </Panel>
        );
    }
}


