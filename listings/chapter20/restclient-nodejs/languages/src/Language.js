import React, { Component } from 'react';
import Api from './Api';
import Xml from './Xml';

class Language extends Component {
  constructor(props) {
    super(props);

    this.state = {
      language: {},
      languageId: props.id ? props.id : 0
    };
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.id !== this.state.languageId) {
      this.setState( { languageId: nextProps.id } );
      this.loadLanguage(nextProps.id);
    }
  }

  componentDidMount() {
    this.loadLanguage(this.state.languageId);
  }

  loadLanguage(languageId) {
    if (languageId > 0) {
      Api.getById(languageId)
        .then(rawLanguage =>
          {
            this.setState( { language: Xml.extractDetails(rawLanguage) } );
          }
        );
    }
  }

  render() {
    const { language } = this.state;

    return (
      <div>
        <h2>{language.name} ({language.year})</h2>
        <p><b>Description:</b> {language.description}</p>
        <p><b>Implementation:</b> {language.implementation}</p>
        <p><b>Architecture:</b> {language.architecture}</p>
        <p><b>Supported Systems:</b> {language.system}</p>
      </div>
    );
  }
}

export default Language;
