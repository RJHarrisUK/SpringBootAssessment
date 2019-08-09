import React, { Component } from 'react';
import axios from 'axios';

export default class RegisterUser extends Component {

    constructor() {
        super();
        this.state = {
            data: [],
            memberNumber: "",
            userName: "",
        };
    }

    createUser = (event) => {

        event.preventDefault();

        let newUser = {
            userName: event.target[0].value,
        }

        axios
            .post("http://localhost:8080/createUser", newUser)
            .then(response => {

                this.setState({ "memberNumber": "Your account number is: " + JSON.stringify(response.data.memberNumber) })
                this.setState({ "userName": "Your username is: " + JSON.stringify(response.data.userName) })

                console.log(response.data.memberNumber)
                console.log(response.data.userName)

            }).catch(err => console.log(err))
    }

    render() {

        return (
            <div>
                <h1>Register User</h1>
                <form onSubmit={this.createUser}>
                    <input id="name" type="text" placeholder="Your Name"></input>
                    <br></br>
                    <p style={{ color: 'blue' }}>{this.state.memberNumber}</p>
                    <p style={{ color: 'blue' }}>{this.state.userName}</p>
                    <button type="submit" >Register User</button>
                </form>
            </div>
        )
    }

}