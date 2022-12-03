package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidCredentialsError;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.SessionRepo;
import com.sadesoft.portal.core.repo.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SessionService {
    private final SessionRepo sessionRepo;
    private final UserRepo userRepo;

    public SessionService(SessionRepo repo, UserRepo userRepo) {
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
        for (User u: userRepo.list()) if (u.equals(user.withId(u.id))) return create(u.id);
        throw new InvalidCredentialsError();
    }

    public void authenticate(String token) {
        System.out.println("Authenticating: " + token);
    }

    private Session create(String userId) {
        LocalDateTime now = LocalDateTime.now();
        String token = UUID.randomUUID().toString();
        return sessionRepo.create(new Session(null, userId, now, now.plusHours(1), token));
    }
}
