package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int sizeStorage;
    private int index = 0;

    public void searchIndex(Resume resume) {

        for (int i = 0; i <= sizeStorage; i++) {
            if (sizeStorage == storage.length) {
                System.out.println("storage is full");
                index = -1;
                break;
            }
            if (storage[i] == null) {
                index = i;
                break;
            }
            if (storage[i].getUuid().equals(resume.getUuid())) {
                System.out.println(resume + " storage already contains this resume");
                index = i;
                break;
            }
        }
    }
    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }
    public void save(Resume resume) {
        searchIndex(resume);

        storage[index] = resume;
        sizeStorage++;
        System.out.println(resume + " resume was saved");
    }
    public void update(Resume resume) {
        searchIndex(resume);

        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i] = resume;
                System.out.println(resume + " resume was updated");
                break;
            }
            if (i > sizeStorage) {
                System.out.println(resume + " resume not found");
            }
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
