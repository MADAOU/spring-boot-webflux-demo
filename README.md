# spring-boot-webflux-demo

## Servlet Stack Web Application存在的问题
一个请求需要一个线程处理，在处理结束之前，线程一直被占用，即使处理因为阻塞而等待，也不会释放线程。
网络IO、外部服务调用、数据库访问等典型的阻塞处理时，假如需要从慢速的网络缓慢地read数据，这样会一直低效的占用着线程。
这样一来，面对大量的请求就需要的同样大量的线程来处理。
线程的生成，本身也是需要消耗内存资源的（最起码需要保障每个线程的堆栈空间）。
另外，CPU在切换线程时，也需要进行上下文切换。

## 编译
./gradlew build

## 容器
docker build -f ./docker/springboot -t springboot .
docker build -f ./Dockerfile-remote -t webflux-demo-remote .
docker build -f ./Dockerfile-proxy -t webflux-demo-proxy .
docker network create -d bridge tokyobs
docker run --name=webflux-demo-remote --network=tokyobs -d -p 8090:8090 webflux-demo-remote
docker run --name=webflux-demo-proxy --network=tokyobs -d -p 8080:8080 -p 9090:9090 webflux-demo-proxy

## Gradle启动webflux-demo-proxy
gradlew bootRun -Pargs="--server.port=8090"
 
## Gradle启动webflux-demo-remote
gradlew bootRun -Pargs="--server.port=8080,--services.user.remote.enable=1"

## 性能测试工具gatling
https://github.com/lkishalmi/gradle-gatling-plugin
https://github.com/nuxeo/gatling-report

./gradlew gatlingRun
./gradlew gatlingRun-ApiGatlingSimulationTest
./gradlew gatlingRun-RxApiGatlingSimulationTest

## 网络访问的命令
 
curl -X GET -i http://localhost:8080/users
 
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/users --data '{
"id":"user01",
"name":"gutianle",
age:21
}'

curl -X PUT -H 'Content-Type: application/json' -i http://localhost:8080/users/user01 --data '{
"id":"user01",
"name":"gutianle",
age:21
}'

curl -X GET -i http://localhost:8080/users/user01

curl -X DELETE -i http://localhost:8080/users/user01

curl -X GET -H 'Content-Type: application/json' -i http://localhost:8080/users/query --data '{
"name":"gulianle"
}'

curl -X GET -i 'http://localhost:8080/users/query?name=gutianle'

docker pull amazoncorretto:11
yum update
yum install -y procps


ps  -efL | grep -E "java|PID"
jcmd {PID} Thread.print | grep nid


## 参考文章
 使用 Spring 5 的 WebFlux 开发反应式 Web 应用
 https://www.ibm.com/developerworks/cn/java/spring5-webflux-reactive/index.html
 
 使用 Reactor 进行反应式编程
 https://www.ibm.com/developerworks/cn/java/j-cn-with-reactor-response-encode/index.html
 
 Reactor 实例解析
 https://www.infoq.cn/article/reactor-by-example
 
 Spring Boot 2.0 - WebFlux framework
 https://mp.weixin.qq.com/s?__biz=MzAxODcyNjEzNQ==&mid=2247484281&idx=1&sn=5ec89d247cd5949bee81b808a5a89d63&chksm=9bd0aee1aca727f77a08b44a1b1175991210551237cc039faf2ddf98209e9360602e389bfb1f&scene=27#wechat_redirect
 
 Spring WebFlux の概要を理解する
 https://www.kimullaa.com/entry/2018/04/25/214708
 
 SpringBoot2のBlocking Web vs Reactive WebについてLTしてきた
 https://bufferings.hatenablog.com/entry/2018/03/27/233152
 
 Web on Reactive Stack
 https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-httphandler
 
 Spring WebFlux 要革了谁的命？
 https://mp.weixin.qq.com/s?__biz=MzAxOTc0NzExNg==&mid=2665515772&idx=1&sn=205b10cfb2241cfe1b16c7f832b48197&chksm=80d672bfb7a1fba99cbbbc423984da5c9034fffd8ca12f6fc7098fe5d69c6d1e39152de45cbd&scene=27#wechat_redirect
 
 https://github.com/reactor/reactor-core
 
 
## 其他说明
#fd の確認 (default: 1048576)
ulimit -n

#fd の設定
ulimit -n 50000


 
 
