const db = require('../models/db_conf.js');

module.exports.activate = ( email ) => {

    const stmt_update = db.prepare("UPDATE users SET active=? WHERE email=?");
    stmt_update.run( 1, email );

};

module.exports.desactivate = ( email ) => {

    const stmt_update = db.prepare("UPDATE users SET active=? WHERE email=?");
    stmt_update.run( 0, email );

};