package com.komiac.lmo.data;

import android.content.Context;

import com.komiac.lmo.exceptions.NotFoundInRepositoryException;

import java.util.ArrayList;
import java.util.Date;

public class PrescriptionsRepository {
    private final ArrayList<Prescription> prescribed;
    private final ArrayList<Prescription> released;
    private final ArrayList<Prescription> delayed;

    public PrescriptionsRepository() {
        prescribed = new ArrayList<>();
        released = new ArrayList<>();
        delayed = new ArrayList<>();
    }

    // TODO: test;
    private int count = 1;
    public void update() {

        // TODO: test;
        prescribed.add(new Prescription(
                (long) count,
                "3442143",
                "4342343242314",
                "Амлодипин+Индапамид+Лизиноприл " + count,
                "Нурофен",
                "№10 (десять)",
                "По 10 в неделю.",
                false,
                new Date(),
                30,
                "Терапевт Молчанов Ростислав Дмитриевич",
                "Аптека №103",
                "Поликлиника городской больницы № 1 Центрального района г. Кемерово",
                PrescriptionType.Prescribed
        ));

        released.add(new Prescription(
                (long) count,
                "3442143",
                "4342343242314",
                "Пихты сибирской экстракт " + count,
                null,
                "№10 (десять)",
                "По 10 в неделю.",
                false,
                new Date(),
                7,
                "Терапевт Молчанов Ростислав Дмитриевич",
                "Аптека №103",
                "Поликлиника городской больницы № 1 Центрального района г. Кемерово",
                PrescriptionType.Released
        ));

        delayed.add(new Prescription(
                (long) count,
                "7875896",
                "3413545467576",
                "Анастрозол " + count,
                "Анастрозол",
                "№10 (десять)",
                "По 10 в час.",
                false,
                new Date(),
                15,
                "Терапевт Молчанов Ростислав Дмитриевич",
                "Аптека №103",
                "Поликлиника городской больницы № 1 Центрального района г. Кемерово",
                PrescriptionType.Delayed
        ));

        count += 1;
    }

    public ArrayList<Prescription> getAllByType(PrescriptionType type) {
        switch (type) {
            case Prescribed:
                return prescribed;

            case Released:
                return released;

            case Delayed:
            default:
                return delayed;
        }
    }

    public ArrayList<PrescriptionOverview> getAllOverviewsByType(PrescriptionType type, Context context) {
        ArrayList<PrescriptionOverview> overviews = new ArrayList<>();

        for (Prescription prescription : getAllByType(type)) {
            overviews.add(prescription.getOverview(context));
        }

        return overviews;
    }

    public ArrayList<Prescription> getAll() {
        ArrayList<Prescription> all = new ArrayList<>();

        all.addAll(prescribed);
        all.addAll(delayed);
        all.addAll(released);

        return all;
    }

    public Prescription getById(long id) {
        for (Prescription prescription : getAll()) {
            if (prescription.getId() == id)
                return prescription;
        }

        return null;
    }

    public Prescription getByIdOrFail(long id) throws NotFoundInRepositoryException {
        Prescription prescription = getById(id);
        if (prescription == null)
            throw new NotFoundInRepositoryException();

        return prescription;
    }
}
