# 流式微服务框架Flower

![Build Status](https://travis-ci.org/yaolinxue/flower.svg?branch=master)
[![codecov](https://codecov.io/gh/leeyazhou/test/branch/master/graph/badge.svg)](https://codecov.io/gh/leeyazhou/test)
[![Percentage of issues still open](http://isitmaintained.com/badge/open/zhihuili/flower.svg)](http://isitmaintained.com/project/zhihuili/flower "Percentage of issues still open")
![license](https://img.shields.io/github/license/zhihuili/flower.svg)
[![star this repo](http://githubbadges.com/star.svg?user=zhihuili&repo=flower&style=flat)](https://github.com/zhihuili/flower)
[![fork this repo](http://githubbadges.com/fork.svg?user=zhihuili&repo=flower&style=flat)](https://github.com/zhihuili/flower/fork)

Flower是一个构建在Akka上的流式微服务框架，开发者只需要针对每一个细粒度的业务功能开发一个Service服务，并将这些Service按照业务流程进行可视化编排，即可得到一个反应式系统
* 即时响应：服务流程的调用者可以得到即时响应，无需等待整个Service流程执行完毕（Flower也支持调用者阻塞，等待整个Service流程执行完毕，得到返回结果）；Service之间无调用阻塞，即时响应。
* 回弹性：当Service失效、服务器失效，系统能够进行自修复，依然保持响应，不会出现系统崩溃。
* 弹性：能够对调用负载压力做出响应，能够自动进行资源伸缩适应负载压力，能够根据系统负载能力控制请求的进入速度（回压）。
* 消息驱动：Service之间通过消息驱动，完成服务流程，Service之间没有任何调用耦合，唯一的耦合就是消息，前一个Service的返回值，必须是后一个Service的输入参数，Flower框架负责将前一个Service的返回值封装成一个消息，发送给后一个Service。

**Flower框架使得开发者无需关注反应式编程细节，即可得到一个反应式系统。**

-----------

* [Flower响应式编程Quick Start](/docs/quick_start.md)

* [Flower应用指南](/docs/program_guide.md)

* [Flower框架设计](/docs/design.md)
* [Flower分布式开发](/docs/distribution_design.md)
