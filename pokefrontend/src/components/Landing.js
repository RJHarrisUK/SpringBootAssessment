import React, { Component } from "react";
import axios from "axios";
import { BrowserRouter as Router, Route } from "react-router-dom";

import Navigationbar from './Navigationbar.js';
import RegisterUser from './RegisterUser.js';
import Search from './Search.js';

export default class Landing extends Component {

    constructor() {
        super();
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        this.getAll();
    }

    getAll = () => {
        axios
            .get('http://localhost:8079/getUsers')
            .then(response => {

                this.setState({
                    data: response.data
                })
                console.log(response.data);
            })
    }

    render() {
        return (
            <div>
                <Router>
                    <Navigationbar />
                    <Route exact path="/" render={() => <RegisterUser onload={this.onLoad} />} />
                    <Route path="/Search" render={() => <Search data={this.state.data} />} />
                </Router>
            </div>
        )
    }

}