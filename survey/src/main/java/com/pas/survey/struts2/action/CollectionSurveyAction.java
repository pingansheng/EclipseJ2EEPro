package com.pas.survey.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pas.survey.model.Answer;
import com.pas.survey.model.Page;
import com.pas.survey.model.Question;
import com.pas.survey.model.Survey;
import com.pas.survey.service.SurveyService;
import com.pas.survey.util.StringUtil;

/**
 * 收集信息Action
 * @author pingansheng
 *
 */
@Controller("collectionsurveyaction")
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey>{

	private static final long serialVersionUID = 121733788026529297L;

	@Resource(name = "surveyService")
	private SurveyService surveyService;

	// 目标调查的sid
	private Integer sid;
	
	private String fileName;

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	/**
	 * 导出调查的结果至Excel文件
	 * @return
	 */
	public String collectData(){
		return SUCCESS;
	}
	
	/**
	 * 返回Excel文件
	 * @return
	 */
	public InputStream getResult(){
		try {
			Map<Integer,Integer> indexColumn= new HashMap<Integer, Integer>();//记录每题的列索引
			List<Question> questions=surveyService.getQuestions(sid);
			HSSFWorkbook excel=new HSSFWorkbook();
			HSSFSheet sheet=excel.createSheet("调查结果信息");
			HSSFRow row=sheet.createRow(0);
			
			this.fileName=surveyService.getSuvey(sid).getTitle()+"_结果信息.xls";
			this.fileName = new String(fileName.getBytes(), "ISO8859-1");        
			/**
			 * 调查标题
			 */
			Question q=null;
			for (int i=0;i<questions.size();i++) {
				q=questions.get(i);
				indexColumn.put(q.getId(), i);
				sheet.setColumnWidth(i, 6000);
				HSSFCell cell=row.createCell(i);
				cell.setCellValue(q.getTitle());
			}
			
			HSSFCellStyle cellStyle=excel.createCellStyle();
			cellStyle.setWrapText(true);
			/**
			 * 输出Answer
			 */
			List<Answer> anses=surveyService.getAnswers(sid);
			
			String oldUUID="";//上次的uuid
			HSSFRow answerRow=null;//行对象
			int rowIndex=0;//行索引初始为1第二行
			for (Answer a : anses) {
				
				//判断是否需要换行
				if(!a.getUuid().equals(oldUUID)){
					//uuid不一致需要换行行索引变化
					oldUUID=a.getUuid();
					rowIndex++;
					answerRow=sheet.createRow(rowIndex);
				}
				//输出答案
				HSSFCell cell=answerRow.createCell(indexColumn.get(a.getQuestionid()));
				cell.setCellValue(a.getAnswer());
				cell.setCellStyle(cellStyle);
			}
			ByteArrayOutputStream os=new ByteArrayOutputStream();
			excel.write(os);
			return new ByteArrayInputStream(os.toByteArray()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
