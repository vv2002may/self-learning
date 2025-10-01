-- Write a query that returns the highest customer ID registered in each store. You can use the customer table and the store_id column to get the store ID.
-- Write Query Here

SELECT store_id, MAX(customer_id) as "Highest Customer ID"
FROM customer
GROUP BY store_id;