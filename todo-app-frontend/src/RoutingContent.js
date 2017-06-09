import React, {Component} from 'react';
import {Router,Route,browserHistory} from "react-router";
import JumbotronComponent from "./JumbotronComponent";
import Department from "./department/Department";
import Employee from "./employee/Employee";
import Meeting from "./meeting/Meeting";
import App from "../App";

export default class RoutingContent extends Component {

    render() {
        return (
                <Router history={browserHistory}>
                    <Route component={Content}>
                        <Route path="/" component={JumbotronComponent}/>
                        <Route path="/department" component={Department}/>
                        <Route path="/employee"   component={Employee} />
                        <Route path="/meeting"    component={Meeting}/>
                    </Route>
                </Router>
        );

    }
}


