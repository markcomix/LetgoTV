package com.letgo.letgotv.business.model;

import com.letgo.letgotv.backend.model.ResultEntity;
import com.letgo.letgotv.config.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Bussiness Object with the Result
 */
public class ResultModel {

    private String posterUrl;

    private int id;

    private String backdropUrl;

    private float voteAverage;

    private String overview;

    private Date firstAirDate;

    private String name;

    /**
     * Main Constructor. It require a Backend Entity Object to create the Model Object
     * @param entity
     */
    public ResultModel(ResultEntity entity) {

        this.posterUrl = AppConstants.Backend.BASE_IMAGE_URL + entity.getPoster();
        this.id = entity.getId();
        this.backdropUrl = AppConstants.Backend.BASE_IMAGE_URL + entity.getBackdrop();
        this.voteAverage = entity.getVoteAverage();
        this.overview = entity.getOverview();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            this.firstAirDate = formatter.parse(entity.getFirstAirDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.name = entity.getName();
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getId() {
        return id;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public Date getFirstAirDate() {
        return firstAirDate;
    }

    public String getName() {
        return name;
    }
}
