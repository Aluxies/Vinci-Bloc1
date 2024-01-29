function greet(name) {
    if (!Array.isArray(name)) {
        if (checkNameNotDefined(name)) {
            return 'Hello, my friend.';
        }

        if (checkUpperCase(name)) {
            return 'HELLO, ' + name + '!';
        }

        if (!checkUpperCase(name)) {
            return 'Hello, ' + name + '.';
        }
    } else {
        return checkAllNames(name);
    }
}

function checkAllNames(array) {
    const arrayLower = array.filter((x) => !checkUpperCase(x));

    let language;
    const languageVerif = arrayLower[arrayLower.length - 1];
    if (
        languageVerif == 'fr' ||
        languageVerif == 'nl' ||
        languageVerif == 'en'
    ) {
        language = languageVerif;
        arrayLower.pop();
    }

    const stringLower = checkAllLowerNames(arrayLower, language);

    const arrayUpper = array.filter((x) => checkUpperCase(x));

    const stringUpper = checkAllUpperNames(arrayUpper, language);

    return stringLower + stringUpper;
}

function checkNameNotDefined(name) {
    return name === undefined || name === ' ' || name === null;
}

function checkUpperCase(name) {
    return name.toUpperCase() === name;
}

function checkAllUpperNames(arrayUpper, language) {
    let stringUpper = '';

    if (arrayUpper.length > 0) {
        stringUpper = checkUpperLanguage(language);
        for (let i = 0; i < arrayUpper.length; i++) {
            if (i == arrayUpper.length - 1) {
                stringUpper += ' ' + arrayUpper[i] + '!';
            } else {
                stringUpper += ' ' + arrayUpper[i] + ',';
            }
        }
    }
    return stringUpper;
}

function checkUpperLanguage(language) {
    let stringUpper;

    if (language == 'fr') {
        stringUpper = ' ET BONJOUR';
    } else if (language == 'nl') {
        stringUpper = ' EN GOEIEDAG';
    } else {
        stringUpper = ' AND HELLO';
    }

    return stringUpper;
}

function checkAllLowerNames(arrayLower, language) {
    let stringLower;
    let andWord;

    if (arrayLower.length > 0) {
        let result = checkLowerLanguage(language);
        stringLower = result.stringLower;
        andWord = result.andWord;
        for (let i = 0; i < arrayLower.length; i++) {
            if (i == arrayLower.length - 1 && arrayLower.length > 1) {
                stringLower += andWord + arrayLower[i];
            } else {
                stringLower += ', ' + arrayLower[i];
            }
        }
    }

    return stringLower + '.';
}

function checkLowerLanguage(language) {
    let stringLower;
    let andWord;

    if (language == 'fr') {
        andWord = ' et ';
        stringLower = 'Bonjour';
    } else if (language == 'nl') {
        andWord = ' en ';
        stringLower = 'Goeiedag';
    } else {
        andWord = ' and ';
        stringLower = 'Hello';
    }

    return { stringLower, andWord };
}

module.exports = greet;
