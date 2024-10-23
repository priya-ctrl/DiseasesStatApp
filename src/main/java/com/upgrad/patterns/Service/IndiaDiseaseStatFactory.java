package com.upgrad.patterns.Service;

import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import com.upgrad.patterns.Constants.SourceType;
import com.upgrad.patterns.Strategies.DiseaseShStrategy;
import com.upgrad.patterns.Strategies.JohnHopkinsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndiaDiseaseStatFactory {
    private IndianDiseaseStat diseaseShStrategy;
    private IndianDiseaseStat johnHopkinsStrategy;

    @Autowired
    public IndiaDiseaseStatFactory(DiseaseShStrategy diseaseShStrategy, JohnHopkinsStrategy johnHopkinsStrategy) {
        this.diseaseShStrategy = diseaseShStrategy;
        this.johnHopkinsStrategy = johnHopkinsStrategy;
    }
    public interface IndianDiseaseStat {
        Object getActiveCount();
        Object getInfectedRatio(); // Ensure this exists
    }

    // Method to get the correct instance of IndianDiseaseStat based on SourceType
    public IndianDiseaseStat GetInstance(SourceType sourceType) {
        if (sourceType == SourceType.JOHN_HOPKINS) {
            return johnHopkinsStrategy;
        } else if (sourceType == SourceType.DISEASE_SH) {
            return diseaseShStrategy;
        } else {
            throw new IllegalArgumentException("Invalid disease strategy/sourceType: " + sourceType);
        }
    }
}
