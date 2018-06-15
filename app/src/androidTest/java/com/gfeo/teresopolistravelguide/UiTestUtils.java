package com.gfeo.teresopolistravelguide;

import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

class UiTestUtils {

	static <T extends View> T getFromUi(IntentsTestRule intentsTestRule, int id) {
		final ArrayList<T> viewArrayList = new ArrayList<>(1);
		runOnUi(intentsTestRule,
		        () -> viewArrayList.add(intentsTestRule.getActivity().findViewById(id)));
		return viewArrayList.get(0);
	}

	static void runOnUi(IntentsTestRule intentsTestRule, Runnable action) {
		try {
			intentsTestRule.runOnUiThread(action);
		} catch (Throwable throwable) {
			Log.e("MainActivityTest", "Error running action on UI thread", throwable);
		}
	}

}
