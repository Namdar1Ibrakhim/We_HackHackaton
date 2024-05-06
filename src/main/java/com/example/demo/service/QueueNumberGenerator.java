package com.example.demo.service;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class QueueNumberGenerator {

    private LocalDate lastDate;
    private int dailyCounter;

    public QueueNumberGenerator() {
        this.lastDate = LocalDate.now();
        this.dailyCounter = 0;
    }

    public synchronized String generateQueueNumber() {
        LocalDate currentDate = LocalDate.now();
        
        // Проверяем, нужно ли обнулить счётчик для нового дня
        if (!currentDate.isEqual(lastDate)) {
            lastDate = currentDate;
            dailyCounter = 0; // Обнуляем счётчик
        }
        
        // Генерируем номер очереди
        String datePart = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String counterPart = String.format("%03d", dailyCounter);
        
        dailyCounter++; // Увеличиваем счётчик для следующего талона
        
        return datePart + counterPart;
    }
}
