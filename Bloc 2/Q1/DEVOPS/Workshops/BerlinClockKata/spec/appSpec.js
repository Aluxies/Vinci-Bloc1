const Main = require("../src/app");

const main = new Main();

describe("phase", function() {

    it("phase 1",function() {
    let result = main.simpleMinutes(1);

        expect(result).toBe("j");
    });

    it("phase 2", function(){
        let result = main.simpleMinutes(2);

        expect(result).toBe("jj");
    });

    it("phase 3", function(){
        let result = main.simpleMinutes(3);

        expect(result).toBe("jjj");
    });

    it("phase 4", function(){
        let result = main.simpleMinutes(4);

        expect(result).toBe("jjjj");
    });

    it("phase 5", function(){
        let result = main.complexMinutes(5);

        expect(result).toBe("j");
    });

    it("phase 6", function(){
        let result = main.complexMinutes(10);

        expect(result).toBe("jj");
    });

    it("phase 7", function(){
        let result = main.complexMinutes(15);

        expect(result).toBe("jjj");
    });

    it("phase 8", function(){
        let result = main.complexMinutes(20);

        expect(result).toBe("jjjj");
    });

    it("phase 9", function(){
        let result = main.complexMinutes(25);

        expect(result).toBe("jjjjj");
    });

    it("phase 10", function(){
        let result = main.complexMinutes(30);

        expect(result).toBe("jjjjjj");
    });

    it("phase 11", function(){
        let result = main.complexMinutes(35);

        expect(result).toBe("jjjjjjj");
    });

    it("phase 12", function(){
        let result = main.complexMinutes(40);

        expect(result).toBe("jjjjjjjj");
    });

    it("phase 13", function(){
        let result = main.complexMinutes(45);

        expect(result).toBe("jjjjjjjjj");
    });

    it("phase 14", function(){
        let result = main.complexMinutes(50);

        expect(result).toBe("jjjjjjjjjj");
    });

    it("phase 15", function(){
        let result = main.complexMinutes(55);

        expect(result).toBe("jjjjjjjjjjj");
    });

});
