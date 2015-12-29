package com.scms.dui.webservice;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import android.net.ParseException;
import android.os.AsyncTask;

public class AsyncDataReader extends AsyncTask<String, Void, Boolean> {
	private AsynchronousTaskHandler asyncTaskHandler;

	private String postingType;
	private String jsonData;
	private List<NameValuePair> nameValuePair;

	public AsyncDataReader() {

	}

	public AsyncDataReader(AsynchronousTaskHandler obj, String postingType) {
		asyncTaskHandler = obj;
		this.postingType = postingType;
	}

	public AsyncDataReader(AsynchronousTaskHandler obj, String postingType,
			String jsonData) {
		asyncTaskHandler = obj;
		this.postingType = postingType;
		this.jsonData = jsonData;
	}

	public AsyncDataReader(AsynchronousTaskHandler obj, String postingType,
			List<NameValuePair> nameValuePair) {
		asyncTaskHandler = obj;
		this.postingType = postingType;
		this.nameValuePair = nameValuePair;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		asyncTaskHandler.preExecute();
	}

	@Override
	protected Boolean doInBackground(String... urls) {
		try {
			HttpResponse response = null;

			if (postingType.equalsIgnoreCase(Utility.NORMALPOSTING)) {
				response = Utility.readDataFromUrl(urls[0]);
			} else if (postingType.equalsIgnoreCase(Utility.JSONPOSTING)) {
				response = Utility.postDataOnUrl(urls[0], jsonData);
			} else if (postingType
					.equalsIgnoreCase(Utility.NAMEVALUEPAIRPOSTING)) {
				response = Utility.postDataOnUrl(urls[0], nameValuePair);
			} else if (postingType.equalsIgnoreCase(Utility.DOWNLOADIMAGE)) {
				response = Utility.readDataFromUrl(urls[0]);
			}

			String data = "";
			if (response != null) {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					data = Utility.readUrlResponseAsString(response);
				}
				asyncTaskHandler.onProcess(true, data);
				return true;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		asyncTaskHandler.onProcess(false, "");
		return false;
	}

	protected void onPostExecute(Boolean result) {
		asyncTaskHandler.postExecute(result);
	}
}
