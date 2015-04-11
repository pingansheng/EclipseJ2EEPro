package com.pas.survey.struts2.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.User;
import com.pas.survey.service.UserService;
import com.pas.survey.util.DataUtil;
import com.pas.survey.util.ValidateUtil;

/**
 * ×¢²áAction
 * 
 * @author pingansheng
 * 
 */
@Controller("regaction")
@Scope("prototype")
public class RegAction extends BaseAction<User> {

	private static final long serialVersionUID = 6520010073500627383L;

	@Resource
	private UserService userService;
	private Map json;
	
	public Map getJson() {
		return json;
	}

	public void setJson(Map json) {
		this.json = json;
	}

	/**
	 * Ìø×ªµ½×¢²á½çÃæ
	 * 
	 * @return
	 */
	public String toRegPage() {
		return "regPage";
	}

	/**
	 * ÓÃ»§×¢²á
	 * 
	 * @return
	 */
	public String doReg() {
		//·µ»ØµÄjsonÐÅÏ¢
		Map<String, Object> jsonInfo = new HashMap<String, Object>(); 
		if (dataIsValid(jsonInfo)) {
			model.setPassword(DataUtil.sha_1(model.getPassword()));
			userService.saveEntity(model);
			jsonInfo.put("info", "OK");
		}else{
			jsonInfo.put("info", "ERR");
		}
		this.setJson(jsonInfo);
		return SUCCESS;
	}

	private boolean dataIsValid(Map<String,Object> gson) {
		List<String> errs=new ArrayList<String>();
		
		if (!ValidateUtil.isValid(model.getEmail())) {
			errs.add("ÓÊÏä±ØÐëÌîÐ´");
		}else{
			if (userService.isRegisted(model.getEmail())) {
				errs.add("ÓÊÏäÒÑ×¢²á");
			}
		}
		if (!ValidateUtil.isValid(model.getPassword())) {
			errs.add("ÃÜÂë±ØÐëÌîÐ´");
		}
		if (!ValidateUtil.isValid(model.getNickname())) {
			errs.add("êÇ³Æ±ØÐëÌîÐ´");
		}
		gson.put("Errors", errs);
		if(errs.size()>0){
			return false;
		}
		return true;
	}
}
