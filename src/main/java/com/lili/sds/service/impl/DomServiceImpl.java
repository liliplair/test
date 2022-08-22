package com.lili.sds.service.impl;

import com.lili.sds.bean.Dom;
import com.lili.sds.mapper.DomMapper;
import com.lili.sds.service.DomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomServiceImpl implements DomService {

    @Autowired
    private DomMapper domMapper;
    @Override
    public List<Dom> getAllDom() {
        return domMapper.selectAllDom();
    }

    @Override
    public Dom selectDomById(String id) {
        return domMapper.selectDomByDomId(id);
    }
}
