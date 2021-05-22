package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeStorage;

    public void clear() {
        // обнуляем каждую занятую ячейку storage
        for (int i = 0; i < sizeStorage; i++) {
            storage[i] = null;
        }
        sizeStorage = 0;
    }

    public void save(Resume resume) {
        boolean isEmpty = false;

        while (!isEmpty) {
            if (sizeStorage == storage.length) {
                System.out.println(resume + " storage is full");
            } else
                for (int i = 0; i <= sizeStorage; i++) {
                    if (storage[i] == null) {
                        break;
                    } else if (storage[i].getUuid().equals(resume.getUuid())) {
                        System.out.println(resume + " storage already contains this resume");
                        break;
                    }
                }
            isEmpty = true;
        }

        int i = 0;

        while (storage[i] != null | i <= sizeStorage ) {
            sizeStorage++;
        }
        storage[i] = resume;
        System.out.println(resume + " resume was saved");
    }

    public void update(Resume resume) {
        int i = 0;

        while (i < sizeStorage) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i] = resume;
                System.out.println(resume + " resume was updated");
                break;
            }
            i++;
        }

        if (i > sizeStorage) {
            System.out.println(resume + " resume not found");
        }
    }

    public Resume get(String uuid) {
        int i = 0;

        while (i < sizeStorage) {
            if (!storage[i].getUuid().equals(uuid)) {
                i++;
            } else return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        int i = 0;

        while (i < sizeStorage) {
            if (storage[i].getUuid().equals(uuid)) {
                // резюме которое хотим удалить заменятся на резюме стоящее последнем в хранилище
                storage[i] = storage[sizeStorage];
                sizeStorage--;
                System.out.println(uuid + " was deleted");
                break;
            } else
                i++;
        }

        if (i > sizeStorage) {
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
