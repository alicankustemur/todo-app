import React, {Component} from 'react';
import {Panel,  FormGroup, FormControl, Col, Button, ControlLabel} from 'react-bootstrap';

import "../style.css";

export default class AddMeeting extends Component {

    render() {

        return (
            <Panel bsStyle="primary" header="Add Meeting" className="panel">
                <div className="form-horizontal">
                <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Name
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Name" name="name" required/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Description
                        </Col>
                        <Col sm={10}>
                            <FormControl componentClass="textarea" rows="4" placeholder="Description" name="description"
                                         required/>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Department
                        </Col>
                        <Col sm={10}>
                            <select className="form-control departmentsDropDown" name="department" required>
                            </select>
                        </Col>
                    </FormGroup>
                    <FormControl type="hidden" name="id"/>
                    <FormGroup>
                        <Col smOffset={2} lg={2}>
                            <Button bsStyle="primary" type="submit" className="addMeetingButton"
                                    onComplete={() => this.addMeetingOnComplete()}>
                                Add
                            </Button>
                        </Col>

                        <Col lg={2}>
                            <Button bsStyle="warning" type="button" className="clearMeetingButton"
                                    onClick={() => this.addMeetingOnComplete()}>
                                Clear
                            </Button>
                        </Col>
                    </FormGroup>
                </div>
            </Panel>
        );
    }
}


