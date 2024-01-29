const express = require('express');
const router = express.Router();

const User = require('../models/User.js');

const validator = require('validator');

// ON FAIT UN GET SEULEMENT QUAND ON AFFICHE UNE PAGE

router.get('/', (req, res) => {

    if ( req.session.user !== undefined ){

        res.redirect('/members');

    };

    const passwordError = req.session.passwordError;
    const userError = req.session.userError;
    const activeError = req.session.activeError;

    if ( passwordError || userError || activeError ){

        req.session.passwordError = undefined;
        req.session.userError = undefined;
        req.session.activeError = undefined;

    };

    res.render('users/login.hbs', { passwordError, userError, activeError } );

});

router.get('/register', (req, res) => {

    res.render('users/register.hbs', { tableErrors : req.session.tableErrors });

    if ( req.session.tableErrors ){

        delete req.session.tableErrors;

    };

});

router.post('/login', (req, res) => {

    const login = req.body.login;
    const password = req.body.password;

    const user = User.findByEmail( login );

    if ( user === undefined ){
            
        req.session.userError = true;

        res.redirect('/users');

    } 
    else {

        if ( user.active === 0 ){

            req.session.activeError = true;

            res.redirect('/users');

        } 
        else {

            if ( User.checkPassword( user, password ) ){

                req.session.user = user;
        
                res.redirect('/members');
        
            } 
            else {
    
                req.session.passwordError = true;
    
                res.redirect('/users');
    
            };

        };

    };

});

router.post('/logout', (req, res) => {

    req.session.destroy();

    res.redirect('/');

});

router.post('/add', (req, res) => {

    const name = req.body.name;
    const firstname = req.body.firstname;
    const email = req.body.email;
    const password = req.body.password;
    const passwordConfirmation = req.body.passwordConfirmation;

    const tableErrors = [];

    if ( password !== passwordConfirmation ){

        tableErrors.push( "Les mots de passes ne correspondent pas" );

    } 
    else if ( !validator.isLength( password, { min : 2 } ) ){

        tableErrors.push( "Le mot de passe doit être fort, minimum 2 caractères" );

    };

    if ( !validator.isEmail( email ) ){

        tableErrors.push( "L'email est invalide" );

    };

    if ( User.findByEmail( email ) !== undefined ){

        tableErrors.push( "Email / Utilisateur déjà présent en DB" );
        
    };

    if ( !validator.isLength( name, { min : 3 } ) ){

        tableErrors.push( "Le nom doit contenir au moins 3 caractères" );

    };

    if ( !validator.isAlphanumeric( name, 'fr-FR' ) ){

        tableErrors.push( "Le nom doit contenir uniquement des caractères alphanumériques" );

    };

    if ( !validator.isLength( firstname, { min : 3 } ) ){

        tableErrors.push( "Le prénom doit contenir au moins 3 caractères" );

    };

    if ( !validator.isAlphanumeric( firstname, 'fr-FR' ) ){

        tableErrors.push( "Le prénom doit contenir uniquement des caractères alphanumériques" );

    };

    if ( tableErrors.length > 0 ){

        req.session.tableErrors = tableErrors;

        res.redirect('/users/register');

    } 
    else {

        User.add( name, firstname, email, password );

        res.render('users/login.hbs');

    };

});

module.exports = router;