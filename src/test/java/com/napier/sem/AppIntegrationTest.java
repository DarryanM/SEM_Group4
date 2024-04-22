package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33070", 30000);

    }
    @Test
    void testGetCountry()
    {
        Country pop = app.getCountry( "ABW");
        assertEquals(pop.code, "ABW");
        assertEquals(pop.population, 103000);
        assertEquals(pop.name, "Aruba");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "Caribbean");
    }
    @Test
    void testGetCity()
    {
        City pop = app.getCity( "KOR");
        assertEquals(pop.population, 9981619);
        assertEquals(pop.countryCode,"KOR");
        assertEquals(pop.name, "Seoul");
        assertEquals(pop.district, "Seoul");
        assertEquals(pop.country, "South Korea");
    }

    @Test
    void testGetTopNCountryPopulation()
    {
        //Testing Query TopNCountryPopulation with variables
        ArrayList<Country> population = app.getTopNCountryPopulation(3);

        Country pop = app.getCountry("USA");
        assertEquals(pop.population, 278357000);
        assertEquals(pop.code,"USA");
        assertEquals(pop.capital,3813);
        assertEquals(pop.name, "United States");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "North America");
        app.getTopNCountryPopulation(10);

        //Testing if SQL syntax incorrect Catch will run
        app.getTopNCountryPopulation(-1);
    }

    @Test
    void testGetTopNCountriesInContPopulation()
    {
        //Testing Query TopNCountriesInContPopulation with variables
        ArrayList<Country> topNCountriesContPop = app.getTopNCountriesInContPopulation("North America", 3);

        Country pop = app.getCountry("USA");
        assertEquals(pop.population, 278357000);
        assertEquals(pop.code,"USA");
        assertEquals(pop.capital,3813);
        assertEquals(pop.name, "United States");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "North America");
        app.getTopNCountriesInContPopulation("North America", 3);

        //Testing if SQL syntax incorrect Catch will run
        app.getTopNCountriesInContPopulation("XYZ",-10);
    }

    @Test
    void testGetTopNCountriesInRegPopulation()
    {
        //Testing Query TopNCountriesInRegPopulation with variables
        ArrayList<Country> topNCountriesRegPop = app.getTopNCountriesInRegPopulation("Seoul", 3);

        Country pop = app.getCountry("USA");
        assertEquals(pop.population, 278357000);
        assertEquals(pop.code,"USA");
        assertEquals(pop.capital,3813);
        assertEquals(pop.name, "United States");
        assertEquals(pop.continent, "North America");
        assertEquals(pop.region, "North America");
        app.getTopNCountriesInRegPopulation("North America", 3);

        //Testing if SQL syntax incorrect Catch will run
        app.getTopNCountriesInRegPopulation("XYZ",-10);
    }

    //Put Integration Tests above HERE

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


}