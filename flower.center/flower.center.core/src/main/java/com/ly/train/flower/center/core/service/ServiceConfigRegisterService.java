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
package com.ly.train.flower.center.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.ly.train.flower.center.core.store.ServiceConfigStore;
import com.ly.train.flower.common.annotation.FlowerService;
import com.ly.train.flower.common.core.config.ServiceConfig;
import com.ly.train.flower.common.core.service.Service;
import com.ly.train.flower.common.core.service.ServiceContext;

/**
 * @author leeyazhou
 * 
 */
@FlowerService
public class ServiceConfigRegisterService implements Service<ServiceConfig, Boolean> {
  @Autowired
  protected ServiceConfigStore serviceConfigStore;

  @Override
  public Boolean process(ServiceConfig message, ServiceContext context) throws Throwable {
    serviceConfigStore.addServiceConfig(message);
    return Boolean.TRUE;
  }

}
