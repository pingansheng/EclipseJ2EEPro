package com.pas.survey.service;

import java.util.List;
import java.util.Set;

import com.pas.survey.model.security.Right;

/**
 * Ȩ�޽ӿ�
 *
 */
public interface RightService extends BaseService<Right>{

	/**
	 * ��������Ȩ��
	 * @param model
	 */
	void saveOrUpdateRight(Right model);
	
	/**
	 * Ȩ���Ƿ��Ѿ�����
	 * @param model
	 * @return
	 */
	boolean existRight(Right model);

	/**
	 * ׷��Ȩ��Url�����ݿ�
	 * @param rightUrl
	 */
	void appendRightUrl(String rightUrl);

	/**
	 * ��������
	 * @param allRights
	 */
	void batchUpdateRights(List<Right> allRights);

	/**
	 * ����id���ݵõ�Ȩ���б�
	 * @param ids
	 * @return
	 */
	List<Right> findRightsByIds(String[] ids);

	/**
	 * �������Ȩ��λ
	 * @return
	 */
	int getMaxRightPos();

	/**
	 * ����url��ȡȨ�޶���
	 * @param url
	 * @return
	 */
	Right getRightByUrl(String url);
	
	

}
