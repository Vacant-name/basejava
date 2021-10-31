package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int sizeStorage = 0;

    @Override
    protected void doSave(Resume resume, Integer index) {
        if (sizeStorage == storage.length) {
            throw new StorageException("Storage is overflowed", resume.getUuid());
        } else {
            insertElement(resume, index);
            sizeStorage++;
            System.out.println(resume + " resume was saved");
        }
    }

    @Override
    protected void doDelete(Integer index) {
        fillDeletedElement(index);
        storage[sizeStorage - 1] = null;
        sizeStorage--;
        System.out.println("uuid" + (index + 1) + " was deleted");
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[(Integer) index] = resume;
        System.out.println("uuid" + (((Integer) index) + 1) + " was updated");
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    public int size() {
        return sizeStorage;
    }

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, sizeStorage));
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer getSearchKey(String uuid);
}


