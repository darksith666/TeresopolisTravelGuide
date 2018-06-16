package com.gfeo.teresopolistravelguide;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertNotNull;

/**
 * A test suite for the {@link AboutActivity}.
 *
 * @author gabrielfeo
 */
@RunWith(AndroidJUnit4.class)
public class AboutActivityTest extends UiTest {

	@Rule
	public IntentsTestRule<AboutActivity> intentsTestRule =
			new IntentsTestRule<>(AboutActivity.class);

	/**
	 * Tests clicking on an attribution in the {@link AboutActivity} ListView. Clicking one should
	 * send an intent to open the license web page.
	 */
	@Test
	public void clickOnAttribution() {
		/*--/ Given /--*/
		assertNotNull(intentsTestRule.getActivity());
		onView(withId(R.id.about_listview)).check(matches(isDisplayed()));
		int attributionPosition = 1;
		ListView listView = getViewFromUi(intentsTestRule, R.id.about_listview);
		Attribution attribution = (Attribution) listView.getAdapter().getItem(attributionPosition);
		Uri expectedUri = attribution.getLicenseUri();

		/*--/ When /--*/
		onData(anything()).atPosition(attributionPosition)
		                  .inAdapterView(withId(R.id.about_listview))
		                  .perform(click());

		/*--/ Then /--*/
		Intents.intended(allOf(hasAction(Intent.ACTION_VIEW),
		                       hasData(expectedUri)));
		Intents.assertNoUnverifiedIntents();
	}

}
