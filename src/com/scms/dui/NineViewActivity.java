package com.scms.dui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;
import com.scms.utility.QuestionUtiltiy;

public class NineViewActivity extends Activity implements OnClickListener {
	Context context = NineViewActivity.this;
	private LinearLayout nine_first_option_layout, nine_second_option_layout,
			nine_third_option_layout, nine_forth_option_layout,
			nine_fivth_option_layout;

	private TextView question_nine_question_no, nine_question_txt,
			nine_first_option_text1, nine_second_option_text1,
			nine_third_option_text1, nine_third_option_text2,
			nine_forth_option_text1, nine_fivth_option_text1,
			nine_first_option_text2;

	private TextView nine_fivth_option_text2, nine_second_option_text2,
			nine_forth_option_text2;
	private Button prev_btn, next_btn, done, cancel;
	private String question = "Question";
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion;
	private String questionType, getData;
	private String activtiy_no, back_activity;
	private TimePicker timePicker;
	private NumberPicker numberPicker;
	private AlertDialog alertDialog;
	private CheckBox checkBox;
	boolean oneChecked = false;
	private SingletonClass singletonClass;
	private int savePosition;

	private HashMap<Object, Object> map;
	View v;
	private String main_question, answer, subquestion, subanswer, desc1, desc2,
			desc3, desc4, desc5, descQ1, descQ2, descQ3, descQ4, descQ5;
	private String checked = "false";

	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private String format = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_question_nine_view);
		setCustomActionBar();
		map = new HashMap<Object, Object>();
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		savePosition = getIntent().getIntExtra("saveposition", 0);
		System.out.println("===savePosition" + savePosition);
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
				Utils.alertDialogTwoButton(context, "Attention!",
						"Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void init() {

		nine_first_option_layout = (LinearLayout) findViewById(R.id.nine_first_option_layout);
		nine_second_option_layout = (LinearLayout) findViewById(R.id.nine_second_option_layout);
		nine_third_option_layout = (LinearLayout) findViewById(R.id.nine_third_option_layout);
		nine_forth_option_layout = (LinearLayout) findViewById(R.id.nine_forth_option_layout);
		nine_fivth_option_layout = (LinearLayout) findViewById(R.id.nine_fivth_option_layout);

		nine_fivth_option_text2 = (TextView) findViewById(R.id.nine_fivth_option_text2);
		nine_second_option_text2 = (TextView) findViewById(R.id.nine_second_option_text2);
		nine_forth_option_text2 = (TextView) findViewById(R.id.nine_forth_option_text2);

		nine_first_option_text2 = (TextView) findViewById(R.id.nine_first_option_text2);

		question_nine_question_no = (TextView) findViewById(R.id.question_nine_question_no);
		nine_question_txt = (TextView) findViewById(R.id.nine_question_txt);
		nine_first_option_text1 = (TextView) findViewById(R.id.nine_first_option_text1);

		nine_second_option_text1 = (TextView) findViewById(R.id.nine_second_option_text1);
		nine_third_option_text1 = (TextView) findViewById(R.id.nine_third_option_text1);
		nine_third_option_text2 = (TextView) findViewById(R.id.nine_third_option_text2);
		nine_forth_option_text1 = (TextView) findViewById(R.id.nine_forth_option_text1);
		nine_fivth_option_text1 = (TextView) findViewById(R.id.nine_fivth_option_text1);

		prev_btn = (Button) findViewById(R.id.prev_btn);
		next_btn = (Button) findViewById(R.id.next_btn);
		done = (Button) findViewById(R.id.done);
		cancel = (Button) findViewById(R.id.cancel);

		checkBox = (CheckBox) findViewById(R.id.checkBox);
		setview(postion);
	}

	private void assignClicks() {

		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);

		nine_first_option_text2.setOnClickListener(this);
		nine_second_option_text2.setOnClickListener(this);
		nine_second_option_text2.setOnClickListener(this);
		nine_third_option_text2.setOnClickListener(this);
		nine_forth_option_text2.setOnClickListener(this);
		nine_fivth_option_text2.setOnClickListener(this);
		checkBox.setOnClickListener(this);

	}

	private void setview(int postion) {

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		main_question = questionData.get(postion).getQues();

		System.out.println(" === questionData :"
				+ (questionData.get(postion).getQuesNo()));
		question_nine_question_no.setText(question + " " + (savePosition + 1));
		nine_question_txt.setText((questionData.get(postion).getQues()));
		optionlist = questionData.get(postion).getOptionArr();
		System.out.println(" === getOptionText;" + optionlist.size());
		nine_first_option_text1.setText(optionlist.get(0).getOptionText());
		nine_second_option_text1.setText(optionlist.get(1).getOptionText());

		conditionCheck();

	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map = singletonClass.getMap().get(main_question);
		System.out.println("====map:::" + map);

		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1 = singletonClass.getMap().get(QuestionUtiltiy.Q8);
		System.out.println("=== map8" + map1);

		if (map != null) {

			back_activity = "" + map.get("back_activity");
			activtiy_no = "" + map.get("activtiy_no");
			nine_first_option_layout.setVisibility(View.VISIBLE);
			nine_second_option_layout.setVisibility(View.VISIBLE);
			nine_third_option_layout.setVisibility(View.VISIBLE);
			nine_forth_option_layout.setVisibility(View.VISIBLE);
			nine_fivth_option_layout.setVisibility(View.VISIBLE);

			if (map.get("desc_Value") != null) {
				nine_first_option_text2.setText("" + map.get("desc_Value"));
			}
			if (map.get("desc_Value1") != null) {
				nine_second_option_text2.setText("" + map.get("desc_Value1"));
			}
			if (map.get("desc_Value2") != null) {
				nine_third_option_text2.setText("" + map.get("desc_Value2"));
			}
			if (map.get("desc_Value3") != null) {
				nine_forth_option_text2.setText("" + map.get("desc_Value3"));
				nine_forth_option_text2.setSelected(true);
			} else if (map.get("checked") == "true") {
				checkBox.setChecked(true);
				nine_forth_option_text2
						.setBackgroundResource(R.drawable.textview_fill_shape);
				nine_forth_option_text2.setSelected(false);
				nine_forth_option_text2.setText("");
			}
			if (map.get("desc_Value4") != null) {
				nine_fivth_option_text2.setText("" + map.get("desc_Value4"));

			}

			desc1 = "" + map.get("desc_Value");
			desc2 = "" + map.get("desc_Value1");
			desc3 = "" + map.get("desc_Value2");
			desc4 = "" + map.get("desc_Value3");
			desc5 = "" + map.get("desc_Value4");
			checked = "" + map.get(checked);

		}

		// remove seventh

		if (back_activity.equalsIgnoreCase("6")) {

			System.out.println("NineViewActivity.conditionCheck()");
			System.out.println("==== back_activity for check:::"
					+ back_activity);

			if (singletonClass.getMap().get(QuestionUtiltiy.Q7) != null)

			{
				singletonClass.getMap().remove(QuestionUtiltiy.Q7);
			}
		}
		// remove eight
		if (back_activity.equalsIgnoreCase("7")) {

			System.out.println("NineViewActivity.conditionCheck()");
			System.out.println("==== back_activity for check:::"
					+ back_activity);

			if (singletonClass.getMap().get(QuestionUtiltiy.Q8) != null) {
				singletonClass.getMap().remove(QuestionUtiltiy.Q8);
			}
		}

		System.out.println("==== back_activity for check:::" + back_activity);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:

			System.out.println("Next Click>>>>>>>>>>>>>>>>>>>>>>>");
			checkedAlertConditions();

			break;
		case R.id.prev_btn:
			if (back_activity.equalsIgnoreCase("6")) {

				savePosition = savePosition - 1;
				postion = Integer.parseInt(back_activity);
				System.out.println("===back_activity" + back_activity);
				Intent intent = new Intent(context,
						DoubleSingleChoiceSixActivity.class);
				System.out.println(" ==== savePosition" + savePosition);
				intent.putExtra("saveposition", savePosition);
				intent.putExtra("Position", postion - 1);
				startActivity(intent);
			}
			if (back_activity.equalsIgnoreCase("8")) {
				savePosition = savePosition - 1;
				postion = Integer.parseInt(back_activity);
				System.out.println("===back_activity" + back_activity);
				Intent intent = new Intent(context,
						SingleChoiceEightActivity.class);
				System.out.println(" ==== savePosition" + savePosition);
				intent.putExtra("saveposition", savePosition);
				intent.putExtra("Position", postion - 1);
				startActivity(intent);
			}
			if (back_activity.equalsIgnoreCase("7")) {
				savePosition = savePosition - 1;
				postion = Integer.parseInt(back_activity);
				System.out.println("===postion" + postion);
				Intent intent = new Intent(context,
						DoubleSingleChoiceSevenActivity.class);
				System.out.println(" ==== savePosition" + savePosition);
				intent.putExtra("saveposition", savePosition);
				intent.putExtra("Position", postion - 1);
				startActivity(intent);
			}
			overridePendingTransition(0, 0);
			finish();

			break;

		case R.id.nine_first_option_text2:
			openTimePicker();
			break;
		case R.id.nine_second_option_text2:

			openNumberPicker(R.id.nine_second_option_text2);
			break;
		case R.id.nine_third_option_text2:
			openNumberPicker(R.id.nine_third_option_text2);

			break;
		case R.id.nine_forth_option_text2:
			openNumberPicker(R.id.nine_forth_option_text2);

			break;
		case R.id.nine_fivth_option_text2:

			openNumberPicker(R.id.nine_fivth_option_text2);

			break;
		case R.id.checkBox:

			oneChecked = checkBox.isChecked();

			if (oneChecked) {
				checked = "true";
				nine_fivth_option_layout.setVisibility(View.VISIBLE);
				nine_forth_option_text2.setEnabled(false);
				nine_forth_option_text2.setText("");
				nine_forth_option_text2.setHint("00");
				nine_forth_option_text2
						.setBackgroundResource(R.drawable.textview_fill_shape);

				nine_forth_option_text2.setHintTextColor(Color
						.parseColor("#4B4B4B"));

			} else {
				nine_fivth_option_layout.setVisibility(View.GONE);
				nine_forth_option_text2.setEnabled(true);
				nine_forth_option_text2
						.setBackgroundResource(R.drawable.textview_shape);
				nine_forth_option_text2.setHint("00");
				nine_forth_option_text2.setHintTextColor(Color
						.parseColor("#696969"));
				checked = "false";

			}

			break;
		default:
			break;
		}

	}

	private void checkedAlertConditions() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setQuestion(main_question);
		savedataUtils.setQuestionNo(questionNo);
		savedataUtils.setDesc1(desc1);
		savedataUtils.setDesc2(desc2);
		savedataUtils.setDesc3(desc3);
		savedataUtils.setDesc4(desc4);
		savedataUtils.setDesc5(desc5);
		savedataUtils.setChecked(checked);

		savedataUtils.setDescQ1(optionlist.get(0).getOptionText());
		savedataUtils.setDescQ2(optionlist.get(1).getOptionText());
		savedataUtils.setDescQ3(optionlist.get(2).getOptionText());
		savedataUtils.setDescQ4(optionlist.get(3).getOptionText());
		savedataUtils.setDescQ4(optionlist.get(4).getOptionText());
		saveDataList.add(savedataUtils);
		// singletonClass.addList(savePosition, saveDataList);

		map.put("back_activity", back_activity);
		map.put("desc_Value", desc1);
		map.put("desc_Value1", desc2);
		map.put("desc_Value2", desc3);
		map.put("desc_Value3", desc4);
		map.put("desc_Value4", desc5);
		map.put("checked", checked);
		map.put("activtiy_no", activtiy_no);

		System.out.println("### Question 9 Descrption :desc1 :" + desc1
				+ "===desc2 : " + desc2 + "==desc3 : " + desc3 + "==desc4 : "
				+ desc4 + "==desc5 : " + desc5 + "=== checked : " + checked);

		singletonClass.addMap(main_question, map);

		// text condition
		if (nine_first_option_text2.getText().length() != 0
				&& nine_third_option_text2.getText().length() != 0
				&& nine_second_option_text2.getText().length() != 0
				&& nine_fivth_option_text2.getText().length() != 0) {
			if (nine_forth_option_text2.getText().length() != 0
					&& !checkBox.isChecked()) {
				activtiy_no = "10";
				getData = nine_second_option_text2.getText().toString();
			} else if (checkBox.isChecked()) {
				getData = "true";
				activtiy_no = "12";
			}

			nextActivity();
		} else {
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");
		}

	}

	private void nextActivity() {
		if (activtiy_no != null) {
			if (activtiy_no.equalsIgnoreCase("10")) {
				System.out.println(" === savePosition  :" + savePosition);
				postion = Integer.parseInt(activtiy_no);
				Intent intent = new Intent(context,
						SingleChoiceTenActivity.class);
				intent.putExtra("Position", postion - 1);
				intent.putExtra("back_activity", "9");
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
			if (activtiy_no.equalsIgnoreCase("12")) {
				System.out.println(" === postion  :" + postion);
				postion = Integer.parseInt(activtiy_no);
				Intent intent = new Intent(context, TwelveViewActivity.class);
				intent.putExtra("back_activity", "9");
				intent.putExtra("Position", postion - 1);
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		} else {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(context, "Attention!",
					"Please answer All questions.");
		}

	}

	private void openTimePicker() {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.picker, null);

		timePicker = (TimePicker) layout.findViewById(R.id.timePicker);
		numberPicker = (NumberPicker) layout.findViewById(R.id.numberPicker);
		done = (Button) layout.findViewById(R.id.done);
		cancel = (Button) layout.findViewById(R.id.cancel);
		timePicker.setIs24HourView(false);

		alertDialogBuilder.setView(layout);
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();

		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onDone();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onCancel();
			}
		});
	}

	private void openNumberPicker(final int id) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.picker, null);

		timePicker = (TimePicker) layout.findViewById(R.id.timePicker);
		timePicker.setVisibility(View.GONE);
		numberPicker = (NumberPicker) layout.findViewById(R.id.numberPicker);
		numberPicker
				.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
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
				String number = "" + numberPicker.getValue();

				switch (id) {
				case R.id.nine_second_option_text2:
					nine_second_option_text2.setText(number);
					desc2 = nine_second_option_text2.getText().toString();
					if (nine_second_option_text2.getText().length() != 0) {
						nine_third_option_layout.setVisibility(View.VISIBLE);
					}
					break;
				case R.id.nine_third_option_text2:

					nine_third_option_text2.setText(number);
					desc3 = nine_third_option_text2.getText().toString();
					System.out.println("===desc3::" + desc3);

					if (nine_third_option_text2.getText().length() != 0) {
						nine_forth_option_layout.setVisibility(View.VISIBLE);
					}
					break;
				case R.id.nine_forth_option_text2:
					nine_forth_option_text2.setText(number);
					desc4 = nine_forth_option_text2.getText().toString();

					System.out.println("===desc3::" + desc4);
					if (nine_forth_option_text2.getText().length() != 0) {
						nine_fivth_option_layout.setVisibility(View.VISIBLE);
					}
					break;
				case R.id.nine_fivth_option_text2:
					nine_fivth_option_text2.setText(number);
					desc5 = nine_fivth_option_text2.getText().toString();

					System.out.println("===desc3::" + desc5);

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

	// private void onDone() {
	// NumberFormat formatter = new DecimalFormat("00");
	// nine_first_option_text2.setText(timePicker.getCurrentHour() + ":"+
	// formatter.format(timePicker.getCurrentMinute()));
	// desc1 = nine_first_option_text2.getText().toString();
	// if (nine_first_option_text2.getText().length() != 0) {
	// nine_second_option_layout.setVisibility(View.VISIBLE);
	// alertDialog.dismiss();
	// }
	//
	// }
	private void onDone() {

		int hour = timePicker.getCurrentHour();
		int min = timePicker.getCurrentMinute();

		showTime(hour, min);

	}

	private void showTime(int hour, int min) {
		if (hour == 0) {
			hour += 12;
			format = "AM";
		} else if (hour == 12) {
			format = "PM";
		} else if (hour > 12) {
			hour -= 12;
			format = "PM";
		} else {
			format = "AM";
		}
		String time = hour + ":" + min + " " + format;
		nine_first_option_text2.setText(new StringBuilder().append(hour)
				.append(" : ").append(min));

		desc1 = time;
		System.out.println("=== himani 9 Activity time value :" + desc1);
		if (nine_first_option_text2.getText().length() != 0) {
			nine_second_option_layout.setVisibility(View.VISIBLE);
			alertDialog.dismiss();
		}
	}

	private void onCancel() {

		alertDialog.dismiss();
	}

	@Override
	public void onBackPressed() {
	}
}
