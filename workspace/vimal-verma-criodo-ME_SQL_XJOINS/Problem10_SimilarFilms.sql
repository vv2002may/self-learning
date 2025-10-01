-- Write a query to find films that have the same rental duration and replacement cost as the film "ACADEMY DINOSAUR". Use a multiple-column subquery.
-- Write Query Here
SELECT title,
    rental_duration,
    replacement_cost
FROM film
WHERE rental_duration =(
        SELECT rental_duration
        FROM film
        WHERE title = "ACADEMY DINOSAUR"
    )
    AND replacement_cost =(
        SELECT replacement_cost
        FROM film
        WHERE title = "ACADEMY DINOSAUR"
    );