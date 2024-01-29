const db = require('./db_conf.js');
 
// TICKETS PART
module.exports.getTickets = (user_id) => {
    const stmt_find = db.prepare("SELECT ticket_id, topic, picture, user_id, opening_date , is_open FROM tickets WHERE user_id = ? ORDER BY opening_date");
    const tickets = stmt_find.all( user_id );
    return tickets;
}

module.exports.getTheTicket = (ticket_id) => {
    const stmt_find = db.prepare("SELECT * from tickets WHERE ticket_id = ?");
    const ticket = stmt_find.get(ticket_id);
    return ticket;
}

module.exports.getAllTickets = () => {
    const stmt_find = db.prepare("SELECT * from tickets");
    const tickets = stmt_find.all();
    return tickets;
}

module.exports.createTicket = (topic, picture, user_id) => {
    const stmt_find = db.prepare("INSERT INTO tickets (topic, picture, user_id) VALUES (?,?,?) RETURNING ticket_id");
    return stmt_find.get(topic,picture, user_id).ticket_id;
}

module.exports.canAccessTicket = (ticket, user_id) => {
    const stmt_find = db.prepare("SELECT user_id from tickets WHERE ticket_id = ?");
    const ticket_id = stmt_find.get(ticket);
    return ticket_id === user_id;
}

module.exports.closedTickets = () => {
    const stmt_find = db.prepare("SELECT COUNT(ticket_id) from tickets WHERE is_open = FALSE");
    const closedTickets = stmt_find.get();
    return closedTickets['COUNT(ticket_id)'];
}

// MESSAGES PART
module.exports.getMessages = (ticket_id) => {
    const stmt_find = db.prepare("SELECT * from messages WHERE ticket_id = ?");
    const messages = stmt_find.all(ticket_id);
    return messages;
}

module.exports.newTicket = (ticket) => {
    const stmt_insert = db.prepare('INSERT INTO tickets (topic, picture, user_id) VALUES (?, ?, ?)');
    stmt_insert.run(ticket.topic, ticket.picture, ticket.user_id);
}

module.exports.sendMessage = (message) => {
    const stmt_insert = db.prepare('INSERT INTO messages (ticket_id, message, user_id) VALUES (?, ?, ?)');
    stmt_insert.run(message.ticket_id, message.message, message.user_id);
}

module.exports.closeTicket = (ticket_id) =>{
    const stmt_update = db.prepare('UPDATE tickets SET is_open=false WHERE ticket_id=?');
    stmt_update.run(ticket_id);
}
