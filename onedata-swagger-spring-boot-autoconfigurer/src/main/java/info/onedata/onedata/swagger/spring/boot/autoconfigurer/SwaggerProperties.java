package info.onedata.onedata.swagger.spring.boot.autoconfigurer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

@ConfigurationProperties(prefix = "openapi.swagger")
public class SwaggerProperties {

    /**
     * 文档标题
     */
    private String title = "接口文档";

    /**
     * 文档版本
     */
    private String version = "1.0.0";

    /**
     * 包
     */
    private String packages;

    /**
     * 忽略类
     */
    private String[] ignoreClasses;

    /**
     * 简介
     */
    private String description;

    /**
     * 联系方式
     */
    private Contact contact;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String[] getIgnoreClasses() {
        return ignoreClasses;
    }

    public void setIgnoreClasses(String[] ignoreClasses) {
        this.ignoreClasses = ignoreClasses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
