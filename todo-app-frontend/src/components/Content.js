import React, {Component} from 'react';
import {Router,Route,browserHistory} from 'react-router';
import App from "../App"
import JumbotronComponent from "./JumbotronComponent";
import Employee from "./employee/Employee";
import Department from "./department/Department";
import Meeting from "./meeting/Meeting";

export default class Content extends Component {

    render() {
        return (
            <Router history={browserHistory}>
                <Route component={App}>
                    <Route path="/" component={JumbotronComponent} />
                    <Route path="/employee" component={Employee} />
                    <Route path="/department" component={Department} />
                    <Route path="/meeting" component={Meeting} />
                </Route>
            </Router>
        );

    }
}


