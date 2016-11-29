package com.letgo.letgotv.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * UI Class to Send the List from Feed Activity to Detail Activity
 */
public class DetailDTO implements Parcelable {

    //List of Detail Items
    private List<DetailListItemDTO> list = new ArrayList<DetailListItemDTO>();

    /**
     * Empty Constructor
     */
    public DetailDTO() {

    }

    public List<DetailListItemDTO> getList() {
        return list;
    }

    public void setList(ArrayList<DetailListItemDTO> list) {
        this.list = list;
    }

    /**
     * Parcel Constructor
     */
    public DetailDTO(Parcel parcel) {

        parcel.readTypedList(list, DetailListItemDTO.CREATOR);
    }

    /**
     * Save the Data on Parcel
     *
     * @param parcel
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeTypedList(list);
    }

    /**
     * Parcel Creator
     */
    public static final Parcelable.Creator<DetailDTO> CREATOR = new Parcelable.Creator<DetailDTO>() {

        @Override
        public DetailDTO createFromParcel(Parcel source) {
            return new DetailDTO(source);
        }

        @Override
        public DetailDTO[] newArray(int size) {
            return new DetailDTO[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }
}
