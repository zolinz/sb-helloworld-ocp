/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.quickstarts.cxf.jaxrs;

import io.swagger.annotations.Api;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Api("/sayHello")
@Component
public class HelloServiceImpl implements HelloService {


    @Autowired
    ProducerTemplate producerTemplate;


    public String welcome() {
        return "Welcome to the CXF RS Spring Boot application, append /{name} to call the hello service";
    }

    public String sayHello(String name) {

        return (String) producerTemplate.requestBody("direct:sayHelloRoute", name);

//        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }
    
}
