package info.onedata.swagger.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ValueController {

    @Value("${datascope.ignore-tables:}")
    private Set<String> ignoreTables;

    @GetMapping("value")
    public Object value() {
        System.out.println(ignoreTables.size());
        return ignoreTables;
    }

}
