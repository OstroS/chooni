package pl.akiba.frontend.kinds.service;

import java.util.List;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;

public interface KindsService {

    public abstract List<Kind> getAll(User user);

    public abstract void add(Kind kind, User user);
    
    public abstract Kind get(User user, Long kindId);

}