package sample.database.dataBaseUtil;

import java.util.Date;
import java.util.List;

public interface OnlineShopDAO<T1, T2> {
    List<T1> selectAll();
    T1 selectSpecificByInt(int value);
    T1 selectSpecificByString(T2 value);
    int insert(T1 value);
}
