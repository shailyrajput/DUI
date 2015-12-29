package com.scms.dui.webservice;

public interface AsynchronousTaskHandler {

	public void preExecute();

	public void postExecute(boolean result);

	public void onProcess(boolean result, String jsonData);

}
