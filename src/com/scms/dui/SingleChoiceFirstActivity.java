package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
import com.scms.dui.utils.Utils;
import com.scms.utility.Constant;

public class SingleChoiceFirstActivity extends Activity implements OnClickListener {

	Context context = SingleChoiceFirstActivity.this;
	private RadioButton radioButton1, radioButton2, radioButton3, radioButton4,
			radioButton5;
	private RadioGroup radioGroup;
	private TextView radio_btn_question, radio_question_no;
	private Button nextBtn, prevBtn;
	private ArrayList<QuestionUtils> questionData;
	private int postion, question_no;
	private int back_position;
	private String question = "Question";
	private ArrayList<OptionUtils> optionlist;
	private SingletonClass singletonClass;
	private String questionType, token, question_Value = "", answer_Value = "";
	private String activtiy_no, back_activity_no;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private int savePosition = 0;
	private boolean btncheckValue = false;
	private String secondAnswer;
	private HashMap<Object, Object> map;
	int p,sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_type_activity);
		setCustomActionBar();

		optionlist = new ArrayList<OptionUtils>();
		// postion = getIntent().getIntExtra("Position", 0);
		singletonClass = SingletonClass.getInstance();
		// singletonClass.checked=true;
		map= new HashMap<Object, Object>();
		init();
		getData();
		assignClick();

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

		radio_btn_question = (TextView) findViewById(R.id.radio_btn_question);
		radio_question_no = (TextView) findViewById(R.id.radio_question_no);

		radioButton1 = (RadioButton) findViewById(R.id.rd1);
		radioButton2 = (RadioButton) findViewById(R.id.rd2);
		radioButton3 = (RadioButton) findViewById(R.id.rd3);
		radioButton4 = (RadioButton) findViewById(R.id.rd4);
		radioButton5 = (RadioButton) findViewById(R.id.rd5);
		savePosition = getIntent().getIntExtra("saveposition", 0);

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		nextBtn = (Button) findViewById(R.id.next_btn);
		prevBtn = (Button) findViewById(R.id.prev_btn);

	}

	private void getData() {
		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionData = SplashScreen.QuestionArrList;
		
		
		question_no = Integer.parseInt(questionData.get(postion).getQuesNo());
		optionlist = questionData.get(postion).getOptionArr();
		System.out.println(" ===  optionlist  :" + optionlist.size());
		System.out.println("=== himani question No. is :" + question_no);

		radio_question_no.setText(question + " "+ questionData.get(postion).getQuesNo());
		radio_btn_question.setText(questionData.get(postion).getQues());
		question_Value = questionData.get(postion).getQues();

		questionType = questionData.get(postion).getQuesType();
		System.out.println("=== questionType :" + questionType);

		radioButton1.setText(optionlist.get(0).getOptionText());
		radioButton2.setText(optionlist.get(1).getOptionText());
		radioButton3.setText(optionlist.get(2).getOptionText());
		radioButton4.setText(optionlist.get(3).getOptionText());
		radioButton5.setText(optionlist.get(4).getOptionText());
		
		
		conditionCheck();

	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map = singletonClass
				.getMap()
				.get("What best describes how the police initially got involved in your case?");
		
		
		
		if(map !=null)
		{
			if(optionlist.get(0).getOptionText().equalsIgnoreCase((String) map.get("answer_Value")))
			{
				radioButton1.setChecked(true);
			}
			if(optionlist.get(1).getOptionText().equalsIgnoreCase((String) map.get("answer_Value")))
			{
				radioButton2.setChecked(true);
			}
			if(optionlist.get(2).getOptionText().equalsIgnoreCase((String) map.get("answer_Value")))
			{
				radioButton3.setChecked(true);
			}
			if(optionlist.get(3).getOptionText().equalsIgnoreCase((String) map.get("answer_Value")))
			{
				radioButton4.setChecked(true);
			}
			if(optionlist.get(4).getOptionText().equalsIgnoreCase((String) map.get("answer_Value")))
			{
				radioButton5.setChecked(true);
			}
			Toast.makeText(getApplicationContext(), "show"+"  "+ map.get("answer_Value"), Toast.LENGTH_LONG).show();
			
			answer_Value = ""+map.get("answer_Value");
			activtiy_no=""+map.get("activtiy_no");
			System.out.println("=====activtiy_no:::"+activtiy_no);
			
			
//			map.put("answer_Value", answer_Value);
//			map.put("activtiy_no", activtiy_no);
//			System.out.println("=== question value :"+question_Value);
//			singletonClass.addMap(question_Value, map);
		}else{
			Toast.makeText(getApplicationContext(), "Not show", Toast.LENGTH_LONG).show();
		}
	}

	private void assignClick() {

		nextBtn.setOnClickListener(this);
		prevBtn.setOnClickListener(this);
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int select_id = radioGroup.getCheckedRadioButtonId();
				radioButton1 = (RadioButton) radioGroup.findViewById(select_id);
				int index = (radioGroup).indexOfChild(radioButton1);
				p = passPosition(index);

				if (select_id == R.id.rd1) {

					Log.e("rd1", "matched");

					btncheckValue = true;

					answer_Value = passRadioData(radioButton1.getText());

					activtiy_no = passRadioActivity(optionlist.get(0)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(0)
							.getBack_activity());
					System.out.println("=== himani back activity number"
							+ back_activity_no);
					radioButton1.setChecked(true);
					radioButton2.setChecked(false);
					radioButton3.setChecked(false);
					radioButton4.setChecked(false);
					radioButton5.setChecked(false);

				}
				if (select_id == R.id.rd2) {
					Log.e("rd2", "matched");

					answer_Value = passRadioData(radioButton2.getText());
					activtiy_no = passRadioActivity(optionlist.get(1).getActivity());

					back_activity_no = backRadioActivity(optionlist.get(1).getBack_activity());
					radioButton1.setChecked(false);
					radioButton2.setChecked(true);
					radioButton3.setChecked(false);
					radioButton4.setChecked(false);
					radioButton5.setChecked(false);

				}
				if (select_id == R.id.rd3) {
					Log.e("rd3", "matched");

					answer_Value = passRadioData(radioButton3.getText());
					activtiy_no = passRadioActivity(optionlist.get(2).getActivity());

					back_activity_no = backRadioActivity(optionlist.get(2).getBack_activity());
					radioButton1.setChecked(false);
					radioButton2.setChecked(false);
					radioButton3.setChecked(true);
					radioButton4.setChecked(false);
					radioButton5.setChecked(false);

				}
				if (select_id == R.id.rd4) {
					Log.e("rd4", "matched");

					answer_Value = passRadioData(radioButton4.getText());
					activtiy_no = passRadioActivity(optionlist.get(3).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(3).getBack_activity());
					System.out.println("=== himani back activity number"+ back_activity_no);
					radioButton1.setChecked(false);
					radioButton2.setChecked(false);
					radioButton3.setChecked(false);
					radioButton4.setChecked(true);
					radioButton5.setChecked(false);

				}
				if (select_id == R.id.rd5) {
					Log.e("rd5", "matched");

					answer_Value = passRadioData(radioButton5.getText());
					activtiy_no = passRadioActivity(optionlist.get(4).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(4).getBack_activity());
					System.out.println("=== himani back activity number"+ back_activity_no);
					radioButton1.setChecked(false);
					radioButton2.setChecked(false);
					radioButton3.setChecked(false);
					radioButton4.setChecked(false);
					radioButton5.setChecked(true);

				}

			}

		});

	}
	public int passPosition(int index) {
		p=index;
		return p;
	}
	
	public String passRadioActivity(String radio_activity) {

		return radio_activity;

	}

	public String backRadioActivity(String back_activity) {
		return back_activity;

	}

	public String passRadioData(CharSequence radiotext) {

		return radiotext.toString();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			singletonClass.checked = false;
			add_to_saveData();
			
			break;

		case R.id.prev_btn:
			
			
				break;
		default:
			break;
		}

	}

	private void add_to_saveData() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setAnswer(answer_Value);
		savedataUtils.setQuestion(question_Value);
		savedataUtils.setQuestionNo(question_no);
		saveDataList.add(savedataUtils);
//		singletonClass.addList(savePosition, saveDataList);
		
		map.put("answer_Value", answer_Value);
		map.put("activtiy_no", activtiy_no);
		map.put("Position", ""+p);
		singletonClass.addMap(question_Value, map);
		
		System.out.println("=== In first Activity size :"
				+ singletonClass.getList().size() + "activity number :"
				+ activtiy_no);
		if (activtiy_no == null) {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");

		} else {
			Log.e("Alert", " Not show");
			postion = Integer.parseInt(activtiy_no);
			nextMoveActivity(postion);
		}
	}

	

	private void setview(int postion) {
		System.out.println("SingleChoiceQuestion.setview()" + postion);
		radio_question_no.setText(question + " "	+ questionData.get(postion).getQuesNo());
		radio_btn_question.setText(questionData.get(postion).getQues());
		question_Value = questionData.get(postion).getQues();
		question_no = Integer.parseInt(questionData.get(postion).getQuesNo());
		System.out.println("=== himani question No is :" + question_no);
		optionlist = questionData.get(postion).getOptionArr();
		System.out.println(" ===  optionlist  :" + optionlist.size());
		radioButton3.setVisibility(View.VISIBLE);
		radioButton4.setVisibility(View.VISIBLE);
		radioButton5.setVisibility(View.VISIBLE);
		radioButton1.setText(optionlist.get(0).getOptionText());
		radioButton2.setText(optionlist.get(1).getOptionText());
		radioButton3.setText(optionlist.get(2).getOptionText());
		radioButton4.setText(optionlist.get(3).getOptionText());
		radioButton5.setText(optionlist.get(4).getOptionText());

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				System.out
						.println("SingleChoiceQuestion.init().new OnCheckedChangeListener() {...}.onCheckedChanged()");
				int select_id = radioGroup.getCheckedRadioButtonId();
				System.out.println("=== select_id :" + select_id);

				if (select_id == R.id.rd1) {
					Log.e("rd1", "matched");

					answer_Value = passRadioData(radioButton1.getText());
					System.out.println("=== himani answer data :"
							+ answer_Value);
					activtiy_no = passRadioActivity(optionlist.get(0)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(0)
							.getBack_activity());
					System.out.println("=== himani back activity number"
							+ back_activity_no);
				}
				if (select_id == R.id.rd2) {
					Log.e("rd2", "matched");

					answer_Value = passRadioData(radioButton2.getText());
					System.out.println("=== himani answer data :"
							+ answer_Value);
					activtiy_no = passRadioActivity(optionlist.get(1)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(1)
							.getBack_activity());
					System.out.println("=== himani back activity number"
							+ back_activity_no);
				}
				if (select_id == R.id.rd3) {
					Log.e("rd3", "matched");

					answer_Value = passRadioData(radioButton3.getText());
					System.out.println("=== himani answer data :"
							+ answer_Value);
					activtiy_no = passRadioActivity(optionlist.get(2)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(2)
							.getBack_activity());
					System.out.println("=== himani back activity number"
							+ back_activity_no);

				}
				if (select_id == R.id.rd4) {
					Log.e("rd4", "matched");

					answer_Value = passRadioData(radioButton4.getText());
					System.out.println("=== himani answer data :"+ answer_Value);
					activtiy_no = passRadioActivity(optionlist.get(3).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(3)
							.getBack_activity());
					System.out.println("=== himani back activity number"
							+ back_activity_no);

				}
				if (select_id == R.id.rd5) {
					Log.e("rd5", "matched");
					answer_Value = passRadioData(radioButton5.getText());
					System.out.println("=== himani answer data :"
							+ answer_Value);
					activtiy_no = passRadioActivity(optionlist.get(4)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(2)
							.getBack_activity());
					System.out.println("=== himani back activity number"
							+ back_activity_no);
				}

			}

		});
	}

	public void showMessage(String message) {
		Dialog builder = new Dialog(context);
		builder.setCancelable(true);
		builder.setTitle(message);
		builder.show();
	}

	private void nextMoveActivity(int postion) {

		if (activtiy_no.equalsIgnoreCase("2")) {
			singletonClass.checked = false;
			System.out.println(" === postion  :" + postion);
			Intent intent = new Intent(context,SingleChoiceSecondActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","1");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			 finish();
		}
		if (activtiy_no.equalsIgnoreCase("3")) {
			System.out.println(" === postion  :" + postion);
			System.out.println(" === activtiy_no  :" + activtiy_no);
			Intent intent = new Intent(context, SingleChoiceThirdActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","1");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
		if (activtiy_no.equalsIgnoreCase("4")) {
			System.out.println(" === postion  :" + postion);
			System.out.println(" === activtiy_no  :" + activtiy_no);
			Intent intent = new Intent(context, SingleChoiceFouthActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","1");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
		if (activtiy_no.equalsIgnoreCase("5")) {
			System.out.println(" === postion  :" + postion);
			System.out.println(" === activtiy_no  :" + activtiy_no);
			Intent intent = new Intent(context, SingleChoiceFiveActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","1");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
	}

	private void secondView() {
		System.out
				.println("=== himani second view called :"
						+ singletonClass.getList().get(savePosition).get(0)
								.getAnswer());
		if (singletonClass.getList().get(savePosition).get(0).getAnswer()
				.equals(radioButton1.getText().toString())) {
			System.out.println("=== himani second view called 1:"
					+ singletonClass.getList().get(savePosition).get(0)
							.getAnswer());
			radioButton1.setChecked(true);
			radioButton1.setSelected(true);

		} else if (singletonClass.getList().get(savePosition).get(0)
				.getAnswer().equals(radioButton2.getText().toString())) {
			radioButton2.setChecked(true);
			radioButton2.setSelected(true);
			System.out.println("=== himani second view called 2 :"
					+ singletonClass.getList().get(savePosition).get(0)
							.getAnswer());

		} else if (singletonClass.getList().get(savePosition).get(0)
				.getAnswer().equals(radioButton3.getText().toString())) {
			radioButton3.setChecked(true);
			radioButton3.setSelected(true);
			System.out.println("=== himani second view called 3:"
					+ singletonClass.getList().get(savePosition).get(0)
							.getAnswer());

		} else if (singletonClass.getList().get(savePosition).get(0)
				.getAnswer().equals(radioButton4.getText().toString())) {
			radioButton4.setChecked(true);
			radioButton4.setSelected(true);
			System.out.println("=== himani second view called 4:"
					+ singletonClass.getList().get(savePosition).get(0)
							.getAnswer());

		} else if (singletonClass.getList().get(savePosition).get(0)
				.getAnswer().equals(radioButton5.getText().toString())) {
			radioButton5.setChecked(true);
			radioButton5.setSelected(true);
			System.out.println("=== himani second view called 5:"
					+ singletonClass.getList().get(savePosition).get(0)
							.getAnswer());
		}

	}

}
