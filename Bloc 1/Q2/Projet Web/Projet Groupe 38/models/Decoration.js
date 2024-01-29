const db = require('./db_conf.js');

/**
*   Function formatDate() :
*   
*  1 - For each element of the list
*  2 - Create a new Date object with the data of the date_added property of the element
*  3 - Take the year part of the date
*  4 - Take the month part of the date
*  5 - Take the day part of the date
*  6 - Take the hour part of
*  7 - Return the list
*  
*  @param [ list ]      : list of objects
*  @returns [ list ]    : list of objects
*
*/

const formatDate = ( list ) => {

    for ( const element of list ){

        const date = new Date( element.date_added );
        const year = date.getFullYear()
        const month = date.getMonth() + 1;
        const day = date.getDate();

        element.date_added = `${year}/${month}/${day}`;

    };

    return list;

};

/**
*   Method all() :
*
*  1 - Prepare the SQLite request and list all the rows of the table
*  2 - Use the function 'formatDate'
*  3 - Return the list of the decorations
*
*  @returns [ listDecorations ]     : the list of the decorations
*
*/

module.exports.all = () => {

    const listDecorations = db.prepare("SELECT * FROM decorations").all();
    return formatDate( listDecorations );
    
};

/**
*   Method add() :
*
*  1 - Prepare the SQLite request to insert a new decoration with all of its properties
*  2 - Put the parameters into the SQLite request
*  3 - Return the id of the decoration inserted
*
*  @param {String} name             : name of the decoration
*  @param {String} picturePath      : picturePath of the decoration
*  @param {Number} price            : price of the decoration
*  @param {String} description      : description of the decoration
*  @returns {Number} decoration_id  : the id of the decoration
*
*/

module.exports.add = ( name, picturePath, price, description ) => {

    const new_decoration = db.prepare('INSERT INTO decorations (name, picture_path, price, description) VALUES (?, ?, ?, ?) RETURNING decoration_id');
    const returning = new_decoration.get( name, picturePath, price, description );
    return returning.decoration_id;

};

/**
*   Method findById() :
*
*  1 - Prepare the SQLite request to find a decoration with its id
*  2 - Put the parameters into the SQLite request
*  3 - Return the decoration object
*
*  @param {Number} decoration_id    : id of the decoration
*  @returns {Object} decoration     : the decoration object
*
*/

module.exports.findById = ( decoration_id ) => {

    const stmt_find = db.prepare("SELECT * FROM decorations WHERE decoration_id = ?");
    const decoration = stmt_find.get( decoration_id );
    return decoration;

};

/**
*   Method listFavorites() :
*
*  1 - Prepare the SQLite request to list all the favorite decoration of the user
*  2 - Put the parameters into the SQLite request
*  3 - Use the function 'formatDate'
*  4 - Return the list of favorite decorations
*
*  @param {Number} user_id          : id of the user
*  @returns [ listFavorites ]       : the list of favorite decorations
*
*/

module.exports.listFavorites = ( user_id ) => {

    const select_listFavorites = db.prepare('SELECT dec.* FROM favorites fav, decorations dec WHERE fav.user_id = ? AND fav.decoration_id = dec.decoration_id')
    const listFavorites = select_listFavorites.all( user_id );
    return formatDate( listFavorites );
    
};

/**
*   Method isFavorite() :
*
*  1 - Prepare the SQLite request to find if a decoration is favorite
*  2 - Put the parameters into the SQLite request
*  3 - Use the function 'formatDate'
*  4a - Return true if the decoration is favorite
*  4b - Return false if the decoration is not favorite
*
*  @param {Number} user_id                       : id of the user
*  @param {Number} decoration_id                 : id of the decoration
*  @returns {Boolean} favorite !== undefined     : the list of favorite decorations
*
*/

module.exports.isFavorite = ( user_id, decoration_id ) => {

    const select_favorite = db.prepare('SELECT * FROM favorites WHERE user_id = ? AND decoration_id = ?');
    const favorite = select_favorite.get( user_id, decoration_id );
    return favorite !== undefined;

};

/**
*   Method toggleFavorite() :
*
*  1 - Prepare the SQLite request to find a favorite decoration
*  2 - Put the parameters into the SQLite request
*  3a - If the decoration is in favorites table
*  4a - Prepare the SQLite request to delete the decoration from favorites table
*  5a - Put the parameters into the SQLite request
*
*  3b - If the decoration is not in favorites table
*  4b - Prepare the SQLite request to insert into the decoration the favorites table
*  5b - Put the parameters into the SQLite request
*
*  @param {Number} user_id          : id of the user
*  @param {Number} decoration_id    : id of the decoration
*
*/

module.exports.toggleFavorite = ( user_id, decoration_id ) => {

    const select_favorite = db.prepare('SELECT * FROM favorites WHERE user_id = ? AND decoration_id = ?');
    const favorite = select_favorite.get( user_id, decoration_id );

    if ( favorite !== undefined ){

        const stmt_delete = db.prepare('DELETE FROM favorites WHERE decoration_id = ? AND user_id = ?');

        stmt_delete.run( decoration_id, user_id );

    }
    else {

        const stmt_insert = db.prepare('INSERT INTO favorites (decoration_id, user_id) VALUES (?, ?)');

        stmt_insert.run( decoration_id, user_id );

    };

};

/**
*   Method sortPriceDate() :
*
*  1 - Memorise the beginning of the request in a string
*  2 - check if sort equals to "mostExpensive" : if true, sort : Most Expensive -> Less Expensive
*  3 - else, check if sort equals to "lessExpensive" : if true, sort : Most Recent -> Less Recent
*  4 - else, check if sort equals to "mostRecent" : if true, sort : Most Expensive -> Less Expensive
*  5 - else, check if sort equals to "lessRecent" : if true, sort : Less Recent -> Most Recent*
*  6 - Prepare the SQLite request, and take all the elements of the table
*  7 - Use of the method 'formatDate'
*  8 - Return the sorted list
*
*  @param {String} price_sort   : type of price sorting ( mostExpensive, lessExpensive, mostRecent, lessRecent )
*  @returns [sortedList]        : list sorted by the type of sorting
*
*/

module.exports.sortPriceDate = ( sort ) => {
    
    let request = 'SELECT * FROM decorations dec ORDER BY ';

    if ( sort === "mostExpensive" ) request += 'price DESC';
    else if ( sort === "lessExpensive" ) request += 'price ASC';
    else if ( sort === "mostRecent" ) request += 'date_added DESC';
    else if ( sort === "lessRecent" ) request += 'date_added ASC';

    const sortedList = db.prepare( request ).all();

    return formatDate( sortedList );

};
