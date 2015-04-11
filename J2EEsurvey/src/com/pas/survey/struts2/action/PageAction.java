package com.pas.survey.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.Page;
import com.pas.survey.model.Survey;
import com.pas.survey.service.SurveyService;
/**
 * PageAction
 * @author pingansheng
 *
 */
@Controller("pageaction")
@Scope("prototype")
public class PageAction extends BaseAction<Page> {

	private static final long serialVersionUID = 7032765744767711547L;
	
	@Resource(name="surveyService")
	private SurveyService surveyService;
	private Integer sid;
	private Integer pid;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 到达添加Page页面
	 * @return
	 */
	public String toAddPage(){
		return "addPagePage";
	}
	
	/**
	 * 保存&更新Page
	 * @return
	 */
	public String saveOrUpdatePage() {
		Survey s= new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}
	
	
	/**
	 * 标记Page
	 * @return
	 */
	public String editPage() {
		this.model=surveyService.getPage(pid);
		return "editPagePage";
	}
	
	/**
	 * 删除Page
	 * @return
	 */
	public String deletePage() {
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
}
