package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 2;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int sizeStorage = 0;

    public void save(Resume resume) throws ExistStorageException {
        int index = searchIndex(resume.getUuid());
        if (index > 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (sizeStorage == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, index);
            sizeStorage++;
            System.out.println(resume + " resume saved");
        }
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[sizeStorage - 1] = null;
            sizeStorage--;
            System.out.println(uuid + " was deleted");
        }
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
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

    protected abstract int searchIndex(String uuid);
}


