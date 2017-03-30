'use strict';

const ENABLE = true;
const ReactNative = require('react-native');
const {
    NativeModules,
} = ReactNative;

module.exports = ENABLE ? NativeModules.Des : {
    encrypt: (text, key, callback) => callback(text),
    decrypt: (code, key, callback) => callback(code),
};
