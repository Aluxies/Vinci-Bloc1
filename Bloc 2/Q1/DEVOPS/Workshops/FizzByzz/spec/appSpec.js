const Main = require('../src/app.js');

describe( "A suite of test for the main class", () => {

    // Arrange
    const main = new Main();
    const { fizzByzz } = main;

    it( "fizzByzz function should return 1 when given 1", () => {

        // Act
        let result = fizzByzz(1);

        // Assert
        expect(result).toBe(1);

    });

    it( "fizzByzz function should return 2 when given 2", () => {

        // Act
        let result = fizzByzz(2);

        // Assert
        expect(result).toBe(2);

    });

    it( "fizzByzz function should return fizz when given 3", () => {

        // Act
        let result = fizzByzz(3);

        // Assert
        expect(result).toBe("fizz");

    });
    
    it( "fizzByzz function should return 4 when given 4", () => {

        // Act
        let result = fizzByzz(4);

        // Assert
        expect(result).toBe(4);

    });

    it( "fizzByzz function should return byzz when given 5", () => {

        // Act
        let result = fizzByzz(5);

        // Assert
        expect(result).toBe("byzz");

    });
    


});