package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;
import com.scms.utility.Constant;
import com.scms.utility.QuestionUtiltiy;

public class CheckBoxTypeViewTwtyTwoActivity extends Activity implements
		OnClickListener {
	private Activity activity = CheckBoxTypeViewTwtyTwoActivity.this;
	private LinearLayout checkbox_layout7, checkbox_layout8, checkbox_layout9,
			checkbox_layout10, checkbox_layout11;
	private TextView checkbox_question_no, checkbox_question_txt;
	private CheckBox check_btn_one, check_btn_two, check_btn_three,
			check_btn_four, check_btn_five, check_btn_six, check_btn_seven,
			check_btn_eight, check_btn_nine, check_btn_ten, check_btn_eleven;
	private Button prev_btn, next_btn;
	private String check_one = "", check_two = "", check_three = "",
			check_four = "", check_five = "", check_six = "", check_seven = "",
			check_eight = "", check_nine = "", check_ten = "",
			check_eleven = "",back_activity;
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	
	private List<String> valSetOne;
	private int postion;
	private String activtiy_no;
	private String question = "Question", main_question;
	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private int savePosition;
	private SingletonClass singletonClass;
	private ArrayList<String> twentyTwoData;
	private HashMap<Object, Object> map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_checkbox_type_activity);
		setCustomActionBar();

		optionlist = new ArrayList<OptionUtils>();
		questionData = SplashScreen.QuestionArrList;
		// index = new ArrayList<Object>();
		map = new HashMap<Object, Object>();
		// twentyTwoData = Constant.twentyOnelist;
		twentyTwoData = new ArrayList<String>();

		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		singletonClass = SingletonClass.getInstance();
		
		back_activity = getIntent().getStringExtra("back_activity");
		System.out.println("=== postion:" + postion);
		init();
		assignClicks();
		conditionCheck();
	}

	private void setCustomActionBar() {
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(
				R.layout.custom_action_bar, null);
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
		checkbox_question_no = (TextView) findViewById(R.id.checkbox_question_no);
		checkbox_question_txt = (TextView) findViewById(R.id.checkbox_question_txt);
		checkbox_layout7 = (LinearLayout) findViewById(R.id.checkbox_layout7);
		checkbox_layout8 = (LinearLayout) findViewById(R.id.checkbox_layout8);
		checkbox_layout9 = (LinearLayout) findViewById(R.id.checkbox_layout9);
		checkbox_layout10 = (LinearLayout) findViewById(R.id.checkbox_layout10);
		checkbox_layout11 = (LinearLayout) findViewById(R.id.checkbox_layout11);
		check_btn_one = (CheckBox) findViewById(R.id.check_btn_one);
		check_btn_two = (CheckBox) findViewById(R.id.check_btn_two);
		check_btn_three = (CheckBox) findViewById(R.id.check_btn_three);
		check_btn_four = (CheckBox) findViewById(R.id.check_btn_four);
		check_btn_five = (CheckBox) findViewById(R.id.check_btn_five);
		check_btn_six = (CheckBox) findViewById(R.id.check_btn_six);
		check_btn_seven = (CheckBox) findViewById(R.id.check_btn_seven);
		check_btn_eight = (CheckBox) findViewById(R.id.check_btn_eight);
		check_btn_nine = (CheckBox) findViewById(R.id.check_btn_nine);
		check_btn_ten = (CheckBox) findViewById(R.id.check_btn_ten);
		check_btn_eleven = (CheckBox) findViewById(R.id.check_btn_eleven);
		next_btn = (Button) findViewById(R.id.next_btn);
		prev_btn = (Button) findViewById(R.id.prev_btn);
		setview(postion);
	}

	private void setview(int postion) {
		
		System.out.println("=====map size to set visibility of 212223"+singletonClass.map.size());
		
	
		checkbox_layout7.setVisibility(View.GONE);
		checkbox_layout8.setVisibility(View.GONE);
		checkbox_layout9.setVisibility(View.GONE);
		checkbox_layout10.setVisibility(View.GONE);
		checkbox_layout11.setVisibility(View.GONE);
		check_btn_one.setChecked(false);
		check_btn_two.setChecked(false);
		check_btn_three.setChecked(false);
		check_btn_four.setChecked(false);
		check_btn_five.setChecked(false);
		check_btn_six.setChecked(false);
		

		valSetOne = new ArrayList<String>();

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		main_question = questionData.get(postion).getQues();
		activtiy_no = questionData.get(postion).getActivity();
		optionlist = questionData.get(postion).getOptionArr();
		checkbox_question_no.setText(question + " " + (savePosition + 1));
		checkbox_question_txt.setText(questionData.get(postion).getQues());
		System.out.println(" =====optionlist:size ::" + optionlist.size());
		check_btn_one.setText(optionlist.get(0).getOptionText());
		check_btn_two.setText(optionlist.get(1).getOptionText());
		check_btn_three.setText(optionlist.get(2).getOptionText());
		check_btn_four.setText(optionlist.get(3).getOptionText());
		check_btn_five.setText(optionlist.get(4).getOptionText());
		check_btn_six.setText(optionlist.get(5).getOptionText());
		
		check_one = check_btn_one.getText().toString();
		check_two = check_btn_two.getText().toString();
		check_three = check_btn_three.getText().toString();
		check_four = check_btn_four.getText().toString();
		check_five = check_btn_five.getText().toString();
		check_six = check_btn_six.getText().toString();
		
		
	}
	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		
		map = singletonClass.getMap().get(QuestionUtiltiy.Q22);
        Toast.makeText(getApplicationContext(), "map22"+map, Toast.LENGTH_LONG).show();
		if (map != null) {
			back_activity = "" + map.get("back_activity");
			
            if (map.get(QuestionUtiltiy.Q22_1) != null&& map.get(QuestionUtiltiy.Q22_1).toString().equalsIgnoreCase(optionlist.get(0).getOptionText())) {
				check_btn_one.setChecked(true);
				check_one=optionlist.get(0).getOptionText();
				map.put(QuestionUtiltiy.Q21_1, check_one);
			}
			if (map.get(QuestionUtiltiy.Q22_2) != null&& map.get(QuestionUtiltiy.Q22_2).toString().equalsIgnoreCase(optionlist.get(1).getOptionText())) {
				check_btn_two.setChecked(true);
				check_two = optionlist.get(1).getOptionText();
				map.put(QuestionUtiltiy.Q21_1, check_two);
				
			}

			if (map.get(QuestionUtiltiy.Q22_3) != null&& map.get(QuestionUtiltiy.Q22_3).toString().equalsIgnoreCase(optionlist.get(2).getOptionText())) {
				check_btn_three.setChecked(true);
				check_three=optionlist.get(2).getOptionText();
				map.put(QuestionUtiltiy.Q21_1, check_three);
			}
			if (map.get(QuestionUtiltiy.Q22_4) != null&& map.get(QuestionUtiltiy.Q22_4).toString().equalsIgnoreCase(optionlist.get(3).getOptionText())) {
				check_btn_four.setChecked(true);
				check_four=optionlist.get(3).getOptionText();
				map.put(QuestionUtiltiy.Q21_1, check_four);
			}
			if (map.get(QuestionUtiltiy.Q22_5) != null&& map.get(QuestionUtiltiy.Q22_5).toString().equalsIgnoreCase(optionlist.get(4).getOptionText())) {
				check_btn_five.setChecked(true);
				check_five=optionlist.get(4).getOptionText();
				map.put(QuestionUtiltiy.Q21_1, check_five);
			}
			if (map.get(QuestionUtiltiy.Q22_6) != null && map.get(QuestionUtiltiy.Q22_6).toString().equalsIgnoreCase(optionlist.get(5).getOptionText())) {
				check_btn_six.setChecked(true);
				check_six=optionlist.get(5).getOptionText();
				map.put(QuestionUtiltiy.Q21_1, check_six);
				
			}
			
            activtiy_no=""+map.get("activtiy_no" );
     
            singletonClass.addMap(main_question, map);	
		}

	}
	private void assignClicks() {
		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);
		check_btn_one.setOnClickListener(this);
		check_btn_two.setOnClickListener(this);
		check_btn_three.setOnClickListener(this);
		check_btn_four.setOnClickListener(this);
		check_btn_five.setOnClickListener(this);
		check_btn_six.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			
			map.put("" + (postion - 1), valSetOne);
			Constant.twentyTwoList = twentyTwoData;
		

			nextBtnAction();
			finish();
			break;

		case R.id.prev_btn:
			backMove();
			break;
		case R.id.check_btn_one:

			if (check_btn_one.isChecked()) {
				activtiy_no = optionlist.get(0).getActivity();
				check_one = check_btn_one.getText().toString();
				map.put(QuestionUtiltiy.Q21_1, QuestionUtiltiy.Q21_5);
				
				valSetOne.add(check_one);
			singletonClass.map.put(optionlist.get(0).getOptionText(), check_one);

			} else {
				valSetOne.remove(check_one);
				
				singletonClass.map.remove(optionlist.get(0).getOptionText());
				map.remove(QuestionUtiltiy.Q21_1);
			}
			break;

		case R.id.check_btn_two:
			if (check_btn_two.isChecked()) {
				activtiy_no = optionlist.get(1).getActivity();
				check_two = check_btn_two.getText().toString();
				valSetOne.add(check_two);
				
				singletonClass.map.put(optionlist.get(1).getOptionText(), check_two);
				
				map.put(QuestionUtiltiy.Q21_2, QuestionUtiltiy.Q21_2);
			} else {
				valSetOne.remove(check_two);
				singletonClass.map.remove(optionlist.get(1).getOptionText());
				
				map.remove(QuestionUtiltiy.Q21_2);

			}
			break;

		case R.id.check_btn_three:

			if (check_btn_three.isChecked()) {
				activtiy_no = optionlist.get(2).getActivity();
				check_three = check_btn_three.getText().toString();
				valSetOne.add(check_three);
			
				singletonClass.map.put(optionlist.get(2).getOptionText(), check_three);
				map.put(QuestionUtiltiy.Q21_3, QuestionUtiltiy.Q21_3);
			} else {
				valSetOne.remove(check_three);
				
				singletonClass.map.remove(optionlist.get(2).getOptionText());
				map.remove(QuestionUtiltiy.Q21_3);
			}
			break;

		case R.id.check_btn_four:

			if (check_btn_four.isChecked()) {
				activtiy_no = optionlist.get(3).getActivity();
				check_four = check_btn_four.getText().toString();
				valSetOne.add(check_four);
				
				singletonClass.map.put(optionlist.get(3).getOptionText(), check_four);
				map.put(QuestionUtiltiy.Q21_4, QuestionUtiltiy.Q21_4);
				
			} else {
				valSetOne.remove(check_four);
			
				singletonClass.map.remove(optionlist.get(3).getOptionText());
				map.remove(QuestionUtiltiy.Q21_4);
			}
			break;
		case R.id.check_btn_five:
			// check_five = check_btn_five.isChecked();
			if (check_btn_five.isChecked()) {
				activtiy_no = optionlist.get(4).getActivity();
				check_five = check_btn_five.getText().toString();
				
				map.put(QuestionUtiltiy.Q21_5, QuestionUtiltiy.Q21_5);
				singletonClass.map.put(optionlist.get(4).getOptionText(), check_five);
		
				valSetOne.add(check_five);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				
			} else {
				valSetOne.remove(check_five);
				singletonClass.map.remove(optionlist.get(4).getOptionText());
				map.remove(QuestionUtiltiy.Q21_5);
			}
			break;

		case R.id.check_btn_six:

			if (check_btn_six.isChecked()) {
				activtiy_no = optionlist.get(5).getActivity();
				check_six = check_btn_six.getText().toString();
				valSetOne.add(check_six);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
			
				singletonClass.map.put(optionlist.get(5).getOptionText(),check_six);
				map.put(QuestionUtiltiy.Q21_6,QuestionUtiltiy.Q21_6);
			} else {
				valSetOne.remove(check_six);
				singletonClass.map.remove(optionlist.get(0).getOptionText());
				map.remove(QuestionUtiltiy.Q21_6);
			}
			break;


		default:
			break;
		}

	}

	private void backMove() {
		savePosition = savePosition - 1;
		back_activity="21";
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, CheckBoxTypeViewTwtyOneActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		
		
	}

	private void nextBtnAction() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setQuestion(main_question);
		savedataUtils.setQuestionNo(questionNo);
		savedataUtils.setCheck1(check_one);
		savedataUtils.setCheck2(check_two);
		savedataUtils.setCheck3(check_three);
		savedataUtils.setCheck4(check_four);
		savedataUtils.setCheck5(check_five);
		savedataUtils.setCheck6(check_six);
	//	saveDataList.add(savedataUtils);
		
        map.put("back_activity", back_activity);
        map.put("activtiy_no", activtiy_no);
		
		singletonClass.addMap(main_question, map);
		
		System.out.println("===22data"+singletonClass.getMap().get(QuestionUtiltiy.Q22));
		if (singletonClass.map.size()==0) {
			activtiy_no="24";
			postion = Integer.parseInt(activtiy_no);
			System.out.println(" === postion in 22 Activity :  :" + postion);
			Intent intent = new Intent(CheckBoxTypeViewTwtyTwoActivity.this,CheckBoxTypeViewTwtyFourActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","22");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);

		} else if ( singletonClass.map.size() >0) {

			    activtiy_no="23";
				postion = Integer.parseInt(activtiy_no);
			
				Intent intent = new Intent(CheckBoxTypeViewTwtyTwoActivity.this,TwentyThirdViewActivity.class);
				intent.putExtra("Position", postion - 1);
				intent.putExtra("back_activity","22");
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);
			

		}
		
	}

	@Override
	public void onBackPressed() {

	}

}
