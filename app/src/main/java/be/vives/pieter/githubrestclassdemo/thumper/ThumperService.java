package be.vives.pieter.githubrestclassdemo.thumper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Pieter on 25/01/2018.
 */

public interface ThumperService {
    @GET("neopixels/strings/{id}")
    Call<Pixel> getPixels (
            @Path("id") String id
    );

    @FormUrlEncoded
    @POST("neopixels/strings/{id}")
    Call<Pixel> setPixels (
            @Path("id") String id,
            @Field("red") int red,
            @Field("green") int green,
            @Field("blue") int blue
    );

    @FormUrlEncoded
    @POST("speed")
    Call<Pixel> setSpeed (
            @Field("left_speed") int left_speed,
            @Field("right_speed") int right_speed
    );
}
