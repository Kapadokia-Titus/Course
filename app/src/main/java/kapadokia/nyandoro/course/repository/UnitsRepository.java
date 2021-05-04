package kapadokia.nyandoro.course.repository;

import android.os.strictmode.UntaggedSocketViolation;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import kapadokia.nyandoro.course.model.Payment;
import kapadokia.nyandoro.course.model.Units;
import kapadokia.nyandoro.course.model.User;
import kapadokia.nyandoro.course.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnitsRepository {
    private static final String TAG = "UnitsRepository";
    private UnitsService unitsService;
    public static UnitsRepository unitsRepository;



    private UnitsRepository(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UnitsService.UNITS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        unitsService =retrofit.create(UnitsService.class);
    }

    public synchronized static UnitsRepository getInstance(){
        //TODO No need to implement this singleton later since Dagger will handle it ...

        if (unitsRepository==null){
            unitsRepository = new UnitsRepository();
        }

        return unitsRepository;
    }

    // this method returns a live data that will be used in the GADS Hours view model
    public LiveData<List<Units>> getUnitList(String uid){

        final MutableLiveData<List<Units>> data = new MutableLiveData<>();

        unitsService.getAllUnits(uid).enqueue(new Callback<List<Units>>() {
            @Override
            public void onResponse(Call<List<Units>> call, Response<List<Units>> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Units>> call, Throwable t) {
                // TODO better error handling later ...
//                data.setValue(null);
                Log.d(TAG, "onResponse: " + t.getMessage() );
            }
        });

        return data;
    }
    // this method returns a live data that will be used in the GADS Hours view model
    public LiveData<User> getUser(User user){
        Log.d(TAG, "onResponse: "+ user.getUser_pass() +" "+ user.getUserId());
        final MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        unitsService.confirmUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                simulateDelay();
                userMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return userMutableLiveData;
    }
    // this method returns a live data that will be used in the GADS Hours view model
    public LiveData<Payment> getPayment(String uid){

        final MutableLiveData<Payment> data = new MutableLiveData<>();
        unitsService.paymentDetails(uid).enqueue(new Callback<Payment>(){

            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                simulateDelay();
                data.setValue(response.body());
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {

            }
        });

        return data;
    }

    private void simulateDelay() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
