package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.DocumentClassRequest;
import com.example.springboot.controller.request.DocumentPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.entity.DocumentClass;
import com.example.springboot.entity.Role;
import com.example.springboot.service.IDocumentClassService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentClass")
public class DocumentClassController {

    @Autowired
    IDocumentClassService documentClassService;


    @PostMapping("/saveDocumentClass")
    public Result SaveDocumentClass(@RequestBody DocumentClass documentClass){
        documentClassService.saveDocumentClass(documentClass);
        return Result.success();
    }

    @GetMapping("/listDocumentClass")
    public Result listDocumentClass(){
        List<DocumentClass> documentClasses=documentClassService.listDocumentClass();
        return Result.success(documentClasses);
    }

    @GetMapping("/pageDocumentClass")
    public Result pageDocumentClass(DocumentClassRequest documentClassRequest){
        return Result.success(documentClassService.pageDocumentClass(documentClassRequest));
    }

    @GetMapping("/getDocumentClassById/{documentClassId}")
    public Result getDocumentClassById(@PathVariable Integer documentClassId){
        return Result.success(documentClassService.getDocumentClassById(documentClassId));
    }

    @PutMapping("/updateDocumentClassById")
    public Result updateDocumentClassById(@RequestBody DocumentClass documentClassForm){
        documentClassService.updateDocumentClassById(documentClassForm);
        return Result.success();
    }

//    @PostMapping("/addDocumentClass")
//    public Result AddDocumentClass(@RequestBody Object documentClassForm){
//        documentClassService.AddDocumentClass(documentClassForm);
//        return Result.success();
//    }

    @GetMapping("/getRightDetailByRoleId/{roleId}")
    public Result getRightDetailByRoleId(@PathVariable Integer roleId){
        return Result.success(documentClassService.getRightDetailByRoleId(roleId));
    }

    @DeleteMapping("/delDocumentClassById/{rolerelationId}")
    public Result delDocumentClassById(@PathVariable Integer documentClassId){
        documentClassService.delDocumentClassById(documentClassId);
        return Result.success();
    }

}

