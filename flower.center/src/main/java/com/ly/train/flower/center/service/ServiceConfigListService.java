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
package com.ly.train.flower.center.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.ly.train.flower.center.common.ServiceManager;
import com.ly.train.flower.common.annotation.FlowerService;
import com.ly.train.flower.common.service.Service;
import com.ly.train.flower.common.service.config.ServiceConfig;
import com.ly.train.flower.common.service.container.ServiceContext;

/**
 * @author leeyazhou
 *
 */
@FlowerService(timeout = 1000)
public class ServiceConfigListService implements Service<Object, Set<ServiceConfig>> {

  @Autowired
  protected ServiceManager serviceManager;

  @Override
  public Set<ServiceConfig> process(Object message, ServiceContext context) throws Throwable {

    return serviceManager.getAllServiceConfig();
  }

}
