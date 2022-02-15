package com.komiac.lmo.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komiac.lmo.OnSelectListener;
import com.komiac.lmo.R;
import com.komiac.lmo.adapters.OverviewAdapter;
import com.komiac.lmo.data.PrescriptionOverview;
import com.komiac.lmo.data.PrescriptionType;
import com.komiac.lmo.data.PrescriptionsRepository;

import java.util.ArrayList;


public class PageFragment extends Fragment implements FragmentWithPrescriptionRepository {
    private PrescriptionsRepository repository;
    private PrescriptionType pageType;
    private OnSelectListener onSelect;
    private RecyclerView recyclerView;

    public PageFragment() {
        super(R.layout.fragment_page);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_page,
                container,
                false
        );

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        update();

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update() {
        ArrayList<PrescriptionOverview> overviews = repository.getAllOverviewsByType(pageType, getContext());

        recyclerView.setAdapter(new OverviewAdapter(this.getContext(), overviews, onSelect));
    }

    public boolean isInit() {
        return getView() != null;
    }

    // Setters & getters.
    @Override
    public void setRepository(PrescriptionsRepository repository) {
        this.repository = repository;
    }

    public void setPageType(PrescriptionType type) {
        pageType = type;
    }

    public void setOnSelect(OnSelectListener onSelect) {
        this.onSelect = onSelect;
    }

    public PrescriptionType getPageType() {
        return pageType;
    }

    public PrescriptionsRepository getRepository() {
        return repository;
    }

    public OnSelectListener getOnSelect() {
        return onSelect;
    }
}