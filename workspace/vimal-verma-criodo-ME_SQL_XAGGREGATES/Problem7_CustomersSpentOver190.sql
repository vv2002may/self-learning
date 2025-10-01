-- Write a query that returns the customer_id and the total amount paid by users who spent more than $190.
-- Write Query Here

SELECT customer_id, SUM(amount) as "Total Amount Paid"
FROM payment
GROUP BY customer_id
HAVING SUM(amount)>190;