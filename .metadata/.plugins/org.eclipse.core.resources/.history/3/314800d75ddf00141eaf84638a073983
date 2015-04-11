package com.pas.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.model.security.Right;
import com.pas.survey.service.RightService;
import com.pas.survey.util.StringUtil;
import com.pas.survey.util.ValidateUtil;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {
	@Override
	@Resource(name = "rightDao")
	public void setDao(BaseDao<Right> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateRight(Right model) {
		if (model.getId() == null) {
			// 插入
			// 获取最大权限位权限码
			String hql = "select r.rightPos,max(r.rightCode) from Right r where r.rightPos=(select max(r.rightPos) from Right r)";
			Object[] res = (Object[]) this.getUniqueResult(hql);
			Integer topPos = (Integer) res[0];
			Long topCode = (Long) res[1];
			if (topPos == null) {
				// 权限表为空
				topPos = 0;
				topCode = 1L;
			} else {
				// 权限表不为空
				// 权限码最大
				if (topCode >= (1L << 60)) {
					topPos++;
					topCode = 1L;
				} else {
					topCode <<= 1;
				}
			}
			// 设置权限位与权限码
			model.setRightPos(topPos);
			model.setRightCode(topCode);
		}
		// 更新无需做相关操作
		this.saveOrUpdateEntity(model);
	}

	@Override
	public boolean existRight(Right model) {
		String hql = "from Right r where r.rightUrl=?";
		List<Right> res = this.findEntityByHql(hql, model.getRightUrl());
		return ValidateUtil.isValid(res);
	}

	@Override
	public void appendRightUrl(String rightUrl) {
		Right r = new Right();
		r.setRightUrl(rightUrl);
		if (!existRight(r)) {
			this.saveOrUpdateRight(r);
		}
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		if (ValidateUtil.isValid(allRights)) {
			String hql = "update Right r set r.common=?,r.rightName=?,r.rightDesc=? where r.id=?";
			for (Right r : allRights) {
				this.batchEntityByHql(hql, r.isCommon(), r.getRightName(),
						r.getRightDesc(), r.getId());
			}
		}
	}

	@Override
	public List<Right> findRightsByIds(String[] ids) {
		if (ValidateUtil.isValid(ids)) {
			String hql = "from Right r where r.id in ("
					+ StringUtil.arrTostr(ids) + ")";
			return this.findEntityByHql(hql);
		}
		return null;
	}

	@Override
	public int getMaxRightPos() {
		String hql = "select max(r.rightPos) from Right r";
		Integer maxPos = (Integer) this.getUniqueResult(hql);
		return maxPos == null ? 0 : maxPos;
	}

	@Override
	public Right getRightByUrl(String url) {
		String hql = "from Right r where r.rightUrl=?";
		List<Right> rights = this.findEntityByHql(hql, url);
		if (rights.size() > 0) {
			return rights.get(0);
		}
		return null;
	}
}
