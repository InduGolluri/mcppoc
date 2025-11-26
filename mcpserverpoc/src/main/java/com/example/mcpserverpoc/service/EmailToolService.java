package com.example.mcpserverpoc.service;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcpserverpoc.repo.EmailRepository;

// NOTE: replace import lines above with correct package for @McpTool in your version
// The canonical annotation examples: @McpTool and @McpToolParam (see Spring AI docs)

@Service
public class EmailToolService {

	private final EmailRepository repo;

	private final OpenAiChatModel llm;
	
    @Autowired
    public EmailToolService(EmailRepository repo) {
        this.repo = repo;
        
        OpenAiApi api = new OpenAiApi(System.getenv("OPENAI_API_KEY"));
        this.llm = new OpenAiChatModel(api);
    }

    

    @Tool(
        name = "emailInsight",
        description = "Returns LLM-generated insight about emails sent by a specific sender."
    )
    public String emailInsight(
            @ToolParam(description = "Sender email", required = true) String sender,
            @ToolParam(description = "Your question for the LLM", required = false) String question
    ) {

        long count = repo.countBySender(sender);

        String prompt = """
            You are an intelligent email assistant.
            Sender: "%s"
            Emails sent: %d
            User question: %s
            Provide a conversational and helpful insight.
        """.formatted(sender, count, question);

        return llm.call(prompt);
    }
    
//   @Tool(name = "countEmailsBySender", description = "Count emails sent by a given sender address.")
//    public long countEmailsBySender(
//    		@ToolParam(description = "Sender email address", required = true) String sender) {
//
//        if (sender == null || sender.isBlank()) {
//            return 0L;
//        }
//        return repo.countBySender(sender.trim().toLowerCase());
//    }
}

