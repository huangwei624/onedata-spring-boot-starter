package info.onedata.dynamic.datasource.mybatisplus.common.dg;

import java.util.List;

public interface ChildPreservable<T> {

    void setChild(List<T> child);
}
