const db = require('./db_conf.js');


/**
 * Add an order into the database, it is open by default, the datetime is automatically set to the current datetime
 * @param {*} user_id 
 * @param {*} curtain_height
 * @param {*} curtain_width 
 * @param {*} price 
 * @param {*} fabric_id
 */
module.exports.add = (user_id,curtain_height, curtain_width, price, fabric_id) => {
    const new_order = db.prepare('INSERT INTO curtains_orders (user_id, curtain_height, curtain_width, price, fabric_id) VALUES (?, ?, ?, ?, ?)');
    new_order.run( user_id, curtain_height, curtain_width, price, fabric_id);
}



/**Get all the orders from a specific user
 * @param {*} user_id 
 * @returns a table with the id, date and status of each orders for this user
 */
module.exports.getOrdersFor = (user_id) => {
    const orders = db.prepare('SELECT order_id, order_date, status FROM curtains_orders WHERE user_id = ? ORDER BY order_date DESC');
    result = orders.all(user_id);

    for ( let order of result ){
        const date = new Date( order.order_date );
        const year = date.getFullYear()
        const month = date.getMonth() + 1;
        const day = date.getDate();

        let hour = date.getHours();
        let minute = date.getMinutes();

        // Display the minute/hour in 2 numbers format
        if(minute >=0 && minute < 10) minute = "0"+minute;
        if(hour >=0 && hour < 10) hour = "0"+hour;

        order.order_date = `${day}/${month}/${year} - ${hour}:${minute}`;

        order["orderDisplay"] = "test";
        if(order.status === "open") order.orderDisplay = "Ouvert";
        else if(order.status === "paid") order.orderDisplay = "Payé";
        else if(order.status === "sent") order.orderDisplay = "Envoyé";

        order.status = order.status.charAt(0).toUpperCase() + order.status.slice(1);
    };


    return result;
}



/** Get everything to know about each orders
 * It is recommended to keep this method for admin purposes
 * @returns firstname, lastname, order id, orderdate and status of all orders
 */
module.exports.getAllOrders = () => {
    const orders = db.prepare('SELECT user.firstname, user.lastname, ord.order_id, ord.order_date, ord.status FROM curtains_orders ord, users user WHERE ord.user_id = user.user_id ORDER BY ord.order_date DESC');
    let result = orders.all()

    for ( let order of result ){
        const date = new Date( order.order_date );
        const year = date.getFullYear()
        const month = date.getMonth() + 1;
        const day = date.getDate();

        let hour = date.getHours();
        let minute = date.getMinutes();

    // Display the minute/hour in 2 numbers format (01:09 instead of 1:9)
                if(minute >=0 && minute < 10) minute = "0"+minute;
                if(hour >=0 && hour < 10) hour = "0"+hour;
    // Modifying date format            
        order.order_date = `${day}/${month}/${year} - ${hour}:${minute}`;

    // Adding a text to display the status in french on screen and 2 booleans for the admin page, used for handlebars
        if(order.status === "open") {
            order["orderDisplay"] = "Ouvert";
            order["isPaid"] = false;
            order["isSent"] = false;
        }
        else if(order.status === "paid"){
            order["orderDisplay"] = "Payé";  
            order["isPaid"] = true;
            order["isSent"] = false;
        } 
        else if(order.status === "sent"){
            order["orderDisplay"] = "Envoyé"; 
            order["isPaid"] = true;
            order["isSent"] = true;
        } 
        // Place an uppercase at the first char of the string
        order.status = order.status.charAt(0).toUpperCase() + order.status.slice(1);
    };
   
    return result;
}


/**
 * Update the status of an order
 * @param {*} order_id 
 * @param {*} status 
 */
module.exports.setStatus = (order_id, status) =>{
    if(status === undefined || isNaN(order_id)) return false;
    if(status !== "sent" && status !== "paid" && status !== "sent") return false;

    const update = db.prepare("UPDATE curtains_orders SET status = ? WHERE order_id = ?");
    update.run(status, order_id);
}

/**
 * Get the total amount of orders
 * @returns The amount of orders already created, whether they are closed or not
 */
module.exports.ordersCount = () => {
    const orders = db.prepare('SELECT order_id FROM curtains_orders');
    return orders.all().length;
}
