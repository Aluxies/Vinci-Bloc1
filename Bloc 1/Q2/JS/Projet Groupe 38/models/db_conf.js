const Database = require('better-sqlite3');
const config = require("../config.js")

const db = new Database(config.dbPath, { verbose: console.log });
module.exports = db;