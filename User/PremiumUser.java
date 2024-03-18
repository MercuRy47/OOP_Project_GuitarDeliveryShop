package User;

public class PremiumUser extends User {
    public PremiumUser() {
        super(); // เรียก constructor ของ User
        rank = "Premium"; // กำหนด rank เป็น Premium
    }

    @Override
    public double getDiscount() {
        return 0.3; // ส่วนลดสำหรับ PremiumUser คือ 30%
    }
}

