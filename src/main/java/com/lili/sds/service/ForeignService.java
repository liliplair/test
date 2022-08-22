package com.lili.sds.service;

import com.lili.sds.bean.Foreign;

import java.util.List;

/**
 * @ClassName ForeignService
 * @Deacription TODO
 * @Author daier
 * @Date 2021/1/6 1:14
 * @Version 1.0
 **/
public interface ForeignService {

    List<Foreign> getAllForeign();

    int addForeign(Foreign foreign);

    int deleteForeign(String foreignId);

    Foreign selectById(String foreignId);
}
