import React, { Component } from 'react';
  
class Instruments extends Component {
  render() {
    const { list } = this.props;
    return (
      list.map(
        (instrument, index) => <span key={'instrument_' + index}>
          {(index > 0) ? ', ' : ''}{instrument}
        </span>
      )
    );
  }
}

export default Instruments;
