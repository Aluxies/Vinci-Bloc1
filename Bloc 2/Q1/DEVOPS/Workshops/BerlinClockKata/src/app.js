class Main{

    simpleMinutes(minutes){

        if(minutes === 1){
            return "j";
        }

        if(minutes === 2){
            return "jj";
        }

        if(minutes === 3){
            return "jjj";
        }
        if(minutes === 4){
            return "jjjj";
        }
    }

    complexMinutes(minutes){

        if ( minutes % 5 === 0 && minutes < 60 ){

            return "j".repeat(minutes/5);
            
        }

    }
}
module.exports = Main