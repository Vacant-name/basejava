package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int sizeStorage;

    public int resumeIndex(Resume resume) {

        for (int i = 0; i <= sizeStorage; i++) {
            if (sizeStorage == storage.length) {
                System.out.println("storage is full");
                break;
            }
            if (storage[i] == null) {
                return i;
            }
            if (storage[i].getUuid().equals(resume.getUuid())) {
                return i;
            }
        }
        return -1;
    }
    public int uuidIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }
    public void save(Resume resume) {
        resumeIndex(resume);

        if (resumeIndex(resume) >= 0) {
            storage[resumeIndex(resume)] = resume;
            sizeStorage++;
            System.out.println(resume + " resume was saved");
        } else {
            System.out.println(resume + " storage already contains this resume");
        }
    }
    public void update(Resume resume) {
        resumeIndex(resume);

        if (resumeIndex(resume) >= 0) {
            storage[resumeIndex(resume)] = resume;
            System.out.println(resume + " resume was updated");
        } else {
            System.out.println(resume + " resume not found");
        }
    }
    public Resume get(String uuid) {
        uuidIndex(uuid);

        if (uuidIndex(uuid) >= 0) {
            return storage[uuidIndex(uuid)];
        } else return null;
    }
    public void delete(String uuid) {
        uuidIndex(uuid);

        if (uuidIndex(uuid) >= 0) {
            storage[uuidIndex(uuid)] = storage[sizeStorage - 1];
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
}
