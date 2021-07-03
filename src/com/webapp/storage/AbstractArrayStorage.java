package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    //super class
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int sizeStorage = 0;

    public int size() {
        return sizeStorage;
    }

    public Resume get(String uuid) {

        int index = searchIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume " + uuid + " found");
            return storage[index];
        } else System.out.println("Resume " + uuid + " not found");
        return null;
    }
    protected int searchIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
