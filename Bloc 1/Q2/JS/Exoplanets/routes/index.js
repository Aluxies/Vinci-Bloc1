const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {

  const date = new Date();
  const dateComplete = `${date.getDate()}/${date.getMonth()+1}/${date.getFullYear()}`;
  const heure = `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;

  const string = `Nous sommes le ${dateComplete}. Il est ${heure} à Bruxelles`;

  res.render('index.hbs', { string } );

});

module.exports = router;