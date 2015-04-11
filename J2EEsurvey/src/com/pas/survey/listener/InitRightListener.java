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
import com.pas.survey.service.RightService;

/**
 * Spring初始化完成后立即调用
 * 
 * @author pingansheng
 * 
 */
@Controller
public class InitRightListener implements ApplicationListener,
		ServletContextAware {

	@Resource
	private RightService rs;

	private ServletContext sc;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// Spring容器初始化，上下文刷新时间
		if (arg0 instanceof ContextRefreshedEvent) {
			List<Right> allrights = rs.findAllEntities();
			Map<String, Right> map = new HashMap<String, Right>();

			for (Right right : allrights) {
				map.put(right.getRightUrl(), right);
			}
			if (sc != null) {
				sc.setAttribute("all rights map", map);
				System.out.println("*************加载权限完成");
			}
		}
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

}
