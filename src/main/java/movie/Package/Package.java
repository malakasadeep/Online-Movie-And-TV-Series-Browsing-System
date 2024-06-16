package movie.Package;

public class Package {

    private int id;
    private String pkgName;
    private String description;
    private double price;
    private int duration;
    private String videoQuality;
    private String pkgType;

    public Package(int id, String pkgName, String description, double price, int duration, String videoQuality, String pkgType) {
        this.id = id;
        this.pkgName = pkgName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.videoQuality = videoQuality;
        this.pkgType = pkgType;
    }

    public Package(String pkgName, String description, double price, int duration, String videoQuality, String pkgType) {
        this.pkgName = pkgName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.videoQuality = videoQuality;
        this.pkgType = pkgType;
    }

    public int getId() {
        return id;
    }

    public String getPkgName() {
        return pkgName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getVideoQuality() {
        return videoQuality;
    }

    public String getPkgType() {
        return pkgType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }

    public void setPkgType(String pkgType) {
        this.pkgType = pkgType;
    }
}

