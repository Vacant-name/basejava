package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

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
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
