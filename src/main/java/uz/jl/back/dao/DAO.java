package uz.jl.back.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DAO<T> {

    protected Gson gson = new Gson();

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass())
            .getActualTypeArguments()[0];
    protected List<T> generic_list = new ArrayList<>();

    protected DAO() {
        this.generic_list = load(entityClass);
    }

    private List<T> load(Class<T> clazz) {
        String fileName = clazz.getSimpleName().toLowerCase();

        try (FileReader fileReader = new FileReader("src/main/resources/%s.json".formatted(fileName));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonSTRING = bufferedReader.lines().collect(Collectors.joining());
            return gson.fromJson(jsonSTRING, TypeToken.getParameterized(List.class, clazz).getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        String fileName = entityClass.getSimpleName().toLowerCase();
        try (FileWriter fileWriter = new FileWriter("src/main/resources/%s.json".formatted(fileName))) {
            fileWriter.write(gson.toJson(this.generic_list));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
