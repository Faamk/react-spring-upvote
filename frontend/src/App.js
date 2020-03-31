import React, {Component} from 'react';
import Posts from './components/posts';
import NewPost from './components/NewPost';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
    render () {
    return (
        <Router>
            <Switch>
                <Route path='/' exact={true} component={Posts}/>
                <Route path='/new' exact={true} component={NewPost}/>>
            </Switch>
        </Router>
    );
  }


}

export default App;
