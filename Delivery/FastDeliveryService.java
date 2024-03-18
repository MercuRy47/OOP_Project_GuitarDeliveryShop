package Delivery;

public class FastDeliveryService implements DeliveryService {
    private double deliveryPrice; // 500 ภายใน 24 ชั่วโมง

    public FastDeliveryService(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public double deliverPrice() {
        return deliveryPrice;
    }
}