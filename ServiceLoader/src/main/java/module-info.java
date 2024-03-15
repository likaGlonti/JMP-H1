module service.loader {
    uses com.epam.jmp.service.api.Service;
    uses com.epam.jmp.api.Bank;
    requires jmp.cloud.service.impl;
    requires jmp.cloud.bank.impl;
}