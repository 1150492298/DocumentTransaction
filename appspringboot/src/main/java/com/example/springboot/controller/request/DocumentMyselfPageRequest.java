package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class DocumentMyselfPageRequest extends BaseRequest{
    private String token;
    private Integer userId;
    private String documentName;
    private Integer documentStatue;
    private String documentPostTime;
    private Integer documentClassId;
}
