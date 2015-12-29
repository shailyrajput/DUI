package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.TwentyFiveDataUtils;
import com.scms.dui.utils.Utils;
import com.scms.utility.Constant;

public class CheckBoxTypeViewTwtyFiveActivity extends Activity implements
		OnClickListener {
	private Activity activity = CheckBoxTypeViewTwtyFiveActivity.this;

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
	// private Map<String, List<String>> map;
	private List<String> valSetOne;
	private int postion;
	private int savePosition;

	private String activtiy_no;
	private String question = "Question", main_question,back_activity;

	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private SingletonClass singletonClass;
	private ArrayList<TwentyFiveDataUtils> twentyFiveDatalist;
	TwentyFiveDataUtils utils;
	private HashMap<Object, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_checkbox_type_activity);
		setCustomActionBar();
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		map = new HashMap<Object, Object>();
		postion = getIntent().getIntExtra("Position", 0);
		System.out.println("=== postion in 25 Activity :" + postion);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		twentyFiveDatalist = new ArrayList<TwentyFiveDataUtils>();
		utils = new TwentyFiveDataUtils();
		init();
		assignClicks();
		conditionCheck();

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

		check_btn_three.setChecked(false);
		check_btn_four.setChecked(false);
		check_btn_five.setChecked(false);
		check_btn_six.setChecked(false);
		valSetOne = new ArrayList<String>();

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		System.out.println("=== himani question No in 25 Activity :"
				+ questionNo);
		main_question = questionData.get(postion).getQues();

		optionlist = questionData.get(postion).getOptionArr();
		checkbox_question_no.setText(question + " " + (savePosition + 1));
		checkbox_question_txt.setText(questionData.get(postion).getQues());
		System.out.println(" =====optionlist:size ::" + optionlist.size());

		if (optionlist.size() == 11) {
			checkbox_layout8.setVisibility(View.VISIBLE);
			checkbox_layout9.setVisibility(View.VISIBLE);
			checkbox_layout10.setVisibility(View.VISIBLE);
			checkbox_layout11.setVisibility(View.VISIBLE);
			
			check_btn_one.setText(optionlist.get(0).getOptionText());
			check_btn_two.setText(optionlist.get(1).getOptionText());
			check_btn_three.setText(optionlist.get(2).getOptionText());
			check_btn_four.setText(optionlist.get(3).getOptionText());
			check_btn_five.setText(optionlist.get(4).getOptionText());
			check_btn_six.setText(optionlist.get(5).getOptionText());
			check_btn_seven.setText(optionlist.get(6).getOptionText());
			check_btn_eight.setText(optionlist.get(7).getOptionText());
			check_btn_nine.setText(optionlist.get(8).getOptionText());
			check_btn_ten.setText(optionlist.get(9).getOptionText());
			check_btn_eleven.setText(optionlist.get(10).getOptionText());
			activtiy_no = optionlist.get(0).getActivity();
			
			check_btn_one.setChecked(true);
			check_one = check_btn_one.getText().toString();
			utils.setOptiontext(check_btn_one.getText().toString());
			utils.setOptionNo("1");
			twentyFiveDatalist.add(utils);
			check_btn_two.setChecked(true);
			check_two = check_btn_two.getText().toString();
			utils.setOptiontext(check_btn_two.getText().toString());
			utils.setOptionNo("2");
			twentyFiveDatalist.add(utils);
			
			conditionCheck();
		}

	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		
		map = singletonClass.getMap().get(main_question);

		if (map != null) {
			back_activity = "" + map.get("back_activity");

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
			
			if (map.get("check_eight") != null && map.get("check_eight").toString().equalsIgnoreCase(optionlist.get(7).getOptionText())) {
				check_btn_eight.setChecked(true);
				check_eight=optionlist.get(7).getOptionText();
				map.put("check_eight", check_eight);
			}
			
			if (map.get("check_nine") != null && map.get("check_nine").toString().equalsIgnoreCase(optionlist.get(8).getOptionText())) {
				check_btn_nine.setChecked(true);
				check_nine=optionlist.get(8).getOptionText();
				map.put("check_nine", check_nine);
				
			}
			
			if (map.get("check_ten") != null && map.get("check_ten").toString().equalsIgnoreCase(optionlist.get(9).getOptionText())) {
				
				check_ten=optionlist.get(9).getOptionText();
				check_btn_ten.setChecked(true);
				map.put("check_ten", check_ten);
			}
			
			if (map.get("check_eleven") != null && map.get("check_eleven").toString().equalsIgnoreCase(optionlist.get(10).getOptionText())) {
				check_btn_eleven.setChecked(true);
				check_eleven=optionlist.get(10).getOptionText();
				map.put("check_eleven", check_eleven);
			}

		    System.out.println("====not null map"+map);
			activtiy_no=""+map.get("activtiy_no" );
			System.out.println("====not null map"+map);
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
		check_btn_eight.setOnClickListener(this);
		check_btn_nine.setOnClickListener(this);
		check_btn_ten.setOnClickListener(this);
		check_btn_eleven.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.next_btn:
			System.out.println("=== activtiy_no :" + activtiy_no);
			if (activtiy_no != null) {
				postion = Integer.parseInt(activtiy_no);
			    nextBtnAction();
			} else {
				Utils.alertDilogWithOkBtn(activity, "Attention!",
						"Please answer All questions.");
			}
			/*
			 * for (Map.Entry<String, List<String>> entry :
			 * Constant.MAP.entrySet()) { String key = entry.getKey();
			 * List<String> values = entry.getValue();
			 * System.out.println("Key = " + key);
			 * System.out.println("Values = " + values + "\n"); }
			 */
			// moveActivity();

			break;

		case R.id.prev_btn:
		backMove();

			break;
		case R.id.check_btn_one:

			if (check_btn_one.isChecked()) {
				activtiy_no = optionlist.get(0).getActivity();
				check_one = check_btn_one.getText().toString();
				valSetOne.add(check_one);
				utils.setOptiontext(check_four);

				utils.setOptionNo("1");
				twentyFiveDatalist.add(utils);

				map.put("check_one", check_one);

			} else {
				valSetOne.remove(check_one);
				twentyFiveDatalist.remove(utils);
				map.remove("check_one");
			}
			break;

		case R.id.check_btn_two:
			if (check_btn_two.isChecked()) {
				activtiy_no = optionlist.get(1).getActivity();
				check_two = check_btn_two.getText().toString();
				valSetOne.add(check_two);
				utils.setOptiontext(check_four);
				utils.setOptionNo("2");
				map.put("check_two", check_two);
				twentyFiveDatalist.add(utils);
			} else {
				valSetOne.remove(check_two);
				twentyFiveDatalist.remove(utils);
				map.remove("check_two");
			}
			break;

		case R.id.check_btn_three:

			if (check_btn_three.isChecked()) {
				activtiy_no = optionlist.get(2).getActivity();
				check_three = check_btn_three.getText().toString();
				valSetOne.add(check_three);
				utils.setOptiontext(check_four);
				utils.setOptionNo("3");
				twentyFiveDatalist.add(utils);
				map.put("check_three", check_three);
			} else {
				valSetOne.remove(check_three);
				twentyFiveDatalist.remove(utils);
				map.remove("check_three");

			}
			break;

		case R.id.check_btn_four:

			if (check_btn_four.isChecked()) {
				activtiy_no = optionlist.get(3).getActivity();
				check_four = check_btn_four.getText().toString();
				valSetOne.add(check_four);
				utils.setOptiontext(check_four);
				utils.setOptionNo("4");
				twentyFiveDatalist.add(utils);
				map.put("check_four", check_four);
			} else {
				valSetOne.remove(check_four);
				twentyFiveDatalist.remove(utils);
				map.remove("check_four");
			}
			break;
		case R.id.check_btn_five:
			// check_five = check_btn_five.isChecked();
			if (check_btn_five.isChecked()) {
				activtiy_no = optionlist.get(4).getActivity();
				check_five = check_btn_five.getText().toString();
				/*
				 * himani start Alert Dialog
				 */

				Utils.alertDialogToMoveContact(activity, "Hehehe!",
						"You have indicated that you were charged with �refusing a breath sample�. This questionnaire cannot assist with that charge." +
						" Please contact David Anber, a criminal lawyer, 24hrs/day to discuss your charges.",check_btn_five);
				/*AlertDialog.Builder alBuilder = new AlertDialog.Builder(
						activity);
				alBuilder.setTitle("Attention!");
				alBuilder
						.setMessage(
								"You have indicated that you were charged with �refusing a breath sample�. This questionnaire cannot assist with that charge. Please contact David Anber, a criminal lawyer, 24hrs/day to discuss your charges.")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Intent intent = new Intent(
												CheckBoxTypeViewTwtyFiveActivity.this,
												ContactDUILawyer.class);
										startActivity(intent);
										finish();
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										check_btn_five.setChecked(false);
										twentyFiveDatalist.remove(check_five);
										dialog.cancel();
									}
								});

				AlertDialog alertDialog = alBuilder.create();
				alertDialog.show();*/
				/*
				 * finish Alert Functionality
				 */

				valSetOne.add(check_five);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				utils.setOptiontext(check_five);
				utils.setOptionNo("5");
				twentyFiveDatalist.add(utils);
				map.put("check_five", check_five);
			} else {
				valSetOne.remove(check_five);
				twentyFiveDatalist.remove(utils);
				map.remove("check_five");

			}
			break;

		case R.id.check_btn_six:

			if (check_btn_six.isChecked()) {
				activtiy_no = optionlist.get(5).getActivity();
				check_six = check_btn_six.getText().toString();
				valSetOne.add(check_six);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				utils.setOptiontext(check_six);
				utils.setOptionNo("6");
				twentyFiveDatalist.add(utils);
				map.put("check_six", check_six);
			} else {
				valSetOne.remove(check_six);
				twentyFiveDatalist.remove(utils);
				map.remove("check_six");
			}
			break;
		case R.id.check_btn_seven:
			if (check_btn_seven.isChecked()) {
				activtiy_no = optionlist.get(6).getActivity();
				check_seven = check_btn_seven.getText().toString();
				valSetOne.add(check_seven);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				utils.setOptiontext(check_seven);
				utils.setOptionNo("7");
				twentyFiveDatalist.add(utils);
				map.put("check_seven", check_seven);

			} else {
				valSetOne.remove(check_seven);
				twentyFiveDatalist.remove(utils);
				map.remove("check_seven");
			}
			break;
		case R.id.check_btn_eight:
			if (check_btn_eight.isChecked()) {
				activtiy_no = optionlist.get(7).getActivity();
				check_eight = check_btn_eight.getText().toString();
				valSetOne.add(check_eight);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				utils.setOptiontext(check_eight);
				utils.setOptionNo("8");
				twentyFiveDatalist.add(utils);
				map.put("check_eight", check_eight);
			} else {
				valSetOne.remove(check_eight);
				twentyFiveDatalist.remove(utils);
				map.remove("check_eight");
			}
			break;
		case R.id.check_btn_nine:
			if (check_btn_nine.isChecked()) {
				activtiy_no = optionlist.get(8).getActivity();
				check_nine = check_btn_nine.getText().toString();
				valSetOne.add(check_nine);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				utils.setOptiontext(check_nine);
				utils.setOptionNo("9");
				twentyFiveDatalist.add(utils);
				map.put("check_nine", check_nine);
			} else {
				valSetOne.remove(check_nine);
				map.remove("check_nine");
				twentyFiveDatalist.remove(utils);
			}
			break;
		case R.id.check_btn_ten:
			if (check_btn_ten.isChecked()) {
				activtiy_no = optionlist.get(9).getActivity();
				check_ten = check_btn_ten.getText().toString();
				valSetOne.add(check_ten);

				utils.setOptiontext(check_ten);
				utils.setOptionNo("10");
				twentyFiveDatalist.add(utils);
				map.put("check_ten", check_ten);
			} else {
				valSetOne.remove(check_ten);
				map.remove("check_ten");

				twentyFiveDatalist.remove(utils);
			}
			break;
		case R.id.check_btn_eleven:
			if (check_btn_eleven.isChecked()) {
				activtiy_no = optionlist.get(10).getActivity();
				check_eleven = check_btn_eleven.getText().toString();
				/*
				 * Utils.alertDialogTwoButton(
				 * CheckBoxTypeViewTwtyFiveActivity.this, "Attention!",
				 * "You have indicated that there was a fatality in your case. You should immediately contact a lawyer as this is an extremely serious situation. For a free consultation with a criminal lawyer contact David Anber 24hrs/day."
				 * );
				 */

				/*
				 * himani start Alert Dialog
				 */

				
				Utils.alertDialogToMoveContact(activity, "Hehehe!",
						"You have indicated that there was a fatality in your case. You should immediately contact a lawyer as this is an extremely serious situation." +
						" For a free consultation with a criminal lawyer contact David Anber 24hrs/day.",check_btn_eleven);
/*				AlertDialog.Builder alBuilder = new AlertDialog.Builder(
						activity);
				alBuilder.setTitle("Attention!");
				alBuilder
						.setMessage(
								"You have indicated that there was a fatality in your case. You should immediately contact a lawyer as this is an extremely serious situation. For a free consultation with a criminal lawyer contact David Anber 24hrs/day.")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Intent intent = new Intent(
												CheckBoxTypeViewTwtyFiveActivity.this,
												ContactDUILawyer.class);
										startActivity(intent);
										finish();
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										check_btn_eleven.setChecked(false);
										twentyFiveDatalist.remove(check_eleven);
										dialog.cancel();
									}
								});

				AlertDialog alertDialog = alBuilder.create();
				alertDialog.show();*/
				/*
				 * finish Alert Dialog Box
				 */
				valSetOne.add(check_eleven);
				// TwentyFiveDataUtils utils = new TwentyFiveDataUtils();
				utils.setOptiontext(check_eleven);
				utils.setOptionNo("11");
				twentyFiveDatalist.add(utils);
				map.put("check_eleven", check_eleven);
			} else {
				valSetOne.remove(check_btn_eleven);
				twentyFiveDatalist.remove(check_eleven);
				map.remove("check_eleven");
			}
			break;
		default:
			break;
		}

	}

	private void backMove() {
		savePosition = savePosition - 1;
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, CheckBoxTypeViewTwtyFourActivity.class);
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
		savedataUtils.setCheck7(check_seven);
		savedataUtils.setCheck8(check_eight);
		savedataUtils.setCheck9(check_nine);
		savedataUtils.setCheck10(check_ten);
		savedataUtils.setCheck11(check_eleven);
		
		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(main_question, map);
		
		if (activtiy_no != null) {

			postion = Integer.parseInt(activtiy_no);
			System.out.println(" === postion  :" + postion);
			Intent intent = new Intent(activity,SingleChoiceTwentySIxActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","25");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();

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
