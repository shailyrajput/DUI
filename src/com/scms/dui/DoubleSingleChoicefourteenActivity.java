package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;

public class DoubleSingleChoicefourteenActivity extends Activity implements
		OnClickListener {
	private Context context = DoubleSingleChoicefourteenActivity.this;
	private LinearLayout double_radio_type_sub_question_layout;
	private TextView question_txt, double_radio_type_ques,
			double_radio_type_sub_ques;
	private RadioGroup double_radiogroup1, double_radiogroup2;
	private RadioButton double_radio_type1_rd1, double_radio_type1_rd2,
			double_radio_type2_rd1, double_radio_type2_rd2,
			double_radio_type2_rd3, double_radio_type2_rd4;

	private Button prev_btn, next_btn;
	private String question = "Question";
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion, back_position, question_no;
	private String questionType, question_Value = "", answer_Value = "",
			subquestion_Value = "", subanswer_Value = "";
	private String activtiy_no, back_activity_no,back_activity;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private SingletonClass singletonClass;
	private int savePosition;
	private HashMap<Object, Object> map;
	private int p,sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.double_radio_type_question_view);
		setCustomActionBar();

		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		System.out.println("  ===== postion :" + postion);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		System.out.println("=== himani save position in 14 Activity "
				+ savePosition);
		map = new HashMap<Object, Object>();
		init();
		assignClicks();

	}

	private void setCustomActionBar() {
		// getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(
				R.layout.custom_action_bar, null);
		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setText("DUI Questionnaire");
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);
		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.alertDialogTwoButton(context, "Attention!","Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void init() {
		saveDataList = new ArrayList<SaveQuestionDataUtils>();

		double_radio_type_sub_question_layout = (LinearLayout) findViewById(R.id.double_radio_type_sub_question_layout);
		question_txt = (TextView) findViewById(R.id.question_txt);
		double_radio_type_ques = (TextView) findViewById(R.id.double_radio_type_ques);
		double_radio_type_sub_ques = (TextView) findViewById(R.id.double_radio_type_sub_ques);

		double_radiogroup1 = (RadioGroup) findViewById(R.id.double_radiogroup1);
		double_radiogroup2 = (RadioGroup) findViewById(R.id.double_radiogroup2);

		double_radio_type1_rd1 = (RadioButton) findViewById(R.id.double_radio_type1_rd1);
		double_radio_type1_rd2 = (RadioButton) findViewById(R.id.double_radio_type1_rd2);

		double_radio_type2_rd1 = (RadioButton) findViewById(R.id.double_radio_type2_rd1);
		double_radio_type2_rd2 = (RadioButton) findViewById(R.id.double_radio_type2_rd2);
		double_radio_type2_rd3 = (RadioButton) findViewById(R.id.double_radio_type2_rd3);
		double_radio_type2_rd4 = (RadioButton) findViewById(R.id.double_radio_type2_rd4);

		next_btn = (Button) findViewById(R.id.next_btn);
		prev_btn = (Button) findViewById(R.id.prev_btn);

		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);

		setview(postion);

	}

	private void setview(int postion) {

		System.out.println(" === questionData :"
				+ (questionData.get(postion).getQuesNo()));

		// Toast.makeText(context, "13", Toast.LENGTH_LONG).show();

		question_txt.setText(question + " " + (savePosition + 1));
		double_radio_type_ques.setText(questionData.get(postion).getQues());

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		question_no = Integer.parseInt(questionData.get(postion).getQuesNo());
		question_Value = questionData.get(postion).getQues();

		questionType = questionData.get(postion).getQuesType();
		System.out.println("=== questionType :" + questionType);
		optionlist = questionData.get(postion).getOptionArr();
		System.out.println("=== optionlist :" + optionlist.size());

		double_radio_type1_rd1.setText(optionlist.get(0).getOptionText());
		double_radio_type1_rd2.setText(optionlist.get(1).getOptionText());

		int size = optionlist.get(1).getSubQuesOptionArr().size();
		sub_optionlist = optionlist.get(1).getSubQuesOptionArr();
		double_radio_type_sub_ques.setText(optionlist.get(1).getSubQues());
		double_radio_type2_rd1.setText(sub_optionlist.get(0).getText());
		double_radio_type2_rd2.setText(sub_optionlist.get(1).getText());
		double_radio_type2_rd3.setText(sub_optionlist.get(2).getText());
		// double_radio_type2_rd4.setText(sub_optionlist.get(3).getText());
		
		conditionCheck();
	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map = singletonClass.getMap().get(question_Value);

		if (map != null) {
			back_activity = "" + map.get("back_activity");
			if (optionlist.get(0).getOptionText().equalsIgnoreCase((String) map.get("answer_Value"))) {
				double_radio_type1_rd1.setChecked(true);
			}
			if (optionlist.get(1).getOptionText().equalsIgnoreCase((String) map.get("answer_Value"))) {
				double_radio_type1_rd2.setChecked(true);
				double_radio_type_sub_question_layout.setVisibility(View.VISIBLE);

				if (sub_optionlist.get(0).getText().equalsIgnoreCase((String) map.get("subanswer_Value"))) {
					double_radio_type2_rd1.setChecked(true);
				}
				if (sub_optionlist.get(1).getText().equalsIgnoreCase((String) map.get("subanswer_Value"))) {
					double_radio_type2_rd2.setChecked(true);
				}
				if (sub_optionlist.get(2).getText().equalsIgnoreCase((String) map.get("subanswer_Value"))) {
					double_radio_type2_rd3.setChecked(true);
				}

			}

			answer_Value = "" + map.get("answer_Value");
			subanswer_Value = "" + map.get("subanswer_Value");
			subquestion_Value = "" + map.get("subquestion_Value");
			activtiy_no = "" + map.get("activtiy_no");

		}

	}

	private void assignClicks() {
		double_radiogroup1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						int select_id = double_radiogroup1.getCheckedRadioButtonId();
                        double_radio_type1_rd1 = (RadioButton) double_radiogroup1.findViewById(select_id);
						int index = double_radiogroup1.indexOfChild(double_radio_type1_rd1);
						System.out.println( "=== index::"+index);
                        p = getPosition(index);
						
						if (select_id == R.id.double_radio_type1_rd1) {
							Log.e("double_radio_type1_rd1", "matched");

							answer_Value = passRadioData(double_radio_type1_rd1
									.getText());
							activtiy_no = passRadioActivity(optionlist.get(0)
									.getActivity());
							back_activity_no = backRadioActivity(optionlist.get(0).getBack_activity());
							System.out.println(" === activtiy_no :"
									+ activtiy_no);
						}
						if (select_id == R.id.double_radio_type1_rd2) {
							Log.e("double_radio_type1_rd2", "matched");

							answer_Value = passRadioData(double_radio_type1_rd2
									.getText());
							activtiy_no = passRadioActivity(optionlist.get(1)
									.getActivity());
							System.out.println(" === activtiy_no :"
									+ activtiy_no);
							back_activity_no = backRadioActivity(optionlist
									.get(1).getBack_activity_to_five());
						}

						if (questionData.get(postion).getQuesNo().equals("6")
								|| questionData.get(postion).getQuesNo()
										.equals("7")) {
							if (index == 0) {
								double_radio_type_sub_question_layout
										.setVisibility(View.VISIBLE);
							} else {
								double_radio_type_sub_question_layout
										.setVisibility(View.GONE);
							}
						} else {
							if (index == 1) {
								subquestion_Value = optionlist.get(index)
										.getSubQues();
								double_radio_type_sub_question_layout
										.setVisibility(View.VISIBLE);
							} else {
								double_radio_type_sub_question_layout
										.setVisibility(View.GONE);
							}
						}
					}

				});

		double_radiogroup2
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						int select_id = double_radiogroup2.getCheckedRadioButtonId();
						int subindex = double_radiogroup1.indexOfChild(double_radio_type1_rd1);
						  System.out.println( "=== sindex::"+subindex);
						  sp=  getSubPosition(subindex);
						
						if (select_id == R.id.double_radio_type2_rd1) {
							Log.e("double_radio_type2_rd1", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd1
									.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(
									0).getActivity());
							System.out.println(" === activtiy_no :"
									+ activtiy_no);
							/*
							 * new changes
							 */
							back_activity_no = backRadioActivity(sub_optionlist
									.get(0).getBack_activity());
							System.out
									.println("=== himani back activity in double Single choice :"
											+ back_activity_no);
						}
						if (select_id == R.id.double_radio_type2_rd2) {
							Log.e("double_radio_type2_rd2", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd2
									.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(
									1).getActivity());
							System.out.println(" === activtiy_no :"
									+ activtiy_no);
							/*
							 * new changes
							 */
							back_activity_no = backRadioActivity(sub_optionlist
									.get(1).getBack_activity());
							System.out.println("=== himani back value ");
						}
						if (select_id == R.id.double_radio_type2_rd3) {
							Log.e("double_radio_type2_rd3", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd3
									.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(
									2).getActivity());
							System.out.println(" === activtiy_no :"
									+ activtiy_no);
						}
						// 3 dec new changes
						if (select_id == R.id.double_radio_type2_rd4) {
							Log.e("double_radio_type2_rd4", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd4
									.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(
									3).getActivity());
							System.out.println(" === activtiy_no :"
									+ activtiy_no);
						}
					}
				});
	}

	public int getPosition(int index) {

		return index;
	}

	public int getSubPosition(int index) {

		return index;
	}
	
	protected String backRadioActivity(String back_activity) {
		return back_activity;
	}

	public String passRadioActivity(String radio_activity) {

		return radio_activity;

	}

	public String passRadioData(CharSequence radiotext) {

		return radiotext.toString();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			add_to_saveData();
			finish();
			break;
		case R.id.prev_btn:
	    	backMove();
			
			break;

		default:
			break;
		}

	}

	private void backMove() {
		
		savePosition = savePosition - 1;
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(context, ThirteenViewActivty.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		
	}

	private void add_to_saveData() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setAnswer(answer_Value);
		savedataUtils.setQuestion(question_Value);
		savedataUtils.setSubanswer(subanswer_Value);
		savedataUtils.setSubquestion(subquestion_Value);
		savedataUtils.setQuestionNo(question_no);
		saveDataList.add(savedataUtils);
		//singletonClass.addList(savePosition, saveDataList);
		
		map.put("answer_Value", answer_Value);
		map.put("subanswer_Value", subanswer_Value);
		map.put("Position", ""+p);
		map.put("SubPosition", ""+sp);
		map.put("subquestion_Value", subquestion_Value);
		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(question_Value, map);
		
		

		if (activtiy_no == null) {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");

		} else {
			Log.e("Alert", " Not show");
			nextBtnAction();
			
		}

	}

	

	private void nextBtnAction() {
		if (activtiy_no != null) {
			if (activtiy_no.equalsIgnoreCase("15")) {
				Intent intent = new Intent(context,DoubleSingleChoiceFifteenQuestion.class);
				postion = Integer.parseInt(activtiy_no);
				intent.putExtra("Position", postion - 1);
				intent.putExtra("back_activity","14");
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);

			} else if (activtiy_no.equalsIgnoreCase("17")) {
				Intent intent = new Intent(context,DoubleDiscriptionViewSeventeenActivity.class);
				postion = Integer.parseInt(activtiy_no);
				intent.putExtra("Position", postion - 1);
				intent.putExtra("back_activity","14");
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);

			}

		} else {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");
		}
	}

	@Override
	public void onBackPressed() {

	}

}
