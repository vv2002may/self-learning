
public class ImageView implements View{

    private String content;

    
    public ImageView(String content) {
        this.content = content;
    }

    @Override
    public void displayMedia() {
        System.out.println("Displaying image: "+ content);
        
    }

    @Override
    public void setContentForMedia(String content) {
        this.content=content;
        
    }

}