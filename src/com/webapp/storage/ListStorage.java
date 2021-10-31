package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
//        list.remove(list.get((Integer) searchKey)); //- auto unboxing in get()
//        list.remove(Integer.parseInt(String.valueOf(searchKey))); //- unboxing parseInt 'n valueOf
        list.remove(searchKey.intValue());
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        list.set(searchKey, resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
       return list.get(searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }
}
