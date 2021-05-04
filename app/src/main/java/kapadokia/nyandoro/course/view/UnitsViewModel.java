package kapadokia.nyandoro.course.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import kapadokia.nyandoro.course.model.Units;
import kapadokia.nyandoro.course.repository.UnitsRepository;

public class UnitsViewModel extends AndroidViewModel {

    private  LiveData<List<Units>> unitsListObservable;
    public UnitsViewModel(@NonNull Application application) {
        super(application);

    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */

    public LiveData<List<Units>> getSkillIqListObservable(String uid){
        unitsListObservable = UnitsRepository.getInstance().getUnitList(uid);
        return unitsListObservable;}
}
