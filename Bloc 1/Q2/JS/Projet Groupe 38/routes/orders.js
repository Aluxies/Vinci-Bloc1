const express = require('express');
const router = express.Router();
const Orders = require("../models/Orders.js");

router.get('/', (req, res) => {
    
    if ( req.session.user !== undefined ) {
  
     if (req.session.isAdmin ) {
            let orders = Orders.getAllOrders(req.session.user.user_id);
            res.render('orders/orders.hbs', {orders, isAdmin: true});
        } else {
            let orders = Orders.getOrdersFor(req.session.user.user_id);
            res.render('orders/orders.hbs', {orders, isAdmin: false, isEmpty: orders.length == 0});
        }
    } else res.redirect('/');

});

router.post('/update', (req,res) =>{
    if(req.session.user !== undefined && req.session.isAdmin) {
        Orders.setStatus(req.body.orderId, req.body.status);
        res.redirect("/orders")
    }
    else res.redirect("/");
});

module.exports = router;