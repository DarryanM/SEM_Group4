package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;




public class AppTest {

    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }


    // Unit Testing for All the countries in the world population
    @Test
    void printCountryPopulationTestNull()
    {
        app.printCountryPopulation(null);
    }

    @Test
    void printCountryPopulationTestContainsNull()
    {
        ArrayList<Country> population = new ArrayList<Country>();
        population.add(null);
        app.printCountryPopulation(population);
    }

    @Test
    void printCountryPopulationTestEmpty()
    {
        ArrayList<Country> population = new ArrayList<Country>();
        app.printCountryPopulation(population);
    }
    @Test
    void printCountryPopulation()
    {
        ArrayList<Country> population = new ArrayList<Country>();
        Country pop = new Country();
        pop.code = "ABW";
        pop.population = 103000;
        pop.name = "Aruba";
        pop.capital = 129;
        pop.continent = "North America";
        pop.region = "Caribbean";
        population.add(pop);
        app.printCountryPopulation(population);
    }

    // Unit Testing for All the Countries in the Continent Population
    @Test
    void printContinentPopulationTestNull()
    {
        app.printContinentPopulation(null);
    }

    @Test
    void printContinentPopulationTestContainsNull()
    {
        ArrayList<Country> population2 = new ArrayList<Country>();
        population2.add(null);
        app.printContinentPopulation(population2);
    }

    @Test
    void printContinentPopulationTestEmpty()
    {
        ArrayList<Country> population2 = new ArrayList<Country>();
        app.printContinentPopulation(population2);
    }

    @Test
    void printContinentPopulation()
    {
        ArrayList<Country> population2 = new ArrayList<Country>();
        Country pop = new Country();
        pop.code = "ABW";
        pop.population = 103000;
        pop.name = "Aruba";
        pop.capital = 129;
        pop.continent = "North America";
        pop.region = "Caribbean";
        population2.add(pop);
        app.printContinentPopulation(population2);
    }


    // Unit Testing for All the countries in the Region population
    @Test
    void printRegionPopulationTestNull()
    {
        app.printRegionPopulation(null);
    }

    @Test
    void printRegionPopulationTestContainsNull()
    {
        ArrayList<Country> population3 = new ArrayList<Country>();
        population3.add(null);
        app.printRegionPopulation(population3);
    }

    @Test
    void printRegionPopulationTestEmpty()
    {
        ArrayList<Country> population3 = new ArrayList<Country>();
        app.printRegionPopulation(population3);
    }

    @Test
    void printRegionPopulation()
    {
        ArrayList<Country> population3 = new ArrayList<Country>();
        Country pop = new Country();
        pop.code = "ABW";
        pop.population = 103000;
        pop.name = "Aruba";
        pop.capital = 129;
        pop.continent = "North America";
        pop.region = "Caribbean";
        population3.add(pop);
        app.printRegionPopulation(population3);
    }



    //Unit Testing for Top N Populated Countries in the World where N was provided
    @Test
    void printTopNCountryPopulationTestNull()
    {
        app.printTopNCountryPopulation(null);
    }
    @Test
    void printTopNCountryPopulationTestContainsNull()
    {
        ArrayList<Country> topPopulation = new ArrayList<Country>();
        topPopulation.add(null);
        app.printTopNCountryPopulation(topPopulation);
    }
    @Test
    void printTopNCountryPopulationTestEmpty()
    {
        ArrayList<Country> topPopulation = new ArrayList<Country>();
        app.printTopNCountryPopulation(topPopulation);
    }

    @Test
    void printTopNCountryPopulation()
    {
        ArrayList<Country> topPopulation = new ArrayList<Country>();
        Country pop = new Country();
        pop.population = 111506000;
        pop.code = "NGA";
        pop.capital = 2754;
        pop.name = "Nigeria";
        pop.continent = "Africa";
        pop.region = "Western Africa ";
        topPopulation.add(pop);
        app.printTopNCountryPopulation(topPopulation);
    }

    //Unit Testing for Top N Populated Countries in a Continent where N was provided
    @Test
    void printTopNCountriesInContPopulationTestNull()
    {
        app.printTopNCountriesInContPopulation(null);
    }
    @Test
    void printTopNCountriesInContPopulationTestContainsNull()
    {
        ArrayList<Country> topNCountriesContPop = new ArrayList<Country>();
        topNCountriesContPop.add(null);
        app.printTopNCountriesInContPopulation(topNCountriesContPop);
    }
    @Test
    void printTopNCountriesInContPopulationTestEmpty()
    {
        ArrayList<Country> topNCountriesContPop = new ArrayList<Country>();
        app.printTopNCountriesInContPopulation(topNCountriesContPop);
    }

    @Test
    void printTopNCountriesInContPopulation()
    {
        ArrayList<Country> topNCountriesContPop = new ArrayList<Country>();
        Country pop = new Country();
        pop.row_num = 0;
        pop.code = "NGA";
        pop.population = 111506000;
        pop.name = "Nigeria";
        pop.capital = 2754;
        pop.continent = "Africa";
        pop.region = "Western Africa ";
        topNCountriesContPop.add(pop);
        app.printTopNCountriesInContPopulation(topNCountriesContPop);
    }

    //Unit Testing for Top N Populated Countries in a Region where N was provided
    @Test
    void printTopNCountriesInRegPopulationTestNull()
    {
        app.printTopNCountriesInRegPopulation(null);
    }
    @Test
    void printTopNCountriesInRegPopulationTestContainsNull()
    {
        ArrayList<Country> topNCountriesRegPop = new ArrayList<Country>();
        topNCountriesRegPop.add(null);
        app.printTopNCountriesInRegPopulation(topNCountriesRegPop);
    }
    @Test
    void printTopNCountriesInRegPopulationTestEmpty()
    {
        ArrayList<Country> topNCountriesRegPop = new ArrayList<Country>();
        app.printTopNCountriesInRegPopulation(topNCountriesRegPop);
    }

    @Test
    void printTopNCountriesInRegPopulation()
    {
        ArrayList<Country> topNCountriesRegPop = new ArrayList<Country>();
        Country pop = new Country();
        pop.row_num = 0;
        pop.code = "NGA";
        pop.population = 111506000;
        pop.name = "Nigeria";
        pop.capital = 2754;
        pop.continent = "Africa";
        pop.region = "Western Africa ";
        topNCountriesRegPop.add(pop);
        app.printTopNCountriesInRegPopulation(topNCountriesRegPop);
    }

    //Unit Tests for Report 10 - All the cities in a country organised by largest population to smallest.
    @Test
    void printCityPopulation10TestNull()
    {
        app.printCountryPopulation(null);
    }

    @Test
    void printCityPopulation10TestContainsNull()
    {
        ArrayList<Country> population = new ArrayList<Country>();
        population.add(null);
        app.printCountryPopulation(population);
    }

    @Test
    void printCityPopulation10TestEmpty()
    {
        ArrayList<Country> population = new ArrayList<Country>();
        app.printCountryPopulation(population);
    }

    @Test
    void printCityPopulation10()
    {
        ArrayList<City> population = new ArrayList<>();
        City citypop10 = new City();
        citypop10.population = 10000;
        citypop10.name = "city";
        citypop10.country = "country";
        citypop10.district = "district";
        population.add(citypop10);
        app.printCityPopulation10(population);
    }

    //Unit tests for Report 11 - All the cities in a district organised by largest population to smallest.
    @Test
    void printDistrictPopulationTestNull()
    {
        app.printDistrictPopulation(null);
    }

    @Test
    void printDistrictPopulationTestContainsNull()
    {
        ArrayList<City> districtpopulation11 = new ArrayList<>();
        districtpopulation11.add(null);
        app.printDistrictPopulation(districtpopulation11);
    }

    @Test
    void printDistrictPopulationTestEmpty()
    {
        ArrayList<City> districtpopulation11 = new ArrayList<>();
        app.printDistrictPopulation(districtpopulation11);
    }

    @Test
    void printDistrictPopulation()
    {
        ArrayList<City> districtpopulation11 = new ArrayList<>();
        City district = new City();
        district.population = 10000;
        district.name = "city";
        district.country = "country";
        district.district = "district";
        districtpopulation11.add(district);
        app.printDistrictPopulation(districtpopulation11);
    }

    //Apptests for Report 12 - The top N populated cities in the world where N is provided by the user.
    @Test
    void printGetTopNCityPopWorld12TestNull()
    {
        app.printGetTopNCityPopWorld12(null);
    }

    @Test
    void printGetTopNCityPopWorld12TestContainsNull()
    {
        ArrayList<City> topNCityPopWorld12 = new ArrayList<City>();
        app.printGetTopNCityPopWorld12(topNCityPopWorld12);
    }

    @Test
    void printGetTopNCityPopWorld12TestEmpty()
    {
        ArrayList<City> topNCityPopWorld12 = new ArrayList<City>();
        app.printGetTopNCityPopWorld12(topNCityPopWorld12);
    }

    @Test
    void printGetTopNCityPopWorld12()
    {
        ArrayList<City> topNCityPopWorld12 = new ArrayList<City>();
        City pop = new City();
        pop.population = 10000;
        pop.name = "city";
        pop.country = "country";
        pop.row_num = 40;
        pop.district = "district";
        topNCityPopWorld12.add(pop);
        app.printGetTopNCityPopWorld12(topNCityPopWorld12);
    }



}
