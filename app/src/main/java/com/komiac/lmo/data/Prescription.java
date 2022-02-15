package com.komiac.lmo.data;

import android.content.Context;

import com.komiac.lmo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Prescription {
    private long id;
    private String series;
    private String number;
    private String mnn;
    private String tradeName;
    private String dtd;
    private String signa;
    private boolean isReplacementForbidden;
    private Date orderDate;
    private int daysCount;
    private String pharmacistName;
    private String pharmacy;
    private String organization;
    private PrescriptionType type;

    public PrescriptionOverview getOverview(Context context) {
        return new PrescriptionOverview(mnn, getShortInfo(context), type, this);
    }

    public String getShortInfo(Context context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());

        return context.getString(R.string.shortInfoFormat, dateFormat.format(orderDate),
                getSeriesAndNumber(context));
    }

    public String getSeriesAndNumber(Context context) {
        return context.getString(R.string.seriesAndNumber, series, number);
    }

    public String getStringType(Context context) {
        switch (type) {
            case Prescribed:
                return context.getString(R.string.prescribed1);

            case Released:
                return context.getString(R.string.released1);

            case Delayed:
            default:
                return context.getString(R.string.delayed1);
        }
    }
}
