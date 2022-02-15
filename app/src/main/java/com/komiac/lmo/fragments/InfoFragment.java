package com.komiac.lmo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.komiac.lmo.R;
import com.komiac.lmo.data.Prescription;
import com.komiac.lmo.data.PrescriptionsRepository;
import com.komiac.lmo.exceptions.NotFoundInRepositoryException;


public class InfoFragment extends Fragment implements FragmentWithPrescriptionRepository {
    private Prescription prescription;
    private PrescriptionsRepository repository;

    public InfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            try {
                prescription = repository.getByIdOrFail(getArguments().getLong("id"));
            } catch (NotFoundInRepositoryException e) {
                // TODO: обработка исключения.
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_info, container, false);

        TextView recipeShortInfo = view.findViewById(R.id.recipeShortInfo);
        recipeShortInfo.setText(getString(R.string.prescription,
                prescription.getSeriesAndNumber(requireContext())));

//        view.findViewById(R.id.infoRpDtdSignaFragment);

        return view;
    }

    // Getters & setters.
    @Override
    public void setRepository(PrescriptionsRepository repository) {
        this.repository = repository;
    }
}