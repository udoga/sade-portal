package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.SessionError;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.SessionRepo;
import com.sadesoft.portal.core.repo.UserRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SessionService {
    public final Duration sessionTimeout;
    private final SessionRepo sessionRepo;
    private final UserRepo userRepo;

    public SessionService(Duration sessionTimeout, SessionRepo repo, UserRepo userRepo) {
        this.sessionTimeout = sessionTimeout;
        this.sessionRepo = repo;
        this.userRepo = userRepo;
    }

    public List<Session> list() {
        return sessionRepo.list().stream().map(s -> s.withToken(null)).collect(Collectors.toList());
    }

    public Session read(String id) {
        return sessionRepo.read(id).withToken(null);
    }

    public Session login(User user) {
        for (User u: userRepo.list()) if (u.equals(user.withId(u.id))) return create(u.id, LocalDateTime.now());
        throw new SessionError("Invalid username or password.");
    }

    public void authenticate(String token) {
        authenticate(token, LocalDateTime.now());
    }

    public void authenticate(String token, LocalDateTime time) {
        Session session = find(token);
        if (time.isAfter(session.endTime)) throw new SessionError("Session is expired.");
        if (time.isBefore(session.beginTime)) throw new SessionError("Session is not started.");
    }

    private Session create(String userId, LocalDateTime beginTime) {
        String token = UUID.randomUUID().toString();
        return sessionRepo.create(new Session(null, userId, beginTime, beginTime.plus(sessionTimeout), token));
    }

    private Session find(String token) {
        for (Session s: sessionRepo.list()) if (s.token.equals(token)) return s;
        throw new SessionError((token == null)? "Please login to continue." : "Session not found.");
    }
}
