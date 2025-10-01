-- Calculate the average replacement cost of films, but only for films that have a rating of PG. Rename the column to Average Replacement Cost.
-- Write Query Here

SELECT AVG(replacement_cost) as "Average Replacement Cost"
FROM film
WHERE rating like "PG";