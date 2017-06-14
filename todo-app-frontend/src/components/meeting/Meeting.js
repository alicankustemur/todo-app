import React, {Component} from 'react';
import {Col,Grid} from 'react-bootstrap';
import AddMeeting from "./AddMeeting";
import MeetingList from "./MeetingList";
import axios from "axios";
import toast from "pre-toast/lib/Toast";

import "../style.css";


const serviceUrl = "http://localhost:8080/todo-app/meeting" ;

export default class Meeting extends Component {

    constructor(props) {
        super(props);

        this.state = {
            meetings: [],
            meeting: {
                name: "",
                description: "",
                department: ""
            },
            departments: []
        }
    }

    render() {
        return (
            <Grid className="grid">
                <Col lg={6}>
                    <AddMeeting meeting={this.state.meeting} departments={this.state.departments} onClear={this._clear} addOrUpdate={this.__addOrUpdate}/>
                </Col>
                <Col lg={6}>
                    <MeetingList meetings={this.state.meetings} onDelete={this.__delete} onUpdate={this.__update} />
                </Col>
            </Grid>
        );
    }

    _clear() {
        this.setState({
            meeting: {
                id: "",
                name: "",
                description: "",
                department: ""
            }
        });
    }

    _list() {

        axios.get(serviceUrl + '/list')
            .then(response => {
                this.setState({
                    meetings: response.data
                });
            });
    }

    __delete = (id) => {
        axios.delete(serviceUrl + '/delete/' + id)
            .then(() => {
                this._list();
                toast.success("Deleted selected meeting.");
            });
    }

    __update = (meeting) => {

        this.setState({
            meeting: {
                id: meeting.id,
                name: meeting.name,
                description: meeting.description,
                department: meeting.department.id
            }
        });

    }

    _getDepartmentById(id) {
        let departments = this.state.departments;
        for (let i = 0; i < departments.length; i++) {
            let department = departments[i];

            if (id === department.id.toString()) {
                return department;
            }
        }

    };

    __addOrUpdate = (meeting) => {

        if (meeting.name && meeting.description && meeting.department) {

            meeting.department = this._getDepartmentById(meeting.department);

            if (!meeting.id) {
                axios.post(serviceUrl + "/add", {
                    name: meeting.name,
                    description: meeting.description,
                    department: meeting.department
                }).then(() => {
                    this._list();
                    toast.success("Added new meeting.");
                    this._clear();
                });
            } else {
                axios.put(serviceUrl + "/update/" + meeting.id, {
                    name: meeting.name,
                    description: meeting.description,
                    department: meeting.department
                }).then(() => {
                    this._list();
                    toast.success("Updated new meeting.");
                    this._clear();
                });
            }

        } else if (!this.state.meeting.name) {
            toast.info("Please enter a name");
        } else if (!this.state.meeting.description) {
            toast.info("Please enter a description");
        } else if (!this.state.meeting.department) {
            toast.info("Please choose a department");
        }

    }

    _departmentList() {
        axios.get("http://localhost:8080/todo-app/department/list")
            .then(response => {
                this.setState({
                    departments: response.data
                });
            });
    }

    componentDidMount() {
        this._list();
        this._departmentList();
    }
}


