const listMessages = [];

class Message {

    constructor( author, message ){

        this.id = listMessages.length + 1,
        this.author = author,
        this.message = message,
        this.nb_likes = 0;
        
    };

};

module.exports.list = () => {

    return listMessages;

};

module.exports.add = ( author, message ) => {

    listMessages.push( new Message( author, message ) );

};

module.exports.like = ( id ) => {

    listMessages[id-1].nb_likes++;

};