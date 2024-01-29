function checkNames(names) {
    const updatedNames = names.filter( name => !name.toLowerCase().includes('x'));
    return updatedNames;
}

module.exports = {
    checkNames
};