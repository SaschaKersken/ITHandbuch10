import { Component } from 'react';
  
class Xml extends Component {
  static extractList(rawLanguages) {
    let languages = [];
    for (let key = 0; key < rawLanguages.length; key++) {
      let language = rawLanguages[key];
      languages.push(
        {
          id: language.getAttribute('id'),
          name: language.querySelector('name').textContent
        }
      );
    }
    return languages;
  }

  static extractDetails(rawLanguage) {
    let language = {};
    let data = rawLanguage.firstChild.childNodes;
    for (let key = 0; key < data.length; key++) {
      let element = data[key];
      language[element.tagName] = element.textContent;
    }
    return language;
  }

  static buildXml(language) {
    let data = '<?xml version="1.0" encoding="utf-8" standalone="yes" ?>';
    data += '<language>';
    for (let key of Object.keys(language)) {
      data += '<' + key + '>' + language[key] + '</' + key + '>';
    }
    data += '</language>';
    return data;
  }
}

export default Xml;
