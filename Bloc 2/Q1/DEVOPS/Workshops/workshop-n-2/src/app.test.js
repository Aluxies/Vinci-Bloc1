const {checkNames} = require('./app');

test('array containing no name with a x', () => {
  const result = checkNames(['Momo', 'Hadjil', 'Leo']);
  expect(result).toStrictEqual(['Momo', 'Hadjil', 'Leo']);
});

test('array containing two names with a x', () => {
  const result = checkNames(['DMX', 'Xzibit', 'JeanKevin']);
  expect(result).toStrictEqual(['JeanKevin']);
});

test('array containing one name with a x', () => {
  const result = checkNames(['JC', 'Leo', 'xena']);
  expect(result).toStrictEqual(['JC', 'Leo']);
});