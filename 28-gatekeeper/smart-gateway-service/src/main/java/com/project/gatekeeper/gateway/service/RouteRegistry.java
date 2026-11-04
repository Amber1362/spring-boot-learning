package com.project.gatekeeper.gateway.service;

import com.project.gatekeeper.gateway.dto.ResponseDto.CompiledRoute;
import com.project.gatekeeper.gateway.dto.ResponseDto.GatewayRoute;
import org.springframework.stereotype.Service;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RouteRegistry {

    private final PathPatternParser parser = new PathPatternParser();

    private final AtomicReference<List<CompiledRoute>> routes =
            new AtomicReference<>(Collections.emptyList());

    public void replaceAll(List<GatewayRoute> gatewayRoutes) {
        List<CompiledRoute> compiledRoutes = new ArrayList<>();

        if(gatewayRoutes != null) {
            for (GatewayRoute route : gatewayRoutes) {
                PathPattern pattern = parser.parse(route.getPathPattern());

                compiledRoutes.add(new CompiledRoute(route, pattern,
                        sourcePrefix(route.getPathPattern())));
            }
        }

        Collections.sort(compiledRoutes, new Comparator<CompiledRoute>() {
            @Override
            public int compare(CompiledRoute first, CompiledRoute second) {
                return PathPattern.SPECIFICITY_COMPARATOR.compare(
                        first.getPathPattern(), second.getPathPattern()
                );
            }
        });

        routes.set(Collections.unmodifiableList(compiledRoutes));
    }

    private String sourcePrefix(String pathPattern) {
        if(pathPattern.endsWith("/**")) {
            return pathPattern.substring(0, pathPattern.length() - 3);
        }
        return pathPattern;
    }
}
