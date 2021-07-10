package com.webapp.storage;

import com.webapp.model.Resume;

public interface Storage {

    void clear();

    void save(Resume r);

    void update(Resume r);

    void delete(String uuid);

    int size();

    Resume get(String uuid);

    Resume[] getAll();


}
