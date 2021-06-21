import { Component } from 'react';

class Api extends Component {
  static url = 'http://localhost/language/';
  static apiUser = 'apiuser';
  static apiKey = 'my-key';

  static getList() {
    return fetch(Api.url)
      .then(result => result.text())
      .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"));
  }

  static getById(id) {
    return fetch(Api.url + id)
      .then(result => result.text())
      .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"));
  }

  static save(data, id) {
    if (id === 0) {
      return fetch(Api.url + '?user=' + Api.apiUser + '&key=' + Api.apiKey,
        { method: 'post', contentType: 'text/xml', body: data }
      ).then(result => result.text())
       .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"));
    } else {
      return fetch(Api.url + id + '?user=' + Api.apiUser + '&key=' + Api.apiKey,
        { method: 'put', contentType: 'text/xml', body: data }
      ).then(result => result.text())
       .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"));
    }
  }
}

export default Api;
