package com.company;

public class XMLParsing {

    public XML parseXML(String initialStr) throws Exception {

        initialStr = initialStr.trim();

        if (!initialStr.startsWith("<")) {
            return new Text(initialStr);
        }

        int closeTagIndex = initialStr.indexOf(">");

        if (closeTagIndex < 0) {
            throw new Exception("Missing end tag.");
        }

        String stringContent = initialStr.substring(1, closeTagIndex);

        if (!initialStr.endsWith("</" + stringContent + ">")) {
            throw new Exception("Missing end tag for " + stringContent + ".");
        }

        XML xmlContent = new XML(stringContent);
        String inside = initialStr.substring(closeTagIndex + 1, initialStr.length() - (stringContent.length() + 3));
        parseXML(xmlContent, inside.trim());

        return xmlContent;
    }

    private void parseXML(XML xmlContent, String initialStr) throws Exception {
        while (!initialStr.isEmpty()) {
            int amount = 0;
            XML newXml = null;

            if (initialStr.startsWith("<")) {
                int closeTagIndex = initialStr.indexOf(">");

                if (closeTagIndex < 0) {
                    throw new Exception("Missing end tag.");
                }

                String stringContent = initialStr.substring(1, closeTagIndex);
                int lastPosition = initialStr.indexOf("</" + stringContent + ">");

                if (lastPosition < 0) {
                    throw new Exception("Missing end tag for " + stringContent + ".");
                }

                newXml = new XML(stringContent);
                String content = initialStr.substring(closeTagIndex + 1, lastPosition);

                parseXML(newXml, content.trim());
                amount = lastPosition + stringContent.length() + 3;

            } else {
                int firstTagIndex = initialStr.indexOf("<");

                if (firstTagIndex < 0) {
                    newXml = new Text(initialStr);
                    amount = initialStr.length();
                } else {
                    newXml = new Text(initialStr.substring(0, firstTagIndex));
                    amount = firstTagIndex;
                }
            }

            xmlContent.addChild(newXml);
            initialStr = initialStr.substring(amount).trim();
        }
    }
}
