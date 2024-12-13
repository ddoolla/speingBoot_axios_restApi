package com.example.restapi.api;

import com.example.restapi.api.request.UserCreateRequest;
import com.example.restapi.api.request.UserUpdateRequest;
import com.example.restapi.api.response.UserInfoResponse;
import com.example.restapi.api.response.UserListResponse;
import com.example.restapi.business.UserService;
import com.example.restapi.shared.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserListResponse> readManyUsers(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(new UserListResponse(userService.listUsers(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> readOneUser(@PathVariable Long id) {
        return ResponseEntity.ok(new UserInfoResponse(userService.getUserBy(id)));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateRequest request) {
        Long newUserId = userService.registerUser(UserMapper.toUserCreateDto(request));
        return ResponseEntity.created(URI.create("/api/users/" + newUserId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                           @Valid @RequestBody UserUpdateRequest request) {
        userService.editUser(id, UserMapper.toUserUpdateDto(request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.removeUserBy(id);
        return ResponseEntity.noContent().build();
    }
}
