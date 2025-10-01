-- Find all actors whose last names contain the letters "LI". Order the rows by last_name and then by first_name
-- Write Query Here

SELECT actor_id, first_name, last_name
FROM actor
WHERE last_name like "%LI%"
ORDER BY last_name, first_name;