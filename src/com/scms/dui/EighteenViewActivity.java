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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.utility.QuestionUtiltiy;

public class EighteenViewActivity extends Activity implements OnClickListener {
	Activity activity = EighteenViewActivity.this;
	private TextView eighteen_question_no, eighteen_question_txt,
			eighteen_question_txt1, eighteen_check_btn1_text;
	private CheckBox eighteen_check_btn;
	private EditText eighteen_desc_text;
	private Button prev_btn, next_btn;

	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private int postion;
	private String question = "Question";
	private String questionType;
	private String activtiy_no;
	private boolean oneChecked = false;

	private String main_question, answer, subquestion, subanswer, desc1, desc2,
			desc3, desc4, desc5, descQ1, descQ2, descQ3, descQ4, descQ5,back_activity,
			checked="true";

	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private SingletonClass singletonClass;
	private int savePosition;
    private  HashMap<Object, Object> map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_eighteen_view);
		setCustomActionBar();
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		System.out.println("  ===== postion :" + postion);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		System.out.println("=== himani save position in 18 Activity :"
				+ savePosition);
		
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
		optionlist = questionData.get(postion).getOptionArr();

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		main_question = questionData.get(postion).getQues();

		eighteen_question_no = (TextView) findViewById(R.id.eighteen_question_no);
		eighteen_question_txt = (TextView) findViewById(R.id.eighteen_question_txt);
		// eighteen_check_btn1_text = (TextView)
		// findViewById(R.id.eighteen_check_btn1_text);
		eighteen_question_txt1 = (TextView) findViewById(R.id.eighteen_question_txt1);

		eighteen_desc_text = (EditText) findViewById(R.id.eighteen_desc_text);
		eighteen_desc_text.setEnabled(false);

		desc1 = eighteen_desc_text.getText().toString();

		eighteen_question_no.setText(question + " " + (savePosition + 1));
		eighteen_question_txt.setText(questionData.get(postion).getQues());

		eighteen_check_btn = (CheckBox) findViewById(R.id.eighteen_check_btn);

		prev_btn = (Button) findViewById(R.id.prev_btn);
		next_btn = (Button) findViewById(R.id.next_btn);

		activtiy_no = passRadioActivity(optionlist.get(0).getActivity());
		System.out.println(" === activtiy_no :" + activtiy_no);
		
		conditionCheck();

	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map = singletonClass.getMap().get(QuestionUtiltiy.Q18);
		if(map!=null)
		{
			back_activity=""+map.get("back_activity");
			
			if(map.get("checked").toString().equalsIgnoreCase("true") )
			{
				
				eighteen_desc_text.setEnabled(false);
				eighteen_desc_text.setBackgroundResource(R.drawable.textview_fill_shape);
				eighteen_check_btn.setChecked(true);
			}
			else {
				
				eighteen_desc_text.setEnabled(true);
				eighteen_desc_text.setBackgroundResource(R.drawable.textview_shape);
				eighteen_desc_text.setText(""+map.get("desc_Value") );
				
			}
			
			checked = ""+map.get("checked");
			activtiy_no=""+	map.get("activtiy_no");
		}
		
	}

	private String passRadioActivity(String activtiy_no) {

		return activtiy_no;
	}

	private void assignClicks() {

		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);

		eighteen_check_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				oneChecked = ((CheckBox) v).isChecked();
				if (oneChecked) {
					eighteen_desc_text.setEnabled(false);
					eighteen_desc_text.setBackgroundResource(R.drawable.textview_fill_shape);
					eighteen_desc_text.setText("");
					checked = "true";
				} else {

					eighteen_desc_text.setEnabled(true);
					eighteen_desc_text.setBackgroundResource(R.drawable.textview_shape);
					checked = "false";
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			moveActivity();
			finish();
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
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, DoubleDiscriptionViewSeventeenActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);
		
	}

	private void moveActivity() {

		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();

		savedataUtils.setQuestion(main_question);
		savedataUtils.setQuestionNo(questionNo);

		savedataUtils.setChecked(checked);
		savedataUtils.setDesc1(desc1);

	//	saveDataList.add(savedataUtils);
		
		map.put("checked", checked);
		map.put("desc_Value", desc1);
		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		singletonClass.addMap(main_question, map);
	

		if (activtiy_no.equalsIgnoreCase("19")) {
			postion = Integer.parseInt(activtiy_no);
			System.out.println(" === postion  :" + postion);
			Intent intent = new Intent(activity, NineteenViewActivity.class);
			intent.putExtra("Position", postion - 1);
			intent.putExtra("back_activity","18");
			intent.putExtra("saveposition", savePosition + 1);
			startActivity(intent);
			overridePendingTransition(0, 0);
		}

	}

	@Override
	public void onBackPressed() {
	}

}
