package com.komiac.lmo;

import com.komiac.lmo.data.PrescriptionOverview;

@FunctionalInterface
public interface OnSelectListener {
    void onPrescriptionOverviewSelectListener(PrescriptionOverview overview);
}