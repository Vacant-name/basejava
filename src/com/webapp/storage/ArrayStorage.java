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
        // обнуляем каждую занятую ячейку storage
        Arrays.fill(storage,null);
        sizeStorage = 0;
    }

    public void save(Resume resume) { // not working

        for (int i = 0; i <= sizeStorage; i++) {

            if (sizeStorage == storage.length) {
                System.out.println("storage is full");
                break;
            }
            if (storage[i] == null) {
                storage[i] = resume;
                sizeStorage++;
                System.out.println(resume + " resume was saved");
                break;
            }
            if (storage[i].getUuid().equals(resume.getUuid())) {
                System.out.println(resume + " storage already contains this resume");
                break;
            }
        }
    }

    public void update(Resume resume) {

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
