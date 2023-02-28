use world;
-- 1.
select count(Language) from countrylanguage where language='english';
-- 2.
select countrylanguage.Language
from country, countrylanguage
where country.code=countrylanguage.CountryCode and country.name='South Korea';
-- 3.
select country.continent, country.name
from countrylanguage, country
where country.code=countrylanguage.CountryCode 
and countrylanguage.language='english' and countrylanguage.IsOfficial='F';
-- 4.
select country.continent, count(country.name)
from countrylanguage, country
where country.code=countrylanguage.CountryCode
and countrylanguage.language='english' 
group by country.continent;