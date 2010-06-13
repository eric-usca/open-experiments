/*
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.sakaiproject.nakamura.samples.helloworld;

import junit.framework.Assert;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sakaiproject.nakamura.samples.api.helloworld.SpeakingClockService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

/**
 *
 */
public class HelloWorldServletTest {

  @Mock
  private SlingHttpServletRequest req;
  @Mock
  private SlingHttpServletResponse resp;
  @Mock
  private SpeakingClockService speakingClockService;
  @Mock
  private Resource resource;
  @Mock
  private Node node;
  
  /**
   * 
   */
  public HelloWorldServletTest() {
   MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDoGet() throws ServletException, IOException, RepositoryException, JSONException {
    HelloWorldServlet helloWorldServlet = new HelloWorldServlet();
    
    StringWriter sw = new StringWriter();
    Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));
    helloWorldServlet.speakingClockService = speakingClockService;
    
    Mockito.when(req.getResource()).thenReturn(resource);
    Mockito.when(resource.adaptTo(Node.class)).thenReturn(node);
    Mockito.when(node.getPath()).thenReturn("/testinpath");
    
    Mockito.when(speakingClockService.whatsTheTime()).thenReturn("its always ten to three");
    helloWorldServlet.doGet(req, resp);
    
    String s = sw.toString();
    Assert.assertNotNull(s);
    JSONObject o = new JSONObject(s);
    Assert.assertEquals("/testinpath", o.get("nodepath"));
    Assert.assertEquals("Hello World from Sling/Nakamura/Osgi the speaking clock says :its always ten to three", o.get("message"));
  }
  
}
