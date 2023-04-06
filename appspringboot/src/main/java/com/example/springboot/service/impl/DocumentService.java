package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.springboot.controller.dto.DocumentExamineDTO;
import com.example.springboot.controller.dto.DocumentMyselfDTO;
import com.example.springboot.controller.dto.UserAboutDTO;
import com.example.springboot.controller.request.DocumentMyselfPageRequest;
import com.example.springboot.controller.request.DocumentPageRequest;
import com.example.springboot.controller.request.GetDocumentExamineRequest;
import com.example.springboot.controller.request.UserUpdateRequest;
import com.example.springboot.entity.Document;
import com.example.springboot.entity.DocumentClass;
import com.example.springboot.entity.RoleSearch;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.DocumentClassMapper;
import com.example.springboot.mapper.DocumentMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IDocumentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    DocumentMapper documentMapper;

    @Autowired
    DocumentClassMapper documentClassMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Object PageDocuments(DocumentPageRequest documentPageRequest) {
//        documentPageRequest.setDocumentStatue(2);
        PageHelper.startPage(documentPageRequest.getPageNum(), 5);
        List<Document> documents=documentMapper.PageDocuments(documentPageRequest);
        PageInfo<Document> documentPageInfo=new PageInfo<>(documents);
        return documentPageInfo;
    }

    @Override
    public String getDocumentNameById(Integer documentId) {
        return documentMapper.getDocumentNameById(documentId);
    }

    @Override
    public void PostDocument(Object document) {
        Map entity = (Map) document;
        String token = (String) entity.get("token");
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        String documentPostForm = JSON.toJSONString(entity.get("documentPostForm")) ;
        Document document1= JSONObject.parseObject(documentPostForm,Document.class);
        String[] documentClassString=documentClassMapper.getDocumentClassStringById(document1.getDocumentClassId()).split("-")[0].split(":");
        Integer documentNowDepartmentId=Integer.parseInt(documentClassString[0]);
        Integer documentNowExamerId=Integer.parseInt(documentClassString[1]);
        document1.setDocumentNowDepartmentId(documentNowDepartmentId);
        document1.setDocumentNowExamerId(documentNowExamerId);
        document1.setUserId(userId);
        documentMapper.PostDocument(document1);
    }


    @Override
    public Object getDocumentExamine(GetDocumentExamineRequest getDocumentExamineRequest) {
        String token = getDocumentExamineRequest.getToken();
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        User user=userMapper.getUsersById(userId);
        getDocumentExamineRequest.setDocumentNowDepartmentId(user.getDepartmentId());
        getDocumentExamineRequest.setDocumentNowExamerId(user.getRoleId());
        PageHelper.startPage(getDocumentExamineRequest.getPageNum(), 5);
        List<DocumentExamineDTO> documents=documentMapper.getDocumentExamine(getDocumentExamineRequest);
        PageInfo<DocumentExamineDTO> documentPageInfo=new PageInfo<>(documents);
        return documentPageInfo;
    }

    @Override
    public Document getDocumentById(Integer documentId) {
        return documentMapper.getDocumentById(documentId);
    }

    @Override
    public void documentApprove(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        String userId = JWT.decode(token).getAudience().get(0);
        Integer documentId = (Integer) entity.get("documentId");
        Document document=documentMapper.getDocumentById(documentId);
        DocumentClass documentClass=documentClassMapper.getDocumentClassById(document.getDocumentClassId());
        String userName=userMapper.getUsersById(Integer.parseInt(userId)).getUserName();
        String documentExamerString=document.getDocumentExamerString();
        String documentExameTimeString=document.getDocumentExameTimeString();
        Date date = new Date();
        String newDocumentExameTimeString= DateUtil.format(date, "yyyy-MM-dd  HH:mm:ss")+'|';
        String newDocumentExamerString=userId+":"+userName+"-";
        if(documentExamerString==null){
            documentExamerString=newDocumentExamerString;
        }else {
            documentExamerString+=newDocumentExamerString;
        }
        if(documentExameTimeString==null){
            documentExameTimeString=newDocumentExameTimeString;
        }else {
            documentExameTimeString+=newDocumentExameTimeString;
        }
        Integer documentProcess=document.getDocumentProcess();
        Integer documentProcessCount=documentClass.getDocumentProcessCount();
        documentProcess++;
        if(documentProcess.equals(documentProcessCount)){
            document.setDocumentStatue(2);
        }else{
            document.setDocumentStatue(1);
            String[] documentClassString=documentClass.getDocumentDepartmentRoleString().split("-")[documentProcess].split(":");
            Integer documentNowDepartmentId=Integer.parseInt(documentClassString[0]);
            Integer documentNowExamerId=Integer.parseInt(documentClassString[1]);
            document.setDocumentNowDepartmentId(documentNowDepartmentId);
            document.setDocumentNowExamerId(documentNowExamerId);
        }
        document.setDocumentProcess(documentProcess);
        document.setDocumentExamerString(documentExamerString);
        document.setDocumentExameTimeString(documentExameTimeString);
        documentMapper.approveDocumentById(document);
    }

    @Override
    public void documentReject(Object object) {
        Map entity = (Map) object;
        String token = (String) entity.get("token");
        String userId = JWT.decode(token).getAudience().get(0);
        Integer documentId = (Integer) entity.get("documentId");
        Document document=documentMapper.getDocumentById(documentId);
        String userName=userMapper.getUsersById(Integer.parseInt(userId)).getUserName();
        String documentExamerString=document.getDocumentExamerString();
        String documentExameTimeString=document.getDocumentExameTimeString();
        Date date = new Date();
        String newDocumentExameTimeString= DateUtil.format(date, "yyyy-MM-dd  HH:mm:ss")+'|';
        String newDocumentExamerString=userId+":"+userName+"-";
        if(documentExamerString==null){
            documentExamerString=newDocumentExamerString;
        }else {
            documentExamerString+=newDocumentExamerString;
        }
        if(documentExameTimeString==null){
            documentExameTimeString=newDocumentExameTimeString;
        }else {
            documentExameTimeString+=newDocumentExameTimeString;
        }
        Integer documentProcess=document.getDocumentProcess();
        documentProcess++;
        document.setDocumentStatue(3);
        document.setDocumentProcess(documentProcess);
        document.setDocumentExamerString(documentExamerString);
        document.setDocumentExameTimeString(documentExameTimeString);
        System.out.println("丁真");
        System.out.println(document);
        documentMapper.approveDocumentById(document);
    }

    @Override
    public Object getDocumentByUserToken(DocumentMyselfPageRequest documentMyselfPageRequest) {
        String token = documentMyselfPageRequest.getToken();
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        documentMyselfPageRequest.setUserId(userId);
        PageHelper.startPage(documentMyselfPageRequest.getPageNum(), 5);
        List<DocumentMyselfDTO> documents=documentMapper.getDocumentByUserToken(documentMyselfPageRequest);
        PageInfo<DocumentMyselfDTO> documentPageInfo=new PageInfo<>(documents);
        return documentPageInfo;
    }

    @Override
    public void delDocumentById(Integer documentId) {
        documentMapper.delDocumentById(documentId);
    }
}
