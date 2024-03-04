package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Extract Country Population
        ArrayList<Country> population = a.getCountryPopulation();

        //Display Results
        a.printCountryPopulation(population);

        // Extract City Population
        ArrayList<City> cityPop = a.getCityPop();

        //Display Results
        a.printCityPop(cityPop);

        // Extract city population information
        ArrayList<City> citypop10 = a.getCityPopulation10();

        //Display Results
        a.printCityPopulation10(citypop10);

        // Extract Top Countries Population
        ArrayList<Country> topPopulation = a.getTopNCountryPopulation();

        //Display Results
        a.printTopNCountryPopulation(topPopulation);

        // Extract Top City Population in a Continent
        ArrayList<City> nCityPop = a.getTopCityPopulation();

        //Display Results
        a.printTopCityPopulation(nCityPop);

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
     * Gets the population of all countries.
     *
     * @return A list of all Population sorted in descending order, or null if there is an error.
     */
    public ArrayList<Country> getCountryPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT name, continent, Region, population "
                            + "FROM country "
                            + "Order By population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> population = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
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
     * Prints a list of Populations.
     *
     * @param population The list of Population to print.
     */
    public void printCountryPopulation(ArrayList<Country> population) {
        // Print header
        System.out.println(String.format("%-20s ", "All the countries in the world organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-30s %10s", "Country", "Continent", "Region", "Population"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : population) {

            String popCount = String.format("%-20s %-20s %-30s %10s", pop.name, pop.continent, pop.region, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Gets the All Cities population in the world .
     *
     * @return A list of all cities in the world Population sorted in descending order, or null if there is an error.
     */
    public ArrayList<City> getCityPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT name, countryCode, district, population "
                            + "FROM city "
                            + "Order By population DESC "
                            + "Limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> cityPop = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("city.population");
                pop.name = rset.getString("city.Name");
                pop.district = rset.getString("city.district");
                pop.countryCode = rset.getString("city.countryCode");
                cityPop.add(pop);
            }
            return cityPop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the Cities in the World.
     *
     * @param CityPop The list of All Cities in the world Population to print.
     */
    public void printCityPop(ArrayList<City> CityPop) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Cities in the world organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-30s %10s", "Name", "Country Code", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : CityPop) {

            String popCount = String.format("%-20s %-20s %-30s %10s", pop.name, pop.countryCode, pop.district, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Gets All the Cities in a Country.
     */
    public ArrayList<City> getCityPopulation10() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name AS city, city.population, country.name AS country " +
                            "FROM city " +
                            "INNER JOIN country ON city.countrycode = country.code " +
                            "ORDER BY country ASC, population DESC ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population = new ArrayList<>();
            while (rset.next()) {
                City citypop10 = new City();
                citypop10.population = rset.getInt("city.population");
                citypop10.name = rset.getString("city");
                citypop10.country = rset.getString("country");

                population.add(citypop10);
            }
            return population;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of all the Cities in a country.
     */

    public void printCityPopulation10(ArrayList<City> citypop10) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("Cities in a country from largest to smallest population"));
        System.out.println(String.format("%-20s %-20s %-30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : citypop10) {
            String popCount = String.format("%-20s %-20s %-30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Gets the population of Top N of all countries in the world.
     *
     * @return A list of all Top Populated countries, or null if there is an error.
     */
    public ArrayList<Country> getTopNCountryPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT name, continent, Region, population "
                    + "FROM country "
                    + "Order By population DESC "
                    + "Limit 10";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> population = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
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
     * Prints a list of Top N Populations.
     *
     * @param topPopulation The list of Population to print.
     */
    public void printTopNCountryPopulation(ArrayList<Country> topPopulation) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Top N countries in the world."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-30s %10s", "Country", "Continent", "Region", "Population"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : topPopulation) {

            String popCount = String.format("%-20s %-20s %-30s %10s", pop.name, pop.continent, pop.region, pop.population);
            System.out.println(popCount);
        }
    }

    public ArrayList<City> getTopCityPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "WITH city1 as (select city.name as name, country.continent as continent, city.population as population, RANK () " +
                            "OVER(PARTITION BY continent ORDER BY population DESC) row_num " +
                            "FROM city inner join country on city.countrycode = country.code) " +
                            "SELECT * FROM city1  WHERE row_num <=3";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> nCityPop = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("Name");
                pop.continent = rset.getString("continent");
                pop.row_num = rset.getInt("row_num");
                nCityPop.add(pop);
            }
            return nCityPop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Populations.
     *
     * @param nCityPop The list of Population to print.
     */
    public void printTopCityPopulation(ArrayList<City> nCityPop) {
        // Print header
        System.out.println(String.format("%-20s ", "The top N populated cities in a continent where N is provided by the user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%10s %-30s %-30s %10s", "row_num", "Country", "Continent", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : nCityPop) {

            String popCount = String.format("%10s %-30s %-30s %10s", pop.row_num, pop.name, pop.continent, pop.population);
            System.out.println(popCount);
        }
    }
}