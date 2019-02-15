package com.gc.materialdesigndemo.temp;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class WithXPath {
    public static Matcher<View> withXPath(@Nullable final View root, final String xpath,
                                          @Nullable final Integer index) throws AppiumException {
        // Get a list of the Views that match the provided xpath
        final List<View> matchedXPathViews = new SourceDocument(root).findViewsByXPath(xpath);
        Log.d("SIZER", String.valueOf(matchedXPathViews.size()));
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (index != null) {
                    // If index is not null, match it with the xpath in the list at the provided index
                    return index < matchedXPathViews.size() && matchedXPathViews.get(index).equals(item);
                }

                // If index is null, then we only check that the view is contained in the list of matched xpaths
                return matchedXPathViews.contains(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("Looked for element with XPath %s", xpath));
            }
        };
    }

    public static Matcher<View> withXPath(@Nullable final View root, final String xpath) throws AppiumException {
        return withXPath(root, xpath, null);
    }
}