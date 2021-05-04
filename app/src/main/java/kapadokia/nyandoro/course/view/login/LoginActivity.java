package kapadokia.nyandoro.course.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import kapadokia.nyandoro.course.R;
import kapadokia.nyandoro.course.databinding.ActivityLoginBinding;
import kapadokia.nyandoro.course.model.User;
import kapadokia.nyandoro.course.utils.PreferenceKeys;
import kapadokia.nyandoro.course.view.pay.PaymentActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    User user;
    private  Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    }





    public void doLogin(View view) {
        getDialog().show();
        user = new User();
        user.setUserId(binding.userId.getText().toString().trim());
        user.setUser_pass(binding.userPassword.getText().toString().trim());

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected && !binding.userPassword.getText().toString().isEmpty()
                && !binding.userId.getText().toString().isEmpty()){
            final LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
            observeViewModel(loginViewModel,user);
        }else{
            getDialog().dismiss();
            Toast.makeText(context, "Connectivity issues", Toast.LENGTH_SHORT).show();
        }


    }

    private void observeViewModel(LoginViewModel loginViewModel, User user) {
        Log.d(TAG, "observeViewModel: "+user.getUserId());
        // Update the list when the data changes
        loginViewModel.getLoginObservable(user).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User newUser) {
                if (newUser!= null){
                    getDialog().dismiss();
                    String uid = newUser.getUserId();

                    //shared pref
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(PreferenceKeys.user_id, newUser.getCourse());
                    editor.apply();
                    //intent
                    Intent intent = new Intent(LoginActivity.this, PaymentActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                    finish();
                }else{
                    getDialog().dismiss();
                    Toast.makeText(context, "Invalid Credentials ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Dialog  getDialog(){
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_activity);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        dialog.getWindow().setAttributes(lp);

        return dialog;
    }

}