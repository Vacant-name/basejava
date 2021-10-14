package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract boolean isExist(Object searchKey);


    public void save(Resume resume) throws ExistStorageException {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
        System.out.println(resume + " resume saved");
    }

    public void delete(String uuid) throws NotExistStorageException {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
        System.out.println(uuid + " was deleted");
    }

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
        System.out.println(resume + " was updated");
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
