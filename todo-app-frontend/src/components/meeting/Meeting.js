import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddMeeting from "./AddMeeting";

import "../style.css";
import MeetingList from "./MeetingList";

const serviceUrl = "http://localhost:8080/todo-app/meeting" ;

export default class Meeting extends Component {

    render() {
        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddMeeting serviceUrl={serviceUrl}/>
                </Col>
                <Col lg={6}>
                    <MeetingList serviceUrl={serviceUrl}/>
                </Col>
            </Grid>
        );
    }
}


