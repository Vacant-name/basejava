package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    //sub class
    @Override
    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    @Override
    public void update(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println(r + " resume updated");
        } else
            System.out.println(r + " resume not found");
    }

    @Override
    public void save(Resume r) {
        if (sizeStorage == storage.length) {
            System.out.println("storage is full");
        } else {
            int index = searchIndex(r.getUuid());
            if (index >= 0) {
                System.out.println(r + " storage already contains this resume");
            } else {
                storage[sizeStorage] = r;
                sizeStorage++;
                System.out.println(r + " resume saved");
            }
        }
    }

    @Override
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

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    @Override
    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, searchKey);
    }

}
