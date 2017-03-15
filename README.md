# React Native Des (remobile)
A des crypto for react-native

> fork from remobile/react-native-des
> only add cbc feature in Android and iOS

## Installation
```sh
npm install react-native-des-cbc --save
```

### Installation (iOS)
1. Drag `node_modules/react-native-des-cbc/ios/RCTDes.xcodeproj` to your project's `Libraries` in Xcode.
2. Click on your main project and select `Build Phases` then drag `libRCTDes.a` from the `Libraries/RCTDes.xcodeproj/Products` into `Link Binary With Libraries`.
3. (Optional) Look for `Build Settings/Header Search Paths` and make sure it contains both `$(SRCROOT)/../../../react-native/React` as recursive.
4. `command + b` to buil it.

### Installation (Android)

1/3. In `android/settings.gradle`

```gradle
//...
include ':react-native-des-cbc'
project(':react-native-des-cbc').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-des-cbc/android')
```

2/3. In `android/app/build.gradle`

```gradle
//...
dependencies {
    //...
    compile project(':react-native-des-cbc')
}
```

3/3. register module (in `MainApplication.java`)

```java
//......
import com.remobile.des.RCTDesPackage;  // <--- import
//......

@Override
protected List<ReactPackage> getPackages() {
  return Arrays.<ReactPackage>asList(
          //......
          new RCTDesPackage(),            // <------ add here
         //......
      );
}
```

## Usage

### Example
```js
var Des = require('react-native-des-cbc');

Des.encrypt("fangyunjiang is a good developer", "ABCDEFGH", function(base64) {
    console.log(base64); //wWcr2BJdyldTHn4z3AxA0qBIdHQkIKmpqhTgNuRd3fAFXzvIO5347g==
    Des.decrypt(base64, "ABCDEFGH", function(text) {
        console.log(text); //fangyunjiang is a good developer
    }, function(){
        console.log("error");
    });
}, function() {
    console.log("error");
});

var vec = "cute";
Des.encryptCbc("PizzaLiu is a good developer too", "ABCDEFGH", vec, function(base64) {
    console.log(base64);
    Des.decryptCbc(base64, "ABCDEFGH", vec, function(text) {
        console.log(text); //PizzaLiu is a good developer
    }, function(){
        console.log("error");
    });
}, function() {
    console.log("error");
});
```

### method
- `encrypt(text, key, callback)`
- `encrypt(base64, key, callback)`

- `encryptCbc(text, key, vec, callback)`
- `encryptCbc(base64, key, vec, callback)`


## Server Side
* see https://github.com/remobile/react-native-des/blob/master/server
* support java, nodejs, js, php
