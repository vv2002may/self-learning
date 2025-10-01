-- Retrieve the 10 most recently updated films in the film table. Include the title, release_year, and last_update columns in your result. Rename the last_update column to Last Updated.
-- Write Query Here

SELECT title, release_year, last_update as "Last Updated"
FROM film
ORDER BY last_update
LIMIT 10;