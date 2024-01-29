-- 1

SELECT AVG(t.price)
FROM titles t,
     publishers p
WHERE p.pub_id = t.pub_id
  AND p.pub_name = 'Algodata Infosystems';

--2 

SELECT a.au_id, a.au_fname, a.au_lname, AVG(t.price) as prix_moyen
FROM authors a
         LEFT OUTER JOIN titleauthor ta on a.au_id = ta.au_id
         LEFT OUTER JOIN titles t on ta.title_id = t.title_id
GROUP BY a.au_id, a.au_fname, a.au_lname;

-- 3

SELECT t.title_id, t.title, t.price, COUNT(ta.au_id)
FROM titles t
         LEFT OUTER JOIN titleauthor ta on t.title_id = ta.title_id
WHERE t.pub_id IN (SELECT p.pub_id
                   FROM publishers p
                   WHERE p.pub_name = 'Algodata Infosystems')
GROUP BY t.title_id, t.title, t.price;

-- 4

SELECT t.title_id, t.title, t.price, COUNT(DISTINCT sd.stor_id) as magasins
FROM titles t
         LEFT OUTER JOIN salesdetail sd ON t.title_id = sd.title_id
GROUP BY t.title_id, t.title, t.price;

-- 5

SELECT t.title_id, t.title
FROM titles t,
     salesdetail sd
WHERE t.title_id = sd.title_id
GROUP BY t.title_id, t.title
HAVING COUNT(DISTINCT sd.stor_id) > 1;

-- 6

SELECT t.type, COUNT(t.title_id) AS nbr_livre, AVG(t.price) AS prix_moyen
FROM titles t
WHERE t.type IS NOT NULL
GROUP BY t.type;

-- 7 

SELECT t.title_id, t.total_sales, SUM(sd.qty) AS total_qty
FROM titles t
         LEFT OUTER JOIN salesdetail sd ON t.title_id = sd.title_id
GROUP BY t.title_id, t.total_sales;

-- 8

SELECT t.title_id, t.total_sales, SUM(sd.qty) AS total_qty
FROM titles t
         LEFT OUTER JOIN salesdetail sd ON t.title_id = sd.title_id
GROUP BY t.title_id, t.total_sales
HAVING COALESCE(SUM(sd.qty), 0) != COALESCE(t.total_sales, 0);

-- 9

SELECT t.title_id, t.title
FROM titles t,
     titleauthor ta
WHERE t.title_id = ta.title_id
GROUP BY t.title_id, t.title
HAVING COUNT(ta.au_id) >= 3;

-- 10

SELECT SUM(sd.qty)
FROM titles t,
     publishers p,
     salesdetail sd,
     stores st
WHERE t.pub_id = p.pub_id
  AND t.title_id = sd.title_id
  AND sd.stor_id = st.stor_id
  AND t.title_id IN (SELECT ta.title_id
                     FROM titleauthor ta,
                          authors a
                     WHERE ta.au_id = a.au_id
                       AND a.state = 'CA')
  AND p.state = 'CA'
  AND st.state = 'CA';
