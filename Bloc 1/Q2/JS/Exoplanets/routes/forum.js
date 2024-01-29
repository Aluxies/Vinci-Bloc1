const express = require('express');
const router = express.Router();

const Forum = require('../models/Forum.js');

router.get('/', (req, res) => {

    res.render('forum/forum.hbs', { listMessages : Forum.list() } );
  
});

router.post('/addmessage', (req, res) => {

    Forum.add( req.body.author, req.body.message );
  
    res.redirect('/forum');
  
});

router.post('/like', (req, res) => {

    Forum.like( req.body.id );

    res.redirect('/forum');

});

module.exports = router;