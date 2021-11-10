package com.webapp.storage;

import static com.webapp.storage.AbstractStorageTest.STORAGE_DIR;
import static org.junit.Assert.*;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}