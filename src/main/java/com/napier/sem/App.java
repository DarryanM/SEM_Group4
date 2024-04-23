package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33070", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Extract Country Population
        ArrayList<Country> population = a.getCountryPopulation();

        //Display Results
        a.printCountryPopulation(population);

        // Extract Continent Population
        ArrayList<Country> population2 = a.getContinentPopulation("Europe");

        //Display Continent Population Results
        a.printContinentPopulation(population2);

        // Extract Region Population
        ArrayList<Country> population3 = a.getRegionPopulation("Caribbean");

        //Display Region Population Results
        a.printRegionPopulation(population3);

        // Extract Top Countries Population
        ArrayList<Country> topPopulation = a.getTopNCountryPopulation(10);

        //Display Results
        a.printTopNCountryPopulation(topPopulation);

        // Extract Top N Countries in a Continent
        ArrayList<Country> topNCountriesContPop = a.getTopNCountriesInContPopulation("North America", 3);

        //Display Results
        a.printTopNCountriesInContPopulation(topNCountriesContPop);

        // Extract Top N Countries in a Region
        ArrayList<Country> topNCountriesRegPop = a.getTopNCountriesInRegPopulation("North America", 3);

        //Display Results
        a.printTopNCountriesInRegPopulation(topNCountriesRegPop);

        // Extract City Population in the world
        ArrayList<City> cityPop7 = a.getCityPop();

        //Display Results for City population in the world
        a.printCityPop(cityPop7);

        // Extract City Population in a continent
        ArrayList<City> cityPop8 = a.getCityPopconti("Asia",999999);

        //Display Results for city population in a continent
        a.printCityPop8(cityPop8);

        // Extract City Population in a region
        ArrayList<City> cityPop9 = a.getCityPopregi("Eastern Asia", 999999);

        //Display Results for city population in a region
        a.printCityPop9(cityPop9);


        //Report 10 - Cities in a country from largest to smallest population
        // Extract city population information
        ArrayList<City> citypop10 = a.getCityPopulation10("China", 5);

        //Display Results of Report 10
        a.printCityPopulation10(citypop10);

        /*Report 11 - All the cities in a district organised by largest population to smallest.
         Extract district population information */
        ArrayList<City> districtPopulation11 = a.getDistrictPopulation("Istanbul", 5);

        // Display district population results
        a.printDistrictPopulation(districtPopulation11);

        // Report 12 - The top N populated cities in the world where N is provided by the user.
        ArrayList<City> topNCityPopWorld = a.getTopNCityPopWorld12(5);

        //Display Results of Report 12
        a.printGetTopNCityPopWorld12(topNCityPopWorld);
        // Extract Top City Population in a Continent
        ArrayList<City> nCityTopCont = a.getTopCityInContinent("Asia",3);

        //Display Results
        a.printTopCityInContinent(nCityTopCont);


        // Extract district population information
        ArrayList<City> nCityTopReg = a.getTopCityInRegion("Eastern Asia", 3);

        // Display district population results
        a.printTopCityInRegion(nCityTopReg);

        // Extract district population information
        ArrayList<City> nCityTopCtry = a.getTopCityInCountry("South Korea",3);

        // Display district population results
        a.printTopCityInCountry(nCityTopCtry);

        // Report 16 Extract top N populated cities in a District where N was provided
        ArrayList<City> population19 = a.getTopNPopCitiesDistrict("Seol",3);

        //Display Results of top N populated cities in a District where N was provided
        a.printTopNPopCitiesDistrict(population19);

        // Report 17 Extract All the capital cities in the world organised by largest population to smallest
        ArrayList<City> population20 = a.getAllCapCitiesWorld(999999);

        //Display Results of All the capital cities in the world organised by largest population to smallest
        a.printAllCapCitiesWorld(population20);

        // Extract All the capital cities in a Continent organised by largest population to smallest
        ArrayList<City> population21 = a.getAllCapCitiesContinent("Europe", 99999);

        //Display Results of All the capital cities in a Continent organised by largest population to smallest
        a.printAllCapCitiesContinent(population21);

        // Extract All the capital cities in a Region organised by largest population to smallest
        ArrayList<City> population22 = a.getAllCapCitiesRegion("Caribbean", 999999);

        //Display Results of All the capital cities in a Region organised by largest population to smallest
        a.printAllCapCitiesRegion(population22);


        // Report 20 Extract All top N populated capital cities in the world where N is provided by the user
        ArrayList<City> population23 = a.getTopNPopCapCitiesWorld(5);

         //Display Results of top N populated capital cities in the world where N is provided by the user
        a.printTopNPopCapCitiesWorld(population23);

        // Report 21 Extract All top N populated capital cities in a Continent where N and Continent are provided by the user
        ArrayList<City> population24 = a.getTopNPopCapCitiesContinent("Europe",5);

        //Display Results of top N populated capital cities in a Continent where N and Continent were provided by the user
        a.printTopNPopCapCitiesContinent(population24);

        // Report 22 - Extract top N populated capital cities in a Region where N and Region are provided by the user
        ArrayList<City> population25 = a.getTopNPopCapCitiesRegion("Caribbean",5);

        //Display Results of top N populated capital cities in a Region where N and Region were provided by the user
        a.printTopNPopCapCitiesRegion(population25);

        //Report 23 - Extract The population of people, people living in cities, and people not living in cities in each continent
        ArrayList<City> population26 = a.getLivingPopContinent(5);

        //Display Results of The population of people, people living in cities, and people not living in cities in each continent
        a.printLivingPopContinent(population26);

        // Extract The population of people, people living in cities, and people not living in cities in each Region
        ArrayList<City> PopLivReg = a.getLivingPopRegion(10);

        //Display Results of The population of people, people living in cities, and people not living in cities in each Region
        a.printLivingPopRegion(PopLivReg);

        // Extract The population of people, people living in cities, and people not living in cities in each Country
        ArrayList<City> PopLivCtry = a.getLivingPopCountry(10);

        //Display Results of The population of people, people living in cities, and people not living in cities in each Country
        a.printLivingPopCountry(PopLivCtry);

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
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "sem4");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
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

        // Check employees is not null
        if (population == null) {
            System.out.println("No Country Population");
            return;
        }

        // Print  header
        System.out.println(String.format("%-20s ", "All the countries in the world organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-30s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        for (Country pop : population) {

            if (pop == null)
                continue;

            String popCount = String.format("%-10s %10s %-50s %-30s %-30s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }


    }


    /**
     * Gets the population of all countries in a continent.
     *
     * @return A list of all countries in continenet population sorted in descending order, or null if there is an error.
     */
    public ArrayList<Country> getContinentPopulation(String cont1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, continent, region, capital, population "
                            + "FROM country "
                            + "WHERE continent = '" + cont1 + "' ORDER BY population DESC";
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
        // Check Population is not null
        if (population2 == null) {
            System.out.println("No Continent Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "Population of All the countries in a continent organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-10s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (Country pop : population2) {

            if (pop == null)
                continue;

            String popCount = String.format("%-10s %10s %-50s %10s %-30s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
        }
    }


    /**
     * Gets the population of all countries in a Region.
     *
     * @return A list of the population of all countries in a Region  sorted in descending order, or null if there is an error.
     */

    public ArrayList<Country> getRegionPopulation(String reg1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, continent, region, capital, population "
                            + "FROM country "
                            + "WHERE region = '" + reg1 + "'Order By population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<Country> population3 = new ArrayList<Country>();
            while (rset.next()) {
                Country pop = new Country();
                pop.population = rset.getInt("country.population");
                pop.name = rset.getString("country.Name");
                pop.continent = rset.getString("country.continent");
                pop.capital = rset.getInt("country.capital");
                pop.region = rset.getString("country.region");
                pop.code = rset.getString("country.code");
                population3.add(pop);
            }
            return population3;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }


    /**
     * Prints a list of Countries in a Region Populations.
     *
     * @param population3 The list of Countries in Continent Populations to print.
     */
    public void printRegionPopulation(ArrayList<Country> population3) {
        // Check Population is not null
        if (population3 == null) {
            System.out.println("No Region Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "Population of All the countries in a Region organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-10s %10s %-50s %-30s %-30s %-30s", "Code", "Population", "Country", "Capital", "Continent", "Region"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (Country pop : population3) {
            if (pop == null)
                continue;

            String popCount = String.format("%-10s %10s %-50s %-30s %-30s %-30s", pop.code, pop.population, pop.name, pop.capital, pop.continent, pop.region);
            System.out.println(popCount);
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
            } catch(Exception e){
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
        public void printTopNCountryPopulation (ArrayList < Country > topPopulation) {

            // Check countries is not null
            if (topPopulation == null) {
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
        public ArrayList<Country> getTopNCountriesInContPopulation (String cont1,int Limit1){
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =

                        "with country as (select name, code, capital, region, continent, population, row_number() over " +
                                "(partition by continent order by population desc, continent desc) as row_num from country) " +
                                "select row_num, name, code, capital, region, continent, population from country where continent = '" + cont1 + "' Limit " + Limit1;

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
        public void printTopNCountriesInContPopulation (ArrayList < Country > topNCountriesContPop) {

            // Check countries is not null
            if (topNCountriesContPop == null) {
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
        public ArrayList<Country> getTopNCountriesInRegPopulation (String reg1,int Limit1){
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =

                        "with country as (select name, code, capital, region, continent, population, row_number() over " +
                                "(partition by region order by population desc, name desc) as row_num from country) " +
                                "select row_num, name, code, capital, region, continent, population from country where region = '" + reg1 + "' Limit " + Limit1;

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
        public void printTopNCountriesInRegPopulation (ArrayList < Country > topNCountriesRegPop) {

            // Check countries is not null
            if (topNCountriesRegPop == null) {
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

        // Check employees is not null
        if (CityPop7 == null)
        {
            System.out.println("No Countries");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Cities in the world organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-20s %-30s %10s", "Country Code", "city", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : CityPop7) {

            if (pop == null)
                continue;

            String popCount = String.format("%-20s %-20s %-20s %-30s %10s", pop.countryCode, pop.name, pop.countryCode, pop.district, pop.population);
            System.out.println(popCount);
        }
    }







    /**
     * Gets All the cities in a continent.
     *
     * @return A list of all Population sorted in descending order, or null if there is an error.
     */

    public ArrayList<City> getCityPopconti(String conti1, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ct.countryCode, c.name As Country, ct.name As City, ct.population, c.continent " +
                            " FROM city as ct Join country as c ON ct.CountryCode = c.code " +
                            " WHERE c.continent = '" + conti1 +"' "+
                            "Order by ct.population desc LIMIT "+ limit1;



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

        // Check employees is not null
        if (CityPop8 == null)
        {
            System.out.println("No Cities in a continent");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Cities in a continent organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-20s %-30s %10s", "Country Code", "city", "Country", "Continent", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : CityPop8) {

            if (pop == null)
                continue;

            String popCount = String.format("%-20s %-20s %-20s %-30s %10s", pop.countryCode, pop.name, pop.country, pop.continent, pop.population);
            System.out.println(popCount);
        }
    }



    /**
     * Gets All the cities in a Region.
     *
     * @return A list of all Population sorted in descending order, or null if there is an error.
     */

    public ArrayList<City> getCityPopregi(String region1, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ct.countryCode, c.name As Country, ct.name As  City, ct.population, c.region  " +
                            " from city as ct  Join country as c ON ct.CountryCode = c.code  " +
                            " WHERE c.region = '" + region1 +"' "+
                            "Order by ct.population desc LIMIT "+ limit1;



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> cityPop9 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("city");
                pop.countryCode = rset.getString("countryCode");
                pop.country = rset.getString("Country");
                pop.region = rset.getString("region");

                cityPop9.add(pop);
            }
            return cityPop9;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the Cities in a region sorted by largest to smallest population.
     *
     * @param CityPop9 The list of All Cities in a region Population to print.
     */
    public void printCityPop9(ArrayList<City> CityPop9) {

        // Check employees is not null
        if (CityPop9 == null)
        {
            System.out.println("No Cities in a region");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the Cities in a region organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %-20s %-20s %-30s %10s", "Country Code", "city", "Country", "Region", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : CityPop9) {

            if (pop == null)
                continue;

            String popCount = String.format("%-20s %-20s %-20s %-30s %10s", pop.countryCode, pop.name, pop.country, pop.region, pop.population);
            System.out.println(popCount);
        }
    }



    /**
    All the cities in a country organised by largest population to smallest.*/
    public ArrayList<City> getCityPopulation10(String country10, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name AS city, city.population, city.district, country.name AS country " +
                            "FROM city " +
                            "INNER JOIN country ON city.countrycode = country.code " +
                            "WHERE country.name = '" + country10 + "'"+
                            "ORDER BY city.population DESC " +
                            "LIMIT " + limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population10 = new ArrayList<>();
            while (rset.next()) {
                City citypop10 = new City();
                citypop10.population = rset.getInt("city.population");
                citypop10.name = rset.getString("city");
                citypop10.country = rset.getString("country");
                citypop10.district = rset.getString("district");

                population10.add(citypop10);
            }
            return population10;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of all the Cities in a country, ordered by Largest to Smallest Population.
     */
    public void printCityPopulation10(ArrayList<City> citypop10) {
        if (citypop10 == null)
        {
            System.out.println("No Cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("Cities in a country from largest to smallest population"));
        System.out.println(String.format("%-20s %-20s %-30s %-30s", "Name", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : citypop10) {
            String popCount = String.format("%-20s %-20s %-30s %-30s", pop.name, pop.country, pop.district, pop.population);
            System.out.println(popCount);
        }
    }

    /** Report 11 - All the cities in a district organised by largest population to smallest.*/
    public ArrayList<City> getDistrictPopulation(String dist, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name AS city, country.name AS country, city.district, city.population " +
                            "FROM city INNER JOIN country ON city.countrycode = country.code " +
                            "WHERE district = '"+ dist + "' ORDER BY population desc LIMIT " + limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> districtpopulation11 = new ArrayList<>();
            while (rset.next()) {
                City district = new City();
                district.population = rset.getInt("population");
                district.name = rset.getString("city");
                district.district = rset.getString("district");
                district.country = rset.getString("country");
                districtpopulation11.add(district);
            }
            return districtpopulation11;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get district population details");
            return null;
        }
    }

    /**
     * Prints the list of cities in a district from largest to smallest population"
     */
    public void printDistrictPopulation(ArrayList<City> districtpopulation11) {
        if (districtpopulation11 == null)
        {
            System.out.println("No Countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("Cities in a district from largest to smallest population"));
        System.out.println(String.format("%-20s %-30s %-20s %-30s", "City Name", "Country", "District", "Population"));
        // Loop over all retrieved populations in the list
        for (City district : districtpopulation11) {

            if (district == null)
                continue;
            String popCount = String.format("%-20s %-30s %-20s %-30s", district.name, district.country, district.district, district.population);
            System.out.println(popCount);
        }
    }

    /**Report 12 - The top N populated cities in the world where N is provided by the user.*/
    public ArrayList<City> getTopNCityPopWorld12(int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name as name, country.name as country, city.district as district, city.population as population " +
                            "FROM city inner join country on city.countrycode = country.code " +
                            "ORDER BY city.population desc LIMIT " + limit1;


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> topNCityPopWorld12 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("Name");
                pop.country = rset.getString("country");
                pop.district = rset.getString("district");
                topNCityPopWorld12.add(pop);
            }
            return topNCityPopWorld12;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Populations.
     *
     * @param topNCityPopWorld12 The list of Population to print.
     */
    public void printGetTopNCityPopWorld12(ArrayList<City> topNCityPopWorld12) {
        if (topNCityPopWorld12 == null)
        {
            System.out.println("No Countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The top N populated cities in the world where N is provided by the user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-30s %-30s %-30s %-30s", "Name", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : topNCityPopWorld12) {
            if (pop == null)
                continue;
            String popCount = String.format("%-30s %-30s %-30s %-30s", pop.name, pop.country, pop.district, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     *
     * **
     *Report 13 - The top N populated cities in a continent where N is provided by the user.*/
    public ArrayList<City> getTopCityInContinent(String Cont, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "WITH city1 as (select city.name as name, country.name as country, district, country.continent as continent, city.population as population, RANK () " +
                            "OVER(PARTITION BY continent ORDER BY population DESC) row_num " +
                            "FROM city inner join country on city.countrycode = country.code) " +
                            "SELECT * FROM city1  WHERE continent = '" + Cont + "' LIMIT " + limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> nCityTopCont = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("Name");
                pop.country = rset.getString("Country");
                pop.continent = rset.getString("continent");
                pop.district = rset.getString("district");
                pop.row_num = rset.getInt("row_num");
                nCityTopCont.add(pop);
            }
            return nCityTopCont;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Populations.
     *
     * @param nCityTopCont The list of Population to print.
     */
    public void printTopCityInContinent(ArrayList<City> nCityTopCont) {
        if (nCityTopCont == null)
        {
            System.out.println("No Countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", "The top N populated cities in a continent where N is provided by the user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%10s %-30s %-30s %-30s %-30s %10s", "row_num", "City", "Country", "Continent", "District",  "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : nCityTopCont) {
            if (pop == null)
                continue;

            String popCount = String.format("%10s %-30s %-30s %-30s %-30s %10s", pop.row_num, pop.name, pop.country, pop.continent, pop.district,  pop.population);
            System.out.println(popCount);
        }
    }

    /**
     *
     * **
     *Report 14 - The top N populated cities in a region where N is provided by the user.*/
    public ArrayList<City> getTopCityInRegion(String reg, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "WITH city1 as (select city.name as name, country.name as country, district, country.region as region, city.population as population, RANK () " +
                            "OVER(PARTITION BY region ORDER BY population DESC) row_num " +
                            "FROM city inner join country on city.countrycode = country.code) " +
                            "SELECT * FROM city1  WHERE region = '" + reg + "' LIMIT " + limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> nCityTopReg = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("Name");
                pop.country = rset.getString("Country");
                pop.region = rset.getString("region");
                pop.district = rset.getString("district");
                pop.row_num = rset.getInt("row_num");
                nCityTopReg.add(pop);
            }
            return nCityTopReg;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Populations.
     *
     * @param nCityTopReg The list of Population to print.
     */
    public void printTopCityInRegion(ArrayList<City> nCityTopReg) {

        //Null test
        if(nCityTopReg == null)
        {
            System.out.println("No Cities");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s ", "The top N populated cities in a region where N is provided by the user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%10s %-30s %-30s %-30s %-30s %10s", "row_num", "City", "Country", "District", "Region", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : nCityTopReg) {

            if (pop == null) continue;

            String popCount = String.format("%10s %-30s %-30s %-30s %-30s %10s", pop.row_num, pop.name, pop.country, pop.district, pop.region, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     *
     * **
    *Report 15 - The top N populated cities in a country where N is provided by the user.*/
    public ArrayList<City> getTopCityInCountry(String Ctry ,int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "WITH city1 as (select city.name as name, country.name as country, district, city.population as population, RANK () " +
                            "OVER(PARTITION BY country.name ORDER BY population DESC) row_num " +
                            "FROM city inner join country on city.countrycode = country.code) " +
                            "SELECT * FROM city1  WHERE country = '" + Ctry + "' LIMIT " + limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> nCityTopCtry = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("Name");
                pop.country = rset.getString("Country");
                pop.district = rset.getString("district");
                pop.row_num = rset.getInt("row_num");
                nCityTopCtry.add(pop);
            }
            return nCityTopCtry;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of Populations.
     *
     * @param nCityTopCtry The list of Population to print.
     */
    public void printTopCityInCountry(ArrayList<City> nCityTopCtry) {

        // Check countries is not null
        if (nCityTopCtry == null)
        {
            System.out.println("No Cities");
            return;
        }

        // Print header
        System.out.println(String.format("%-20s ", "The top N populated cities in a country where N is provided by the user."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%10s %-30s %-30s %-30s %10s", "row_num", "City", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        for (City pop : nCityTopCtry) {

            if(pop == null) continue;

            String popCount = String.format("%10s %-30s %-30s %-30s %10s", pop.row_num, pop.name, pop.country, pop.district, pop.population);
            System.out.println(popCount);
        }
    }
    public Country getCountry(String code1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select code, population, continent, name, region, capital "
                            + "From country "
                            + "WHERE country.code = '" + code1 + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Country pop = new Country();
                pop.code = rset.getString("code");
                pop.population = rset.getInt("population");
                pop.continent = rset.getString("continent");
                pop.name = rset.getString("name");
                pop.region = rset.getString("region");
                pop.capital = rset.getInt("capital");
                return pop;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //Inetegeration Test get City population information
    public City getCity(String code2)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ct.countryCode, c.name as Country, ct.name As  City, ct.district, ct.population, c.continent " +
                            "from city as ct Join country as c ON ct.CountryCode = c.code  " +
                            "WHERE ct.countryCode = '" + code2 + "'";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next())
            {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("city");
                pop.district = rset.getString("district");
                pop.countryCode = rset.getString("countryCode");
                pop.country = rset.getString("Country");
                pop.continent = rset.getString("Continent");
                return pop;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets the top N populated cities in a District where N is provided.
     *
     * @return A list of the top N populated cities in a District where N was provided  sorted in descending order, or null if there is an error.
     */

    public ArrayList<City> getTopNPopCitiesDistrict(String dist1, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "WITH district1 as (select city.name as name, country.name as country, district, city.population as population, RANK ()  "
                            + "OVER(PARTITION BY district ORDER BY population DESC) row_num "
                            + "FROM city inner join country on city.countrycode = country.code) "
                            + "SELECT row_num, name, country, district, population "
                            + "FROM district1  WHERE district = '"+ dist1 + "' LIMIT " + limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population19 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.row_num = rset.getInt("row_num");
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.district = rset.getString("district");
                pop.population = rset.getInt("population");
                population19.add(pop);
            }
            return population19;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of the top N populated cities in a District where N is provided.
     *
     * @param population19 The list of  the top N populated cities in a District where N was provided to print.
     */
    public void printTopNPopCitiesDistrict(ArrayList<City> population19) {
        // Check Population is not null
        if (population19 == null)
        {
            System.out.println("No Top City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The Top N populated cities in a District where N was provided."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%10s %-40s %-40s %-40s %30s", "No.", "Name", "Country", "District", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population19) {
            if (pop == null)
                continue;

            String popCount = String.format("%10s %-40s %-40s %-40s %30s", pop.row_num, pop.name, pop.country,  pop.district, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Report #17 Gets the All the capital cities in the world organised by largest population to smallest.
     *
     * @return A list of All the capital cities in the world organised by largest population to smallest, or null if there is an error.
     */

    public ArrayList<City> getAllCapCitiesWorld(int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select city.name as name, country.name as country, district, city.population as population "
                            + "FROM city inner join country on city.id = country.capital "
                            + "ORDER BY population DESC limit " + limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population20 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.population = rset.getInt("population");
                population20.add(pop);
            }
            return population20;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the capital cities in the world organised by largest population to smallest..
     *
     * @param population20 The list of All the capital cities in the world organised by largest population to smallest to print.
     */
    public void printAllCapCitiesWorld(ArrayList<City> population20) {
        // Check Population is not null
        if (population20 == null)
        {
            System.out.println("No Capital City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the capital cities in the world organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %-40s %30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population20) {
            if (pop == null)
                continue;
            String popCount = String.format("%-40s %-40s %30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }


    public ArrayList<City> getAllCapCitiesContinent( String cont1, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select city.name as name, country.name as country, country.continent as continent, city.population as population "
                            + "FROM city inner join country on city.id = country.capital "
                            + "WHERE continent = '"+ cont1 +"' "
                            + "Order by city.population desc LIMIT "+ limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population21 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.population = rset.getInt("population");
                population21.add(pop);
            }
            return population21;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the capital cities in a Continent organised by largest population to smallest..
     *
     * @param population21 The list of All the capital cities in a Continent organised by largest population to smallest to print.
     */
    public void printAllCapCitiesContinent(ArrayList<City> population21) {
        // Check Population is not null
        if (population21 == null)
        {
            System.out.println("No Capital City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the capital cities in a Continent organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %-40s %30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population21) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %-40s %30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Gets the All the capital cities in a Continent organised by largest population to smallest.
     *
     * @return A list of All the capital cities in a Continent organised by largest population to smallest, or null if there is an error.
     */

    public ArrayList<City> getAllCapCitiesRegion( String reg1, int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select city.name as name, country.name as country, country.region as region, city.population as population "
                            + "FROM city inner join country on city.id = country.capital "
                            + "WHERE region = '"+ reg1 +"' "
                            + "Order by city.population desc LIMIT "+ limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population22 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.population = rset.getInt("population");
                population22.add(pop);
            }
            return population22;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of All the capital cities in a Region organised by largest population to smallest..
     *
     * @param population22 The list of All the capital cities in a Region organised by largest population to smallest to print.
     */
    public void printAllCapCitiesRegion(ArrayList<City> population22) {
        // Check Population is not null
        if (population22 == null)
        {
            System.out.println("No Capital City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "All the capital cities in a Region organised by largest population to smallest."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %-40s %30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population22) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %-40s %30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }




    /**
     * Report#20 Gets the top N populated capital cities in the world where N is provided.
     *
     * @return A list of the top N populated cities in the world where N was provided  sorted in descending order, or null if there is an error.
     */

    public ArrayList<City> getTopNPopCapCitiesWorld(int Limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name as name, country.name as country, city.population as population  "
                            + "FROM city inner join country on city.id = country.capital "
                            + "ORDER BY population DESC Limit " + Limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population23 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.population = rset.getInt("population");
                population23.add(pop);
            }
            return population23;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of the top N populated cities in a continent where N is provided.
     *
     * @param population23 The list of  the top N populated Capital cities in the world where N was provided to print.
     */
    public void printTopNPopCapCitiesWorld(ArrayList<City> population23) {
        // Check Population is not null
        if (population23 == null)
        {
            System.out.println("No Top Capital City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The Top N populated Capital cities in a continent where N was provided."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %-40s %30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population23) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %-40s %30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Report 21 Gets the top N populated Capital cities in a Continent where N and Continent are provided.
     *
     * @return A list of the top N populated Capital cities in a Continent where N and Continent were provided  sorted in descending order, or null if there is an error.
     */

    public ArrayList<City> getTopNPopCapCitiesContinent(String cont1, int Limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name as name, country.name as country, city.population as population  "
                            + "FROM city inner join country on city.id = country.capital "
                            + "WHERE continent = '" + cont1 + "' "
                            + "ORDER BY population desc Limit " + Limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population24 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.population = rset.getInt("population");
                population24.add(pop);
            }
            return population24;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City Population details");
            return null;
        }
    }

    /**
     * Prints a list of the top N populated Capital cities in a Continent where N and Continent are provided.
     *
     * @param population24 The list of  the top N populated Capital cities in a Continent where N and continent were provided to print.
     */
    public void printTopNPopCapCitiesContinent(ArrayList<City> population24) {
        // Check Population is not null
        if (population24 == null)
        {
            System.out.println("No Top Capital City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The Top N populated Capital cities in a Continent where N and continent was provided."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %-40s %30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population24) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %-40s %30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }

    /**
     * Report 22 - The top N populated capital cities in a region where N is provided by the user.*/
    public ArrayList<City> getTopNPopCapCitiesRegion(String reg1, int Limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name as name , country.name as country, city.population as population  "
                            + "FROM city inner join country on city.id = country.capital "
                            + "WHERE region = '" + reg1 + "' "
                            + "ORDER BY population desc Limit " + Limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population25 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.name = rset.getString("name");
                pop.country = rset.getString("country");
                pop.population = rset.getInt("population");
                population25.add(pop);
            }
            return population25;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City Population details");
            return null;
        }
    }

    /**
     * Prints a list of the top N populated Capital cities in a Region where N and Region are provided.
     *
     * @param population25 The list of  the top N populated Capital cities in a Region where N and Region were provided to print.
     */
    public void printTopNPopCapCitiesRegion(ArrayList<City> population25) {
        // Check Population is not null
        if (population25 == null)
        {
            System.out.println("No Top Capital City Population");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The Top N populated Capital cities in a Region where N and Region was provided."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %-40s %30s", "Name", "Country", "Population"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population25) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %-40s %30s", pop.name, pop.country, pop.population);
            System.out.println(popCount);
        }
    }

    /**Report 23  - The population of people, people living in cities, and people not living in cities in each continent.
    */
    public ArrayList<City> getLivingPopContinent(int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cont1 as continent, continentpop, citypop, (continentpop-citypop) as noncitypop, round((citypop/continentpop *100),2) as citypoppercent,  100-round((citypop/continentpop *100),2) as noncitypoppercent "
                            + "FROM (select sum(city.population) as CityPop, country.continent as cont1 from city join country on city.countrycode = country.code group by cont1) AS A "
                            + "Join (select sum(country.population) as continentpop, country.continent as cont2 from country group by country.continent) AS B "
                            + "ON A.cont1 = B.cont2 LIMIT " + limit1;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> population26 = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.continent = rset.getString("continent");
                pop.continentpop = rset.getLong("continentpop");
                pop.citypop = rset.getLong("citypop");
                pop.noncitypop = rset.getLong("noncitypop");
                pop.citypoppercent =rset.getDouble("citypoppercent");
                pop.noncitypoppercent =rset.getDouble("noncitypoppercent");
                population26.add(pop);
            }
            return population26;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Continent City Population details");
            return null;
        }
    }

    /**
     * Prints a list of The population of people, people living in cities, and people not living in cities in each continent.
     *
     * @param population26 The list of The population of people, people living in cities, and people not living in cities in each continent to print.
     */
    public void printLivingPopContinent(ArrayList<City> population26) {
        // Check Population is not null
        if (population26 == null)
        {
            System.out.println("No Population Information");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The population of people, people living in cities, and people not living in cities in each continent."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s %30s %30s %30s %20s %20s", "Continents", "Continent Population", "Continent City Population", "Non Continent City Population", "City Population %", "Non City Population %"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : population26) {
            if (pop == null)
                continue;

            String popCount = String.format("%-20s %30s %30s %30s %20s %20s", pop.continent, pop.continentpop, pop.citypop, pop.noncitypop, pop.citypoppercent, pop.noncitypoppercent );
            System.out.println(popCount);
        }
    }

    /**
     * Report 24 - get the population of people, people living in cities, and people not living in cities in each Region.
     *
     * @return A list of The population of people, people living in cities, and people not living in cities in each Region or null if there is an error.
     */

    public ArrayList<City> getLivingPopRegion(int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT reg1 as region, regionpop, citypop, (regionpop-citypop) as noncitypop, round((citypop/regionpop *100),2) as citypoppercent,  100-round((citypop/regionpop *100),2) as noncitypoppercent   "
                            + "FROM (select sum(city.population) as CityPop, country.region as reg1 from city join country on city.countrycode = country.code group by reg1) AS A "
                            + "Join (select sum(country.population) as regionpop, country.region as reg2 from country group by reg2) AS B "
                            + "ON A.reg1 = B.reg2 LIMIT " +limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> PopLivReg = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.region = rset.getString("region");
                pop.regionpop = rset.getLong("regionpop");
                pop.citypop = rset.getLong("citypop");
                pop.noncitypop = rset.getLong("noncitypop");
                pop.citypoppercent =rset.getDouble("citypoppercent");
                pop.noncitypoppercent =rset.getDouble("noncitypoppercent");
                PopLivReg.add(pop);
            }
            return PopLivReg;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Regional Population details");
            return null;
        }
    }

    /**
     * Prints a list of The population of people, people living in cities, and people not living in cities in each Region.
     *
     * @param PopLivReg The list of The population of people, people living in cities, and people not living in cities in each Region to print.
     */
    public void printLivingPopRegion(ArrayList<City> PopLivReg) {
        // Check Population is not null
        if (PopLivReg == null)
        {
            System.out.println("No Population Information");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The population of people, people living in cities, and people not living in cities in each Region."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %30s %30s %30s %20s %20s", "Region", "Region Population", "Region City Population", "Non Region City Population", "City Population %", "Non City Population %"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : PopLivReg) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %30s %30s %30s %20s %20s", pop.region, pop.regionpop, pop.citypop, pop.noncitypop, pop.citypoppercent, pop.noncitypoppercent );
            System.out.println(popCount);
        }
    }

    /**
     * Gets the population of people, people living in cities, and people not living in cities in each Country.
     *
     * @return A list of The population of people, people living in cities, and people not living in cities in each Region or null if there is an error.
     */

    public ArrayList<City> getLivingPopCountry(int limit1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT con1 as country, countrypop, citypop, (countrypop-citypop) as noncitypop, round((citypop/countrypop *100),2) as citypoppercent,  100-round((citypop/countrypop *100),2) as noncitypoppercent "
                            + "FROM (select sum(city.population) as CityPop, country.name as con1 from city join country on city.countrycode = country.code group by con1) AS A "
                            + "Join (select sum(country.population) as countrypop, country.name as con2 from country group by con2) AS B "
                            + "ON A.con1 = B.con2 limit " + limit1;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Population information
            ArrayList<City> PopLivCtry = new ArrayList<City>();
            while (rset.next()) {
                City pop = new City();
                pop.country = rset.getString("country");
                pop.countrypop = rset.getLong("countrypop");
                pop.citypop = rset.getLong("citypop");
                pop.noncitypop = rset.getLong("noncitypop");
                pop.citypoppercent =rset.getDouble("citypoppercent");
                pop.noncitypoppercent =rset.getDouble("noncitypoppercent");
                PopLivCtry.add(pop);
            }
            return PopLivCtry;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * Prints a list of The population of people, people living in cities, and people not living in cities in each Country.
     *
     * @param PopLivCtry The list of The population of people, people living in cities, and people not living in cities in each Country to print.
     */
    public void printLivingPopCountry(ArrayList<City> PopLivCtry) {
        // Check Population is not null
        if (PopLivCtry == null)
        {
            System.out.println("No Population Information");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-20s ", "The population of people, people living in cities, and people not living in cities in each Country."));
        System.out.println(String.format("%-20s ", " "));
        System.out.println(String.format("%-40s %30s %30s %30s %20s %20s", "Country", "Country Population", "City Population", "Non City Population", "City Population %", "Non City Population %"));
        // Loop over all Retrieved Populations in the list
        // Check if query returned values.
        for (City pop : PopLivCtry) {
            if (pop == null)
                continue;

            String popCount = String.format("%-40s %30s %30s %30s %20s %20s", pop.country, pop.countrypop, pop.citypop, pop.noncitypop, pop.citypoppercent, pop.noncitypoppercent );
            System.out.println(popCount);
        }
    }




    // INTEGRATION TESTING QUERIES
    //Inetegeration Test get City population information
    public City getDistrict(String code2)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ct.countryCode, c.name as Country, ct.name As  City, ct.district, ct.population, c.continent " +
                            "from city as ct Join country as c ON ct.CountryCode = c.code  " +
                            "WHERE ct.district = '" + code2 + "'";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next())
            {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.name = rset.getString("city");
                pop.district = rset.getString("district");
                pop.countryCode = rset.getString("countryCode");
                pop.country = rset.getString("Country");
                pop.continent = rset.getString("Continent");
                return pop;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }


    //For Integration Testing
    public City getCapitalCity(String code1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select city.name as name, country.name as country, district, city.population as population, country.continent, country.region "
                            + "FROM city inner join country on city.id = country.capital "
                            + "WHERE city.countrycode = '" + code1 + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                City pop = new City();
                pop.population = rset.getInt("population");
                pop.continent = rset.getString("continent");
                pop.name = rset.getString("name");
                pop.region = rset.getString("region");
                pop.district = rset.getString("district");
                pop.country = rset.getString("country");
                return pop;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital Country details");
            return null;
        }
    }

    //integration test - get population of people within a continent who living in and out of cities
    public City getLivingPop1(String contin1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cont1 as continent, continentpop, citypop, (continentpop-citypop) as noncitypop, round((citypop/continentpop *100),2) as citypoppercent,  100-round((citypop/continentpop *100),2) as noncitypoppercent "
                            + "FROM (select sum(city.population) as CityPop, country.continent as cont1 from city join country on city.countrycode = country.code group by cont1) AS A "
                            + "Join (select sum(country.population) as continentpop, country.continent as cont2 from country group by country.continent) AS B "
                            + "ON A.cont1 = B.cont2 "
                            + "WHERE cont1 = '" + contin1 + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                City pop = new City();
                pop.continent = rset.getString("continent");
                pop.continentpop = rset.getLong("continentpop");
                pop.citypop = rset.getLong("citypop");
                pop.noncitypop = rset.getLong("noncitypop");
                pop.citypoppercent = rset.getDouble("citypoppercent");
                pop.noncitypoppercent = rset.getDouble("noncitypoppercent");
                return pop;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country City and Non-City Population details");
            return null;
        }
    }
    //integration test - get population of people within a reagion who living in and out of cities
    public City getLivingPop2(String region1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT reg1 as region, regionpop, citypop, (regionpop-citypop) as noncitypop, round((citypop/regionpop *100),2) as citypoppercent,  100-round((citypop/regionpop *100),2) as noncitypoppercent   "
                            + "FROM (select sum(city.population) as CityPop, country.region as reg1 from city join country on city.countrycode = country.code group by reg1) AS A "
                            + "Join (select sum(country.population) as regionpop, country.region as reg2 from country group by reg2) AS B "
                            + "ON A.reg1 = B.reg2 "
                            + "Where reg1 = '" + region1 + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                City pop = new City();
                pop.region = rset.getString("region");
                pop.regionpop = rset.getLong("regionpop");
                pop.citypop = rset.getLong("citypop");
                pop.noncitypop = rset.getLong("noncitypop");
                pop.citypoppercent = rset.getDouble("citypoppercent");
                pop.noncitypoppercent = rset.getDouble("noncitypoppercent");
                return pop;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country City and Non-City Population details");
            return null;
        }
    }

    //integration test - get population of people within a country who living in and out of cities
    public City getLivingPop3(String country1) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT con1 as country, countrypop, citypop, (countrypop-citypop) as noncitypop, round((citypop/countrypop *100),2) as citypoppercent,  100-round((citypop/countrypop *100),2) as noncitypoppercent "
                            + "FROM (select sum(city.population) as CityPop, country.name as con1 from city join country on city.countrycode = country.code group by con1) AS A "
                            + "Join (select sum(country.population) as countrypop, country.name as con2 from country group by con2) AS B "
                            + "ON A.con1 = B.con2 "
                            + " WHERE con1 = '" + country1 +"'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                City pop = new City();
                pop.country = rset.getString("country");
                pop.countrypop = rset.getLong("countrypop");
                pop.citypop = rset.getLong("citypop");
                pop.noncitypop = rset.getLong("noncitypop");
                pop.citypoppercent = rset.getDouble("citypoppercent");
                pop.noncitypoppercent = rset.getDouble("noncitypoppercent");
                return pop;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country City and Non-City Population details");
            return null;
        }
    }


}