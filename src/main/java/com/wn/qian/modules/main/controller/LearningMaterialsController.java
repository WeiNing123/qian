package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.LearningMaterials;
import com.wn.qian.modules.main.service.ILearningMaterialsService;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/main/materials")
public class LearningMaterialsController extends BaseController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private ILearningMaterialsService learningMaterialsService;

    @PostMapping("/list")
    public IPage<LearningMaterials> list(@RequestBody LearningMaterials learningMaterials){
        learningMaterials.setCreater((String) getRedisUtil().get("user_code"));
        Page<LearningMaterials> page = new Page<>(learningMaterials.getPage(), learningMaterials.getLimit());
        return learningMaterialsService.page(page, learningMaterialsService.getQueryWrapper(learningMaterials));
    }

    @PostMapping("/add")
    public R add(@RequestBody LearningMaterials learningMaterials){
        learningMaterials.setCreater((String) getRedisUtil().get("user_code"));
        learningMaterialsService.save(learningMaterials);
        return R.ok();
    }

    @PostMapping("/edit")
    public R edit(@RequestBody LearningMaterials learningMaterials){
        learningMaterialsService.updateById(learningMaterials);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody LearningMaterials learningMaterials){
        File file = new File(uploadFolder + File.separator + learningMaterials.getPath().replaceAll("/upfile/qian/", ""));
        if (file.exists()) {
            file.delete();
        }
        learningMaterialsService.removeById(learningMaterials.getId());
        return R.ok();
    }

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file){
        File dir = new File(uploadFolder);
        if(!dir.exists()){
            dir.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

        String newFileName = UUID.randomUUID() + "." + fileSuffix;
        File dest = new File(uploadFolder + File.separator + newFileName);
        try {
            file.transferTo(dest);
            return R.ok().put("path", newFileName);
        } catch (IOException e) {
            return R.error();
        }
    }

    @PostMapping("/file")
    public R file(@RequestBody LearningMaterials learningMaterials) throws IOException{
        File file = new File(uploadFolder + File.separator + learningMaterials.getPath().replaceAll("/upfile/qian/", ""));
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String data = null;
        String content = "";
        while((data = br.readLine()) != null) {
            content += data + "%##%";
        }
        br.close();
        isr.close();
        fis.close();
        return R.ok().put("content", content);
    }
}
