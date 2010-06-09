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

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class HelloWorldServletTest {

  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  
  /**
   * 
   */
  public HelloWorldServletTest() {
   MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDoGet() throws ServletException, IOException {
    HelloWorldServlet helloWorldServlet = new HelloWorldServlet();
    
    StringWriter sw = new StringWriter();
    Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(sw));
    
    helloWorldServlet.doGet(req, resp);
    
    Assert.assertEquals("Hello World from Sling/Nakamura/Osgi", sw.toString());
  }
  
}
