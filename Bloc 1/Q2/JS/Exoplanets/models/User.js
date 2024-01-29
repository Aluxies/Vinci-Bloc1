const db = require('../models/db_conf.js');
const bcrypt = require('bcrypt');

module.exports.list = () => {

    const stmt_all = db.prepare('SELECT * FROM users');

    return stmt_all.all();

};

module.exports.add = ( name, firstname, email, password ) => {

    const stmt_insert = db.prepare('INSERT INTO users (name, firstname, email, password, admin, active) VALUES (?, ?, ?, ?, ?, ?)');

    const saltRounds = 10;

    const encryptedData = bcrypt.hashSync( password, saltRounds);

    stmt_insert.run( name, firstname, email, encryptedData, 0, 1 );

};

module.exports.findByEmail = ( email ) => {

    const stmt_find = db.prepare("SELECT * FROM users WHERE email = ?");
    const user = stmt_find.get( email );

    return user;

};

module.exports.checkPassword = ( user, passwordToCheck ) => {

    const userPassword = user.password;

    if ( bcrypt.compareSync( passwordToCheck, userPassword ) ){

        return true;
    
    } 
    else return false;

};