# Spring Cloud Consul

## Consul Config

[로컬 Consul Web-UI](http://localhost:8500) 에서 KEY/VALUE를 아래와 같이 등록요망 

*config/application.yml*
```yml
management:
  endpoints:
    web:
      base-path: /actuator    # (1)

spring:
  cloud:
    consul:
      discovery:
        instance-id: ${spring.application.name}-${spring.application.instance-id:${random.value}}   # (1)
        health-check-path: ${management.endpoints.web.base-path}/health                             # (2)
        health-check-interval: 5s                                                                   # (3)

server:
  port: 0   # (4)
  
feign:
  hystrix:
    enabled: true
```
