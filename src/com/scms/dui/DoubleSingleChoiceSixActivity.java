package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import com.scms.utility.QuestionUtiltiy;

public class DoubleSingleChoiceSixActivity extends Activity implements
		OnClickListener {
	private Context context = DoubleSingleChoiceSixActivity.this;
	private LinearLayout double_radio_type_sub_question_layout;

	private TextView question_txt, double_radio_type_ques,double_radio_type_sub_ques;

	private RadioGroup double_radiogroup1, double_radiogroup2;

	private RadioButton double_radio_type1_rd1, double_radio_type1_rd2,double_radio_type2_rd1, double_radio_type2_rd2,
			double_radio_type2_rd3, double_radio_type2_rd4;

	private Button prev_btn, next_btn;
	private SingletonClass singletonClass;
	private String question = "Question";
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion, back_position, question_no;
	private String questionType, question_Value = "", answer_Value = "",
			subquestion_Value = "", subanswer_Value = "";
	private String activtiy_no, back_activity_no;
	private int savePosition;

	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private String questiondata, answer, subquestion, subanswer, desc1, desc2,
			desc3, desc4, desc5, descQ1, descQ2, descQ3, descQ4, descQ5,
			checked;
	private int questionNo;
	private HashMap<Object, Object> map;
	private String back_activity,DotText2,DotText1,changeText1,changeText2;
    int p,sp;
    private QuestionUtiltiy questionUtiltiy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.double_radio_type_question_view);
		setCustomActionBar();

		optionlist = new ArrayList<OptionUtils>();
		questionData = SplashScreen.QuestionArrList;
		singletonClass = SingletonClass.getInstance();
		back_activity = getIntent().getStringExtra("back_activity");
		System.out.println("====back_activity"+back_activity);
		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		
		back_activity = getIntent().getStringExtra("back_activity");
		map = new HashMap<Object, Object>();
		//questionUtiltiy= new QuestionUtiltiy();
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

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		question_no = Integer.parseInt(questionData.get(postion).getQuesNo());
		question_Value = questionData.get(postion).getQues();

		questionType = questionData.get(postion).getQuesType();
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
		
		
		// new change option text change according previous question
		if ((singletonClass.getMap().get(QuestionUtiltiy.Q2)!=null)|| (singletonClass.getMap().get(QuestionUtiltiy.Q3) != null|| (singletonClass.getMap().get(QuestionUtiltiy.Q4) !=null))) {
			
//	    	double_radio_type1_rd1.setText("..."+ optionlist.get(0).getOptionText());
//	    	double_radio_type1_rd2.setText("..."+ optionlist.get(1).getOptionText());
	    	
		    DotText1="..."+ optionlist.get(0).getOptionText();
		   	DotText2="..."+ optionlist.get(1).getOptionText();
		    	
	    	SpannableString spanString = new SpannableString(DotText1);
			spanString.setSpan(new UnderlineSpan(), spanString.length()-21, spanString.length()-1, 0);
			
			SpannableString spanString2 = new SpannableString(DotText2);
			spanString2.setSpan(new UnderlineSpan(),38, 62, 0);
			
			double_radio_type1_rd1.setText(spanString);
         	double_radio_type1_rd2.setText(spanString2);
			
		} else {

			changeText1="..."
					+ "ask you to provide a breath sample at/near your home into a small handheld breathayser.";
			changeText2="..."
			+ "tell you that you were under arrest without making you blow into the small handheld breathayser at your home.";
		
			SpannableString spanString = new SpannableString(changeText1);
			spanString.setSpan(new UnderlineSpan(), spanString.length()-21, spanString.length()-1, 0);
			
			SpannableString spanString2 = new SpannableString(changeText2);
			spanString2.setSpan(new UnderlineSpan(),38, 62, 0);
			
			double_radio_type1_rd1.setText(spanString);
         	double_radio_type1_rd2.setText(spanString2);
	
		}
		

		conditionCheck();
	}

	
	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map = singletonClass.getMap().get(question_Value);
		System.out.println("===question_Value::" + question_Value);
		System.out.println("===map::" + map);

        Toast.makeText(getApplicationContext(),"DoubleSingleChoiceSixActivity.conditionCheck()" , Toast.LENGTH_LONG).show();

		if (map != null) {
			
			back_activity = ""+map.get("back_activity");
			System.out.println("======back_activity"+back_activity);
			if (double_radio_type1_rd1.getText().toString().equalsIgnoreCase((String) map.get("answer_Value"))) {
				double_radio_type1_rd1.setChecked(true);
				double_radio_type_sub_question_layout.setVisibility(View.VISIBLE);
			}
			if (double_radio_type1_rd2.getText().toString().equalsIgnoreCase((String) map.get("answer_Value"))) {
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
			activtiy_no = "" + map.get("activtiy_no");
			answer_Value = "" + map.get("answer_Value");

		} else {
			
		}

	}

	private void assignClicks() {
		double_radiogroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
							System.out.println("====q8 data"+singletonClass.getMap().get(QuestionUtiltiy.Q8));
							
							//if(double_radio_type1_rd1.isChecked())
							
							/*if(singletonClass.getMap().get(QuestionUtiltiy.Q8) != null)
							{
								
								int p8= getSingleChoiceData(QuestionUtiltiy.Q8);
								if(p8==2)
								{
									Utils.alertDialogToMoveContact(context, "Attentation!",
											"You have indicated that you were charged with “refusing a breath sample”. This questionnaire cannot assist with that charge. Please contact David Anber, a criminal lawyer, 24hrs/day to discuss your charges.",double_radio_type1_rd1);
									activtiy_no = passRadioActivity(optionlist.get(2).getActivity());	
								}
							
							}*/
						}
						if (select_id == R.id.double_radio_type1_rd2) {

							answer_Value = passRadioData(double_radio_type1_rd2.getText());
							
							activtiy_no = passRadioActivity(optionlist.get(1).getActivity());
							back_activity_no = backRadioActivity(optionlist.get(1).getBack_activity_to_five());
						}

						if (index == 0) {
							subquestion_Value = optionlist.get(index).getSubQues();
							double_radio_type_sub_question_layout.setVisibility(View.VISIBLE);
						} else {
							double_radio_type_sub_question_layout.setVisibility(View.GONE);
						}
					}

				});

		double_radiogroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						int select_id = double_radiogroup2.getCheckedRadioButtonId();
						int subindex = double_radiogroup1.indexOfChild(double_radio_type1_rd1);
						  System.out.println( "=== sindex::"+subindex);
						  sp=  getSubPosition(subindex);
						if (select_id == R.id.double_radio_type2_rd1) {
							Log.e("double_radio_type2_rd1", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd1.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(0).getActivity());
							
							/*
							 * new changes
							 */
							
							back_activity_no = backRadioActivity(sub_optionlist.get(0).getBack_activity());
							
						}
						if (select_id == R.id.double_radio_type2_rd2) {
							Log.e("double_radio_type2_rd2", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd2.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(1).getActivity());
							
							back_activity_no = backRadioActivity(sub_optionlist.get(1).getBack_activity());
						}
						if (select_id == R.id.double_radio_type2_rd3) {
							Log.e("double_radio_type2_rd3", "matched");

							subanswer_Value = passRadioData(double_radio_type2_rd3.getText());
							activtiy_no = passRadioActivity(sub_optionlist.get(2).getActivity());
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

	public String backRadioActivity(String back_activity) {
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
			
			break;
		case R.id.prev_btn:
			back_move();
		 
			break;

		default:
			break;
		}

	}

	private void back_move() {
		if(back_activity.equalsIgnoreCase("2"))
		{
			
			postion = Integer.parseInt(back_activity);
			Intent intent = new Intent(this, SingleChoiceSecondActivity.class);
			Toast.makeText(getApplicationContext(),
					"  ===== savePosition :" + savePosition, Toast.LENGTH_LONG).show();

			intent.putExtra("saveposition", savePosition - 1);
			intent.putExtra("Position", postion - 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			   finish();
		}else
		if(back_activity.equalsIgnoreCase("3"))
		{
			postion = Integer.parseInt(back_activity);
			Intent intent = new Intent(this,SingleChoiceThirdActivity.class);
			Toast.makeText(getApplicationContext(), "  ===== savePosition :" + savePosition, Toast.LENGTH_LONG).show();
			
			intent.putExtra("saveposition", savePosition-1);
			intent.putExtra("Position", postion - 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			   finish();
		}else
		if(back_activity.equalsIgnoreCase("4"))
		{
			postion = Integer.parseInt(back_activity);
			Intent intent = new Intent(this,SingleChoiceFouthActivity.class);
			Toast.makeText(getApplicationContext(), "  ===== savePosition :" + savePosition, Toast.LENGTH_LONG).show();
			
			intent.putExtra("saveposition", savePosition-1);
			intent.putExtra("Position", postion - 1);
			startActivity(intent);
			
			overridePendingTransition(0, 0);
			   finish();
		}
		else
			if(back_activity.equalsIgnoreCase("5"))
			{
				postion = Integer.parseInt(back_activity);
				Intent intent = new Intent(this,SingleChoiceFiveActivity.class);
				Toast.makeText(getApplicationContext(), "  ===== savePosition :" + savePosition, Toast.LENGTH_LONG).show();
				
				intent.putExtra("saveposition", savePosition-1);
				intent.putExtra("Position", postion - 1);
				startActivity(intent);
				overridePendingTransition(0, 0);
				  finish();
			}
		
	}

	private void add_to_saveData() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setAnswer(answer_Value);
		savedataUtils.setQuestion(question_Value);
		savedataUtils.setSubanswer(subanswer_Value);
		savedataUtils.setSubquestion(subquestion_Value);
		savedataUtils.setQuestionNo(question_no);
		saveDataList.add(savedataUtils);
//		singletonClass.addList(savePosition, saveDataList);

		map.put("answer_Value", answer_Value);;
		map.put("back_activity", back_activity);
		map.put("subquestion_Value", subquestion_Value);
		map.put("subanswer_Value", subanswer_Value);
		map.put("Position", ""+p);
		map.put("SubPosition", ""+sp);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(question_Value, map);
	
		if (activtiy_no == null) {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!","Please answer All questions.");

		} else {
			postion = Integer.parseInt(activtiy_no);
			// moveActivity();
			nextActivity();
		}

	}

	private void nextActivity() {
		if (activtiy_no.equalsIgnoreCase("7")) {
			Intent intent = new Intent(context,DoubleSingleChoiceSevenActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("saveposition", savePosition + 1);
			intent.putExtra("back_activity","6");
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
		if (activtiy_no.equalsIgnoreCase("9")) {
			Intent intent = new Intent(context, NineViewActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","6");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
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


	@Override
	public void onBackPressed() {
	}
}
