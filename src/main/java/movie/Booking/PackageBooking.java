package movie.Booking;


public class PackageBooking {

    private int id;
    private String userName;
    private String userEmail;
    private String bookpkgName;
    private String bookpkgType;
    private String startDate;
    private int duration;
    private String paymentMethod;


    public PackageBooking(int id, String userName, String userEmail, String bookpkgName, String bookpkgType, String startDate, int duration, String paymentMethod) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.bookpkgName = bookpkgName;
        this.bookpkgType = bookpkgType;
        this.startDate = startDate;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
    }
    
    public PackageBooking( String userName, String userEmail, String bookpkgName, String bookpkgType, String startDate, int duration, String paymentMethod) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.bookpkgName = bookpkgName;
        this.bookpkgType = bookpkgType;
        this.startDate = startDate;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBookpkgName() {
        return bookpkgName;
    }

    public void setBookpkgName(String bookpkgName) {
        this.bookpkgName = bookpkgName;
    }

    public String getBookpkgType() {
        return bookpkgType;
    }

    public void setBookpkgType(String bookpkgType) {
        this.bookpkgType = bookpkgType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

