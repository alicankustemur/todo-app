import React, {Component} from 'react';
import Navbar from "react-bootstrap/lib/Navbar" ;
import {browserHistory} from "react-router";

export default class NavbarComponent extends Component {

    constructor() {
        super();
        this.onClick = this.__onClick.bind(this);
    }

    __onClick(path){
        this.goToPath(path);
    }

    goToPath(url){
        browserHistory.push(url);
    }


    render() {

        return (
            <Navbar inverse collapseOnSelect fixedTop>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Navbar.Link onClick={ () => this.__onClick("/") } >Todo App</Navbar.Link>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Navbar.Text>
                        <Navbar.Link onClick={ () => this.__onClick("/employee") } >Employee</Navbar.Link>
                    </Navbar.Text>
                    <Navbar.Text>
                        <Navbar.Link onClick={ () => this.__onClick("/department") } >Department</Navbar.Link>
                    </Navbar.Text>
                    <Navbar.Text>
                        <Navbar.Link onClick={ () => this.__onClick("/meeting") }>Meeting</Navbar.Link>
                    </Navbar.Text>
                </Navbar.Collapse>
            </Navbar>
        );
    }
}


