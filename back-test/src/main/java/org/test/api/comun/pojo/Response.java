package org.test.api.comun.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    private Boolean result;
    private String message;
    private Object body;
}
