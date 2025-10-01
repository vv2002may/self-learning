-- Write a query that returns the customer_id, the total spent amount (rename this column as total_amount), and the number of rentals (rename this column as total_rentals) of the customers that paid more than $100 in total and made less than 25 rentals.
-- Write Query Here

SELECT customer_id, SUM(amount) as "total_amount", COUNT(rental_id) as total_rentals
FROM payment
GROUP BY customer_id
HAVING SUM(amount)>100 AND COUNT(rental_id)<25;