package com.example.springboot.mapper;

import com.example.springboot.controller.dto.DocumentExamineDTO;
import com.example.springboot.controller.dto.DocumentMyselfDTO;
import com.example.springboot.controller.request.DocumentMyselfPageRequest;
import com.example.springboot.controller.request.DocumentPageRequest;
import com.example.springboot.controller.request.GetDocumentExamineRequest;
import com.example.springboot.entity.Document;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocumentMapper {

    List<Document> PageDocuments(DocumentPageRequest documentPageRequest);

    String getDocumentNameById(Integer documentId);

    void PostDocument(Document document);

    List<DocumentExamineDTO> getDocumentExamine(GetDocumentExamineRequest getDocumentExamineRequest);

    Document getDocumentById(Integer documentId);

    void approveDocumentById(Document document);

    List<DocumentMyselfDTO> getDocumentByUserToken(DocumentMyselfPageRequest documentMyselfPageRequest);

    void delDocumentById(Integer documentId);
}
