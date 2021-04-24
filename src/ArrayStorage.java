/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int emptyPlace;
    int saveCounterResume;
    int sizeStorage;
    int getAllCounter;
    void clear() {
        while(emptyPlace <= sizeStorage) { // обнуляем каждую занятую ячейку storage
            storage[emptyPlace] = null;
            emptyPlace++;
        }
    }

    void save(Resume r) {
        if(storage[saveCounterResume] == null) { // если в ячейке под индексом saveCounterResume пусто
            storage[saveCounterResume] = r; // вставляем в пустую ячейку резюме
            saveCounterResume++;
        } else {
            System.out.println("Resume storage is full");
        }
    }

     Resume get(String uuid) {
         return storage[saveCounterResume];
    }

    void delete(String uuid) {
        uuid = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        for (int i = 0; i <= sizeStorage; i++) {
            getAllCounter = i;
        }
        return new Resume[getAllCounter];
    }

    int size() {
        while (sizeStorage != saveCounterResume) {
            sizeStorage++;
        }
        return sizeStorage;
    }
}
