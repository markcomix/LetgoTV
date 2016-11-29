package com.letgo.letgotv.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity to map the Backend response
 */
public class ResultEntity {

    @JsonProperty("poster_path")
    private String poster;

    @JsonProperty("popularity")
    private float popularity;

    @JsonProperty("id")
    private int id;

    @JsonProperty("backdrop_path")
    private String backdrop;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("name")
    private String name;

    public ResultEntity() {

        this.poster = "";
        this.popularity = 0f;
        this.id = 0;
        this.backdrop = "";
        this.voteAverage = 0f;
        this.overview = "";
        this.firstAirDate = "";
        this.name = "";
    }

    /**
     * Constructor for Mock Test
     */
    public ResultEntity(int id, String name, String overview, String poster, float voteAverage) {

        this.poster = poster;
        this.popularity = 0;
        this.id = id;
        this.backdrop = "";
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.firstAirDate = "";
        this.name = name;
    }

    /**
     * Constructor to Maper
     *      *
     * @param poster
     * @param popularity
     * @param id
     * @param backdrop
     * @param voteAverage
     * @param overview
     * @param firstairDate
     * @param name
     */
    public ResultEntity(String poster, float popularity, int id, String backdrop,
                        float voteAverage, String overview, String firstairDate, String name) {

        this.poster = poster;
        this.popularity = popularity;
        this.id = id;
        this.backdrop = backdrop;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.firstAirDate = firstairDate;
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getName() {
        return name;
    }
}
