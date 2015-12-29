package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class CheckBoxTypeViewTwtyFourActivity extends Activity implements
		OnClickListener {
	private Activity activity = CheckBoxTypeViewTwtyFourActivity.this;
	private LinearLayout checkbox_layout7, checkbox_layout8, checkbox_layout9,
			checkbox_layout10, checkbox_layout11;
	private TextView checkbox_question_no, checkbox_question_txt,common_text;
	private CheckBox check_btn_one, check_btn_two, check_btn_three,
			check_btn_four, check_btn_five, check_btn_six, check_btn_seven,
			check_btn_eight, check_btn_nine, check_btn_ten, check_btn_eleven;
	private Button prev_btn, next_btn;
	private String check_one = "", check_two = "", check_three = "",
			check_four = "", check_five = "", check_six = "", check_seven = "",
			check_eight = "", check_nine = "", check_ten = "",
			check_eleven = "";
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private HashMap<Object, Object> map;
	private List<String> valSetOne;
	private int postion;

	private String activtiy_no;
	private String question = "Question", main_question,back_activity;

	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private int savePosition;
	private SingletonClass singletonClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_checkbox_type_activity);
		setCustomActionBar();

Toast.makeText(getApplicationContext(), "CheckBoxTypeViewTwtyFourActivity.onCreate()", Toast.LENGTH_LONG).show();
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;

		map = new HashMap<Object, Object>();

		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		Toast.makeText(getApplicationContext(), "position\n"+postion+"back_activity\n"+back_activity+"savePosition\n"+
		savePosition, Toast.LENGTH_LONG).show();
		
		System.out.println("=== postion:" + postion);
		init();
		assignClicks();
		conditionCheck();

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

		
		common_text = (TextView) findViewById(R.id.common_text);
		common_text.setVisibility(View.GONE);
		setview(postion);
	}

	private void setview(int postion) {
		checkbox_layout7.setVisibility(View.VISIBLE);
		

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

		
			//show data on check boxes
		
			check_btn_one.setText(optionlist.get(0).getOptionText());
			check_btn_two.setText(optionlist.get(1).getOptionText());
			check_btn_three.setText(optionlist.get(2).getOptionText());
			check_btn_four.setText(optionlist.get(3).getOptionText());
			check_btn_five.setText(optionlist.get(4).getOptionText());
			check_btn_six.setText(optionlist.get(5).getOptionText());
        	check_btn_seven.setText(optionlist.get(6).getOptionText());
		
		

		
		conditionCheck();
	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		
		map = singletonClass.getMap().get(main_question);

		if (map != null) {
			back_activity = "" + map.get("back_activity");

			Toast.makeText(getApplicationContext(), back_activity, Toast.LENGTH_LONG);

			if (map.get("check_one") != null&& map.get("check_one").toString().equalsIgnoreCase(optionlist.get(0).getOptionText())) {
				check_btn_one.setChecked(true);
				check_one=optionlist.get(0).getOptionText();
				map.put("check_one", check_one);
				
				
			}
			if (map.get("check_two") != null&& map.get("check_two").toString().equalsIgnoreCase(optionlist.get(1).getOptionText())) {
				check_btn_two.setChecked(true);
				check_two = optionlist.get(1).getOptionText();
				map.put("check_two", check_two);
			}

			if (map.get("check_three") != null&& map.get("check_three").toString().equalsIgnoreCase(optionlist.get(2).getOptionText())) {
				check_btn_three.setChecked(true);
				check_three=optionlist.get(2).getOptionText();
				map.put("check_three", check_three);
				
			}
			if (map.get("check_four") != null&& map.get("check_four").toString().equalsIgnoreCase(optionlist.get(3).getOptionText())) {
				check_btn_four.setChecked(true);
				check_four=optionlist.get(3).getOptionText();
				map.put("check_four", check_four);
			}
			if (map.get("check_five") != null&& map.get("check_five").toString().equalsIgnoreCase(optionlist.get(4).getOptionText())) {

				check_btn_five.setChecked(true);
				check_five=optionlist.get(4).getOptionText();
				map.put("check_five", check_five);
			}
			if (map.get("check_six") != null && map.get("check_six").toString().equalsIgnoreCase(optionlist.get(5).getOptionText())) {
				check_btn_six.setChecked(true);
				check_six=optionlist.get(5).getOptionText();
				map.put("check_six", check_six);
			}
			if (map.get("check_seven") != null && map.get("check_seven").toString().equalsIgnoreCase(optionlist.get(6).getOptionText())) {
				check_btn_seven.setChecked(true);
				check_seven=optionlist.get(6).getOptionText();
				map.put("check_seven", check_seven);
			}

			
			System.out.println("====not null map"+map);
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
		check_btn_seven.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.next_btn:
			System.out.println("=== activtiy_no :" + activtiy_no);
			
			map.put("" + (postion - 1), valSetOne);
			
		
			nextBtnAction();
			break;

		case R.id.prev_btn:
			backMove();

			break;
		case R.id.check_btn_one:

			if (check_btn_one.isChecked()) {
				activtiy_no = optionlist.get(0).getActivity();
				check_one = check_btn_one.getText().toString();
				map.put("check_one", check_one);
				
				valSetOne.add(check_one);
				singletonClass.setIndex("1");
				
				
			} else {
				valSetOne.remove(check_one);
				singletonClass.removeIndex("1");
				map.remove("check_one");
			}
			break;

		case R.id.check_btn_two:
			if (check_btn_two.isChecked()) {
				activtiy_no = optionlist.get(1).getActivity();
				check_two = check_btn_two.getText().toString();
				singletonClass.setIndex("2");
				valSetOne.add(check_two);
				map.put("check_two", check_two);
			} else {
				valSetOne.remove(check_two);
				singletonClass.removeIndex("2");
				
				map.remove("check_two");

			}
			break;

		case R.id.check_btn_three:

			if (check_btn_three.isChecked()) {
				activtiy_no = optionlist.get(2).getActivity();
				check_three = check_btn_three.getText().toString();
				valSetOne.add(check_three);
			
				map.put("check_three", check_three);
				singletonClass.setIndex("3");
			} else {
				valSetOne.remove(check_three);
				singletonClass.removeIndex("3");
				map.remove("check_three");
			}
			break;

		case R.id.check_btn_four:

			if (check_btn_four.isChecked()) {
				activtiy_no = optionlist.get(3).getActivity();
				check_four = check_btn_four.getText().toString();
				valSetOne.add(check_four);
				map.put("check_four", check_four);
				singletonClass.setIndex("4");
			} else {
				valSetOne.remove(check_four);
				
				map.remove("check_four");
				singletonClass.removeIndex("4");
			}
			break;
		case R.id.check_btn_five:
			// check_five = check_btn_five.isChecked();
			if (check_btn_five.isChecked()) {
				activtiy_no = optionlist.get(4).getActivity();
				check_five = check_btn_five.getText().toString();
				singletonClass.setIndex("5");
				map.put("check_five", check_five);

				valSetOne.add(check_five);
			} else {
				valSetOne.remove(check_five);
				map.remove("check_five");
				singletonClass.removeIndex("5");
			}
			break;

		case R.id.check_btn_six:

			if (check_btn_six.isChecked()) {
				activtiy_no = optionlist.get(5).getActivity();
				check_six = check_btn_six.getText().toString();
				valSetOne.add(check_six);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
			singletonClass.setIndex("6");
				map.put("check_six", check_six);
			} else {
				valSetOne.remove(check_six);
				singletonClass.removeIndex("6");

				
				map.remove("check_six");
			}
			break;
		case R.id.check_btn_seven:
			if (check_btn_seven.isChecked()) {
				activtiy_no = optionlist.get(6).getActivity();
				check_seven = check_btn_seven.getText().toString();
				valSetOne.add(check_seven);
				singletonClass.setIndex("7");
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
			
			
				
				map.put("check_seven", check_seven);

			} else {
				valSetOne.remove(check_seven);
			singletonClass.removeIndex("7");
				map.remove("check_seven");
			}
			break;

		default:
			break;
		
		
		}

	}

	private void backMove() {
		if(back_activity.equalsIgnoreCase("23"))
		{
		savePosition = savePosition - 1;
		back_activity="23";
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, TwentyThirdViewActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		}
		if(back_activity.equalsIgnoreCase("22"))
		{
		back_activity="22";
		savePosition = savePosition - 1;
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, CheckBoxTypeViewTwtyTwoActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		}
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

		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(main_question, map);
		
		postion = Integer.parseInt(activtiy_no);
		System.out.println(" === postion  :" + postion);
		Intent intent = new Intent(CheckBoxTypeViewTwtyFourActivity.this,CheckBoxTypeViewTwtyFiveActivity.class);
		intent.putExtra("Position", postion - 1);
		intent.putExtra("back_activity","24");
		intent.putExtra("saveposition", savePosition + 1);
		startActivity(intent);
		overridePendingTransition(0, 0);
		finish();

	}

	@Override
	public void onBackPressed() {

	}
}
