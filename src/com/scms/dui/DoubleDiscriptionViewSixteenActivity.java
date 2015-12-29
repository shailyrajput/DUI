package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DoubleDiscriptionViewSixteenActivity extends Activity implements
		OnClickListener {
	private Activity activity = DoubleDiscriptionViewSixteenActivity.this;

	private LinearLayout double_desc_sub_ques_layout;
	private TextView question_txt, double_desc_type_ques1, minutes_text,
			minutes_text1, double_desc_type_sub_ques,
			double_desc_type_ques1_option, double_desc_type_subques_option;

	private Button prev_btn, next_btn, done, cancel;
	private String question = "Question";
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion;
	private String questionType;
	private String activtiy_no;
	private TimePicker timePicker;
	private NumberPicker numberPicker;
	private AlertDialog alertDialog;
	private SingletonClass singletonClass;

	private String question_Value = "", subquestion_Value = "",back_activity,
			answer_value = "", subanswer_value = "";
	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private int savePosition;
	private HashMap<Object, Object> map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.double_description_type_question_view);
		setCustomActionBar();

		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		System.out.println("  ===== postion :" + postion);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		System.out.println("=== himani save position in 16 activity :"+ savePosition);
		
		back_activity = getIntent().getStringExtra("back_activity");
		map = new HashMap<Object, Object>();
		init();
		assignClicks();
		setview(postion);

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
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);

		txt.setText("DUI Questionnaire");

		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.alertDialogTwoButton(activity, "Attention!",
						"Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void init() {
		double_desc_sub_ques_layout = (LinearLayout) findViewById(R.id.double_desc_sub_ques_layout);

		question_txt = (TextView) findViewById(R.id.question_txt);
		double_desc_type_ques1 = (TextView) findViewById(R.id.double_desc_type_ques1);
		double_desc_type_ques1_option = (TextView) findViewById(R.id.double_desc_type_ques1_option);

		double_desc_type_sub_ques = (TextView) findViewById(R.id.double_desc_type_sub_ques);
		double_desc_type_subques_option = (TextView) findViewById(R.id.double_desc_type_subques_option);

		minutes_text = (TextView) findViewById(R.id.minutes_text);
		minutes_text1 = (TextView) findViewById(R.id.minutes_text1);

		prev_btn = (Button) findViewById(R.id.prev_btn);
		next_btn = (Button) findViewById(R.id.next_btn);

	}

	private void setview(int postion) {

		double_desc_sub_ques_layout.setVisibility(View.VISIBLE);

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		question_Value = questionData.get(postion).getQues();

		optionlist = questionData.get(postion).getOptionArr();
		sub_optionlist = optionlist.get(0).getSubQuesOptionArr();

		subanswer_value = questionData.get(postion).getSecondQues();

		question_txt.setText(question + " "+ (savePosition+1));
		double_desc_type_ques1.setText(questionData.get(postion).getQues());
		double_desc_type_sub_ques.setText(questionData.get(postion).getSecondQues());

		System.out.println(" === activtiy_no :" + activtiy_no);
		
		conditionCheck();
	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map = singletonClass.getMap().get(question_Value);

		if (map != null) {
			back_activity = "" + map.get("back_activity");
			if (map.get("desc_Value") != null) {
				double_desc_type_ques1_option.setText(" "+ map.get("desc_Value"));
			
				
			}
			if (map.get("desc_Value2") != null) {
				
				double_desc_type_subques_option.setText(" "+ map.get("desc_Value2"));
			}
			
			        answer_value=" "+ map.get("desc_Value");
			        subanswer_value=" "+ map.get("desc_Value2");
			 		activtiy_no = "" + map.get("activtiy_no");
		}
		
		

	}
	private String passRadioActivity(String activtiy_no) {

		return activtiy_no;
	}

	private void assignClicks() {
		prev_btn.setOnClickListener(this);
		next_btn.setOnClickListener(this);
		double_desc_type_ques1_option.setOnClickListener(this);
		double_desc_type_subques_option.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			nextBtnAction();
			finish();
			break;
		case R.id.prev_btn:
		    baceMove(); 
		    
			break;
		case R.id.double_desc_type_ques1_option:

			openNumberPicker(R.id.double_desc_type_ques1_option);

			break;
		case R.id.double_desc_type_subques_option:
			openNumberPicker(R.id.double_desc_type_subques_option);

			break;
		default:
			break;
		}

	}

	private void baceMove() {
		
		savePosition = savePosition - 1;
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, DoubleSingleChoiceFifteenQuestion.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
	}

	private void openNumberPicker(final int id) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				activity);
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.picker, null);

		timePicker = (TimePicker) layout.findViewById(R.id.timePicker);
		timePicker.setVisibility(View.GONE);
		numberPicker = (NumberPicker) layout.findViewById(R.id.numberPicker);
		numberPicker.setVisibility(View.VISIBLE);
		numberPicker.setMaxValue(60);
		numberPicker.setMinValue(0);
		done = (Button) layout.findViewById(R.id.done);
		cancel = (Button) layout.findViewById(R.id.cancel);
		timePicker.setIs24HourView(true);

		alertDialogBuilder.setView(layout);
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();

		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtiy_no = passRadioActivity(optionlist.get(0).getActivity());
				String number = "" + numberPicker.getValue();

				switch (id) {
				case R.id.double_desc_type_ques1_option:
					double_desc_type_ques1_option.setText(number);
					answer_value = number;
					break;
				case R.id.double_desc_type_subques_option:
					double_desc_type_subques_option.setText(number);
					subanswer_value = number;
					break;

				default:
					break;
				}
				alertDialog.dismiss();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onCancel();
			}
		});

	}

	protected void onCancel() {
		alertDialog.dismiss();

	}

	private void nextBtnAction() {

		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setQuestion(question_Value);
		savedataUtils.setSubquestion(subquestion_Value);
		savedataUtils.setQuestionNo(questionNo);
		savedataUtils.setDesc1(answer_value);
		savedataUtils.setDesc2(subanswer_value);
		saveDataList.add(savedataUtils);
		//singletonClass.addList(savePosition, saveDataList);
		
		map.put("question_Value", question_Value);
		map.put("desc_Value2", subanswer_value);
		map.put("desc_Value", answer_value);
		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		map.put("subquestion_Value", subquestion_Value);
		
		
		singletonClass.addMap(question_Value, map);
		
	
		if (activtiy_no != null && double_desc_type_ques1_option.getText().length()!= 0 && double_desc_type_subques_option.getText().length()!=0) {

			postion = Integer.parseInt(activtiy_no);
			System.out.println(" === postion  :" + postion);
			Intent intent = new Intent(activity,DoubleDiscriptionViewSeventeenActivity.class);
			intent.putExtra("back_activity","16");
		    intent.putExtra("Position", postion - 1);
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
		} else {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(activity, "Attention!",
					"Please answer All questions.");

		}
	}

	@Override
	public void onBackPressed() {

	}

}
