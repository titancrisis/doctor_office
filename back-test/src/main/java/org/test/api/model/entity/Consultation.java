package org.test.api.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "description")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "prescription_drugs")
    private String prescriptionDrugs;

    @PrePersist
    void prePersist() {
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPrescriptionDrugs() {
        return prescriptionDrugs;
    }

    public void setPrescriptionDrugs(String prescriptionDrugs) {
        this.prescriptionDrugs = prescriptionDrugs;
    }
}
