import React, {Component} from 'react';
import {Panel, FormGroup, FormControl, Col, Button, ControlLabel} from 'react-bootstrap';

import "../style.css";

export default class AddMeeting extends Component {

    constructor(props) {
        super(props);

        this.state = {
            meeting: this.props.meeting,
            departments: this.props.departments
        };
    }


    render() {

        let operationText = this.state.meeting.id ? "Update" : "Add";
        let bsStyle = this.state.meeting.id ? "success" : "primary";

        return (
            <Panel bsStyle={bsStyle} header={operationText + " Meeting"} className="panel">
                <div className="form-horizontal">
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Name
                        </Col>
                        <Col sm={10}>
                            <FormControl type="input" placeholder="Name" name="name" value={this.state.meeting.name}
                                         onChange={this._handleChange} onKeyPress={this._onEnterClick}/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Description
                        </Col>
                        <Col sm={10}>
                            <FormControl componentClass="textarea" rows="4" placeholder="Description" name="description"
                                         value={this.state.meeting.description}
                                         onChange={this._handleChange} onKeyPress={this._onEnterClick}/>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col componentClass={ControlLabel} sm={2}>
                            Department
                        </Col>
                        <Col sm={10}>
                            <select className="form-control" name="department"
                                    value={this.state.meeting.department} onChange={this._handleChange}>
                                {this._departmentOptions()}
                            </select>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col lg={2 } xs={2} xsPush={3}>
                            <Button bsStyle={bsStyle} type="submit"
                                    onClick={this.props.addOrUpdate.bind(this, this.state.meeting)}>
                                {operationText}
                            </Button>
                        </Col>
                        <Col lg={2} xs={2} xsPush={4}>
                            <Button bsStyle="warning" type="button"
                                    onClick={this.props.onClear.bind(this)}>
                                Clear
                            </Button>
                        </Col>
                    </FormGroup>
                </div>
            </Panel>
        );
    }

    _onEnterClick = (event) => {
        if (event.key === "Enter") {
            this.props.addOrUpdate(this.state.department);
        }
    };

    _handleChange = (e) => {
        let state = {
            meeting: this.state.meeting
        };
        state.meeting[e.target.name] = e.target.value;
        this.setState(state);
    };

    _departmentOptions = () => {
        let departments = this.state.departments;
        let rows = [];
        for (let i = 0; i < departments.length; i++) {
            let department = departments[i];
            rows.push(<option key={i} value={department.id}>{department.name}</option>);
        }

        return rows;
    };

    componentWillReceiveProps = (nextProps) => {
        this.setState({meeting: nextProps.meeting, departments: nextProps.departments});
    };

}

AddMeeting.propTypes = {

}


