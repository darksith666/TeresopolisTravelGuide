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
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;

/**
 * A test suite for the {@link MainActivity}.
 *
 * @author gabrielfeo
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends UiTest {

	@Rule
	public IntentsTestRule<MainActivity> intentsTestRule =
			new IntentsTestRule<>(MainActivity.class);

	/**
	 * Tests clicking on a place in the {@link MainActivity} ListView. Clicking one should send
	 * an intent to Google Maps with a search query.
	 */
	@Test
	public void clickOnPlace() {
		/*--/ Given /--*/
		assertNotNull(intentsTestRule.getActivity());
		onView(allOf(withId(R.id.root_view_places_fragment), hasFocus()))
				.check(matches(isDisplayed()));
		int placePosition = 0;
		ListView listView = getViewFromUi(intentsTestRule, R.id.places_listview);
		Place place = (Place) listView.getAdapter().getItem(placePosition);
		Uri expectedUri = Uri.parse("geo:0,0?q=" + place.getTitle() + ", " + place.getLocation()
				                            + ", Teresópolis");

		/*--/ When /--*/
		onData(anything()).atPosition(placePosition)
		                  .inAdapterView(allOf(withId(R.id.places_listview), hasFocus()))
		                  .perform(click());

		/*--/ Then /--*/
		Intents.intended(allOf(hasData(equalTo(expectedUri)),
		                       hasAction(Intent.ACTION_VIEW)));
		Intents.assertNoUnverifiedIntents();
	}

	/**
	 * Tests swiping between sections in {@code MainActivity}. Basically, the test cycles the
	 * {@link #swipeToSection(boolean, TabLayout, int)} method according to the number of tabs
	 * available. This test swipes between all tabs, forward and back.
	 *
	 * @see PlacesFragment
	 * @see SimpleFragmentPagerAdapter
	 */
	@Test
	public void swipeBetweenSections() {
		TabLayout tabLayout = getViewFromUi(intentsTestRule, R.id.main_tablayout);
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
		assertNotNull(intentsTestRule.getActivity());

		/*--/ When /--*/
		openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
		onView(withText(R.string.main_menu_about)).perform(click());

		/*--/ Then /--*/
		Intents.intended(hasComponent(AboutActivity.class.getName()));
		Intents.assertNoUnverifiedIntents();
	}

	/**
	 * Tests swiping between Views in a {@link android.support.v4.view.ViewPager} with a
	 * {@link TabLayout}. Can swipe either to the next tab or to the previous tab.
	 *
	 * @param toPreviousSection determines the swipe direction.
	 * @param tabLayout         the {@code TabLayout} to be tested. This is used to assert the
	 *                          result, by getting the expected tab's title.
	 * @param currentPosition   the current tab's position in the {@code TabLayout}, used to
	 *                          determine the target tab.
	 */
	private void swipeToSection(boolean toPreviousSection,
	                            TabLayout tabLayout,
	                            int currentPosition) {

		int destinationSection = (toPreviousSection) ? currentPosition - 1
		                                             : currentPosition + 1;

		/*--/ Given /--*/
		assertNotNull(intentsTestRule.getActivity());
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
