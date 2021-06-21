import React, { Component } from 'react';
import Instruments from './Instruments';
  
class Musician extends Component {
  render() {
    const { name, instruments } = this.props;
    return (
      <div>
        <h2>{name}</h2>
        <Instruments list={instruments} />
      </div>
    );
  }
}

export default Musician;
