package com.summer.api;

import com.summer.common.Response;
import com.summer.entity.dto.FileDto;
import com.summer.enums.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tubo
 * @date 2022/05/28
 */
@RestController
@RequestMapping("file")
@Api(value = "FileUploadApi", tags = "文件上传")
public class FileUploadApi {

    @Value("${upload.file}")
    private String uploadFile;


    @Value("${upload.url}")
    private String uploadUrl;

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/upload")
    public Response FileUpload(@RequestParam(value="myfile") MultipartFile[] myfile) throws IllegalStateException, IOException {
        //存储图片路径
        List<FileDto> path = new ArrayList<>();
        if (myfile == null) {
            return Response.rspMsg(ResponseEnum.FILED_FAILED);
        }
        for (MultipartFile multipartFile : myfile) {
            if(!multipartFile.isEmpty()){
                FileDto fileDto = new FileDto();
                String oldName = multipartFile.getOriginalFilename();

                int suffix = oldName.lastIndexOf(".");

                String Suffix = oldName.substring(suffix);
                if(Suffix.equals(".apk")){
                    setUploadPrefix(path, multipartFile, fileDto, oldName);
                    //return R.ok().success(path);
                    return Response.rspData(path);
                }

                String newName = System.currentTimeMillis()+oldName.substring(suffix);

                setUploadPrefix(path, multipartFile, fileDto, newName);
            }
        }
        return Response.rspData(path);
    }

    private void setUploadPrefix(List<FileDto> path, MultipartFile multipartFile, FileDto fileDto, String name) throws IOException {
        File file = new File(uploadFile, name);

        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        if(file.isFile()){
            file.createNewFile();
        }
        multipartFile.transferTo(file);

        fileDto.setFileName(name);
        fileDto.setFileUrl(uploadUrl + "/file/" + name);
        path.add(fileDto);
    }
}
