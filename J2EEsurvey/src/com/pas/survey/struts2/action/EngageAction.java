package com.pas.survey.struts2.action;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.datasource.SurveyToken;
import com.pas.survey.model.Answer;
import com.pas.survey.model.Page;
import com.pas.survey.model.Survey;
import com.pas.survey.service.SurveyService;
import com.pas.survey.util.StringUtil;

/**
 * 参与调查Action
 * @author pingansheng
 *
 */
@Controller("engageaction")
@Scope("prototype")
public class EngageAction extends BaseAction<Survey> implements SessionAware,ParameterAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 121733788026529297L;

	private static final String ALL_ANSWER_MAP = "all_answer_map";

	private static final String CURRENT_SURVEY = "currentSurvey";

	@Resource(name = "surveyService")
	private SurveyService surveyService;

	// 参与调查的sid
	private Integer sid;

	// 接收所有开放的Survey
	private List<Survey> openedSurveys;
	// 接收要参与的调查的当前页面
	private Page currentPage;
	// 接收参与的调查
	private Survey survey;
	// 接收Session
	private Map<String, Object> sessionMap;
	
	// 接收提交的所有参数
	private Map<String, String[]> postedParameters;

	//当前页面的ID
	private Integer currentPid;
	
	public Integer getCurrentPid() {
		return currentPid;
	}

	public void setCurrentPid(Integer currentPid) {
		this.currentPid = currentPid;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getOpenedSurveys() {
		return openedSurveys;
	}

	public void setOpenedSurveys(List<Survey> openedSurveys) {
		this.openedSurveys = openedSurveys;
	}

	/**
	 * 查询所有开放的调查
	 * 
	 * @return
	 */
	public String findAllOpenedSurveys() {
		this.openedSurveys = surveyService.getAllOpenedSurveys();
		return "engageSurveyListPage";
	}

	/**
	 * 参与调查
	 * 
	 * @return
	 */
	public String entry() {
		if (sid != null) {
			this.survey = surveyService.getSuvey(sid);
			if(this.survey.getClosed()==true){
				return ERROR;
			}
			//存入Session以便输出标题且避免多次查询
			sessionMap.put(CURRENT_SURVEY, this.survey);
			this.currentPage = surveyService.getFirstPage(sid);
			//存放所有参数的大map存放入Session中Map<页面ID,Map<题号,答案字符串>>
			sessionMap.put(ALL_ANSWER_MAP, new HashMap<Integer, Map<String,String>>());
			return "engageSurveyPage";
		}
		return ERROR;
	}

	/**
	 * 提交
	 * @return 
	 */
	public String doEnageSurvey(){
		if(getAllParameters()==null){
			return ERROR;
		}
		//首先取到提交按钮的名称
		//迭代所有参数
		String submitName=getSubmitName();
		if("submit_pre".equals(submitName)){
			mergeAnswersIntoSession();
			this.currentPage=surveyService.getPrePage(currentPid);
			return "engageSurveyPage";
		}
		else if("submit_next".equals(submitName)){
			mergeAnswersIntoSession();
			this.currentPage=surveyService.getNextPage(currentPid);
			return "engageSurveyPage";
			
		}else if("submit_done".equals(submitName)){
			mergeAnswersIntoSession();
			
			//绑定token到当前线程
			SurveyToken token=new SurveyToken();
			token.setSurvey(getCurrentSurvey());
			SurveyToken.bindToken(token);;
			
			surveyService.saveAnswers(storeAnswers());
			clearAnswerSession();
			return "engageSurveyComplete";
			
		}else if("submit_exit".equals(submitName)){
			clearAnswerSession();
			return "engageSurveyAction";
		}
		
		return ERROR;
	}
	
	/**
	 * 处理答案存储
	 */
	private List<Answer> storeAnswers() {
		//矩阵单选HashMap
		Map<Integer,String> matrixRadioMap=new HashMap<Integer, String>();
		//所有答案集合
		List<Answer> answers=new ArrayList<Answer>();
		Answer ans=null;
		String key=null;
		String[] value=null;
		//所有的参数
		Map<Integer,Map<String,String[]>> allMap=getAllParameters();
		for(Map<String,String[]> pageMap:allMap.values()){
			for(Entry<String, String[]> answerEntry:pageMap.entrySet()){
				//此处取出的是<题号,取值>对，<q12,"1,2,3">
				key=answerEntry.getKey();
				value=answerEntry.getValue();
				//处理所有q开头
				if(key.startsWith("q")){
					//常规参数 不含other和下划线
					if(!key.contains("other")&&!key.contains("_")){
						ans=new Answer();
						ans.setAnswer(StringUtil.arrTostr(value));
						ans.setQuestionid(getQid(key));
						ans.setSurveyid(getCurrentSurvey().getId());
						ans.setOtherAnswer(StringUtil.arrTostr(pageMap.get(key+"other")));
						answers.add(ans);
					}else if(key.contains("_"))//矩阵单选按钮
					{
						Integer radioQid=getRadioQid(key);
						String oldvalue=matrixRadioMap.get(radioQid);
						if(oldvalue==null){
							matrixRadioMap.put(radioQid, StringUtil.arrTostr(value));
						}else{
							matrixRadioMap.put(radioQid, oldvalue+","+StringUtil.arrTostr(value));
						}
					}
				}
			}
		}
		
		storeMatrixRadiomap(matrixRadioMap,answers);
		return answers;
	}

	/**
	 * 处理矩阵单选问题
	 */
	private void storeMatrixRadiomap(Map<Integer, String> matrixRadioMap,
			List<Answer> answers) {
		for (Entry<Integer, String> value:matrixRadioMap.entrySet()) {
			Answer ans=new Answer();
			ans.setAnswer(value.getValue());
			ans.setQuestionid(value.getKey());
			ans.setSurveyid(getCurrentSurvey().getId());
			answers.add(ans);
		}
	}

	/**
	 * 获取矩阵式问题id q12_0
	 * @param key
	 * @return
	 */
	private Integer getRadioQid(String key) {
		return Integer.parseInt(key.substring(1, key.lastIndexOf("_")));
	}

	/**
	 * 从Session中得到当前Survey
	 * @return
	 */
	private Survey getCurrentSurvey() {
		return (Survey) sessionMap.get(CURRENT_SURVEY);
	}

	/**
	 * 提取问题题号--q12->12
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}

	/**
	 * 清空session中的答案
	 */
	private void clearAnswerSession() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_ANSWER_MAP);
	}

	/**
	 * 更新答案数据到Session
	 */
	private void mergeAnswersIntoSession() {
		Map<Integer,Map<String,String[]>> allParameters=getAllParameters();
		allParameters.put(currentPid,postedParameters);
	}

	/**
	 * 获得Session中存放的所有答案集合（总map={pageid={q1=1,2,3;q2=2,3,4},}）
	 * @return
	 */
	private Map<Integer, Map<String, String[]>> getAllParameters() {
		Map<Integer, Map<String, String[]>> map = 
				(Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_ANSWER_MAP);
		return map;
	}

	/**
	 * 得到Submit按钮名称
	 * @return
	 */
	private String getSubmitName() {
		//得到提交的所有参数
		for (String key : this.postedParameters.keySet()) {
			if(key.startsWith("submit_")){
				return key;
			}
		}
		return null;
	}

	/**
	 * 注入Session
	 */
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

	//注入提交的参数
	public void setParameters(Map<String, String[]> parameters) {
		this.postedParameters=parameters;
		
	}
}
