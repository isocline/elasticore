package io.elasticore.springboot3.http;

import io.elasticore.runtime.port.ExternalService;
import io.elasticore.runtime.port.HttpEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HttpServiceProxyFactoryTest {

    @Mock
    private BeanFactory beanFactory;

    @Mock
    private Environment environment;

    private TestService testService;

    // 테스트용 인터페이스
    interface TestService {
        @HttpEndpoint(method = "GET", url = "/api/users/{id}")
        UserResponse getUser(String id);

        @HttpEndpoint(method = "POST", url = "/api/users")
        UserResponse createUser(UserRequest request);

        @HttpEndpoint(method = "GET", url = "/api/search")
        SearchResponse searchUsers(String query, Integer page, Integer size);
    }

    // 테스트용 DTO 클래스들
    record UserRequest(String name, String email) {}
    record UserResponse(String id, String name, String email) {}
    record SearchResponse(java.util.List<UserResponse> users, int totalCount) {}

    @ExternalService(id = "testService", url = "http://api.test.com" , protocol = "http")
    static class TestServiceImpl implements TestService {
        @Override
        public UserResponse getUser(String id) { return null; }
        @Override
        public UserResponse createUser(UserRequest request) { return null; }
        @Override
        public SearchResponse searchUsers(String query, Integer page, Integer size) { return null; }
    }

    @BeforeEach
    void setUp() {
        // 기본 환경 설정
        when(environment.getProperty("testService.url", "http://api.test.com"))
                .thenReturn("http://api.test.com");
        when(environment.getProperty("testService.authKey", ""))
                .thenReturn("test-auth-token");

        ExternalService externalService = TestServiceImpl.class.getAnnotation(ExternalService.class);
        testService = HttpServiceProxyFactory.createService(
                TestService.class,
                externalService,
                beanFactory,
                environment
        );
    }

    @Test
    @DisplayName("GET 요청 - 단일 사용자 조회 테스트")
    void getUser_ShouldHandlePathParameter() {
        // given
        String userId = "user123";

        // when & then
        assertThatCode(() -> testService.getUser(userId))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("POST 요청 - 사용자 생성 테스트")
    void createUser_ShouldHandleRequestBody() {
        // given
        UserRequest request = new UserRequest("홍길동", "hong@test.com");

        // when & then
        assertThatCode(() -> testService.createUser(request))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("GET 요청 - 쿼리 파라미터 처리 테스트")
    void searchUsers_ShouldHandleQueryParameters() {
        // given
        String searchQuery = "홍길동";
        Integer page = 1;
        Integer size = 10;

        // when & then
        assertThatCode(() -> testService.searchUsers(searchQuery, page, size))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("인증 키가 없는 경우 테스트")
    void shouldHandleNoAuthKey() {
        // given
        when(environment.getProperty("testService.authKey", ""))
                .thenReturn("");

        // when & then
        assertThatCode(() -> testService.getUser("user123"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("프록시 기본 메서드 테스트")
    void proxyBasicMethods() {
        // toString 테스트
        assertThat(testService.toString())
                .contains("Proxy");

        // equals 테스트
        assertThat(testService)
                .isEqualTo(testService)
                .isNotEqualTo(new Object());

        // hashCode 테스트
        assertThat(testService.hashCode())
                .isNotZero();
    }

    @Test
    @DisplayName("잘못된 서버 URL 설정 테스트")
    void invalidServerUrl() {
        // given
        when(environment.getProperty("testService.url", "http://api.test.com"))
                .thenReturn("invalid-url");

        // when & then
        assertThatCode(() -> testService.getUser("user123"))
                .isInstanceOf(ResponseStatusException.class);
    }
}