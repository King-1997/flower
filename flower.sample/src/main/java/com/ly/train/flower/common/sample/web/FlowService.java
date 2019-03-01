package com.ly.train.flower.common.sample.web;

import com.ly.train.flower.common.service.Complete;
import com.ly.train.flower.common.service.Service;
import com.ly.train.flower.common.service.containe.ServiceContext;
import com.ly.train.flower.common.service.web.Flush;
import com.ly.train.flower.common.service.web.HttpComplete;

public class FlowService implements Service, HttpComplete, Flush, Complete {

  public FlowService() {
  }

  @Override
  /**
   * trim service
   */
  public Object process(Object message, ServiceContext context) throws Exception {

    context.getWeb().println(" - end:" + System.currentTimeMillis());
    return "";
  }

  public long delay() {
    return 100;
  }

}
