package com.lili.sds.service;

import com.lili.sds.bean.Dom;

import java.util.List;

public interface DomService {

    List<Dom> getAllDom();
    Dom selectDomById(String id);
}
