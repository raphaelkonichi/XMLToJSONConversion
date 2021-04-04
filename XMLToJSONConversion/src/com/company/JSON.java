package com.company;
import java.util.List;

import java.util.List;

public class JSON {
    public String convertJSON(XML xmlElement) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("{");
        buffer.append("\"" + xmlElement.getName() + "\":{");

        this.convertJSON(xmlElement, buffer);

        buffer.append("}");
        String jsonElement = buffer.toString();

        return jsonElement;
    }

    private void convertJSON(XML root, StringBuffer jsonElement) {
        List<XML> xmlChildren = root.getChildren();

        if (xmlChildren.size() == 1 && xmlChildren.get(0) instanceof XML) {
            jsonElement.append("\"" + root.getName() + "\":\"" + ((Text) xmlChildren.get(0)).getText() + "\",");
        } else {
            boolean checkChildren = true;

            if (xmlChildren.size() > 1) {
                for (int i = 0; i < xmlChildren.size() - 1; i++) {
                    if (!xmlChildren.get(i).getName().equals(xmlChildren.get(i + 1).getName())) {
                        checkChildren = false;
                        break;
                    }
                }
                if (checkChildren) {
                    jsonElement.append("\"" + xmlChildren.get(0).getName() + "\":[");
                } else {
                    jsonElement.append("{");
                }
            } else {
                jsonElement.append("{");
            }

            for (XML xmlChild : xmlChildren) {
                convertJSON(xmlChild, jsonElement);
            }

            jsonElement = jsonElement.delete(jsonElement.length() - 1, jsonElement.length());

            if (checkChildren) {
                jsonElement.append("}");
                jsonElement.append("]");
            }

            jsonElement.append("}");
        }
    }
}
