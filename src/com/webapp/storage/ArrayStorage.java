package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

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
        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume + " resume updated");
        } else
            System.out.println(resume + " resume not found");
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

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume " + uuid + " found");
            return storage[index];
        } else System.out.println("Resume " + uuid + " not found");
        return null;
    }

    protected int searchIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
