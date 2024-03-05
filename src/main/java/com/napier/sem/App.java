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

        // Extract City Population in the world
        ArrayList<City> cityPop7 = a.getCityPop();

        //Display Results for City population in the world
        a.printCityPop(cityPop7);

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

        // Extract Continent Population
        ArrayList<Country> population2 = a.getContinentPopulation();

        //Display Continent Population Results
        a.printContinentPopulation(population2);

        // Extract City Population in a continent
        ArrayList<City> cityPop8 = a.getCityPopconti();

        //Display Results for city population in a continent
        a.printCityPop8(cityPop8);

        // Extract Top N Countries in a Continent
        ArrayList<Country> topNCountriesContPop = a.getTopNCountriesInContPopulation();

        //Display Results
        a.printTopNCountriesInContPopulation(topNCountriesContPop);

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
                    "SELECT code, name, continent, region, capital, population "
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
                pop.capital = rset.getInt("country.capital");
                pop.region = rset.getString("country.region");
                pop.code = rset.getString("country.code");
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
        System.out.println(String.format("%-10s %10s %-50s %10s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : population) {

            String popCount = String.format("%-10s %10s %-50s %-30s %-30s, %-30s", pop.code, pop.population,  pop.name, pop.capital, pop.continent, pop.region);
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
                    "SELECT ct.countryCode, c.name as Country, ct.name As  City, ct.district, ct.population " +
                            "from city as ct Join country as c ON ct.CountryCode = c.code  " +
                            "Order by ct.population desc ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> cityPop7 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("city");
                pop.district = rset.getString("district");
                pop.countryCode = rset.getString("countryCode");
                pop.country = rset.getString("Country");
                cityPop7.add(pop);
            }
            return cityPop7;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the Cities in the World.
     *
     * @param CityPop7 The list of All Cities in the world Population to print.
     */
    public void printCityPop(ArrayList<City> CityPop7) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Cities in the world organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-20s %-30s %10s", "Country Code", "city", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : CityPop7) {

            String popCount = String.format("%-20s %-20s %-20s %-30s %10s", pop.countryCode, pop.name, pop.countryCode, pop.district, pop.population);
            System.out.println(popCount);
        }
    }

    public ArrayList<City> getCityPopulation10()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name AS city, city.population, city.district, country.name AS country " +
                            "FROM city " +
                            "INNER JOIN country ON city.countrycode = country.code " +
                            "ORDER BY country ASC, population DESC ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population = new ArrayList<>();
            while (rset.next())
            {
                City citypop10 = new City();
                citypop10.population = rset.getInt("city.population");
                citypop10.name = rset.getString("city");
                citypop10.country = rset.getString("country");
                citypop10.district = rset.getString("district");

                population.add(citypop10);
            }
            return population;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }
    /**
     * Prints a list of all the Cities in a country, ordered by Largest to Smallest Population.
     */
    public void printCityPopulation10(ArrayList<City> citypop10)
    {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("Cities in a country from largest to smallest population"));
        System.out.println(String.format("%-20s %-20s %-30s %-30s", "Name", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : citypop10)
        {
            String popCount = String.format("%-20s %-20s %-30s %-30s", pop.name, pop.country, pop.district, pop.population);
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
            String strSelect = "SELECT name, continent, code, capital, Region, population "
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
     * Prints a list of Top N Populations.
     *
     * @param topPopulation The list of Population to print.
     */
    public void printTopNCountryPopulation(ArrayList<Country> topPopulation) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Top N countries in the world with N provided by user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-30s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : topPopulation) {

            String popCount = String.format("%-10s %10s %-50s %-30s %-30s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }
    }

    public ArrayList<City> getTopCityPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "WITH city1 as (select city.name as name, country.name as country, district, country.continent as continent, city.population as population, RANK () " +
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
                pop.country = rset.getString("Country");
                pop.continent = rset.getString("continent");
                pop.district = rset.getString("district");
                pop.row_num = rset.getInt("row_num");
                nCityPop.add(pop);
            }
            return nCityPop;
        }
        catch (Exception e)
        {
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
        System.out.println(String.format("%10s %-30s %-30s %-30s %-30s %10s", "row_num", "City", "Country",  "District", "Continent", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : nCityPop) {

            String popCount = String.format("%10s %-30s %-30s %-30s %-30s %10s", pop.row_num, pop.name,pop.country, pop.district, pop.continent, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Gets the population of all countries in a continent.
     *
     * @return A list of all countries in continenet population sorted in descending order, or null if there is an error.
     */
    public ArrayList<Country> getContinentPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, continent, region, capital, population "
                            + "FROM country "
                            + "Order By continent ASC, population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> population2 = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
                pop.name = rset.getString("country.Name");
                pop.continent = rset.getString("country.continent");
                pop.capital = rset.getInt("country.capital");
                pop.region = rset.getString("country.region");
                pop.code = rset.getString("country.code");
                population2.add(pop);
            }
            return population2;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Countries in Continent Populations.
     *
     * @param population2 The list of Countries in Continent Populations to print.
     */
    public void printContinentPopulation(ArrayList<Country> population2) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "Population of All the countries in a continent organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-30s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : population2) {

            String popCount = String.format("%-10s %10s %-50s %10s %-30s %-30s", pop.code, pop.population,  pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }
    }

    /**
     * Gets All the cities in a continent.
     * @return A list of all Population sorted in descending order, or null if there is an error.
     */

    public ArrayList<City> getCityPopconti() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ct.countryCode, c.name As Country, ct.name As City, ct.population, c.continent " +
                            " from city as ct Join country as c ON ct.CountryCode = c.code " +
                            "Order by c.continent, ct.population desc ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> cityPop8 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("city");
                pop.countryCode = rset.getString("countryCode");
                pop.country = rset.getString("Country");
                pop.continent = rset.getString("Continent");
                cityPop8.add(pop);
            }
            return cityPop8;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the Cities in a continent sorted by largest to smallest population.
     *
     * @param CityPop8 The list of All Cities in a continent Population to print.
     */
    public void printCityPop8(ArrayList<City> CityPop8) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Cities in a continent organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-20s %-30s %10s", "Country Code", "city", "Country", "Continent", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : CityPop8) {

            String popCount = String.format("%-20s %-20s %-20s %-30s %10s", pop.countryCode, pop.name, pop.country, pop.continent, pop.population);
            System.out.println(popCount);
        }
    }


    /**
     * Gets the population of Top N countries, value provided by user.
     *
     * @return A list of Top N Countries Population in a Continent, or null if there is an error.
     */
    public ArrayList<Country> getTopNCountriesInContPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "with country as (select name, continent, population, row_number() over " +
                            "(partition by continent order by population desc, continent desc) as row_num from country) " +
                            "select row_num, name, continent, population from country where row_num <=3";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> topNCountriesContPop = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
                pop.name = rset.getString("country.Name");
                pop.code = rset.getString("country.Code");
                pop.capital = rset.getInt("country.Capital");
                pop.continent = rset.getString("country.continent");
                pop.row_num = rset.getInt("country.row_num");
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
     * @param topNCountriesContPop The list of Top M Countries in a Continent to print.
     */
    public void printTopNCountriesInContPopulation(ArrayList<Country> topNCountriesContPop) {
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the TOP N countries in a Continent with N value provided by user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%10s %10s %-50s %-30s %-30s %-30s", "row_num", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : topNCountriesContPop) {

            String popCount = String.format("%10s %10s %-50s %-30s %-30s %-30s", pop.row_num,  pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }

    }

}