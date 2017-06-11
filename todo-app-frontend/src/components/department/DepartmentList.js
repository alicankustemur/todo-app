import React, {Component} from 'react';
import {Panel, Table} from 'react-bootstrap';
import axios from "axios";

import "../style.css";

export default class DepartmentList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            departments: []
        };

    }

    componentDidMount() {
        axios.get( this.props.serviceUrl + '/departments')
            .then(response => {
                this.setState({
                    departments: response.data
                });
            });
    }

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
                    </thead>
                    <tbody>
                    {
                        this.state.departments.map((department, index) =>
                            <tr key={index}>
                                <td>{department.name}</td>
                                <td>{department.description}</td>
                                <td>{department.employee.name}</td>
                            </tr>
                        )
                    }
                    </tbody>
                </Table>
            </Panel>
        );
    }
}


