package kapadokia.nyandoro.course.view.pay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import kapadokia.nyandoro.course.R;
import kapadokia.nyandoro.course.databinding.ActivityPaymentBinding;
import kapadokia.nyandoro.course.model.Payment;
import kapadokia.nyandoro.course.model.User;
import kapadokia.nyandoro.course.utils.CalculateRange;
import kapadokia.nyandoro.course.view.UnitsActivity;
import kapadokia.nyandoro.course.view.WebActivity;
import kapadokia.nyandoro.course.view.login.LoginActivity;
import kapadokia.nyandoro.course.view.login.LoginViewModel;

public class PaymentActivity extends AppCompatActivity {

    private static final String TAG = "PaymentActivity";

    ActivityPaymentBinding binding;
    private  Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment);
        init();
    }


    public void init() {

        binding.toCourses.setEnabled(false);
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected){
            Intent intent = getIntent();
            String uid = intent.getExtras().getString("uid");
            final PaymentViewModel paymentViewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);
            observeViewModel(paymentViewModel, uid);

        }else{
            Toast.makeText(context, "Connectivity issues", Toast.LENGTH_SHORT).show();
        }


    }

    private void observeViewModel(PaymentViewModel paymentViewModel, String uid) {

        paymentViewModel.getPaymentObservable(uid).observe(this, new Observer<Payment>() {
            @Override
            public void onChanged(Payment payment) {
                CalculateRange calculateRange = new CalculateRange();
              binding.setPay(payment);

              if (calculateRange.isAboveHalf(payment.getTotal_payment(), payment.getAmount_paid())){
                  binding.paymentStatus.setText(R.string.success_payment);
                  binding.toCourses.setEnabled(true);
              }else {
                  binding.toCourses.setEnabled(false);
                  binding.paymentStatus.setText(R.string.low_payment);
              }
            }
        });
    }

    public void goToCourses(View view) {
        Intent intent = new Intent(PaymentActivity.this, UnitsActivity.class);
        startActivity(intent);
    }

    public void goToWebView(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}