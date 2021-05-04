package kapadokia.nyandoro.course.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import kapadokia.nyandoro.course.R;
import kapadokia.nyandoro.course.databinding.UnitsItemListBinding;
import kapadokia.nyandoro.course.model.Units;
import kapadokia.nyandoro.course.view.details.UnitsDetails;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.MyViewHolder>{
    private static final String TAG = "UnitsAdapter";
    List<? extends Units> unitsList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final UnitsItemListBinding binding;
        public MyViewHolder(@NonNull UnitsItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //setting the listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // first retrieve the selected position
            int position = getAdapterPosition();
            Units selectedUnit = unitsList.get(position);
            //then create an intent to the BookDetail class

            Intent intent = new Intent(view.getContext(), UnitsDetails.class);
            intent.putExtra("Unit", selectedUnit);

            // finnally, call the start activity class.
            view.getContext().startActivity(intent);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UnitsItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.units_item_list, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setUnits(unitsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return unitsList == null ? 0 : unitsList.size();
    }

    public void setUnitsList(final List<? extends Units> unitsList) {

        if (this.unitsList == null) {
            this.unitsList = unitsList;
            notifyItemRangeInserted(0, unitsList.size());
        } else {

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UnitsAdapter.this.unitsList.size();
                }

                @Override
                public int getNewListSize() {
                    return unitsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UnitsAdapter.this.unitsList.get(oldItemPosition).getId().equals(unitsList.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Units latest = unitsList.get(newItemPosition);
                    Units old = unitsList.get(oldItemPosition);
                    return latest.getId().equals(old.getId()) && Objects.equals(latest.getContent(), old.getCourse());
                }
            });

            this.unitsList = unitsList;
            result.dispatchUpdatesTo(this);
        }
    }
}
