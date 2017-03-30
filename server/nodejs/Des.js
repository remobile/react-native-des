module.exports = (function () {
    const crypto = require('crypto');
    function DES () {
    }
    DES.prototype.encode = function (text, key) {
        const cipher = crypto.createCipheriv('des-ecb', key, new Buffer(0));
        let ciph = cipher.update(text, 'utf8', 'base64');
        ciph += cipher.final('base64');
        return ciph;
    };
    DES.prototype.decode = function (code, key) {
        const decipher = crypto.createDecipheriv('des-ecb', key, new Buffer(0));
        let txt = decipher.update(code, 'base64', 'utf8');
        txt += decipher.final('utf8');
        return txt;
    };

    return new DES();
})();
