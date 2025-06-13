package com.invex.port.api.config;

import com.invex.port.api.handler.EmployeeService;
import com.invex.port.api.handler.LoginHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EmployeeRouter {

     @Bean
     @RouterOperations({
             @RouterOperation(
                     path = "/employees",
                     produces = { "application/json" },
                     method = RequestMethod.GET,
                     beanClass = EmployeeService.class,
                     beanMethod = "getAllEmployees",
                     operation = @Operation(
                             operationId = "getAllEmployees",
                             summary = "Get all employees",
                             responses = {
                                     @ApiResponse(
                                             responseCode = "200",
                                             description = "List of employees",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "success",
                                                             summary = "Successful response",
                                                             value = "[{\"id\":1,\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"},{\"id\":2,\"name\":\"Bob\",\"surname\":\"Brown\",\"lastName\":\"Williams\",\"age\":28,\"gender\":\"MALE\",\"birthDate\":\"1996-03-22\",\"position\":\"Analyst\"},{\"id\":3,\"name\":\"Carol\",\"surname\":\"Davis\",\"lastName\":\"Miller\",\"age\":35,\"gender\":\"FEMALE\",\"birthDate\":\"1989-07-10\",\"position\":\"Manager\"},{\"id\":4,\"name\":\"David\",\"surname\":\"Wilson\",\"lastName\":\"Moore\",\"age\":40,\"gender\":\"MALE\",\"birthDate\":\"1984-05-05\",\"position\":\"Lead\"},{\"id\":5,\"name\":\"Eve\",\"surname\":\"Taylor\",\"lastName\":\"Anderson\",\"age\":25,\"gender\":\"FEMALE\",\"birthDate\":\"1999-11-30\",\"position\":\"QA\"}]"
                                                     )
                                             )
                                     ),
                                     @ApiResponse(
                                             responseCode = "500",
                                             description = "Internal server error",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "error",
                                                             summary = "Error response",
                                                             value = "{\"error\":\"Internal Server Error\",\"message\":\"Unexpected error occurred\"}"
                                                     )
                                             )
                                     ),
                                     @ApiResponse(
                                             responseCode = "401",
                                             description = "Invalid credentials",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "error",
                                                             summary = "Authentication failed",
                                                             value = "{\"error\":\"Unauthorized\",\"message\":\"Invalid username or password\"}"
                                                     )
                                             )
                                     )
                             }
                     )
             ),
             @RouterOperation(
                     path = "/employees/{id}",
                     produces = { "application/json" },
                     method = RequestMethod.DELETE,
                     beanClass = EmployeeService.class,
                     beanMethod = "deleteEmployee",
                     operation = @Operation(
                             operationId = "deleteEmployee",
                             summary = "Delete an employee",
                             responses = {
                                     @ApiResponse(responseCode = "204", description = "Employee deleted"),
                                     @ApiResponse(responseCode = "404", description = "Employee not found")
                             }
                     )
             ),
             @RouterOperation(
                     path = "/employees/{id}",
                     produces = { "application/json" },
                     method = RequestMethod.PUT,
                     beanClass = EmployeeService.class,
                     beanMethod = "updateEmployee",
                     operation = @Operation(
                             operationId = "updateEmployee",
                             summary = "Update an employee",
                             requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                     required = true,
                                     content = @Content(
                                             mediaType = "application/json",
                                             examples = @ExampleObject(
                                                     name = "employeeRequest",
                                                     summary = "Sample employee update request",
                                                     value = "{\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"}"
                                             )
                                     )
                             ),
                             responses = {
                                     @ApiResponse(
                                             responseCode = "200",
                                             description = "Employee Updated",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "success",
                                                             summary = "Successful response",
                                                             value = "{\"id\":1,\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"}"
                                                     )
                                             )
                                     ),
                                     @ApiResponse(responseCode = "404", description = "Employee not found"),
                                     @ApiResponse(
                                             responseCode = "401",
                                             description = "Invalid credentials",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "error",
                                                             summary = "Authentication failed",
                                                             value = "{\"error\":\"Unauthorized\",\"message\":\"Invalid username or password\"}"
                                                     )
                                             )
                                     )
                             }
                     )
             ),
             @RouterOperation(
                     path = "/employees",
                     produces = { "application/json" },
                     method = RequestMethod.POST,
                     beanClass = EmployeeService.class,
                     beanMethod = "createEmployee",
                     operation = @Operation(
                             operationId = "createEmployee",
                             summary = "Create a new employee",
                             requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                     required = true,
                                     content = @Content(
                                             mediaType = "application/json",
                                             examples = @ExampleObject(
                                                     name = "employeeRequest",
                                                     summary = "Sample employee creation request",
                                                     value = "{\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"}"
                                             )
                                     )
                             ),
                             responses = {
                                     @ApiResponse(
                                             responseCode = "201",
                                             description = "Employee Created",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "success",
                                                             summary = "Successful response",
                                                             value = "{\"id\":1,\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"}"
                                                     )
                                             )
                                     ),
                                     @ApiResponse(
                                             responseCode = "401",
                                             description = "Invalid credentials",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "error",
                                                             summary = "Authentication failed",
                                                             value = "{\"error\":\"Unauthorized\",\"message\":\"Invalid username or password\"}"
                                                     )
                                             )
                                     )
                             }
                     )
             ),
             @RouterOperation(
                     path = "/employees/batch",
                     produces = { "application/json" },
                     method = RequestMethod.POST,
                     beanClass = EmployeeService.class,
                     beanMethod = "createEmployeeBatch",
                     operation = @Operation(
                             operationId = "createEmployeeBatch",
                             summary = "Create a new employees",
                             requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                     required = true,
                                     content = @Content(
                                             mediaType = "application/json",
                                             examples = @ExampleObject(
                                                     name = "employeeRequest",
                                                     summary = "Sample employee creation request",
                                                     value = "[{\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"}]"
                                             )
                                     )
                             ),
                             responses = {
                                     @ApiResponse(
                                             responseCode = "201",
                                             description = "Employee Created",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "success",
                                                             summary = "Successful response",
                                                             value = "[{\"id\":1,\"name\":\"Alice\",\"surname\":\"Smith\",\"lastName\":\"Johnson\",\"age\":30,\"gender\":\"FEMALE\",\"birthDate\":\"1994-01-15\",\"position\":\"Developer\"}]"
                                                     )
                                             )
                                     ),
                                     @ApiResponse(
                                             responseCode = "401",
                                             description = "Invalid credentials",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "error",
                                                             summary = "Authentication failed",
                                                             value = "{\"error\":\"Unauthorized\",\"message\":\"Invalid username or password\"}"
                                                     )
                                             )
                                     )
                             }
                     )
             ),
             @RouterOperation(
                     path = "/login",
                     produces = { "application/json" },
                     method = RequestMethod.POST,
                     beanClass = LoginHandler.class,
                     beanMethod = "login",
                     operation = @Operation(
                             operationId = "login",
                             summary = "Authenticate user and return JWT token",
                             requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                     required = true,
                                     content = @Content(
                                             mediaType = "application/json",
                                             examples = @ExampleObject(
                                                     name = "loginRequest",
                                                     summary = "Sample login request",
                                                     value = "{\"username\":\"user\",\"password\":\"pass\"}"
                                             )
                                     )
                             ),
                             responses = {
                                     @ApiResponse(
                                             responseCode = "200",
                                             description = "JWT Token",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "success",
                                                             summary = "Successful login",
                                                             value = "{\"token\":\"<jwt_token>\"}"
                                                     )
                                             )
                                     ),
                                     @ApiResponse(
                                             responseCode = "401",
                                             description = "Invalid credentials",
                                             content = @Content(
                                                     mediaType = "application/json",
                                                     examples = @ExampleObject(
                                                             name = "error",
                                                             summary = "Authentication failed",
                                                             value = "{\"error\":\"Unauthorized\",\"message\":\"Invalid username or password\"}"
                                                     )
                                             )
                                     )
                             }
                     )
             )
     })
     public RouterFunction<ServerResponse> employeeRoutes(EmployeeService employeeService, LoginHandler loginHandler) {
         return RouterFunctions.route(
                 GET("/employees"), employeeService::getAllEmployees).andRoute(
                 GET("/employees/search").and(accept(MediaType.APPLICATION_JSON)), employeeService::findEmployeeByName).andRoute(
                 GET("/employees/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeService::findEmployeeById).andRoute(
                 DELETE("/employees/{id}"),    employeeService::deleteEmployee).andRoute(
                 PUT("/employees/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeService::updateEmployee).andRoute(
                 POST("/employees").and(accept(MediaType.APPLICATION_JSON)), employeeService::createEmployee).andRoute(
                 POST("/employees/batch").and(accept(MediaType.APPLICATION_JSON)), employeeService::createEmployeeBatch).andRoute(
                 POST("/login").and(accept(MediaType.APPLICATION_JSON)), loginHandler::login
         );
     }

    // Additional route definitions can be added here
}
