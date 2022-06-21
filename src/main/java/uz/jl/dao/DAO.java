package uz.jl.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.jl.domains.User;
import uz.jl.enums.Role;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public class DAO<T> {

    protected Gson gson = new Gson();
    public static User session = null;
    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass())

            .getActualTypeArguments()[0];
    protected final List<T> generic_list = load();

    private List<T> load() {
        String fileName = entityClass.getSimpleName().toLowerCase();
        try (FileReader fileReader = new FileReader("src/main/resources/%s.json".formatted(fileName));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonSTRING = bufferedReader.lines().collect(Collectors.joining());
            return gson.fromJson(jsonSTRING, new TypeToken<>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        String fileName = entityClass.getSimpleName().toLowerCase();
        try (FileWriter fileWriter = new FileWriter("src/main/resources/%s.json".formatted(fileName))) {
            fileWriter.write(gson.toJson(generic_list));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
