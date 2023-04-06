package com.example.springboot.mapper;

import com.example.springboot.controller.request.DocumentClassRequest;
import com.example.springboot.entity.Rights;
import com.example.springboot.entity.DocumentClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DocumentClassMapper {

    List<DocumentClass> listDocumentClasss();

    void saveDocumentClass(DocumentClass documentClass);

    List<DocumentClass> getRightByRoleId(Integer roleId);

    void updateDocumentClassById(DocumentClass documentClass);

    void delDocumentClassById(Integer roleId);

    List<Rights> getRightDetailByRoleId(Integer roleId);

    List<DocumentClass> pageDocumentClass(DocumentClassRequest documentClassRequest);

    DocumentClass getDocumentClassById(Integer documentClassId);

    String getDocumentClassStringById(Integer documentClassId);

    List<DocumentClass> listDocumentClass();
}
