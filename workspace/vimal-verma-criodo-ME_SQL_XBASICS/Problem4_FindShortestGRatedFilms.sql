-- Find the 5 shortest films in the film table that are rated 'G'. Display the columns title, length, and rating. Sort the result by the length of the film in ascending order.
-- Write Query Here

SELECT title, length, rating
FROM film
WHERE rating="G"
ORDER BY length
LIMIT 5;