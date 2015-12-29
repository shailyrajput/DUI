package com.scms.dui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;

public class NineteenViewActivity extends Activity implements OnClickListener {
	private Activity activity = NineteenViewActivity.this;

	private TextView question_nineteen_question_no, nine_question_txt,
			nineteen_first_option_text2, nineteen_second_option_text1,
			nineteen_second_option_text2, nineteen_third_option_text2,
			nineteen_forth_option_text2, nineteen_check_btn_text,
			nineteen_note_text, tap_txt, nineteen_first_option_text1,
			nineteen_third_option_text1, nineteen_forth_option_text1;

	private CheckBox nineteen_check_btn;
	private Button prev_btn, next_btn, done, cancel;
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion;
	private String questionType, getData;
	private String activtiy_no;
	private String question = "Question";
	private boolean oneChecked = false;

	private TimePicker timePicker;
	private NumberPicker numberPicker;
	private AlertDialog alertDialog;

	private String main_question="", answer="", subquestion="", subanswer="", desc1="", desc2="",
			desc3="", desc4="", desc5="", descQ1, descQ2, descQ3, descQ4, descQ5,
			checked="",back_activity;
	private String format = "";
	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private SingletonClass singletonClass;
	private int savePosition;
	
	private HashMap<Object, Object> map;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_nineteen_view);
		setCustomActionBar();

		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		System.out.println("=== postion:" + postion);
		
		back_activity = getIntent().getStringExtra("back_activity");
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
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);

		txt.setText("DUI Questionnaire");

		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void init() {

		question_nineteen_question_no = (TextView) findViewById(R.id.question_nineteen_question_no);
		nine_question_txt = (TextView) findViewById(R.id.nine_question_txt);
		nineteen_first_option_text2 = (TextView) findViewById(R.id.nineteen_first_option_text2);
		nineteen_second_option_text1 = (TextView) findViewById(R.id.nineteen_second_option_text1);
		nineteen_second_option_text2 = (TextView) findViewById(R.id.nineteen_second_option_text2);

		nineteen_third_option_text2 = (TextView) findViewById(R.id.nineteen_third_option_text2);
		nineteen_forth_option_text2 = (TextView) findViewById(R.id.nineteen_forth_option_text2);
		nineteen_check_btn_text = (TextView) findViewById(R.id.nineteen_check_btn_text);

		nineteen_check_btn_text = (TextView) findViewById(R.id.nineteen_check_btn_text);

		nineteen_note_text = (TextView) findViewById(R.id.nineteen_note_text);

		nineteen_first_option_text1 = (TextView) findViewById(R.id.nineteen_first_option_text1);

		nineteen_third_option_text1 = (TextView) findViewById(R.id.nineteen_third_option_text1);
		nineteen_forth_option_text1 = (TextView) findViewById(R.id.nineteen_forth_option_text1);
		tap_txt = (TextView) findViewById(R.id.tap_txt);

		nineteen_check_btn = (CheckBox) findViewById(R.id.nineteen_check_btn);
		next_btn = (Button) findViewById(R.id.next_btn);
		prev_btn = (Button) findViewById(R.id.prev_btn);

		setview();
	}

	private void setview() {

		optionlist = questionData.get(postion).getOptionArr();
		System.out.println(" === optionlist :" + optionlist.size());

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		main_question = questionData.get(postion).getQues();

		System.out.println(" === questionData :"
				+ (questionData.get(postion).getQuesNo()));
		question_nineteen_question_no.setText(question + " "
				+ (savePosition+1));
		nine_question_txt.setText(questionData.get(postion).getQues());

		System.out.println("===== activtiy_no :"
				+ optionlist.get(2).getActivity());
		activtiy_no = optionlist.get(2).getActivity();
		tap_txt.setText("TAP HERE TO SKIP QUESTION IF YOU DON'T KNOW");

		conditionCheck();

	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map = singletonClass.getMap().get(main_question);

		if (map != null) {
			back_activity = "" + map.get("back_activity");

			if (map.get("checked") != null) {
				nineteen_first_option_text1.setText("");
				nineteen_second_option_text1.setText("");
				
				nineteen_third_option_text1.setText("");
				nineteen_forth_option_text1.setText("");
				
				
				nineteen_first_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_second_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_third_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_forth_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				
				
				nineteen_check_btn.setChecked(true);
			} else {
				if (map.get("desc_Value") != null) {
					nineteen_first_option_text1.setText(""+ map.get("desc_Value"));
					nineteen_first_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);

				}
				if (map.get("desc_Value1") != null) {

					nineteen_second_option_text1.setText(""+ map.get("desc_Value1"));
					nineteen_second_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				}
				if (map.get("desc_Value2") != null) {

					nineteen_third_option_text1.setText(""+ map.get("desc_Value2"));
					nineteen_third_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				}
				if (map.get("desc_Value3") != null) {

					nineteen_forth_option_text1.setText(""+ map.get("desc_Value3"));
					nineteen_forth_option_text1.setBackgroundResource(R.drawable.textview_fill_shape);
				}
			}

			checked = "" + map.get("checked");
			activtiy_no = "" + map.get("activtiy_no");

			desc1 = "" + map.get("desc_Value");
			desc2 = "" + map.get("desc_Valu1");
			desc3 = "" + map.get("desc_Valu2");
			desc4 = "" + map.get("desc_Valu4");
		}

	}

	private void assignClicks() {
		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);

		nineteen_first_option_text1.setOnClickListener(this);
		nineteen_second_option_text1.setOnClickListener(this);
		nineteen_third_option_text1.setOnClickListener(this);
		nineteen_forth_option_text1.setOnClickListener(this);

		tap_txt.setOnClickListener(this);
		nineteen_check_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			moveActivity();
			finish();
			break;
		case R.id.tap_txt:
			moveActivity();
			break;
		case R.id.prev_btn:
			backMove();

			break;
		case R.id.nineteen_first_option_text1:
			openNumberPicker(R.id.nineteen_first_option_text1);
			break;

		case R.id.nineteen_second_option_text1:
			openTimePicker(R.id.nineteen_second_option_text1);
			break;

		case R.id.nineteen_third_option_text1:
			openNumberPicker(R.id.nineteen_third_option_text1);
			break;

		case R.id.nineteen_forth_option_text1:
			openTimePicker(R.id.nineteen_forth_option_text1);
			break;

		case R.id.nineteen_check_btn:
			oneChecked = ((CheckBox) v).isChecked();
			if (oneChecked) {
				nineteen_first_option_text1.setEnabled(false);
				nineteen_second_option_text1.setEnabled(false);
				nineteen_third_option_text1.setEnabled(false);
				nineteen_forth_option_text1.setEnabled(false);
				nineteen_first_option_text1
						.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_second_option_text1
						.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_third_option_text1
						.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_forth_option_text1
						.setBackgroundResource(R.drawable.textview_fill_shape);
				nineteen_first_option_text1.setText("");
				nineteen_first_option_text1.setHint("00");
				nineteen_first_option_text1.setHintTextColor(Color
						.parseColor("#4B4B4B"));
				nineteen_second_option_text1.setText("");
				nineteen_second_option_text1.setHint("00:00");
				nineteen_second_option_text1.setHintTextColor(Color
						.parseColor("#4B4B4B"));
				nineteen_third_option_text1.setText("");
				nineteen_third_option_text1.setHint("00");
				nineteen_third_option_text1.setHintTextColor(Color
						.parseColor("#4B4B4B"));
				nineteen_forth_option_text1.setText("");
				nineteen_forth_option_text1.setHint("00:00");
				nineteen_forth_option_text1.setHintTextColor(Color
						.parseColor("#4B4B4B"));
				checked = "true";
			} else {
				nineteen_first_option_text1.setEnabled(true);
				nineteen_second_option_text1.setEnabled(true);
				nineteen_third_option_text1.setEnabled(true);
				nineteen_forth_option_text1.setEnabled(true);
				nineteen_first_option_text1
						.setBackgroundResource(R.drawable.textview_shape);
				nineteen_second_option_text1
						.setBackgroundResource(R.drawable.textview_shape);
				nineteen_third_option_text1
						.setBackgroundResource(R.drawable.textview_shape);
				nineteen_forth_option_text1
						.setBackgroundResource(R.drawable.textview_shape);
				nineteen_first_option_text1.setHintTextColor(Color
						.parseColor("#696969"));
				nineteen_second_option_text1.setHintTextColor(Color
						.parseColor("#696969"));
				nineteen_third_option_text1.setHintTextColor(Color
						.parseColor("#696969"));
				nineteen_forth_option_text1.setHintTextColor(Color
						.parseColor("#696969"));
				//
				Log.e("cheked", "false");
				checked = "false";
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
		Intent intent = new Intent(activity, EighteenViewActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		
	}

	private void openTimePicker(final int id) {

	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

		LayoutInflater inflater = (LayoutInflater) activity
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

				//NumberFormat formatter = new DecimalFormat("00");
				
				int hour = timePicker.getCurrentHour();
				int min = timePicker.getCurrentMinute();
				
				
				switch (id) {
				case R.id.nineteen_second_option_text1:
					
				
					showTime(hour, min,nineteen_second_option_text1);
				
					desc2=	showTime(hour, min,nineteen_second_option_text1);
					break;
				case R.id.nineteen_forth_option_text1:
					
					showTime(hour, min,nineteen_forth_option_text1);
              
					desc4=	showTime(hour, min,nineteen_forth_option_text1);
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

	protected String showTime(int hour, int min, TextView text) {
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
		text.setText(new StringBuilder().append(hour).append(" : ").append(min));

		//desc1 = time;
		System.out.println("=== himani 9 Activity time value :" + desc1);
		return time;
		
		
	}

	private void showTime(int hour, int min) {
		
	}

	private void openNumberPicker(final int id) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.picker, null);

		timePicker = (TimePicker) layout.findViewById(R.id.timePicker);
		timePicker.setVisibility(View.GONE);
		numberPicker = (NumberPicker) layout.findViewById(R.id.numberPicker);
		numberPicker.setVisibility(View.VISIBLE);
		numberPicker.setMaxValue(99);
		numberPicker.setMinValue(0);
		done = (Button) layout.findViewById(R.id.done);
		cancel = (Button) layout.findViewById(R.id.cancel);
		timePicker.setIs24HourView(false);

		alertDialogBuilder.setView(layout);
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();

		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = "" + numberPicker.getValue();

				switch (id) {
				case R.id.nineteen_first_option_text1:
					nineteen_first_option_text1.setText(number);
					desc1 = nineteen_first_option_text1.getText().toString();
					break;
				case R.id.nineteen_third_option_text1:
					nineteen_third_option_text1.setText(number);
					desc3 = nineteen_third_option_text1.getText().toString();
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

	

	private void moveActivity() {

		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();

		savedataUtils.setQuestion(main_question);
		savedataUtils.setQuestionNo(questionNo);
		savedataUtils.setChecked(checked);
		savedataUtils.setDesc1(desc1);
		savedataUtils.setDesc2(desc2);
		savedataUtils.setDesc3(desc3);
		savedataUtils.setDesc4(desc4);
		saveDataList.add(savedataUtils);
	//	singletonClass.addList(savePosition, saveDataList);
		
		map.put("checked", checked);
		map.put("desc_Value", desc1);
		map.put("desc_Value1", desc2);
		map.put("desc_Value2", desc3);
		map.put("desc_Value3", desc4);
		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(main_question, map);

			
		if (activtiy_no.equalsIgnoreCase("20")) {
			postion = Integer.parseInt(activtiy_no);
			System.out.println(" === postion  :" + postion);
			Intent intent = new Intent(activity,SingleChoiceTwentyActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","19");
			intent.putExtra("saveposition", savePosition+1);
			startActivity(intent);
			overridePendingTransition(0, 0);
		}

	}
	@Override
	public void onBackPressed() {
	}

}
