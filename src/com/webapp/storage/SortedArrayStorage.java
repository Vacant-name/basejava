package com.webapp.storage;

import com.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public int size() {
        return sizeStorage;
    }

    public void save(Resume resume) {
        if (sizeStorage == storage.length) {
            System.out.println("storage is full");
        } else {
            int index = searchIndex(resume.getUuid());

            System.out.println(index + " storage already contains this resume");
            storage[sizeStorage] = resume;
            sizeStorage++;
            System.out.println(resume + " resume saved");
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

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, sizeStorage);
    }

    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, searchKey);
    }

}
