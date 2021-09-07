package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[sizeStorage] = resume;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[sizeStorage - 1];
        storage[sizeStorage - 1] = null;
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
