package com.example.springboot.service.impl;

import com.auth0.jwt.JWT;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.RoleSearchResultDTO;
import com.example.springboot.controller.request.DocumentClassRequest;
import com.example.springboot.entity.DocumentClass;
import com.example.springboot.entity.Rights;
import com.example.springboot.mapper.DocumentClassMapper;
import com.example.springboot.service.IDocumentClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentClassService implements IDocumentClassService {

    @Autowired
    DocumentClassMapper documentClassMapper;

    @Override
    public List<DocumentClass> listDocumentClasss() {
        return null;
    }

    @Override
    public void saveDocumentClass(DocumentClass documentClass) {
        documentClassMapper.saveDocumentClass(documentClass);
    }

    @Override
    public void updateDocumentClassById(DocumentClass documentClass) {
        documentClassMapper.updateDocumentClassById(documentClass);
    }

    @Override
    public void delDocumentClassById(Integer id) {

    }

    @Override
    public List<Rights> getRightDetailByRoleId(Integer roleId) {
        return null;
    }


    @Override
    public Object pageDocumentClass(DocumentClassRequest documentClassRequest) {
        PageHelper.startPage(documentClassRequest.getPageNum(), 5);
        List<DocumentClass> documentClasses = documentClassMapper.pageDocumentClass(documentClassRequest);
        PageInfo<DocumentClass> documentClassPageInfo = new PageInfo<>(documentClasses);
        return documentClassPageInfo;
    }

    @Override
    public DocumentClass getDocumentClassById(Integer documentClassId) {
        return documentClassMapper.getDocumentClassById(documentClassId);
    }

    @Override
    public List<DocumentClass> listDocumentClass() {
        return documentClassMapper.listDocumentClass();
    }
}
