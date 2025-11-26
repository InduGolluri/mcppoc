package com.example.mcpserverpoc;

import java.util.List;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mcpserverpoc.service.EmailToolService;

@SpringBootApplication
public class McpserverpocApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpserverpocApplication.class, args);
	}
	
	@Bean
	public List<ToolCallback> toolCallbacks(EmailToolService emailToolService){
		return List.of(ToolCallbacks.from(emailToolService));
	}

}
