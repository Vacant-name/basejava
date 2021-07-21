package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {

        System.out.println(resume.hashCode());
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int searchIndex(String uuid) {
        Resume expectedPosition = new Resume();
        expectedPosition.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, expectedPosition);
    }

}
