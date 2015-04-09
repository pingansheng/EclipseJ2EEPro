package com.pas.survey.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.pas.survey.model.Log;
import com.pas.survey.model.User;
import com.pas.survey.service.LogService;
import com.pas.survey.util.StringUtil;

/**
 * ��־��¼��
 * 
 * @author pingansheng
 * 
 */
public class Logger {

	@Resource(name="logService")
	private LogService logService;

	/**
	 * ��¼
	 * @param pjp
	 * @return
	 */
	public Object record(ProceedingJoinPoint pjp) {
		Log log = new Log();

		try {
			ActionContext ac = ActionContext.getContext();
			if (ac != null) {
				Map<String, Object> session = ac.getSession();
				if (null != session) {
					User user = (User) session.get("user");
					if (null != user) {
						log.setOperator("" + user.getId() + ":"
								+ user.getEmail());
					}
				}
			}
			// ��������
			String opername = pjp.getSignature().getName();
			log.setOperName(opername);
			// ��������
			Object[] params = pjp.getArgs();
			log.setOperParams(StringUtil.arrTostr(params));
			// ����Ŀ�����ķ���
			Object ret = pjp.proceed();
			//���ò������
			log.setOperResult("success");
			// ���ý����Ϣ
			if (null != ret) {
				log.setResultMsg(ret.toString());
			}
			
			return ret;
		} catch (Throwable e) {
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());
		} finally {
			logService.saveEntity(log);
		}
		return null;
	}
}
