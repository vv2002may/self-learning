-- Write a query to display for each store its store ID, city, and country.
-- Write Query Here

SELECT s.store_id, ci.city, co.country
FROM store s
JOIN address a
ON s.address_id=a.address_id
JOIN city ci
ON a.city_id=ci.city_id
JOIN country co 
ON ci.country_id=co.country_id;
