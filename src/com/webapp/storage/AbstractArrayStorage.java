package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int sizeStorage = 0;

    public abstract void save(Resume resume);

    public abstract void update(Resume resume);

    public abstract void delete(String uuid);

    protected abstract int searchIndex(String uuid);

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume " + uuid + " found");
            return storage[index];
        } else System.out.println("Resume " + uuid + " not found");
        return null;
    }

    public int size() {
        return sizeStorage;
    }

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, sizeStorage);
    }
}


