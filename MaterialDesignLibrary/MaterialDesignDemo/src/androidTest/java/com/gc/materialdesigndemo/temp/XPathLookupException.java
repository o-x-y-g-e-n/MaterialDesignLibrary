package com.gc.materialdesigndemo.temp;

public class XPathLookupException extends AppiumException {
    public XPathLookupException(String xpath, String reason) {
        super(String.format("Could not parse XPath %s: %s", xpath, reason));
    }
}