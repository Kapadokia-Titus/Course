package kapadokia.nyandoro.course.view.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import kapadokia.nyandoro.course.model.Units;
import kapadokia.nyandoro.course.model.User;
import kapadokia.nyandoro.course.repository.UnitsRepository;
import kapadokia.nyandoro.course.repository.UnitsService;

public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = "LoginViewModel";
    private LiveData<User> userObservable = new MutableLiveData<>();
    public LoginViewModel(@NonNull Application application) {
        super(application);

    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */



    public LiveData<User> getLoginObservable(User user){
        userObservable = UnitsRepository.getInstance().getUser(user);
        return userObservable;}
}
