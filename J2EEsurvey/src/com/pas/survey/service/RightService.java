package com.pas.survey.service;

import java.util.List;
import java.util.Set;

import com.pas.survey.model.security.Right;

/**
 * 权限接口
 *
 */
public interface RightService extends BaseService<Right>{

	/**
	 * 保存或更新权限
	 * @param model
	 */
	void saveOrUpdateRight(Right model);
	
	/**
	 * 权限是否已经存在
	 * @param model
	 * @return
	 */
	boolean existRight(Right model);

	/**
	 * 追加权限Url至数据库
	 * @param rightUrl
	 */
	void appendRightUrl(String rightUrl);

	/**
	 * 批量更新
	 * @param allRights
	 */
	void batchUpdateRights(List<Right> allRights);

	/**
	 * 根据id数据得到权限列表
	 * @param ids
	 * @return
	 */
	List<Right> findRightsByIds(String[] ids);

	/**
	 * 计算最大权限位
	 * @return
	 */
	int getMaxRightPos();

	/**
	 * 根据url获取权限对象
	 * @param url
	 * @return
	 */
	Right getRightByUrl(String url);
	
	

}
