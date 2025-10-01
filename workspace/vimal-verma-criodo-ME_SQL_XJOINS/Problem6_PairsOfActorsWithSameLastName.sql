-- List all pairs of actors who share the same last name. Display the actor_id and first_name of both actors in each pair, and sort the results first by last_name and then by first_name to group and order the pairs clearly.
-- Write Query Here
SELECT a1.actor_id as actor_id1,
    a1.first_name as first_name1,
    a2.actor_id as actor_id2,
    a2.first_name as first_name2
FROM actor a1
    JOIN actor a2 ON a1.last_name = a2.last_name
    AND a1.actor_id < a2.actor_id
ORDER BY a1.last_name,
    a1.first_name;