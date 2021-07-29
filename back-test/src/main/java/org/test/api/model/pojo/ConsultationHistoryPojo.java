package org.test.api.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConsultationHistoryPojo {
    private int id;
    private String doctor;
    private String specialty;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/La_Paz")
    private Date createdAt;
    private String prescriptionDrugs;
    private String description;
}
