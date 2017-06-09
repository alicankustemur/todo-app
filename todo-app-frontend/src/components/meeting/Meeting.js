import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddMeeting from "./AddMeeting";

import "../style.css";
import MeetingList from "./MeetingList";

export default class Meeting extends Component {

    render() {
        console.log("calling meeting!");
        return (
            <Grid className="grid">
                <Col lg="6">
                    <AddMeeting/>
                </Col>
                <Col lg="6">
                    <MeetingList/>
                </Col>
            </Grid>
        );
    }
}


