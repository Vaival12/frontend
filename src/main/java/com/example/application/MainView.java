package com.example.application;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Route("")
public class MainView extends VerticalLayout {

    // Создаем текстовое поля для ввода данных
    TextField textField = new TextField("Введите данные");

    TextField textField1 = new TextField("Результат запроса с бэкенда");
    public MainView() {

        // Кнопка для отправки данных
        Button sendButton = new Button("Отправить", event -> {
            String inputData = textField.getValue();
            if (!inputData.isEmpty()) {
                // Отправляем данные на бэкенд
                String response = sendDataToBackend(inputData);
                Notification.show(response);
            } else {
                Notification.show("Пожалуйста, введите данные!");
            }
        });

        textField1 = new TextField("Результат запроса с бэкенда");
        textField1.setReadOnly(true);
        //Button getButton = new Button("Получить данные с бэкенда", event -> getData());


        add(textField, sendButton,textField1);
    }

    // Метод для отправки данных на бэкенд
    private String sendDataToBackend(String data) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(data, headers);

            // Отправка POST-запроса на сервер
            String response = restTemplate.postForObject("http://localhost:8080/api/save", request, String.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при отправке данных на сервер.";
        }
    }


}
