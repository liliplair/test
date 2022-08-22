package com.lili.sds.service;

import com.lili.sds.bean.Repair;

import java.util.List;

public interface RepairService {
    List<Repair> getAllRepair();
    int deleteById(String repairId);
    int addRepair(Repair repair);
    Repair getRepairById(String repairId);
    List<Repair> getRepairByDom(String repairDom);
}
