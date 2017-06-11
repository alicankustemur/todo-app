import React, {Component} from 'react';
import {Panel, Table, Button} from 'react-bootstrap';
import axios from 'axios';

import "../style.css";

export default class EmployeeList extends Component {


    constructor(props) {
        super(props);
        this.state = {
            employees: []
        };

    }

    componentDidMount() {
        var _this = this;

        axios.get( this.props.serviceUrl + '/employees')
            .then(response => {
                _this.setState({
                    employees: response.data
                });
            });
    }

    render() {

        return (
            <Panel header="Employee List" className="panel">
                <Table className="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Salary</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.employees.map((employee, index) =>
                            <tr key={index}>
                                <td>{employee.name}</td>
                                <td>{employee.surname}</td>
                                <td>{employee.salary}</td>
                                <td>
                                    <Button bsStyle="danger"
                                            onClick={ () => this.__delete(employee.id)}>Delete</Button>
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


