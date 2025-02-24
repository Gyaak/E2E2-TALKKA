package com.talkka.server.bus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talkka.server.bus.dto.BusRouteRespDto;
import com.talkka.server.bus.exception.BusRouteNotFoundException;
import com.talkka.server.bus.service.BusRouteService;
import com.talkka.server.common.dto.ErrorRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bus/route")
public class BusRouteController implements BusRouteApi {
	private final BusRouteService busRouteService;

	@Override
	@GetMapping("")
	public ResponseEntity<List<BusRouteRespDto>> getRoutes(
		@RequestParam(value = "search", required = false) String routeName
	) {
		List<BusRouteRespDto> routeList;

		if (routeName != null) {
			routeList = busRouteService.getRoutesByRouteName(routeName);
		} else {
			routeList = busRouteService.getRoutes();
		}

		return ResponseEntity.ok(routeList);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getRouteById(@PathVariable("id") Long routeId) {
		ResponseEntity<?> response;
		try {
			response = ResponseEntity.ok(busRouteService.getRouteById(routeId));
		} catch (BusRouteNotFoundException exception) {
			response = ResponseEntity.badRequest().body(ErrorRespDto.of(exception));
		}
		return response;
	}
}
