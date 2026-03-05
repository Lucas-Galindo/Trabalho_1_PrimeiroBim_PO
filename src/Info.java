public class Info {
    int info;

    public Info(int info) { this.info = info; }
    public Info() {}

    public int getInfo() { return info; }
    public void setInfo(int info) { this.info = info; }

    @Override
    public String toString() {
        return String.valueOf(info);
    }
}