import React, {Component} from 'react';
import {Panel, Button, FormControl, FormGroup, Col, ControlLabel} from 'react-bootstrap';

export default class AddEmployee extends Component {

    constructor(props) {
        super(props);

        this.state = {
            employee: this.props.employee
        };

    }

    render() {

        let operationText = this.state.employee.id ? "Update" : "Add";
        let bsStyle = this.state.employee.id ? "success" : "primary";

        return (
            <Panel bsStyle={bsStyle} header={operationText + " Employee"} className="panel">
                <div className="form-horizontal">
                    <FormGroup>
                        <Col componentClass={ControlLabel} lg={2}>
                            Name
                        </Col>
                        <Col lg={10}>
                            <FormControl type="text" placeholder="Name" name="name" value={this.state.employee.name}
                                         onChange={this.__handleChange} onKeyPress={this.__onEnterClick}/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} lg={2}>
                            Surname
                        </Col>
                        <Col lg={10}>
                            <FormControl type="text" placeholder="Surname" name="surname"
                                         value={this.state.employee.surname} onChange={this.__handleChange}
                                         onKeyPress={this.__onEnterClick}/>
                        </Col>
                    </FormGroup>

                    <FormGroup>
                        <Col componentClass={ControlLabel} lg={2}>
                            Salary
                        </Col>
                        <Col lg={10}>
                            <FormControl type="input" placeholder="Salary" name="salary"
                                         value={this.state.employee.salary} onChange={this.__handleChange}
                                         onKeyPress={this.__onEnterClick}/>
                        </Col>
                    </FormGroup>
                    <FormGroup>
                        <Col lg={2 } xs={2} xsPush={3}>
                            <Button bsStyle={bsStyle} type="submit"
                                    onClick={this.props.addOrUpdate.bind(this, this.state.employee)}>
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

    __onEnterClick = (event) => {
        if (event.key === "Enter") {
            this.props.addOrUpdate(this.state.employee);
        }
    };


    __handleChange = (e) => {

        let state = {
            employee: this.state.employee
        };

        state.employee[e.target.name] = e.target.value;

        this.setState(state);

    };

    componentWillReceiveProps = (nextProps) => {
        this.setState({employee: nextProps.employee});
    };

}


