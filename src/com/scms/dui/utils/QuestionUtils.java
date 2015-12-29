package com.scms.dui.utils;

import java.util.ArrayList;

public class QuestionUtils {
	/*
	 * variable inisialise
	 */
	private String quesNo, ques, quesType, secondQues,activity;
	private ArrayList<OptionUtils> optionArr;
	private ArrayList<SubOptionUtils> secondOptionArr;

	/*
	 * Getter and setter
	 */
	public String getQuesNo() {
		return quesNo;
	}

	public void setQuesNo(String quesNo) {
		this.quesNo = quesNo;
	}

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public String getQuesType() {
		return quesType;
	}

	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}

	public String getSecondQues() {
		return secondQues;
	}

	public void setSecondQues(String secondQues) {
		this.secondQues = secondQues;
	}

	public ArrayList<OptionUtils> getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(ArrayList<OptionUtils> optionArr) {
		this.optionArr = optionArr;
	}

	public ArrayList<SubOptionUtils> getSecondOptionArr() {
		return secondOptionArr;
	}

	public void setSecondOptionArr(ArrayList<SubOptionUtils> secondOptionArr) {
		this.secondOptionArr = secondOptionArr;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}
