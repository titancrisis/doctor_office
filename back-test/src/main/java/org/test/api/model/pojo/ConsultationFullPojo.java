package org.test.api.model.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConsultationFullPojo {
    private int id;
    private String patient;
    private String doctor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/La_Paz")
    private Date createdAt;
    private String description;
    private String prescriptionDrugs;
}
