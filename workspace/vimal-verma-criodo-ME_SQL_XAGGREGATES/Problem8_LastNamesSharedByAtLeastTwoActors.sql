-- List the last names of actors and the number of actors who have that last name, but only for names that are shared by at least two actors.
-- Write Query Here

SELECT last_name, COUNT(actor_id) as "number_of_actors"
FROM actor
GROUP BY last_name
HAVING COUNT(actor_id)>1;