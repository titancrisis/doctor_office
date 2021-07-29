package org.test.api.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConsultationPatientPojo {
    private int id;
    private String patient;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
}
