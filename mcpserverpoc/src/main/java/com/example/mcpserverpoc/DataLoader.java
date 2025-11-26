package com.example.mcpserverpoc;

import org.springframework.stereotype.Component;

import com.example.mcpserverpoc.model.Email;
import com.example.mcpserverpoc.repo.EmailRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    private final EmailRepository repo;

    public DataLoader(EmailRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void load() {
        if (repo.count() == 0) {
            repo.save(new Email("alice@example.com","bob@example.com","Hello","Hi Bob"));
            repo.save(new Email("alice@example.com","carol@example.com","Update","Status update"));
            repo.save(new Email("dave@example.com","bob@example.com","Question","Quick question"));
            repo.save(new Email("alice@example.com","erin@example.com","Meeting","Let's meet"));
            repo.save(new Email("frank@example.com","bob@example.com","FYI","For your info"));
        }
    }
}
