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

    //Put Integration Tests above HERE
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