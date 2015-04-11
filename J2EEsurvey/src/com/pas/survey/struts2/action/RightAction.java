package com.pas.survey.struts2.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.User;
import com.pas.survey.model.security.Right;
import com.pas.survey.service.RightService;
import com.pas.survey.service.UserService;
import com.pas.survey.util.ClassUtil;
import com.pas.survey.util.DataUtil;
import com.pas.survey.util.ValidateUtil;

/**
 * Ȩ��Action
 * 
 * @author pingansheng
 * 
 */
@Controller("rightaction")
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = 6520010073500627383L;

	private List<Right> allRights;

	private Integer rightid;

	private String info;

	private String common;
	
	@Resource(name = "rightService")
	private RightService rightService;

	/**
	 * Action��Name�б�
	 */
	private List<String> actionNames;

	
	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getRightid() {
		return rightid;
	}

	public void setRightid(Integer rightid) {
		this.rightid = rightid;
	}

	public List<String> getActionNames() {
		return actionNames;
	}

	public void setActionNames(List<String> actionNames) {
		this.actionNames = actionNames;
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	/**
	 * ��ȡ����Ȩ��
	 * 
	 * @return
	 */
	public String findAllRights() {
		this.allRights = rightService.findAllEntities();
		return "rightListPage";
	}

	/**
	 * ���Ȩ��ҳ��
	 * 
	 * @return
	 */
	public String toAddRightPage() {
		try {
			// ׼��ActionName�б�����
			this.actionNames = new ArrayList<String>();
			String actionPackageName = this.getClass().getPackage().getName();
			List<String> rightUrls = ClassUtil.getAllRightString(
					actionPackageName, rightService);
			for (String string : rightUrls) {
				actionNames.add(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		// ������޸ĵĻ�ģ�͸�ֵ
		if (rightid != null) {
			this.model = rightService.getEntity(rightid);
		}
		return "addRightPage";
	}

	/**
	 * ��ӻ����Ȩ��
	 * 
	 * @return
	 */
	public String addOrUpdate() {
		if(ValidateUtil.isValid(common)){
			model.setCommon(true);
		}else{
			model.setCommon(false);
		}
		rightService.saveOrUpdateRight(model);
		return "findAllRightsAction";
	}

	/**
	 * ɾ��Ȩ��
	 * 
	 * @return
	 */
	public String deleteRight() {
		rightService.deleteEntity(rightService.getEntity(rightid));
		return "findAllRightsAction";
	}
	
	/**
	 * ��������
	 * @return
	 */
	public String batchUpdateRights(){
		rightService.batchUpdateRights(this.allRights);
		return "findAllRightsAction";
	}
}
