import React, {Component} from 'react';
import Jumbotron from "react-bootstrap/lib/Jumbotron" ;

export default class JumbotronComponent extends Component {

    render() {

        return (
            <Jumbotron>
                <div className="container">
                    <h1>Todo App</h1>
                    <p> This is a simple <b>Spring Boot</b> and <b>React JS</b> project.</p>
                    <p>The goal of the project ; save employees, departments and meeting information to using basic database operations. </p>
                    <p><a className="btn btn-primary btn-lg" target="_blank" href="http://alicankustemur.github.io"
                          role="button" rel="noopener noreferrer" >go to my blog. »</a></p>
                </div>
            </Jumbotron>
        );
    }
}


