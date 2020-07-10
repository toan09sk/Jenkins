### NVM Install
nvm -v
nvm ls
nvm install 10.15.0
nvm uninstall 10.15.0
nvm use 12.18.2

>error npm -v --> https://nodejs.org/dist/v8.9.3/node-v8.9.3-win-x64.zip

8.9.3

12.18.2

nvm install 12.18.2

### NodeJS
### Run Node

node let x = e; console.log(x);

.exit / process.exit()

node app (app.js)

npm install nodeon -g 
nodemon app (app.js)

node process/require

### global
node console.log(__dirname);console.log(__filename);

### Module
add.js >>
const add =(num1,num2)=>{return num1 + num2};
module.exports =add

app.js >>
import add from './add'; or const add = require('./add');
console.log(add(1,2));

then node app >> 3
then node module.paths

### Using NPM modules & Github Modules Node.js
npm init
useless.js >>
const useless = () => {
    console.log('you just wasted sometime');
}

module.exports = useless;
index.js >>
const useless = require('./useless');
module.exports = useless;
git init
git add .
git commit -m "initial commit"
npm install techsithgit/a-useless-packge#master --> "dependency":{"a-useless-packge":github:techsithgit/a-useless-packge#master"}

### fs module Node.js
Delete/Read/Write File\
const fs = require('fs');\
fs.unlinkSync('./tobedeleted.txt'); ---> delete\
console.log('do next step after deleted);

fs.unkink --> async\
fs.readFileSync && encoding: "utf8" --> sync\
fs.readFile -> async\
fs.writeFile --> async
fs.appendFile

### Passing Command Line Arguments Node.js

node app fly
node app drive

drive/fly -> argument

let command = process.argv[2];
if(command === 'fly')
if(command === 'drive')

node app drive --speed=30 --car=nissan
npm install yargs --save

const yargs = require('yargs');
console.log(yargs.argv)

let command = yargs.argv._[0]

### Node.js project grocery list | add remove records from file via cli 