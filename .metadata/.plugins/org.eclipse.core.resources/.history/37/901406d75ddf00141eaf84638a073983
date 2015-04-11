package com.pas.survey.service;

import java.util.Date;
import java.util.List;

import com.pas.survey.model.Log;
import com.pas.survey.model.User;
import com.pas.survey.model.security.Role;

public interface LogService extends BaseService<Log>{

	/**
	 * 通过表名创建日志表
	 */
	void createTable(String tableName1);
	
	List<Log> findHistoryLogsByMonth();

	List<Log> findLogsByDate(Date start_date, Date end_date);
}
