
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[3];
    int resumeNumbers;

    void clear() {
        for (int i = 0; i < resumeNumbers; i++) { // обнуляем каждую занятую ячейку storage
            storage[i] = null;
        }
        resumeNumbers = 0;
    }

    void save(Resume r) {
        storage[resumeNumbers] = r; // вставляем в пустую ячейку резюме
        resumeNumbers++;
    }

    Resume get(String uuid) {
        int i = 0;

        while (i + 1 < resumeNumbers) {
            if (storage[i].uuid != uuid) {
                i++;
            } else return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;

        if (storage[i].uuid != uuid) {
            for (int j = 0; j <= resumeNumbers; j++) {
                i = j;
            }
        }
        storage[i] = null;

        while (i + 1 != resumeNumbers) { // сдвигаем все резюме на одну позицию влево на место удаленного
            storage[i] = storage[i + 1];
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, resumeNumbers);
    }

    int size() {
        return resumeNumbers;
    }
}
