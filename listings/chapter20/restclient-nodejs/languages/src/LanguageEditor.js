import React, { Component } from 'react';
import Api from './Api';
import Xml from './Xml';

class LanguageEditor extends Component {
  static implementations = ['compiler', 'interpreter', 'VM', 'other'];
  static architectures = ['imperative', 'oop', 'other'];
  static systems = ['Unix', 'Windows', 'macOS', 'sonstige'];

  constructor(props) {
    super(props);

    this.state = {
      language: {},
      languageId: props.id ? props.id : 0,
      unsetEditMode: props.unsetEditMode,
      loadList: props.loadList,
      selectedSystems: []
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
            let language =  Xml.extractDetails(rawLanguage);
            this.setState({
              language: language,
              selectedSystems: language.system ? language.system.split(',') : []
            });
          }
        );
    }
  }

  renderSelector(options, field) {
    const { language } = this.state;
    return (
      <select onChange={evt => this.handleChange(field, evt)}>
        {options.map(
          (option, index) => <option key={field + '_' + index}
           selected={language[field] && language[field] === option}
           value={option}>{option}</option>
        )}
      </select>
    );
  }

  renderImplementationSelector() {
    return this.renderSelector(LanguageEditor.implementations, 'implementation');
  }

  renderArchitectureSelector() {
    return this.renderSelector(LanguageEditor.architectures, 'architecture');
  }

  renderSystemCheckboxes() {
    const { selectedSystems } = this.state;
    return(
      LanguageEditor.systems.map(
        (system, index) =>
          <span key={'system_' + index}>
            <input type="checkbox" onChange={evt => this.handleSystemChange(evt, system)} value={system}
            checked={selectedSystems.some(item => item === system) ? 'checked' : ''} /> {system}
          </span>
      )
    );
  }

  handleSelectionChange(evt, field) {
    const { language } = this.state;
    language[field] = evt.target.value;
    this.setState( { language: language } );
  }

  handleSystemChange(evt, system) {
    const { language, selectedSystems } = this.state;
    if (evt.target.checked) {
      if (!selectedSystems.some(item => item === system)) {
        selectedSystems.push(system);
      }
    } else {
      let index = selectedSystems.indexOf(system);
      if (index > -1) {
        selectedSystems.splice(index, 1);
      }
    }
    language.system = selectedSystems.join(',');
    this.setState( { language: language, selectedSystems: selectedSystems } );
  }

  handleChange(field, evt) {
    const { language } = this.state;
    language[field] = evt.target.value;
    this.setState( { language: language });
  }

  saveLanguage(evt) {
    const { language, languageId, unsetEditMode, loadList } = this.state;
    evt.preventDefault();
    let data = Xml.buildXml(language);
    Api.save(data, languageId)
      .then(result => {loadList();});
    unsetEditMode(evt);
  }

  render() {
    const { language, unsetEditMode } = this.state;

    return (
      <form>
        <p><b>Name:</b> <input type="text" onChange={evt => this.handleChange('name', evt)}
          defaultValue={language.name ? language.name : ''} /></p>
        <p><b>Year of introduction:</b> <input type="text" onChange={evt => this.handleChange('year', evt)}
          defaultValue={language.year ? language.year : ''} /></p>
        <p><b>Description:</b> <textarea onChange={evt => this.handleChange('description', evt)}
          value={language.description ? language.description : ''}></textarea></p>
        <p><b>Implementation:</b> {this.renderImplementationSelector()}</p>
        <p><b>Architecture:</b> {this.renderArchitectureSelector()}</p>
        <p><b>Supported Systems:</b> {this.renderSystemCheckboxes()}</p>
        <p><input type="submit" value="Save" onClick={evt => this.saveLanguage(evt)} />
           <input type="reset" value="Cancel" onClick={evt => unsetEditMode(evt)} /></p>
      </form>
    );
  }
}

export default LanguageEditor;
