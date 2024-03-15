import com.epam.jmp.api.Bank;
import com.epam.jmp.cloud.bank.api.BankImpl;

module jmp.cloud.bank.impl {
    requires transitive bank.api;
    requires jmp.dto;
    exports com.epam.jmp.cloud.bank.api to app;
    provides Bank with BankImpl;
}