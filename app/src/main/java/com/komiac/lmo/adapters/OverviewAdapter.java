package com.komiac.lmo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.komiac.lmo.OnSelectListener;
import com.komiac.lmo.R;
import com.komiac.lmo.data.PrescriptionOverview;

import java.util.List;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<PrescriptionOverview> overviews;
    private final OnSelectListener onSelect;

    public OverviewAdapter(Context context, List<PrescriptionOverview> overviews,
                           OnSelectListener onSelect) {
        this.overviews = overviews;
        this.inflater = LayoutInflater.from(context);
        this.onSelect = onSelect;
    }

    @NonNull
    @Override
    public OverviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {
        View view = inflater.inflate(R.layout.fragment_overview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OverviewAdapter.ViewHolder holder, int position) {
        PrescriptionOverview overview = overviews.get(position);

        holder.tradeName.setText(overview.getTradeName());
        holder.shortInfo.setText(overview.getShortInfo());

        holder.itemContainer.setOnClickListener(v -> {
            onSelect.onPrescriptionOverviewSelectListener(overviews.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return overviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tradeName, shortInfo;
        final ConstraintLayout itemContainer;

        ViewHolder(View view){
            super(view);
            tradeName = view.findViewById(R.id.tradeName);
            shortInfo = view.findViewById(R.id.shortInfo);
            itemContainer = view.findViewById(R.id.itemContainer);
        }
    }
}