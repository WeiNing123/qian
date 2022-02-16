package com.wn.qian.modules.main.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wn.qian.framework.base.BaseModel;
import lombok.Data;

@TableName("learning_materials")
@Data
public class LearningMaterials extends BaseModel {

    //标题
    private String title;
    //路径
    private String path;
    //类别
    private String category;
    //类型 1上传文件 2外部路径
    private Integer type;
}
