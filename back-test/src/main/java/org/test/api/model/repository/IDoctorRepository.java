package org.test.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.test.api.model.entity.Doctor;

import java.util.List;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "SELECT \n" +
            "  d.id,\n" +
            "  concat(d.name, ' ', d.last_name) full_name\n" +
            "FROM doctor d;", nativeQuery = true)
    List findAllSingle();
}
