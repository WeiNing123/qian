package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.LearningMaterialsMapper;
import com.wn.qian.modules.main.model.LearningMaterials;
import com.wn.qian.modules.main.service.ILearningMaterialsService;
import org.springframework.stereotype.Service;

@Service
public class LearningMaterialsServiceImpl extends ServiceImpl<LearningMaterialsMapper, LearningMaterials> implements ILearningMaterialsService {
    @Override
    public QueryWrapper<LearningMaterials> getQueryWrapper(LearningMaterials materials) {
        QueryWrapper<LearningMaterials> wrapper = new QueryWrapper<>();
        wrapper.eq("category", materials.getCategory());
        wrapper.orderByAsc("id");
        return wrapper;
    }
}
