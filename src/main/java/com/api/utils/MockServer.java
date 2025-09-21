package com.api.utils;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
public class MockServer {
 private static WireMockServer  wiremockserver;
 
 public static void startMockServer() {
	 wiremockserver = new WireMockServer(8080);
	 wiremockserver.start();
	 configureFor("localhost",8080);
	 
//	 wiremockserver.stubFor(get(urlEqualTo("/mockapi/hello")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("{ \"message\": \"Hello from WireMock!\" }")));

 }

 public static void stopMockServer() {
     if (wiremockserver != null) {
         wiremockserver.stop();
     }
 }
 public static void createStub() {
     stubFor(get(urlEqualTo("/api/user/123"))
             .willReturn(aResponse()
                     .withStatus(200)
                     .withHeader("Content-Type", "application/json")
                     .withBody("{ \"id\": 123, \"name\": \"Annappa\", \"role\": \"Automation Engineer\" }")));
 }
}

