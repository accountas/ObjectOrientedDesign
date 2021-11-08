package lt.mif.ood.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lt.mif.ood.models.User;
import lt.mif.ood.persistence.EntityListProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserListGsonProvider implements EntityListProvider<User> {
    private final String filename;

    public UserListGsonProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public ArrayList<User> load() {
        try (var reader = new JsonReader(new FileReader(filename))){
            var gson = new Gson();
            var userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            return gson.fromJson(reader, userListType);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Iterable<User> users) {
        try (var writer = new FileWriter(filename)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
