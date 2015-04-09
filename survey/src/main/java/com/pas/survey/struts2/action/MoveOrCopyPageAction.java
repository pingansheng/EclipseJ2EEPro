package com.pas.survey.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.dao.impl.SurveyDaoImpl;
import com.pas.survey.model.Page;
import com.pas.survey.model.Survey;
import com.pas.survey.model.User;
import com.pas.survey.service.SurveyService;
import com.pas.survey.struts2.UserAware;

@Controller("moveorcopypageaction")
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {

	private static final long serialVersionUID = 1191686831514781093L;

	@Resource(name = "surveyService")
	private SurveyService surveyService;
	// 原始Page ID
	private int srcpid;
	// 目标页面Pid
	private int targetpid;
	// 目标页面Surveyid
	private int sid;
	
	// 接收注入的User
	private User user;

	// 获取所有Survey
	private List<Survey> mySurveys;

	// 接收调整类型
	// 0之前 1之后
	private int movetype;

	public int getTargetpid() {
		return targetpid;
	}

	public void setTargetpid(int targetpid) {
		this.targetpid = targetpid;
	}

	public int getMovetype() {
		return movetype;
	}

	public void setMovetype(int movetype) {
		this.movetype = movetype;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	public int getSrcpid() {
		return srcpid;
	}

	public void setSrcpid(int srcpid) {
		this.srcpid = srcpid;
	}

	/**
	 * 跳转至调整页面
	 * 
	 * @return
	 */
	public String toSelectTargetPage() {
		this.mySurveys = surveyService.getSuveysWithPages(user);
		return "selecttarget";
	}

	/**
	 * 执行移动或复制Page
	 * 
	 * @return
	 */
	public String doMoveOrCopyPage() {
		
		//接收参数
		surveyService.moveOrCopyPage(srcpid,targetpid,movetype);
		return "designSurveyAction";
	}

	public void setUser(User user) {
		this.user = user;
	}
}
