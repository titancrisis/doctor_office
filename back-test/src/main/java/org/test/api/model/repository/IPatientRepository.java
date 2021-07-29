package org.test.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.test.api.model.entity.Patient;

import java.util.List;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT \n" +
            "  p.id,\n" +
            "  concat(p.name, ' ', p.last_name) full_name\n" +
            "FROM patient p;", nativeQuery = true)
    List findAllSingle();
}
