package com.pas.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pas.survey.dao.BaseDao;
import com.pas.survey.model.Answer;
import com.pas.survey.model.Question;
import com.pas.survey.model.statistic.OptionStatisticModel;
import com.pas.survey.model.statistic.QuestionStatisticModel;
import com.pas.survey.service.StatisticService;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {

	@Resource(name = "questionDao")
	private BaseDao<Question> questionDao;
	@Resource(name = "answerDao")
	private BaseDao<Answer> answerDao;

	@Override
	public QuestionStatisticModel statistic(Integer qid) {
		Question q = questionDao.getEntity(qid);
		QuestionStatisticModel qsm = new QuestionStatisticModel();
		qsm.setQuestion(q);

		// �ش������������
		String qhql = "select count(*) from Answer a where a.questionid=?";
		Long qcount = (Long) answerDao.getUniqueResult(qhql, qid);
		qsm.setCount(qcount.intValue());

		// ͳ��ÿ��ѡ�����
		int qt = q.getQuestionType();

		//ѡ��ͳ��ģ��
		OptionStatisticModel osm = null;
		//ѡ��ͳ��HQL���
		String ohql = "select count(*) from Answer a where a.questionid=? and concat(',',a.answer,',') like ?";
		//�ش�����
		Long ocount;
		
		switch (qt) {
		// ǰ5��ͳ�Ʒ�ʽһ��
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			String[] arr = q.getOptionArr();
			for (int i = 0; i < arr.length; i++) {
				osm = new OptionStatisticModel();
				osm.setOptionLabel(arr[i]);
				osm.setOptionIndex(i);
				ocount = (Long) answerDao.getUniqueResult(ohql, qid, "%,"
						+ i + ",%");
				osm.setCount(ocount.intValue());
				qsm.getOpSMS().add(osm);
			}
			// otherѡ��
			if (q.getOther() == true) {
				osm = new OptionStatisticModel();
				osm.setOptionLabel("����");
				ocount = (Long) answerDao.getUniqueResult(ohql, qid,
						"%,other,%");
				osm.setCount(ocount.intValue());
				qsm.getOpSMS().add(osm);
			}
			break;
		// ���� ��ѡ ��ѡ ����
		case 6:
		case 7:
		case 8:

			String[] rows = q.getMatrixRowTitlesArr();
			String[] cols = q.getMatrixColTitlesArr();
			String[] options = q.getMatrixSelectOptionsArr();

			for (int i = 0; i < rows.length; i++) {
				for (int j = 0; j < cols.length; j++) {
					if (qt != 8) {
						// ��˫ѡ
						osm=new OptionStatisticModel();
						osm.setMatrixRowLabel(rows[i]);
						osm.setMatrixRowIndex(i);
						osm.setMatrixColLabel(cols[j]);
						osm.setMatrixColIndex(j);
						ocount=(Long) answerDao.getUniqueResult(ohql, qid,"%,"+i+"_"+j+",%");
						osm.setCount(ocount.intValue());
						
						qsm.getOpSMS().add(osm);
					} else {
						// ����
						for (int k = 0; k < options.length; k++) {
							osm=new OptionStatisticModel();
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixRowIndex(i);
							osm.setMatrixColLabel(cols[j]);
							osm.setMatrixColIndex(j);
							osm.setMatrixSelectLabel(options[k]);
							osm.setMatrixSelectIndex(k);
							ocount=(Long) answerDao.getUniqueResult(ohql, qid,"%,"+i+"_"+j+"_"+k+",%");
							osm.setCount(ocount.intValue());
							
							qsm.getOpSMS().add(osm);
						}
					}
				}
			}
			break;
		default:
			break;
		}
		return qsm;
	}

}
