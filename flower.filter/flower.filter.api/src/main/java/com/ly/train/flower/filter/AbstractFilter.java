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
package com.ly.train.flower.filter;

import com.ly.train.flower.common.core.service.Service;
import com.ly.train.flower.common.core.service.ServiceContext;
import com.ly.train.flower.common.logging.Logger;
import com.ly.train.flower.common.logging.LoggerFactory;

/**
 * @author leeyazhou
 * 
 */
public abstract class AbstractFilter<P, R> implements Service<P, R>, Filter<P, R> {
  protected final Logger logger = LoggerFactory.getLogger(getClass());
  private Filter<P, R> nextFilter;

  @Override
  public void init() {}

  @Override
  public R process(P message, ServiceContext context) throws Throwable {
    return filter(message, context);
  }

  @Override
  public R filter(P message, ServiceContext context) throws Throwable {
    R ret = doFilter(message, context);
    if (ret != null && nextFilter != null) {
      return nextFilter.filter(message, context);
    }
    return ret;
  }

  @Override
  public void setNext(Filter<P, R> filter) {
    if (nextFilter != null) {
      nextFilter.setNext(filter);
    } else {
      this.nextFilter = filter;
    }
  }

  /**
   * 执行职责
   * 
   * @param message 参数
   * @param context 上下文
   * @return result
   */
  public abstract R doFilter(P message, ServiceContext context);

}
