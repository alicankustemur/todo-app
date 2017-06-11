import React, {Component} from 'react';
import {Panel,Table,Button} from 'react-bootstrap';
import axios from "axios";

import "../style.css";

export default class MeetingList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            meetings: []
        };

    }

    componentDidMount(){
        axios.get( this.props.serviceUrl + '/meetings')
            .then(response => {
                this.setState({
                    meetings: response.data
                });
            });
    }

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
                    {
                        this.state.meetings.map((meeting,index) =>
                         <tr key={index} >
                             <td>{meeting.name}</td>
                             <td>{meeting.description}</td>
                             <td>{meeting.department.name}</td>
                             <td>
                                 <Button bsStyle="danger"
                                         onClick={ () => this.__delete(meeting.id)}>Delete</Button>
                             </td>
                             <td>
                                 <Button bsStyle="success">Update</Button>
                             </td>
                         </tr>
                        )
                    }
                    </tbody>
                </Table>
            </Panel>
        );
    }

    __delete(id) {
        axios.delete(this.props.serviceUrl  + '/delete/' + id);
    }
}


