package com.gfeo.teresopolistravelguide;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResourceUtils.class)
public class PlaceTest {

	/**
	 * Tests a Place object creation by verifying the behavior of its constructor and accessor
	 * methods.
	 */
	@Test
	public void createPlaceObject() {
		/*--/ Given /--*/
		// These values:
		String resourceName = "empire";
		String expectedTitle = "Empire State";
		String expectedSubtitle = "A building";
		String expectedLocation = "New York";
		int expectedResourceId = 12345;
		// These Mocks:
		Context context = mock(Context.class);
		ResourceUtilsMock resourceUtilsMock =
				new ResourceUtilsMock(null, expectedResourceId,
				                        expectedTitle, expectedSubtitle, expectedLocation);

		/*--/ When /--*/
		Place place = new Place(context, resourceName);

		/*--/ Then /--*/
		assertNotNull(place);
		assertThat(place, isA(Place.class));
		resourceUtilsMock.verify(false, true, true);
		assertThat(place.getTitle(), equalTo(expectedTitle));
		assertThat(place.getSubtitle(), equalTo(expectedSubtitle));
		assertThat(place.getLocation(), equalTo(expectedLocation));
		assertThat(place.getThumbnailResourceId(), equalTo(expectedResourceId));
	}
}
