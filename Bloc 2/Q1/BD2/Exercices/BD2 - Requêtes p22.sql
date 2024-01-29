-- 1

SELECT DISTINCT t.title, t.price, p.pub_name
FROM titles t, publishers p
WHERE t.pub_id = p.pub_id

-- 2

SELECT DISTINCT t.title, t.price, p.pub_name
FROM titles t, publishers p
WHERE t.pub_id = p.pub_id
    AND t.type = 'psychology'

-- 3

SELECT DISTINCT a.au_fname, a.au_lname
FROM authors a, titleauthor ta
WHERE a.au_id = ta.au_id

-- 4

SELECT DISTINCT a.state
FROM authors a, titleauthor ta
WHERE a.au_id = ta.au_id

-- 5

SELECT DISTINCT st.stor_name, st.stor_address, st.stor_id
FROM stores st, sales sa
WHERE st.stor_id = sa.stor_id
    AND date_part( 'year', sa.date ) = '1991'
    AND date_part( 'month', sa.date ) = '11'

-- 6

SELECT DISTINCT t.title_id, title, type, pub_id, price, total_sales, pubdate
FROM titles t, publishers p
WHERE t.pub_id = p.pub_id
    AND t.type = 'psychology'
    AND t.price < 20
    AND p.pub_name NOT LIKE 'Algo%'

-- 7

SELECT t.title_id, t.title
FROM titles t, titleauthor ta, authors a
WHERE t.title_id = ta.title_id
    AND ta.au_id = a.au_id
    AND a.state = 'CA'

-- 8

SELECT a.au_id, a.au_lname, a.au_fname, a.phone, a.address, a.city, a.state, a.country
FROM titles t, titleauthor ta, authors a, publishers p
WHERE p.pub_id = t.pub_id
    AND t.title_id = ta.title_id
    AND ta.au_id = a.au_id
    AND p.state = 'CA'

-- 9

SELECT DISTINCT a.au_id, a.au_lname, a.au_fname, a.phone, a.address, a.city, a.state, a.country
FROM titles t, titleauthor ta, authors a, publishers p
WHERE p.pub_id = t.pub_id
    AND t.title_id = ta.title_id
    AND ta.au_id = a.au_id
    AND a.state = p.state

-- 10

SELECT p.pub_id, p.pub_name, p.city, p.state
FROM titles t, publishers p, sales sa, salesdetail sd
WHERE p.pub_id = t.pub_id
    AND t.title_id = sd.title_id
    AND sd.stor_id = sa.stor_id
    AND sd.ord_num = sa.ord_num
    AND sa.date >= '1/11/1990'
    AND sa.date <= '1/3/1991'

-- 11

SELECT DISTINCT st.stor_id, st.stor_name, st.state, st.country
FROM stores st, salesdetail sd, titles t
WHERE st.stor_id = sd.stor_id
    AND sd.title_id = t.title_id
    AND (t.title LIKE '%cook%'
    OR t.title LIKE '%Cook%')

-- 12

SELECT t1.title_id, t1.title, t2.title_id, t2.title
FROM titles t1, titles t2
WHERE t1.pub_id = t2.pub_id
    AND t1.pubdate = t2.pubdate
    AND t1.title_id < t2.title_id

-- 13

SELECT DISTINCT a.au_id, a.au_lname, a.au_fname
FROM authors a, titleauthor ta, titles t
WHERE a.au_id = ta.au_id
    AND ta.title_id = t.title_id
GROUP BY a.au_id, a.au_lname, a.au_fname
HAVING COUNT( DISTINCT t.pub_id ) > 1

-- 14

SELECT DISTINCT t.title_id, t.title
FROM titles t, salesdetail sd, sales sa
WHERE t.title_id = sd.title_id
    AND sd.stor_id = sa.stor_id
    AND sd.ord_num = sa.ord_num
    AND sa.date < t.pubdate

-- 15

SELECT DISTINCT st.stor_id, st.stor_name
FROM stores st, salesdetail sd, titleauthor ta, authors a
WHERE st.stor_id = sd.stor_id
    AND sd.title_id = ta.title_id
    AND ta.au_id = a.au_id
    AND a.au_lname = 'Ringer'
    AND a.au_fname = 'Anne'

-- 16

SELECT DISTINCT a.state
FROM authors a, titleauthor ta, salesdetail sd, sales s, stores st
WHERE a.au_id = ta.au_id
    AND ta.title_id = sd.title_id
    AND sd.stor_id = s.stor_id
    AND sd.ord_num = s.ord_num
    AND s.stor_id = st.stor_id
    AND st.state = 'CA'
    AND date_part( 'year', s.date ) = '1991'
    AND date_part( 'month', s.date ) = '2'
    AND a.state IS NOT NULL

-- 17

SELECT DISTINCT st1.stor_id, st1.stor_name, st2.stor_id, st2.stor_name
FROM stores st1, stores st2, sales s1, sales s2, salesdetail sd1, salesdetail sd2, titleauthor ta1, titleauthor ta2, authors a
WHERE (st1.stor_id = s1.stor_id
    AND s1.ord_num = sd1.ord_num
    AND s1.stor_id = sd1.stor_id
    AND sd1.title_id = ta1.title_id
    AND ta1.au_id = a.au_id)
    AND st1.stor_id < st2.stor_id
    AND st1.state = st2.state
    AND (st2.stor_id = s2.stor_id
    AND s2.ord_num = sd2.ord_num
    AND s2.stor_id = sd2.stor_id
    AND sd2.title_id = ta2.title_id
    AND ta2.au_id = a.au_id)

-- 18

SELECT DISTINCT a1.au_id, a1.au_fname, a1.au_lname, a2.au_id, a2.au_fname, a2.au_lname
FROM authors a1, authors a2, titleauthor ta1, titleauthor ta2
WHERE a1.au_id = ta1.au_id
    AND a2.au_id = ta2.au_id
    AND ta2.title_id = ta1.title_id
    AND a1.au_id < a2.au_id

-- 19

SELECT sd.ord_num, sd.stor_id, t.title_id, t.price, st.stor_name, sd.qty, (sd.qty*t.price) AS "Montant total", (((sd.qty * t.price)/100)*2) AS "Eco taxe"
FROM titles t, salesdetail sd, stores st
WHERE t.title_id = sd.title_id
    AND sd.stor_id = st.stor_id