package kapadokia.nyandoro.course.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import kapadokia.nyandoro.course.R;
import kapadokia.nyandoro.course.databinding.ActivityMainBinding;
import kapadokia.nyandoro.course.model.Units;
import kapadokia.nyandoro.course.utils.PreferenceKeys;

public class UnitsActivity extends AppCompatActivity {
    private static final String TAG = "UnitsActivity";
    private ActivityMainBinding binding;
    private UnitsAdapter unitsAdapter;
    private  Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        unitsAdapter = new UnitsAdapter();
        binding.unitsRecycler.setAdapter(unitsAdapter);
        init();

    }

    private void init(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected){
            Toast.makeText(context, "Connectivity issues", Toast.LENGTH_SHORT).show();
        }else {
            final UnitsViewModel unitsViewModel = ViewModelProviders.of(this).get(UnitsViewModel.class);
            observeViewModel(unitsViewModel);
        }
    }

    private void observeViewModel(UnitsViewModel unitsViewModel) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String uid= preferences.getString(PreferenceKeys.user_id, "");
        // Update the list when the data changes
        unitsViewModel.getSkillIqListObservable(uid).observe(this, new Observer<List<Units>>() {
            @Override
            public void onChanged(List<Units> units) {
                
                unitsAdapter.setUnitsList(units);
                Log.d(TAG, "onChanged: "+units);
                
                if (units.isEmpty())Toast.makeText(context, "Cant retrieve", Toast.LENGTH_SHORT).show();
            }
        });
    }


}