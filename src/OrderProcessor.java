// Интерфейс оплаты для соблюдения OCP
interface PaymentProcessor {
    void processPayment(double amount);
}

// Реализация для оплаты кредитной картой
class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

// Реализация для оплаты через PayPal
class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

// Класс для логирования (соблюдение SRP)
class Logger {
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

// Основной класс обработки заказов (соблюдение SRP и DIP)
class OrderProcessor {
    private final PaymentProcessor paymentProcessor;
    private final Logger logger;

    // Внедрение зависимостей через конструктор
    public OrderProcessor(PaymentProcessor paymentProcessor, Logger logger) {
        this.paymentProcessor = paymentProcessor;
        this.logger = logger;
    }

    public void processOrder(String orderId, double amount) {
        logger.log("Processing order: " + orderId);
        paymentProcessor.processPayment(amount);
        logger.log("Order " + orderId + " processed successfully.");
    }
}

// Класс для обработки специальных заказов
class SpecialOrderProcessor extends OrderProcessor {

    public SpecialOrderProcessor(PaymentProcessor paymentProcessor, Logger logger) {
        super(paymentProcessor, logger);
    }

    // Переопределение метода без нарушения ожидаемого поведения
    @Override
    public void processOrder(String orderId, double amount) {
        super.processOrder(orderId, amount);
        System.out.println("Additional processing for special order: " + orderId);
    }
}

// Интерфейс уведомлений
interface NotificationService {
    void notifyCustomer(String customerId, String message);
}

// Реализация для уведомлений по email
class EmailNotificationService implements NotificationService {
    @Override
    public void notifyCustomer(String customerId, String message) {
        System.out.println("Sending email to " + customerId + ": " + message);
    }
}

// Точка входа в приложение
public class Main {
    public static void main(String[] args) {
        // Создание зависимостей
        Logger logger = new Logger();
        PaymentProcessor paymentProcessor = new CreditCardPaymentProcessor();
        NotificationService notificationService = new EmailNotificationService();

        // Обработка стандартного заказа
        OrderProcessor orderProcessor = new OrderProcessor(paymentProcessor, logger);
        orderProcessor.processOrder("12345", 99.99);

        // Обработка специального заказа
        OrderProcessor specialOrderProcessor = new SpecialOrderProcessor(paymentProcessor, logger);
        specialOrderProcessor.processOrder("54321", 199.99);

        // Уведомление клиента
        notificationService.notifyCustomer("customer1@example.com", "Your order has been completed.");
    }
}
