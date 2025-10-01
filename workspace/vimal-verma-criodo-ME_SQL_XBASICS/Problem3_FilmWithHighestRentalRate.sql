-- Retrieve the details of the film with the highest rental rate in the film table. Display the columns title, rental_rate, and rating. For this exercise, assume that there is only one film with the maximum rental rate.
-- Write Query Here

SELECT title, rental_rate, rating
FROM film
ORDER BY rental_rate DESC
LIMIT 1;