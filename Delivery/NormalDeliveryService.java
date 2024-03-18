package Delivery;

public class NormalDeliveryService implements DeliveryService {
    private double deliveryPrice; // 350 ภายใน 3 วัน

    public NormalDeliveryService(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public double deliverPrice() {
        return deliveryPrice;
    }
}
