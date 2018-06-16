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
public class AttributionTest {

	/**
	 * Tests an Attribution object creation by verifying the behavior of its constructor and
	 * accessor methods.
	 */
	@Test
	public void createAttributionObject() {
		/*--/ Given /--*/
		// These values:
		String resourceName = "geddes";
		String licenseName = "(CC BY 3.0 BR)";
		String expectedAuthor = "Anne Geddes\n" + licenseName;
		String expectedLicenseUri = "https://creativecommons.org/licenses/by-sa/3.0/deed.en";
		int expectedImageResourceId = 12345;
		// These mocks:
		Context context = mock(Context.class);
		ResourceUtilsMock resourceUtilsMock =
				new ResourceUtilsMock(expectedAuthor, expectedImageResourceId,
				                        licenseName, expectedLicenseUri);

		/*--/ When /--*/
		Attribution attribution = new Attribution(context, resourceName);

		/*--/ Then /--*/
		assertNotNull(attribution);
		assertThat(attribution, isA(Attribution.class));
		resourceUtilsMock.verify(true, true, true);
		assertThat(attribution.getAuthorName(), equalTo(expectedAuthor));
		assertThat(attribution.getLicenseUri(), equalTo(expectedLicenseUri));
		assertThat(attribution.getImageResourceId(), equalTo(expectedImageResourceId));
	}

}
