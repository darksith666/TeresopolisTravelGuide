package com.gfeo.teresopolistravelguide;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Represents a UI test suite. This class will be extended by the actual test classes, to make
 * use of its common methods.
 *
 * @author gabrielfeo
 */
class UiTest {

	/**
	 * Gets a {@link View} from the <strong>current</strong> Activity's layout.
	 *
	 * @param intentsTestRule an {@link IntentsTestRule} used to get a reference to the current
	 *                        activity
	 * @param id              the integer ID of the desired {@code View}
	 * @param <T>             the type of the desire {@code View}
	 * @return the desired view
	 */
	protected <T extends View> T getViewFromUi(IntentsTestRule intentsTestRule, int id) {
		final ArrayList<T> viewArrayList = new ArrayList<>(1);
		Runnable getViewAction = () -> viewArrayList.add(intentsTestRule.getActivity()
		                                                                .findViewById(id));
		runOnUiThread(intentsTestRule, getViewAction);
		return viewArrayList.get(0);
	}

	/**
	 * Runs an action on the UI thread, using the {@link IntentsTestRule#runOnUiThread(Runnable)}
	 * method.
	 *
	 * @param intentsTestRule an {@link IntentsTestRule} to use the
	 *                        {@code runOnUiThread(Runnable)} method
	 * @param action          the {@link Runnable} to be run on the UI thread
	 */
	protected void runOnUiThread(IntentsTestRule intentsTestRule, Runnable action) {
		try {
			intentsTestRule.runOnUiThread(action);
		} catch (Throwable throwable) {
			Log.e("MainActivityTest", "Error running action on UI thread", throwable);
		}
	}

}
