package kapadokia.nyandoro.course.view.pay;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import kapadokia.nyandoro.course.model.Payment;
import kapadokia.nyandoro.course.repository.UnitsRepository;

public class PaymentViewModel extends AndroidViewModel {
    private static final String TAG = "PaymentViewModel";
    private LiveData<Payment> paymentLiveData = new MutableLiveData<>();
    public PaymentViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */

    public LiveData<Payment> getPaymentObservable(String uid){
        paymentLiveData = UnitsRepository.getInstance().getPayment(uid);
        return paymentLiveData;}
}
