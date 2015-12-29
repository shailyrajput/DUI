package com.scms.dui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

	private Context mContext;
	Utils utils;
	public static ArrayList<QuestionUtils> QuestionArrList;
	public int postion;

	ArrayList<OptionUtils> optionArrayList;
	ArrayList<SubOptionUtils> subQuesArrList;
	ArrayList<SubOptionUtils> secondQuesArrList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		mContext = SplashScreen.this;
		utils = new Utils(mContext);

		try {
			getActionBar().hide();

		} catch (Exception e) {
			e.printStackTrace();
		}
		splashLoad();
	}

	private void splashLoad() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// getQuestionData();
				getQuestionData1();
				Intent spIntent = new Intent(mContext, Dashboard.class);
				postion = 0;
				spIntent.putExtra("initial_Position", postion);
				startActivity(spIntent);
				finish();
			}
		}, 3000);

	}

	public void getQuestionData() {
		try {
			QuestionArrList = new ArrayList<QuestionUtils>();
			JSONObject mainObj = new JSONObject(utils.loadJSONFromAsset());
			System.out.println("=== json Data :" + mainObj);
			JSONArray mainArr = mainObj.getJSONArray("questionData");
			for (int i = 0; i < mainArr.length(); i++) {
				QuestionUtils questionUtils = new QuestionUtils();
				JSONObject jsonObj = mainArr.getJSONObject(i);
				questionUtils.setQuesNo(jsonObj.getString("quesNo"));
				System.out.println("=== question Number :"
						+ jsonObj.getString("quesNo"));
				questionUtils.setQuesType(jsonObj.getString("quesType"));
				System.out.println("=== question Type :"
						+ jsonObj.getString("quesType"));
				questionUtils.setQues(jsonObj.getString("ques"));
				System.out
						.println("=== question :" + jsonObj.getString("ques"));
				optionArrayList = new ArrayList<OptionUtils>();
				JSONArray optJsonArr = jsonObj.getJSONArray("optnArry");
				System.out.println("=== option  Arr size :"
						+ optJsonArr.length());

				for (int j = 0; j < optJsonArr.length(); j++) {

					OptionUtils optionUtils = new OptionUtils();
					JSONObject optionObj = optJsonArr.getJSONObject(j);
					if (optionObj.has("text")) {
						optionUtils.setOptionText(optionObj.getString("text"));
						System.out.println("=== text in option array : "
								+ optionObj.getString("text"));
					}
					if (optionObj.has("activity")) {
						optionUtils
								.setActivity(optionObj.getString("activity"));
						System.out.println("=== activity in option array : "
								+ optionObj.getString("activity"));
					}
					if (optionObj.has("subQues")) {
						optionUtils.setSubQues(optionObj.getString("subQues"));
						System.out
								.println("=== sub Question  in option array : "
										+ optionObj.getString("subQues"));
					}
					if (optionObj.has("desc")) {
						optionUtils.setDesc(optionObj.getString("desc"));
						System.out
								.println("=== description  in option array : "
										+ optionObj.getString("desc"));
					}
					subQuesArrList = new ArrayList<SubOptionUtils>();
					if (optionObj.has("subQuesArr")) {

						JSONArray subOptnArr = optionObj
								.getJSONArray("subQuesArr");
						System.out.println("=== sub Question Arr size :"
								+ subOptnArr.length());
						for (int k = 0; k < subOptnArr.length(); k++) {
							JSONObject subOptnObj = subOptnArr.getJSONObject(k);
							SubOptionUtils subOpUtils = new SubOptionUtils();
							if (subOptnObj.has("text")) {
								subOpUtils
										.setText(subOptnObj.getString("text"));
								System.out
										.println("=== text  in sub option array : "
												+ subOptnObj.getString("text"));
							}
							if (subOptnObj.has("activity")) {
								subOpUtils.setActivity(subOptnObj
										.getString("activity"));
								System.out
										.println("=== activity  in sub option array : "
												+ subOptnObj
														.getString("activity"));

							}
							if (subOptnObj.has("desc")) {
								subOpUtils
										.setDesc(subOptnObj.getString("desc"));
								System.out
										.println("=== description  in sub option array : "
												+ subOptnObj.getString("desc"));
							}
							subQuesArrList.add(subOpUtils);
						}
						optionUtils.setSubQuesOptionArr(subQuesArrList);
					}
					optionArrayList.add(optionUtils);

				}
				questionUtils.setOptionArr(optionArrayList);
				if (jsonObj.has("secondQues")) {

					questionUtils
							.setSecondQues(jsonObj.getString("secondQues"));
					System.out.println("=== second question :"
							+ jsonObj.getString("secondQues"));
				}
				if (jsonObj.has("secondOptnArry")) {
					secondQuesArrList = new ArrayList<SubOptionUtils>();
					JSONArray secondOptnArr = jsonObj
							.getJSONArray("secondOptnArry");
					System.out
							.println("=== second question option Array size  :"
									+ secondOptnArr.length());
					for (int m = 0; m < secondOptnArr.length(); m++) {
						JSONObject secondOptnObj = secondOptnArr
								.getJSONObject(m);
						SubOptionUtils secondUtils = new SubOptionUtils();
						if (secondOptnObj.has("text")) {
							secondUtils
									.setText(secondOptnObj.getString("text"));
							System.out
									.println("=== second Question Option Text :"
											+ secondOptnObj.getString("text"));
						}
						if (secondOptnObj.has("activity")) {
							secondUtils.setActivity(secondOptnObj
									.getString("activity"));
							System.out
									.println("=== second Question Option activity  :"
											+ secondOptnObj
													.getString("activity"));

						}
						if (secondOptnObj.has("desc")) {
							secondUtils
									.setDesc(secondOptnObj.getString("desc"));
							System.out
									.println("=== second Question Option decription :"
											+ secondOptnObj.getString("desc"));
						}
						secondQuesArrList.add(secondUtils);
					}
					questionUtils.setSecondOptionArr(secondQuesArrList);
				}
				QuestionArrList.add(questionUtils);
			}
			System.out.println("=== Question Array list size :"
					+ QuestionArrList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getQuestionData1() {
		try {
			QuestionArrList = new ArrayList<QuestionUtils>();
			JSONObject mainObj = new JSONObject(utils.loadJSONFromAsset());
			System.out.println("=== json Data :" + mainObj);
			JSONArray mainArr = mainObj.getJSONArray("questionData");
			for (int i = 0; i < mainArr.length(); i++) {
				QuestionUtils questionUtils = new QuestionUtils();
				JSONObject jsonObj = mainArr.getJSONObject(i);
				questionUtils.setQuesNo(jsonObj.getString("quesNo"));
				System.out.println("=== question Number :"
						+ jsonObj.getString("quesNo"));
				questionUtils.setQuesType(jsonObj.getString("quesType"));
				System.out.println("=== question Type :"
						+ jsonObj.getString("quesType"));
				questionUtils.setQues(jsonObj.getString("ques"));
				System.out
						.println("=== question :" + jsonObj.getString("ques"));
				if (jsonObj.has("activity")) {
					questionUtils.setActivity(jsonObj.getString("activity"));
				}
				optionArrayList = new ArrayList<OptionUtils>();
				JSONArray optJsonArr = jsonObj.getJSONArray("optnArry");
				System.out.println("=== option  Arr size :"
						+ optJsonArr.length());

				for (int j = 0; j < optJsonArr.length(); j++) {

					OptionUtils optionUtils = new OptionUtils();
					JSONObject optionObj = optJsonArr.getJSONObject(j);
					if (optionObj.has("text")) {
						optionUtils.setOptionText(optionObj.getString("text"));
						System.out.println("=== text in option array : "
								+ optionObj.getString("text"));
					}
					if (optionObj.has("activity")) {
						optionUtils
								.setActivity(optionObj.getString("activity"));
						System.out.println("=== activity in option array : "
								+ optionObj.getString("activity"));
					}
					if (optionObj.has("subQues")) {
						optionUtils.setSubQues(optionObj.getString("subQues"));
						System.out
								.println("=== sub Question  in option array : "
										+ optionObj.getString("subQues"));
						/*
						 * new changes for back activity
						 */
					}
					if (optionObj.has("back_activity")) {
						optionUtils.setBack_activity(optionObj
								.getString("back_activity"));
						System.out
								.println("=== himani back activity in option array :"
										+ optionObj.getString("back_activity"));

					}
					if (optionObj.has("back_activity_to_five")) {
						optionUtils.setBack_activity_to_five(optionObj
								.getString("back_activity_to_five"));
						System.out
								.println("=== himani back activity to five in option array"
										+ optionObj
												.getString("back_activity_to_five"));

					}
					if (optionObj.has("back_activity_to_four")) {
						optionUtils.setBack_activity_to_four(optionObj
								.getString("back_activity_to_four"));
						System.out
								.println("=== himani back activity to four in option array"
										+ optionObj
												.getString("back_activity_to_four"));

					}
					if (optionObj.has("back_activity_to_three")) {
						optionUtils.setBack_activity_to_three(optionObj
								.getString("back_activity_to_three"));
						System.out
								.println("=== himani back activity to three in option array"
										+ optionObj
												.getString("back_activity_to_three"));

					}
					if (optionObj.has("back_activity_to_two")) {
						optionUtils.setBack_activity_to_two(optionObj
								.getString("back_activity_to_two"));
						System.out
								.println("=== himani back activity to two in option array"
										+ optionObj
												.getString("back_activity_to_two"));

					}
					if (optionObj.has("back_activity_to_eight")) {
						optionUtils.setBack_activity_to_eight(optionObj
								.getString("back_activity_to_eight"));
						System.out
								.println("=== himani back activity to eight in option array"
										+ optionObj
												.getString("back_activity_to_eight"));

					}
					if (optionObj.has("back_activity_to_twty_two")) {
						optionUtils.setBack_activity_to_twty_two(optionObj
								.getString("back_activity_to_twty_two"));
						System.out
								.println("=== himani back activity to five in option array");

					}
					if (optionObj.has("desc")) {
						optionUtils.setDesc(optionObj.getString("desc"));
						System.out
								.println("=== description  in option array : "
										+ optionObj.getString("desc"));
					}
					subQuesArrList = new ArrayList<SubOptionUtils>();
					if (optionObj.has("subQuesArr")) {

						JSONArray subOptnArr = optionObj
								.getJSONArray("subQuesArr");
						System.out.println("=== sub Question Arr size :"
								+ subOptnArr.length());
						for (int k = 0; k < subOptnArr.length(); k++) {
							JSONObject subOptnObj = subOptnArr.getJSONObject(k);
							SubOptionUtils subOpUtils = new SubOptionUtils();
							if (subOptnObj.has("text")) {
								subOpUtils
										.setText(subOptnObj.getString("text"));
								System.out
										.println("=== text  in sub option array : "
												+ subOptnObj.getString("text"));
							}
							if (subOptnObj.has("activity")) {
								subOpUtils.setActivity(subOptnObj
										.getString("activity"));
								System.out
										.println("=== activity  in sub option array : "
												+ subOptnObj
														.getString("activity"));

							}
							/*
							 * start new Changes for back activity
							 */
							if (subOptnObj.has("back_activity")) {
								subOpUtils.setBack_activity(subOptnObj
										.getString("back_activity"));
								System.out
										.println("himani back activity in sub option array :"
												+ subOptnObj
														.getString("back_activity"));

							}
							if (subOptnObj.has("back_activity_to_five")) {
								subOpUtils.setBack_activity_to_five(subOptnObj
										.getString("back_activity_to_five"));
								System.out
										.println("himani back  to five activity in sub option array :"
												+ subOptnObj
														.getString("back_activity_to_five"));

							}
							if (subOptnObj.has("back_activity_to_four")) {
								subOpUtils.setBack_activity_to_four(subOptnObj
										.getString("back_activity_to_four"));
								System.out
										.println("himani back  to four activity in sub option array :"
												+ subOptnObj
														.getString("back_activity_to_four"));

							}
							if (subOptnObj.has("back_activity_to_three")) {
								subOpUtils.setBack_activity_to_three(subOptnObj
										.getString("back_activity_to_three"));
								System.out
										.println("himani back  to three activity in sub option array :"
												+ subOptnObj
														.getString("back_activity_to_three"));

							}
							if (subOptnObj.has("back_activity_to_two")) {
								subOpUtils.setBack_activity_to_two(subOptnObj
										.getString("back_activity_to_two"));
								System.out
										.println("himani back  to two activity in sub option array :"
												+ subOptnObj
														.getString("back_activity_to_two"));

							}
							/*
							 * end new Changes for back activity
							 */
							if (subOptnObj.has("desc")) {
								subOpUtils
										.setDesc(subOptnObj.getString("desc"));
								System.out
										.println("=== description  in sub option array : "
												+ subOptnObj.getString("desc"));
							}
							subQuesArrList.add(subOpUtils);
						}
						optionUtils.setSubQuesOptionArr(subQuesArrList);
					}
					optionArrayList.add(optionUtils);

				}
				questionUtils.setOptionArr(optionArrayList);
				if (jsonObj.has("secondQues")) {

					questionUtils
							.setSecondQues(jsonObj.getString("secondQues"));
					System.out.println("=== second question :"
							+ jsonObj.getString("secondQues"));
				}
				if (jsonObj.has("secondOptnArry")) {
					secondQuesArrList = new ArrayList<SubOptionUtils>();
					JSONArray secondOptnArr = jsonObj
							.getJSONArray("secondOptnArry");
					System.out
							.println("=== second question option Array size  :"
									+ secondOptnArr.length());
					for (int m = 0; m < secondOptnArr.length(); m++) {
						JSONObject secondOptnObj = secondOptnArr
								.getJSONObject(m);
						SubOptionUtils secondUtils = new SubOptionUtils();
						if (secondOptnObj.has("text")) {
							secondUtils
									.setText(secondOptnObj.getString("text"));
							System.out
									.println("=== second Question Option Text :"
											+ secondOptnObj.getString("text"));
						}
						if (secondOptnObj.has("activity")) {
							secondUtils.setActivity(secondOptnObj
									.getString("activity"));
							System.out
									.println("=== second Question Option activity  :"
											+ secondOptnObj
													.getString("activity"));

						}
						/*
						 * new Changes for back activity
						 */
						if (secondOptnObj.has("back_activity")) {
							secondUtils.setBack_activity(secondOptnObj
									.getString("back_activity"));

						}
						if (secondOptnObj.has("desc")) {
							secondUtils
									.setDesc(secondOptnObj.getString("desc"));
							System.out
									.println("=== second Question Option decription :"
											+ secondOptnObj.getString("desc"));
						}
						secondQuesArrList.add(secondUtils);
					}
					questionUtils.setSecondOptionArr(secondQuesArrList);
				}
				QuestionArrList.add(questionUtils);
			}
			System.out.println("=== Question Array list size :"
					+ QuestionArrList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
