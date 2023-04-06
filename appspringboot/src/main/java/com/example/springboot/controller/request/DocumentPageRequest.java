package com.example.springboot.controller.request;

import lombok.Data;


@Data
public class DocumentPageRequest extends BaseRequest{

    private String documentName;
    private String userName;
    private Integer documentStatue;
    private String documentPostTime;
    private Integer documentClassId;
}
