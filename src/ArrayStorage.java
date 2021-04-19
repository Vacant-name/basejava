/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int saveCounterResume;
    int sizeStorage;

    void clear() {
        for (Resume resume : storage) { // обнуляем каждую ячейку storage
            resume = null;
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
        return null;
    }

    void delete(String uuid) {
        uuid = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[sizeStorage];
    }

    int size() {
        while (sizeStorage != saveCounterResume) {
            sizeStorage++;
        }
        return sizeStorage;
    }
}
