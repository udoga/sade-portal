package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.AuthConfig;
import com.sadesoft.portal.base.error.SessionError;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.SessionRepo;
import com.sadesoft.portal.core.repo.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SessionService {
    public final AuthConfig config;
    private final SessionRepo sessionRepo;
    private final UserRepo userRepo;

    public SessionService(AuthConfig config, SessionRepo repo, UserRepo userRepo) {
        this.config = config;
        this.sessionRepo = repo;
        this.userRepo = userRepo;
    }

    public List<Session> list() {
        return sessionRepo.list().stream().map(s -> s.withToken(null)).toList();
    }

    public Session read(String id) {
        return sessionRepo.read(id).withToken(null);
    }

    public Session login(User user) {
        for (User u: userRepo.list())
            if (u.equals(user.withId(u.id).withEncryptedPassword(config.salt)))
                return create(u.id, LocalDateTime.now());
        throw new SessionError("Invalid username or password.");
    }

    public Session logout(String token) {
        Session session = find(token);
        return sessionRepo.update(session.id, session.withEndTime(LocalDateTime.now()));
    }

    public Session authenticate(String token) {
        return authenticate(token, LocalDateTime.now());
    }

    public Session authenticate(String token, LocalDateTime time) {
        if (!config.authenticationEnabled) return new Session();
        Session session = find(token);
        if (time.isAfter(session.endTime)) throw new SessionError("Session expired, please login.");
        if (time.isBefore(session.beginTime)) throw new SessionError("Session is not started yet.");
        return session;
    }

    private Session create(String userId, LocalDateTime beginTime) {
        String token = UUID.randomUUID().toString();
        LocalDateTime endTime = beginTime.plusSeconds(config.sessionTimeout);
        return sessionRepo.create(new Session(null, userId, beginTime, endTime, token));
    }

    private Session find(String token) {
        for (Session s: sessionRepo.list()) if (s.token.equals(token)) return s;
        throw new SessionError((token == null)? "Please login to continue." : "Session not found, invalid token.");
    }
}
