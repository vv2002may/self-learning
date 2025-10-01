
public class WhatsappStatus{

    private ViewFactory viewFactory;
    private View view;

    public WhatsappStatus(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    public void publishStatus(String type, String content) {
        view= ViewFactory.createView(type, content);
        view.displayMedia();
    }

    public void removeStatus(String type, String content) {
        view.setContentForMedia(null);
        
        if (type.equals("text")) {
            System.out.println("Removed text status: "+content);
        } else if (type.equals("image")) {
            System.out.println("Removed image status: "+content);
        } else {
            System.out.println("Removed video status: "+content);
        }
    }

    
}