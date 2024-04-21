package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Extract Top Countries Population
        ArrayList<Country> topPopulation = a.getTopNCountryPopulation(10);

        //Display Results
        a.printTopNCountryPopulation(topPopulation);

        // Extract Top N Countries in a Continent
        ArrayList<Country> topNCountriesContPop = a.getTopNCountriesInContPopulation("North America",3);

        //Display Results
        a.printTopNCountriesInContPopulation(topNCountriesContPop);

        // Extract Top N Countries in a Region
        ArrayList<Country> topNCountriesRegPop = a.getTopNCountriesInRegPopulation("North America", 3);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "sem4");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets top N populated countries in the world where N is provided by the user.
     *
     * @return A list of all top N populated countries in the world where N is provided by the user, or null if there is an error.
     */
    public ArrayList<Country> getTopNCountryPopulation(int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT name, continent, code, capital, Region, population "
                    + "FROM country "
                    + "Order By population DESC "
                    + "Limit " + limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> population = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
                pop.code = rset.getString("country.Code");
                pop.capital = rset.getInt("country.Capital");
                pop.name = rset.getString("country.Name");
                pop.continent = rset.getString("country.continent");
                pop.region = rset.getString("country.region");
                population.add(pop);
            }
            return population;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Top N Populations countries in the world.
     *
     * @param topPopulation The list of Population to print.
     */
    public void printTopNCountryPopulation(ArrayList<Country> topPopulation) {

        // Check countries is not null
        if (topPopulation == null)
        {
            System.out.println("No Countries");
            return;
        }


        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Top N countries in the world with N provided by user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-30s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : topPopulation) {

            if (pop == null)
                continue;

            String popCount = String.format("%-10s %10s %-50s %-30s %-30s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }
    }



    /**
     * Gets the population of Top N countries, value provided by user.
     *
     * @return A list of Top N Countries Population in a Continent, or null if there is an error.
     */
    public ArrayList<Country> getTopNCountriesInContPopulation(String cont1, int Limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "with country as (select name, code, capital, region, continent, population, row_number() over " +
                            "(partition by continent order by population desc, continent desc) as row_num from country) " +
                            "select row_num, name, code, capital, region, continent, population from country where continent = '"+ cont1 +"' Limit " + Limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> topNCountriesContPop = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
                pop.code = rset.getString("country.Code");
                pop.capital = rset.getInt("country.Capital");
                pop.name = rset.getString("country.Name");
                pop.continent = rset.getString("country.continent");
                pop.region = rset.getString("country.region");
                topNCountriesContPop.add(pop);
            }
            return topNCountriesContPop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Top N Countries Populations in a Continent.
     *
     * @param topNCountriesContPop The list of Top N Countries in a Continent to print.
     */
    public void printTopNCountriesInContPopulation(ArrayList<Country> topNCountriesContPop) {

        // Check countries is not null
        if (topNCountriesContPop == null)
        {
            System.out.println("No Countries");
            return;
        }


        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the TOP N countries in a Continent with N value provided by user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-30s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : topNCountriesContPop) {

            if (pop == null)
                continue;

            String popCount = String.format("%-10s %10s %-50s %-30s %-30s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }

    }


    /**
     * Gets the all the countries in a Region.
     *
     * @return A list of Top N Countries Population in a Region with value provide by user, or null if there is an error.
     */
    public ArrayList<Country> getTopNCountriesInRegPopulation(String reg1, int Limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "with country as (select name, code, capital, region, continent, population, row_number() over " +
                            "(partition by region order by population desc, name desc) as row_num from country) " +
                            "select row_num, name, code, capital, region, continent, population from country where region = '"+ reg1 +"' Limit " + Limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> topNCountriesRegPop = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
                pop.code = rset.getString("country.Code");
                pop.capital = rset.getInt("country.Capital");
                pop.name = rset.getString("country.Name");
                pop.continent = rset.getString("country.continent");
                pop.region = rset.getString("country.region");
                topNCountriesRegPop.add(pop);
            }
            return topNCountriesRegPop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Top N Countries Populations in a Region.
     *
     * @param topNCountriesRegPop The list of Top N Countries in a Region to print.
     */
    public void printTopNCountriesInRegPopulation(ArrayList<Country> topNCountriesRegPop) {

        // Check countries is not null
        if (topNCountriesRegPop == null)
        {
            System.out.println("No Countries");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the TOP N countries in a Region with N value provided by user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-20s %-25s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : topNCountriesRegPop) {

            if (pop == null)
                continue;

            String popCount = String.format("%-10s %10s %-50s %-20s %-25s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }
    }

}