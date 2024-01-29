const db = require('../models/db_conf.js');

module.exports.list = () => {

    const stmt_all = db.prepare("SELECT exoplanet_id AS id, unique_name AS uniqueName, hclass AS hClass, discovery_year AS discoveryYear, ist AS IST, pclass AS pClass, image_file FROM exoplanets");
    const listeexoplanetes = stmt_all.all();

    return listeexoplanetes;

};

module.exports.add = ( uniqueName, hClass, discoveryYear, imageFile ) => {

    const stmt_insert = db.prepare('INSERT INTO exoplanets (unique_name, hclass, discovery_year, image_file) VALUES (?, ?, ?, ?)');

    if ( imageFile === undefined ) imageFile = null;

    stmt_insert.run( uniqueName, hClass, discoveryYear, imageFile );

};

module.exports.remove = ( id ) => {

    const stmt_delete = db.prepare('DELETE FROM exoplanets WHERE exoplanet_id = ?');

    stmt_delete.run( id );

};

module.exports.findById = ( id ) => {

    const stmt_find = db.prepare("SELECT exoplanet_id AS id, unique_name AS uniqueName, hclass AS hClass, discovery_year AS discoveryYear, ist AS IST, pclass AS pClass, image_file FROM exoplanets WHERE exoplanet_id = ?");
    const exoplanete = stmt_find.get( id );

    const reponse = {

        messageErreur : null,
        couleurEstJaune : null,
        couleurEstRouge : null,
        exoplanete : null

    };

    if ( exoplanete === undefined ){

        reponse.messageErreur = "Aucune Exoplanète pour cet ID !",
        reponse.couleurEstJaune = true;
        reponse.couleurEstRouge = false;
        reponse.exoplanete = null;

    } 
    else if ( isNaN( id ) ){

        reponse.messageErreur = "Erreur l'id n'est pas un entier",
        reponse.couleurEstJaune = false;
        reponse.couleurEstRouge = true;
        reponse.exoplanete = null;

    } 
    else reponse.exoplanete = exoplanete;

    return reponse;

};

module.exports.findByName = ( name ) => {

    const exoplanete_found = {
        
        boolean : false,
        value : null,
        message : null,
        erreur : false

    };

    const stmt_all = db.prepare(
        "SELECT exoplanet_id AS id, unique_name AS uniqueName, hclass AS hClass, discovery_year AS discoveryYear, ist AS IST, pclass AS pClass, image_file FROM exoplanets WHERE lower(unique_name) LIKE ?");
    const listeexoplanetes = stmt_all.all( name.toLowerCase()+"%" );

    const nombreExoplanetes = listeexoplanetes.length;

    if ( nombreExoplanetes === 0 ){
        
        exoplanete_found.erreur = true;
        exoplanete_found.message = "Exoplanète non trouvée";

    }
    else {

        exoplanete_found.boolean = true;
        exoplanete_found.value = listeexoplanetes;
        exoplanete_found.message = `${nombreExoplanetes} exoplanète(s) trouvée(s)`;

    };

    return exoplanete_found;

};

module.exports.filter = ( hClass_filter, year_filter ) => {

    let stmt_all = null;
    let listeexoplanetes = null;

    // On a appuyé sur le bouton de filtrage par hClass :
    // donc la valeur est définie
    // => on filtre par hClass

    if ( hClass_filter !== undefined ){

        stmt_all = db.prepare("SELECT exoplanet_id AS id, unique_name AS uniqueName, hclass AS hClass, discovery_year AS discoveryYear, ist AS IST, pclass AS pClass, image_file FROM exoplanets WHERE hclass = ?");
        listeexoplanetes = stmt_all.all( hClass_filter );

    };

    if ( year_filter !== undefined ){

        stmt_all = db.prepare("SELECT exoplanet_id AS id, unique_name AS uniqueName, hclass AS hClass, discovery_year AS discoveryYear, ist AS IST, pclass AS pClass, image_file FROM exoplanets WHERE discovery_year = ?");
        listeexoplanetes = stmt_all.all( year_filter );

    }

    return listeexoplanetes;

};

module.exports.edit = ( object ) => {

    const stmt_update = db.prepare("UPDATE exoplanets SET unique_name=?, hclass=?, discovery_year=?, ist=?, pclass=? WHERE exoplanet_id=?");
    stmt_update.run( object.uniqueName, object.hClass, object.discoveryYear, object.IST, object.pClass, object.id );

};