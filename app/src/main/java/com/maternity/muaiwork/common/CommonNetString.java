package com.maternity.muaiwork.common;

/**
 * Created by apple on 16/10/13.
 */

public class CommonNetString {
    public static final String DOMAIN="http://120.55.164.253:8080/";
            //"http://10.0.0.11:8080/";
//            "http://120.55.164.253:8080/";
    public static final String advUrl="http://120.55.164.253:8080/staticImg/zp.jpg";
    public static final String SAVESTATIC=DOMAIN+"staticImg/";
    public static final String IMAGEURL=SAVESTATIC+"photo/";
    public static final String MEDIUM=SAVESTATIC+"video/";
    public static final String baseUrl=DOMAIN+"Maternity/app/user/";//120.55.164.253
    public static final String WORK=DOMAIN+"Maternity/app/work/";       //工作记录
    public static final String TOPIC=SAVESTATIC+"topic/"; //文章
    public static final String ORERS=DOMAIN+"Maternity/app/order/"; //订单
    public static final String WORKORDER=DOMAIN+"Maternity/app/appworkorder/";//工单
    public static final String LEAVE=DOMAIN+"Maternity/app/appleave/";//请假

    public static final String maLogin=baseUrl+"maLogin";
    //    maRegist
    public static final String maRegist=baseUrl+"maRegist";
    //    userAddCertificate
    public static final String userAddCertificate=baseUrl+"userAddCertificate";
    //    userAddInsurance
    public static final String userAddInsurance=baseUrl+"userAddInsurance";
    //    userAddMedical
    public static final String userAddMedical=baseUrl+"userAddMedical";
    //    userAddMember
    public static final String userAddMember=baseUrl+"userAddMember";
    //    userAddServer
    public static final String userAddServer=baseUrl+"userAddServer";
    //    userAddTechnical
    public static final String userAddTechnical=baseUrl+"userAddTechnical";
    //    userAddTraining
    public static final String userAddTraining=baseUrl+"userAddTraining";
    //    userReadCertificate
    public static final String userReadCertificate=baseUrl+"userReadCertificate";
    //    userReadInfo
    public static final String userReadInfo=baseUrl+"userReadInfo";
    //            userReadInsurance
    public static final String userReadInsurance=baseUrl+"userReadInsurance";
    //    userReadMedical
    public static final String userReadMedical=baseUrl+"userReadMedical";
    //            userReadMember
    public static final String userReadMember=baseUrl+"userReadMember";
    //    userReadServer
    public static final String userReadServer=baseUrl+"userReadServer";
    //            userReadTechnical
    public static final String userReadTechnical=baseUrl+"userReadTechnical";
    //    userReadTraining
    public static final String userReadTraining=baseUrl+"userReadTraining";
    //            userUpdateCertificate
    public static final String userUpdateCertificate=baseUrl+"userUpdateCertificate";
    //    userUpdateInfo
    public static final String userUpdateInfo=baseUrl+"userUpdateInfo";
    //            userUpdateInsurance
    public static final String userUpdateInsurance=baseUrl+"userUpdateInsurance";
    //    userUpdateMedical
    public static final String userUpdateMedical=baseUrl+"userUpdateMedical";
    //            userUpdateMember
    public static final String userUpdateMember=baseUrl+"userUpdateMember";
    //    userUpdateServer
    public static final String userUpdateServer=baseUrl+"userUpdateServer";
    //            userUpdateTechnical
    public static final String userUpdateTechnical=baseUrl+"userUpdateTechnical";
    //    userUpdateTraining
    public static final String userUpdateTraining=baseUrl+"userUpdateTraining";
    //userAccountConfig
    public static final String userAccountConfig=baseUrl+"userAccountConfig";       //我的帐号信息
    //上传图片
    public static final String uploadImage=baseUrl+"uploadImage";
    //上传视频
    public static final String uploadVideo=baseUrl+"uploadVideo";
    public static final String paperBase=DOMAIN+"Maternity/app/paper/";
    public static final String paperInfo=paperBase+"getPaper";
    public static final String paperAsk=paperBase+"paperAnswerSubmit"; //提交答案
    public static final String getTopicList=TOPIC+"getTopicList";//获得文章列表

    /**
     * 订单接口
      */
    public static final String getOrderList=ORERS+"getOrderList"; //需求订单
    public static final String applyOrderNeeds=ORERS+"applyOrderNeeds"; //抢单

    /**
     * 工作记录接口
     */
    public static final String addRecorders=WORK+"addRecorders";//新增工作记录
    public static final String selectRecorders=WORK+"selectRecorders";//工作历史记录
    public static final String getRecordInfo=WORK+"getRecordInfo"; //工作选项
    public static final String selectRecordersChild=WORK+"selectRecordersChild";//宝宝工作选项
    public static final String selectRecordersMom=WORK+"selectRecordersMom";//妈妈工作选项
    public static final String selectHistoryMom=WORK+"selectHistoryMom"; //妈妈历史
    public static final String selectHistoryChild=WORK+"selectHistoryChild"; //宝宝历史

    /**
     * 工单接口
     */
    public static final String getAllWorkOder=WORKORDER+"getAllWorkOder";//当前工单
    public static final String getToolList=WORKORDER+"getToolList";//工具表
    public static final String getWorkOderList=WORKORDER+"getWorkOderList";//我的工单
    public static final String insertTool=WORKORDER+"insertTool";//工具认领
    public static final String PlaceOrder=WORKORDER+"PlaceOrder";//下单
    public static final String Single=WORKORDER+"Single";//上单
    //工具归还



    /**
     * 请假
     */
    public static final String leaveList=LEAVE+"leaveList";//请假记录
    public static final String leaveRequest=LEAVE+"leaveRequest";//请假发起




}
