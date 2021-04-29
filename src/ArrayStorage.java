import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Arrays;


/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int resumeNumbers;

    void clear() {
        while(resumeNumbers != 0) { // обнуляем каждую занятую ячейку storage
            storage[resumeNumbers] = null;
            resumeNumbers--;
        }
    }

    void save(Resume r) { // тип рюземе содержит строку
        if(storage[resumeNumbers] == null) { // если в ячейке под индексом resumeNumbers пусто
            storage[resumeNumbers] = r; // вставляем в пустую ячейку резюме
            resumeNumbers++;
        } else {
            System.out.println("Resume storage is full");
        }
    }

    Resume get(String uuid) {
        int i = 0;
        if(storage[i] != null) {

        }
        return storage[i];
    }

    void delete(String uuid) {
        uuid = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        for(int i = 0; i < resumeNumbers; i++) {
            System.out.println(storage[i]);
        }
        return null;
    }

    int size() {
        return resumeNumbers;
    }
}
