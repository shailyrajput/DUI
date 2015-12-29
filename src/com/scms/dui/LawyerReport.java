package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Region.Op;

import com.scms.dui.utils.SingletonClass;
import com.scms.utility.Constant;
import com.scms.utility.QuestionUtiltiy;

public class LawyerReport {
	private static HashMap<Object, Object> map;
	private String OptionValue;
	String getoptionvalues;
	private ArrayList<String> Q9arrList;
	SingletonClass singletonClass = SingletonClass.getInstance();

	public void getLawyerReport() {
		map = new HashMap<Object, Object>();
		String question1Data = invatigationString();
		System.out.println("### Question 1invatigation String data:  "+ question1Data);
		String question2Data = inCarString();
		System.out.println("### Question 2 String data:  " + question2Data);
		String question3Data = seatedString();
		System.out.println("### Question 3 String data:  " + question3Data);
		String question4Data = accidentArrivalString();
		System.out.println("### Question 4 String data:  " + question4Data);
		String question5Data = postDrivingDrinkingString();
		System.out.println("### Question 5 String data:  " + question5Data);

		String question6Data = ASDString();
		System.out.println("### Question 6 String data:  " + question6Data);
		
		String question7Data = BolusString();
		System.out.println("### Question 7 String data:  " + question7Data);

		String question10Data=RTCString();
		System.out.println("### Question 10 String data:  " + question10Data);
		
		String question11Data=responseString();
		System.out.println("### Question 11 String data:  " + question11Data);

		String question13Data=ArrivalstationString();
		System.out.println("### Question 13 String data:  " + question13Data);
		String question14Data=LawyerString();
		System.out.println("### Question 14 String data:  " + question14Data);
		
		String question15Data=SatisfiedString();
		System.out.println("### Question 15_1 String data:  " + question15Data);
		
		String question151Data=ToldPoliceString();
		System.out.println("### Question 15_2 String data:  " + question151Data);
		
		String question16_1Data=AfterhowlongString();
		System.out.println("### Question 16_1 String data:  " + question16_1Data);

		
		String question16_2Data=CalllengthString();
		System.out.println("### Question 16_2 String data:  " + question16_2Data);

		
		String question17_1Data=AfterString();
		System.out.println("### Question 17 String data:  " + question17_1Data);

		String question18Data=IssuesString();
		System.out.println("### Question 18 String data:  " + question18Data);


		ArrayList<String> Q19arrList=ReadingsArray();
		System.out.println("### Question 19 String data:  " + Q19arrList);
		
		String question20Data=FlaskString();
		System.out.println("### Question 20 String data:  " + question20Data);
		
		
		HashMap<String, String> Q23map=ObservationsArray();
		System.out.println("### Question 23String data:  " + Q23map);
		
		ArrayList<String> question24Data=BadTreatmentArray();
		System.out.println("### Question 24 String data:  " + question24Data.size());

		
		String question26Data=ReleasedString();
		System.out.println("### Question 26 String data:  " + question26Data);	

	}

	public int getSingleChoiceData(String question) {
		int p = 0;
		if (singletonClass.getMap().get(question) != null) {
			map = singletonClass.getMap().get(question);

			System.out.println("===question:::" + question);
			if (map.get("Position") != null) {
				String value = "" + map.get("Position");
				p = Integer.parseInt(value);
			}
		}
		return p;
	}

	private ArrayList<Object> getDoubleSingleChoiceData(String question) {
		ArrayList<Object> arrList = new ArrayList<Object>();
		if (singletonClass.getMap().get(question) != null) {
			if (map.get("Position") != null) {
				String value = "" + map.get("Position");
				arrList.add(0, Integer.parseInt(value));
			}
			if (map.get("SubPosition") != null) {
				String value = "" + map.get("SubPosition");
				arrList.add(1, Integer.parseInt(value));
			}
		}
		return arrList;

	}

	// ......................method Question 1 ........................
	public String invatigationString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q1);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q1_1)) {
			OptionValue = "Traffic stop";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q1_2)) {
			OptionValue = "RIDE";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q1_3)) {
			OptionValue = "Accident";

		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q1_4)) {
			OptionValue = "Care/Control";

		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q1_5)) {
			OptionValue = "In house";
		}
		return OptionValue;
	}

	// ......................method Question 2 ........................
	public String inCarString() {

		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q2);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q2_1)) {
			OptionValue = "Alone";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q2_2)) {
			OptionValue = "One Passenger";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q2_3)) {
			OptionValue = "Full Car";

		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 3 ........................
	public String seatedString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q3);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q3_1)) {
			OptionValue = "Drivers Seat";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q3_2)) {
			OptionValue = "Somewhere Else";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 4 ........................

	public String accidentArrivalString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q4);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q4_1)) {
			OptionValue = "Inside";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q4_2)) {
			OptionValue = "Outside";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 5 ........................

	public String postDrivingDrinkingString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q5);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q5_1)) {
			OptionValue = "Yes";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q5_2)) {
			OptionValue = "No";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 6 ........................

	public String ASDString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q6);
		if (getoptionvalues.equalsIgnoreCase(Constant.question6_optn1a)
				|| getoptionvalues.equalsIgnoreCase(Constant.question6_optn1b)) {
			OptionValue = "ASD YES";
		} else if (getoptionvalues.equalsIgnoreCase(Constant.question6_optn2a)
				|| getoptionvalues.equalsIgnoreCase(Constant.question6_optn2b)) {
			OptionValue = "ASD NO";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 7 ........................

	public String BolusString() {
		/*
		 * arrList= getDoubleSingleChoiceData(QuestionUtiltiy.Q7); int
		 * optionSelected = 0; if (arrList.size() == 1) { optionSelected =
		 * (Integer) arrList.get(0); } if (arrList.size() == 2) { optionSelected
		 * = (Integer) arrList.get(0);
		 * 
		 * } System.out.println("### Question seven selected Option :" +
		 * optionSelected); switch (optionSelected) { case 0:
		 * 
		 * OptionValue = "Yes"; break;
		 * 
		 * case 1: OptionValue = "No"; break;
		 * 
		 * default: OptionValue = "N/A"; break; }
		 */
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q7);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q7_1)) {
			OptionValue = "Yes";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q7_2)) {
			OptionValue = "No";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 8 ........................

	public String ASDResultString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q8);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q8_1)) {
			OptionValue = "Fail";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q8_2)) {
			OptionValue = "Other";
		}
		// else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q8_3)) {
		// OptionValue = "Refusal";
		//
		// }
		else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 9 ........................

	public ArrayList<String> TimesArray() {
		// QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q9);
		String checkValue = SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q9).get("checked").toString();
		System.out.println("###  Question 9 checkvalue is :"+checkValue);
		ArrayList<String> Q9TimeArr = new ArrayList<String>();
		Q9TimeArr.add(SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q9).get("desc_Value").toString());
		Q9TimeArr.add(SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q9).get("desc_Value1").toString());
		Q9TimeArr.add(SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q9).get("desc_Value2").toString());

		if (checkValue.equals("false")) {
			Q9TimeArr.add(SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q9).get("desc_Value3").toString());
		} else {
			Q9TimeArr.add("");
		}

		Q9TimeArr.add(SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q9).get("desc_Value4").toString());

		return Q9TimeArr;

	}

	
	// ......................method Question 10 ........................

	public String RTCString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q10);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q10_1)) {
			OptionValue = "Card";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q10_1)) {
			OptionValue = "Verbally";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;

	}

	// ......................method Question 11 ........................

	public String responseString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q11);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q11_1)) {
			OptionValue = getoptionvalues;
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q11_2)) {
			OptionValue = getoptionvalues;
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q11_1)) {
			OptionValue = getoptionvalues;
		}
		
		else {
			OptionValue = "N/A";
		}
		return OptionValue;
	}

	// ......................method Question 12 ........................

	public String timetoStationString() {
		
	
				
	     getoptionvalues = QuestionUtiltiy
				.getDoubleSingleValeData(QuestionUtiltiy.Q12);
	     if (SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q12).toString()!=null) {
				String Q12Desc = SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q12).get("desc_Value").toString();
//			if (Q12Desc!= null) {
//				if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q12_1)) {
//					OptionValue = Q12Desc + " " + "Minutes Ok";
//
//				} else if (getoptionvalues
//						.equalsIgnoreCase(QuestionUtiltiy.Q12_2)) {
//					OptionValue = Q12Desc + " " + "Minutes too long";
//
//				} else {
//					OptionValue = "N/A";
//				}
//			}
			
		}
		
		return OptionValue;

	}

	// ......................method Question 13 ........................

	public String ArrivalstationString() {
		 getoptionvalues = QuestionUtiltiy.getDoubleSingleValeData(QuestionUtiltiy.Q13);
		 if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q13_1)) {
			 OptionValue="Inside right away";
			
		}
		 else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q13_2)) {
			 if (SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q13) != null) {
			 if (SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q13).get("desc_Value").toString()!=null) {
				 OptionValue="had to wait "+SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q13).get("desc_Value").toString()+" Minutes";
			    }
			 }
			
		}
		else {
			OptionValue="N/A";
		}
		
		return OptionValue;

	}

	// ......................method Question 14 ........................

	public String LawyerString() {
		getoptionvalues=QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q14);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q14_1)) {
            OptionValue="yes";			
		}
		else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q14_2)) {
			getoptionvalues=QuestionUtiltiy.getDoubleSingleValeData(QuestionUtiltiy.Q14);
			if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q14_21)) {
				OptionValue="No chance";
				
			}
			else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q14_22)) {
				OptionValue="Declined";
			}
			else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q14_23)) {
				OptionValue="Didn't know";
			}
			else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q14_24)) {
				OptionValue="No Ans";
			}
			
		}
		else {
			OptionValue="N/A";
		}
		
		return OptionValue;

	}

	// ......................first method Question 15 ........................

	public String SatisfiedString() {
		getoptionvalues=QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q15);
		System.out.println("### Question  15 firt method data :" +getoptionvalues);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q15_1)) {
            OptionValue="yes";	
            System.out.println("### Question  15 firt method data 1 :" +OptionValue);
            
		}
		else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q15_2)) {
			  OptionValue="No";	
			  System.out.println("### Question  15 firt method data 2:" +OptionValue);
		}
		else {
			 OptionValue="N/A";	
			 System.out.println("### Question  15 firt method data 3:" +OptionValue);
		}
		
		return OptionValue;

	}
	//............................second method Question 15.....................
	public String ToldPoliceString(){
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q15);
		System.out.println("### Question  15 second  method data :" +getoptionvalues);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q15_2)) {
			getoptionvalues = QuestionUtiltiy
					.getDoubleSingleValeData(QuestionUtiltiy.Q15);
			System.out.println("### Question  15 second  method data getoptionvalues :" +getoptionvalues);

			if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q15_21)) {
				OptionValue = "No";
				System.out.println("### Question  15 second  method data getoptionvalues 1 :" +OptionValue);
			} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q15_22)) {
				OptionValue = "yes no other call";
				System.out.println("### Question  15 second  method data getoptionvalues 2 :" +OptionValue);
			} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q15_23)) {
				OptionValue = "Yes";
				System.out.println("### Question  15 second  method data getoptionvalues 3 :" +OptionValue);
			}
		} 
		else {
			OptionValue = "N/A";
		     }

		return OptionValue;
		
	}
	
	// ............................second method Question 16 1st  data.....................

	private String AfterhowlongString() {
		getoptionvalues = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q16, "desc_Value");
		return getoptionvalues;
	}

	
	// ............................second method Question 16 2nd data.....................

	private String CalllengthString() {
		getoptionvalues = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q16, "desc_Value2");
		return getoptionvalues;
	}
	
	
	// ............................second method Question 17 1st  data.....................

	private String AfterString() {
		getoptionvalues = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q17, "desc_Value");
		return getoptionvalues;
	}
	
	// ............................second method Question 18 1st  data.....................

	private String IssuesString() {

		System.out.println("###18 value"+ singletonClass.getMap().get(QuestionUtiltiy.Q18));
		getoptionvalues = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q18, "desc_Value");
		return getoptionvalues;
	}

	private ArrayList<String> ReadingsArray() {
		Q9arrList = new ArrayList<String>();
		String value  = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q19, "desc_Value");
		String value1 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q19, "desc_Value1");
		String value2 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q19, "desc_Value2");
		String value3 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q19, "desc_Value3");
	   if(!value.equalsIgnoreCase("N/A") || !value1.equalsIgnoreCase("N/A") 
			   ||!value2.equalsIgnoreCase("N/A") || !value3.equalsIgnoreCase("N/A") )
	   {
		   Q9arrList.add(value);
		   Q9arrList.add(value1);
		   Q9arrList.add(value2); 
		   Q9arrList.add(value3);
	   } 
	   else
	   {
		   Q9arrList.add("N/A");   
	   }
	
	return Q9arrList;
	}
	
	// ............................second method Question 20 1st  data.....................
	private String FlaskString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q20);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q20_1)) {
			OptionValue = "Yes";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q20_2)) {
			OptionValue = "No";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;
	}

 /////............................method Question 23  data.....................
	private HashMap<String, String>  ObservationsArray() {
		HashMap<String , String> map = new HashMap<String, String>();
		String value  = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q23,"descValue");
		String value1 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q23,"descValue1");
		String value2 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q23, "descValue2");
		String value3 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q23, "descValue3");
		String value4  = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q23,"descValue4");
		String value5 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q23, "descValue5");
		  
		map.put(QuestionUtiltiy.Q21_1, value);
		map.put(QuestionUtiltiy.Q21_2, value1);
		map.put(QuestionUtiltiy.Q21_3, value2);
		map.put(QuestionUtiltiy.Q21_4, value3);
		map.put(QuestionUtiltiy.Q21_5, value4);
		map.put(QuestionUtiltiy.Q21_6, value5);
		
		System.out.println("===23map"+map);
		  if(map.size()==0 )
		   {
			  Q9arrList.add("N/A");   
			  
		   } 
		   
		return map;
	}

//////............................second method Question 24  data.....................

	private ArrayList<String> BadTreatmentArray() {		
		Q9arrList = new ArrayList<String>();
		
		String value  = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_one");
		String value1 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_two");
		String value2 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_three");
		String value3 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_four");
		String value4  = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_five");
		String value5 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_six");
		String value6 = QuestionUtiltiy.getdiscriptionValeData(QuestionUtiltiy.Q24, "check_seven");

		
		if(!value.equalsIgnoreCase("N/A") || !value1.equalsIgnoreCase("N/A") 
			   ||!value2.equalsIgnoreCase("N/A") || !value3.equalsIgnoreCase("N/A") 
			   || !value4.equalsIgnoreCase("N/A") || !value5.equalsIgnoreCase("N/A") 
			   ||!value6.equalsIgnoreCase("N/A")  )
	   {
		   Q9arrList.add(value);
		   Q9arrList.add(value1);
		   Q9arrList.add(value2); 
		   Q9arrList.add(value3);
		   Q9arrList.add(value4);
		   Q9arrList.add(value5);
		   Q9arrList.add(value6); 
		   System.out.println("===Q9arraylist"+Q9arrList);
	   } 
	   if(Q9arrList.size()==0) 
	   {
		   Q9arrList.add("N/A");   
	   }
		return Q9arrList;
	}


	////// ............................second method Question 26 1st  data.....................
	 private String ReleasedString() {
		getoptionvalues = QuestionUtiltiy.getSingleValeData(QuestionUtiltiy.Q26);
		if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q26_1)) {
			OptionValue = "less than hour";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q26_2)) {
			OptionValue = "1-2 hours";
		} else if (getoptionvalues.equalsIgnoreCase(QuestionUtiltiy.Q26_3)) {
			OptionValue = "More than 2 hours";
		} else {
			OptionValue = "N/A";
		}
		return OptionValue;
	}

}
