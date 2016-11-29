package com.letgo.letgotv.ui.model;

import com.letgo.letgotv.business.model.ResultModel;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * UI Object for Feed Item
 */
public class FeedListItemDTO implements Serializable {

    private int id;

    private String imageUrl;

    private String title;

    private String voteAverage;

    private String overview;

    private String backdropUrl;

    private String firstAirDate;

    /**
     * Default Constructor. You must send a Business Object
     *
     * @param model
     */
    public FeedListItemDTO(ResultModel model) {

        this.id = model.getId();

        this.imageUrl = model.getPosterUrl();
        this.title = model.getName();
        this.voteAverage = String.valueOf(model.getVoteAverage());
        this.overview = model.getOverview();
        this.backdropUrl = model.getBackdropUrl();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.firstAirDate = dateFormat.format(model.getFirstAirDate());
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }
}
