package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.Utils;
import com.scms.utility.QuestionUtiltiy;

public class SingleChoiceTenActivity extends Activity implements
		OnClickListener {

	Context context = SingleChoiceTenActivity.this;
	private RadioButton radioButton1, radioButton2, radioButton3, radioButton4,
			radioButton5;
	private RadioGroup radioGroup;
	private TextView radio_btn_question, radio_question_no;
	private Button nextBtn, prevBtn;
	private ArrayList<QuestionUtils> questionData;
	private int postion, back, question_no;
	private int back_position;
	private String question = "Question";
	private ArrayList<OptionUtils> optionlist;
	private String questionType, token, question_Value = "", answer_Value = "";
	private String activtiy_no, back_activity_no,back_activity;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private SingletonClass singletonClass;
	private int savePosition;
	private HashMap<Object, Object> map;
	int p,sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_type_activity);
		setCustomActionBar();
		
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		System.out.println("=== himani save position in 10 Activity :"+ savePosition);
		map = new HashMap<Object, Object>();
		
		init();
		getData();
		assignClick();

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
		radio_btn_question = (TextView) findViewById(R.id.radio_btn_question);
		radio_question_no = (TextView) findViewById(R.id.radio_question_no);

		radioButton1 = (RadioButton) findViewById(R.id.rd1);
		radioButton2 = (RadioButton) findViewById(R.id.rd2);
		radioButton3 = (RadioButton) findViewById(R.id.rd3);
		radioButton4 = (RadioButton) findViewById(R.id.rd4);
		radioButton5 = (RadioButton) findViewById(R.id.rd5);
		radioButton3.setVisibility(View.GONE);
		radioButton4.setVisibility(View.GONE);
		radioButton5.setVisibility(View.GONE);

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

		nextBtn = (Button) findViewById(R.id.next_btn);
		prevBtn = (Button) findViewById(R.id.prev_btn);
	}

	private void getData() {
		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionData = SplashScreen.QuestionArrList;
		// postion = getIntent().getIntExtra("Position", 0);
		// back = postion;

		System.out.println("=== postion :" + postion);
		System.out.println("=== question Data size :" + questionData.size());
		question_no = Integer.parseInt(questionData.get(postion).getQuesNo());
		optionlist = questionData.get(postion).getOptionArr();
		System.out.println(" ===  optionlist  :" + optionlist.size());
		System.out.println("=== himani question No. is :" + question_no);

		radio_question_no.setText(question + " "
				+ (savePosition+1));
		radio_btn_question.setText(questionData.get(postion).getQues());
		question_Value = questionData.get(postion).getQues();

		questionType = questionData.get(postion).getQuesType();
		System.out.println("=== questionType :" + questionType);

		radioButton1.setText(optionlist.get(0).getOptionText());
		radioButton2.setText(optionlist.get(1).getOptionText());

		conditionCheck();
	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		
		
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1= singletonClass.getMap().get(QuestionUtiltiy.Q8);
		System.out.println("=== map8"+map1);
		
		map = singletonClass
				.getMap()
				.get("When the police told you at the roadside that you had the right to speak to a lawyer "
						+ "was he\bshe reading it, or telling it to you from memory?");

		if (singletonClass
				.getMap()
				.get("How long did it take to travel from the scene of you being arrested to the police station?") != null) {
			singletonClass
					.getMap()
					.remove("How long did it take to travel from the scene of you being arrested to the police station?");
		}

		if (map != null) {
			back_activity = "" + map.get("back_activity");
			if (optionlist.get(0).getOptionText().equalsIgnoreCase((String) map.get("answer_Value"))) {
				radioButton1.setChecked(true);
			}
			if (optionlist.get(1).getOptionText().equalsIgnoreCase((String) map.get("answer_Value"))) {
				radioButton2.setChecked(true);
			}
			if (optionlist.get(2).getOptionText().equalsIgnoreCase((String) map.get("answer_Value"))) {
				radioButton3.setChecked(true);
			}

			answer_Value = "" + map.get("answer_Value");
			activtiy_no = "" + map.get("activtiy_no");

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

					answer_Value = passRadioData(radioButton1.getText());

					activtiy_no = passRadioActivity(optionlist.get(0).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(0).getBack_activity());
					System.out.println("=== himani back activity number"+ back_activity_no);
				}
				if (select_id == R.id.rd2) {
					Log.e("rd2", "matched");

					answer_Value = passRadioData(radioButton2.getText());
					activtiy_no = passRadioActivity(optionlist.get(1).getActivity());

					back_activity_no = backRadioActivity(optionlist.get(1).getBack_activity());

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
			add_to_saveData();
			
			break;

		case R.id.prev_btn:
			
		
			savePosition = savePosition - 1;
			postion= Integer.parseInt(back_activity);
			System.out.println("===savePosition"+savePosition);
			Intent intent = new Intent(context,NineViewActivity.class);
			System.out.println(" ==== savePosition"+savePosition);
			intent.putExtra("saveposition", savePosition);
			intent.putExtra("Position", postion - 1);
			startActivity(intent);
			

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
		
		map.put("activtiy_no", activtiy_no);
		map.put("back_activity", back_activity);
		map.put("Position", ""+p);
		map.put("answer_Value", answer_Value);
		singletonClass.addMap(question_Value, map);

		if (savedataUtils.getAnswer().equals("")) {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");

		} else {
			Log.e("Alert", " Not show");
			postion = Integer.parseInt(activtiy_no);
			System.out.println(":::::postion" + postion);
			nextMoveActivity(postion);
		}
	}

	private void nextMoveActivity(int postion) {

		System.out.println(" === postion  :" + postion);
		Intent intent = new Intent(context, SingleChoiceElevenActivity.class);
		intent.putExtra("Position", postion - 1);
		intent.putExtra("back_activity","10");
		intent.putExtra("saveposition", savePosition + 1);
		startActivity(intent);
		overridePendingTransition(0, 0);
		finish();

	}

	@Override
	public void onBackPressed() {

	}
}
