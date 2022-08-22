package com.lili.sds.service;

import com.lili.sds.bean.Admin;

public interface AdminService  {

    Admin adminLogin(String AdminId, String AdminPass);
}
