package com.example.restapi.business.impl;

import com.example.restapi.business.UserService;
import com.example.restapi.business.component.ExistenceValidator;
import com.example.restapi.business.dto.UserCreateDto;
import com.example.restapi.business.dto.UserInfoDto;
import com.example.restapi.business.dto.UserListDto;
import com.example.restapi.business.dto.UserUpdateDto;
import com.example.restapi.business.exception.NotFoundException;
import com.example.restapi.persistence.UserRepository;
import com.example.restapi.shared.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ExistenceValidator validator;

    @Override
    public Page<UserListDto> listUsers(Pageable pageable) {
        return userRepository.selectAllUsers(pageable)
                .map(UserMapper::toUserListDto);
    }

    @Override
    public UserInfoDto getUserBy(Long id) {
        return userRepository.selectUserBy(id)
                .map(UserMapper::toUserInfoDto)
                .orElseThrow(() -> new NotFoundException("User Not Found [Id: " + id + "]"));
    }

    @Override
    public Long registerUser(UserCreateDto dto) {
        return userRepository.insertUser(UserMapper.toEntity(dto));

    }

    @Override
    public void editUser(Long id, UserUpdateDto dto) {
        validator.validateUserExists(id);
        userRepository.updateUser(id, UserMapper.toEntity(dto));
    }

    @Override
    public void removeUserBy(Long id) {
        validator.validateUserExists(id);
        userRepository.deleteUserBy(id);
    }


}
