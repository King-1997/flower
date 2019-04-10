/**
 * Copyright © 2019 同程艺龙 (zhihui.li@ly.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package com.ly.train.flower.common.akka.actor;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import com.ly.train.flower.base.TestBase;
import com.ly.train.flower.base.model.User;
import com.ly.train.flower.base.service.ServiceA;
import com.ly.train.flower.base.service.ServiceB;
import com.ly.train.flower.base.service.ServiceC1;
import com.ly.train.flower.base.service.ServiceC2;
import com.ly.train.flower.common.service.container.ServiceFlow;

/**
 * @author leeyazhou
 *
 */
public class ServiceFacadeTest extends TestBase {

  @Test
  public void testSyncCallService() throws Exception {
    ServiceFlow serviceFlow = serviceFactory.getOrCreateServiceFlow(flowName);
    serviceFlow.buildFlow(ServiceA.class, ServiceB.class);
    serviceFlow.buildFlow(ServiceB.class, ServiceC1.class);
    serviceFlow.buildFlow(ServiceB.class, ServiceC2.class);

    User user = new User();
    user.setName("响应式编程");
    user.setAge(2);

    Object o = serviceFacade.syncCallService(flowName, user);
    System.out.println(o);
    Thread.sleep(TimeUnit.SECONDS.toMillis(5));

  }

  @Test
  public void testAsyncCallService() throws Exception {
    ServiceFlow serviceFlow = serviceFactory.getOrCreateServiceFlow(flowName);
    serviceFlow.buildFlow(ServiceA.class, ServiceB.class);
    serviceFlow.buildFlow(ServiceB.class, ServiceC1.class);
    serviceFlow.buildFlow(ServiceB.class, ServiceC2.class);

    User user = new User();
    user.setName("响应式编程");
    user.setAge(2);

    serviceFacade.asyncCallService(flowName, user);
    Thread.sleep(TimeUnit.SECONDS.toMillis(2));

  }
}
