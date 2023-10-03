const express = require('express');
const router = express.Router();

const User = require('../models/User.js');

const validator = require('validator');

// login get page
router.get('/', (req, res) => {
    
    if ( req.session.user === undefined ) {

        res.render('users/login.hbs');

    } else res.redirect('/');

});

// Account creation page
router.get('/register', (req, res) => {

    if ( req.session.user !== undefined ){

        res.redirect('/');

    } 
    else res.render('users/register.hbs');

});

// login post route
router.post('/login', (req, res) => {

    if ( req.session.user !== undefined ){

        res.redirect('/');

    };

    const email = req.body.email;
    const password = req.body.password;

    let loginError;
    let hasError = false;

    const user = User.findByEmail( email );

    if ( !validator.isEmail( email ) ){

        loginError = "L'email entré est invalide.";
        hasError = true;

    }
    else if ( user === undefined ){

        loginError = "L'email ne correspond à aucun utilisateur.";
        hasError = true;

    } 
    else if ( !User.checkPassword( user, password ) ){

        loginError = "Le mot de passe est incorrect.";
        hasError = true;
        
    };

    if ( hasError ){

        res.render('users/login.hbs', { loginError, hasError} );

    }
    else {

        req.session.user = user;
        req.session.isAdmin = false;

        if(req.session.user.is_admin){
            req.session.isAdmin = true;
        };

        res.redirect('/');
    };
    
});

// logout route
router.get('/logout', (req, res) => {

    if ( req.session.user !== undefined ){
        
        req.session.destroy();
        
    };

    res.redirect('/');
    
});

router.post('/add', (req, res) => {

    const firstname = req.body.firstname;
    const lastname = req.body.lastname;
    const email = req.body.email;
    const password = req.body.password;
    const passwordConfirmation = req.body.passwordConfirmation;

    let registerError;
    let hasError = false;

    if ( !validator.isAlphanumeric( firstname, 'fr-FR' ) ){

        registerError = "Le prénom doit contenir uniquement des caractères alphanumériques.";
        hasError = true;

    }
    else if ( !validator.isAlphanumeric( lastname, 'fr-FR' ) ){

        registerError = "Le nom doit contenir uniquement des caractères alphanumériques.";
        hasError = true;

    }
    // if the email already exist in the DB
    else if ( User.findByEmail( email ) !== undefined ) {

        registerError = "Un compte est déjà enregistré sous cette adresse email.";
        hasError = true;

    }
    else if ( !validator.isEmail( email ) ){

        registerError = "L'email entré est invalide.";
        hasError = true;

    }
    //if both passwords aren't identical
    else if ( password !== passwordConfirmation ) {

        registerError = "Les mots de passe ne correspondent pas.";
        hasError = true;

    }
    // if the password has at least 1 uppercase, 1 lowercase, 1 number
    else if ( !validator.isStrongPassword( password, { minLength : 8, minLowercase: 1, minUppercase: 1, minNumbers: 1, minSymbols : 0 } ) ){

        registerError = "Le mot de passe doit faire au moins 8 caractères et contenir une minuscule, une majuscule et un chiffre";
        hasError = true;

    };
    if ( hasError ){

        res.render('users/register.hbs', { registerError, hasError } );

    }
    else {

        User.add( firstname, lastname, password, email );
        // auto log
        req.session.user = User.findByEmail( email );
        req.session.isAdmin = false;
        
        res.redirect('/');
        
    };
    
});

module.exports = router;