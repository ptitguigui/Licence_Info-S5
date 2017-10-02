-- Q2
\echo '\nQuestion 2\n'
select a.anom, c.prix
from catalogue as c natural join articles as a
where prix >= (select max(prix) from catalogue);

-- Q4
-- il faut trouver 3 micro sd et pas 4
\echo '\nQuestion 4\n'
select anom as article, count(*) as nb_a
from (select anom, acoul
      from articles
      group by anom, acoul) as dummy
group by anom;

-- Q6
\echo '\nQuestion 6\n'
select acoul
from articles
group by acoul
having count(*) = 1;

-- Q8
\echo '\nQuestion 8\n'
select aid, acoul, count(*)
from catalogue natural join articles
group by aid, acoul;

-- Q10
\echo '\nQuestion 10\n'

-- Q12
\echo '\nQuestion 12\n'

-- Q14
\echo '\nQuestion 14\n'
