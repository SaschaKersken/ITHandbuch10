import React, { Component } from 'react';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { clicks: 0 };
  }

  /*componentDidMount() {
    fetch("http://localhost/language/")
      .then(result => result.text())
      .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
      .then(data => console.log(data));
  }*/

  clickHandler(evt) {
    evt.preventDefault();
    const { clicks } = this.state;
    let increasedClicks = clicks + 1;
    this.setState( { clicks: increasedClicks } );
  }
    
  render() {
    const { clicks } = this.state;

    return (
      <div className="App">
        {clicks == 1 ?
        <p>Bisher 1 Klick.</p>
        :
        <p>Bisher {clicks} Klicks.</p>}
        <p><a href="#" onClick = {evt => this.clickHandler(evt)}>Noch ein Klick</a></p>
      </div>
    );
  }
}

export default App;
