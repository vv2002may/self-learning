
public class ViewFactory {
    public static View createView(String type, String content) {
        if (type.equals("text")) {
            return new TextView(content);
        } else if (type.equals("image")) {
            return new ImageView(content);
        } else {
            return new VideoView(content);
        }
    }

}
