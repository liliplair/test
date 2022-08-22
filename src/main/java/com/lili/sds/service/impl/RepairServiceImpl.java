package com.lili.sds.service.impl;

import com.lili.sds.bean.Repair;
import com.lili.sds.mapper.RepairMapper;
import com.lili.sds.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;

    @Override
    public List<Repair> getAllRepair() {
        return repairMapper.selectAllRepair();
    }

    @Override
    public int addRepair(Repair repair) {
        return repairMapper.insertRepair(repair);
    }

    @Override
    public Repair getRepairById(String repairId) {
        return repairMapper.selectRepairById(repairId);
    }

    @Override
    public List<Repair> getRepairByDom(String repairDom) {
        return repairMapper.selectRepairByDom(repairDom);
    }

    @Override
    public int deleteById(String repairId) {
        return repairMapper.deleteRepairById(repairId);
    }


}
