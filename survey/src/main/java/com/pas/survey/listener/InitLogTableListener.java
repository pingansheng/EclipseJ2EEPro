package com.pas.survey.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.pas.survey.model.security.Right;
import com.pas.survey.service.LogService;
import com.pas.survey.service.RightService;
import com.pas.survey.util.LogUtil;

/**
 * Spring��ʼ����ɺ���������
 * ��ʼ����־���ݿ��
 * @author pingansheng
 * 
 */
@Controller
public class InitLogTableListener implements ApplicationListener {

	@Resource
	private LogService logService;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// Spring������ʼ����������ˢ��ʱ��
		if (arg0 instanceof ContextRefreshedEvent) {
			//������־���ݿ�� ���£���һ�£��¶���
			String table1name=LogUtil.generateLogTableName(0);
			String table2name=LogUtil.generateLogTableName(1);
			String table3name=LogUtil.generateLogTableName(2);
			logService.createTable(table1name);
			logService.createTable(table2name);
			logService.createTable(table3name);
		}
	}
}
