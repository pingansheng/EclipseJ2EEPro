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
 * �ռ���ϢAction
 * @author pingansheng
 *
 */
@Controller("collectionsurveyaction")
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey>{

	private static final long serialVersionUID = 121733788026529297L;

	@Resource(name = "surveyService")
	private SurveyService surveyService;

	// Ŀ������sid
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
	 * ��������Ľ����Excel�ļ�
	 * @return
	 */
	public String collectData(){
		return SUCCESS;
	}
	
	/**
	 * ����Excel�ļ�
	 * @return
	 */
	public InputStream getResult(){
		try {
			Map<Integer,Integer> indexColumn= new HashMap<Integer, Integer>();//��¼ÿ���������
			List<Question> questions=surveyService.getQuestions(sid);
			HSSFWorkbook excel=new HSSFWorkbook();
			HSSFSheet sheet=excel.createSheet("��������Ϣ");
			HSSFRow row=sheet.createRow(0);
			
			this.fileName=surveyService.getSuvey(sid).getTitle()+"_�����Ϣ.xls";
			this.fileName = new String(fileName.getBytes(), "ISO8859-1");        
			/**
			 * �������
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
			 * ���Answer
			 */
			List<Answer> anses=surveyService.getAnswers(sid);
			
			String oldUUID="";//�ϴε�uuid
			HSSFRow answerRow=null;//�ж���
			int rowIndex=0;//��������ʼΪ1�ڶ���
			for (Answer a : anses) {
				
				//�ж��Ƿ���Ҫ����
				if(!a.getUuid().equals(oldUUID)){
					//uuid��һ����Ҫ�����������仯
					oldUUID=a.getUuid();
					rowIndex++;
					answerRow=sheet.createRow(rowIndex);
				}
				//�����
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
