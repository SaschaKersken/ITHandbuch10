import React, { Component } from 'react';
import './App.css';
import Api from './Api';
import Xml from './Xml';
import Language from './Language';
import LanguageEditor from './LanguageEditor';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      languages: [],
      languageId: 0,
      editMode: false
    };

    this.unsetEditMode = this.unsetEditMode.bind(this);
    this.loadList = this.loadList.bind(this);
  }

  componentDidMount() {
    this.loadList();
  }

  loadList() {
    Api.getList()
      .then(data => {
        let rawLanguages = data.querySelectorAll('language');
        let languages = Xml.extractList(rawLanguages);
        this.setState( { languages: languages } );
      }); 
  }

  showLanguage(evt, languageId) {
    evt.preventDefault();
    this.unsetEditMode(evt);
    this.setState( { languageId: languageId } );
  }

  setEditMode(evt, newLanguage) {
    evt.preventDefault();
    if (newLanguage) {
      this.setState( { languageId: 0 } );
    }
    this.setState( { editMode: true } );
  }

  unsetEditMode(evt) {
    evt.preventDefault();
    this.setState( { editMode: false } );
  }

  render() {
    const { languages, languageId, editMode } = this.state;

    return (
      <div>
        {languages.map((language, index) =>
          <div key={'language_' + index}>
            <a href="#" onClick = {evt => this.showLanguage(evt, language.id)}>{language.name}</a>
          </div>
        )}
        <br />
        <a href="#" onClick = { evt => this.setEditMode(evt, true) }>Add Language</a>
        {languageId > 0 &&
        !editMode &&
        <div>
          <Language id={languageId} />
          <a href="#" onClick={ evt => this.setEditMode(evt, false) }>Edit</a>
        </div>}
        {editMode &&
        <LanguageEditor id={languageId} unsetEditMode={this.unsetEditMode} loadList={this.loadList} />}
      </div>
    );
  }
}

export default App;
