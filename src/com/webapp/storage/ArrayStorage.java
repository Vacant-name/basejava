package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int sizeStorage;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void save(Resume resume) {
        searchIndex(resume, null);

        int index = searchIndex(resume, null);
        if (index >= 0) {
            storage[index] = resume;
            sizeStorage++;
            System.out.println(resume + " resume was saved");
        } else {
            System.out.println(resume + " storage already contains this resume");
        }
    }

    public void update(Resume resume) {
        searchIndex(resume, null);

        int index = searchIndex(resume, null);
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume + " resume was updated");
        } else {
            System.out.println(resume + " resume not found");
        }
    }

    public Resume get(String uuid) {
        searchIndex(null, uuid);

        int index = searchIndex(null, uuid);
        if (index >= 0) {
            return storage[index];
        } else return null;
    }

    public void delete(String uuid) {
        searchIndex(null, uuid);

        int index = searchIndex(null, uuid);
        if (index >= 0) {
            storage[index] = storage[sizeStorage - 1];
            sizeStorage--;
            System.out.println(uuid + " was deleted");
        } else {
            System.out.println(uuid + " resume not found");
        }
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    private int searchIndex(Resume resume, String uuid) {
        for (int i = 0; i <= sizeStorage; i++) {
            if (sizeStorage == storage.length) {
                System.out.println("storage is full");
                break;
            }
            if (storage[i] == null) { // where does it put?
                return i;
            }
            if (resume != null && storage[i].getUuid().equals(resume.getUuid())) {
                return i;
            }
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
