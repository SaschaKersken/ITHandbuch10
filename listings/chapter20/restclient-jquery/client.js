$(document).ready(function() {
  var baseUrl = "http://localhost";
  var apiUser = 'apiuser';
  var apiKey = 'my-key';

  var fieldMap = {
    'id': 'ID',
    'name': 'Name',
    'architecture': 'Architektur',
    'implementation': 'Implementierung',
    'system': 'Betriebssysteme',
    'description': 'Kurzbeschreibung',
    'year': 'Entstehungsjahr'
  };

  var languages = null;
  var currentId = 0;

  function loadList(searchTerm) {
    var ajaxUrl = baseUrl + "/language/";
    if (searchTerm != '') {
      ajaxUrl += '?search=' + escape(searchTerm);
    }
    $.ajax({
      url: ajaxUrl,
      type: "GET",
      dataType: "xml",
      success: function(xml) {
        languages = xml;
        $('#output').empty();
        var even = true;
        $(xml).find("language").each(function() {
          var paragraph = $('<div/>', {class : even ? 'even' : 'odd' });
          even = !even;
          var id = null;
          $(this).children().each(function() {
            var element = this.tagName;
            if (element == 'id') {
              id = $(this).text();
            }
            paragraph.append(
                $('<b/>').append(fieldMap[this.tagName])
              ).append(": " + $(this).text()
              ).append('<br/>');
          });
          paragraph.append($('<a/>', {href : 'javascript:edit(' + id + ')'})
            .append("Bearbeiten"));
          paragraph.append(' | ');
          paragraph.append($('<a/>', {href: 'javascript:remove(' + id + ')'})
            .append("Löschen"));
          $('#output').append(paragraph);
        });
      },
      error: function() {
        $('#output').html('Keine Sprachen gefunden.');
      }
    });
  }

  window.edit = function(id) {
    $('#editor')
      .removeClass('success error')
      .addClass('neutral');
    currentId = id;
    if (id > 0) {
      var language = $(languages).find('language[id="' + id + '"]');
      var languageName = language.find('name').text();
    }
    var form = $('<form/>');
    if (id > 0) {
      form.append($('<h2/>').append('"' + languageName + '" bearbeiten'));
    } else {
      form.append($('<h2/>').append('Sprache hinzufügen'));
    }
    for (var key in fieldMap) {
      var content = id > 0 ? language.find(key).text() : '';
      if (key != 'id') {
        form.append(fieldMap[key] + ": ")
          .append($('<input/>', {'type' : 'text', 'name' : key, value : content}))
          .append('<br/>');
      }
    }
    form.append($('<input/>', {
      'id': 'save',
      'type': 'button',
      'value': 'Speichern',
      'click': function() {
        var xml = $('<language/>');
        for (var key in fieldMap) {
          if (key != 'id') {
            xml.append($('<' + key + '/>')
              .append($('#editor').find('form')
              .find('input[name="' + key + '"]').val()));
          }
        }
        var ajaxUrl = '';
        var method = '';
        if (id > 0) {
          ajaxUrl = baseUrl + '/language/' + currentId
            + '?user=' + apiUser + '&key=' + apiKey;
          method = 'PUT';
        } else {
          ajaxUrl = baseUrl + '/language/?user=' + apiUser + '&key=' + apiKey;
          method = 'POST';
        }
        var xmlString = '<?xml version="1.0" encoding="utf-8" standalone="yes"?>'
          + $(xml)[0].outerHTML;
        $.ajax({
          url: ajaxUrl,
          type: method,
          dataType: 'xml',
          data: xmlString,
          success: function() {
            $('#editor')
              .html('Sprache gespeichert.')
              .removeClass('neutral')
              .addClass('success');
            loadList('');
          },
          error: function() {
            $('#editor')
              .html('Konnte Sprache nicht speichern.')
              .removeClass('neutral')
              .addClass('error');
          }
        });
      }}));
    form.append($('<input/>', {
      'id': 'close',
      'type': 'button',
      'value': 'Schließen',
      'click': function() {
        $('#editor').hide();
    }}));
    $('#editor')
      .html(form)
      .show();
    window.scrollTo(0, 0);
  };

  window.remove = function(id) {
    var really = confirm("Sprache " + id + " wirklich löschen?");
    if (!really) {
      return;
    }
    var ajaxUrl = baseUrl + '/language/' + id + '?user=' + apiUser + '&key=' + apiKey;
    $.ajax({
      url: ajaxUrl,
      type: 'DELETE',
      dataType: 'xml',
      success: function() {
        $('#editor')
          .html("Sprache erfolgreich gelöscht.")
          .removeClass('neutral')
          .addClass('success')
          .show();
        loadList('');
        window.scrollTo(0, 0);
      },
      error: function() {
        $('#editor')
          .html("Konnte Sprache nicht löschen.")
          .removeClass('neutral')
          .addClass('error')
          .show();
        window.scrollTo(0, 0);
      }
    });
  };

  $('#reset').click(function() {
    $('#search').val('');
    $('#search').focus();
    loadList('');
  });

  $('#search').keyup(function() {
    loadList($(this).val());
  });

  $(window).keydown(function(event) {
    if (event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
  });

  $('#editor').hide();
  loadList('');
}
