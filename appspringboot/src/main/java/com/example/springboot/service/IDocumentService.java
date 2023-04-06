package com.example.springboot.service;

import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.DocumentExamineDTO;
import com.example.springboot.controller.request.DocumentMyselfPageRequest;
import com.example.springboot.controller.request.DocumentPageRequest;
import com.example.springboot.controller.request.GetDocumentExamineRequest;
import com.example.springboot.entity.Document;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDocumentService {

    Object PageDocuments(DocumentPageRequest documentPageRequest);

    String getDocumentNameById(Integer documentId);

    void PostDocument(Object document);

    Object getDocumentExamine(GetDocumentExamineRequest getDocumentExamineRequest);

    Document getDocumentById(Integer id);

    void documentApprove(Object object);

    void documentReject(Object object);

    Object getDocumentByUserToken(DocumentMyselfPageRequest documentMyselfPageRequest);

    void delDocumentById(Integer documentId);
}
