package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertIdx =  - index - 1;
        System.arraycopy(storage, insertIdx, storage,insertIdx + 1,sizeStorage - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = sizeStorage - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume expectedPosition = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, sizeStorage, expectedPosition);
    }
}
