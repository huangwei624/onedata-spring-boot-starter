package info.onedata.dynamic.datasource.mybatisplus.common.dg.test;

import com.alibaba.fastjson.JSONObject;
import info.onedata.dynamic.datasource.mybatisplus.common.dg.AbstractDgComp;
import info.onedata.dynamic.datasource.mybatisplus.common.dg.ChildPreservable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceTypeDG extends AbstractDgComp<ServiceTypeDG.ServiceType> {

    private static List<ServiceType> serviceTypes = new ArrayList<>();

    static {
        serviceTypes.add(new ServiceType(1, "订舱", -1));
        serviceTypes.add(new ServiceType(2, "VGM", 1));
        serviceTypes.add(new ServiceType(3, "预配舱单", 1));
        serviceTypes.add(new ServiceType(7, "fankong舱单", 3));
        serviceTypes.add(new ServiceType(4, "拖车", -1));
        serviceTypes.add(new ServiceType(5, "报关", 4));
        serviceTypes.add(new ServiceType(6, "保险", 4));
    }


    @Override
    protected List<ServiceType> getNodeList() {
        return serviceTypes.stream().filter(item -> item.getParentId().equals(-1)).collect(Collectors.toList());
    }

    @Override
    protected List<ServiceType> getChildNode(ServiceType serviceType) {
        return serviceTypes.stream().filter(item -> item.getParentId().equals(serviceType.getId())).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        ServiceTypeDG serviceTypeDG = new ServiceTypeDG();
        List<ServiceType> serviceTypes = serviceTypeDG.dg();
        System.out.println(JSONObject.toJSONString(serviceTypes));

        StringBuilder services = new StringBuilder();
        serviceTypes.forEach(item -> {

        });
    }

    public static class ServiceType implements ChildPreservable<ServiceType> {

        private Integer id;

        private String name;

        private Integer parentId;

        private List<ServiceType> serviceTypes;

        public ServiceType(Integer id, String name, Integer parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public List<ServiceType> getServiceTypes() {
            return serviceTypes;
        }

        public void setServiceTypes(List<ServiceType> serviceTypes) {
            this.serviceTypes = serviceTypes;
        }

        @Override
        public void setChild(List<ServiceType> child) {
            this.serviceTypes = child;
        }
    }


}
