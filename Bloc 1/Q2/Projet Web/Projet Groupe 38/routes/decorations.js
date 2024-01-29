// Imports for the rooter

const express = require('express');
const router = express.Router();

// Import for the model 'Decoration'

const Decoration = require('../models/Decoration.js');

// Import for the node module 'multer' to save pictures

const multer = require('multer');

// Memorise the directory where the pictures will be saved

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/images/decorations/decorations_pictures');
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

// Router /decorations

router.get('/', (req, res) => {
    
    // Take the list of all the decorations

    const listDecorations = Decoration.sortPriceDate( "mostExpensive" );
 
    // If the user is connected, we can see the favorites decoration

    if ( req.session.user ) {

        for ( const decoration of listDecorations ){

            const isFavorite = Decoration.isFavorite( req.session.user.user_id, decoration.decoration_id );

            decoration.isFavorite = isFavorite;
    
        };

    };

    // Boolean used to display a text in the table if true

    let listEmpty = false;

    if ( listDecorations.length === 0 ) listEmpty = true;

    // Render the catalogue.hbs page

    res.render('decorations/catalogue.hbs', { listDecorations, listEmpty } );

});

// Router /decorations/create

router.get('/create', (req, res) => {

    // If the user is defined and admin
    // Render the additem.hbs page
    
    if ( req.session.user !== undefined && req.session.isAdmin) {

        res.render('decorations/additem.hbs');

    } 

    // Else, redirect to the homepage

    else res.redirect('/');

});

// Router /decorations/details

router.get('/details', (req, res) => {

    // Get the id of the decoration in the link

    const id = req.query.id;

    // Get the decoration in the database by its associated id

    const decoration = Decoration.findById( id );

    // If the decoration is not found,
    // Redirect to the homepage

    if ( decoration === undefined ) res.redirect('/');

    // If the user is not connected, 
    // the isFavorite property of the decoration is set to false

    if ( !req.session.user ){

        decoration.isFavorite = false;

    } 

    // Else, the property is set to the value in the database

    else {

        const isFavorite = Decoration.isFavorite( req.session.user.user_id, decoration.decoration_id );

        decoration.isFavorite = isFavorite;

    };

    // Render the the detail page of the created decoration
    
    res.render('decorations/details.hbs', { decoration } );

});

// Rooter /decorations/add

router.post('/add', upload.single('picture'), (req, res) => {
    
    // If the user is not connected or is not admin
    // redirect to the homepage

    if ( req.session.user === undefined || !req.session.isAdmin ) {

        res.redirect('/');

    };

    // Variable used to memorise the filepath of the decoration picture

    let file;

    // If the filepath is undefined, we use a placeholder as a picture
    
    if ( req.file === undefined ){

        file = "/placeholder.png";

    } 

    // Else, we use the path of the decoration picture

    else file = "/decorations/decorations_pictures/" + req.file.filename;

    // Create a decoration dictionary with the name, the picturePath, 
    // the price and the description

    const decoration = {
        name : req.body.name,
        picturePath : file,
        price : req.body.price,
        description : req.body.description
    };

    // Add the decoration to the database table of decorations

    const decoration_id = Decoration.add( decoration.name, decoration.picturePath, decoration.price, decoration.description );

    // Redirect to the detail page of the created decoration

    res.redirect('/decorations/details?id='+decoration_id);
    
});

// Rooter /decorations/favorite

router.post('/favorite', (req, res) => {

    // Variable used to memorise the user connected

    const user = req.session.user;

    // If the user is not connected, redirect to the homepage

    if ( !user ) res.redirect('/');

    // Get the id of the decoration in the page

    const decoration_id = req.body.decoration_id;

    // Get the page date in the page to know where to redirect

    const page = req.body.page;

    // Toggle the favortie decoration of the connected user,
    // To set the decoration favorite or not favorite

    Decoration.toggleFavorite( parseInt( user.user_id ), parseInt( decoration_id ) );

    // Compare the content of the page variable
    // to know where to redirect

    if ( page === "details" ) res.redirect('/decorations/details?id='+decoration_id);
    if ( page === "catalogue" ) res.redirect('/decorations');
    
});

// Rooter /decorations/filterFavorite

router.post('/filterFavorite', (req, res) => {

    // If the user is not connected, redirect to the homepage

    if ( !req.session.user ) res.redirect('/decorations');

    // Take the list of all the decorations

    const listDecorations = Decoration.listFavorites( req.session.user.user_id );

    // Add a property to each decoration to know if it is a favorite or not

    for ( const decoration of listDecorations ){

        const isFavorite = Decoration.isFavorite( req.session.user.user_id, decoration.decoration_id );

        decoration.isFavorite = isFavorite;

    };

    // Boolean used to display a text in the table if true

    let listEmpty = false;

    if ( listDecorations.length === 0 ) listEmpty = true;

    // Render the catalogue.hbs page

    res.render('decorations/catalogue.hbs', { listDecorations, listEmpty } );
    
});

// Rooter /decorations/filterPriceDate

router.post('/sortPriceDate', (req, res) => {

    // Get the sort type in the page

    const sort = req.body.sort;

    // Take the list of all the decorations

    const listDecorations = Decoration.sortPriceDate( sort );
    
    // If the user is connected,
    // Add a property to each decoration to know if it is a favorite or not

    if ( req.session.user ){

        for ( const decoration of listDecorations ){

            const isFavorite = Decoration.isFavorite( req.session.user.user_id, decoration.decoration_id );

            decoration.isFavorite = isFavorite;
    
        };

    };

    // Render the catalogue.hbs page

    res.render('decorations/catalogue.hbs', { listDecorations } );
    
});

// Export the router

module.exports = router;