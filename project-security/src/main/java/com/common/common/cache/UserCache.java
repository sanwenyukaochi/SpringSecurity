package com.common.common.cache;

import com.common.authentication.handler.auth.UserLoginInfo;
import com.common.common.cache.constant.RedisCache;
import com.common.common.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCache  {

    private final UserService userService;

    @Cacheable(value = RedisCache.USER_INFO, key = "#username")
    public UserLoginInfo getUserLoginInfo(String username, String sessionId, Long expiredTime){
        UserLoginInfo userLoginInfo = userService.loadUserByUsername(username);
        userLoginInfo.setSessionId(sessionId);
        userLoginInfo.setExpiredTime(expiredTime);
        return userLoginInfo;
    }

}
