package org.na.action;

import org.na.dao.AncientPrescriptionDao;
import org.na.entity.AncientPrescription;

import java.util.List;
import java.util.Map;

public class AncientPrescriptionAction {

    //添加女神
    public void add(AncientPrescription aP) throws Exception{
        AncientPrescriptionDao aPDao=new AncientPrescriptionDao();
        aP.setID(1);
        aP.setAncientUse("");
        aPDao.addAncientPrescription(aP);
    }

    //修改女神
//    public void edit(Goddess goddess) throws Exception{
//        GoddessDao dao=new GoddessDao();
//        dao.updateGoddess(goddess);
//    }
//
//    //删除女神
//    public void del(Integer id) throws Exception{
//        GoddessDao dao=new GoddessDao();
//        dao.delGoddess(id);
//    }
//
//    //查询单个女神信息(根据id)
//    public Goddess get(Integer id) throws Exception{
//        GoddessDao dao=new GoddessDao();
//        return dao.get(id);
//    }
//
//    //查询单个女神信息（根据姓名等信息）
//    public List<AncientPrescription> get(List<Map<String, Object>> params) throws Exception{
//        AncientPrescriptionDao aPDao=new AncientPrescriptionDao();
//        return aPDao.get(params);
//    }
}

