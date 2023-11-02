select *
from student s
where s.age between 10 and 40;

select s.name
from student s;

select s.name
from student s
where name like '%o%' ;

select *
from student s
where s.age < s.id;

select *
from student s
order by age;