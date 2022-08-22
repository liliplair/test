package com.lili.sds.service.impl;

import com.lili.sds.bean.Foreign;
import com.lili.sds.mapper.ForeignMapper;
import com.lili.sds.service.ForeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ForeignServiceImpl implements ForeignService {

    @Autowired
    private ForeignMapper foreignMapper;

    @Override
    public List<Foreign> getAllForeign() {
        return foreignMapper.selectAllForeign();
    }

    @Override
    public int addForeign(Foreign foreign) {
        return foreignMapper.insertForeign(foreign);
    }

    @Override
    public int deleteForeign(String foreignId) {
        return foreignMapper.deleteForeignById(foreignId);
    }

    @Override
    public Foreign selectById(String foreignId) {
        return foreignMapper.selectForeignById(foreignId);
    }
}
