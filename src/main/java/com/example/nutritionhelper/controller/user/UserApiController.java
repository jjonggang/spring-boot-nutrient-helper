package com.example.nutritionhelper.controller.user;

import com.example.nutritionhelper.domain.user.User;
import com.example.nutritionhelper.dto.Response.ResponseDto;
import com.example.nutritionhelper.dto.User.UserDto;
import com.example.nutritionhelper.security.TokenProvider;
import com.example.nutritionhelper.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1")
public class UserApiController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try{
            User user = User.builder()
                    .email(userDto.getEmail())
                    .name(userDto.getName())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .birthDate(userDto.getBirthDate())
                    .gender(userDto.getGender())
                    .build();
            User registeredUser = userService.create(user);
//            UserDto responseUserDto = UserDto.builder()
//                    .email(registeredUser.getEmail())
//                    .userId(registeredUser.getUserId())
//                    .name(registeredUser.getName())
//                    .build();
            return ResponseEntity.ok().body("success");
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDto userDto){
        User user = userService.getByCredentials(
                userDto.getEmail(),
                userDto.getPassword(),
                passwordEncoder
        );
        if(user!=null){
            final String token = tokenProvider.create(user);
            final UserDto responseUserDto = UserDto.builder()
                    .email(user.getEmail())
                    .userId(user.getUserId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDto);
        }else{
            ResponseDto responseDto = ResponseDto.builder()
                    .error("Login failed")
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @PostMapping("/auth/email-duplication")
    public ResponseEntity<?> emailExists(@RequestParam("email") String email){

        try{
            boolean exist = userService.checkEmailExist(email);
            if(exist){
                return ResponseEntity.ok().body(1);
            }else{
                return ResponseEntity.ok().body(0);
            }

        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }

    @PostMapping("/auth/name-duplication")
    public ResponseEntity<?> nameExists(@RequestParam("name") String name){

        try{
            boolean exist = userService.checkNameExist(name);
            if(exist){
                return ResponseEntity.ok().body(1);
            }else{
                return ResponseEntity.ok().body(0);
            }

        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }


//    @PutMapping("/user/birth-date")
//    public ResponseEntity<?> updateBirthDate(@RequestParam("birth_date")String birthDate, @AuthenticationPrincipal String strUserId) throws Exception{
//        Long userId = Long.parseLong(strUserId);
//        // 이미 존재하는 프로필 사진에 대한 삭제
//        User user = userService.updateBirthDate(userId, birthDate);
//        return ResponseEntity.ok().body(user.getName());
//    }

//    @PutMapping("/user/gender")
//    public ResponseEntity<?> changeUserName(@RequestParam("name")String name, @AuthenticationPrincipal String strUserId) throws Exception{
//        Long userId = Long.parseLong(strUserId);
//        // 이미 존재하는 프로필 사진에 대한 삭제
//        User user = userService.updateName(userId, name);
//        return ResponseEntity.ok().body(user.getName());
//    }
//    @PutMapping("/user/name")
//    public ResponseEntity<?> changeUserName(@RequestParam("name")String name, @AuthenticationPrincipal String strUserId) throws Exception{
//        Long userId = Long.parseLong(strUserId);
//        // 이미 존재하는 프로필 사진에 대한 삭제
//        User user = userService.updateName(userId, name);
//        return ResponseEntity.ok().body(user.getName());
//    }

}
