const db = require('./db_conf.js');

/**
 *  Return every fabrics
 * @returns id, name, description, price and color of each fabrics
 */
module.exports.list = () => {
    const stmt = db.prepare('SELECT * FROM fabrics');
    return stmt.all();
};

/**
 * Return everything to know about a specific fabric
 * @param {*} id the ID of the fabric 
 * @returns id, name, description, price and color of a fabric
 */
module.exports.get = (id) => {
    const stmt = db.prepare('SELECT * FROM fabrics WHERE fabric_id = ?');
    return stmt.get(id);
};