package kapadokia.nyandoro.course.repository;

import android.database.Observable;

import java.util.List;

import kapadokia.nyandoro.course.model.Payment;
import kapadokia.nyandoro.course.model.Units;
import kapadokia.nyandoro.course.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UnitsService {

    // defining the baseUrl
    public String UNITS_BASE_URL ="https://assn-course.herokuapp.com/";

    // getting units list
    @GET("unit/{uid}")
    Call<List<Units>> getAllUnits(@Path("uid") String uid);
    // getting units list
    @POST("user/confirm")
    Call<User> confirmUser(@Body User user);

    // getting paymentData
    @GET("pay/{uid}")
    Call<Payment> paymentDetails(@Path("uid") String uid);
}
