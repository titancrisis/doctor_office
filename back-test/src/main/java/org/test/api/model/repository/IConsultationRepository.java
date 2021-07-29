package org.test.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.api.model.entity.Consultation;

import java.util.List;

@Repository
public interface IConsultationRepository extends JpaRepository<Consultation, Integer> {

    @Query(value = "SELECT \n" +
            "  a.id,\n" +
            "  concat(p.name, ' ', p.last_name) patient,\n" +
            "  concat(d.name, ' ', d.last_name) doctor,\n" +
            "  a.description,\n" +
            "  a.created_at,\n" +
            "  a.prescription_drugs\n" +
            "FROM doctor_office.consultation a\n" +
            "INNER JOIN doctor d ON a.doctor_id = d.id \n" +
            "INNER JOIN patient p ON a.patient_id = p.id \n" +
            "ORDER BY a.created_at DESC;", nativeQuery = true)
    List findAllFull();

    @Query(value = "SELECT \n" +
            "  a.id,\n" +
            "  concat(d.name, ' ', d.last_name) doctor,\n" +
            "  d.specialty,\n" +
            "  a.created_at,\n" +
            "  a.prescription_drugs,\n" +
            "  a.description\n" +
            "FROM doctor_office.consultation a\n" +
            "INNER JOIN doctor d ON a.doctor_id = d.id \n" +
            "WHERE\n" +
            "  a.patient_id = :patientId \n" +
            "ORDER BY\n" +
            "  a.created_at DESC;", nativeQuery = true)
    List findAllByPatientId(@Param("patientId") int patientId);

    @Query(value = "SELECT DISTINCT \n" +
            "  p.id,\n" +
            "  concat(p.name, ' ', p.last_name) patient,\n" +
            "  p.birth_date\n" +
            "FROM doctor_office.consultation a\n" +
            "INNER JOIN patient p ON a.patient_id = p.id\n" +
            "WHERE\n" +
            "  a.doctor_id = :doctorId \n" +
            "ORDER BY\n" +
            "  a.created_at DESC;", nativeQuery = true)
    List findAllByDoctorId(@Param("doctorId") int doctorId);
}
