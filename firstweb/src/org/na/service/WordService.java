package org.na.service;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.na.dao.*;
import org.na.dto.ImportWordParamDto;
import org.na.dto.ImportWordResultDto;
import org.na.entity.*;
import org.na.util.DbUtil;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class WordService {
    Map<String, String> corMap = new HashMap<String, String>();
    public ImportWordResultDto imp(ImportWordParamDto dto){
        ImportWordResultDto result = new ImportWordResultDto();
        result.setTitle(dto.getTitle());
        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(dto.getWord().getInputStream());
            result.setContent(doc.getDocumentText().replace("\r","<br/>"));
        } catch(OfficeXmlFileException oe){
            System.out.println("这可能是一个07版的word");
        } catch (Exception e) {
//            System.out.println("这可能不是一个word");
            e.printStackTrace();
            result.setMsg("这可能不是一个word");
        }finally {
            if(doc != null){
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //07版的word
        XWPFDocument docx = null;

        try {
            docx = new XWPFDocument(dto.getWord().getInputStream());

            StringBuilder content = new StringBuilder();
            List<XWPFParagraph> paragraphList = docx.getParagraphs();
            for(int i = 0; i < paragraphList.size(); i++){
                if(i != 0){
                    content.append("<br/>");
                }
//                System.out.println("content: " + content);
                content.append(paragraphList.get(i).getText());
            }
            result.setContent(content.toString());

            String cont = content.toString();
            ArrayList<String> fullLinesList = new ArrayList<String>();
            ArrayList<String> labelList = new ArrayList<String>();
            Map<String, String> descMap = new HashMap<String, String>();
            Map<String, String> lableMap = new HashMap<String, String>();
            Map<String, String> tableMap = new HashMap<String, String>();

            String objs[] = cont.split("【");
            for(String obj : objs){
                fullLinesList.add(obj);
            }
            for(int a = 1; a < fullLinesList.size(); a++){
                String line = fullLinesList.get(a);
                String desc[] = line.split("】");
                desc[1] = desc[1].replace("<br/>","");
                descMap.put(desc[0], desc[1]);
            }
            Iterator<Map.Entry<String, String>> entries = descMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
//                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                String label = entry.getKey();
                String desc = entry.getValue();
                switch (label){
                    case "方名":
                        lableMap.put("方名", "presName");
                        tableMap.put("presName", desc);
                        corMap.put("方名", desc);
                        break;
                    case "别名":
                        lableMap.put("别名", "alias");
                        tableMap.put("alias", desc);
                        corMap.put("别名", desc);
                        break;
                    case "拼音":
                        lableMap.put("拼音", "pinYin");
                        tableMap.put("pinYin", desc);
                        corMap.put("拼音", desc);
                        break;
                    case "英文":
                        lableMap.put("英文", "englishName");
                        tableMap.put("englishName", desc);
                        corMap.put("英文", desc);
                        break;
                    case "民族":
                        lableMap.put("民族", "nation");
                        tableMap.put("nation", desc);
                        corMap.put("民族", desc);
                        break;
                    case "分类":
                        lableMap.put("分类", "classify");
                        tableMap.put("classify", desc);
                        corMap.put("分类", desc);
                        break;
                    case "归经":
                        lableMap.put("归经", "tropism");
                        tableMap.put("tropism", desc);
                        corMap.put("归经", desc);

                        break;
                    case "来源":
                        lableMap.put("来源", "source");
                        tableMap.put("source", desc);
                        corMap.put("来源", desc);
                        break;
                    case "名方":
                        lableMap.put("名方", "famous");
                        tableMap.put("famous", desc);
                        corMap.put("名方", desc);
                        break;
                    case "制方原理":
                        lableMap.put("制方原理", "principle");
                        tableMap.put("principle", desc);
                        corMap.put("制方原理", desc);
                        break;
                    case "用法":
                        lableMap.put("用法", "useway");
                        tableMap.put("useway", desc);
                        corMap.put("用法", desc);
                        break;
                    case "功效":
                        lableMap.put("功效", "effects");
                        tableMap.put("effects", desc);
                        corMap.put("功效", desc);
                        break;
                    case "主治":
                        lableMap.put("主治", "indications");
                        tableMap.put("indications", desc);
                        corMap.put("主治", desc);
                        break;
                    case "注意":
                        lableMap.put("注意", "attention");
                        tableMap.put("attention", desc);
                        corMap.put("注意", desc);
                        break;
                    case "规格":
                        lableMap.put("规格", "size");
                        tableMap.put("size", desc);
                        corMap.put("规格", desc);
                        break;
                    case "贮藏":
                        lableMap.put("贮藏", "storage");
                        tableMap.put("storage", desc);
                        corMap.put("贮藏", desc);
                        break;
                    case "性味":
                        lableMap.put("性味", "smell");
                        tableMap.put("smell", desc);
                        corMap.put("性味", desc);
                        break;
                    case "歌诀":
                        lableMap.put("歌诀", "song");
                        tableMap.put("song", desc);
                        corMap.put("歌诀", desc);
                        break;
                    case "古方用法":
                        lableMap.put("古方用法", "ancient_prescription");
                        tableMap.put("ancient_prescription", desc);
                        corMap.put("古方用法", desc);
                        break;
                    case "古方组成":
                        lableMap.put("古方组成", "ancient_prescriptioncomponent");
                        tableMap.put("ancient_prescriptioncomponent", desc);
                        corMap.put("古方组成", desc);
                        break;
                    case "古方主治":
                        lableMap.put("古方主治", "ancient_prescriptiontreats");
                        tableMap.put("ancient_prescriptiontreats", desc);
                        corMap.put("古方主治", desc);
                        break;
                    case "臣药":
                        lableMap.put("臣药", "chencomponent");
                        tableMap.put("chencomponent", desc);
                        corMap.put("臣药", desc);
                        break;
                    case "君药":
                        lableMap.put("君药", "juncomponent");
                        tableMap.put("juncomponent", desc);
                        corMap.put("君药", desc);
                        break;
                    case "现代研究":
                        lableMap.put("现代研究", "modern_study");
                        tableMap.put("modern_study", desc);
                        corMap.put("现代研究", desc);
                        break;
                    case "方论":
                        lableMap.put("方论", "prescriptionarguments");
                        tableMap.put("prescriptionarguments", desc);
                        corMap.put("方论", desc);
                        break;
                    case "方义":
                        lableMap.put("方义", "prescriptionmeans");
                        tableMap.put("prescriptionmeans", desc);
                        corMap.put("方义", desc);
                        break;
                    case "使药":
                        lableMap.put("使药", "shicomponent");
                        tableMap.put("shicomponent", desc);
                        corMap.put("使药", desc);
                        break;
                    case "佐药":
                        lableMap.put("佐药", "zuocomponent");
                        tableMap.put("zuocomponent", desc);
                        corMap.put("佐药", desc);
                        break;
                    case "佐使药":
                        lableMap.put("佐使药", "zuoshicomponent");
                        tableMap.put("zuoshicomponent", desc);
                        corMap.put("佐使药", desc);
                        break;
                    case "方剂衍化":
                        lableMap.put("方剂衍化", "derivation");
                        tableMap.put("derivation", desc);
                        corMap.put("方剂衍化", desc);
                        break;
                    case "临床应用":
                        lableMap.put("临床应用", "clinic_application");
                        tableMap.put("clinic_application", desc);
                        corMap.put("临床应用", desc);
                        break;
                    case "组成":
                        lableMap.put("组成", "component");
                        tableMap.put("component", desc);
                        corMap.put("组成", desc);
                        break;
                    default:
                        System.out.println("没有该字段！");
                        break;
                }
            }
//            一个tableMap存放：<clinic_application, '腰酸背疼、下肢酸痛'>;
//            一个lableMap存放：<'临床应用'，'clinic_application'>;
//            clinic_application = labelMap.get('临床应用');
//            insert into medicine(clinic_application) values(tableMap.get(clinic_application));

            //数据库操作
//            Connection conn = DbUtil.getConnection();
//            Statement stmt = conn.createStatement();
//            Statement stmtPIDs = conn.createStatement();
//            Statement stmtAPIDs = conn.createStatement();
//            Statement stmtAPC = conn.createStatement();

//            int rs = 0;
//            sql = "insert into twoPart(title, time, ) values (" + "'"+result.getTitle() + "'," + "'"+label + "'," + "'"+desc+"'" +")";
//            sqlAncient_prescription = "insert into ancient_prescription(ancient_use) values (" + "'" + corMap.get("古方用法") +"'" +");";//古方用法
//            stmt.execute(sqlAncient_prescription);

//            Prescription pres = new Prescription();

            //古方用法 ancient_prescrition
            AncientPrescription aP = new AncientPrescription();
            AncientPrescriptionDao aPDao=new AncientPrescriptionDao();
            aP.setAncientUse(corMap.get("古方用法"));
            aPDao.addAncientPrescription(aP);
            Integer aPID = aPDao.query().getID();
            System.out.println("aPID = " + aPID);
            //简单字段 prescription
            Prescription p = new Prescription();
            PrescriptionDao pDao = new PrescriptionDao();
            p.setOpTitle(result.getTitle());
            p.setPresName(corMap.get("方名"));
            p.setOtherNames(corMap.get("别名"));
            p.setPinYin(corMap.get("拼音"));
            p.setEnglishName(corMap.get("英文"));
            p.setNation(corMap.get("民族"));
            p.setClassify(corMap.get("分类"));
            p.setTropism(corMap.get("归经"));
            p.setSource(corMap.get("来源"));
            p.setFamous(corMap.get("名方"));
            p.setPrinciple(corMap.get("制方原理"));
            p.setUseway(corMap.get("用法"));
            p.setEffects(corMap.get("功效"));
            p.setIndications(corMap.get("主治"));
            p.setAttention(corMap.get("注意"));
            p.setSize(corMap.get("规格"));
            p.setReposit(corMap.get("贮藏"));
            p.setSmell(corMap.get("性味"));
            p.setSong(corMap.get("歌诀"));
            p.setAncientID(aPID);
            pDao.addPrescription(p);
            Integer pID = pDao.query().getID();
            System.out.println("pID = " + pID);

            //古方组成 ancient_prescriptioncomponent
            //切分
            Map<String, String> pDMap = new HashMap<String, String>();
            String ancient_prescriptioncomponent = corMap.get("古方组成");
            ancient_prescriptioncomponent = ancient_prescriptioncomponent.replace("。","");
            String desc[] = ancient_prescriptioncomponent.split("、");
            for(String plantDosage : desc){
                plantDosage = plantDosage.replace("）","");
                String pD[] = plantDosage.split("（");
                pDMap.put(pD[0],pD[pD.length-1]);
            }//杜仲做法没取

            AncientPrescriptionComponent APC = new AncientPrescriptionComponent();
            AncientPrescriptionComponentDao APCDao = new AncientPrescriptionComponentDao();
            //存入数据库
            Iterator<Map.Entry<String, String>> APEntries = pDMap.entrySet().iterator();
            while (APEntries.hasNext()) {
                Map.Entry<String, String> APEntry = APEntries.next();
                String plantName = APEntry.getKey();
                String dosage = APEntry.getValue();
                AncientPrescriptionComponentEles APCEles = new AncientPrescriptionComponentEles();
                APCEles.setPlantName(plantName);
                APCEles.setDosage(dosage);
                APC.setAncientPrescriptionID(aPID);
                APC.setAPCEles(APCEles);
                APCDao.addAncientPrescriptionComponent(APC);
            }

            //方论 prescriptionarguments
            PrescriptionArguments PA = new PrescriptionArguments();
            PrescriptionArgumentDao PADao = new PrescriptionArgumentDao();
            PA.setArgument(corMap.get("方论"));
            PA.setPrescritionID(pID);
            PADao.addPrescritionArgumrnts(PA);

            //君臣佐使 juncomponent chencomponent zuocomponent shicomponent 佐使药字段没处理
            ArrayList<String> junComs = splitJCZSComponent("君药");
            if(0 != junComs.size()){
                JunComponent JC = new JunComponent();
                JunComponentDao JCDao = new JunComponentDao();
                for(int i = 0 ; i < junComs.size() ; i++){
//                System.out.println("junComs[" + i + "]" + junComs[i]);
                    JC.setPlantName(junComs.get(i));
                    JC.setPrescriptionID(pID);
                    JCDao.addJunComponent(JC);
                }
            }

            ArrayList<String> chenComs = splitJCZSComponent("臣药");
            if(0 != chenComs.size()){
                ChenComponent CC = new ChenComponent();
                ChenComponentDao CCDao = new ChenComponentDao();
                for(int i = 0 ; i < chenComs.size() ; i++){
//                System.out.println("chenComs[" + i + "]" + chenComs[i]);
                    CC.setPlantName(chenComs.get(i));
                    CC.setPrescriptionID(pID);
                    CCDao.addChenComponent(CC);
                }
            }

            ArrayList<String> zuoComs = splitJCZSComponent("佐药");
            if (0 != zuoComs.size()) {
                ZuoComponent ZC = new ZuoComponent();
                ZuoComponentDao ZCDao = new ZuoComponentDao();
                for(int i = 0 ; i < zuoComs.size() ; i++){
//                System.out.println("zuoComs[" + i + "]" + zuoComs[i]);
                    ZC.setPlantName(zuoComs.get(i));
                    ZC.setPrescriptionID(pID);
                    ZCDao.addZuoComponent(ZC);
                }
            }

            ArrayList<String> shiComs = splitJCZSComponent("使药");
            if(0 != shiComs.size()){
                ShiComponent SC = new ShiComponent();
                ShiComponentDao SCDao = new ShiComponentDao();
                for(int i = 0 ; i < shiComs.size() ; i++){
//                System.out.println("shiComs[" + i + "]" + shiComs[i]);
                    SC.setPlantName(shiComs.get(i));
                    SC.setPrescriptionID(pID);
                    SCDao.addShiComponent(SC);
                }
            }

            //现代研究
            List<String> modernStudyList = new ArrayList<String>();
            String modernStudy = corMap.get("现代研究");
            modernStudy = modernStudy.replace("(","");
            String studies[] = modernStudy.split("。");
            for(String study : studies){
                String tmp[] = study.split("）");
                String studyCont = tmp[tmp.length-1];
                System.out.println("studyCont = " + studyCont);
                modernStudyList.add(studyCont);
            }
            ModernStudy MS = new ModernStudy();
            ModernStudyDao MSDao = new ModernStudyDao();
            for(int i = 0; i < modernStudyList.size(); i++){
                MS.setStudy(modernStudyList.get(i));
                MS.setPrescriptionID(pID);
                MSDao.addModernStudy(MS);
            }

            //方义
            List<String> prescriptionMeansList = new ArrayList<String>();
            String prescriptionMeans = corMap.get("方义");
            prescriptionMeans = prescriptionMeans.replace("(","");
            String means[] = prescriptionMeans.split("。");
            for(String mean : means){
                String tmp[] = mean.split("）");
                String meanCont = tmp[tmp.length-1];
                System.out.println("meanCont = " + meanCont);
                prescriptionMeansList.add(meanCont);
            }
            PrescriptionMeans PM = new PrescriptionMeans();
            PrescriptionMeansDao PMDao = new PrescriptionMeansDao();
            for(int i = 0; i < prescriptionMeansList.size(); i++){
                PM.setMeaning(prescriptionMeansList.get(i));
                PM.setPrescriptionID(pID);
                PMDao.addPrescriptionMeans(PM);
            }

            //临床应用 clinic_application
            ClinicApplication CA = new ClinicApplication();
            ClinicApplicationDao CADao = new ClinicApplicationDao();
            String clinicApplication = corMap.get("临床应用");
            clinicApplication = clinicApplication.replace("。","");
            String clinicApps[] = clinicApplication.split("、");
            for(String clinicApp : clinicApps){
                CA.setClinicUse(clinicApp);
                CA.setPrescriptionID(pID);
                CADao.addClinicApplication(CA);
            }

            //古方主治---句号切分有问题
            AncientPrescriptionTreats APT = new AncientPrescriptionTreats();
            AncientPrescriptionTreatsDao APTDao = new AncientPrescriptionTreatsDao();
            String ancientPrescriptionTreats = corMap.get("古方主治");
            String treats[] = ancientPrescriptionTreats.split("。");
            for(String treat : treats){
                APT.setTreatName(treat);
                APT.setAncientPrescriptionID(aPID);
                APTDao.addAncientPrescriptionTreats(APT);
            }

            //方剂衍化
            Map<String, String> pDeriMap = new HashMap<>();
            PrescriptionDerivation pD = new PrescriptionDerivation();
            PrescriptionDerivationDao pDDao = new PrescriptionDerivationDao();
            DerivationChange DC = new DerivationChange();
            DerivationChangeDao DCDao = new DerivationChangeDao();
            String derivation = corMap.get("方剂衍化");
            System.out.println("derivation = " + derivation);
            String pres[] = derivation.split("。");
            for(String pre:pres){
                System.out.println("pre = " + pre);
                String tmps[] = pre.split("：");
                System.out.println("tmps[0] = " + tmps[0]);
                System.out.println("tmps[1] = " + tmps[1]);
                pDeriMap.put(tmps[0], tmps[1]);
                pD.setName(tmps[0]);
                pD.setContent(tmps[1]);
                pD.setPrescriptionID(pID);
                pDDao.addPrescriptionDerivation(pD);
                Integer dID = pDDao.query().getID();
                System.out.println("dID = " + dID);


                List<String> changesList = new ArrayList<>();
                if(tmps[1].contains("，")){
                    String tmpChanges[] = tmps[1].split("，");
                    for(String tmpChange : tmpChanges){
                        System.out.println("tmpChange = " + tmpChange);
                        changesList.add(tmpChange);
                    }
                }
                else{
                    changesList.add(tmps[1]);
                }


                for(String change:changesList){
                    System.out.println("change = " + change);
                    if(change.contains("去")&&change.contains("、")){
                        change = change.replace("去", "");
                        String ingres[] = change.split("、");
                        for(String ingre:ingres){
                            System.out.println("ingre = "+ingre);
                            DC.setPlantName(ingre);
                            DC.setChangeFlag(0);
                            DC.setDerivationID(dID);
                            DCDao.addDerivationChange(DC);
                        }

                    }
                    if(change.contains("去")&&((-1) == change.indexOf("、"))){
                        change = change.replace("去", "");
                        System.out.println("change = " + change);
                        DC.setPlantName(change);
                        DC.setChangeFlag(0);
                        DC.setDerivationID(dID);
                        DCDao.addDerivationChange(DC);
                    }
                    if(change.contains("加")&&change.contains("、")){
                        change = change.replace("加", "");
                        String ingres[] = change.split("、");
                        for(String ingre:ingres){
                            System.out.println("ingre = "+ingre);
                            DC.setPlantName(ingre);
                            DC.setChangeFlag(1);
                            DC.setDerivationID(dID);
                            DCDao.addDerivationChange(DC);
                        }
                    }
                    if(change.contains("加")&&((-1) == change.indexOf("、"))){
                        change = change.replace("加", "");
                        System.out.println("change = " + change);
                        DC.setPlantName(change);
                        DC.setChangeFlag(1);
                        DC.setDerivationID(dID);
                        DCDao.addDerivationChange(DC);
                    }
                }

            }



            Connection conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ancient_prescription where ID = 1");
            rs.close();
            stmt.close();
            conn.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(docx != null){
                try {
                    docx.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    public void str2Part(String cont, String reg,String otherSymble){
        String desc[] = cont.split(reg);
        desc[desc.length-1] = desc[desc.length-1].replace(otherSymble,"");
//        descMap.put(desc[0], desc[1]);//好像不行

    }
    public ArrayList splitJCZSComponent(String nameJCZS){
        //君臣佐使
        String components = corMap.get(nameJCZS);
        List<String> comsList = new ArrayList<>();
        if(null != components)
        {
            System.out.println("corMap.get(nameJCZS) = " + components);
            components = components.replace("。","");
            String coms[] = components.split("、");
            for(int i = 0; i < coms.length ; i++){
                comsList.add(coms[i]);
            }
        }
        return (ArrayList) comsList;
    }
}
