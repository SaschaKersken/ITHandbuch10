import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Band from './Band';

class App extends Component {
  constructor(props) {
    super(props);

    this.bands = [
      {
        name: 'The Beatles',
        members: [
          { name: 'John Lennon',
            instruments: ['vocals', 'guitars'] },
          { name: 'Paul McCartney',
            instruments: ['vocals', 'bass'] },
          { name: 'George Harrison',
            instruments: ['guitars', 'vocals'] },
          { name: 'Ringo Starr',
            instruments: ['drums', 'vocals'] }
        ]
      },
      {
        name: 'GCP',
        members: [
          { name: 'Gewi Cane Panze',
            instruments: ['vocals', 'guitars'] },
          { name: 'Snelly Bardhard',
            instruments: ['guitars'] },
          { name: 'Bean Legwourste',
            instruments: ['bass'] },
          { name: 'Lift Nestgreen',
            instruments: ['drums', 'percussion'] }
        ]
      },
      {
        name: 'Rush',
        members: [
          { name: 'Geddy Lee',
            instruments: ['vocals', 'bass', 'keyboards'] },
          { name: 'Alex Lifeson',
            instruments: ['guitars'] },
          { name: 'Neil Peart',
            instruments: ['drums'] }
        ]
      }
    ];
    this.state = { currentBand: 0 };
  }

  nextBand(evt) {
    evt.preventDefault();
    const { currentBand } = this.state;
    let nextBand = currentBand + 1;
    if (nextBand >= this.bands.length) {
      nextBand = 0;
    }
    this.setState( { currentBand: nextBand } );
  }

  render() {
    const { currentBand } = this.state;
    let band = this.bands[currentBand];

    return (
      <div>
        <Band name={band.name} members={band.members} />
        <br />
        <a href="#" onClick = { evt => this.nextBand(evt) }>Next band</a>
      </div>
    );
  }
}

export default App;
