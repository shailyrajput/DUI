package com.scms.utility;

import java.util.ArrayList;

import com.scms.dui.SplashScreen;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SingletonClass;

public class QuestionUtiltiy {

	private static ArrayList<QuestionUtils> questionData = SplashScreen.QuestionArrList;
	private ArrayList<OptionUtils> optionlist = new ArrayList<OptionUtils>();
	static String value;

	public static String Q1 = questionData.get(0).getQues();
	public  static String Q2 = questionData.get(1).getQues();
	public  static String Q3 = questionData.get(2).getQues();

	public static String Q4 = questionData.get(3).getQues();
	public static String Q5 = questionData.get(4).getQues();
	public static String Q6 = questionData.get(5).getQues();
	public static String Q7 = questionData.get(6).getQues();

	public static String Q8 = questionData.get(7).getQues();
	public static String Q9 = questionData.get(8).getQues();
	public static String Q10 = questionData.get(9).getQues();
	public static String Q11 = questionData.get(10).getQues();

	public static String Q12 = questionData.get(11).getQues();
	public static String Q13 = questionData.get(12).getQues();
	public static String Q14 = questionData.get(13).getQues();
	public static String Q15 = questionData.get(14).getQues();

	public static String Q16 = questionData.get(15).getQues();
	public static String Q17 = questionData.get(16).getQues();
	public static String Q18 = questionData.get(17).getQues();
	public static String Q19 = questionData.get(18).getQues();

	public static String Q20 = questionData.get(19).getQues();
	public static String Q21 = questionData.get(20).getQues();
	public static String Q22 = questionData.get(21).getQues();
	public static String Q23 = questionData.get(22).getQues();

	public static String Q24 = questionData.get(23).getQues();
	public static String Q25 = questionData.get(24).getQues();
	public static String Q26 = questionData.get(25).getQues();

	public static String Q21_1 = questionData.get(21).getOptionArr().get(0)
			.getOptionText();
	public static String Q21_2 = questionData.get(21).getOptionArr().get(1)
			.getOptionText();
	public static String Q21_3 = questionData.get(21).getOptionArr().get(2)
			.getOptionText();
	public static String Q21_4 = questionData.get(21).getOptionArr().get(3)
			.getOptionText();
	public static String Q21_5 = questionData.get(21).getOptionArr().get(4)
			.getOptionText();
	public static String Q21_6 = questionData.get(21).getOptionArr().get(5)
			.getOptionText();

	public static String Q22_1 = questionData.get(22).getOptionArr().get(0)
			.getOptionText();
	public static String Q22_2 = questionData.get(22).getOptionArr().get(1)
			.getOptionText();
	public static String Q22_3 = questionData.get(22).getOptionArr().get(2)
			.getOptionText();
	public static String Q22_4 = questionData.get(22).getOptionArr().get(3)
			.getOptionText();
	public static String Q22_5 = questionData.get(22).getOptionArr().get(4)
			.getOptionText();
	public static String Q22_6 = questionData.get(22).getOptionArr().get(5)
			.getOptionText();

	// ............................Question 8 data......................
	
/*
	public static String Q8_1 = "Blow a \"Fail\" result";
	public static String Q8_2 = "Blow a result other than \"Fail\" but the police officer arrested you anyway.";
	// ............................Question 6 data......................
	public static String Q6_1 = questionData.get(6).getOptionArr().get(0).getOptionText();

	public static String Q13_1 = questionData.get(12).getOptionArr().get(0)
			.getOptionText();
*/
	// ................. Question 1 Option Data.............................
	public static String Q1_1 = questionData.get(0).getOptionArr().get(0)
			.getOptionText();
	public static String Q1_2 = questionData.get(0).getOptionArr().get(1)
			.getOptionText();
	public static String Q1_3 = questionData.get(0).getOptionArr().get(2)
			.getOptionText();
	public static String Q1_4 = questionData.get(0).getOptionArr().get(3)
			.getOptionText();
	public static String Q1_5 = questionData.get(0).getOptionArr().get(4)
			.getOptionText();
	// ........................Question 2 Option Data..................
	public static String Q2_1 = questionData.get(1).getOptionArr().get(0)
			.getOptionText();
	public static String Q2_2 = questionData.get(1).getOptionArr().get(1)
			.getOptionText();
	public static String Q2_3 = questionData.get(1).getOptionArr().get(2)
			.getOptionText();

	// ........................Question 4 Option Data..................
	public static String Q3_1 = "Driver\'s seat";
	public static String Q3_2 = "Somewhere else (i.e. passenger seat, back seat, trunk...)";
	// ........................Question 4 Option Data..................
	public static String Q4_1 = questionData.get(3).getOptionArr().get(0).getOptionText();
	public static String Q4_2 = questionData.get(3).getOptionArr().get(1).getOptionText();

	// ........................Question 5 Option Data..................
	public static String Q5_1 = questionData.get(4).getOptionArr().get(0).getOptionText();
	public static String Q5_2 = questionData.get(4).getOptionArr().get(1)
			.getOptionText();
	// ............................Question 6 data......................

	// ............................Question 7 data......................
	public static String Q7_1 = questionData.get(6).getOptionArr().get(0).getOptionText();
	public static String Q7_2 = questionData.get(6).getOptionArr().get(1).getOptionText();
	// ............................Question 8 data......................

	public static String Q8_1 = "Blow a \"Fail\" result";
	public static String Q8_2 = "Blow a result other than \"Fail\" but the police officer arrested you anyway.";
	// public static String Q8_3=
	// questionData.get(7).getOptionArr().get(3).getOptionText();
	public static String Q8_3 = "";
	// ............................Question 10 data......................
	public static String Q10_1 = questionData.get(9).getOptionArr().get(0).getOptionText();
	public static String Q10_2 = questionData.get(9).getOptionArr().get(1).getOptionText();

	// ............................Question 11 data......................
	public static String Q11_1 = questionData.get(10).getOptionArr().get(0).getOptionText();
	public static String Q11_2 = questionData.get(10).getOptionArr().get(1).getOptionText();
	public static String Q11_3 = questionData.get(10).getOptionArr().get(2).getOptionText();

	// ..........................Question 12 data...........................

	// public static String Q12_1 =
	// questionData.get(11).getOptionArr().get(0).getOptionText();
	// public static String Q12_2 =
	// questionData.get(11).getOptionArr().get(1).getOptionText();

	// ..........................Question 13 data...........................
	public static String Q13_1 = questionData.get(12).getOptionArr().get(0).getOptionText();
	public static String Q13_2 = questionData.get(12).getOptionArr().get(1).getOptionText();

	// ..........................Question 14 data...........................
	public static String Q14_1 = questionData.get(13).getOptionArr().get(0)
			.getOptionText();
	public static String Q14_2 = questionData.get(13).getOptionArr().get(1).getOptionText();
	public static String Q14_21 = questionData.get(13).getOptionArr().get(1).getSubQuesOptionArr().get(0).getText();
	public static String Q14_22 = questionData.get(13).getOptionArr().get(1).getSubQuesOptionArr().get(1).getText();
	public static String Q14_23 = questionData.get(13).getOptionArr().get(1).getSubQuesOptionArr().get(2).getText();
	public static String Q14_24 = questionData.get(13).getOptionArr().get(1).getSubQuesOptionArr().get(3).getText();
	// .................................Question 15 data....................
	public static String Q15_1 = questionData.get(14).getOptionArr().get(0).getOptionText();
	public static String Q15_2 = questionData.get(14).getOptionArr().get(1).getOptionText();
	public static String Q15_21 = questionData.get(14).getOptionArr().get(1).getSubQuesOptionArr().get(0).getText();
	public static String Q15_22 = questionData.get(14).getOptionArr().get(1).getSubQuesOptionArr().get(1).getText();
	public static String Q15_23 = questionData.get(14).getOptionArr().get(1).getSubQuesOptionArr().get(2).getText();
	
	
	
	// ............................Question 20 data......................
	public static String Q20_1 = questionData.get(19).getOptionArr().get(0).getOptionText();
	public static String Q20_2 = questionData.get(19).getOptionArr().get(1).getOptionText();

		
	// ............................Question 26data......................
	public static String Q26_1 = questionData.get(25).getOptionArr().get(0).getOptionText();
	public static String Q26_2 = questionData.get(25).getOptionArr().get(1).getOptionText();
	public static String Q26_3 = questionData.get(25).getOptionArr().get(2).getOptionText();

	
	public static boolean isGretterThanOrEqual() {
		String desc1 = "", desc2 = "", desc3 = "", desc4 = "";

		int des1 = 0;
		int des2 = 0;
		int des3 = 0;
		int des4 = 0;
		if (SingletonClass.getInstance().getMap().get(Q9) != null) {
			if (SingletonClass.getInstance().getMap().get(Q9).get("desc_Value1").toString() != null) {
				desc1 = SingletonClass.getInstance().getMap().get(Q9).get("desc_Value1").toString();
				des1 = Integer.parseInt(desc1);
			}
			if (SingletonClass.getInstance().getMap().get(Q9).get("desc_Value2").toString() != null) {
				desc2 = SingletonClass.getInstance().getMap().get(Q9).get("desc_Value2").toString();
				des2 = Integer.parseInt(desc2);

			}
			System.out.println("====Q9 "
					+ SingletonClass.getInstance().getMap().get(Q9));

			if (SingletonClass.getInstance().getMap().get(Q9).get("checked").toString().equalsIgnoreCase("false")) {
				if (SingletonClass.getInstance().getMap().get(Q9).get("desc_Value3") != null) {
				desc3 = SingletonClass.getInstance().getMap().get(Q9).get("desc_Value3").toString();
				des3 = Integer.parseInt(desc3);
				}

			}

			if (SingletonClass.getInstance().getMap().get(Q9).get("desc_Value4") != null) {
				desc4 = SingletonClass.getInstance().getMap().get(Q9).get("desc_Value4").toString();
				des4 = Integer.parseInt(desc4);

			}

		}

		if (des1 >= 11 || des2 >= 11 || des3 >= 11 || des4 >= 11) {
			return true;
		}
		return false;

	}

	

	public static String getSingleValeData(String pos) {
		if (SingletonClass.getInstance().getMap().get(pos) != null) {
			value = SingletonClass.getInstance().getMap().get(pos).get("answer_Value").toString();
		}
		return value;

	}
	public static String getDoubleSingleValeData(String pos) {
		if (SingletonClass.getInstance().getMap().get(pos) != null) {
			value = SingletonClass.getInstance().getMap().get(pos)
					.get("subanswer_Value").toString();
		}
		return value;

	}
	// new changes 28-12-15
	public static String getdiscriptionValeData(String question, String key) {
		if (question.equalsIgnoreCase(Q23)) {
		  if (SingletonClass.getInstance().getMap().get(question) != null) {
			if (SingletonClass.getInstance().getMap().get(question).get(key) != null
					&& (!SingletonClass.getInstance().getMap().get(question)
							.get(key).toString().isEmpty())) {

				value = SingletonClass.getInstance().getMap().get(question)
						.get(key).toString();
			    }
			      else {
				          value = "I have No Explanation";
			           }
			}
		       else {
			         value="N/A";
		             }
		} else {
			
			System.out.println("### Question 24 data"+SingletonClass.getInstance().getMap().get(question));
	   if (SingletonClass.getInstance().getMap().get(question) != null) {
			if (SingletonClass.getInstance().getMap().get(question).get(key) != null
					&& (!SingletonClass.getInstance().getMap().get(question)
							.get(key).toString().isEmpty())) {

				value = SingletonClass.getInstance().getMap().get(question)
						.get(key).toString();
			} else {
				value = "N/A";
			}
		}
	    else {
			value = "N/A";
		     }
		}
		return value;

	}

}
