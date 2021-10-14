package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int sizeStorage = 0;

    @Override
    protected void doSave(Resume resume, Object index) {
        if (sizeStorage == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, (Integer) index);
            sizeStorage++;
            System.out.println(resume + " resume saved");
        }
    }

    @Override
    protected void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[sizeStorage - 1] = null;
        sizeStorage--;
        System.out.println(index + " was deleted");
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
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

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer getSearchKey(String uuid);
}


