-- Retrieve the top 10 longest length films in the film table which have a PG-13 rating. Include the title, length, and rating columns in your result.
-- Write Query Here

SELECT title,length,rating
FROM film
WHERE rating="PG-13"
ORDER BY length DESC
LIMIT 10;