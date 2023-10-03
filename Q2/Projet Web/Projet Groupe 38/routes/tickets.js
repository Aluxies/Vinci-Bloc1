const express = require('express');
const router = express.Router();

const Tickets = require('../models/Tickets.js');

const multer = require('multer');


const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/images/tickets/tickets_picture');
    },
    filename: function (req, file, cb) {
        const date = new Date();
        const uniquePrefix = date.getFullYear() + '-' + (date.getMonth() + 1) + 
        '-' + date.getDate() + '-' + date.getHours() + '-' + date.getMinutes() + 
        '-' + date.getSeconds();
        cb(null, uniquePrefix + '-' + file.originalname);
    }
});

const upload = multer({ storage: storage });



router.get('/', (req, res) => {
    
    if ( req.session.user !== undefined ) {

        res.render('tickets/tickets.hbs', {tickets:Tickets.getTickets(req.session.user.user_id)});

    } else res.redirect('/');

});

router.get('/manage', (req, res) => {
    
    if ( req.session.user !== undefined && req.session.isAdmin) {

        res.render('tickets/manage.hbs', {tickets:Tickets.getAllTickets()});

    } else res.redirect('/');

});

router.get('/discussion', (req, res) => {
    const ticket_id = req.query.ticket_id;
    if ( req.session.user !== undefined ) {
        const messages = Tickets.getMessages(ticket_id);
        res.render('tickets/discussion.hbs', {messages, ticket:Tickets.getTheTicket(ticket_id)});
             
    } else res.redirect('/');

});

router.post('/newticket', (req, res) => {
    if ( req.session.user !== undefined ) {
        const ticket = {topic: req.body.topic, picture: req.body.picture,user_id: req.session.user.user_id};
        Tickets.newTicket(ticket);
        const ticketslist = Tickets.getTickets(req.session.user.user_id);
        const thenewticket = ticketslist[ticketslist.length-1];
        const message = {ticket_id: thenewticket.ticket_id,message: req.body.message, user_id : req.session.user.user_id};
        Tickets.sendMessage(message);
        res.redirect('/tickets/discussion?ticket_id='+message.ticket_id)
    } else 
        res.redirect('/');

});

router.post('/discussion/sendmessage', (req, res) => {
    if ( req.session.user !== undefined ) {
        const message = {ticket_id: req.body.ticket_id,message : req.body.message, user_id : req.session.user.user_id};
        Tickets.sendMessage(message);
        res.redirect('/tickets/discussion?ticket_id='+req.body.ticket_id);
    } else res.redirect('/');

});

router.post('/discussion/close', (req, res) => {
    if ( req.session.user !== undefined && req.session.isAdmin) {
        Tickets.closeTicket(req.body.ticket_id);
        res.redirect('/tickets/manage');
    } else res.redirect('/');

});

router.post('/newticket', upload.single('picture'), (req,res) =>{

    let file;
    
    if ( req.file !== undefined )
        file = "/tickets/tickets_pictures/" + req.file.filename;

    const ticket_id = Tickets.createTicket( req.body.topic, file, req.session.user.user_id );
    

    res.redirect('/tickets/discussions?ticket_id='+ticket_id);
})

module.exports = router;