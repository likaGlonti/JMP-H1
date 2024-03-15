module app {
    requires transitive jmp.cloud.bank.impl;
    requires transitive jmp.cloud.service.impl;
    uses com.epam.jmp.api.Bank;
    uses com.epam.jmp.service.api.Service;
    requires jmp.dto;
}