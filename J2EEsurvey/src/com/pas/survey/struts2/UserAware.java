package com.pas.survey.struts2;

import com.pas.survey.model.User;

/**
 * �û���ע�ӿ� ����ע��Session�е�User����
 * @author pingansheng
 *
 */
public interface UserAware {
	public void setUser(User user);
}
