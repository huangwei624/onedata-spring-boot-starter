package info.onedata.dynamic.datasource.mybatisplus.common.dg.test;

import com.alibaba.fastjson.JSONObject;
import info.onedata.dynamic.datasource.mybatisplus.common.dg.AbstractDgComp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDg extends AbstractDgComp<Category> {

    private static ArrayList<Category> categories = new ArrayList<>();

    static {
        categories.add(new Category(1, "shucai", -1));
        categories.add(new Category(2, "xihongshi", 1));
        categories.add(new Category(3, "tudou", 1));
        categories.add(new Category(4, "shuiguo", -1));
        categories.add(new Category(5, "xigua", 4));
        categories.add(new Category(6, "xiangjiao", 4));
    }

    @Override
    protected List<Category> getNodeList() {
        return categories.stream().filter(item -> item.getParentId().equals(-1)).collect(Collectors.toList());
    }

    @Override
    protected List<Category> getChildNode(Category category) {
        return categories.stream().filter(item -> item.getParentId().equals(category.getId())).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        CategoryDg categoryDg = new CategoryDg();
        categoryDg.dg();
        System.out.println(JSONObject.toJSONString(categoryDg.getNodeList()));
    }

}
