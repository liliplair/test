package com.lili.sds.service.impl;

import com.lili.sds.bean.Admin;
import com.lili.sds.mapper.AdminMapper;
import com.lili.sds.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(String AdminId, String AdminPass)
    {
        return adminMapper.selectAdminByIdAndPass(AdminId,AdminPass);
    }
}
