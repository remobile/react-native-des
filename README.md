# React Native Des (remobile)
A des crypto for react-native

## Installation
```sh
npm install react-native-des --save
```
### ios
Drag RCTDes.xcodeproj to your project on Xcode.
Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTDes.a from the Products folder inside the RCTRefreshControl.xcodeproj.
### android
come soon

## Usage

### Example
```js
var Des = require('react-native-des');

Des.encrypt("fangyunjiang is a good developer", "ABCDEFGH", function(base64) {
    console.log(base64); //wWcr2BJdyldTHn4z3AxA0qBIdHQkIKmpqhTgNuRd3fAFXzvIO5347g==
    Des.decrypt(base64, "ABCDEFGH", function(text) {
        console.log(text); //fangyunjiang is a good developer
    });
});
```

### method
- `encrypt(text, key, callback)`
- `encrypt(base64, key, callback)`
