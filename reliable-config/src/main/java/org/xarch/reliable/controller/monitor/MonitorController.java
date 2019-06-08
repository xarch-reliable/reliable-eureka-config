package org.xarch.reliable.controller.monitor;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class MonitorController {

	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Value("${server.port}")
	private String port;
	
	/**
	 * 模拟monitor 远程 git端 webhooks push 触发POST请求自动刷新
	 * 
	 */
	@RequestMapping("/refresh")
	public String refresh() {
		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = "http://localhost:" + port + "/actuator/bus-refresh";
		HttpPost httpPost = new HttpPost(url); // 执行请求
		try {
			httpClient.execute(httpPost);
			logger.info("MonitorController::refresh() [success]");
			return "refresh success";
		} catch (IOException e) {
			logger.warn("MonitorController::refresh() [error]");
			e.printStackTrace();
		}
		return "refresh error";
	}

	@RequestMapping("/refresh/new")
	public Mono<String> newrefresh() {
		return WebClient.create().post().uri(uriBuilder -> {
			return uriBuilder.scheme("http").host("localhost").port(port).path("/actuator/bus-refresh").build();
		}).contentType(MediaType.APPLICATION_JSON_UTF8).retrieve().bodyToMono(String.class).doOnSuccess(s -> {
			logger.info("MonitorController::newrefresh() [success]");
		}).doOnError(e -> {
			logger.warn("MonitorController::newrefresh() [error]");
		}).onErrorReturn("refresh-new error");
	}
}
