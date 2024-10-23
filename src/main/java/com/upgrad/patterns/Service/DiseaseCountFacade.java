package com.upgrad.patterns.Service;

import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import com.upgrad.patterns.Service.IndiaDiseaseStatFactory;
import com.upgrad.patterns.Constants.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseCountFacade {

    private final IndiaDiseaseStatFactory indiaDiseaseStatFactory;

    public interface IndianDiseaseStat {
        Object getActiveCount();
        Object getInfectedRatio(); // Ensure this exists
    }

    @Autowired
    public DiseaseCountFacade(IndiaDiseaseStatFactory indiaDiseaseStatFactory) {
        this.indiaDiseaseStatFactory = indiaDiseaseStatFactory;
    }

    public Object getDiseaseShCount() {
        IndianDiseaseStat diseaseStat = indiaDiseaseStatFactory.GetInstance(SourceType.DISEASE_SH);
        return diseaseStat.getActiveCount(); // Method name correction
    }

    public Object getJohnHopkinCount() {
        IndianDiseaseStat diseaseStat = indiaDiseaseStatFactory.GetInstance(SourceType.JOHN_HOPKINS);
        return diseaseStat.getActiveCount(); // Method name correction
    }

    public Object getInfectedRatio(String sourceType) {
        SourceType type;
        try {
            type = SourceType.valueOf(sourceType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid source type: " + sourceType);
        }

        IndianDiseaseStat diseaseStat = indiaDiseaseStatFactory.GetInstance(type);
        return diseaseStat.getInfectedRatio(); // Assuming this method exists in the interface
    }
}
