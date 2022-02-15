package com.komiac.lmo.factories;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.komiac.lmo.OnSelectListener;
import com.komiac.lmo.data.PrescriptionsRepository;
import com.komiac.lmo.fragments.FragmentWithPrescriptionRepository;
import com.komiac.lmo.fragments.OnSelectableFragment;

import lombok.SneakyThrows;

public class PrescriptionsFragmentFactory extends FragmentFactory {
    private final PrescriptionsRepository repository;
    private final OnSelectListener onSelect;

    public PrescriptionsFragmentFactory(PrescriptionsRepository repository, OnSelectListener onSelect) {
        super();
        this.repository = repository;
        this.onSelect = onSelect;
    }

    @SneakyThrows
    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);

        if (FragmentWithPrescriptionRepository.class.isAssignableFrom(fragmentClass)) {
            Fragment fragment = fragmentClass.newInstance();

            ((FragmentWithPrescriptionRepository) fragment).setRepository(repository);

            if (OnSelectableFragment.class.isAssignableFrom(fragmentClass))
                ((OnSelectableFragment) fragment).setOnSelectListener(onSelect);

            return fragment;
        } else
            return super.instantiate(classLoader, className);
    }
}