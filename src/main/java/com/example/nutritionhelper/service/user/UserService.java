package com.example.nutritionhelper.service.user;


import com.example.nutritionhelper.domain.user.User;
import com.example.nutritionhelper.domain.user.UserRepository;
import com.example.nutritionhelper.domain.userGroup.UserGroup;
import com.example.nutritionhelper.domain.userGroup.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    @Transactional
    public User create(final User user){
        if (user == null || user.getEmail() == null){
            throw new RuntimeException("Invalid arguments");
        }
        final String email = user.getEmail();
        if(userRepository.existsByEmail(email)){
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final User originalUser = userRepository.findByEmail(email);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password, originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }

    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkNameExist(String name) {
        return userRepository.existsByName(name);
    }

    public Long checkUserGroup(String birthDate, String gender) {
        int americanAge = getAmericanAge(birthDate.replace("-", ""));
        UserGroup userGroup = userGroupRepository.findByAgeAndGender(americanAge, gender);
        if(userGroup == null){
            throw new RuntimeException("user group이 존재하지 않습니다.");
        }
        return userGroup.getUserGroupId();
    }

    private int getAmericanAge(String birthDate) {
        LocalDate now = LocalDate.now();
        LocalDate parsedBirthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

        int americanAge = now.minusYears(parsedBirthDate.getYear()).getYear(); // (1)

        // (2)
        // 생일이 지났는지 여부를 판단하기 위해 (1)을 입력받은 생년월일의 연도에 더한다.
        // 연도가 같아짐으로 생년월일만 판단할 수 있다
        if (parsedBirthDate.plusYears(americanAge).isAfter(now)) {
            americanAge = americanAge -1;
        }

        return americanAge;
    }

    public User getById(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
