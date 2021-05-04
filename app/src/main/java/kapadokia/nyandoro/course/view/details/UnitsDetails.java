package kapadokia.nyandoro.course.view.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import kapadokia.nyandoro.course.R;
import kapadokia.nyandoro.course.databinding.ActivityUnitsDetailsBinding;
import kapadokia.nyandoro.course.model.Units;

public class UnitsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUnitsDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_units_details);
        Units units = getIntent().getParcelableExtra("Unit");


        // passing the current book to this object;
        binding.setUnit(units);
    }
}