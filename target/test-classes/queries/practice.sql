SELECT B.name, BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id = BC.id;

-- US01 -1
select count(id)
from users; -- 4891

select count(distinct id)
from users; -- 4891


select *
from users;
-- 4891

-- US02-CK
SELECT count(*)
FROM book_borrow
where is_returned = 0;

-- US03-CK

SELECT distinct name
from book_categories;

-- US04-CK

SELECT b.name, b.isbn, b.year, b.author, bc.name, b.description
FROM books b
         join book_categories bc
              on b.book_category_id = bc.id
where b.name like 'Test2023';

-- US05-CK
select *
from book_borrow;
select *
from books;
select *
from book_categories;

select bc.name, count(borrowed_date)
from book_borrow bb
         join books b on b.id = bb.book_id
         join book_categories bc on bc.id = b.book_category_id
group by book_id
order by count(borrowed_date) desc;


-- US06-CK

select name
from books
where name like 'Head First Java';
select name
from books
where name like 'The Scrum Field Guide';

-- US07-CK

select *
from books;
select *
from book_borrow;
select *
from users
where full_name like ('Test Student 5');

select b.name
from book_borrow bb
         join books b on bb.book_id = b.id
         join users u on u.id = bb.user_id
where full_name like ('Test Student 5');

