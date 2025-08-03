package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.dto.AccountDTO;
import com.sanwenyukaochi.security.dto.RouterDTO;
import com.sanwenyukaochi.security.vo.AccountVO;
import com.sanwenyukaochi.security.vo.Result;
import com.sanwenyukaochi.security.vo.RouterVO;
import com.sanwenyukaochi.security.vo.page.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sanwenyukaochi.security.service.UserService;
import com.sanwenyukaochi.security.service.RouterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final RouterService routerService;
    @GetMapping("/users")
    @Operation(summary = "查询账户列表")
    public Result<PageVO<AccountVO>> listUsers(@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(currentPage, size);
        Page<AccountDTO> accountPage = userService.findAllUser(pageable);
        return Result.success(PageVO.from(accountPage.map(newUser -> new AccountVO(
                newUser.getId(),
                newUser.getUserName(),
                newUser.getNickName(),
                newUser.getPhone(),
                newUser.getAvatar(),
                new AccountVO.TenantVO(
                        newUser.getTenant().getTenantId(),
                        newUser.getTenant().getTenantName()
                ),
                newUser.getUpdatedAt(),
                newUser.getCreatedAt()
        ))));
    }

    @GetMapping("/me")
    @Operation(summary = "获取当前登录用户信息")
    public Result<AccountVO> currentUserInfo(Authentication authentication) {
        AccountDTO user = userService.findByUserInfo(authentication);
        return Result.success(new AccountVO(
                user.getId(),
                user.getUserName(),
                user.getNickName(),
                user.getPhone(),
                user.getAvatar(),
                new AccountVO.TenantVO(
                        user.getTenant().getTenantId(),
                        user.getTenant().getTenantName()
                ),
                user.getUpdatedAt(),
                user.getCreatedAt()
        ));
    }

    @GetMapping("/router")
    @Operation(summary = "获取当前用户路由权限")
    public Result<List<RouterVO>> getRouter(Authentication authentication) {
        List<RouterDTO> routerDTOs = routerService.getUserRouters(authentication);
        return Result.success(convertRouters(routerDTOs));
    }

    private List<RouterVO> convertRouters(List<RouterDTO> routerDTOs) {
        if (routerDTOs == null) return Collections.emptyList();
        return routerDTOs.stream()
                .map(dto -> new RouterVO(
                        dto.getId(),
                        dto.getName(),
                        dto.getCode(),
                        dto.getPath(),
                        dto.getType(),
                        dto.getSort(),
                        convertRouters(dto.getChildren())
                )).collect(Collectors.toList());
    }

}
