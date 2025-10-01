public class VideoView implements View{
    private String content;

    public VideoView(String content) {
        this.content = content;
    }

    @Override
    public void displayMedia() {
        System.out.println("Displaying video: "+ content);
        
    }

    @Override
    public void setContentForMedia(String content) {
        this.content=content;
        
    }

}