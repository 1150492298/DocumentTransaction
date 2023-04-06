package com.example.springboot.service;

import com.example.springboot.controller.request.DocumentClassRequest;
import com.example.springboot.entity.Rights;
import com.example.springboot.entity.DocumentClass;

import java.util.List;

public interface IDocumentClassService {

    List<DocumentClass> listDocumentClasss();

    void saveDocumentClass(DocumentClass documentClass);

    void updateDocumentClassById(DocumentClass documentClass);

    void delDocumentClassById(Integer id);

    List<Rights> getRightDetailByRoleId(Integer roleId);

    Object pageDocumentClass(DocumentClassRequest documentClassRequest);

    Object getDocumentClassById(Integer documentClassId);

    List<DocumentClass> listDocumentClass();
}
