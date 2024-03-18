package User;

public class User {
    private String userID;
    private String username;
    private String password;
    protected String rank;
    private UserBasket basket;

    public User() {
        username = "";
        password = "";
        rank = "Normal";
        basket = new UserBasket(this);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String rank) {
        this.username = username;
        this.password = password;
        this.rank = rank;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // ส่วนลดเริ่มต้นที่ 0%
    public double getDiscount() {
        return 0.0;
    }

    public UserBasket getBasket() {
        return basket;
    }
}

