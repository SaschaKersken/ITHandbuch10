import React, { Component } from 'react';
import Musician from './Musician';
  
class Band extends Component {
  render() {
    const { name, members } = this.props;
    return (
      <div>
        <h1>{name}</h1>
        {members.map((data, index) =>
          <Musician key={'musician_' + index}
                    name={data['name']}
                    instruments={data['instruments']}
          />
        )}
      </div>
    );
  }
}

export default Band;
