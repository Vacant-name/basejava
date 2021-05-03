
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int resumeNumbers;

    void clear() {
        while (resumeNumbers != 0) { // обнуляем каждую занятую ячейку storage
            storage[resumeNumbers] = null;
            resumeNumbers--;
        }
    }

    void save(Resume r) { // тип рюземе содержит строку
        if (storage[resumeNumbers] == null) { // если в ячейке под индексом resumeNumbers пусто
            storage[resumeNumbers] = r; // вставляем в пустую ячейку резюме
            resumeNumbers++;
        } else {
            System.out.println("Resume storage is full");
        }
    }

    Resume get(String uuid) {
        int i = 0;

        if (!uuid.contains("uuid")) {
            System.out.println(uuid + " is incorrect id");
            return null;
        }

        while (storage[i].uuid != uuid) { // содержание информации в i-ом элементе массива
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
        int i = 0;

        while (storage[i].uuid != uuid) { // содержание информации в i-ом элементе массива
            i++;
        }
        storage[i] = null;

        while (storage[i + 1] != null) { // сдвигаем все резюме на одну позицию влево на место удаленного
            storage[i] = storage[i + 1];
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[resumeNumbers];
        allResumes = Arrays.copyOf(storage, resumeNumbers);
        return allResumes;
    }

    int size() {
        return resumeNumbers;
    }
}
