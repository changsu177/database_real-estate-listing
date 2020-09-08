 
# 1. User would like to know the property that added recently (in this year)

SELECT `PropertyName`, `PropertyLocation`
FROM `Property`
WHERE `AddTime` > '2020-01-01'AND `Status`='ACTIVE';


# 2. User wants to know what the Top 10 popular booking property

SELECT `property_id`
FROM `Property`
  LEFT JOIN `Booking`
  ON `Property`.`PropertyKey` = `Booking`.`property_id`
GROUP BY `property_id`
ORDER BY COUNT(`property_id`) DESC
LIMIT 10;


# 3. User wants to find 2 bedroom property with bikeable area

SELECT `PropertyName`FROM    
    (SELECT `property_id`
        FROM (SELECT FeatureKey
            FROM Feature f
            WHERE f.FeatureType='BEDROOM'AND `Count` =2) AS A
            JOIN FeaturePropertyMapping fpm
            ON A.FeatureKey = fpm.feature_id ) AS B
    JOIN
    (SELECT * FROM
        `Property` JOIN
        (SELECT `PropertyLocationKey`
            FROM
              (SELECT `property_location_key`
                 FROM(
                    SELECT id
                        FROM PropertyLocationFeature plf
                        WHERE plf.Type='BIKEABILITY') AS C
           JOIN PropertyLocationFeatureMapping plfm
           ON C.id = plfm.feature_id) AS D
           INNER JOIN
           PropertyLocation pl ON D.property_location_key = pl.PropertyLocationKey ) AS E  ON `Property`.`PropertyLocationFK` = `E`.`PropertyLocationKey`) AS F
    ON B.`property_id` = F.`PropertyKey` ;
    

# 4. Which properties have the most total amount of bathroom and bedroom, display their name.

SELECT `PropertyName`, `a`.`max_data` as `Amount`
From `Property` `p` JOIN
(SELECT `property_id`, `max_data`
FROM (SELECT `property_id`, SUM(`Count`) as `sum`
FROM `FeaturePropertyMapping` `fpm` JOIN `FeatureProperty` `fp` on `fpm`.`feature_id` = `fp`.`FeatureKey`
GROUP BY `property_id` ) as `sumtable`
JOIN ( SELECT SUM(`Count`) as `max_cnt`
FROM `FeaturePropertyMapping` `fpm` JOIN `FeatureProperty` `fp` on `fpm`.`feature_id` = `fp`.`FeatureKey`
GROUP BY `property_id`
ORDER BY SUM(`Count`) desc
LIMIT 1 ) AS `maxtable` ON `sumtable`.`sum` = `maxtable`.`max_cnt`) as `a` ON `p`.`PropertyKey` = `a`.`property_id`;


# 5. Properties with the top three average rating among all active ones, display all their information.

SELECT *
FROM (SELECT * FROM `Property` WHERE `Status` = 'ACTIVE') as `active_properties` JOIN
(SELECT `Property_id` FROM (SELECT `Property_id`, AVG(`rating`) AS `avg`
FROM `Review`
GROUP BY `Property_id`) AS `a` JOIN 
(SELECT  DISTINCT AVG(`rating`) AS `the_third_high`
FROM `Review` 
GROUP BY `Property_id`
ORDER BY AVG(`rating`) desc
LIMIT 3 OFFSET 2) AS `b` ON `a`.`avg` >= `b`.`the_third_high` ) AS `c` ON `active_properties`.`PropertyKey` = `c`.`Property_id`;


# 6. which properties have been booked the most time during the last 1 year. (The most popular ones);

SELECT *
FROM `Property` JOIN
(SELECT `a`.`property_id` FROM (SELECT `property_id`, COUNT(`property_id`) AS `cnt`
FROM `Booking`
WHERE `start_data` > DATEADD(year,-1,GETDATE())
GROUP BY `property_id`) AS `a` JOIN
(SELECT `property_id`, COUNT(`property_id`) AS `max_cnt`
FROM `Booking`
WHERE `start_data` > DATEADD(year,-1,GETDATE())
GROUP BY `property_id` 
ORDER BY COUNT(`property_id`) desc
LIMIT 1) AS `b` ON `a`.`cnt` = `b`.`max_cnt`) AS `c` ON `Property`.`PropertyKey` = `c`.`property_id`; 


# 7. which properties' average booking prices are greater than the average local price.

SELECT *
FROM `Property` JOIN
(SELECT * FROM (SELECT `property_id`, AVG(`price`) AS `avg`
FROM `Booking` 
GROUP BY `property_id`) AS `a` JOIN
(SELECT AVG(`price`) AS `total_avg`
FROM `Booking`)  AS `b` on `a`.`avg` > `b`.`total_avg`) AS `b` ON  `Property`.`PropertyKey` = `b`.`property_id`;


# 8. User will like to see properties with at least 1 image.

SELECT * 
FROM `Property` 
WHERE `PropertyKey` IN (SELECT DISTINCT `PropertyFK` FROM `PropertyImage` ) ;


# 9.  User wants to know the hosts which are added recently in the system (in this year)

SELECT * 
FROM `User` 
WHERE `id` IN
(SELECT `user_id`
FROM `Host`
WHERE `host_since` > '2020-01-01');


# 10 Booking prices lower than the average price
SELECT *
FROM Property 
JOIN (SELECT * FROM (SELECT property_id, AVG(price) AS average FROM Booking GROUP BY property_id) AS a 
JOIN (SELECT AVG(price) AS totAverage FROM Booking)  AS b ON a.average < b.totAverage) AS b 
ON  Property.PropertyKey = b.property_id;

# More things to consider, per diem, why above was selected even though similar to question 7