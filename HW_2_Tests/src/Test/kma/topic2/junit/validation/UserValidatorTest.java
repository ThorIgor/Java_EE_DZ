package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserValidator userValidator;

    @ParameterizedTest(name = "login [{0}] password [{1}] fullName [{2}]")
    @MethodSource("GoodData")
    void validateNewUserTest(final String login, final String password, final String fullName) {
        userValidator.validateNewUser(NewUser.builder().login(login)
                .password(password).fullName(fullName).build());
    }


    @ParameterizedTest(name = "login [{0}] password [{1}] fullName [{2}]")
    @MethodSource("BadPass")
    void validateBadPasswordTest(final String login, final String password, final String fullName) {
        assertThatThrownBy(() -> userValidator.validateNewUser(NewUser.builder().login(login)
                .password(password).fullName(fullName).build()))
                .isInstanceOf(ConstraintViolationException.class);

    }

    @ParameterizedTest(name = "login [{0}] password [{1}] fullName [{2}]")
    @CsvSource({ "Login, Pass, FullName" })
    void validateBadLoginTest(final String login, final String password, final String fullName) {
        Mockito.when(userRepository.isLoginExists("Login")).thenReturn(Boolean.TRUE);
        assertThatThrownBy(() -> userValidator.validateNewUser(NewUser.builder().login(login)
                .password(password).fullName(fullName).build())).isInstanceOf(LoginExistsException.class);

    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> GoodData() {
        return Stream.of(Arguments.of("Login", "Pass", "FullName"),
                Arguments.of("Log", "Passs", "FullName"),
                Arguments.of("Gin", "Passss", "FullName"));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> BadPass() {
        return Stream.of(Arguments.of("Login", "Pa", "FullName"),
                Arguments.of("Login", "Pass\\", "FullName"),
                Arguments.of("Login", "Passssss", "FullName"));
    }
}