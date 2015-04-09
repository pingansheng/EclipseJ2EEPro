package com.pas.survey.scheduler;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.pas.survey.service.LogService;
import com.pas.survey.util.LogUtil;

/**
 * 调度任务 创建日志表
 * 
 * @author pingansheng
 * 
 */
public class CreateLogTables extends QuartzJobBean {

	private LogService logService;

	/**
	 * 注入LogService
	 * 
	 * @param logService
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	/**
	 * 调度业务逻辑
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// 一般生成三个月
		String tableName1 = LogUtil.generateLogTableName(1);
		String tableName2 = LogUtil.generateLogTableName(2);
		String tableName3 = LogUtil.generateLogTableName(3);

		logService.createTable(tableName1);
		logService.createTable(tableName2);
		logService.createTable(tableName3);
	}
}
