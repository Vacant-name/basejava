package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int sizeStorage;

    public int searchIndex(Resume resume) {

        for (int i = 0; i <= sizeStorage; i++) {
            if (sizeStorage == storage.length) {
                System.out.println("storage is full");
                break;
            }
            if (storage[i] == null) {
                return i;
            }
            if (storage[i].getUuid().equals(resume.getUuid())) {
                System.out.println(resume + " storage already contains this resume");
                return i;
            }
        }
        return sizeStorage++;
    }
    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }
    public void save(Resume resume) {
        searchIndex(resume);

        if (sizeStorage == storage.length) {
            System.out.println("storage is full");
        }
        if (storage[searchIndex(resume)] == null  | searchIndex(resume) == sizeStorage++) {
            storage[searchIndex(resume)] = resume;
            sizeStorage++;
            System.out.println(resume + " resume was saved");
        }
    }
    public void update(Resume resume) {
        searchIndex(resume);

        if (storage[searchIndex(resume)] == resume) {
            storage[searchIndex(resume)] = resume;
            System.out.println(resume + " resume was updated");
        } else {
            System.out.println(resume + " resume not found");
        }
    }
    public Resume get(String uuid) {

        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
    public void delete(String uuid) {

        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[sizeStorage - 1];
                sizeStorage--;
                System.out.println(uuid + " was deleted");
                break;
            }
            if (i > sizeStorage) {
                System.out.println(uuid + " resume not found");
            }
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
