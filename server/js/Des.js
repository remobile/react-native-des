'use strict';

const CryptoJS = require('crypto-js');

module.exports = {
    encrypt (message, key, success, error) {
        setImmediate(() => {
            const keyHex = CryptoJS.enc.Utf8.parse(key);
            const encrypted = CryptoJS.DES.encrypt(message, keyHex, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7,
            });
            success(encrypted.toString());
        });
    },
    decrypt (ciphertext, key, success, error) {
        setImmediate(() => {
            const keyHex = CryptoJS.enc.Utf8.parse(key);
            const decrypted = CryptoJS.DES.decrypt({
                ciphertext: CryptoJS.enc.Base64.parse(ciphertext),
            }, keyHex, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7,
            });
            success(decrypted.toString(CryptoJS.enc.Utf8));
        });
    },
};
