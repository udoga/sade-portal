package com.sadesoft.portal.core.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.sadesoft.portal.base.error.InvalidCredentialsError;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.SessionRepo;
import com.sadesoft.portal.core.repo.UserRepo;

public class SessionService {
    private final SessionRepo sessionRepo;
    private final UserRepo userRepo;

    public SessionService(SessionRepo repo, UserRepo userRepo) {
        this.sessionRepo = repo;
        this.userRepo = userRepo;
    }

    public List<Session> list() {
        return sessionRepo.list();
    }

    public Session read(String id) {
        return sessionRepo.read(id);
    }

    public Session login(User user) {
        for (User u: userRepo.list())
            if (user.username.equals(u.username) && user.password.equals(u.password))
                return createSession(u.id);
        throw new InvalidCredentialsError();
    }

    private Session createSession(String userId) {
        LocalDateTime now = LocalDateTime.now();
        String token = UUID.randomUUID().toString();
        return sessionRepo.create(new Session(null, userId, now, now.plusHours(1), token));
    }
}
