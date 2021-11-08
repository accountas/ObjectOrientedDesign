package lt.mif.ood.persistence;

import java.util.ArrayList;

public interface EntityListProvider<T> {
    ArrayList<T> load();
    void save(Iterable<T> entities);
}
