import React, {Component} from 'react';
import "./App.css";
import Header from "./components/Header";

export default class App extends Component {

    render() {
        return (
            <div>
                <Header/>
                {this.props.children}
            </div>
        );

    }
}


