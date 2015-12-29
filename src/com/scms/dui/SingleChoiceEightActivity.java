package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import com.scms.utility.QuestionUtiltiy;

public class SingleChoiceEightActivity extends Activity implements OnClickListener {

	Context context = SingleChoiceEightActivity.this;
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
	private String activtiy_no, back_activity_no;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private SingletonClass singletonClass;
	private int savePosition;
	private HashMap<Object, Object> map;
	private String back_activity;
	int p, sp;
	private ArrayList<Object> arrList;
	String text1,text2,text3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_type_activity);
		setCustomActionBar();
		optionlist = new ArrayList<OptionUtils>();
		arrList= new ArrayList<Object>();
		singletonClass = SingletonClass.getInstance();
		map = new HashMap<Object, Object>();
		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		back_activity = getIntent().getStringExtra("back_activity");

		System.out.println("=== back_activity:" + back_activity);
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

		radio_question_no.setText(question + " " + (savePosition + 1));
		radio_btn_question.setText(questionData.get(postion).getQues());
		question_Value = questionData.get(postion).getQues();

		questionType = questionData.get(postion).getQuesType();
		System.out.println("=== questionType :" + questionType);

		// radioButton1.setText(optionlist.get(0).getOptionText());
		// radioButton2.setText(optionlist.get(1).getOptionText());
		// radioButton3.setText(optionlist.get(2).getOptionText());
		text1="Blow a \"Fail\" result";
		text2 ="Blow a result other than \"Fail\" but the police officer arrested you anyway.";
		text3="Provide several breath samples but not get a \'proper reading\' and then get charged for refusing to " +
				"provide a suitable breath sample.";
		
		radioButton1.setText(text1);
		radioButton2.setText(text2);
		radioButton3.setText(text3);

		conditionCheck();
	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map = singletonClass
				.getMap()
				.get(QuestionUtiltiy.Q8);
		Toast.makeText(getApplicationContext(), " out condition", Toast.LENGTH_LONG).show();
		if (singletonClass.getMap().get(QuestionUtiltiy.Q9) != null) {
			singletonClass.getMap().remove(QuestionUtiltiy.Q9);
		}

		if (map != null)

		{
			Toast.makeText(getApplicationContext(), " in condition", Toast.LENGTH_LONG).show();
			back_activity = "" + map.get("back_activity");
			if (text1.equalsIgnoreCase((String) map.get("answer_Value"))) {
				radioButton1.setChecked(true);
			}
			if (text2.equalsIgnoreCase((String) map.get("answer_Value"))) {
				radioButton2.setChecked(true);
			}
			if (text3.equalsIgnoreCase((String) map.get("answer_Value"))) {
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
				if (select_id == R.id.rd3) {
					Log.e("rd3", "matched");

					/*answer_Value = passRadioData(radioButton3.getText());
					activtiy_no = passRadioActivity(optionlist.get(2)
							.getActivity());

					back_activity_no = backRadioActivity(optionlist.get(2).getBack_activity());*/
					
					
					if(singletonClass.getMap().get(QuestionUtiltiy.Q6) != null)
					{
					int p6 = 0,sp6 = 0;
					arrList = getDoubleSingleChoiceData(QuestionUtiltiy.Q6);

					if (arrList.size() == 1 && sp==1) {
						 p6 = (Integer) arrList.get(0);
					}
					if (arrList.size() == 2) {
						p6 = (Integer) arrList.get(0);
						sp6 = (Integer) arrList.get(1);
					}
					
					if(p6==0 && sp6==0)
					{
						Utils.alertDialogToMoveContact(context, "Attentation!",
								"You have indicated that you were charged with “refusing a breath sample”. This questionnaire cannot assist with that charge. Please contact David Anber, a criminal lawyer, 24hrs/day to discuss your charges.",radioButton3);
						answer_Value = passRadioData(radioButton3.getText());
						activtiy_no = passRadioActivity(optionlist.get(2).getActivity());
					}
				   }

				}
				if (select_id == R.id.rd4) {
					Log.e("rd4", "matched");

					answer_Value = passRadioData(radioButton4.getText());
					activtiy_no = passRadioActivity(optionlist.get(3).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(3).getBack_activity());
					System.out.println("=== himani back activity number"+ back_activity_no);

				}
				if (select_id == R.id.rd5) {
					Log.e("rd5", "matched");

					answer_Value = passRadioData(radioButton5.getText());
					activtiy_no = passRadioActivity(optionlist.get(4).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
					back_activity_no = backRadioActivity(optionlist.get(4).getBack_activity());
					System.out.println("=== himani back activity number"+ back_activity_no);
				}

			}

		});

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


	public int passPosition(int index) {
		p = index;
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
			System.out.println("=== index::" + p);
			add_to_saveData();
			
			break;

		case R.id.prev_btn:
			 saveData();
			 
			savePosition = savePosition - 1;
			 back_activity="7";
			System.out.println(" ==== back_activity"+back_activity);
			postion= Integer.parseInt(back_activity);
			Intent intent = new Intent(context,DoubleSingleChoiceSevenActivity.class);
			System.out.println(" ==== savePosition" + savePosition);
			intent.putExtra("saveposition", savePosition);
			intent.putExtra("Position", 6);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
			break;
		default:
			break;
		}

	}
	
	private void  saveData()
	{
		map.put("activtiy_no", activtiy_no);
		map.put("back_activity", back_activity);
		map.put("Position", "" + p);
		map.put("answer_Value", answer_Value);
		singletonClass.addMap(question_Value, map);
	}

	private void add_to_saveData() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setAnswer(answer_Value);
		savedataUtils.setQuestion(question_Value);
		savedataUtils.setQuestionNo(question_no);
		saveDataList.add(savedataUtils);
		// singletonClass.addList(savePosition, saveDataList);
		 saveData();
	
		System.out.println("=== himani In 8 Activity Arraylist Size :"+ saveDataList.size());

		if (savedataUtils.getAnswer().equals("")) {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!","Please answer All questions.");

		} else {
			Log.e("Alert", " Not show");
			postion = Integer.parseInt(activtiy_no);
			System.out.println(":::::postion" + postion);
			nextMoveActivity(postion);
		}
	}

	private void nextMoveActivity(int postion) {

		System.out.println(" === postion  :" + postion);
		Intent intent = new Intent(context, NineViewActivity.class);
		intent.putExtra("Position", postion - 1);
		intent.putExtra("back_activity", "8");
		intent.putExtra("saveposition", savePosition + 1);
		startActivity(intent);
		overridePendingTransition(0, 0);
		finish();

	}

	@Override
	public void onBackPressed() {
		
	}
}
