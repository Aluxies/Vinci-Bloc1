const express = require('express');
const router = express.Router();
const Curtains = require("../models/Curtains.js");
const Orders = require("../models/Orders.js");

router.get('/', (req, res) => {
    res.redirect('/curtains/customize');
});

router.get('/customize', (req, res) => {
    curtains = Curtains.list();
    res.render('curtains/customize.hbs' , {curtains});
});


// QUOTATION GET ROUTE
router.get('/quotation', (req, res) => {
            let hasError = false;
            let errorMessage = undefined;
            if(req.query.color === undefined || req.query.length === undefined || req.query.height === undefined) {
                hasError = true;
                errorMessage = "Tout les champs n'ont pas été spécifié"
    }

    if(req.query.length === '' || req.query.height === '') {
        hasError = true;
        errorMessage = "Vous n'avez pas spécifié les dimensions"
}

    if(req.query.color === "Choisir une option") {
            hasError = true;
            errorMessage = "Veuillez choisir un tissu pour votre rideau"
    }

    if(req.query.length < 0 || req.query.height < 0) {
            hasError = true;
            errorMessage = "Les dimensions du rideau ne peuvent pas être négatif"
    }
    
    if(hasError) {
        curtains = Curtains.list();
        res.render('curtains/customize.hbs', {curtains, hasError, errorMessage});
    } else {


    const curtain = Curtains.get(req.query.color);
    let price = curtain.price_per_square_meter * (req.query.length /100) * (req.query.height / 100)

    price = price.toFixed(2);

    res.render('curtains/quotation.hbs' , {curtain, query: req.query, price});
    }
});




router.post('/order', (req, res) =>{
    Orders.add(req.session.user.user_id, req.body.curtain_length, req.body.curtain_height, req.body.price, req.body.fabric)
    res.redirect("/orders")
})



module.exports = router;