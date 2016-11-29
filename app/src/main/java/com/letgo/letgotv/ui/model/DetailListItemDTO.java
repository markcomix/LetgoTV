package com.letgo.letgotv.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.letgo.letgotv.business.model.ResultModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * UI Object for Detail Item
 */
public class DetailListItemDTO implements Parcelable {

    private int id;

    private String imageUrl;

    private String title;

    private String overview;

    private String voteAverage;

    private String firstAirDate;

    /**
     * Default Constructor. You must send a Business Object
     *
     * @param model
     */
    public DetailListItemDTO(ResultModel model) {

        this.id = model.getId();

        this.imageUrl = model.getBackdropUrl();

        this.title = model.getName();
        this.overview = model.getOverview();
        this.voteAverage = String.valueOf(model.getVoteAverage());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.firstAirDate = dateFormat.format(model.getFirstAirDate());
    }

    /**
     * Constructor  to Add the Selected Feed Item to this List
     * @param feedItem
     */
    public DetailListItemDTO(FeedListItemDTO feedItem) {

        this.id = feedItem.getId();

        this.imageUrl = feedItem.getBackdropUrl();

        this.title = feedItem.getTitle();
        this.overview = feedItem.getOverview();
        this.voteAverage = String.valueOf(feedItem.getVoteAverage());

        this.firstAirDate = feedItem.getFirstAirDate();
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

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    /**
     * Parcel Constructor
     */
    public DetailListItemDTO(Parcel parcel) {

        this.id = parcel.readInt();
        this.imageUrl = parcel.readString();
        this.title = parcel.readString();
        this.overview = parcel.readString();
        this.voteAverage = parcel.readString();
        this.firstAirDate = parcel.readString();
    }

    /**
     * Save the Data on Parcel
     *
     * @param parcel
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeInt(this.id);
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.voteAverage);
        parcel.writeString(this.firstAirDate);
    }

    /**
     * Parcel Creator
     */
    public static final Parcelable.Creator<DetailListItemDTO> CREATOR = new Parcelable.Creator<DetailListItemDTO>() {

        @Override
        public DetailListItemDTO createFromParcel(Parcel source) {
            return new DetailListItemDTO(source);
        }

        @Override
        public DetailListItemDTO[] newArray(int size) {
            return new DetailListItemDTO[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }
}
