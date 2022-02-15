package com.komiac.lmo.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrescriptionOverview implements Serializable {
    public String tradeName;
    public String shortInfo;
    public PrescriptionType type;
    public Prescription fullPrescription;
}
