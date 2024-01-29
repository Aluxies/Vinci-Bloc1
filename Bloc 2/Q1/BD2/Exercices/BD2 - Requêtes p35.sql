-- 1

SELECT t1.title_id, t1.title, t1.price
FROM titles t1, publishers p
WHERE t1.pub_id = p.pub_id
    AND p.pub_name = 'Algodata Infosystems'
    AND t1.price = ( SELECT MAX( t2.price )
                     FROM titles t2
                     WHERE t2.price IS NOT NULL
                        AND t2.pub_id = p.pub_id);

-- 2

SELECT t.title_id, COUNT(sd.stor_id)
FROM titles t, salesdetail sd
WHERE t.title_id = sd.title_id
GROUP BY t.title_id
HAVING COUNT(sd.stor_id) > 1;

-- 3

SELECT t1.title_id, t1.title
FROM titles t1
WHERE t1.price IS NOT NULL
    AND t1.price > (SELECT 1.5 * AVG(t2.price)
                 FROM titles t2
                 WHERE t2.type = t1.type );

-- 4

SELECT DISTINCT a.au_id, a.au_fname, a.au_lname
FROM authors a, titleauthor ta
WHERE a.au_id = ta.au_id
    AND 1 <= (SELECT COUNT(t.title_id)
              FROM titles t, publishers p
              WHERE ta.title_id = t.title_id
                AND t.pub_id = p.pub_id
                  AND a.state = p.state);

-- 5

SELECT p.pub_id, p.pub_name
FROM publishers p
WHERE p.pub_id NOT IN (SELECT t.pub_id
                        FROM titles t);

-- 6

SELECT p.pub_id, p.pub_name
FROM publishers p, titles t
GROUP BY p.pub_id, p.pub_name
HAVING COUNT( * ) >= ALL (
    SELECT COUNT( * )
    FROM titles t2
    GROUP BY t2.title_id
    );

-- 7

SELECT p.pub_id, p.pub_name
FROM publishers p
WHERE p.pub_id NOT IN (
    SELECT t.pub_id
    FROM salesdetail sd, titles t
    WHERE sd.title_id = t.title_id
    )

-- 8

SELECT DISTINCT t.title_id, t.title
FROM titles t, publishers p, salesdetail sd, titleauthor ta, authors a
WHERE t.pub_id = p.pub_id
    AND t.title_id = sd.title_id
    AND t.title_id = ta.title_id
    AND ta.au_id = a.au_id
    AND a.state = 'CA'
    AND p.state = 'CA'
    AND NOT EXISTS (
        SELECT *
        FROM salesdetail sd, stores st
        WHERE t.title_id = sd.title_id
            AND sd.stor_id = st.stor_id
            AND st.state <> 'CA'
    )

-- 9

SELECT DISTINCT t.title_id, t.title, s.date
FROM titles t, salesdetail sd, sales s
WHERE t.title_id = sd.title_id
    AND sd.stor_id = s.stor_id
    AND s.date = ( SELECT MAX(s2.date)
                   FROM sales s2);

-- 10

SELECT st.stor_id, st.stor_name
FROM stores st
WHERE NOT EXISTS (
    SELECT *
    FROM stores st2, salesdetail sd
    WHERE sd.stor_id = st2.stor_id
    AND st2.stor_name LIKE 'Bookbeat'
    AND NOT EXISTS( 
        SELECT *
        FROM salesdetail sd1
        WHERE sd1.title_id = sd.title_id
        AND sd1.stor_id = st.stor_id
        )
    )
AND st.stor_name <> 'Bookbeat'

-- 11

SELECT a.city, a.state
FROM authors a
WHERE a.state = 'CA'
    AND a.city NOT IN (
        SELECT s.city
        FROM stores s
        WHERE s.state = 'CA'
            AND s.city IS NOT NULL
    )
    AND a.city IS NOT NULL

-- 12

SELECT p.pub_id, p.pub_name
FROM authors a, publishers p
WHERE a.city = p.city
    AND a.state = p.state
GROUP BY p.pub_id, p.pub_name
HAVING COUNT( a.au_id ) >= ALL (
    SELECT COUNT( a2.au_id )
    FROM authors a2
    GROUP BY a2.city, a2.state
    )

-- 13

SELECT DISTINCT t.title_id, t.title
FROM titles t, titleauthor t1
WHERE t.title_id = t1.title_id
    AND NOT EXISTS (
        SELECT ta2.au_id
        FROM titleauthor ta2
        INNER JOIN authors a
        ON ta2.au_id = a.au_id
        WHERE t.title_id = ta2.title_id
            AND a.state <> 'CA'
    )

-- 14

SELECT DISTINCT t.title_id, t.title
FROM titles t, titleauthor t1
WHERE t.title_id = t1.title_id
    AND NOT EXISTS (
        SELECT ta2.au_id
        FROM titleauthor ta2
        INNER JOIN authors a
        ON ta2.au_id = a.au_id
        WHERE t.title_id = ta2.title_id
            AND a.state = 'CA'
    )

-- 15

SELECT t.title_id, t.title
FROM titles t, titleauthor ta
WHERE ta.title_id = t.title_id
GROUP BY t.title_id, t.title
HAVING COUNT( ta.au_id ) = 1

-- 16

SELECT t.title_id, t.title
FROM titles t, titleauthor ta, authors a
WHERE t.title_id = ta.title_id
    AND ta.au_id = a.au_id
    AND a.state = 'CA'
    AND 1 = (
        SELECT COUNT( ta2.au_id )
        FROM titleauthor ta2
        WHERE ta2.title_id = t.title_id
    )