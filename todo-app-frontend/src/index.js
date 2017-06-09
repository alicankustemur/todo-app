import React from 'react';
import ReactDOM from 'react-dom';
import registerServiceWorker from './registerServiceWorker';
import {Router,Route,browserHistory} from 'react-router';
import Main from "./components/Main"
import JumbotronComponent from "./components/JumbotronComponent";
import Employee from "./components/employee/Employee";
import Department from "./components/department/Department";
import Meeting from "./components/meeting/Meeting";

ReactDOM.render((
    <Router history={browserHistory}>
        <Route component={Main}>
            <Route path="/" component={JumbotronComponent} />
            <Route path="/employee" component={Employee} />
            <Route path="/department" component={Department} />
            <Route path="/meeting" component={Meeting} />
        </Route>
    </Router>
),document.getElementById('root'));
registerServiceWorker();
