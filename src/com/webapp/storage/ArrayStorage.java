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
        if (sizeStorage == storage.length) {
            System.out.println("storage is full");
        } else {
            int index = searchIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println(resume + " storage already contains this resume");
            } else {
                storage[sizeStorage] = resume;
                sizeStorage++;
                System.out.println(resume + " resume saved");
            }
        }
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index == sizeStorage) {
            System.out.println("storage is full");
        } else if (index >= 0) {
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

    public void delete(String uuid) {
        int index = searchIndex(uuid);
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

    private int searchIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
