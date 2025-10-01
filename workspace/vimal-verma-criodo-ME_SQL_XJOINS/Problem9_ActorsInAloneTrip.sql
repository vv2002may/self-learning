-- Display all actors who appear in the film "Alone Trip" using subqueries.
-- Write Query Here

SELECT a.first_name, a.last_name
FROM actor a 
JOIN film f
ON f.title="Alone Trip" 
JOIN film_actor fa
ON f.film_id=fa.film_id AND fa.actor_id= a.actor_id;
