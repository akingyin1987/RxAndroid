package rx.android.samples.http;

/**
 * 
 * @Description: TODO(与服务器请求URL或方法)
 * @author king
 * @date 2014-8-27 下午12:42:34
 * @version V1.0
 */
public class HttpRequestUrlConfig {

	public static final String CONNECT_URL = "/terminal/validateConfig.action";// 连接测试

	public static final String LOGIN_URL = "/terminal/login.action";// 登录连接

	public static final String LOGINOFF_URL = "/terminal/logout.action";// 注销

	public static final String CHANGE_PASSWORD_URL = "/terminal/updatePassword.action";// 修改密码

	public static final String UPLOAD_LOCATION_URL = "/terminal/uploadLocation.action";// 上传经纬度
	
	public static final String SELECT_PROJECT_URL="/terminal/project.action";//选择项目 
	
	public static final String SERVER_TIME_URL="/";//获取服务器时间
	
	
	
	
	
	public static final String UPLOAD_FILE_URL="/terminal/uploadImageVideo.action";//上传文件

	
	public static  final String UPLOAD_VALFILE_URL="/terminal/validateImageVideo.action";//验证文件
	

	//下载任务
	public static  final  String  DOWNLOAD_TASK="/terminal/downloadInspectJob.action";
	
	//下载任务数据
	public static  final  String  DOWNLOAD_TASK_DATA="/terminal/downloadInspectData.action";
	
	//上传聊天信息
	public static  final  String  UPLOAD_CHARTMESSAGE_DATA="/terminal/uploadInformation.action";
	
	//下载聊天
	public static  final  String  DOWNLOAD_CHARTMESSAGE_DATA="/terminal/queryInformation.action";

	//任务转发
	public static  final  String  SUBMIT_FORWARD_TASK="/terminal/applyForward.action";
	
	//任务完成
	public static  final  String  SUBMIT_COMPLETE_TASK="/terminal/applyFinish.action";
	
	//上传巡检数据
	public static  final  String  UPLOAD_INSPECT_DATA="/terminal/uploadInspectJob.action";
	
	//上传管线巡查数据
	public static  final  String  UPLOAD_PATROL_DATA="";
	
	//上传工单信息
	public  static   final   String   UPLOAD_ORDERINFO_DATA="/terminal/uploadReportInfo.action";
	
	//下载工单定额数据
	public  static   final   String   DOWNLOAD_ORDER_QUOTA = "";//
	
	//下载工单任务数据
	public  static   final   String   DOWNLOAD_ORDERTASK_DATA = "/terminal/downloadReportJob.action";//
	
	//工单位任务数据上传
	public  static   final   String   UPLOAD_ORDERTASK_DATA="/terminal/uploadSceneJob.action";
	
	//下载工单基础数据
	public  static   final   String   DOWNLOAD_BASEORDER_DATA="/terminal/downloadReportTypeQuota.action";//
	
	//上传维修数据
	public  static   final   String   UPLOAD_ORDERREPAIR_DATA="/terminal/uploadRepairJob.action";
	
	//查询任务状态
	public  static   final   String   Query_JOBSTATUS="/terminal/queryJobStatus.action";
	
	//是否有新任务
	public  static   final   String   QUERY_NEW_JOBS="/terminal/queryNewJob";
	
	
}
