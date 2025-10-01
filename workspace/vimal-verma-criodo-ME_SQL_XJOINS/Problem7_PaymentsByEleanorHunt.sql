-- Find the number of payments made by the customer ELEANOR HUNT in the payment table and rename it to Payments by ELEANOR HUNT.
-- Write Query Here
-- SELECT COUNT(p.customer_id) as "Payments by ELEANOR HUNT"
-- FROM payment p
-- JOIN customer c
-- ON p.customer_id=c.customer_id AND c.first_name="ELEANOR" AND c.last_name="HUNT";


SELECT COUNT(customer_id) as "Payments by ELEANOR HUNT"
FROM payment
WHERE customer_id IN (
        SELECT customer_id
        FROM customer
        WHERE first_name="ELEANOR" AND last_name="HUNT"
    );