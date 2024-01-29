const listeExolunes = [];

module.exports.list = () => {

    return listeExolunes;

};

module.exports.add = ( data ) => {

    if ( Array.isArray( data ) && data.length > 0 ){

        for ( const exolune of data ){

            listeExolunes.push( exolune );
            
        };

    } 
    else if ( typeof data === "object" ){

        listeExolunes.push( data );

    } 
    else {

        console.error( `Ajout échoué : le paramètre entré n'est ni de type 'array' ni de type 'object'\nValeur du paramètre reçu : ${data}\nType du paramètre reçu : ${typeof data}` );

    };

};