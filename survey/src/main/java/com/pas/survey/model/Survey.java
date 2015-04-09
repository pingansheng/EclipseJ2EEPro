package com.pas.survey.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.pas.survey.util.StringUtil;
import com.pas.survey.util.ValidateUtil;

/**
 * 调查类
 * 
 * @author pingansheng
 * 
 */
public class Survey extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7742815044834806731L;
	private Integer id;
	private String title = "调查初始名称";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	//最小页面的序号
	private float minOrderno=-1;
	//最大页面的序号
	private float maxOrderno=-1;
	private Date createTime = new Date();
	private User user;
	
	private Boolean closed=true;
	
	private String logoPhotoPath;
	
	// 建立从Survey到Page之间一对多关联关系
	private Set<Page> pages=new HashSet<Page>();
	
	public float getMinOrderno() {
		return minOrderno;
	}

	public void setMinOrderno(float minOrderno) {
		this.minOrderno = minOrderno;
	}

	public float getMaxOrderno() {
		return maxOrderno;
	}

	public void setMaxOrderno(float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	
	
	/**
	 * 增加业务逻辑
	 * 直接返回图片绝对地址
	 * @return
	 */
	public String getLogoPhotoPath() {
		String path=StringUtil.getProPath();
		String imgPath=path+logoPhotoPath;
		File img=new File(imgPath);
		if(ValidateUtil.isValid(imgPath)){
			if(img.exists()){
				return logoPhotoPath;
			}
		}
		return null;
	}

	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}
}
