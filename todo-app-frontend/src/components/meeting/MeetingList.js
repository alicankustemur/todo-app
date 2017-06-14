import React, {Component} from 'react';
import {Panel, Table, Button} from 'react-bootstrap';

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
                        <th>Department</th>
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
        let meetings = this.props.meetings;
        let rows = [];
        for (let i = 0; i < meetings.length; i++) {
            let meeting = meetings[i];
            rows.push(<tr key={i}>
                <td>{meeting.name}</td>
                <td>{meeting.description}</td>
                <td>{meeting.department.name}</td>
                <td>
                    <Button bsStyle="danger" onClick={ this.props.onDelete.bind(this, meeting.id) }>Delete</Button>
                </td>
                <td>
                    <Button bsStyle="success" onClick={ this.props.onUpdate.bind(this, meeting) }>Update</Button>
                </td>
            </tr>);
        }

        return rows;
    };
}


