package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;

public class DoubleSingleChoiceSevenActivity extends Activity implements
		OnClickListener {
	private Context context = DoubleSingleChoiceSevenActivity.this;
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
		back_activity = getIntent().getStringExtra("back_activity");
		
//		back_activity = ""+postion;
		System.out.println("DoubleSingleChoiceSevenActivity.onCreate()");
		System.out.println("  ===== back_activity :" + back_activity);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		
		map = new HashMap<Object, Object>();
		init();
		assignClicks();

	}

	private void setCustomActionBar() {
		// getActionBar().setDisplayHomeAsUpEnabled(false);
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
				Utils.alertDialogTwoButton(context, "Attention!",
						"Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	public void backAlert(final Context context, String tittle, String message) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder.setTitle(tittle);
		alertDialogBuilder.setMessage(message).setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(context, QuestionForm.class);
						startActivity(intent);

					}
				});

		alertDialogBuilder.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

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

		

		question_txt.setText(question + " " + (savePosition + 1));
		double_radio_type_ques.setText(questionData.get(postion).getQues());
		// save question data
		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		question_no = Integer.parseInt(questionData.get(postion).getQuesNo());
		question_Value = questionData.get(postion).getQues();

		questionType = questionData.get(postion).getQuesType();
		System.out.println("=== questionType :" + questionType);
		optionlist = questionData.get(postion).getOptionArr();

		double_radio_type1_rd1.setText(optionlist.get(0).getOptionText());
		double_radio_type1_rd2.setText(optionlist.get(1).getOptionText());
		int size = optionlist.get(0).getSubQuesOptionArr().size();
		sub_optionlist = optionlist.get(0).getSubQuesOptionArr();
		double_radio_type_sub_ques.setText(optionlist.get(0).getSubQues());
		double_radio_type2_rd1.setText(sub_optionlist.get(0).getText());
		double_radio_type2_rd2.setText(sub_optionlist.get(1).getText());
		double_radio_type2_rd3.setVisibility(View.GONE);
		double_radio_type2_rd4.setVisibility(View.GONE);

		conditionCheck();
	}

	
	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map = singletonClass.getMap().get(question_Value);
		System.out.println("===question_Value::" + question_Value);
		System.out.println("DoubleSingleChoiceSevenActivity.conditionCheck()");
		System.out.println("===map::" + map);

		// remove ninth view
		if (singletonClass.getMap().get(
				"Please indicate what time the following things happened:") != null) {
			singletonClass.getMap().remove(
					"Please indicate what time the following things happened:");
		}
		if (map != null) {
			
			back_activity = ""+map.get("back_activity");
			
			activtiy_no = "" + map.get("activtiy_no");
			answer_Value = "" + map.get("answer_Value");

			if (optionlist.get(0).getOptionText()
					.equalsIgnoreCase((String) map.get("answer_Value"))) {

				double_radio_type1_rd1.setChecked(true);
				double_radio_type_sub_question_layout
						.setVisibility(View.VISIBLE);
			}
			if (optionlist.get(1).getOptionText()
					.equalsIgnoreCase((String) map.get("answer_Value"))) {

				double_radio_type1_rd2.setChecked(true);
			}

            if (map.get("subquestion_Value") != null) {
				
				
				if (sub_optionlist.get(0).getText().equalsIgnoreCase((String) map.get("subanswer_Value"))) {
					double_radio_type2_rd1.setChecked(true);
				}
				if (sub_optionlist.get(1).getText().equalsIgnoreCase((String) map.get("subanswer_Value"))) {
					double_radio_type2_rd2.setChecked(true);
				}
				subquestion_Value = "" + map.get("subquestion_Value");
				subanswer_Value = "" + map.get("subanswer_Value");
				
			}
			

			/*
			 * map.put("answer_Value", answer_Value);
			 * map.put("subquestion_Value", subquestion_Value);
			 * map.put("subanswer_Value", subanswer_Value);
			 * map.put("activtiy_no", activtiy_no);
			 */
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

							answer_Value = passRadioData(double_radio_type1_rd1.getText());
							activtiy_no = passRadioActivity(optionlist.get(0).getActivity());
							back_activity_no = backRadioActivity(optionlist.get(0).getBack_activity());
							
						}
						if (select_id == R.id.double_radio_type1_rd2) {
							Log.e("double_radio_type1_rd2", "matched");

							answer_Value = passRadioData(double_radio_type1_rd2.getText());
							activtiy_no = passRadioActivity(optionlist.get(1).getActivity());
							System.out.println(" === activtiy_no :"+ activtiy_no);
							back_activity_no = backRadioActivity(optionlist.get(1).getBack_activity_to_five());
						}

						if (questionData.get(postion).getQuesNo().equals("6")|| questionData.get(postion).getQuesNo()
										.equals("7")) {
							if (index == 0) {
								double_radio_type_sub_question_layout.setVisibility(View.VISIBLE);
							} else {
								double_radio_type_sub_question_layout.setVisibility(View.GONE);
							}
						} else {
							if (index == 1) {
								subquestion_Value = optionlist.get(index).getSubQues();
								double_radio_type_sub_question_layout
										.setVisibility(View.VISIBLE);
							} else {
								double_radio_type_sub_question_layout.setVisibility(View.GONE);
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
							/*
							 * new changes
							 */
							back_activity_no = backRadioActivity(sub_optionlist
									.get(0).getBack_activity());
							
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
						}
						if (select_id == R.id.double_radio_type2_rd3) {
							Log.e("double_radio_type2_rd3", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd3
									.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(
									2).getActivity());
						}
					}
				});
	}

	protected String backRadioActivity(String back_activity) {
		return back_activity;
	}

	public int getPosition(int index) {

		return index;
	}

	public int getSubPosition(int index) {

		return index;
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
			
			savePosition = savePosition - 1;
            postion= Integer.parseInt(back_activity);
			System.out.println("===back_activity"+back_activity);
			Intent intent = new Intent(context,DoubleSingleChoiceSixActivity.class);
			System.out.println(" ==== savePosition"+savePosition);
			intent.putExtra("saveposition", savePosition);
			intent.putExtra("Position", postion-1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
			break;

		default:
			break;
		}

	}

	private void add_to_saveData() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setAnswer(answer_Value);
		savedataUtils.setQuestion(question_Value);
		savedataUtils.setQuestion(subanswer_Value);
		savedataUtils.setQuestion(subquestion_Value);
		savedataUtils.setQuestionNo(question_no);
		saveDataList.add(savedataUtils);
//		singletonClass.addList(savePosition, saveDataList);

		map.put("answer_Value", answer_Value);
		map.put("back_activity", back_activity);
		map.put("subquestion_Value", subquestion_Value);
		map.put("subanswer_Value", subanswer_Value);
		map.put("Position", ""+p);
		map.put("SubPosition", ""+sp);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(question_Value, map);
		
		System.out.println("DoubleSingleChoiceSevenActivity.add_to_saveData()");
		System.out.println("===map::" + map);


		if (activtiy_no == null) {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");

		} else {
			Log.e("Alert", " Not show");
			postion = Integer.parseInt(activtiy_no);
			nextActivity();
		}

	}

	private void nextActivity() {
		if (activtiy_no.equalsIgnoreCase("8")) {
			Intent intent = new Intent(context, SingleChoiceEightActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","7");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
		}
		if (activtiy_no.equalsIgnoreCase("9")) {
			Intent intent = new Intent(context, NineViewActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","7");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
		}
	}

	
	@Override
	public void onBackPressed() {
	}

}
