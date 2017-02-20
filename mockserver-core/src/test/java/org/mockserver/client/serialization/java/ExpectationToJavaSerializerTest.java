package org.mockserver.client.serialization.java;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.mockserver.client.serialization.Base64Converter;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.mockserver.mock.Expectation;
import org.mockserver.model.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mockserver.model.ConnectionOptions.connectionOptions;
import static org.mockserver.model.HttpError.error;

/**
 * @author jamesdbloom
 */
public class ExpectationToJavaSerializerTest {

    @Test
    public void shouldSerializeFullObjectWithResponseAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withMethod(\"GET\")" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withHeaders(" + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameOne\", \"requestHeaderValueOneOne\", \"requestHeaderValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameTwo\", \"requestHeaderValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withCookies(" + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameOne\", \"requestCookieValueOne\")," + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameTwo\", \"requestCookieValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withQueryStringParameters(" + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameOne\", \"requestQueryStringParameterValueOneOne\", \"requestQueryStringParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameTwo\", \"requestQueryStringParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withBody(new StringBody(\"somebody\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .respond(" + System.getProperty("line.separator") +
                        "                response()" + System.getProperty("line.separator") +
                        "                        .withStatusCode(304)" + System.getProperty("line.separator") +
                        "                        .withHeaders(" + System.getProperty("line.separator") +
                        "                                new Header(\"responseHeaderNameOne\", \"responseHeaderValueOneOne\", \"responseHeaderValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Header(\"responseHeaderNameTwo\", \"responseHeaderValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withCookies(" + System.getProperty("line.separator") +
                        "                                new Cookie(\"responseCookieNameOne\", \"responseCookieValueOne\")," + System.getProperty("line.separator") +
                        "                                new Cookie(\"responseCookieNameTwo\", \"responseCookieValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withBody(\"responseBody\")" + System.getProperty("line.separator") +
                        "                        .withDelay(new Delay(TimeUnit.MINUTES, 1))" + System.getProperty("line.separator") +
                        "                        .withConnectionOptions(" + System.getProperty("line.separator") +
                        "                                connectionOptions()" + System.getProperty("line.separator") +
                        "                                        .withSuppressContentLengthHeader(true)" + System.getProperty("line.separator") +
                        "                                        .withContentLengthHeaderOverride(10)" + System.getProperty("line.separator") +
                        "                                        .withSuppressConnectionHeader(true)" + System.getProperty("line.separator") +
                        "                                        .withKeepAliveOverride(true)" + System.getProperty("line.separator") +
                        "                                        .withCloseSocket(true)" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withMethod("GET")
                                        .withPath("somePath")
                                        .withQueryStringParameters(
                                                new Parameter("requestQueryStringParameterNameOne", "requestQueryStringParameterValueOneOne", "requestQueryStringParameterValueOneTwo"),
                                                new Parameter("requestQueryStringParameterNameTwo", "requestQueryStringParameterValueTwo")
                                        )
                                        .withHeaders(
                                                new Header("requestHeaderNameOne", "requestHeaderValueOneOne", "requestHeaderValueOneTwo"),
                                                new Header("requestHeaderNameTwo", "requestHeaderValueTwo")
                                        )
                                        .withCookies(
                                                new Cookie("requestCookieNameOne", "requestCookieValueOne"),
                                                new Cookie("requestCookieNameTwo", "requestCookieValueTwo")
                                        )
                                        .withBody(new StringBody("somebody")),
                                Times.once(),
                                TimeToLive.unlimited()).thenRespond(
                                new HttpResponse()
                                        .withStatusCode(304)
                                        .withHeaders(
                                                new Header("responseHeaderNameOne", "responseHeaderValueOneOne", "responseHeaderValueOneTwo"),
                                                new Header("responseHeaderNameTwo", "responseHeaderValueTwo")
                                        )
                                        .withCookies(
                                                new Cookie("responseCookieNameOne", "responseCookieValueOne"),
                                                new Cookie("responseCookieNameTwo", "responseCookieValueTwo")
                                        )
                                        .withBody("responseBody")
                                        .withDelay(new Delay(TimeUnit.MINUTES, 1))
                                        .withConnectionOptions(
                                                connectionOptions()
                                                        .withSuppressContentLengthHeader(true)
                                                        .withContentLengthHeaderOverride(10)
                                                        .withSuppressConnectionHeader(true)
                                                        .withKeepAliveOverride(true)
                                                        .withCloseSocket(true)
                                        )
                        )
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithParameterBodyResponseAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withBody(" + System.getProperty("line.separator") +
                        "                                new ParameterBody(" + System.getProperty("line.separator") +
                        "                                        new Parameter(\"requestBodyParameterNameOne\", \"requestBodyParameterValueOneOne\", \"requestBodyParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                        new Parameter(\"requestBodyParameterNameTwo\", \"requestBodyParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                                )" + System.getProperty("line.separator") +
                        "                        )," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .respond(" + System.getProperty("line.separator") +
                        "                response()" + System.getProperty("line.separator") +
                        "                        .withBody(\"responseBody\")" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withBody(
                                                new ParameterBody(
                                                        new Parameter("requestBodyParameterNameOne", "requestBodyParameterValueOneOne", "requestBodyParameterValueOneTwo"),
                                                        new Parameter("requestBodyParameterNameTwo", "requestBodyParameterValueTwo")
                                                )
                                        ),
                                Times.once(),
                                TimeToLive.unlimited())
                                .thenRespond(
                                        new HttpResponse()
                                                .withBody("responseBody")
                                )
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithBinaryBodyResponseAsJava() throws IOException {
        // when
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withBody(Base64Converter.base64StringToBytes(\"" + Base64Converter.bytesToBase64String("request body".getBytes()) + "\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .respond(" + System.getProperty("line.separator") +
                        "                response()" + System.getProperty("line.separator") +
                        "                        .withBody(\"responseBody\")" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withBody(
                                                new BinaryBody("request body".getBytes())
                                        ),
                                Times.once(),
                                TimeToLive.unlimited())
                                .thenRespond(
                                        new HttpResponse()
                                                .withBody("responseBody")
                                )
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithForwardAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withMethod(\"GET\")" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withHeaders(" + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameOne\", \"requestHeaderValueOneOne\", \"requestHeaderValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameTwo\", \"requestHeaderValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withCookies(" + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameOne\", \"requestCookieValueOne\")," + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameTwo\", \"requestCookieValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withQueryStringParameters(" + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameOne\", \"requestQueryStringParameterValueOneOne\", \"requestQueryStringParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameTwo\", \"requestQueryStringParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withBody(new StringBody(\"somebody\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .forward(" + System.getProperty("line.separator") +
                        "                forward()" + System.getProperty("line.separator") +
                        "                        .withHost(\"some_host\")" + System.getProperty("line.separator") +
                        "                        .withPort(9090)" + System.getProperty("line.separator") +
                        "                        .withScheme(HttpForward.Scheme.HTTPS)" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withMethod("GET")
                                        .withPath("somePath")
                                        .withQueryStringParameters(
                                                new Parameter("requestQueryStringParameterNameOne", "requestQueryStringParameterValueOneOne", "requestQueryStringParameterValueOneTwo"),
                                                new Parameter("requestQueryStringParameterNameTwo", "requestQueryStringParameterValueTwo")
                                        )
                                        .withHeaders(
                                                new Header("requestHeaderNameOne", "requestHeaderValueOneOne", "requestHeaderValueOneTwo"),
                                                new Header("requestHeaderNameTwo", "requestHeaderValueTwo")
                                        )
                                        .withCookies(
                                                new Cookie("requestCookieNameOne", "requestCookieValueOne"),
                                                new Cookie("requestCookieNameTwo", "requestCookieValueTwo")
                                        )
                                        .withBody(new StringBody("somebody")),
                                Times.once(),
                                TimeToLive.unlimited())
                                .thenForward(
                                        new HttpForward()
                                                .withHost("some_host")
                                                .withPort(9090)
                                                .withScheme(HttpForward.Scheme.HTTPS)
                                )
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithErrorAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withMethod(\"GET\")" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withHeaders(" + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameOne\", \"requestHeaderValueOneOne\", \"requestHeaderValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameTwo\", \"requestHeaderValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withCookies(" + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameOne\", \"requestCookieValueOne\")," + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameTwo\", \"requestCookieValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withQueryStringParameters(" + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameOne\", \"requestQueryStringParameterValueOneOne\", \"requestQueryStringParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameTwo\", \"requestQueryStringParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withBody(new StringBody(\"somebody\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .error(" + System.getProperty("line.separator") +
                        "                error()" + System.getProperty("line.separator") +
                        "                        .withDelay(new Delay(TimeUnit.MINUTES, 1))" + System.getProperty("line.separator") +
                        "                        .withDropConnection(true)" + System.getProperty("line.separator") +
                        "                        .withResponseBytes(Base64Converter.base64StringToBytes(\"" + Base64Converter.bytesToBase64String("some_bytes".getBytes()) + "\"))" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withMethod("GET")
                                        .withPath("somePath")
                                        .withQueryStringParameters(
                                                new Parameter("requestQueryStringParameterNameOne", "requestQueryStringParameterValueOneOne", "requestQueryStringParameterValueOneTwo"),
                                                new Parameter("requestQueryStringParameterNameTwo", "requestQueryStringParameterValueTwo")
                                        )
                                        .withHeaders(
                                                new Header("requestHeaderNameOne", "requestHeaderValueOneOne", "requestHeaderValueOneTwo"),
                                                new Header("requestHeaderNameTwo", "requestHeaderValueTwo")
                                        )
                                        .withCookies(
                                                new Cookie("requestCookieNameOne", "requestCookieValueOne"),
                                                new Cookie("requestCookieNameTwo", "requestCookieValueTwo")
                                        )
                                        .withBody(new StringBody("somebody")),
                                Times.once(),
                                TimeToLive.unlimited()
                        )
                                .thenError(
                                        error()
                                                .withDelay(new Delay(TimeUnit.MINUTES, 1))
                                                .withDropConnection(true)
                                                .withResponseBytes("some_bytes".getBytes())
                                )
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithCallbackAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withMethod(\"GET\")" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withHeaders(" + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameOne\", \"requestHeaderValueOneOne\", \"requestHeaderValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Header(\"requestHeaderNameTwo\", \"requestHeaderValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withCookies(" + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameOne\", \"requestCookieValueOne\")," + System.getProperty("line.separator") +
                        "                                new Cookie(\"requestCookieNameTwo\", \"requestCookieValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withQueryStringParameters(" + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameOne\", \"requestQueryStringParameterValueOneOne\", \"requestQueryStringParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestQueryStringParameterNameTwo\", \"requestQueryStringParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                        .withBody(new StringBody(\"somebody\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .callback(" + System.getProperty("line.separator") +
                        "                callback()" + System.getProperty("line.separator") +
                        "                        .withCallbackClass(\"some_class\")" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withMethod("GET")
                                        .withPath("somePath")
                                        .withQueryStringParameters(
                                                new Parameter("requestQueryStringParameterNameOne", "requestQueryStringParameterValueOneOne", "requestQueryStringParameterValueOneTwo"),
                                                new Parameter("requestQueryStringParameterNameTwo", "requestQueryStringParameterValueTwo")
                                        )
                                        .withHeaders(
                                                new Header("requestHeaderNameOne", "requestHeaderValueOneOne", "requestHeaderValueOneTwo"),
                                                new Header("requestHeaderNameTwo", "requestHeaderValueTwo")
                                        )
                                        .withCookies(
                                                new Cookie("requestCookieNameOne", "requestCookieValueOne"),
                                                new Cookie("requestCookieNameTwo", "requestCookieValueTwo")
                                        )
                                        .withBody(new StringBody("somebody")),
                                Times.once(),
                                TimeToLive.unlimited())
                                .thenCallback(
                                        new HttpClassCallback()
                                                .withCallbackClass("some_class")
                                )
                )
        );
    }

    @Test
    public void shouldEscapeJsonBodies() throws IOException {
        assertEquals("" + System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withBody(new JsonBody(\"[" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"1\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"Xenophon's imperial fiction : on the education of Cyrus\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"James Tatum\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"0691067570\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"1989\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"2\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"You are here : personal geographies and other maps of the imagination\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"Katharine A. Harmon\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"1568984308\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"2004\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"3\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"You just don't understand : women and men in conversation\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"Deborah Tannen\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"0345372050\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"1990\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "]\", JsonBodyMatchType.ONLY_MATCHING_FIELDS))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .respond(" + System.getProperty("line.separator") +
                        "                response()" + System.getProperty("line.separator") +
                        "                        .withStatusCode(304)" + System.getProperty("line.separator") +
                        "                        .withBody(\"[" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"1\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"Xenophon's imperial fiction : on the education of Cyrus\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"James Tatum\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"0691067570\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"1989\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"2\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"You are here : personal geographies and other maps of the imagination\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"Katharine A. Harmon\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"1568984308\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"2004\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"3\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"You just don't understand : women and men in conversation\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"Deborah Tannen\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"0345372050\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"1990\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "]\")" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withPath("somePath")
                                        .withBody(new JsonBody("[" + System.getProperty("line.separator") +
                                                "    {" + System.getProperty("line.separator") +
                                                "        \"id\": \"1\"," + System.getProperty("line.separator") +
                                                "        \"title\": \"Xenophon's imperial fiction : on the education of Cyrus\"," + System.getProperty("line.separator") +
                                                "        \"author\": \"James Tatum\"," + System.getProperty("line.separator") +
                                                "        \"isbn\": \"0691067570\"," + System.getProperty("line.separator") +
                                                "        \"publicationDate\": \"1989\"" + System.getProperty("line.separator") +
                                                "    }," + System.getProperty("line.separator") +
                                                "    {" + System.getProperty("line.separator") +
                                                "        \"id\": \"2\"," + System.getProperty("line.separator") +
                                                "        \"title\": \"You are here : personal geographies and other maps of the imagination\"," + System.getProperty("line.separator") +
                                                "        \"author\": \"Katharine A. Harmon\"," + System.getProperty("line.separator") +
                                                "        \"isbn\": \"1568984308\"," + System.getProperty("line.separator") +
                                                "        \"publicationDate\": \"2004\"" + System.getProperty("line.separator") +
                                                "    }," + System.getProperty("line.separator") +
                                                "    {" + System.getProperty("line.separator") +
                                                "        \"id\": \"3\"," + System.getProperty("line.separator") +
                                                "        \"title\": \"You just don't understand : women and men in conversation\"," + System.getProperty("line.separator") +
                                                "        \"author\": \"Deborah Tannen\"," + System.getProperty("line.separator") +
                                                "        \"isbn\": \"0345372050\"," + System.getProperty("line.separator") +
                                                "        \"publicationDate\": \"1990\"" + System.getProperty("line.separator") +
                                                "    }" + System.getProperty("line.separator") +
                                                "]")),
                                Times.once(),
                                TimeToLive.unlimited()).thenRespond(
                                new HttpResponse()
                                        .withStatusCode(304)
                                        .withBody("[" + System.getProperty("line.separator") +
                                                "    {" + System.getProperty("line.separator") +
                                                "        \"id\": \"1\"," + System.getProperty("line.separator") +
                                                "        \"title\": \"Xenophon's imperial fiction : on the education of Cyrus\"," + System.getProperty("line.separator") +
                                                "        \"author\": \"James Tatum\"," + System.getProperty("line.separator") +
                                                "        \"isbn\": \"0691067570\"," + System.getProperty("line.separator") +
                                                "        \"publicationDate\": \"1989\"" + System.getProperty("line.separator") +
                                                "    }," + System.getProperty("line.separator") +
                                                "    {" + System.getProperty("line.separator") +
                                                "        \"id\": \"2\"," + System.getProperty("line.separator") +
                                                "        \"title\": \"You are here : personal geographies and other maps of the imagination\"," + System.getProperty("line.separator") +
                                                "        \"author\": \"Katharine A. Harmon\"," + System.getProperty("line.separator") +
                                                "        \"isbn\": \"1568984308\"," + System.getProperty("line.separator") +
                                                "        \"publicationDate\": \"2004\"" + System.getProperty("line.separator") +
                                                "    }," + System.getProperty("line.separator") +
                                                "    {" + System.getProperty("line.separator") +
                                                "        \"id\": \"3\"," + System.getProperty("line.separator") +
                                                "        \"title\": \"You just don't understand : women and men in conversation\"," + System.getProperty("line.separator") +
                                                "        \"author\": \"Deborah Tannen\"," + System.getProperty("line.separator") +
                                                "        \"isbn\": \"0345372050\"," + System.getProperty("line.separator") +
                                                "        \"publicationDate\": \"1990\"" + System.getProperty("line.separator") +
                                                "    }" + System.getProperty("line.separator") +
                                                "]")
                        )
                )
        );
    }

    @Test
    public void shouldEscapeJsonSchemaBodies() throws IOException {
        String jsonSchema = "{" + System.getProperty("line.separator") +
                "    \"$schema\": \"http://json-schema.org/draft-04/schema#\"," + System.getProperty("line.separator") +
                "    \"title\": \"Product\"," + System.getProperty("line.separator") +
                "    \"description\": \"A product from Acme's catalog\"," + System.getProperty("line.separator") +
                "    \"type\": \"object\"," + System.getProperty("line.separator") +
                "    \"properties\": {" + System.getProperty("line.separator") +
                "        \"id\": {" + System.getProperty("line.separator") +
                "            \"description\": \"The unique identifier for a product\"," + System.getProperty("line.separator") +
                "            \"type\": \"integer\"" + System.getProperty("line.separator") +
                "        }," + System.getProperty("line.separator") +
                "        \"name\": {" + System.getProperty("line.separator") +
                "            \"description\": \"Name of the product\"," + System.getProperty("line.separator") +
                "            \"type\": \"string\"" + System.getProperty("line.separator") +
                "        }," + System.getProperty("line.separator") +
                "        \"price\": {" + System.getProperty("line.separator") +
                "            \"type\": \"number\"," + System.getProperty("line.separator") +
                "            \"minimum\": 0," + System.getProperty("line.separator") +
                "            \"exclusiveMinimum\": true" + System.getProperty("line.separator") +
                "        }," + System.getProperty("line.separator") +
                "        \"tags\": {" + System.getProperty("line.separator") +
                "            \"type\": \"array\"," + System.getProperty("line.separator") +
                "            \"items\": {" + System.getProperty("line.separator") +
                "                \"type\": \"string\"" + System.getProperty("line.separator") +
                "            }," + System.getProperty("line.separator") +
                "            \"minItems\": 1," + System.getProperty("line.separator") +
                "            \"uniqueItems\": true" + System.getProperty("line.separator") +
                "        }" + System.getProperty("line.separator") +
                "    }," + System.getProperty("line.separator") +
                "    \"required\": [\"id\", \"name\", \"price\"]" + System.getProperty("line.separator") +
                "}";
        assertEquals("" + System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withBody(new JsonSchemaBody(\"" + StringEscapeUtils.escapeJava(jsonSchema) + "\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .respond(" + System.getProperty("line.separator") +
                        "                response()" + System.getProperty("line.separator") +
                        "                        .withStatusCode(304)" + System.getProperty("line.separator") +
                        "                        .withBody(\"responseBody\")" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withPath("somePath")
                                        .withBody(new JsonSchemaBody(jsonSchema)),
                                Times.once(),
                                TimeToLive.unlimited()).thenRespond(
                                new HttpResponse()
                                        .withStatusCode(304)
                                        .withBody("responseBody")
                        )
                )
        );
    }

    @Test
    public void shouldSerializeMinimalObjectAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        new MockServerClient(\"localhost\", 1080)" + System.getProperty("line.separator") +
                        "        .when(" + System.getProperty("line.separator") +
                        "                request()" + System.getProperty("line.separator") +
                        "                        .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                        .withBody(new StringBody(\"responseBody\"))," + System.getProperty("line.separator") +
                        "                Times.once()" + System.getProperty("line.separator") +
                        "        )" + System.getProperty("line.separator") +
                        "        .respond(" + System.getProperty("line.separator") +
                        "                response()" + System.getProperty("line.separator") +
                        "                        .withStatusCode(304)" + System.getProperty("line.separator") +
                        "        );",
                new ExpectationToJavaSerializer().serializeAsJava(1,
                        new Expectation(
                                new HttpRequest()
                                        .withPath("somePath")
                                        .withBody(new StringBody("responseBody")),
                                Times.once(),
                                TimeToLive.unlimited()).thenRespond(
                                new HttpResponse()
                                        .withStatusCode(304)
                        )
                )
        );
    }
}
