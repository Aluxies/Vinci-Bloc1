-- 1

SELECT a.au_id, a.au_lname
FROM authors a
WHERE a.city = 'Oakland'

-- 2

SELECT a.au_id, a.au_lname, a.address
FROM authors a
WHERE a.au_fname LIKE 'A%'

-- 3

SELECT a.au_lname, a.address
FROM authors a
WHERE a.phone IS NULL

-- 4

SELECT a.au_id, au_lname, au_fname, phone, address, city, state, country
FROM authors a
WHERE a.state = 'CA'
    AND a.phone NOT LIKE '415%'

-- 5

SELECT a.au_id, au_lname, au_fname, phone, address, city, state, country
FROM authors a
WHERE a.country = 'BEL'
    OR a.country = 'LUX'
    OR a.country = 'NEL'

-- 6

SELECT t.pub_id
FROM titles t
WHERE t.type = 'psychology'

-- 7

SELECT t.pub_id, t.price
FROM titles t
WHERE t.type = 'psychology'
    AND (t.price < 10
    OR t.price > 25)

-- 8

SELECT a.au_id, a.city
FROM authors a
WHERE a.state = 'CA'
    AND (a.au_fname = 'Albert'
    OR a.au_lname LIKE '%er')
    AND a.city IS NOT NULL

-- 9

SELECT DISTINCT a.state, a.country
FROM authors a
WHERE a.state IS NOT NULL
    AND a.country <> 'USA'

-- 10

SELECT DISTINCT t.type
FROM titles t
WHERE t.price < 15
    AND t.type IS NOT NULL