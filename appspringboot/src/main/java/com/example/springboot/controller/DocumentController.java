package com.example.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.DocumentMyselfPageRequest;
import com.example.springboot.controller.request.DocumentPageRequest;
import com.example.springboot.controller.request.GetDocumentExamineRequest;
import com.example.springboot.entity.Document;
import com.example.springboot.entity.DocumentClass;
import com.example.springboot.entity.User;
import com.example.springboot.service.impl.DocumentService;
import com.example.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    private static final String BASE_FilePath=System.getProperty("user.dir")+ "/files/";

    @GetMapping("/pageDocuments")
    public Result PageDocuments(DocumentPageRequest documentPageRequest){
        return Result.success(documentService.PageDocuments(documentPageRequest));
    }

    @PostMapping("/PostDocument")
    public Result PostDocument(@RequestBody Object document){
        documentService.PostDocument(document);
        return Result.success();
    }

    @DeleteMapping("/delDocumentById/{documentId}")
    public Result delDocumentById(@PathVariable Integer documentId){
        documentService.delDocumentById(documentId);
        return Result.success();
    }

    @GetMapping("/getDocumentExamine")
    public Result getDocumentExamine(GetDocumentExamineRequest getDocumentExamineRequest){
        return Result.success(documentService.getDocumentExamine(getDocumentExamineRequest));
    }

    @GetMapping("/getDocumentById/{id}")
    public Result getDocumentById(@PathVariable Integer id){
        return Result.success(documentService.getDocumentById(id));
    }

    @GetMapping("/getDocumentByUserToken")
    public Result getDocumentByUserToken(DocumentMyselfPageRequest documentMyselfPageRequest){
        return Result.success(documentService.getDocumentByUserToken(documentMyselfPageRequest));
    }

    @PutMapping("/documentApprove")
    public Result documentApprove(@RequestBody Object object){
        documentService.documentApprove(object);
        return Result.success();
    }

    @PutMapping("/documentReject")
    public Result documentReject(@RequestBody Object object){
        documentService.documentReject(object);
        return Result.success();
    }

    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartFile file){
        String originalFilename=file.getOriginalFilename();
        long flag=System.currentTimeMillis();
        String filePath=BASE_FilePath+flag+"-"+originalFilename;
        try{
            FileUtil.mkParentDirs(filePath);
            file.transferTo(FileUtil.file(filePath));
            Document document=new Document();
            document.setDocumentContent("http://localhost:8082/api/document/download/"+flag);
            document.setDocumentContentName(originalFilename);
            return Result.success(document);
        }catch (Exception e){
            log.error("文件上传失败");
        }
        return Result.error("文件上传失败");
    }

    @GetMapping("/download/{flag}")
    public void downloadFile(@PathVariable String flag, @RequestParam(required = false) String play , HttpServletResponse response) throws IOException {
        OutputStream os;
        List<String> fileNames=FileUtil.listFileNames(BASE_FilePath);
        String fileName=fileNames.stream().filter(name->name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(fileName)){
                String realName=fileName.substring(fileName.indexOf('-')+1);
                if("1".equals(play)){
                    response.addHeader("Content-Disposition","inline;filename="+ URLEncoder.encode(realName,"UTF-8"));
                }else{
                    response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(realName,"UTF-8"));
                }
                byte[] bytes=FileUtil.readBytes(BASE_FilePath+fileName);
                os =response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch(Exception e){
            log.error("文件下载失败");
        }
    }

}
