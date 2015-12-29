package com.scms.dui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dui.R;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.TwentyFiveDataUtils;
import com.scms.dui.utils.Utils;
import com.scms.noghteh.JustifiedTextView;
import com.scms.utility.Constant;
import com.scms.utility.QuestionUtiltiy;

public class LawyerLetter extends Activity implements OnClickListener {
	private List<ArrayList<SaveQuestionDataUtils>> arrList1;
	private SingletonClass singletonClass;

	private ListView mList;
	private TextView letter_ottawa_number_txt, letter_toronoto_number_txt,
			letter_montreal_num_txt, letter_toll_no_txt, letter_email_txt;
	private LinearLayout letter_contact_layout;
	private TextView mName;
	private String name;
	private JustifiedTextView txtview, txt_one_two_ans;
	private String allAnswer;
	private ArrayList<SaveQuestionDataUtils> saveDatalist;
	private ArrayList<TwentyFiveDataUtils> twentyFiveData;
	private String value1, value2, value3, value4, value5, value6, value7,
			value8, value9, value10;
	private int savePosition;
	private String str_25_op1_op2_op6, str_25_single_optn,
			str_25_multiple_optn, str_ques1, str_19_txt, commonString;
	private StringBuilder first_answer;

	private HashMap<Object, Object> map;
	boolean condition = false;
	private ArrayList<Object> arrList;
	String Q161stValue, Q162ndValue, Q13Value, Q12Value;
	String tollno, ottawano, tornotono, montrealno, letterEmailId;
    private QuestionUtiltiy questionUtiltiy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lawyer_letter);
		setCustomActionBar();
		init();
		twentyFiveData = Constant.twentyFiveData;
		savePosition = getIntent().getIntExtra("saveposition", 0);
		singletonClass = SingletonClass.getInstance();
		saveDatalist = new ArrayList<SaveQuestionDataUtils>();
		questionUtiltiy= new QuestionUtiltiy();
		arrList = new ArrayList<Object>();
		System.out.println("==== savePosition:::::::" + savePosition);
		setTextViewdata();
		// conditionCheck();
	}

	private void setCustomActionBar() {
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setText("DUI Questionnaire");
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);
		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.alertDialogTwoButton(LawyerLetter.this, "Attention!",
						"Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void init() {

		str_25_op1_op2_op6 = " causing bodily harm";
		str_25_multiple_optn = "You have been charged with criminal offences , namely ";
		str_25_single_optn = "You have been charged with a criminal offence , namely ";
		str_19_txt = "These are serious criminal offences. You can receive a criminal record, go to jail and lose your licence in addition to other significant consequences. For these reasons, you should treat this matter very seriously. Please do not treat this information as legal advice but rather as information that you should discuss with a criminal lawyer prior to your next court date.";
		str_ques1 = "You have indicated that the police first got involved when";

		commonString = "Although this does not automatically mean your case will be thrown out,"
				+ " this delay could provide you a few ways to defend this case. Also, even if your "
				+ "samples were taken within 2 hours, any unnecessary or unexplained delays could also be "
				+ "scrutinized in court.";

		mName = (TextView) findViewById(R.id.name_text2);
		txtview = (JustifiedTextView) findViewById(R.id.txtview25_letter);
		letter_contact_layout = (LinearLayout) findViewById(R.id.letter_contact_layout);
		letter_email_txt = (TextView) findViewById(R.id.letter_email_txt);
		letter_montreal_num_txt = (TextView) findViewById(R.id.letter_montreal_num_txt);
		letter_ottawa_number_txt = (TextView) findViewById(R.id.letter_ottawa_number_txt);
		letter_toronoto_number_txt = (TextView) findViewById(R.id.letter_toronoto_number_txt);
		letter_toll_no_txt = (TextView) findViewById(R.id.letter_toll_no_txt);
		letter_email_txt.setOnClickListener(this);
		letter_montreal_num_txt.setOnClickListener(this);
		letter_ottawa_number_txt.setOnClickListener(this);
		letter_toll_no_txt.setOnClickListener(this);
		letter_toronoto_number_txt.setOnClickListener(this);

		map = new HashMap<Object, Object>();
		name = Constant.name;
		if (name != null) {
			name = name.toLowerCase();
			System.out.println("=== name is in lower case :" + name);
			name = name.substring(0, 1).toUpperCase()
					+ name.substring(1).toLowerCase();
			mName.setText(name + ":");
		}

		System.out.println("=== QuestionUtiltiy.Q8" + QuestionUtiltiy.Q8);
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

	private void setTextViewdata() {

		first_answer = new StringBuilder();
		// 25 Question Data

		first_answer = get25QuestionCondition();
		System.out.println("====first_answer 25 data" + first_answer);

		// Q19 Data
		first_answer = Q19Conditions();
		System.out.println("====first_answer 19 data" + first_answer);

		// Q6 8 Data
		first_answer = Q68Conditions();
		System.out.println("====first_answer 6 and 8 data" + first_answer);

		first_answer = Q12345Conditions();
		System.out.println("====first_answer 1,2 ,3 ,4 data" + first_answer);

		// Q9 & Q19 & Q12 & Q13 && 16 data condition
		// //////////////////////////////////////////////////////////////////////////////////////////////

		first_answer = Q919121316condition();
		System.out.println("====first_answer 9191213 condition data "
				+ first_answer);

		// Q5720Condition
		// //
		// //////////////////////////////////////////////////////////////////////////////////////////////
		first_answer = Q5720Condition();
		System.out.println("====first_answer 5 7 20" + first_answer);

		// ques212223Conditions
		// //////////////////////////////////////////////////////////////////////////////////////////////
		first_answer = Q212223Condition();

		System.out.println("===== first answer 212223 condition data"
				+ first_answer);

		// finalConditions
		// //////////////////////////////////////////////////////////////////////////////////////////////
		first_answer = FinalCondition();

		// System.out.println("===== FRST ANSWER FINALCONDITION CONDITION DATA"+
		// FinalCondition());
		txtview.setText(first_answer + "");
		txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		txtview.setLineSpacing(5);
		txtview.setTextColor(Color.WHITE);
		txtview.setAlignment(Align.LEFT);

	}

	private StringBuilder Q12345Conditions() {

		Toast.makeText(getApplicationContext(),
				"LawyerLetter.Q12345Conditions()", Toast.LENGTH_LONG).show();

		// first Question data
		map = singletonClass
				.getMap()
				.get("What best describes how the police initially got involved in your case?");
		System.out.println("== map value is :" + map);
		if (singletonClass
				.getMap()
				.get("What best describes how the police initially got involved in your case?") != null) {
			if (map.get("answer_Value") != null) {
				// Question 1 option 1 selected
				if ((map.get("answer_Value").toString())
						.equalsIgnoreCase("The police pulled you over in a traffic stop")) {

					first_answer.append("\n");
					first_answer.append("\n");
					first_answer.append(str_ques1 + " ");
					first_answer.append(((map.get("answer_Value").toString())
							.toLowerCase()));
					// second question any option selected
					map = singletonClass
							.getMap()
							.get("At the time the police first spoke to you, how many people were in your vehicle?");
					if (singletonClass
							.getMap()
							.get("At the time the police first spoke to you, how many people were in your vehicle?") != null) {
						System.out
								.println("=== himani second question activate");
						if (map.get("answer_Value") != null) {
							System.out
									.println("=== himani first answer in lawyer letter :"
											+ first_answer);
							first_answer.append(" and ");
							first_answer.append((map.get("answer_Value")
									.toString()).toLowerCase());
							txtview.setText(first_answer + "");
							txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
							txtview.setLineSpacing(5);
							txtview.setTextColor(Color.WHITE);
							txtview.setAlignment(Align.LEFT);
						}
					}
				}
				// Question 1 option 2 selected
				else if ((map.get("answer_Value").toString())
						.equalsIgnoreCase("You drove up to a police \"RIDE\" spotcheck")) {
					first_answer.append("\n");
					first_answer.append("\n");
					first_answer.append(str_ques1 + " ");
					String str = (map.get("answer_Value").toString());
					str = str.replace("You", "you");
					first_answer.append(str);

					// second question any option selected
					map = singletonClass
							.getMap()
							.get("At the time the police first spoke to you, how many people were in your vehicle?");
					if (singletonClass
							.getMap()
							.get("At the time the police first spoke to you, how many people were in your vehicle?") != null) {
						System.out
								.println("=== himani second question activate");
						if (map.get("answer_Value") != null) {
							System.out
									.println("=== himani first answer in lawyer letter :"
											+ first_answer);
							first_answer.append(" and ");

							first_answer.append((map.get("answer_Value")
									.toString()).toLowerCase());
							txtview.setText(first_answer + "");
							txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
							txtview.setLineSpacing(5);
							txtview.setTextColor(Color.WHITE);
							txtview.setAlignment(Align.LEFT);

						}
					}
				}
				// Question 1 option 3 selected
				else if ((map.get("answer_Value").toString())
						.equalsIgnoreCase("You were involved in a traffic  accident")) {
					first_answer.append("\n");
					first_answer.append("\n");
					first_answer.append(str_ques1 + " ");
					first_answer.append((map.get("answer_Value").toString()
							.toLowerCase()) + ".");

					map = singletonClass
							.getMap()
							.get("When the police arrived at the accident were you in your vehicle or standing outside the vehicle?");
					if (singletonClass
							.getMap()
							.get("When the police arrived at the accident were you in your vehicle or standing outside the vehicle?") != null) {
						if (map.get("answer_Value") != null) {
							// Question 4 option 1 selected
							if ((map.get("answer_Value").toString())
									.equalsIgnoreCase("Inside vehicle")) {
								first_answer
										.append("you have indicated that you were involved in an accident and that when the police arrived you were ");
								first_answer.append(map.get("answer_Value")
										.toString().toLowerCase());
								txtview.setText(first_answer + ".");
								txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP,
										14);
								txtview.setLineSpacing(5);
								txtview.setTextColor(Color.WHITE);
								txtview.setAlignment(Align.LEFT);

							} else if ((map.get("answer_Value").toString())
									.equalsIgnoreCase("Outside of your vehicle")) {
								first_answer
										.append("You have indicated that you were involved in an accident and that when the police arrived you were ");
								first_answer.append(map.get("answer_Value")
										.toString().toLowerCase()
										+ ".");
								first_answer
										.append("Since you were not in the car when the police got to the scene, they may have only learned that you were the driver because you told them so. If that is the case, it is possible that your case may have a specific defence available in these circumstances called a �Soules Defence�. You should discuss this with a criminal lawyer.");

								txtview.setText(first_answer + "");
								txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP,
										14);
								txtview.setLineSpacing(5);
								txtview.setTextColor(Color.WHITE);
								txtview.setAlignment(Align.LEFT);

							}
						}

					}
				}
				// Question 1 option 4 selected
				else if ((map.get("answer_Value").toString())
						.equalsIgnoreCase("You were sleeping/sitting in an unmoving vehicle and police came up to you")) {
					first_answer.append("\n");
					first_answer.append("\n");
					first_answer.append(str_ques1 + " ");
					first_answer.append((map.get("answer_Value").toString()
							.toLowerCase()) + ".");
					// Question 3
					map = singletonClass
							.getMap()
							.get("Were you in the driver�s seat of the vehicle, or were you somewhere else in the vehicle when the police arrived?");
					if (singletonClass
							.getMap()
							.get("Were you in the driver�s seat of the vehicle, or were you somewhere else in the vehicle when the police arrived?") != null) {
						if (map.get("answer_Value") != null) {
							// Question 3 option 1 selected
							if ((map.get("answer_Value").toString())
									.equalsIgnoreCase("Driver�s seat")) {
								// first_answer.append((map.get("answer_Value")
								// .toString().toLowerCase()));
								txtview.setText(first_answer + ".");
								txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP,
										14);
								txtview.setLineSpacing(5);
								txtview.setTextColor(Color.WHITE);
								txtview.setAlignment(Align.LEFT);

							}
							// Question 3 option 2 selected
							else if ((map.get("answer_Value").toString())
									.equalsIgnoreCase("Somewhere else (i.e. passenger seat, back seat, trunk�)")) {
								// first_answer.append((map.get("answer_Value")
								// .toString().toLowerCase()));
								first_answer
										.append("Even though you were not in the driver\'n seat, the police officer still felt that you were in control of the vehicle enough to proceed with his/her investigation.");
								txtview.setText(first_answer + "");
								txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP,
										14);
								txtview.setLineSpacing(5);
								txtview.setTextColor(Color.WHITE);
								txtview.setAlignment(Align.LEFT);
							}
						}
					}

				}
				// Question 1 option 5 selected
				else if ((map.get("answer_Value").toString())
						.equalsIgnoreCase("Police came to your house and you were already inside")) {
					first_answer.append("\n");
					first_answer.append("\n");
					first_answer.append(str_ques1 + " ");
					first_answer.append((map.get("answer_Value").toString()
							.toUpperCase()));
					// Question 5
					map = singletonClass
							.getMap()
							.get("While you were at home � after driving but before the police arrived. Did you drink alcohol?");
					if (singletonClass
							.getMap()
							.get("While you were at home � after driving but before the police arrived. Did you drink alcohol?") != null) {

						if (map.get("answer_Value") != null) {
							System.out
									.println("=== himani first answer in lawyer letter :"
											+ first_answer);

							first_answer.append(" "
									+ (map.get("answer_Value").toString())
											.toLowerCase());
							txtview.setText(first_answer + "");
							txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
							txtview.setLineSpacing(5);
							txtview.setTextColor(Color.WHITE);
							txtview.setAlignment(Align.LEFT);
						}

					}

				}

			}
			// first_answer.append("\n");
			// first_answer.append("\n");
			first_answer
					.append("\n\nMany people often think that DUI cases are unwinnable and you should just plead guilty. This is usually not a good idea. This area of the law is very technical and you should speak to a lawyer about your case before making any decisions.");

			first_answer
					.append("\n\nIf you want to have a free consultation to discuss your case, you can contact David Anber, a criminal defence lawyer who knows this area of the law well.");
			txtview.setText(first_answer + "");
			txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			txtview.setLineSpacing(5);
			txtview.setTextColor(Color.WHITE);
			txtview.setAlignment(Align.LEFT);

		}
		return first_answer;

	}

	private StringBuilder Q68Conditions() {
		// six and eight data condition

		Toast.makeText(getApplicationContext(), "LawyerLetter.Q68Conditions()",
				Toast.LENGTH_LONG).show();

		map = singletonClass.getMap().get(QuestionUtiltiy.Q6);

		if (singletonClass.getMap().get(QuestionUtiltiy.Q6) != null) {
			System.out.println("=== himani six question match");
			// if (map.get("answer_Value") != null) {
			// //first_answer.append(map.get("answer_Value"));
			// } else
			if (map.get("answer_Value") != null
					&& map.get("subanswer_Value") != null) {
				map = singletonClass.getMap().get(QuestionUtiltiy.Q8);
				if (singletonClass.getMap().get(QuestionUtiltiy.Q8) != null) {
					if (map.get("answer_Value") != null) {
						if ((map.get("answer_Value").toString())
								.equalsIgnoreCase(QuestionUtiltiy.Q8_1)) {
							first_answer.append("\n");
							first_answer.append("\n");
							first_answer
									.append("You were arrested after "
											+ "you failed a roadside screening device test.");
							txtview.setText(first_answer + "");
							txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
							txtview.setLineSpacing(5);
							txtview.setTextColor(Color.WHITE);
							txtview.setAlignment(Align.LEFT);

						} else if ((map.get("answer_Value").toString())
								.equalsIgnoreCase(QuestionUtiltiy.Q8_2)) {
							first_answer.append("\n");
							first_answer.append("\n");
							first_answer
									.append("You were arrested after "
											+ "a police officer claims that he/she believed that you were sufficiently affected by alcohol and shouldn�t have been driving.");
							txtview.setText(first_answer + "");
							txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
							txtview.setLineSpacing(5);
							txtview.setTextColor(Color.WHITE);
							txtview.setAlignment(Align.LEFT);

						}

					}
				}

			}
		}
		return first_answer;

	}

	private StringBuilder Q19Conditions() {

		Toast.makeText(getApplicationContext(), "LawyerLetter.Q19Conditions()",
				Toast.LENGTH_LONG).show();

		// Question 19 condition
		map = singletonClass
				.getMap()
				.get("What were your breath readings, and at what time were they taken?");
		if (singletonClass
				.getMap()
				.get("What were your breath readings, and at what time were they taken?") != null) {
			if (map.get("desc_Value") != null && map.get("desc_Value2") != null) {
				allAnswer = "Your breath readings were "
						+ map.get("desc_Value").toString() + " and "
						+ map.get("desc_Value2") + "." + str_19_txt;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

			} else if (map.get("desc_Value") != null
					&& map.get("desc_Value2") == null) {

				allAnswer = "Your breath readings were"
						+ map.get("desc_Value").toString() + "." + str_19_txt;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

			} else if (map.get("desc_Value") == null
					&& map.get("desc_Value2") != null) {

				allAnswer = "Your breath readings were"
						+ map.get("desc_Value2").toString() + "." + str_19_txt;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

			}

		}
		return first_answer;

	}

	// ......................----------------------finalCondition-----------------------...................

	private StringBuilder FinalCondition() {

		Toast.makeText(getApplicationContext(),
				"LawyerLetter.FinalCondition()", Toast.LENGTH_LONG).show();

		// StringBuilder finalCondition = new StringBuilder();
		HashMap<Object, Object> map9 = new HashMap<Object, Object>();
		HashMap<Object, Object> map24 = new HashMap<Object, Object>();

		List<String> list_24Des = new ArrayList<String>();
		int p26 = 0, p14 = 0, p10 = 0, p15 = 0, sp14 = 0, sp15 = 0;

		if (singletonClass.getMap().get(QuestionUtiltiy.Q9) != null)

		{
			map9 = singletonClass.getMap().get(QuestionUtiltiy.Q9);

		}
		if (singletonClass.getMap().get(QuestionUtiltiy.Q10) != null)

		{
			// map10 = singletonClass.getMap().get(QuestionUtiltiy.Q10);
			p10 = getSingleChoiceData(QuestionUtiltiy.Q10);
		}
		if (singletonClass.getMap().get(QuestionUtiltiy.Q14) != null)

		{
			// /map14 = singletonClass.getMap().get(QuestionUtiltiy.Q14);
			arrList = getDoubleSingleChoiceData(QuestionUtiltiy.Q14);

			if (arrList.size() == 1) {
				p14 = (Integer) arrList.get(0);
			}
			if (arrList.size() == 2) {
				p14 = (Integer) arrList.get(0);
				sp14 = (Integer) arrList.get(1);
			}
		}
		if (singletonClass.getMap().get(QuestionUtiltiy.Q15) != null)

		{
			// map15 = singletonClass.getMap().get(QuestionUtiltiy.Q15);
			arrList = getDoubleSingleChoiceData(QuestionUtiltiy.Q15);

			if (arrList.size() == 1) {
				p15 = (Integer) arrList.get(0);
			}
			if (arrList.size() == 2) {
				p15 = (Integer) arrList.get(0);
				sp15 = (Integer) arrList.get(1);
			}

		}
		System.out.println("===24 map"
				+ singletonClass.getMap().get(QuestionUtiltiy.Q24));
		// fetch 24 data
		if (singletonClass.getMap().get(QuestionUtiltiy.Q24) != null)

		{
			map24 = singletonClass.getMap().get(QuestionUtiltiy.Q24);

			if (map24.get("check_one") != null) {
				list_24Des.add("" + map24.get("check_one"));

				System.out.println("======check_one" + map.get("check_one"));
			}
			if (map24.get("check_two") != null /*
												 * &&
												 * map24.get("check_two").toString
												 * ().isEmpty()
												 */) {
				list_24Des.add("" + map24.get("check_two"));
				System.out.println("======check_two" + map.get("check_two"));
			}
			if (map24.get("check_three") != null) {
				list_24Des.add("" + map24.get("check_three"));
				System.out.println("======check_three"
						+ map24.get("check_three"));
			}
			if (map24.get("check_four") != null) {
				list_24Des.add("" + map24.get("check_four"));
			}
			if (map24.get("check_five") != null) {
				list_24Des.add("" + map24.get("check_five"));
			}
			if (map24.get("check_six") != null) {
				list_24Des.add("" + map24.get("check_six"));
			}
			if (map24.get("check_seven") != null) {
				list_24Des.add("" + map24.get("check_seven"));
			}
			System.out.println("== 24 question list :" + list_24Des);
		}
		if (singletonClass.getMap().get(QuestionUtiltiy.Q26) != null)

		{
			p26 = getSingleChoiceData(QuestionUtiltiy.Q26);

		}

		// add fianl condition

		if (list_24Des.size() != 0) {
			allAnswer = "\n\nFinally, you indicated that:\n";
			first_answer.append(allAnswer);

			for (int i = 0; i < list_24Des.size(); i++) {
				allAnswer = "\n\u2022 " + list_24Des.get(i);
				System.out.println("== 24 question :");

			}

			first_answer.append(allAnswer);
			if (p26 == 2) {
				{
					allAnswer = "\n\u2022 You were held in custody for a longer period of time than may have been necessary.";

					first_answer.append(allAnswer);
				}
				if (map9.get("desc_Value3") == null
						&& map9.get("checked") != null) {
					allAnswer = "\n\u2022 The officer may not have read you your rights at the proper time.";
					first_answer.append(allAnswer);
				}
				if (p10 == 1) {

					allAnswer = "\n\u2022 The officer may not have worded your rights properly.";
					first_answer.append(allAnswer);

				}
				if ((p14 == 1) && ((sp14 == 0) || (sp14 == 2) || (sp14 == 3))) {
					allAnswer = "\n\u2022 You did not get to speak to a lawyer at the police station.";
					first_answer.append(allAnswer);

				}

				if ((p15 == 1) && (sp15 == 2)) {
					allAnswer = "\n\u2022 The legal advice you received while at the police station may have been inadequate.";
					first_answer.append(allAnswer);
				}

			}
			if (list_24Des.size() > 1) {
				allAnswer = "\n\nThese are things which are of some concern. Make sure to raise this with your lawyer when discussing your case.";
				first_answer.append(allAnswer);
			} else {
				allAnswer = "\n\nThis is of some concern. Make sure to raise this with your lawyer when discussing your case.";
				first_answer.append(allAnswer);
			}
		} else if ((list_24Des.size() == 0) && (p26 == 2)) {
			allAnswer = "\n\nFinally, you indicated that:\n";
			allAnswer = "\n\u2022 You were held in custody for a longer period of time than may have been necessary.";
			// letterString = [letterString stringByAppendingString:str1];
			allAnswer = "\n\nThese are things which are of some concern. Make sure to raise this with your "
					+ "lawyer when discussing your case.";
			first_answer.append(allAnswer);
		}

		if ((list_24Des.size() == 0) && (p26 != 2)) {
			letter_contact_layout.setVisibility(View.GONE);
			allAnswer = "\n\nFinally, if you wish to have a free consultation to further discuss your case, please call  "
					+ "24hrs/day.";
			first_answer.append(allAnswer);

			contactLawyer();
		} else {
			allAnswer = "\n\nIf you wish to have a free consultation to further discuss your case, please call  24hrs/day.";
			letter_contact_layout.setVisibility(View.GONE);
			first_answer.append(allAnswer);
			contactLawyer();
		}

		System.out.println("first_answer in final condition" + allAnswer);
		return first_answer;

	}

	private void contactLawyer() {
		letter_contact_layout.setVisibility(View.VISIBLE);

	}

	// ......................----------------------Q212223Condition-----------------------...................

	private StringBuilder Q212223Condition() {

		Toast.makeText(getApplicationContext(),
				"LawyerLetter.Q212223Condition()", Toast.LENGTH_LONG).show();

		// StringBuilder Q212223 = new StringBuilder();
		int i = 0;
		List<Object> list_23 = new ArrayList<Object>();
		List<Object> list_23Des = new ArrayList<Object>();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		if (singletonClass.getMap().get(QuestionUtiltiy.Q23) != null)

		{
			map = singletonClass.getMap().get(QuestionUtiltiy.Q23);

			// take checked data into list23
			if (map.get("check_one") != null && map.get("check_one").equals("true")) {

				list_23.add("smell like alcohol");
			} else if (!map.get("descValue").toString().isEmpty()) {
				list_23Des.add("" + map.get("descValue"));
			}

			if (map.get("check_two") != null && map.get("check_two").equals("true")) {
				list_23.add("Someone saw you driving erratically");
			} else if (!map.get("descValue1").toString().isEmpty()) {
				list_23Des.add("" + map.get("descValue1"));
			}

			if (map.get("check_three") != null
					&& map.get("check_three").equals("true")) {
				list_23.add("be driving erratically");
			} else if (!map.get("descValue2").toString().isEmpty()) {
				list_23Des.add("" + map.get("descValue2"));
			}

			if (map.get("check_four") != null && map.get("check_four").equals("true")) {
				list_23.add("slur your speech");
			} else if (!map.get("descValue3").toString().isEmpty()) {
				list_23Des.add("" + map.get("descValue3"));
			}

			if (map.get("check_five") != null && map.get("check_five").equals("true")) {
				list_23.add("have difficulty standing");
			} else if (!map.get("descValue4").toString().isEmpty()) {
				list_23Des.add("" + map.get("descValue4"));
			}

			if (map.get("check_six") != null && map.get("check_six").equals("true")) {
				list_23.add("have difficulty walking");
			} else if (!map.get("descValue5").toString().isEmpty()) {
				list_23Des.add("" + map.get("descValue5"));
			}

		}
		System.out.println("======list_23Des" + list_23Des.size() + "list"
				+ list_23Des);

		// getting checked data condition

		// System.out.println("======list_23Des" + list_23.size());
		if (list_23.size() > 0) {
			first_answer
					.append("\n\nAdditionally, there was some indication that you were observed to ");
			for (i = 0; i < list_23.size(); i++) {
				first_answer.append(list_23.get(i));
				if (list_23.size() > 1) {
					if (i < list_23.size() - 2) {
						first_answer.append(", ");
					} else if (i < list_23.size() - 1) {

						first_answer.append(" and ");
					}
				}
			}
		}
	

		// No explanation......////////////////////

		if (list_23Des.size() == 0) {
			if (list_23.size() == 1)
				first_answer
						.append(" Even though there do not appear to be any reasons to explain this observation, these are still only some of the things a court would consider if deciding that you were impaired or not.");
			else
				first_answer
						.append(" Even though there do not appear to be any reasons to explain these observations, these are still only some of the things a court would consider if deciding that you were impaired or not.");

			// Every option has explanation.....
		}

		else if (list_23Des.size() > 0) {
			if (list_23Des.size() == 1)
				first_answer
						.append(" You have provided explanation for this observation. This explanation can be presented to the judge to try and raise doubt as to whether or not you were actually impaired by alcohol.");
			else
				first_answer
						.append(" You have provided explanations for each of these observations. These explanations can be presented to the judge to try and raise doubt as to whether or not you were actually impaired by alcohol.");
			// some Explanain and some not....
		} else
			first_answer
					.append(" You have provided explanations for some of these observations. Explanations can be presented to the judge to try and raise doubt as to whether or not you were actually impaired by alcohol.");

		System.out.println("======Q212223" + first_answer);

		return first_answer;
	}

	// ...........................----------------------Q5720Condition-----------------------........................
	
	private StringBuilder Q5720ConditionNew(){
		
		
		return first_answer;
		
	}
	private StringBuilder Q5720Condition() {
		// StringBuilder Q5720 = new StringBuilder();
		int p5 = getSingleChoiceData(QuestionUtiltiy.Q5);

		int p20 = getSingleChoiceData(QuestionUtiltiy.Q20);

		arrList = getDoubleSingleChoiceData(QuestionUtiltiy.Q7);
		int p = 0, sp = 0;
		if (arrList.size() == 1) {
			p = (Integer) arrList.get(0);
		}
		if (arrList.size() == 2) {
			p = (Integer) arrList.get(0);
			sp = (Integer) arrList.get(1);
		}
		System.out.println("=== p5" + p5);
		System.out.println("=== p20" + p20);
		System.out.println("=== p" + p);
		System.out.println("=== sp" + sp);
		if (p5 == 0 || (p == 0 && sp == 0) || p20 == 0) {
			System.out.println("LawyerLetter.Q5720Condition()   1st");
			first_answer
					.append("\n\nOther issues which could potentially be used to defend you are:\n\nYou have indicated that ");

			if ((p5 == 0) && (p == 0 && sp == 0) && (p20 == 0)) {

				System.out.println("LawyerLetter.Q5720Condition()   2st");
				first_answer
						.append("you drank alcohol after you had finished driving and got "
								+ "out of your car and you also indicated that you drank a large quantity of alcohol "
								+" 5-10 minutes before the police made you exit your vehicle and "
								+ "you also indicated that you drank alcohol after the police arrested you.");

			}

			else if ((p5 == 0) && (p == 0)) {

				System.out.println("LawyerLetter.Q5720Condition()   3st");
				first_answer
						.append("you drank alcohol after you had finished driving and got out of your car and you also indicated that you drank a large "
								+ "quantity of alcohol 5-10 minutes before the police made you exit your vehicle.");

			}

			else if ((p == 0) && (p20 == 0)) {

				System.out.println("LawyerLetter.Q5720Condition()   4st");
				first_answer
						.append("you drank a large quantity of alcohol 5-10 minutes before the police made"
								+ " you exit your vehicle and you also indicated that you drank alcohol after the"
								+ " police arrested you.");

			} else if ((p20 == 0) && (p5 == 0)) {

				System.out.println("LawyerLetter.Q5720Condition()   4st");
				first_answer
						.append("you drank alcohol after you had finished driving and got out of your car and "
								+ "you also indicated that you drank alcohol after the police arrested you.");

			} else if (p5 == 0) {

				System.out.println("LawyerLetter.Q5720Condition()   5st");
				first_answer
						.append("you drank alcohol after you had finished driving and got out of your car.");

			} else if (p == 0) {
				first_answer
						.append("you drank a large quantity of alcohol 5-10 minutes before the police made you exit "
								+ "your vehicle.");

			} else if (p20 == 0) {

				System.out.println("LawyerLetter.Q5720Condition()   6st");
				first_answer
						.append("you drank alcohol after the police arrested you.");
			} else
				System.out.println("LawyerLetter.Q5720Condition()   7st");
			first_answer
					.append(" Depending on a few factors, the timing of when you drank alcohol might "
							+ "prove that you were not actually over the legal limit when you drove. This is clearly "
							+ "something you should investigate further.");

		}

		System.out.println("==== allanswerfor5720:::" + first_answer);

		return first_answer;
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

	// ...................---------------------Q9191213condition--------------------................
	private StringBuilder Q919121316condition() {
		// StringBuilder Q9191213=new StringBuilder();;
		first_answer
				.append("\n\nOne example of how technical the law can be, is how fast the police did things in your case.");
		int p12 = 0, p13 = 0, p16_1 = 0, p16_2 = 0;

		if (SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q12) != null)
			if (singletonClass.getMap().get(QuestionUtiltiy.Q12).get("desc_Value").toString() != null) {
				Q12Value = singletonClass.getMap().get(QuestionUtiltiy.Q12).get("desc_Value").toString();
				p12 = Integer.parseInt(Q12Value);
			}
		System.out.println("====13 map value::"+SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q13)+"=====quation utility data:::::"+QuestionUtiltiy.Q13_1);
		if (SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q13) != null)
			if (!singletonClass.getMap().get(QuestionUtiltiy.Q13).get("subanswer_Value").toString().equalsIgnoreCase(QuestionUtiltiy.Q13_1)) {
				Q13Value = singletonClass.getMap().get(QuestionUtiltiy.Q13).get("desc_Value").toString();
				p13 = Integer.parseInt(Q13Value);
			}
		if (SingletonClass.getInstance().getMap().get(QuestionUtiltiy.Q16) != null) {
			if (singletonClass.getMap().get(QuestionUtiltiy.Q16).get("desc_Value").toString() != null) {
				Q161stValue = singletonClass.getMap().get(QuestionUtiltiy.Q16).get("desc_Value").toString();
				p16_1 = Integer.parseInt(Q161stValue);

			}
			if (singletonClass.getMap().get(QuestionUtiltiy.Q16).get("desc_Value2").toString() != null) {
				Q162ndValue = singletonClass.getMap().get(QuestionUtiltiy.Q16).get("desc_Value2").toString();
				p16_2 = Integer.parseInt(Q162ndValue);
			}
		}

		System.out.println("=== Q12Value" + Q12Value);
		System.out.println("=== Q13Value" + Q13Value);
		System.out.println("=== Q161stValue" + Q161stValue);
		System.out.println("=== Q162ndValue" + Q162ndValue);

		// Q19 data
		long calculatemin = timeInterval(
				singletonClass.getMap().get(QuestionUtiltiy.Q19)
						.get("desc_Value1").toString(), singletonClass.getMap()
						.get(QuestionUtiltiy.Q9).get("desc_Value").toString());

		long calculatemin2 = timeInterval(
				singletonClass.getMap().get(QuestionUtiltiy.Q19)
						.get("desc_Value3").toString(), singletonClass.getMap()
						.get(QuestionUtiltiy.Q9).get("desc_Value").toString());

		System.out.println("===calculatemin :::" + calculatemin);
		if ((calculatemin > 105) && (calculatemin < 140)) {

			first_answer
					.append("In your case the time between the officer first dealing with you and the time you "
							+ "provided a breath sample at the police station appears to be close to 2 hours.z"
							+ commonString);

		}

		else if (calculatemin >= 140) {

			first_answer
					.append("In your case it appears that the police waited too long to take your breath samples."
							+ commonString);

		}

		else if ((p12 > 41) || (p13 > 13) || (p16_1 > 14)
				|| (calculatemin2 > 30)
				|| ((QuestionUtiltiy.isGretterThanOrEqual()))) {
			first_answer
					.append("In your case there appears to be at least one point"
							+ " where the delay in your case was delayed longer than it "
							+ "should have been and so this is something which a lawyer might be able to use to defend you.");

		}

		else if ((p12 != 0 && p12 <= 41) && ((p13) != 0 && (p13) <= 13)
				&& ((p16_2) != 0 && p16_2 <= 14)
				&& (calculatemin2 != 0 && calculatemin2 <= 30)
				&& (QuestionUtiltiy.isGretterThanOrEqual())) {
			first_answer
					.append("Although it appears that everything in your case was done in a "
							+ "reasonably prompt manner, upon reviewing the police  reports and notes,"
							+ " this could change if any unnecessary delays are discovered.");

		}

		// 5th
		// subroutine...............................................................
		else {

			first_answer
					.append("Based on the information you have provided, it is difficult"
							+ " to tell whether or not there are timing problems in your case. "
							+ "These are things you can look at more closely with a lawyer.");
		}

		System.out.println("==== allanswerfor1213916:::" + first_answer);
		return first_answer;

	}

	// .................Question 25Condition........................
	private StringBuilder get25QuestionCondition() {
		System.out.println();
	
		map = singletonClass.getMap().get(
				"The police ultimately charged you with?");

		if (singletonClass.getMap().get(
				"The police ultimately charged you with?") != null) {

			// only 1 selected [condition 1]
			if ((map.get("check_one") != null && map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_single_optn + map.get("check_one");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1 and 2 selected [condition 2]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ " and " + map.get("check_two");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1 and 3 selected [condition 3]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ " and " + map.get("check_three");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1 and 3 with 10 option selected [condition 4]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " and " + map.get("check_three");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1 2, 3 selected [condition 5]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one") + " , "
						+ map.get("check_two") + " and "
						+ map.get("check_three");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1 2, 3 selected with 10 option [condition 6]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_three");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1,2,3,4 selected [condition 7]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one") + " , "
						+ map.get("check_two") + " , " + map.get("check_three")
						+ " and " + map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1,2,3,4 selected with 10 option [condition 8]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + " , " + map.get("check_three")
						+ " and " + map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1 ,4 selected [condition 9]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") != null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ " and " + map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1 ,4 selected with 10 option [condition 10]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") != null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " and " + map.get("check_four")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1 2, 3 , 4 and 6 selected [condition 11]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_six") != null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one") + " , "
						+ map.get("check_two") + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " and "
						+ map.get("check_six") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1 2, 3 , 4 and 6 selected with 10 option [condition 12]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_six") != null
					&& map.get("check_seven") == null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " and "
						+ map.get("check_six") + str_25_op1_op2_op6 + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1,2,3,4,6,7,selected [condition 13]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_six") != null
					&& map.get("check_seven") != null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one") + " , "
						+ map.get("check_two") + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " , "
						+ map.get("check_six") + " and "
						+ map.get("check_seven");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1,2,3,4,6,7,selected with 10 option [condition 14]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_six") != null
					&& map.get("check_seven") != null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " , "
						+ map.get("check_six") + str_25_op1_op2_op6 + " and "
						+ map.get("check_seven") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1,2,3,4,5,6,7,8 selected [condition 15]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") != null
					&& map.get("check_eight") != null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
					allAnswer = str_25_multiple_optn + map.get("check_one") + " , "
						+ map.get("check_two") + map.get("check_three") + " , "
						+ map.get("check_four") + " , " + map.get("check_six")
						+ " , " + map.get("check_seven") + " and "
						+ map.get("check_eight");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1,2,3,4,5,6,7,8 selected with 10 option [condition 16]
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") != null
					&& map.get("check_eight") != null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				Toast.makeText(LawyerLetter.this, "all  matched",
						Toast.LENGTH_LONG).show();
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + map.get("check_three") + " , "
						+ map.get("check_four") + " , " + map.get("check_six")
						+ str_25_op1_op2_op6 + " , " + map.get("check_seven")
						+ " and " + map.get("check_eight");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1,7 selected [condition 17]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") != null && map.get("check_eight") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ " and " + map.get("check_seven") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1,7 with option 10 [condition 18]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") != null && map.get("check_eight") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " and " + map.get("check_seven")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 1,8 selected [condition 17]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ " and " + map.get("check_eight") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 1,8 with option 10 [condition 18]
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_six") == null
					&& map.get("check_seven") == null && map.get("check_eight") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " and " + map.get("check_eight")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_single_optn + map.get("check_two");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2,3 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ " and " + map.get("check_three") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,3 selected with option 10

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_three")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2,4 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ " and " + map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,4 selected with option 10

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_four")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,3,4 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two") + " , "
						+ map.get("check_three") + " and "
						+ map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,3,4 selected with option 10

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " , " + map.get("check_three")
						+ " and " + map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2 ,6 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ " and " + map.get("check_six") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2 ,7 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") != null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ " and " + map.get("check_seven") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2 ,7 selected with 10 option

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") != null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_seven")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,3,4,6 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two") + " , "
						+ map.get("check_three") + " , "
						+ map.get("check_four") + " and "
						+ map.get("check_six") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,3,4,6 selected with 10 option

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_single_optn + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " and "
						+ map.get("check_six") + str_25_op1_op2_op6 + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2,3,4,6 ,7selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") != null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two") + " , "
						+ map.get("check_three") + " , "
						+ map.get("check_four") + " , " + map.get("check_six")
						+ " and " + map.get("check_seven") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2,3,4,6 ,7selected with 10 option

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") != null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_single_optn + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " , "
						+ map.get("check_six") + str_25_op1_op2_op6 + " and "
						+ map.get("check_seven") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2 ,8 selected

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") != null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ " and " + map.get("check_eight") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 2 ,8 selected with 10 option

			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") == null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") != null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_eight")
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2,3,4,6 ,7,8 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") != null
					&& map.get("check_eight") != null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_two") + " , "
						+ map.get("check_three") + " , "
						+ map.get("check_four") + " , " + map.get("check_six")
						+ " , " + map.get("check_seven")
						+ map.get("check_eight") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 2,3,4,6 ,7,8 selected with option 10
			else if ((map.get("check_one") == null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") != null
					&& map.get("check_eight") != null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " , " + map.get("check_three")
						+ " , " + map.get("check_four") + " , "
						+ map.get("check_six") + str_25_op1_op2_op6 + " , "
						+ map.get("check_seven") + map.get("check_eight") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 3 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_single_optn + map.get("check_three");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 3 ,4 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " and " + map.get("check_four") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 3 ,6 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " and " + map.get("check_six") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 3 ,6 selected with 10 option
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " and " + map.get("check_six") + str_25_op1_op2_op6
						+ ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 3 ,7 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") != null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " and " + map.get("check_seven") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 3 ,8 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") == null
					&& map.get("check_seven") == null
					&& map.get("check_eight") != null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " and " + map.get("check_eight") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// 3 ,4,6 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " , " + map.get("check_four") + " and "
						+ map.get("check_six") + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 3 ,4,6 selected with 10 option
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") != null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_three")
						+ " , " + map.get("check_four") + " and "
						+ map.get("check_six") + str_25_op1_op2_op6 + ".";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// 4 selected
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null
					&& map.get("check_three") == null
					&& map.get("check_four") != null
					&& map.get("check_seven") == null
					&& map.get("check_eight") == null && map.get("check_six") == null)
					&& map.get("check_ten") == null) {
				allAnswer = str_25_single_optn + map.get("check_four");
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}

			// Condition 1( 1,2,6 all selected with 10 option)
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				Toast.makeText(LawyerLetter.this, "all three matched",
						Toast.LENGTH_LONG).show();
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_six")
						+ str_25_op1_op2_op6 + " . ";
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
			}
			// Condition 2 ( 1,2 two selected with 10 option)
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " and " + map.get("check_two")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				// Condition 3 ( 1,6 two selected with 10 option)
			} else if ((map.get("check_one") != null
					&& map.get("check_two") == null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " and " + map.get("check_six")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				// Condition 4 ( 2,6 all selected with 10 option)
			} else if ((map.get("check_one") == null
					&& map.get("check_two") != null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_two")
						+ str_25_op1_op2_op6 + " and " + map.get("check_six")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);

			}
			// Condition 5 (2 selected with 10 option)
			else if ((map.get("check_one") == null
					&& map.get("check_two") != null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_single_optn + map.get("check_two")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

				Toast.makeText(LawyerLetter.this, "Not matched",
						Toast.LENGTH_LONG).show();
			}
			// Condition 1( 1,2,6 all selected with 10 option)
			else if ((map.get("check_one") != null
					&& map.get("check_two") == null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_single_optn + map.get("check_one")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

				Toast.makeText(LawyerLetter.this, "one match",
						Toast.LENGTH_LONG).show();
			} else if ((map.get("check_one") == null
					&& map.get("check_two") != null && map.get("check_six") == null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_single_optn + map.get("check_two")
						+ str_25_op1_op2_op6;
				txtview.setText(allAnswer);
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);

				Toast.makeText(LawyerLetter.this, "two match",
						Toast.LENGTH_LONG).show();
			}

			// only 6 option selected with option 10
			else if ((map.get("check_one") == null
					&& map.get("check_two") == null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_single_optn + map.get("check_six")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

				Toast.makeText(LawyerLetter.this, "six match",
						Toast.LENGTH_LONG).show();
			}

			// only 6 option
			else if ((map.get("check_one") != null
					&& map.get("check_two") != null
					&& map.get("check_three") != null
					&& map.get("check_four") != null && map.get("check_six") != null)
					&& map.get("check_ten") != null) {
				allAnswer = str_25_multiple_optn + map.get("check_one")
						+ str_25_op1_op2_op6 + " , " + map.get("check_two")
						+ str_25_op1_op2_op6 + map.get("check_three")
						+ str_25_op1_op2_op6 + map.get("check_four")
						+ str_25_op1_op2_op6;
				first_answer.append(allAnswer);
				txtview.setText(first_answer + "");
				txtview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				txtview.setLineSpacing(5);
				txtview.setTextColor(Color.WHITE);
				txtview.setAlignment(Align.LEFT);

			}

		}

		return first_answer;

	}

	/*
	 * getting time interval
	 */
	private long timeInterval(String one, String two) {

		String string1 = one;
		String string2 = two;

		System.out.println("====one:::" + one + "====two" + two);
		Date time1;
		long min = 0;
		try {

			time1 = new SimpleDateFormat("hh:mm aa").parse(string1);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(time1);
			System.out.println("LawyerLetter.timeInterval()" + time1);

			Date time2;

			time2 = new SimpleDateFormat("hh:mm aa").parse(string2);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(time2);
			calendar2.add(Calendar.DATE, 1);
			Date x = calendar1.getTime();
			Date xy = calendar2.getTime();
			long diffMs = x.getTime() - xy.getTime();

			long diffSec = diffMs / 1000;
			min = diffSec / 60;
			long sec = diffSec % 60;
			long hour = min / 60;

			System.out.println("The difference is " + hour + "minute" + min
					+ " minutes and " + sec + " seconds.");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return min;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.letter_email_txt:
			letterEmailId = letter_email_txt.getText().toString();
			Intent email = new Intent(Intent.ACTION_SEND);
			email.putExtra(Intent.EXTRA_EMAIL, new String[] { letterEmailId });
			email.putExtra(Intent.EXTRA_SUBJECT, "");
			email.putExtra(Intent.EXTRA_TEXT, "");

			// need this to prompts email client only
			email.setType("message/rfc822");

			startActivity(Intent.createChooser(email,
					"Choose an Email client :"));
			break;
		case R.id.letter_ottawa_number_txt:
			ottawano = letter_ottawa_number_txt.getText().toString();
			callDialer(ottawano);

			break;
		case R.id.letter_montreal_num_txt:
			montrealno = letter_montreal_num_txt.getText().toString();
			callDialer(montrealno);

			break;
		case R.id.letter_toll_no_txt:
			tollno = letter_toll_no_txt.getText().toString();
			callDialer(tollno);

			break;
		case R.id.letter_toronoto_number_txt:
			tornotono = letter_toronoto_number_txt.getText().toString();
			callDialer(tornotono);

			break;

		default:
			break;
		}

	}

	private void callDialer(String number) {
		System.out.println("=== call Dialer called:");
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + number));
		startActivity(intent);
	}
}
