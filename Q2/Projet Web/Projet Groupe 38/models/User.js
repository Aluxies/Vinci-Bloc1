const db = require('./db_conf.js');
const bcrypt = require('bcrypt');


module.exports.add = ( firstname, lastname, password, email ) => {

    const stmt_insert = db.prepare('INSERT INTO users (firstname, lastname, password, email) VALUES (?, ?, ?, ?)');

    // password encryption
    const saltRounds = 10;
    const encryptedData = bcrypt.hashSync( password, saltRounds);

    stmt_insert.run( firstname, lastname, encryptedData, email );

};

module.exports.findByEmail = ( email ) => {
    const stmt_find = db.prepare("SELECT * FROM users WHERE email = ?");
    const user = stmt_find.get( email );
    return user;
};

/**
 * Check if the password is correct
 * @param {*} user the user object
 * @param {*} passwordToCheck the req.body.password
 * @returns true if the password is correct
 */
module.exports.checkPassword = ( user, passwordToCheck ) => {
    return bcrypt.compareSync( passwordToCheck, user.password );
};