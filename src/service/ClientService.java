package service;

import model.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private final List<Client> clients = new ArrayList<>();
    private int nextId = 1;

    public ClientService() {
        loadClients(); // Теперь правильно загружает данные
    }

    // Реализованная загрузка клиентов
    private void loadClients() {
        List<String[]> data = FileService.loadFromCsv("clients.csv");
        if (data == null || data.isEmpty()) return;

        // Пропускаем заголовок если есть
        int startIndex = data.get(0)[0].equals("id") ? 1 : 0;

        for (int i = startIndex; i < data.size(); i++) {
            String[] row = data.get(i);
            clients.add(new Client(
                    Integer.parseInt(row[0]), // id
                    row[1], // name
                    row[2], // email
                    row.length > 3 ? row[3] : "" // phone (может отсутствовать)
            ));
            nextId = Math.max(nextId, Integer.parseInt(row[0]) + 1);
        }
    }

    // Полностью рабочее сохранение
    public void saveClients() {
        List<String[]> data = new ArrayList<>();
        // Добавляем заголовок
        data.add(new String[]{"id", "name", "email", "phone"});

        for (Client client : clients) {
            data.add(new String[]{
                    String.valueOf(client.getId()),
                    client.getName(),
                    client.getEmail(),
                    client.getPhone()
            });
        }
        FileService.saveToCsv(data, "clients.csv");
    }

    public Client findOrCreateClient(String name, String email, String phone) {
        Client existingClient = clients.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (existingClient != null) {
            return existingClient;
        }

        Client newClient = new Client(nextId++, name, email, phone);
        clients.add(newClient);
        saveClients(); // Теперь точно сохраняет
        return newClient;
    }

    // Остальные методы без изменений
    public Client getClient(int id) {
        return clients.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateClient(int id, String name, String email, String phone) {
        Client client = getClient(id);
        if (client != null) {
            if (name != null) client.setName(name);
            if (email != null) client.setEmail(email);
            if (phone != null) client.setPhone(phone);
            saveClients(); // Сохраняем после изменений
        }
    }

    public void deleteClient(int id) {
        clients.removeIf(c -> c.getId() == id);
        saveClients(); // Сохраняем после удаления
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }
}