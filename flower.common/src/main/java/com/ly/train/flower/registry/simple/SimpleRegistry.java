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
package com.ly.train.flower.registry.simple;

import java.util.List;
import com.ly.train.flower.common.util.HttpClient;
import com.ly.train.flower.common.util.URL;
import com.ly.train.flower.registry.AbstractRegistry;
import com.ly.train.flower.registry.config.ServiceInfo;

/**
 * @author leeyazhou
 *
 */
public class SimpleRegistry extends AbstractRegistry {

  private final URL url;

  public SimpleRegistry(URL url) {
    this.url = url;
  }

  @Override
  public boolean doRegister(ServiceInfo serviceInfo) {
    logger.info("register serviceInfo : {}", serviceInfo);
    String u = String.format("http://%s:%s/service/register", url.getHost(), url.getPort());

    String ret = HttpClient.builder().setUrl(u).setParam(serviceInfo.toParam()).build().post();
    logger.info("register service result : {}, serviceInfo : {}", ret, serviceInfo);
    return Boolean.TRUE;
  }

  @Override
  public List<ServiceInfo> doGetProvider(ServiceInfo serviceInfo) {
    logger.info("register serviceInfo : {}", serviceInfo);
    String u = String.format("http://%s:%s/service/list", url.getHost(), url.getPort());
    String ret = HttpClient.builder().setUrl(u).setParam(serviceInfo.toParam()).build().post();
    logger.info("register service result : {}, serviceInfo : {}", ret, serviceInfo);
    return null;
  }


}
