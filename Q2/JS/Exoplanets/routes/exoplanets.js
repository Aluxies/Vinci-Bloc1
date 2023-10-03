const express = require('express');
const router = express.Router();

const Exoplanet = require('../models/Exoplanet.js');

const validator = require('validator');

const multer = require('multer');
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/images');
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

    const listeexoplanetes = Exoplanet.list();

    if ( req.session.lengthError !== undefined ){

        res.render('exoplanets/exoplanetes.hbs', { listeexoplanetes, lengthError : req.session.lengthError });

        req.session.lengthError = undefined;

    }
    else {

        res.render('exoplanets/exoplanetes.hbs', { listeexoplanetes });

    };

});

router.post('/add', upload.single('image_exoplanet'), (req, res) => {

    let file;

    if ( req.file === undefined ){

        file = null;

    } else file = "/images/" + req.file.filename;

    if ( !validator.isLength( req.body.unique_name, { min : 3, max : 100 } ) ){

        req.session.lengthError = "Le nom d'une exoplanète doit faire entre 3 et 100 caractères";

    } else {

        Exoplanet.add(req.body.unique_name, req.body.hclass, req.body.discovery_year, file );

    };

    res.redirect('/exoplanets');

});

router.get('/searchexoplanete', (req, res) => {

    let listeexoplanetes = Exoplanet.list();

    const recherche_nom = req.query.recherche;

    let exoplanete_found = null;
    let message = null;
    let erreur = null;

    if (recherche_nom.length >= 3) {

        const exoplanete = Exoplanet.findByName(recherche_nom);

        erreur = exoplanete.erreur;

        exoplanete_found = exoplanete.boolean;
        message = exoplanete.message;

        listeexoplanetes = exoplanete.value;

    } 
    else {

        erreur = true;
        exoplanete_found = false;
        message = "3 caractères minimum";

    };

    res.render('exoplanets/exoplanetes.hbs', { listeexoplanetes, exoplanete_found, message, erreur });

});

router.get('/details', (req, res) => {

    const id = req.query.id;

    const reponse = Exoplanet.findById(id);

    const exoplanete = reponse.exoplanete;
    const messageErreur = reponse.messageErreur;
    const couleurEstJaune = reponse.couleurEstJaune;
    const couleurEstRouge = reponse.couleurEstRouge;

    res.render('exoplanets/details.hbs', { exoplanete, messageErreur, couleurEstJaune, couleurEstRouge });

});

router.post('/filter', (req, res) => {

    const liste_filtree = Exoplanet.filter(req.body.hClass_filter, req.body.year_filter);

    res.render('exoplanets/exoplanetes.hbs', { liste_filtree });

});

router.post('/update', (req, res) => {

    const ID = req.body.id;

    const exoplanete = Exoplanet.findById( ID ).exoplanete;

    res.render('exoplanets/update.hbs', { exoplanete, ID });

});

router.post('/edit', (req, res) => {

    console.log("Passage par la route 'edit'");

    const new_data = {

        id: req.body.id,
        uniqueName: req.body.unique_name,
        hClass: req.body.hclass,
        discoveryYear: req.body.discovery_year,
        IST: req.body.ist,
        pClass: req.body.pclass

    };

    Exoplanet.edit(new_data);

    res.redirect('/exoplanets');

});

router.post('/delete', (req, res) => {

    const ID = req.body.id;

    Exoplanet.remove(ID);

    res.redirect('/exoplanets');

});

module.exports = router;