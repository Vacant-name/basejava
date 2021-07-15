package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {

    }

    @Override
    protected int searchIndex(String uuid) {
        return 0;
    }
}
