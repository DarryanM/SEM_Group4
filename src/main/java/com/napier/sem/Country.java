package com.napier.sem;

public class Country {
    /**
     * World Population
     */
    public int population;
    /**
     * Country Names
     */
    public String name;
    public String continent;
    public String region;

    public int capital;
    public String code;
    public int row_num;

    //Total Country Population
    public long countrypop;

    //Total City Population
    public long citypop;

    //Total Non-City Population
    public long noncitypop;

    // total Region Population
    public long regionpop;

    //Total Continent Population
    public long continentpop;

    //Percentage of people in city
    public double citypoppercent;

    //Percentage of people not living in city
    public double noncitypoppercent;
}
