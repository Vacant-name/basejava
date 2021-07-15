package com.webapp.storage;

import com.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

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

    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, searchKey);
    }

}
