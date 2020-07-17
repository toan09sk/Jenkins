ionic
npm install -g @ionic/cli
npm install -g cordova ionic
npm install -g ionic@3.20.0
npm uninstall -g ionic cordova
cordova -v
ionic -v
ionic info
ionic start helloworld tabs
ionic serve

### ElectronJS Libraries
npm install -g electron
# ElectronJS
Convert JavaScript, HTML, and CSS applications into cross platform desktop apps. More information check https://electronjs.org/
`npm install electron -g --unsafe-perm=true --allow-root`

# Electron Packager
Package your Electron app into OS-specific bundles (.app, .exe, etc.) via JavaScript or the command line. Check for more documentation for electron-packager
`npm install electron-packager -g --unsafe-perm=true --allow-root`

# Electron Installer DMG
Create DMG installers for your electron apps using appdmg.
`npm install electron-installer-dmg -g --unsafe-perm=true --allow-root`

# Create New Ionic Project
```
ionic start ionic-desktop sidemenu --type=angular

cd ionic-desktop
```
# Install Electron Development Dependencies
```
npm install --save-dev electron
npm install --save-dev electron-packager
npm install --save-dev electron-installer-dmg
```
# electron-starter.js
```
const electron = require('electron');
// Module to control application life.
const app = electron.app;
// Module to create native browser window.
const BrowserWindow = electron.BrowserWindow;

const path = require('path');
const url = require('url');

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let mainWindow;

function createWindow() {
// Create the browser window.
mainWindow = new BrowserWindow({width: 1200, height: 700});

// and load the index.html of the app.
const startUrl = process.env.ELECTRON_START_URL || url.format({
pathname: path.join(__dirname, '/../www/index.html'),
protocol: 'file:',
slashes: true
});
mainWindow.loadURL(startUrl);
// Open the DevTools.
mainWindow.webContents.openDevTools();

// Emitted when the window is closed.
mainWindow.on('closed', function () {
// Dereference the window object, usually you would store windows
// in an array if your app supports multi windows, this is the time
// when you should delete the corresponding element.
mainWindow = null
})
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', createWindow);

// Quit when all windows are closed.
app.on('window-all-closed', function () {
// On OS X it is common for applications and their menu bar
// to stay active until the user quits explicitly with Cmd + Q
if (process.platform !== 'darwin') {
app.quit()
}
});

app.on('activate', function () {
// On OS X it's common to re-create a window in the app when the
// dock icon is clicked and there are no other windows open.
if (mainWindow === null) {
createWindow()
}
});

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
```
# package.json
```
{
"name": "ionic-desktop",
"version": "0.0.1",
"author": "Ionic Framework",
"homepage": "http://ionicframework.com/",
"main": "src/electron-starter.js",
........
........
........
}
```
# index.html
```
<base href="/" />

to

<base href="./" />
```
# package.json
```
{
"name": "ionic-desktop",
"version": "0.0.1",
"author": "Ionic Framework",
"homepage": "http://ionicframework.com/",
"main": "src/electron-starter.js",
"scripts": {
"ng": "ng",
"start": "ng serve",
"build": "ng build",
"prod": "ng build -prod",
"test": "ng test",
"lint": "ng lint",
"e2e": "ng e2e",
"electron": "electron .",
"electron-serve": "ng build --prod && electron .",
"mac-release": "electron-packager . --overwrite --platform=darwin --arch=x64 --icon=src/assets/icon.icns --prune=true --out=release-builds",
"windows-release": "electron-packager . electron-tutorial-app --overwrite --asar=true --platform=win32 --arch=ia32 --icon=src/assets/icon.ico --prune=true --out=release-builds --version-string.CompanyName=CE --version-string.FileDescription=CE --version-string.ProductName='Electron Tutorial App'",
"linux-release": "electron-packager . electron-tutorial-app --overwrite --asar=true --platform=linux --arch=x64 --icon=src/assets/icon.png --prune=true --out=release-builds",
"mac-build": "electron-installer-dmg ./release-builds/ionic-desktop-darwin-x64/ionic-desktop.app builds/ionic-desktop",
"windows-build": "node src/windows-build.js"
}
}
```
# Electron Build
Use the following command to build the electron app.
`npm run electron-serve`

# Mac Release
Mac release command, this will create a release files in release-builds folder.
`npm run mac-release`

# Mac DMG Installer
Generating DMG installation file.
`npm run mac-build`

# Windows Build
`npm run windows-release`

# Windows Release
`npm run windows-build`

# .gitignore
`/release-builds`