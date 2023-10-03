const express = require('express');
const router = express.Router();

const Decoration = require('../models/Decoration.js');
const Orders = require("../models/Orders.js")
const Tickets = require("../models/Tickets.js")

router.get("/", (req,res) => {

    const stats = {
        catalog : Decoration.all().length,
        commands: Orders.ordersCount,
        closedTickets: Tickets.closedTickets()
    };

    res.render("homepage.hbs", { stats });
});

module.exports = router;