package com.gfeo.teresopolistravelguide;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResourceMethods.class)
public class PlaceTest {

	private Place place;
	private String[] expectedPlaceInfo = {"Empire State", "A building", "New York"};
	private String resourceName = "EmpireState";
	private int expectedResourceId = 12345;

	/**
	 * Tests a Place object creation by verifying the behavior of its constructor and accessor
	 * methods.
	 */
	@Test
	public void shouldCreatePlaceObject() {
		/*--/ Given /--*/
		Context context = mock(Context.class);
		PowerMockito.mockStatic(ResourceMethods.class);
		when(ResourceMethods.getResourceIdFromName(context, resourceName, "drawable"))
				.thenReturn(expectedResourceId);
		when(ResourceMethods.getStringArrayFromResourceName(context, resourceName))
				.thenReturn(expectedPlaceInfo);

		/*--/ When /--*/
		// A new place object
		place = new Place(context, resourceName);

		/*--/ Then /--*/
		// Verify constructors
		assertNotNull(place);
		assertThat(place, isA(Place.class));
		// Verify mocked static methods
		PowerMockito.verifyStatic(ResourceMethods.class);
		ResourceMethods.getResourceIdFromName(context, resourceName, "drawable");
		PowerMockito.verifyStatic(ResourceMethods.class);
		ResourceMethods.getStringArrayFromResourceName(context, resourceName);
		// Verify actual methods
		assertThat(place.getTitle(), equalTo(expectedPlaceInfo[0]));
		assertThat(place.getSubtitle(), equalTo(expectedPlaceInfo[1]));
		assertThat(place.getLocation(), equalTo(expectedPlaceInfo[2]));
		assertThat(place.getThumbnailResourceId(), equalTo(expectedResourceId));
	}
}
