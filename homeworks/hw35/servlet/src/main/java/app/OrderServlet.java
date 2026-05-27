package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet("/orders/*")
    public class OrderServlet extends HttpServlet {

        private OrderRepository repository;
        private ObjectMapper objectMapper;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            this.repository = new OrderRepositoryImpl();
            this.objectMapper = new ObjectMapper(); // Ініціалізуємо Jackson
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = extractIdFromPath(req.getPathInfo());
            Order order = repository.getById(id);

            if (order != null) {
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK); // 200

                objectMapper.writeValue(resp.getOutputStream(), order); // jackson перетворює Order у JSON
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            }
        }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Order newOrder = objectMapper.readValue(req.getInputStream(), Order.class);

            repository.save(newOrder);

            resp.setStatus(HttpServletResponse.SC_CREATED); // 201
            resp.getWriter().write("Order created with ID: " + newOrder.getId());
        }

        // UPDATE
        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Order updatedOrder = objectMapper.readValue(req.getInputStream(), Order.class);

            if (repository.getById(updatedOrder.getId()) != null) { // чи існує таке замовлення
                repository.save(updatedOrder);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Order updated successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = extractIdFromPath(req.getPathInfo());

            if (repository.getById(id) != null) {
                repository.deleteById(id);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }


        private int extractIdFromPath(String pathInfo) {
            if (pathInfo == null || pathInfo.equals("/")) {
                throw new IllegalArgumentException("ID is required");
            }
            // pathInfo починається з / тому беремо з 1го символу
            return Integer.parseInt(pathInfo.substring(1));
        }
}
