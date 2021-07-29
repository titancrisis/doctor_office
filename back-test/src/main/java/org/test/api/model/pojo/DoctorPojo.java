package org.test.api.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.test.api.model.entity.Doctor;

import java.util.Date;

@Getter
@Setter
public class DoctorPojo {
    private int id;
    private String name;
    private String lastName;
    private String specialty;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String address;

    public DoctorPojo(Doctor entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.lastName = entity.getLastName();
        this.specialty = entity.getSpecialty();
        this.birthDate = entity.getBirthDate();
        this.address = entity.getAddress();
    }
}
