-- 1

SELECT st.stor_id, st.stor_name, SUM( t.price * sd.qty ) AS "chiffre_affaire_total"
FROM stores st LEFT OUTER JOIN salesdetail sd ON st.stor_id = sd.stor_id
LEFT OUTER JOIN titles t ON sd.title_id = t.title_id
GROUP BY st.stor_id, st.stor_name
ORDER BY st.stor_name;

-- 2

SELECT st.stor_id, st.stor_name, SUM( t.price * sd.qty ) AS "chiffre_affaire_total"
FROM stores st LEFT OUTER JOIN salesdetail sd ON st.stor_id = sd.stor_id
LEFT OUTER JOIN titles t ON sd.title_id = t.title_id
GROUP BY st.stor_id, st.stor_name
ORDER BY SUM( t.price * sd.qty ) desc;

-- 3

SELECT t.title, t.type, p.pub_name, t.price
FROM titles t, publishers p
WHERE t.pub_id = p.pub_id
    AND t.price > 20
ORDER BY t.type

-- 4

SELECT DISTINCT t.title, t.type, a.au_fname, a.au_lname, t.price
FROM titles t LEFT OUTER JOIN titleauthor ta ON t.title_id = ta.title_id
LEFT OUTER JOIN authors a ON ta.au_id = a.au_id
WHERE t.price > 20
ORDER BY t.type;

-- 5

SELECT a.city
FROM authors a
WHERE a.state = 'CA'
UNION
SELECT p.city
FROM publishers p
WHERE p.state = 'CA'
EXCEPT
SELECT st.city
FROM stores st
WHERE st.state = 'CA';

-- 6

SELECT a.au_fname, a.au_lname, COUNT( t.title_id ) AS "nb_livres_plus_de_20$"
FROM authors a LEFT OUTER JOIN titleauthor ta
ON a.au_id = ta.au_id
LEFT OUTER JOIN titles t
ON ta.title_id = t.title_id
AND t.price > 20
GROUP BY a.au_fname, a.au_lname
ORDER BY COUNT( t.title_id ) desc, a.au_fname, a.au_lname;
