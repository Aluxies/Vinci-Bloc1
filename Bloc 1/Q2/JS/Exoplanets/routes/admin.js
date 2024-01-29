const express = require('express');
const router = express.Router();

const User = require('../models/User.js');
const Admin = require('../models/Admin.js');

router.get('/', (req, res) => {

    if ( req.session.user === undefined ){

        res.redirect('/users');

    }
    else if ( req.session.user.admin === 0 ){

        res.redirect('/members');

    } 
    else {

        const users = User.list();

        res.render('admin/admin.hbs', { users } );

    };

});

router.post('/activate', (req, res) => {

    const email = req.body.email;

    Admin.activate( email );

    res.redirect('/admin');

});

router.post('/desactivate', (req, res) => {

    const email = req.body.email;

    Admin.desactivate( email );

    res.redirect('/admin');

});

module.exports = router;