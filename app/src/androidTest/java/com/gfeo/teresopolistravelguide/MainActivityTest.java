package com.gfeo.teresopolistravelguide;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.gfeo.teresopolistravelguide.UiTestUtils.getFromUi;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public IntentsTestRule<MainActivity> intentsTestRule =
			new IntentsTestRule<>(MainActivity.class);

	/**
	 * Tests clicking on a place in the {@code MainActivity} ListView. Clicking one should send
	 * an intent to Google Maps with a search query.
	 */
	@Test
	public void clickOnAPlace() {
		/*--/ Given /--*/
		assertActivity();
		checkIfFragmentIsDisplayed();
		int placePosition = 0;
		ListView listView = getFromUi(intentsTestRule, R.id.places_listview);
		Place place = (Place) listView.getAdapter().getItem(placePosition);
		Uri expectedUri = Uri.parse("geo:0,0?q=" + place.getTitle() + ", " + place.getLocation()
				                            + ", Teresópolis");

		/*--/ When /--*/
		onData(anything()).atPosition(placePosition)
		                  .inAdapterView(allOf(withId(R.id.places_listview), hasFocus()))
		                  .perform(click());

		/*--/ Then /--*/
		hasAction(Intent.ACTION_VIEW);
		Intents.intended(allOf(hasData(equalTo(expectedUri)),
		                       hasAction(Intent.ACTION_VIEW)));
	}

	/**
	 * Tests swiping between sections in {@code MainActivity}. Basically, the test cycles the
	 * {@link #swipeToSection(boolean, TabLayout, int)} method according to the number of tabs
	 * available. This test
	 * swipes between all tabs, forward and back.
	 *
	 * @see PlacesFragment
	 * @see SimpleFragmentPagerAdapter
	 */
	@Test
	public void swipeBetweenSections() {
		TabLayout tabLayout = getFromUi(intentsTestRule, R.id.main_tablayout);
		int tabCount = tabLayout.getTabCount();
		int startTab = tabLayout.getSelectedTabPosition();

		while (startTab < tabCount - 1) {
			swipeToSection(false, tabLayout, startTab);
			startTab++;
		}
		while (startTab >= tabCount - 1 && startTab > 0) {
			swipeToSection(true, tabLayout, startTab);
			startTab--;
		}

	}

	/**
	 * Tests the menu button pointing to {@link AboutActivity}.
	 */
	@Test
	public void openAboutScreen() {
		/*--/ Given /--*/
		assertActivity();

		/*--/ When /--*/
		openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
		onView(withText(R.string.main_menu_about)).perform(click());

		/*--/ Then /--*/
		Intents.intended(hasComponent(AboutActivity.class.getName()));
	}

	private void assertActivity() { assertNotNull(intentsTestRule.getActivity()); }

	private void checkIfFragmentIsDisplayed() {
		onView(allOf(withId(R.id.root_view_places_fragment),
		             hasFocus()))
				.check(matches(isDisplayed()));
	}

	private void swipeToSection(boolean toPreviousSection,
	                            TabLayout tabLayout, int currentPosition) {
		int destinationSection = (toPreviousSection) ? currentPosition - 1
		                                             : currentPosition + 1;

		/*--/ Given /--*/
		assertActivity();
		onView(withId(R.id.main_viewpager)).check(matches(isDisplayed()));
		String destinationTabTitle = tabLayout.getTabAt(destinationSection).getText().toString();

		/*--/ When /--*/
		ViewInteraction viewInteraction = onView(withId(R.id.main_viewpager));
		if (toPreviousSection) {
			viewInteraction.perform(swipeRight());
		} else {
			viewInteraction.perform(swipeLeft());
		}

		/*--/ Then /--*/
		onView(withText(destinationTabTitle)).check(matches(isSelected()));
	}

}
