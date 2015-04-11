package com.pas.survey.struts2.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.catalina.tribes.util.Logs;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.Answer;
import com.pas.survey.model.Log;
import com.pas.survey.model.Page;
import com.pas.survey.model.Survey;
import com.pas.survey.service.LogService;
import com.pas.survey.service.SurveyService;
import com.pas.survey.util.StringUtil;

/**
 * 日志Action
 * 
 * @author pingansheng
 * 
 */
@Controller("logaction")
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 121733788026529297L;

	@Resource
	private LogService logService;

	private List<Log> alllogs;

	/**
	 * 检索日期时间段
	 */
	private Date start_date;
	private Date end_date;
	
	
	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public List<Log> getAlllogs() {
		return alllogs;
	}


	public void setAlllogs(List<Log> alllogs) {
		this.alllogs = alllogs;
	}


	/**
	 * 得到所有日志列表
	 * @return
	 */
	public String findAllLogs() {
		alllogs = logService.findHistoryLogsByMonth();
		return "logListPage";
	}
	
	public String findLogsByDate(){
		alllogs=logService.findLogsByDate(start_date,end_date);
		return "logListPage";
	}
}
