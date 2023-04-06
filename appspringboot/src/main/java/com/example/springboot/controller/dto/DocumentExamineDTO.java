package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentExamineDTO {

    private Integer documentId;

    private Integer userId;

    private Integer documentClassId;

    private Integer documentStatue;

    private String documentName;

    private String documentClassName;

    private String userName;

    private String documentAbout;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate documentPostTime;

}
