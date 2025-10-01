-- Write a query that returns the title, rental_duration, and rating of films in the film table where the rental_duration is greater than 5 days.
-- Write Query Here

SELECT title, rental_duration, rating
FROM film
WHERE rental_duration > 5;