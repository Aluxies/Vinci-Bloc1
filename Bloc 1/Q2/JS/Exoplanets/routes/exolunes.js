const express = require('express');
const router = express.Router();

const Exolune = require('../models/Exolune.js');

const trappist = { name : "Trappist-1 d" };
const gliese = { name : "Gliese 581 g" };
const kepler = { name : "Kepler-186 f" };

Exolune.add( [ trappist, gliese, kepler ] );

router.get('/', ( req, res ) => {

  const listeExolunes = Exolune.list();

  res.render('exolunes/exolunes.hbs', { listeExolunes } );

});

module.exports = router;