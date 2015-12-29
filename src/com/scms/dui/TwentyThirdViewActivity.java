package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.EditText;
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

public class TwentyThirdViewActivity extends Activity implements
		OnClickListener {

	private Activity activity = TwentyThirdViewActivity.this;
	private LinearLayout layout1, layout2, layout3, layout4, layout5, layout6;
	private EditText edDesc1, edDesc2, edDesc3, edDesc4, edDesc5, edDesc6;
	private TextView txtOption1, txtOption2, txtOption3, txtOption4,
			txtOption5, txtOption6, twenty_three_question,
			twenty_three_question_no;
	
	private TextView twty_three_option1,twty_three_option2,
						twty_three_option3,twty_three_option4,twty_three_option5,twty_three_option6;
	
	private CheckBox checkbtn1, checkbtn2, checkbtn3, checkbtn4, checkbtn5,checkbtn6;
	private Button prev_btn, next_btn;
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion;
	private String activtiy_no;
	private  boolean checkvalue1=false, checkvalue2=false, checkvalue3=false,
			          checkvalue4=false, checkvalue5=false, checkvalue6=false;
	private String question = "Question";
	//private Map<String, List<String>> map;
	private SingletonClass singletonClass;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private int savePosition;
	private int questionNo;
	private String main_question,back_activity;
	
	private String  desc1, desc2,desc3, desc4, desc5, desc6, descQ2, descQ3, descQ4, descQ5;
	
	private String check_one = "", check_two = "", check_three = "",
			check_four = "", check_five = "", check_six = "", check_seven = "",
			check_eight = "", check_nine = "", check_ten = "",
			check_eleven = "";
	/*
	 * new changes
	 */
	
	private HashMap<Object, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_twenty_three_view);
		setCustomActionBar();
		optionlist = new ArrayList<OptionUtils>();
		questionData = SplashScreen.QuestionArrList;
		map =new HashMap<Object, Object>();
		System.out.println("=== map:" + map);
		singletonClass = SingletonClass.getInstance();
		postion = getIntent().getIntExtra("Position", 0);
		System.out.println("=== postion:" + postion);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		
		init();
		setview();
		assignClicks();
		showLayout();
		conditionCheck();
	}

	private void showLayout() {

		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();

		map1 = singletonClass.getMap().get(QuestionUtiltiy.Q21);
		
		map2 = singletonClass.getMap().get(QuestionUtiltiy.Q22);

		 txtOption1 = (TextView) findViewById(R.id.twty_three_option1);
		 txtOption2 = (TextView) findViewById(R.id.twty_three_option2);
		 txtOption3 = (TextView) findViewById(R.id.twty_three_option3);
		 txtOption4 = (TextView) findViewById(R.id.twty_three_option4);
		 txtOption5 = (TextView) findViewById(R.id.twty_three_option5);
		 txtOption6 = (TextView) findViewById(R.id.twty_three_option6);

		 edDesc1 = (EditText) findViewById(R.id.twty_three_desc_option1);
		 edDesc2= (EditText) findViewById(R.id.twty_three_desc_option2);
		 edDesc3= (EditText) findViewById(R.id.twty_three_desc_option3);
		 edDesc4= (EditText) findViewById(R.id.twty_three_desc_option4);
		 edDesc5= (EditText) findViewById(R.id.twty_three_desc_option5);
		 edDesc6= (EditText) findViewById(R.id.twty_three_desc_option6);
		 
		 
		

	}

	private void setview() {
		System.out.println("TwentyThirdViewActivity.setview()");
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		main_question = questionData.get(postion).getQues();
		twenty_three_question_no.setText(question + " " + (savePosition + 1));
		twenty_three_question.setText(questionData.get(postion).getQues());
		
		System.out.println("=====map size to set visibility of 212223"+singletonClass.map.size());
	
		if((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_1) != null || singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_1) != null) 
				||((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_1) != null && singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_1) != null)))
		{
			layout1.setVisibility(View.VISIBLE);
			txtOption1.setText(QuestionUtiltiy.Q21_1 );
		}
		if((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_2) != null || singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_2) != null) 
				||((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_2) != null && singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_2) != null)))
		{
			layout2.setVisibility(View.VISIBLE);
			txtOption2.setText(QuestionUtiltiy.Q21_2 );
		}
		if((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_3) != null || singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_3) != null)
				||((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_3) != null && singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_3) != null)))
		{
			layout3.setVisibility(View.VISIBLE);
			txtOption3.setText( QuestionUtiltiy.Q21_3) ;
		}
		if((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_4) != null || singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_4) != null)
				||((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_4) != null && singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_4) != null)))
		{
			layout4.setVisibility(View.VISIBLE);
			txtOption4.setText( QuestionUtiltiy.Q21_4) ;
		}
		if((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_5) != null || singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_5) != null) 
				||((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_5) != null && singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_5) != null)))
		{
			layout5.setVisibility(View.VISIBLE);
			txtOption5.setText( QuestionUtiltiy.Q21_5) ;
		}
		if((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_6) != null || singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_6) != null)
				||((singletonClass.getMap().get(QuestionUtiltiy.Q21).get(QuestionUtiltiy.Q21_6) != null && singletonClass.getMap().get(QuestionUtiltiy.Q22).get(QuestionUtiltiy.Q22_6) != null)))
		{	layout6.setVisibility(View.VISIBLE);
			txtOption6.setText( QuestionUtiltiy.Q21_6) ;
		}
		
//		conditionCheck();


	}


	private void init() {
		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		optionlist = questionData.get(postion).getOptionArr();
        System.out.println("TwentyThirdViewActivity.init()");
        
          
		activtiy_no = "24";
		next_btn = (Button) findViewById(R.id.next_btn);
		prev_btn = (Button) findViewById(R.id.prev_btn);
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout3 = (LinearLayout) findViewById(R.id.layout3);
		layout4 = (LinearLayout) findViewById(R.id.layout4);
		layout5 = (LinearLayout) findViewById(R.id.layout5);
		layout6 = (LinearLayout) findViewById(R.id.layout6);
		
		
		txtOption1 = (TextView) findViewById(R.id.twty_three_option1);
		txtOption2 = (TextView) findViewById(R.id.twty_three_option2);
		txtOption3 = (TextView) findViewById(R.id.twty_three_option3);
		txtOption4 = (TextView) findViewById(R.id.twty_three_option4);
		txtOption5 = (TextView) findViewById(R.id.twty_three_option5);
		txtOption6 = (TextView) findViewById(R.id.twty_three_option6);
		
		
		edDesc1 = (EditText) findViewById(R.id.twty_three_desc_option1);
		edDesc2 = (EditText) findViewById(R.id.twty_three_desc_option2);
		edDesc3 = (EditText) findViewById(R.id.twty_three_desc_option3);
		edDesc4 = (EditText) findViewById(R.id.twty_three_desc_option4);
		edDesc5 = (EditText) findViewById(R.id.twty_three_desc_option5);
		edDesc6 = (EditText) findViewById(R.id.twty_three_desc_option6);
		
		
		checkbtn1 = (CheckBox) findViewById(R.id.twty_three_check_btn_optn1);
		checkbtn2 = (CheckBox) findViewById(R.id.twty_three_check_btn_optn2);
		checkbtn3 = (CheckBox) findViewById(R.id.twty_three_check_btn_optn3);
		checkbtn4 = (CheckBox) findViewById(R.id.twty_three_check_btn_optn4);
		checkbtn5 = (CheckBox) findViewById(R.id.twty_three_check_btn_optn5);
		checkbtn6 = (CheckBox) findViewById(R.id.twty_three_check_btn_optn6);
		
		twenty_three_question = (TextView) findViewById(R.id.twenty_three_question);
		twenty_three_question_no = (TextView) findViewById(R.id.twenty_three_question_no);
	
	}

	private void conditionCheck() {
		System.out.println("TwentyThirdViewActivity.conditionCheck()");
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		 map = singletonClass.getMap().get(QuestionUtiltiy.Q23);
		    
		 
		if (map != null) {
			 back_activity = "" + singletonClass.getMap().get(QuestionUtiltiy.Q23).get("back_activity");
	        
			if (map.get("check_one") != null) {

				edDesc1.setText("" + map.get("descValue"));
				
			} else {
				checkbtn1.setChecked(true);
				edDesc1.setBackgroundResource(R.drawable.textview_fill_shape);
			}
			if (map.get("check_two") != null) {
				edDesc2.setText("" + map.get("descValue1"));

			} else {
				checkbtn2.setChecked(true);
				edDesc2.setBackgroundResource(R.drawable.textview_fill_shape);
			}
			if (map.get("check_three") != null) {
				edDesc3.setText("" + map.get("descValue2"));

			} else {
				checkbtn3.setChecked(true);
				edDesc3.setBackgroundResource(R.drawable.textview_fill_shape);
			}
			if (map.get("check_four") != null) {
				edDesc4.setText("" + map.get("descValue3"));

			} else {
				checkbtn4.setChecked(true);
				edDesc4.setBackgroundResource(R.drawable.textview_fill_shape);
			}
			if (map.get("check_five") != null) {

				edDesc5.setText("" + map.get("descValue4"));

			} else {
				checkbtn5.setChecked(true);
				edDesc5.setBackgroundResource(R.drawable.textview_fill_shape);
			}
			if (map.get("check_six") != null) {
				edDesc6.setText("" + map.get("descValue5"));

			} else {
				checkbtn6.setChecked(true);
				edDesc6.setBackgroundResource(R.drawable.textview_fill_shape);
			}

			back_activity = "" + singletonClass.getMap().get(QuestionUtiltiy.Q23).get("back_activity");
			check_one = "" + map.get("check_one");
			check_two = "" + map.get("check_two");
			check_three = "" + map.get("check_three");
			check_four = "" + map.get("check_four");
			check_five = "" + map.get("check_five");
			check_six = "" + map.get("check_six");
			
			desc1= "" + map.get("descValue");
			desc2="" + map.get("descValue1");
			desc3="" + map.get("descValue2");
			desc4="" + map.get("descValue3");
			desc5="" + map.get("descValue4");
			desc6="" + map.get("descValue5");
			
			activtiy_no = "" + map.get("activtiy_no");

		}

	}

	
	private void assignClicks() {
		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);
		
		checkbtn1.setOnClickListener(this);
		checkbtn2.setOnClickListener(this);
		checkbtn3.setOnClickListener(this);
		checkbtn4 .setOnClickListener(this);
		checkbtn5 .setOnClickListener(this);
		checkbtn6 .setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
	
		switch (v.getId()) {
		case R.id.twty_three_check_btn_optn1:
			if(checkbtn1.isChecked())
			{
				edDesc1.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc1.setText("");
				checkvalue1= true;
				edDesc1.setEnabled(false);
			}
			else
			{
				edDesc1.setBackgroundResource(R.drawable.textview_shape);
				edDesc1.setEnabled(true);
				check_one=edDesc1.getText().toString();
		
			}

			break;
		case R.id.twty_three_check_btn_optn2:
			if(checkbtn2.isChecked())
			{
				checkvalue2= true;
				edDesc2.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc2.setText("");
				edDesc2.setEnabled(false);
			}
			else
			{
				edDesc2.setBackgroundResource(R.drawable.textview_shape);
				edDesc2.setEnabled(true);
				check_two=	edDesc2.getText().toString();
				
			}
			break;
		case R.id.twty_three_check_btn_optn3:
			if(checkbtn3.isChecked())
			{
				checkvalue3= true;
				edDesc3.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc3.setText("");
				edDesc3.setEnabled(false);
			}
			else
			{
				edDesc3.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc3.setEnabled(true);
				check_three=edDesc3.getText().toString();
			
			}
			break;
		case R.id.twty_three_check_btn_optn4:
			if(checkbtn4.isChecked())
			{
				checkvalue4= true;
				edDesc4.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc4.setText("");
				edDesc4.setEnabled(false);
			}
			else
			{
				edDesc4.setBackgroundResource(R.drawable.textview_shape);
				edDesc4.setEnabled(true);
				check_four=edDesc4.getText().toString();
			
			}
			break;
		case R.id.twty_three_check_btn_optn5:
			if(checkbtn5.isChecked())
			{
				checkvalue5= true;
				edDesc5.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc5.setText("");
				edDesc5.setEnabled(false);	
			}
			else
			{
				edDesc5.setBackgroundResource(R.drawable.textview_shape);
				edDesc5.setEnabled(true);
				check_five=edDesc5.getText().toString();
				
			}
			break;
		case R.id.twty_three_check_btn_optn6:
			if(checkbtn6.isChecked())
			{
				checkvalue6= true;
				edDesc6.setBackgroundResource(R.drawable.textview_fill_shape);
				edDesc6.setText("");
				edDesc6.setEnabled(false);
			}
			else
			{
				edDesc6.setBackgroundResource(R.drawable.textview_shape);
				edDesc6.setEnabled(true);
				check_six=edDesc6.getText().toString();
			
			}
			break;

		case R.id.next_btn:
			postion = Integer.parseInt(activtiy_no);
			moveActivity();
			break;
		case R.id.prev_btn:
			backMove();
			break;
		default:
			break;
		}
	}

	private void backMove() {
	
		savePosition = savePosition - 1;
		back_activity =  ""+map.get("back_activity");
		back_activity="22";
		System.out.println("TwentyThirdViewActivity.backMove()"+back_activity);
			postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, CheckBoxTypeViewTwtyTwoActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		
	}

	private void moveActivity() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();
		savedataUtils.setQuestion(main_question);
		savedataUtils.setQuestionNo(questionNo);
		saveDataList.add(savedataUtils);
		desc1= edDesc1.getText().toString();
		desc2=edDesc2.getText().toString();
		desc3=edDesc3.getText().toString();
		desc4=edDesc4.getText().toString();
		desc5=edDesc5.getText().toString();
		desc6=edDesc6.getText().toString();
		
		map.put("descValue",  desc1);
		map.put("descValue1", desc2);
		map.put("descValue2", desc3);
		map.put("descValue3", desc4);
		map.put("descValue4", desc5);
		map.put("descValue5", desc6);
		
		
		map.put("activtiy_no", activtiy_no);
		
		map.put("check_one", ""+checkvalue1);
		map.put("check_two", ""+checkvalue2);
		map.put("check_three", ""+checkvalue3);
		map.put("check_four", ""+checkvalue4);
		map.put("check_five", ""+checkvalue5);
		map.put("check_six", ""+checkvalue6);
		map.put("back_activity", back_activity);
		
        singletonClass.addMap(QuestionUtiltiy.Q23, map);
		postion = Integer.parseInt(activtiy_no);
		System.out.println("=== postion  :" + postion);
		Intent intent = new Intent(activity,CheckBoxTypeViewTwtyFourActivity.class);
		intent.putExtra("Position", postion - 1);
		intent.putExtra("back_activity","23");
		intent.putExtra("saveposition", savePosition + 1);
		startActivity(intent);
		overridePendingTransition(0, 0);
		finish();
	}


	@Override
	public void onBackPressed() {

	}

}
