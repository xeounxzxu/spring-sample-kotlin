package com.example.dynamicbean.web

import com.example.dynamicbean.service.AccountFactoryService
import com.example.dynamicbean.service.dto.AccountModel
import com.example.dynamicbean.service.dto.AccountType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/account")
class AccountController(
    private val accountService: AccountFactoryService
) {

    @GetMapping
    fun getAccount(type: AccountType): List<AccountModel> {
        return accountService.getList(type)
    }
}
