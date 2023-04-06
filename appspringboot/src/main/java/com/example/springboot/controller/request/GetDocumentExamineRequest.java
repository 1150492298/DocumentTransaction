package com.example.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetDocumentExamineRequest extends BaseRequest{

    private String documentName;

    private String documentPostTime;

    private String  documentExameTimeString;

    private Integer documentNowExamerId;

    private Integer documentNowDepartmentId;

    private String token;
}
