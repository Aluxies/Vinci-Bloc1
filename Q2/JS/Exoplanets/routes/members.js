const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {

    if ( req.session.user === undefined ){

        res.redirect('/users');

    } 
    else {
        
        res.render('members/members.hbs');

    };

});

module.exports = router;