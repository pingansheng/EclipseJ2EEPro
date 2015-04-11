package com.pas.survey.service;

import java.util.List;

import com.pas.survey.model.Answer;
import com.pas.survey.model.Page;
import com.pas.survey.model.Question;
import com.pas.survey.model.Survey;
import com.pas.survey.model.User;

public interface SurveyService{

	/**
	 * ��ѯ�����б�
	 */
	public List<Survey> findMySurveys(User user);
	
	/**
	 * ��ӵ���
	 */
	public Survey newSurvey(User user);

	/**
	 * ��ȡ��ѯ����
	 */
	public Survey getSuvey(int sid);
	
	/**
	 * ��ȡ��ѯ���飬����ǿ�г�ʼ�����Ӷ���
	 * �����Ϳ��Բ�ʹ��OpenSessionInView������������ʧ����
	 */
	public Survey getSuveyWithChildren(int sid);

	/**
	 * ���µ���
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * ����&����Page
	 * @param model
	 */
	public void saveOrUpdatePage(Page model);

	/**
	 * ��ȡĳ��Page����
	 * @param pid
	 * @return
	 */
	public Page getPage(Integer pid);
	
	/**
	 * ��������
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * �õ�����model
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);
	
	/*
	 * ɾ������
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * ɾ��Page
	 * @param pid
	 */
	public void deletePage(Integer pid);
	
	/**
	 * ɾ������
	 * @param pid
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * ��յ���Ĵ�
	 * @param sid
	 */
	public void clearAnswer(Integer sid);

	/**
	 * �л��ʾ�״̬
	 * @param sid
	 */
	public void toggleStatus(Integer sid);

	/**
	 * ����logo·��
	 * @param sid
	 * @param string
	 */
	public void updateLogoPhotoPath(Integer sid, String string);

	/**
	 * ��ȡ�û���صĵ����б�ǿ�Ƽ���Page����
	 * @param user
	 * @return
	 */
	public List<Survey> getSuveysWithPages(User user);

	/**
	 * �ƶ�����ҳ
	 * @param srcpid Դpageid
	 * @param targetpid Ŀ��pageid
	 * @param moveType �ƶ����� 0֮ǰ 1֮��
	 */
	public void moveOrCopyPage(int srcpid, int targetpid, int moveType);

	/**
	 * ��ѯ���п��ŵĵ���
	 * @return
	 */
	public List<Survey> getAllOpenedSurveys();

	/**
	 * ��ѯSurvey����ҳ
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * �õ���һҳ
	 * @param currentPid
	 * @return
	 */
	public Page getPrePage(Integer currentPid);

	/**
	 * �õ���һҳ
	 * @param currentPid
	 * @return
	 */
	public Page getNextPage(Integer currentPid);

	/**
	 *���汾����Ĵ𰸼���
	 * @param storeAnswers
	 */
	public void saveAnswers(List<Answer> storeAnswers);
	
	/**
	 * ��ѯָ���������������
	 */
	public List<Question> getQuestions(Integer sid);
	
	/**
	 * ��ѯָ����������д�
	 */
	public List<Answer> getAnswers(Integer sid);
}
