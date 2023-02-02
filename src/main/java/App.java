import sevice.OrderService;

public class App {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.createOrderHibernate(1L);
        orderService.createOrderJDBC(2L);
    }

}
