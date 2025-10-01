-- Create a query that returns the details of films from the film table that have a length of exactly 150 minutes. Include title, description, and release_year columns in your result. Display only the first 5 films that satisfy this condition.
-- Write Query Here

SELECT title, description, release_year 
FROM film
WHERE length=150
LIMIT 5;