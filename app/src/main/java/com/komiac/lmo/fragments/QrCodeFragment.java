package com.komiac.lmo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.komiac.lmo.R;
import com.komiac.lmo.data.PrescriptionsRepository;

public class QrCodeFragment extends Fragment implements FragmentWithPrescriptionRepository {
    private PrescriptionsRepository repository;

    public QrCodeFragment() {
    }

    public static QrCodeFragment newInstance() {
        return new QrCodeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qr_code, container, false);
    }

    @Override
    public void setRepository(PrescriptionsRepository repository) {
        this.repository = repository;
    }
}