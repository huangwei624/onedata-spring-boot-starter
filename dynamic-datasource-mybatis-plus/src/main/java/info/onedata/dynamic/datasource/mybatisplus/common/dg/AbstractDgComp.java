package info.onedata.dynamic.datasource.mybatisplus.common.dg;

import java.util.List;

public abstract class AbstractDgComp<T extends ChildPreservable<T>> {

    public List<T> dg() {
        List<T> nodeList = getNodeList();
        for (int i = 0; i < nodeList.size(); i++) {
            T t = nodeList.get(i);
            dgChild(t);
        }
        return nodeList;
    }

    public T dgChild(T t) {
        List<T> childNode = this.getChildNode(t);
        if (childNode == null || childNode.size() == 0) {
            return t;
        }
        t.setChild(childNode);
        for (int i = 0; i < childNode.size(); i++) {
            T t1 = childNode.get(i);
            dgChild(t1);
        }
        return t;
    }

    protected abstract List<T> getNodeList();

    protected abstract List<T> getChildNode(T t);

}
