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
 * 日志记录仪
 * 
 * @author pingansheng
 * 
 */
public class Logger {

	@Resource(name="logService")
	private LogService logService;

	/**
	 * 记录
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
			// 操作名称
			String opername = pjp.getSignature().getName();
			log.setOperName(opername);
			// 操作参数
			Object[] params = pjp.getArgs();
			log.setOperParams(StringUtil.arrTostr(params));
			// 调用目标对象的方法
			Object ret = pjp.proceed();
			//设置操作结果
			log.setOperResult("success");
			// 设置结果消息
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
