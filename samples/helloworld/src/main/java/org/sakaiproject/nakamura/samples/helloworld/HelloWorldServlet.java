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


import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;
import org.sakaiproject.nakamura.samples.api.helloworld.SpeakingClockService;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

/**
 * The <code>HelloWorldServlet</code> says Hello World
 */

@SlingServlet(methods={"GET"}, resourceTypes={"sakai/helloworld"}, extensions={"json"})
@Properties(value={
    @Property(name="service.description", value="Hello World Servlet"),
    @Property(name="service.vendor", value="The Sakai Foundation")
})
public class HelloWorldServlet extends SlingSafeMethodsServlet {
  
  /**
   * 
   */
  public HelloWorldServlet() {
  }
  /**
   *
   */
  private static final long serialVersionUID = -2002186252317448037L;

  @Reference 
  protected SpeakingClockService speakingClockService;
  /**
   * {@inheritDoc}
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  /**
   * {@inheritDoc}
   * @see org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)
   */

  @Override
  protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)
      throws ServletException, IOException {
    Resource resource = req.getResource();
    Node node = resource.adaptTo(Node.class);
    JSONWriter jsonWriter = new JSONWriter(resp.getWriter());
    try {
      jsonWriter.object();
      jsonWriter.key("nodepath");
      jsonWriter.value(node.getPath());
      jsonWriter.key("message");
      jsonWriter.value("Hello World from Sling/Nakamura/Osgi the speaking clock says :"
          + speakingClockService.whatsTheTime());
      jsonWriter.endObject();
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
  }

}
