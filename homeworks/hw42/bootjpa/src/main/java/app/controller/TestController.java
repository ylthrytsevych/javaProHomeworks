package app.controller;
import app.dto.PostRequest;
import app.dto.UserRequest;
import app.service.TransactionDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TransactionDemoService transactionDemoService;

    @PostMapping("/rollback")
    public String testRollback() {
        // Хардкодимо дані суто для тестування відкату
        UserRequest userRequest = new UserRequest("Тест Юзер", "rollback@test.com");
        PostRequest postRequest = new PostRequest("Тест Пост", "Контент", null);

        //тут поставити брейкпоінт і глянути базу! --- не буде видно, бо поки не закінчиться транзакція в базу не комітиться
        transactionDemoService.executeRollbackTest(userRequest, postRequest);

        return "Цей текст ніколи не повернеться";
    }
}
