-- Display the first and last names, as well as the address, of each staff member.
-- Write Query Here


SELECT s.first_name, s.last_name, a.address
FROM staff s
JOIN address a 
ON s.address_id = a.address_id;