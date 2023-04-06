package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentMyselfDTO {

    private Integer documentId;

    private Integer documentStatue;

    private Integer documentClassId;

    private String documentName;

    private String documentClassName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate documentPostTime;
}
