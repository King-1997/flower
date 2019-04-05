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
package com.ly.train.flower.common.service.container;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ly.train.flower.common.exception.ServiceNotFoundException;
import com.ly.train.flower.common.service.FlowerService;
import com.ly.train.flower.common.service.container.simple.SimpleFlowerFactory;
import com.ly.train.flower.registry.Registry;
import com.ly.train.flower.registry.config.ServiceInfo;

public class ServiceFactory {
  static final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);

  public static void registerService(String serviceName, String serviceClass) {
    ServiceLoader.getInstance().registerServiceType(serviceName, serviceClass);

    serviceClass = ServiceLoader.getInstance().loadServiceMeta(serviceName).getServiceClass().getName();
    Set<Registry> registries = SimpleFlowerFactory.get().getRegistry();
    ServiceInfo serviceInfo = new ServiceInfo();
    serviceInfo.setApplication(SimpleFlowerFactory.get().getFlowerConfig().getName());
    serviceInfo.setHost(new HashSet<>());
    serviceInfo.getHost().add(SimpleFlowerFactory.get().getFlowerConfig().getHost());
    serviceInfo.setCreateTime(new Date());
    serviceInfo.setClassName(serviceClass);
    for (Registry registry : registries) {
      registry.register(serviceInfo);
    }
  }

  public static void registerService(String serviceName, Class<?> serviceClass) {
    registerService(serviceName, serviceClass.getName());
  }

  public static void registerService(Map<String, String> map) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      registerService(entry.getKey().trim(), entry.getValue().trim());
    }

  }

  public static void registerFlowerService(String serviceName, FlowerService flowerService) {
    ServiceLoader.getInstance().registerFlowerService(serviceName, flowerService);
  }

  public static FlowerService getService(String serviceName) {
    return ServiceLoader.getInstance().loadService(serviceName);
  }

  public static String getServiceClassName(String serviceName) {
    ServiceMeta serviceMeta = ServiceLoader.getInstance().loadServiceMeta(serviceName);
    if (serviceMeta == null) {
      throw new ServiceNotFoundException("serviceName : " + serviceName);
    }
    return serviceMeta.getServiceClass().getName();
  }

  public static String getServiceClassParameter(String serviceName) {
    return getServiceConf(serviceName, 1);
  }

  private static String getServiceConf(String serviceName, int index) {
    return ServiceLoader.getInstance().loadServiceMeta(serviceName).getConfig(index);
  }

}
