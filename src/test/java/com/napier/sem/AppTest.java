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

}
