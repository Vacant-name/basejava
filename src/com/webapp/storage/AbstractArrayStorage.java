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

    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index > 0) {
            System.out.println(resume + " storage already contains this resume");
        } else if (sizeStorage == storage.length) {
            System.out.println("storage is full");
        } else {
            insertElement(resume, index);
            sizeStorage++;
            System.out.println(resume + " resume saved");
        }
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            System.out.println(uuid + " resume not found");
        } else {
            fillDeletedElement(index);
            storage[sizeStorage - 1] = null;
            sizeStorage--;
            System.out.println(uuid + " was deleted");
        }
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume + " resume updated");
        } else
            System.out.println(resume + " resume not found");
    }

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

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract int searchIndex(String uuid);
}


