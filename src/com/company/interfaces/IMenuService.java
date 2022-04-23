package com.company.interfaces;

import com.company.entities.User;

public interface IMenuService {
    void start();
    void logUser();
    User getCurrentUser();
}
