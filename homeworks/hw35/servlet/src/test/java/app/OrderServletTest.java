package app;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ServletConfig config;

    private ServletInputStream createMockInputStream(String jsonContent) {
        // 1. Перетворюємо рядок на масив байтів.
        // UTF_8 гарантує правильні коди для символів
        ByteArrayInputStream bais = new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8));

        // 2. Створюємо "обгортку", яка вдає із себе ServletInputStream
        return new ServletInputStream() {
            // Чи закінчилися байти? Перевіряємо, чи є ще щось у нашому масиві bais.
            @Override public boolean isFinished() { return bais.available() == 0; }

            // Чи готовий потік до читання? У тестах — завжди так.
            @Override public boolean isReady() { return true; }

            // Метод для асинхронного читання (нам не потрібен, тому порожній)
            @Override public void setReadListener(ReadListener readListener) {}

            // НАЙГОЛОВНІШЕ: Коли Jackson викличе сервлетівське read(),
            // ми реально прочитаємо байт із нашого масиву bais.
            @Override public int read() { return bais.read(); }
        };
    }

    private ServletOutputStream createMockOutputStream(ByteArrayOutputStream baos) {
        // Створюємо обгортку для ServletOutputStream
        return new ServletOutputStream() {
            // Чи готовий приймати дані? Так для тестів
            @Override public boolean isReady() { return true; }

            // Для асинхронного запису (не використовуємо)
            @Override public void setWriteListener(WriteListener writeListener) {}

            // НАЙГОЛОВНІШЕ: Коли сервлет (через Jackson) пише байт (write),
            // ми цей байт складаємо у наше "відро" (baos).
            @Override public void write(int b) { baos.write(b); }
        };
    }

    private OrderServlet orderServlet;

    @BeforeEach
    void setUp() throws Exception {
        orderServlet = new OrderServlet();
        orderServlet.init(config);
    }

    @Test
    void testDoPost_CreatesOrder() throws Exception {
        String inputJson = "{\"id\":1, \"date\":\"2026-05-27\", \"cost\":100.0, \"products\":[]}";

        // Кажемо Mockito: "Коли сервлет попросить тіло запиту (InputStream), поверни наш JSON"
        when(request.getInputStream()).thenReturn(createMockInputStream(inputJson));

        // Підготовлюємо "перехоплювач" для відповіді сервлета (щоб прочитати, що він відповість)
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Викликаємо метод doPost напряму, передаючи йому наш моки
        orderServlet.doPost(request, response);

        // Перевіряємо, чи сервлет встановив правильний статус код (201 Created)
        verify(response).setStatus(HttpServletResponse.SC_CREATED);

        // Перевіряємо, чи у відповіді є текст "Order created"
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("Order created with ID: 1"));
    }

    @Test
    void testDoGet_ReturnsOrder() throws Exception {
        // Щоб щось отримати (GET), треба спочатку це створити
        // Робимо "фейковий" POST запит для додавання замовлення в пам'ять сервлета
        String inputJson = "{\"id\":99, \"date\":\"2026-05-27\", \"cost\":500.0, \"products\":[]}";
        when(request.getInputStream()).thenReturn(createMockInputStream(inputJson));
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        orderServlet.doPost(request, response);

        // 1. ПІДГОТОВКА до GET
        // Змінюємо налаштування мока: тепер він каже, що користувач зайшов на /orders/99
        when(request.getPathInfo()).thenReturn("/99");

        // готуєм переховлювач на вихідний потік (OutputStream), куди Jackson буде писати JSON-відповідь
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(createMockOutputStream(baos));

        // 2. ДІЯ (When)
        orderServlet.doGet(request, response);

        // 3. ПЕРЕВІРКА (Then)
        // Перевіряємо, чи сервлет відповів статусом 200 OK
        verify(response).setStatus(HttpServletResponse.SC_OK);

        // Перевіряємо, чи у відповіді є наш ID
        String responseJson = baos.toString(StandardCharsets.UTF_8);
        assertTrue(responseJson.contains("\"id\":99"));
    }


}
