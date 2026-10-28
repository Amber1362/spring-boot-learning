package com.project.gatekeeper.management.controller;

import com.project.gatekeeper.management.dto.ResponseDto.RouteResponse;
import com.project.gatekeeper.management.service.RouteService;
import org.apache.catalina.LifecycleState;
import org.aspectj.weaver.patterns.ArgsPointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gateway/routes")
public class GatewayConfigurationController {

    private final RouteService routeService;

    public GatewayConfigurationController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ResponseEntity<List<RouteResponse>> getActiveRoutes() {
        return ResponseEntity.ok(routeService.findAllActiveForGateway());
    }
}
