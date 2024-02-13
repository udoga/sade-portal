package com.sadesoft.portal.core.model;

import java.time.LocalDateTime;
import java.util.Objects;
import com.sadesoft.portal.base.repo.Record;

public class Session implements Record {
    public String id = null;
    public String userId = null;
    public LocalDateTime beginTime = null;
    public LocalDateTime endTime = null;
    public String token = null;

    public Session() {
    }

    public Session(String id, String userId, LocalDateTime beginTime, LocalDateTime endTime, String token) {
        this.id = id;
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Session)) return false;
        Session s = (Session) o;
        return Objects.equals(id, s.id) &&
                Objects.equals(userId, s.userId) &&
                Objects.equals(beginTime, s.beginTime) &&
                Objects.equals(endTime, s.endTime) &&
                Objects.equals(token, s.token);
    }

    @Override
    public String toString() {
        return "Session[" +
                "id=" + id +
                " userId=" + userId +
                " beginTime=" + beginTime +
                " endTime=" + endTime +
                " token=" + token +
                "]";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Session copy() {
        try {
            return (Session) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Session withId(String id) {
        Session session = copy();
        session.id = id;
        return session;
    }

    public Session withUserId(String userId) {
        Session session = copy();
        session.userId = userId;
        return session;
    }

    public Session withBeginTime(LocalDateTime beginTime) {
        Session session = copy();
        session.beginTime = beginTime;
        return session;
    }

    public Session withEndTime(LocalDateTime endTime) {
        Session session = copy();
        session.endTime = endTime;
        return session;
    }

    public Session withToken(String token) {
        Session session = copy();
        session.token = token;
        return session;
    }
}
