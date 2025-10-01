-- Retrieve the number of cities available for each country in the city table. Show the count of cities available for each country. Display the results for the first 15 countries in ascending order.
-- Write Query Here

SELECT country_id, COUNT(city_id) as "Number of Cities"
FROM city
GROUP BY country_id
order by country_id
limit 15;