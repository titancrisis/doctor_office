package org.test.api.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.test.api.model.entity.Patient;

import java.util.Date;

@Getter
@Setter
public class PatientPojo {
    private int id;
    private String name;
    private String lastName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String address;

    public PatientPojo(Patient entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.lastName = entity.getLastName();
        this.birthDate = entity.getBirthDate();
        this.address = entity.getAddress();
    }
}
