-- Create a query that retrieves the last 2 actors from the actor table with the first name PENELOPE. Include the actor_id, first_name, and last_name columns in your result. Rename the actor_id, first_name, and last_name columns to Actor ID, First Name, and Last Name, respectively.
-- Write Query Here

SELECT actor_id as "Actor ID", first_name as "First Name", last_name as "Last Name"
FROM actor
WHERE first_name="PENELOPE"
ORDER BY actor_id DESC
LIMIT 2;
