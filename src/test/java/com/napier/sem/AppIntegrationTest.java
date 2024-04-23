package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33070", 30000);

    }

    @Test
    void testGetCountry() {
        Country pop = app.getCountry("ABW");
        assertEquals(pop.code, "ABW");
        assertEquals(pop.population, 103000);
        assertEquals(pop.name, "Aruba");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "Caribbean");
    }

    @Test
    void testGetCity() {
        City pop = app.getCity("KOR");
        assertEquals(pop.population, 9981619);
        assertEquals(pop.countryCode, "KOR");
        assertEquals(pop.name, "Seoul");
        assertEquals(pop.district, "Seoul");
        assertEquals(pop.country, "South Korea");
    }

    // Testing for All the countries in the world organised by largest population
    @Test
    void testGetCountryPopulation() {
        ArrayList<Country> population = app.getCountryPopulation();

        Country pop = app.getCountry("ABW");
        assertEquals(pop.code, "ABW");
        assertEquals(pop.population, 103000);
        assertEquals(pop.name, "Aruba");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "Caribbean");
        app.getCountryPopulation();
    }

    //Testing for All the countries in a continent organised by largest population
    @Test
    void testGetContinentPopulation() {
        ArrayList<Country> population2 = app.getContinentPopulation("North America");

        Country pop = app.getCountry("ABW");
        assertEquals(pop.code, "ABW");
        assertEquals(pop.population, 103000);
        assertEquals(pop.name, "Aruba");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "Caribbean");
        app.getContinentPopulation("North America");
    }

    //Testing for All the countries in a Region organised by largest population
    @Test
    void testGetRegionPopulation() {
        ArrayList<Country> population3 = app.getRegionPopulation("Caribbean");

        Country pop = app.getCountry("ABW");
        assertEquals(pop.code, "ABW");
        assertEquals(pop.population, 103000);
        assertEquals(pop.name, "Aruba");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "Caribbean");
        app.getRegionPopulation("Caribbean");
    }


    @Test
    void testGetTopNCountryPopulation() {
        //Testing Query TopNCountryPopulation with variables
        ArrayList<Country> population = app.getTopNCountryPopulation(3);

        Country pop = app.getCountry("USA");
        assertEquals(pop.population, 278357000);
        assertEquals(pop.code, "USA");
        assertEquals(pop.capital, 3813);
        assertEquals(pop.name, "United States");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "North America");
        app.getTopNCountryPopulation(10);
    }

    //Test For Report 4 Failure
    @Test
    void testGetTopNCountryPopulationFail()
    {
        //Testing if SQL syntax incorrect Catch will run
        app.getTopNCountryPopulation(-1);
    }

    @Test
    void testGetTopNCountriesInContPopulation() {
        //Testing Query TopNCountriesInContPopulation with variables
        ArrayList<Country> topNCountriesContPop = app.getTopNCountriesInContPopulation("North America", 3);

        Country pop = app.getCountry("USA");
        assertEquals(pop.population, 278357000);
        assertEquals(pop.code, "USA");
        assertEquals(pop.capital, 3813);
        assertEquals(pop.name, "United States");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "North America");
        app.getTopNCountriesInContPopulation("North America", 3);
    }

    //Test For Report 5 Failure
    @Test
    void testGetTopNCountriesInContPopulationFail()
    {
        //Testing if SQL syntax incorrect Catch will run
        app.getTopNCountriesInContPopulation("XYZ", -10);
    }

    @Test
    void testGetTopNCountriesInRegPopulation() {
        //Testing Query TopNCountriesInRegPopulation with variables
        ArrayList<Country> topNCountriesRegPop = app.getTopNCountriesInRegPopulation("Seoul", 3);

        Country pop = app.getCountry("USA");
        assertEquals(pop.population, 278357000);
        assertEquals(pop.code, "USA");
        assertEquals(pop.capital, 3813);
        assertEquals(pop.name, "United States");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "North America");
        app.getTopNCountriesInRegPopulation("North America", 3);

    }

    //Test For Report 6 Failure
    @Test
    void testGetTopNCountriesInRegPopulationFail()
    {

        //Testing if SQL syntax incorrect Catch will run
        app.getTopNCountriesInRegPopulation("XYZ", -10);

    }

    //Integration test for Report 7 - All the cities in the world organised by largest population to smallest.
    @Test
    void testGetCityPop7() {
        ArrayList<City> cityPop7 = app.getCityPop();

        City pop = app.getCity("KOR");
        assertEquals(pop.population, 9981619);
        assertEquals(pop.countryCode, "KOR");
        assertEquals(pop.name, "Seoul");
        assertEquals(pop.district, "Seoul");
        assertEquals(pop.country, "South Korea");
        app.getCityPop();
    }

    //Integration test for Report 8 - All the cities in a continent organised by largest population to smallest.
    @Test
    void testGetcityConti() {
        ArrayList<City> cityPop8 = app.getCityPopconti("Asia", 999999);

        City pop = app.getCity("KOR");
        assertEquals(pop.population, 9981619);
        assertEquals(pop.countryCode, "KOR");
        assertEquals(pop.name, "Seoul");
        assertEquals(pop.district, "Seoul");
        assertEquals(pop.country, "South Korea");

        app.getCityPopconti("Asia", 999999);
    }
    ////Test for all cities in a Continent for the catch
    @Test
    void testGetcityContiFail() {
        app.getCityPopconti("xyz", -10);
    }

    //Integration test for Report 9 - All the cities in a continent organised by largest population to smallest.
    @Test
    void testGetcityPop9() {
        ArrayList<City> cityPop9 = app.getCityPopregi("Eastern Asia", 999999);

        City pop = app.getCity("KOR");
        assertEquals(pop.population, 9981619);
        assertEquals(pop.countryCode, "KOR");
        assertEquals(pop.name, "Seoul");
        assertEquals(pop.district, "Seoul");
        assertEquals(pop.country, "South Korea");

        app.getCityPopregi("Eastern Asia", 999999);
    }
        //Test for all cities in a region for the catch
    @Test
    void testGetcityregionFail() {
        app.getCityPopregi("xyz", -10);
    }



    //Integration test for Report 10 - All the cities in a country organised by largest population to smallest.
    @Test
    void testCityPopulation10() {
        app.getCityPopulation10("xyz", -10);
        ArrayList<City> population10 = app.getCityPopulation10("China", 5);

        City citypop10 = app.getCity("Chn");
        assertEquals(citypop10.population, 9696300);
        assertEquals(citypop10.name, "Shanghai");
        assertEquals(citypop10.country, "China");
        assertEquals(citypop10.district, "Shanghai");

        app.getCityPopulation10("Istanbul", 3);
    }

    //Integration test for Report 11 - All the cities in a district organised by largest population to smallest.
    @Test
    void testGetDistrictPopulation() {
        app.getDistrictPopulation("xyz", -10);
        ArrayList<City> districtPopulation11 = app.getDistrictPopulation("Istanbul", 5);

        City district = app.getCity("TUR");
        assertEquals(district.population, 8787958);
        assertEquals(district.name, "Istanbul");
        assertEquals(district.country, "Turkey");
        assertEquals(district.district, "Istanbul");

        app.getDistrictPopulation("Istanbul", 3);
    }

    //Integration test for Report 12 - The top N populated cities in the world where N is provided by the user.
    @Test
    void getTopNCityPopWorld12()
    {
        app.getTopNCityPopWorld12(-10);
        ArrayList<City> topNCityPopWorld12 = app.getTopNCityPopWorld12(5);
        City pop = app.getCity("Ind");
        assertEquals(pop.population, 10500000);
        assertEquals(pop.name, "Mumbai (Bombay)");
        assertEquals(pop.country, "India");
        assertEquals(pop.district, "Maharashtra");

        app.getTopNCityPopWorld12(5);
    }

    //Integration test Report 13 - The top N populated cities in a continent where N is provided by the user.
    @Test
    void testgetTopCityInContinent()
    {
        app.getTopCityInContinent("xyz",-10);
        ArrayList<City> nCityTopCont = app.getTopCityInContinent("Asia",3);

        City pop = app.getCity("KOR");
        assertEquals(pop.population,9981619);
        assertEquals(pop.name,"Seoul");
        assertEquals(pop.country, "South Korea");
        assertEquals(pop.district,"Seoul");
        app.getTopCityInContinent("Asia", 3);
    }
    @Test
    void testgetTopCityInRegion()
    {
        app.getTopCityInRegion("xyz",-10);
        ArrayList<City> nCityTopReg = app.getTopCityInRegion("Eastern Asia",3);

        City pop = app.getCity("KOR");
        assertEquals(pop.population,9981619);
        assertEquals(pop.name,"Seoul");
        assertEquals(pop.country, "South Korea");
        assertEquals(pop.district,"Seoul");
        app.getTopCityInRegion("Eastern Asia", 3);
    }

    @Test
    void testgetTopCityInCountry()
    {
        app.getTopCityInCountry("xyz",-10);
        ArrayList<City> nCityTopCtry = app.getTopCityInCountry("South Korea",3);

        City pop = app.getCity("KOR");
        assertEquals(pop.population,9981619);
        assertEquals(pop.name,"Seoul");
        assertEquals(pop.country, "South Korea");
        assertEquals(pop.district,"Seoul");
        app.getTopCityInCountry("South Korea", 3);
    }

    // Test Report 16 the top N populated cities in a district where N is provided by the user.
    @Test
    void testgetTopNPopCitiesDistrict()
    {
        ArrayList<City> population19 = app.getTopNPopCitiesDistrict("South Korea",3);

        City pop = app.getDistrict("Seoul");
        assertEquals(pop.population,9981619);
        assertEquals(pop.name,"Seoul");
        assertEquals(pop.country, "South Korea");
        assertEquals(pop.district,"Seoul");
        app.getTopNPopCitiesDistrict("Seoul", 3);
    }

    //Test For Report 16 Failure
    @Test
    void testgetTopNPopCitiesDistrictFail()
    {
        //Testing if SQL syntax incorrect Catch will run
        app.getTopNPopCitiesDistrict("xyz",-2);
    }

    // Test Report 17 all the capital cities in the world organised by largest population to smallest
    @Test
    void testGetAllCapCitiesWorld()
    {
        ArrayList<City> population20 = app.getAllCapCitiesWorld(99999);

        City pop = app.getCapitalCity("CHN");
        assertEquals(pop.population,7472000);
        assertEquals(pop.name,"Peking");
        assertEquals(pop.district,"Peking");
        app.getAllCapCitiesWorld(99999);
    }

    //Test For Report 17 Failure
    @Test
    void testGetAllCapCitiesWorldFail()
    {
        //Testing if SQL syntax incorrect Catch will run
        app.getAllCapCitiesWorld(-2);
    }



    // Integration Test for all capital cities in a continent
    @Test
    void testGetAllCapContinent()
    {
        app.getAllCapCitiesContinent("Asia", 999999);
        ArrayList<City> population21 = app.getAllCapCitiesContinent("Asia",999999);

        City pop = app.getCapitalCity("CHN");
        assertEquals(pop.population,7472000);
        assertEquals(pop.name,"Peking");
        assertEquals(pop.district,"Peking");
        app.getAllCapCitiesContinent("Asia", 999999);
    }

    // Test for catch in all capital cities in a continent
    @Test
    void testGetcapitalcontiFail() {
        app.getAllCapCitiesContinent("xyz", -10);
    }


    // Integration Test for all capital cities in a region
    @Test
    void testGetAllCapRegion()
    {
        app.getAllCapCitiesRegion("Eastern Asia", 999999);
        ArrayList<City> population22 = app.getAllCapCitiesRegion("Eastern Asia",999999);

        City pop = app.getCapitalCity("CHN");
        assertEquals(pop.population,7472000);
        assertEquals(pop.name,"Peking");
        assertEquals(pop.district,"Peking");
        app.getAllCapCitiesRegion("Eastern Asia",999999);
    }

    // Test for catch in all capital cities in a Region
    @Test
    void testGetcapitalRegionFail() {
        app.getAllCapCitiesRegion("xyz", -10);
    }



    // Test Report -20 The top N populated Capital Cities in the World where N is provided
    @Test
    void testGetTopNPopCapCitiesWorld()
    {

        ArrayList<City> population23 = app.getTopNPopCapCitiesWorld(5);

        City pop = app.getCapitalCity("CHN");
        assertEquals(pop.population,7472000);
        assertEquals(pop.name,"Peking");
        assertEquals(pop.country,"China");
        app.getTopNPopCapCitiesWorld(5);
    }

    //Test For Report 21 Failure
    @Test
    void testGetTopNPopCapCitiesWorldFail()
    {
        app.getTopNPopCapCitiesWorld(-5);
    }

    // Test Report -21 The top N populated Capital Cities in the World where N is provided
    @Test
    void testGetTopNPopCapCitiesContinent()
    {

        ArrayList<City> population24 = app.getTopNPopCapCitiesContinent("Asia",5);

        City pop = app.getCapitalCity("CHN");
        assertEquals(pop.population,7472000);
        assertEquals(pop.name,"Peking");
        assertEquals(pop.country,"China");
        app.getTopNPopCapCitiesContinent("Asia",5);
    }

    //Test For Report 21 Failure
    @Test
    void testGetTopNPopCapCitiesWorldContinent()
    {
        app.getTopNPopCapCitiesContinent("XYZ", -5);
    }

}