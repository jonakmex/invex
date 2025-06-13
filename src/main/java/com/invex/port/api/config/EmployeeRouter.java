package com.invex.port.api.config;

import com.invex.port.api.handler.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EmployeeRouter {

     @Bean
     public RouterFunction<ServerResponse> employeeRoutes(EmployeeService employeeService) {
         return RouterFunctions.route(
                 GET("/employees"), employeeService::getAllEmployees).andRoute(
                 DELETE("/employees/{id}"),    employeeService::deleteEmployee).andRoute(
                 PUT("/employees/{id}"), employeeService::updateEmployee).andRoute(
                 POST("/employees"), employeeService::createEmployee
         );
     }

    // Additional route definitions can be added here
}
