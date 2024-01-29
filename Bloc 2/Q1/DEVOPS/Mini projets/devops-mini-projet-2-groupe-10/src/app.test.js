const greet = require('./app');

test('print name', () => {
    const result = greet('Bob');
    expect(result).toBe('Hello, Bob.');
});

test('check name empty', () => {
    const result = greet(' ');
    expect(result).toBe('Hello, my friend.');
});

test('check name null', () => {
    const result = greet(null);
    expect(result).toBe('Hello, my friend.');
});

test('check name undefined', () => {
    const result = greet(undefined);
    expect(result).toBe('Hello, my friend.');
});

test('check upper name', () => {
    const result = greet('JERRY');
    expect(result).toBe('HELLO, JERRY!');
});

test('check double name', () => {
    const result = greet(['Jill', 'Jane']);
    expect(result).toBe('Hello, Jill and Jane.');
});

test('check triple name', () => {
    const result = greet(['Amy', 'Brian', 'Charlotte']);
    expect(result).toBe('Hello, Amy, Brian and Charlotte.');
});

test('check mutiple names upper and lower case', () => {
    const result = greet(['Amy', 'BRIAN', 'Charlotte']);
    expect(result).toBe('Hello, Amy and Charlotte. AND HELLO BRIAN!');
});

test('check mutiple names upper and lower case', () => {
    const result = greet(['Amy', 'BRIAN', 'Charlotte', 'ALEXIS']);
    expect(result).toBe('Hello, Amy and Charlotte. AND HELLO BRIAN, ALEXIS!');
});

test('check multiple language name fr (Lower)', () => {
    const result = greet(['Kratos', 'Thanatos', 'fr']);
    expect(result).toBe('Bonjour, Kratos et Thanatos.');
});

test('check multiple language name nl (Lower)', () => {
    const result = greet(['Kratos', 'Thanatos', 'nl']);
    expect(result).toBe('Goeiedag, Kratos en Thanatos.');
});

test('check multiple language name en (lower)', () => {
    const result = greet(['Kratos', 'Thanatos', 'en']);
    expect(result).toBe('Hello, Kratos and Thanatos.');
});

test('check multiple language name fr (lower and UpperCase)', () => {
    const result = greet(['Kratos', 'THANATOS', 'fr']);
    expect(result).toBe('Bonjour, Kratos. ET BONJOUR THANATOS!');
});

test('check multiple language name en (lower and UpperCase)', () => {
    const result = greet(['Kratos', 'THANATOS', 'en']);
    expect(result).toBe('Hello, Kratos. AND HELLO THANATOS!');
});

test('check multiple language name nl (lower and UpperCase)', () => {
    const result = greet(['Kratos', 'THANATOS', 'nl']);
    expect(result).toBe('Goeiedag, Kratos. EN GOEIEDAG THANATOS!');
});

// test du prof

test('test 1 du prof', () => {
    const result = greet('Jean-Kevin');
    expect(result).toBe('Hello, Jean-Kevin.');
});

test('test 2 a du prof', () => {
    const result = greet(null);
    expect(result).toBe('Hello, my friend.');
});

test('test 2 b du prof', () => {
    const result = greet(undefined);
    expect(result).toBe('Hello, my friend.');
});

test('test 2 c du prof', () => {
    const result = greet(' ');
    expect(result).toBe('Hello, my friend.');
});

test('test 3 du prof', () => {
    const result = greet('KENOBI');
    expect(result).toBe('HELLO, KENOBI!');
});

test('test 4 du prof', () => {
    const result = greet(['Kratos', 'Thanatos']);
    expect(result).toBe('Hello, Kratos and Thanatos.');
});

test('test 5 du prof', () => {
    const result = greet(['Kratos', 'Thanatos', 'Hypnos']);
    expect(result).toBe('Hello, Kratos, Thanatos and Hypnos.');
});

test('test 6 du prof', () => {
    const result = greet(['KRATOS', 'Thanatos', 'Hypnos']);
    expect(result).toBe('Hello, Thanatos and Hypnos. AND HELLO KRATOS!');
});

test('test 7 du prof', () => {
    const result = greet(['Kratos', 'Thanatos', 'fr']);
    expect(result).toBe('Bonjour, Kratos et Thanatos.');
});
