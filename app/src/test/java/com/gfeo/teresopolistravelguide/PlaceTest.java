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
@PrepareForTest(ResourceMethods.class)
public class PlaceTest {

	private Place place;
	private String resourceName = "empire";
	private String expectedTitle = "Empire State";
	private String expectedSubtitle = "A building";
	private String expectedLocation = "New York";
	private int expectedResourceId = 12345;

	/**
	 * Tests a Place object creation by verifying the behavior of its constructor and accessor
	 * methods.
	 */
	@Test
	public void shouldCreatePlaceObject() {
		/*--/ Given /--*/
		Context context = mock(Context.class);
		ResourceMethodsMock resourceMethodsMock =
				new ResourceMethodsMock(null, expectedResourceId,
				                        expectedTitle, expectedSubtitle, expectedLocation);

		/*--/ When /--*/
		place = new Place(context, resourceName);

		/*--/ Then /--*/
		assertNotNull(place);
		assertThat(place, isA(Place.class));
		resourceMethodsMock.verify(false, true, true);
		assertThat(place.getTitle(), equalTo(expectedTitle));
		assertThat(place.getSubtitle(), equalTo(expectedSubtitle));
		assertThat(place.getLocation(), equalTo(expectedLocation));
		assertThat(place.getThumbnailResourceId(), equalTo(expectedResourceId));
	}
}
