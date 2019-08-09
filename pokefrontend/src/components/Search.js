import React, { Component } from 'react';
import axios from 'axios';

export default class Search extends Component {

    constructor() {
        super();
        this.state = {
            data: "",
            memberNumber: "",
            pokemonNameOrId: "",
            picture: "",
            error: ""
        };
    }

    pokemonSearch = event => {

        event.preventDefault();

        console.log(event.target[0].value)
        console.log(event.target[1].value)

        axios
            .get("http://localhost:8079/getPokemon/" + this.state.memberNumber + "/" + this.state.pokemonNameOrId + "")
            .then(response => {
                this.setState({
                    data: response.data,
                    picture: response.data.sprites.front_default,
                    error: JSON.stringify(response.data)
                });
                console.log(response.data);
                console.log(response.data.name);
                console.log(response.data.types[0].type.name);
                console.log(response.data.sprites.front_default);
            });
    }

    changeMemberNumber = event => {
        this.setState({
            memberNumber: event.target.value
        });
    }

    changePokemonNameOrId = event => {
        this.setState({
            pokemonNameOrId: event.target.value
        });
    }

    render() {
        const { name, base_experience, height } = this.state.data;
        const picture = this.state.picture;
        return (
            <div className="App">

                <h1>Pokemon Search</h1>
                <form onSubmit={this.pokemonSearch}>
                    Member No: <input id="memberNumber" type="text" placeholder="Member Number" onChange={this.changeMemberNumber} ></input>
                    {/* <p style={{ color: 'red' }}>{this.state.error}</p> */}
                    <br></br>
                    Pokemon Name or ID: <input id="pokemonName" type="text" placeholder="Pokemon Name or ID" onChange={this.changePokemonNameOrId} ></input>
                    {/* <p style={{ color: 'red' }}>{this.state.pokemonNameError}</p> */}
                    <br></br>
                    <br></br>
                    <button type="submit" >Search</button>
                </form>

                <h2>Search Results</h2>
                <h4>Name: {name}</h4>
                <h4>Experience: {base_experience}</h4>
                <h4>Height: {height}</h4>
                <img src={picture} />

            </div>
        );
    }

}
