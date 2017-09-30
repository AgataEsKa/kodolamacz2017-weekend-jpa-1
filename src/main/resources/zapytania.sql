select code, name, code + 1 from manufacturers;

select * from products;

select name, price from products where price <= 120 and price >= 60;
select name, price from products where price between 60 and 120;

select name, (price * 100)  priceInCents, price + 20 as plusTwenty from products;

select avg(price)::integer, avg(price), cast (avg(price) as integer) from products;


select * from products where code = 2;
select avg(price) from products where code = 2;

select count(*) from products where price >= 180;


select name, price
from products
where price >= 180
order by price desc, name;

select * from products;
select * from manufacturers;

select p.*, m.name
from products p
full join manufacturers m on p.manufacturer = m.code;

select (p.name) ProductName, (m.name) ManuName
from  products p
full join manufacturers m on p.manufacturer = m.code;

select avg(price), manufacturer, m.name, min(price)
from products p
  join manufacturers m on p.manufacturer = m.code
group by manufacturer, m.name;

select * from
  (select avg(price), manufacturer, min(price)
   from products p
   group by manufacturer) q1
  join manufacturers m on q1.manufacturer = m.code;

-- tworzenie widoku
CREATE VIEW avgManufacturerPrice as
  select avg(price), manufacturer, min(price)
  from products p
  group by manufacturer;

-- możemy używać widoku jak zwykłej tabelki
select avg, min, m.name from avgmanufacturerprice a
  join manufacturers m on a.manufacturer = m.code;

select (avg(price))  avgprice, manufacturer, m.name
from products p
  join manufacturers m on m.code = p.manufacturer
group by manufacturer, m.name
having avg(price) > 150;


select * from
  (select (avg(price))  avgprice, manufacturer
   from products
   group by manufacturer) q1
  join manufacturers m on m.code = q1.manufacturer
where avgprice > 150;

-- zwraca minimalną cenę i minimalną nazwę (pierwszą w kolejność alfabetycznej)
select min(price), min(name)
from products;

select price, name
from products
where price = (select min(price) from products);

select price, name
from products
order by price
limit 1;

select m.name, p.name, price
from products p
  join
  (select manufacturer, max(price) maxprice
   FROM products
   group by manufacturer) q1 on p.manufacturer = q1.manufacturer and price = maxprice
  join manufacturers m on m.code = p.manufacturer
order by m.name;

insert INTO products(code, name, price, manufacturer) VALUES (12,'Loudspeakers',70,2);


create database orders;
update products set name = 'Laser Printer' where code=8;
update products set price = price * 0.9;