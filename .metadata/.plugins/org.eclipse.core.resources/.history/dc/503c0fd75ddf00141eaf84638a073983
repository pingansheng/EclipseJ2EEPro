package com.pas.survey.model;

import java.io.Serializable;

import com.pas.survey.util.StringUtil;

public class Question extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8110796724331367025L;
	private static final String REG = "\r\n";
	private Integer id;
	// 题型0-8
	private int questionType;
	// 标题
	private String title;
	// 选项
	private String options;
	private String[] optionArr;
	// 其他项
	private String otherStr;

	private boolean other;

	// 其他项样式:0-无 1-文本框 2-下拉列表
	private int otherStyle;

	// 其他项下拉选项
	private String otherSelectOptions;
	private String[] otherSelectOptionArr;

	// 矩阵式行标题集
	private String matrixRowTitles;
	private String[] matrixRowTitlesArr;

	// 矩阵式列标题集
	private String matrixColTitles;
	private String[] matrixColTitlesArr;

	// 矩阵是下拉选项集
	private String matrixSelectOptions;
	private String[] matrixSelectOptionsArr;

	private Page page;

	public String getOtherStr() {
		return otherStr;
	}

	public void setOtherStr(String otherStr) {
		this.otherStr = otherStr;
		if (null == otherStr || "".equals(otherStr)) {
			this.other = false;
		} else {
			this.other = true;
		}
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
		this.otherSelectOptionArr = otherSelectOptionArr;
	}

	public String[] getMatrixRowTitlesArr() {
		return matrixRowTitlesArr;
	}

	public void setMatrixRowTitlesArr(String[] matrixRowTitlesArr) {
		this.matrixRowTitlesArr = matrixRowTitlesArr;
	}

	public String[] getMatrixColTitlesArr() {
		return matrixColTitlesArr;
	}

	public void setMatrixColTitlesArr(String[] matrixColTitlesArr) {
		this.matrixColTitlesArr = matrixColTitlesArr;
	}

	public String[] getMatrixSelectOptionsArr() {
		return matrixSelectOptionsArr;
	}

	public void setMatrixSelectOptionsArr(String[] matrixSelectOptionsArr) {
		this.matrixSelectOptionsArr = matrixSelectOptionsArr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	/**
	 * 重写拆分字符串为数组
	 */
	public void setOptions(String options) {
		this.options = options;
		this.optionArr = StringUtil.stringToArr(options, REG);
	}

	public boolean getOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr = StringUtil.stringToArr(otherSelectOptions,
				REG);
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitlesArr = StringUtil.stringToArr(matrixRowTitles, REG);
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitlesArr = StringUtil.stringToArr(matrixColTitles, REG);
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionsArr = StringUtil.stringToArr(
				matrixSelectOptions, REG);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
