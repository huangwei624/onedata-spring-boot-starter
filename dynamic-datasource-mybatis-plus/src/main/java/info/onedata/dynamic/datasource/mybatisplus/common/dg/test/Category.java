package info.onedata.dynamic.datasource.mybatisplus.common.dg.test;

import info.onedata.dynamic.datasource.mybatisplus.common.dg.ChildPreservable;

import java.util.List;

public class Category implements ChildPreservable<Category> {

    private Integer id;
    private String name;
    private Integer parentId;

    private List<Category> categories;

    public Category(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public void setChild(List<Category> child) {
        this.categories = child;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
