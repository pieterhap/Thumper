package be.vives.pieter.githubrestclassdemo.thumper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 25/01/2018.
 */

public class Pixel {
    @Expose
    @SerializedName("string_id")
    private String stringId;

    @Expose
    @SerializedName("number_of_pixels")
    private int numberOfPixels;

    public String getStringId() {
        return stringId;
    }

    public int getNumberOfPixels() {
        return numberOfPixels;
    }
}
