package com.project.gatekeeper.gateway.service;

import com.project.gatekeeper.gateway.client.ManagementApiClient;
import com.project.gatekeeper.gateway.dto.ResponseDto.GatewayRoute;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GatewayConfigurationLoader implements ApplicationRunner {

    private final ManagementApiClient managementApiClient;

    public GatewayConfigurationLoader(ManagementApiClient managementApiClient) {
        this.managementApiClient = managementApiClient;
    }

    @Override
    public void run(ApplicationArguments arguments) {
        refresh();
    }

    public void refresh() {
        List<GatewayRoute> routes = managementApiClient.fetchActiveRoutes();

        //demo
        for(GatewayRoute route : routes) {
            System.out.println("Route: " + route.getTargetPathPrefix());
        }
    }
}
