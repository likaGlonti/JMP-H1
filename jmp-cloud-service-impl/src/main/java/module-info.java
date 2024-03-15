import com.epam.jmp.service.api.Service;
import com.epam.jmp.service.api.impl.ServiceImpl;

module jmp.cloud.service.impl {
    requires java.sql;
    requires transitive jmp.service.api;
    requires jmp.dto;
    exports com.epam.jmp.service.api.impl to app;
    provides Service with ServiceImpl;
}