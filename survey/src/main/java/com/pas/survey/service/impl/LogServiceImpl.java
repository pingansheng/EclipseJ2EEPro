package com.pas.survey.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.model.Log;
import com.pas.survey.model.security.Right;
import com.pas.survey.service.LogService;
import com.pas.survey.util.DateUtil;
import com.pas.survey.util.LogUtil;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Override
	@Resource(name = "userDao")
	public void setDao(BaseDao<Log> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	@Override
	public void createTable(String tableName1) {
		String sql = "create table if not exists " + tableName1 + " like log";
		this.executeSQL(sql);
	}

	/**
	 * 重写该方法，插入日志数据至动态表
	 */
	@Override
	public void saveEntity(Log l) {
		String sql = "insert into "
				+ LogUtil.generateLogTableName(0)
				+ "(id,operator,operName,operParams,operResult,resultMsg,operTime) values(?,?,?,?,?,?,?)";
		UUIDHexGenerator uuid = new UUIDHexGenerator();
		this.executeSQL(sql, uuid.generate(null, null).toString(),
				l.getOperator(), l.getOperName(), l.getOperParams(),
				l.getOperResult(), l.getResultMsg(), l.getOperTime());
	}

	@Override
	public List<Log> findHistoryLogsByMonth() {
		// 当前月表名称
		String tableName = LogUtil.generateLogTableName(0);
		// 查询最近的日志表名称
		String sql = "select t.table_name from information_schema.tables t where t.table_schema='survey' and t.table_name like 'log_%'"
				+ "and t.table_name <= '"
				+ tableName
				+ "' order by t.table_name desc";

		List<String> tables = this.executeSqlQuery(sql);

		if (tables.size() > 0) {
			StringBuffer sb_sql = new StringBuffer();
			// 联合查询语句
			for (String t : tables) {
				sb_sql.append("select * from ").append(t).append(" union ");
			}

			sb_sql.replace(sb_sql.lastIndexOf(" union "), sb_sql.length(), "");

			List<Log> logs = this.executeSqlQuery(sb_sql.toString(), Log.class);
			return logs;
		}

		return null;
	}

	@Override
	public List<Log> findLogsByDate(Date start_date, Date end_date) {
		Calendar c = Calendar.getInstance();
		c.setTime(start_date);
		int year1 = c.get(Calendar.YEAR);
		int month1 = c.get(Calendar.MONTH) + 1;
		// 最后一天延长至第二天0点
		Calendar date = Calendar.getInstance();
		date.setTime(end_date);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
		end_date = date.getTime();
		// 取加一天之后的日期
		c.setTime(end_date);
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH) + 1;

		DecimalFormat df = new DecimalFormat("00");
		String tableName1 = "log_" + year1 + "_" + df.format(month1);
		String tableName2 = "log_" + year2 + "_" + df.format(month2);

		String sql = "select t.table_name from information_schema.tables t where t.table_schema='survey' and t.table_name like 'log_%'"
				+ "and t.table_name between '"
				+ tableName1
				+ "' and '"
				+ tableName2 + "' order by t.table_name desc";
		List<String> tables = this.executeSqlQuery(sql);

		if (tables.size() > 0) {

			StringBuffer sb_sql = new StringBuffer();
			
			//一个表需要两边限制，否则全集
			if(tables.size()==1){
				sb_sql.append("select * from ").append(tables.get(0))
				.append(" where operTime between '")
				.append(DateUtil.getSpecialDateString(start_date))
				.append("' and '").append(DateUtil.getSpecialDateString(end_date)).append("'");
			}
			
			if (tables.size() > 1) {
				// 第一个表
				sb_sql.append("select * from ").append(tables.get(0))
						.append(" where operTime >='")
						.append(DateUtil.getSpecialDateString(start_date))
						.append("' union ");
				// 中间的若干表
				for (int i = 1; i < tables.size() - 1; i++) {
					sb_sql.append("select * from ").append(tables.get(i))
							.append(" union ");
				}

				// 最后一个表
				sb_sql.append("select * from ")
						.append(tables.get(tables.size() - 1))
						.append(" where operTime <= '")
						.append(DateUtil.getSpecialDateString(end_date))
						.append("'");
			}
			List<Log> logs = this.executeSqlQuery(sb_sql.toString(), Log.class);
			return logs;
		}
		return null;
	}

}
