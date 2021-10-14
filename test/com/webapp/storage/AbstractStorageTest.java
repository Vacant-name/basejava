package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public abstract class AbstractStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 =  new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3) ;

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4) ;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete((UUID_4));
        assertSize(3);
        storage.get(UUID_4);
    }

    @Test
    public void size() {
        assertSize(4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertGet(newResume);
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(4, array.length);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_3, array[2]);
        assertEquals(RESUME_4, array[3]);
    }

    @Test (expected = ExistStorageException.class)
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

//    @Test (expected = AssertionError.class)
//    public void saveOverflow() throws Error {
//        try {
//            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
//                storage.save(new Resume());
//            }
//        } catch (StorageException e) {
//            Assert.fail();
//        }
//        storage.save(new Resume());
//    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int sizeStorage) {
        assertEquals(sizeStorage, storage.size());
    }
}