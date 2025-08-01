package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.dto.AccountDTO;
import com.sanwenyukaochi.security.entity.User;
import com.sanwenyukaochi.security.repository.UserRepository;
import com.sanwenyukaochi.security.security.service.UserDetailsImpl;
import com.sanwenyukaochi.security.vo.AccountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Page<AccountDTO> findAllUser(Pageable pageable) {
        Page<User> all = userRepository.findAll(pageable);
        return all.map(newUser -> new AccountDTO(
                newUser.getId(),
                newUser.getUserName(),
                newUser.getNickName(),
                newUser.getPhone(),
                newUser.getAvatar(),
                new AccountDTO.TenantVO(
                        newUser.getTenant().getId(),
                        newUser.getTenant().getName()
                ),
                newUser.getUpdatedAt(),
                newUser.getCreatedAt()
        ));
    }

    public AccountDTO findByUserInfo(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new AccountDTO(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getNickName(),
                userDetails.getPhone(),
                userDetails.getAvatar(),
                new AccountDTO.TenantVO(
                        userDetails.getTenant().getId(),
                        userDetails.getTenant().getName()
                ),
                userDetails.getUpdatedAt(),
                userDetails.getCreatedAt()
        );
    }

} 