package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;


@Data
public class Document {

    private Integer documentId;

    private Integer userId;

    private String documentName;

    private String documentAbout;

    private Integer documentStatue;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate documentPostTime;

    private Integer documentClassId;

    private String documentExamerString;

    private Integer documentProcess;

    private String documentContent;

    private String documentContentName;

    private String  documentExameTimeString;

    private Integer documentNowExamerId;

    private Integer documentNowDepartmentId;
}
