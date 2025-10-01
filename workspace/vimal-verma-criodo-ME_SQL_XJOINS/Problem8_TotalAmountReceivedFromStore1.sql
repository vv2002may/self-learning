-- Calculate the total amount of money collected from payments associated with the store having store_id 1. To identify these payments, use the staff table to first find which staff members work at this store. Rename the resulting total amount to "Total Amount Received from Store 1."
-- Write Query Here

SELECT SUM(p.amount) as "Total Amount Received from Store 1"
FROM payment p
JOIN staff s
ON p.staff_id=s.staff_id AND s.store_id=1;