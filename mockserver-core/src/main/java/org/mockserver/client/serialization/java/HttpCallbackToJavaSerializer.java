package org.mockserver.client.serialization.java;

import com.google.common.base.Strings;
import org.mockserver.model.HttpClassCallback;

import static org.mockserver.client.serialization.java.ExpectationToJavaSerializer.INDENT_SIZE;

/**
 * @author jamesdbloom
 */
public class HttpCallbackToJavaSerializer implements ToJavaSerializer<HttpClassCallback> {

    @Override
    public String serializeAsJava(int numberOfSpacesToIndent, HttpClassCallback httpClassCallback) {
        StringBuffer output = new StringBuffer();
        if (httpClassCallback != null) {
            appendNewLineAndIndent(numberOfSpacesToIndent * INDENT_SIZE, output).append("callback()");
            if (httpClassCallback.getCallbackClass() != null) {
                appendNewLineAndIndent((numberOfSpacesToIndent + 1) * INDENT_SIZE, output).append(".withCallbackClass(\"").append(httpClassCallback.getCallbackClass()).append("\")");
            }
        }

        return output.toString();
    }

    private StringBuffer appendNewLineAndIndent(int numberOfSpacesToIndent, StringBuffer output) {
        return output.append(System.getProperty("line.separator")).append(Strings.padStart("", numberOfSpacesToIndent, ' '));
    }
}
