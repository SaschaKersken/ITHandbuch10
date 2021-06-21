const commands = {
  'mov': 2,
  'add': 2,
  'sub': 2,
  'mul': 2,
  'div': 2,
  'cmp': 2,
  'inc': 1,
  'dec': 1,
  'jmp': 1,
  'ja': 1,
  'jae': 1,
  'jb': 1,
  'jbe': 1,
  'je': 1,
  'jne': 1,
  'jr': 1,
  'jo': 1,
  'push': 1,
  'pop': 1,
  'lbl': 1,
  'hlt': 0
};

var registers = {};
var memory = [];
var programString = '';
var program = [];
var programPointer = 0;
var labels = {};
var running = false;
reset();

function reset() {
  registers = {
    'a': 0,
    'b': 0,
    'c': {
      'z': 0,
      'c': 0,
      'o': 0,
      'e': 0
    },
    's': 200
  };
  for (let i = 0; i < 200; i++) {
    memory[i] = 0;
  }
  programString = '';
  program = [];
  programPointer = 0;
  labels = {};
  running = false;
  update(-1, false);
}

function isMem(token) {
  if (token == 'a' || token == 'b') {
    return true;
  }
  if (token.charAt(0) == '$') {
    let rest = token.substr(1);
    if (isNaN(rest)) {
      return false;
    }
    if (rest < 0 || rest > 99) {
      return false;
    }
    return true;
  }
  return false;
}

function extractValue(token, includeLiteral) {
  let value = 0;
  if (token == 'a' || token == 'b') {
    value = registers[token];
  } else if (token.charAt(0) == '$') {
    value = memory[token.substr(1)];
  } else if (includeLiteral) {
    value = token;
  }
  return value;
}

function parse() {
  let lines = programString.split(/\n+/);
  let error = false;
  let errorMessages = '';
  for (let lc in lines) {
    let line = lines[lc].toLowerCase();
    let tokens = line.split(/,?\s+/);
    if (Object.keys(commands).indexOf(tokens[0]) == -1) {
      error = true;
      errorMessages += 'Invalid command ' + tokens[0] + ' in line ' + lc + '<br />';
    } else if (tokens.length < commands[tokens[0]] + 1) {
      error = true;
      errorMessages += 'Too few arguments for ' + tokens[0] + ' in line ' + lc + '<br />';
    } else if (tokens[0] == 'lbl') {
      if (!tokens[1].match(/^[a-z_]\w*$/)) {
        error = true;
        errorMessages += 'Invalid label name ' + tokens[1] + ' in line ' + lc + '<br />';
      } else if (labels[tokens[1]]) {
        error = true;
        errorMessages += 'Label ' + tokens[1] + ' used more than once in line ' + lc + '<br />';
      } else {
        labels[tokens[1]] = lc;
      }
    }
    if (!error) {
      program[lc] = tokens;
    }
  }
  document.getElementById('errors').innerHTML = errorMessages;
  return !error;
}

function update(oldProgramPointer, running) {
  let sourceWithPointer = '<pre>';
  for (let i in program) {
    sourceWithPointer += (i == oldProgramPointer ? '-&gt; <span style="color: #090" />' : '   ');
    sourceWithPointer += (i < 10 ? ' ' : '') + i + ' ';
    let line = program[i];
    for (let j = 0; j <= commands[line[0]]; j++) {
      sourceWithPointer += (j > 0 ? ' ' : '') + line[j];
    }
    sourceWithPointer += (i == oldProgramPointer ? '</span>' : '');
    sourceWithPointer += '<br />';
  }
  document.getElementById('source').innerHTML = sourceWithPointer;
  let registerContent = '<span class="cell">A</span>: <span class="content">' + registers['a'] + '</span>';
  registerContent += ' | <span class="cell">B</span>: <span class="content">' + registers['b'] + '</span><br />';
  registerContent += '<span class="cell">C (Flags)</span>: [<span class="cell">Z</span>: <span class="content">' + registers['c']['z'] + '</span>';
  registerContent += ' | <span class="cell">C</span>: <span class="content">' + registers['c']['c'] + '</span>';
  registerContent += ' | <span class="cell">O</span>: <span class="content">' + registers['c']['o'] + '</span>';
  registerContent += ' | <span class="cell">E</span>: <span class="content">' + registers['c']['e'] + '</span>]<br />';
  registerContent += '<span class="cell">Stack Pointer</span>: <span class="content">' + registers['s'] + '</span>';
  document.getElementById('registers').innerHTML = registerContent;
  let stackContent = '';
  for (let i = 199; i >= registers['s']; i--) {
    stackContent += memory[i] + '<br />';
  }
  document.getElementById('stack').innerHTML = stackContent;
  let memoryContent = '';
  for (let i = 0; i < 100; i++) {
    let address = i;
    if (i < 10) {
      address = '0' + address;
    } 
    let pos = i % 5;
    let posClass = 'col' + (pos + 1);
    let end = (pos == 4) ? '<br />' : '';
    memoryContent += '<span class="' + posClass + '"><span class="cell">' + address + '</span>: <span class="content">' + memory[i] + '</span></span>' + end;
  }
  document.getElementById('memory').innerHTML = memoryContent;
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function execute() {
  do {
    let current = program[programPointer];
    let numberOfArgs = commands[current[0]];
    if (numberOfArgs == 2 && current[0] != 'cmp') {
      current[1] = current[1].replace(',', '');
      current[2] = current[2].replace(',', '');
      if (!isMem(current[1]) || !(isMem(current[2]) || !isNaN(current[2]))) {
        console.error('Invalid argument in line ' + programPointer);
        return;
      }
      let value = extractValue(current[2], true);
      let oldValue = 0;
      if (current[0] != 'mov') {
        oldValue = extractValue(current[1], false);
      }
      let divError = false;
      switch (current[0]) {
      case 'add':
        value = parseFloat(oldValue) + parseFloat(value);
        break;
      case 'sub':
        value = oldValue - value;
        break;
      case 'mul':
        value = oldValue * value;
        break;
      case 'div':
        if (value == 0) {
          registers['c']['e'] = 1;
          divError = true;
          console.error('Division by zero in line ' + programPointer);
        }
        value = oldValue / value;
        break;
      }
      if (!divError) {
        switch (current[1]) {
        case 'a':
          registers['a'] = value;
          break;
        case 'b':
          registers['b'] = value;
          break;
        default:
          memory[current[1].substr(1)] = value;
          break;
        }
      }
    }
    if (current[0] == 'push') {
      if (!(isMem(current[1]) || !isNaN(current[1]))) {
        console.error('Invalid argument in line ' + programPointer);
        return;
      }
      if (registers['s'] == 100) {
        registers['c']['o'] = 1;
      } else {
        registers['s']--;
        let value = extractValue(current[1], true);
        memory[registers['s']] = value;
      }
    }
    if (current[0] == 'pop') {
      if (!isMem(current[1])) {
        console.error('Invalid argument in line ' + programPointer);
        return;
      }
      if (registers['s'] > 199) {
        registers['c']['e'] = 1;
      } else {
        let value = memory[registers['s']];
        switch (current[1]) {
        case 'a':
          registers['a'] = value;
          break;
        case 'b':
          registers['b'] = value;
          break;
        default:
          memory[current[1].substr(1)] = value;
          break;
        }
        memory[registers['s']] = 0;
        registers['s']++;
      }
    }
    if (current[0] == 'inc' || current[0] == 'dec') {
      let value = extractValue(current[1], false);
      if (current[0] == 'inc') {
        value++;
      } else {
        value--;
      }
      switch(current[1]) {
      case 'a':
        registers['a'] = value;
        break;
      case 'b':
        registers['b'] = value;
        break;
      default:
        memory[current[1].substr(1)] = value;
        break;
      }
    }
    if (current[0] == 'cmp') {
      if (!(isMem(current[1]) || !isNaN(current[1])) || !(isMem(current[2]) || !isNaN(current[2]))) {
        console.error('Invalid argument in line ' + programPointer);
        return;
      }
      registers['c']['z'] = 0;
      registers['c']['c'] = 0;
      registers['c']['o'] = 0;
      let result = extractValue(current[1], true) - extractValue(current[2], true);
      if (result < 0) {
        registers['c']['c'] = 1;
      } else if (result > 0) {
        registers['c']['o'] = 1;
      } else {
        registers['c']['z'] = 1;
      }
    }
    let jumped = false;
    let oldProgramPointer = programPointer;
    if (current[0].charAt(0) == 'j') {
      let willJump = false;
      if (current[0] == 'jmp') {
        willJump = true;
      } else {
        switch (current[0]) {
        case 'ja':
        case 'jo':
          willJump = (registers['c']['o'] == 1);
          break;
        case 'jae':
          willJump = (registers['c']['o'] == 1 || registers['c']['z'] == 1);
          break;
        case 'jb':
          willJump = (registers['c']['c'] == 1);
          break;
        case 'jbe':
          willJump = (registers['c']['c'] == 1 || registers['c']['z'] == 1);
          break;
        case 'je':
          willJump = (registers['c']['z'] == 1);
          break;
        case 'jne':
          willJump = (registers['c']['c'] == 1 || registers['c']['o'] == 1);
          break;
        case 'jr':
          willJump = (registers['c']['e'] == 1);
          break;
        }
      }
      if (willJump) {
        if (!labels[current[1]]) {
          console.error('Label ' + current[1] + ' in line ' + programPointer + ' does not exist.');
          return;
        }
        programPointer = labels[current[1]];
        jumped = true;
      }
    }
    if (!jumped) {
      programPointer++;
    }
    if (current[0] == 'hlt' || programPointer > program.length - 1) {
      stop();
    }  
    update(oldProgramPointer, running);
    await sleep(1000);
  } while (running);
}

function start() {
  running = true;
  execute();
}

function stop() {
  running = false;
  document.getElementById('editor').style.display = 'block';
}

function clear() {
  stop();
  document.getElementById('sourceInput').value = '';
}

document.getElementById('run').addEventListener(
  'click',
  function(event) {
    event.preventDefault();
    reset();
    programString = document.getElementById('sourceInput').value;
    if (parse()) {
      document.getElementById('editor').style.display = 'none';
      start();
    }
  }
);

document.getElementById('stopLink').addEventListener(
  'click',
  () => stop()
);

document.getElementById('resetLink').addEventListener(
  'click',
  () => reset()
);

document.getElementById('clearLink').addEventListener(
  'click',
  () => clear()
);
